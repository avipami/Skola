using IoTHubTrigger = Microsoft.Azure.WebJobs.EventHubTriggerAttribute;

using Microsoft.Azure.WebJobs;
using Microsoft.Azure.WebJobs.Host;
using Microsoft.Azure.EventHubs;
using System.Text;
using System.Net.Http;
using Microsoft.Extensions.Logging;
using System.Data.SqlClient;
using System;
using Newtonsoft.Json;
using potatisSQL.Models;

namespace potatisSQL
{
    public static class SavetoDB
    {
        private static HttpClient client = new HttpClient();

        [FunctionName("SavetoDB")]
        public static void Run([IoTHubTrigger("messages/events", Connection = "connectionString", ConsumerGroup = "localShit")] EventData message, ILogger log)
        {
            var data = JsonConvert.DeserializeObject<Data>(Encoding.UTF8.GetString(message.Body.Array));

            log.LogInformation("recieved message");

            if (message.Properties["sensorType"].ToString() == "dht")
            {


                using (var conny = new SqlConnection(Environment.GetEnvironmentVariable("SQLDbConny")))
                {
                    conny.Open();

                    using (var cmd = conny.CreateCommand())
                    {
                        var _CoordinateId = InsertCoordinate(cmd, message);
                        var _DeviceTypeId = InsertDeviceType(cmd, message);
                        log.LogInformation(_DeviceTypeId.ToString());
                        var _DeviceVendorId = InsertDeviceVendor(cmd, message);
                        var _ModelId = InsertModel(cmd, message);
                        var _DeviceId = InsertDevices(cmd, message, _DeviceTypeId, _CoordinateId, _DeviceVendorId, _ModelId,log);
                        
                        InsertMessages(cmd, _DeviceId, _DeviceTypeId, data);

                    }

                }

            }
            log.LogInformation($"C# IoT Hub trigger function processed a message: {Encoding.UTF8.GetString(message.Body.Array)}");
        }

        static private int InsertCoordinate(SqlCommand cmd, EventData message)
        {
            cmd.CommandText = "IF NOT EXISTS (SELECT CoordinateId FROM Coordinates WHERE Latitude = @latitude AND Longitude = @longitude)" +
                " INSERT INTO Coordinates OUTPUT inserted.CoordinateId VALUES (@latitude, @longitude)" +
                " ELSE SELECT CoordinateId FROM Coordinates WHERE Latitude = @latitude AND Longitude = @longitude";

            cmd.Parameters.AddWithValue("@Longitude", message.Properties["lon"].ToString());
            cmd.Parameters.AddWithValue("@Latitude", message.Properties["lat"].ToString());

            return Convert.ToInt32(cmd.ExecuteScalar());
        }
        static private int InsertModel(SqlCommand cmd, EventData message)
        {
            cmd.CommandText = "IF NOT EXISTS (SELECT DeviceModelId FROM DeviceModels WHERE DeviceModelName = @DeviceModelName)" +
                                " INSERT INTO DeviceModels OUTPUT inserted.DeviceModelId VALUES (@deviceModelName)" +
                            " ELSE SELECT DeviceModelId FROM DeviceModels WHERE DeviceModelName = @deviceModelName";

            cmd.Parameters.Clear();
            cmd.Parameters.AddWithValue("@deviceModelName", message.Properties["deviceName"].ToString());

            return Convert.ToInt32(cmd.ExecuteScalar());
        }
        static private int InsertDeviceType(SqlCommand cmd, EventData message)
        {

            cmd.CommandText = "IF NOT EXISTS (SELECT DeviceTypeId FROM DeviceTypes WHERE DeviceType = @deviceType)" +
                " INSERT INTO DeviceTypes OUTPUT inserted.DeviceTypeId VALUES (@deviceType)" +
                " ELSE SELECT DeviceTypeId FROM DeviceTypes WHERE DeviceType = @deviceType";

            cmd.Parameters.Clear();
            cmd.Parameters.AddWithValue("@deviceType", message.Properties["sensorType"].ToString());

            return Convert.ToInt32(cmd.ExecuteScalar());
        }
        static private int InsertDevices(SqlCommand cmd, EventData message, int deviceTypeId, int CoordinateId, int vendorId, int modelId,ILogger snog)
        {
            cmd.CommandText = "IF NOT EXISTS (SELECT DeviceId FROM Devices WHERE DeviceName = @deviceName) " +
                                    "INSERT INTO Devices OUTPUT inserted.DeviceId " +
                                    "VALUES (@deviceName, @CoordinateId, @deviceTypeId, @DeviceVendorId, @DeviceModelId)" +
                              " ELSE SELECT DeviceId FROM Devices WHERE DeviceName = @deviceName";

            cmd.Parameters.Clear();
            snog.LogInformation($"INSERT DEVICEID {deviceTypeId}");
            cmd.Parameters.AddWithValue("@deviceName", message.Properties["DEVICEID"].ToString());
            cmd.Parameters.AddWithValue("@deviceTypeId", deviceTypeId);
            cmd.Parameters.AddWithValue("@CoordinateId", CoordinateId);
            cmd.Parameters.AddWithValue("@DeviceVendorId", vendorId);
            cmd.Parameters.AddWithValue("@DeviceModelId", modelId);

            return Convert.ToInt32(cmd.ExecuteScalar());
        }
        static private void InsertMessages(SqlCommand cmd, int DeviceId, int DeviceTypeId, Data data)
        {
            cmd.CommandText = "INSERT INTO DhtMessages VALUES(" +
                "@DeviceId, " +
                "@Temperature," +
                "@Humidity, " +
                "@MessageCreated," +
                "@TemperatureAlert " +
                ")";

            cmd.Parameters.Clear();

            cmd.Parameters.AddWithValue("@DeviceId", DeviceId);
            cmd.Parameters.AddWithValue("@Temperature", data.Temp);
            cmd.Parameters.AddWithValue("@Humidity", data.Hum);

            cmd.Parameters.AddWithValue("@MessageCreated", data.EpochT);
            cmd.Parameters.AddWithValue("@TemperatureAlert", Convert.ToInt32(data.TempAlert));

            cmd.ExecuteNonQuery();
        }
        static private int InsertDeviceVendor(SqlCommand cmd, EventData message)
        {

            cmd.CommandText = "IF NOT EXISTS (SELECT DeviceVendorId FROM DeviceVendors WHERE DeviceVendorName = @DeviceVendorName) " +
                                    "INSERT INTO DeviceVendors OUTPUT inserted.DeviceVendorId " +
                                    "VALUES (@DeviceVendorName)" +
                              " ELSE SELECT DeviceVendorId FROM DeviceVendors WHERE DeviceVendorName = @DeviceVendorName";

            cmd.Parameters.Clear();

            cmd.Parameters.AddWithValue("@DeviceVendorName", message.Properties["vendorName"].ToString());

            return Convert.ToInt32(cmd.ExecuteScalar());
        }
    }
}