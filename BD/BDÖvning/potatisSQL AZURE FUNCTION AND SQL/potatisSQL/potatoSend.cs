using IoTHubTrigger = Microsoft.Azure.WebJobs.EventHubTriggerAttribute;

using Microsoft.Azure.WebJobs;
using Microsoft.Azure.WebJobs.Host;
using Microsoft.Azure.EventHubs;
using System.Text;
using System.Net.Http;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using System.Data.SqlClient;
using System.Collections.Generic;
using System;
using Microsoft.WindowsAzure.Storage.Table;
using potatisSQL.Models;

namespace potatisSQL
{
    public static class potatoSend
    {
        private static HttpClient client = new HttpClient();

        [FunctionName("potatoSend")]
        public static void Run([IoTHubTrigger("messages/events", Connection = "connectionString",ConsumerGroup ="localShit")]EventData message, ILogger log)
        {
            log.LogInformation($"C# IoT Hub trigger function processed a message: {Encoding.UTF8.GetString(message.Body.Array)}");
            using (var conn = new SqlConnection(Environment.GetEnvironmentVariable("Sqldb_Conn")))
            {
                var _data = JsonConvert.DeserializeObject<DhtMeasurement>(Encoding.UTF8.GetString(message.Body.Array));
                conn.Open();
                using (var cmd = new SqlCommand("", conn))
                {
                    long time = _data.EpochT;
                    /* Time */

                    cmd.CommandText = "IF NOT EXISTS (SELECT 1 FROM TimeTable WHERE UnixUtcTime = @tidsbajs)" +
                        "INSERT INTO TimeTable OUTPUT inserted.TimeTableId VALUES(@tidsbajs)" +
                        " ELSE SELECT TimeTableId FROM TimeTable WHERE UnixUtcTime = @tidsbajs";
                        
                        
                    cmd.Parameters.AddWithValue("@tidsbajs",time);
                    var timeTableId = Convert.ToInt32(cmd.ExecuteScalar());

                    /* DeviceVendors */
                    cmd.CommandText = "IF NOT EXISTS (SELECT VendorId FROM DeviceVendors WHERE VendorName = @Vendor) " +
                        "INSERT INTO DeviceVendors OUTPUT inserted.VendorId VALUES(@Vendor)" +
                        "ELSE SELECT VendorId FROM DeviceVendors WHERE VendorName = @Vendor";

                    cmd.Parameters.AddWithValue("@Vendor", message.Properties["vendorName"]);
                    var vendorId = int.Parse(cmd.ExecuteScalar().ToString());

                    /* DeviceModels */
                    cmd.CommandText = "IF NOT EXISTS (SELECT DevModelsId FROM DeviceModels WHERE ModelName = @ModelName)" +
                        "INSERT INTO DeviceModels OUTPUT inserted.DevModelsId VALUES(@ModelName, @VendorId) " +
                        "ELSE SELECT DevModelsId FROM DeviceModels WHERE ModelName = @ModelName";

                    cmd.Parameters.AddWithValue("@ModelName", message.Properties["deviceName"]);
                    cmd.Parameters.AddWithValue("@VendorId", vendorId);
                    var modelId = int.Parse(cmd.ExecuteScalar().ToString());

                    /* SensorTypes */
                    cmd.CommandText = "IF NOT EXISTS (SELECT SensorTypeId FROM SensorTypes WHERE SensorName = @SensorName) " +
                        "INSERT INTO SensorTypes OUTPUT inserted.SensorTypeId VALUES(@SensorName) " +
                        "ELSE SELECT SensorTypeId FROM SensorTypes WHERE SensorName = @SensorName";

                    cmd.Parameters.AddWithValue("@SensorName", message.Properties["sensorType"]);
                    var sensorTypeId = int.Parse(cmd.ExecuteScalar().ToString());

                    /* GeoLocations */
                    cmd.CommandText = "IF NOT EXISTS (SELECT GeoLocId FROM GeoLocations WHERE Latitude = @Latitude AND Longitude = @Longitude) " +
                        "INSERT INTO GeoLocations OUTPUT inserted.GeoLocId VALUES(@Latitude, @Longitude) " +
                        "ELSE SELECT GeoLocId FROM GeoLocations WHERE Latitude = @Latitude AND Longitude = @Longitude";

                    cmd.Parameters.AddWithValue("@Latitude", message.Properties["lat"]);
                    cmd.Parameters.AddWithValue("@Longitude", message.Properties["lon"]);
                    var geoLocationId = long.Parse(cmd.ExecuteScalar().ToString());
                    
                    /* TempAlerts */
                    cmd.CommandText = "IF NOT EXISTS (SELECT TempAlertsId FROM TemperatureAlerts WHERE Status = @tempAlert) " +
                        "INSERT INTO TemperatureAlerts OUTPUT inserted.TempAlertsId VALUES(@tempAlert) " +
                        "ELSE SELECT TempAlertsId FROM TemperatureAlerts WHERE Status = @tempAlert";

                    cmd.Parameters.AddWithValue("@tempAlert", _data.TempAlert);
                    var tempAlertId = long.Parse(cmd.ExecuteScalar().ToString());


                    /* Devices */
                    cmd.CommandText = "IF NOT EXISTS (SELECT DevicesId FROM Devices WHERE DeviceName = @DeviceName) " +
                        "INSERT INTO Devices OUTPUT inserted.DevicesId VALUES(@DeviceName, @SensorTypeId, @GeoLocationId, @ModelId) " +
                        "ELSE SELECT DevicesId FROM Devices WHERE DeviceName = @DeviceName";
                    cmd.Parameters.AddWithValue("@DeviceName", message.Properties["DEVICEID"].ToString());
                    cmd.Parameters.AddWithValue("@SensorTypeId", sensorTypeId);
                    cmd.Parameters.AddWithValue("@GeoLocationId", geoLocationId);
                    cmd.Parameters.AddWithValue("@ModelId", modelId);
                    var deviceId = long.Parse(cmd.ExecuteScalar().ToString());

                    /* Measurement */
                    var tempData = _data.Temp;
                    var humiData = _data.Hum;

                    cmd.CommandText = "INSERT INTO DhtMeasurements VALUES(@DeviceId, @MeasureUnixTime, @Temperature, @Humidity, @TemperatureAlert)";
                    cmd.Parameters.AddWithValue("@DeviceId", deviceId);
                    cmd.Parameters.AddWithValue("@MeasureUnixTime", timeTableId);
                    cmd.Parameters.AddWithValue("@Temperature", tempData);
                    cmd.Parameters.AddWithValue("@Humidity", humiData);
                    cmd.Parameters.AddWithValue("@TemperatureAlert", tempAlertId);
                    cmd.ExecuteNonQuery();

                    cmd.CommandText = "";

                    log.LogInformation(time.ToString());

                    //return deviceName;
                }
            }
        }
    }

}