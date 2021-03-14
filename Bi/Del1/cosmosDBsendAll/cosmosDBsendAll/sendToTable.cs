using IoTHubTrigger = Microsoft.Azure.WebJobs.EventHubTriggerAttribute;

using Microsoft.Azure.WebJobs;
using Microsoft.Azure.WebJobs.Host;
using Microsoft.Azure.EventHubs;
using System.Text;
using System.Net.Http;
using Microsoft.Azure.Cosmos.Table;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using System.Collections.Generic;
using System;
using System.Globalization;

namespace cosmosDBsendAll
{
    public static class sendToTable
    {
        private static HttpClient client = new HttpClient();

        [FunctionName("sendToTable")]
        [return: Table("LSMeasurements")]
        public static LSMeasurements Run(
            [IoTHubTrigger("messages/events", 
            Connection = "connectionString", 
            ConsumerGroup = "sendtotables")]
            EventData message, ILogger log)
        {

            var _data = JsonConvert.DeserializeObject<Dictionary<string, dynamic>>(Encoding.UTF8.GetString(message.Body.Array));
            if(message.SystemProperties["iothub-connection-device-id"].ToString() == "Esp32LightSens")
            {

                LSMeasurements kaffeTable = new LSMeasurements();

                kaffeTable.PartitionKey = "LSMeasurements";
                kaffeTable.DeviceId = message.SystemProperties["iothub-connection-device-id"].ToString();
                kaffeTable.RowKey = Guid.NewGuid().ToString();
                kaffeTable.SchoolName = message.Properties["schoolName"].ToString();
                kaffeTable.PersonName = message.Properties["personName"].ToString();
                kaffeTable.LightSens = _data["LS"];
                //kaffeTable.Temperature = _data["Temp"];
                //kaffeTable.Humidity = _data["Hum"];
                kaffeTable.TimestampThing = _data["EpochT"];

                kaffeTable.Latitude = double.Parse(message.Properties["lat"].ToString(), CultureInfo.InvariantCulture);
                kaffeTable.Longitude = double.Parse(message.Properties["lon"].ToString(), CultureInfo.InvariantCulture);


                log.LogInformation($"TABLES!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: {Encoding.UTF8.GetString(message.Body.Array)}");

                return kaffeTable;
            }
            return null;
          
        }
    }
}