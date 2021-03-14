using IoTHubTrigger = Microsoft.Azure.WebJobs.EventHubTriggerAttribute;

using Microsoft.Azure.WebJobs;
using Microsoft.Azure.WebJobs.Host;
using Microsoft.Azure.EventHubs;
using System.Text;
using System.Net.Http;
using Newtonsoft.Json;
using Microsoft.Extensions.Logging;
using System.Collections.Generic;

namespace cosmosDBsendAll
{
    public static class sendAlltoCosmosDB
    {
        private static HttpClient client = new HttpClient();

        [FunctionName("sendAlltoCosmosDB")]
        public static void Run([IoTHubTrigger("messages/events", Connection = "connectionString", ConsumerGroup = "sendToCosmos")]EventData message, 
            [CosmosDB(
            databaseName: "neopixelcosmosdb",
            collectionName: "Messages",
            ConnectionStringSetting = "CosmosDB",
            CreateIfNotExists = true
            )] out dynamic cosmos, ILogger log)
        {
            log.LogInformation($"messages/events:  {Encoding.UTF8.GetString(message.Body.Array)}");
            //var msg = JsonConvert.DeserializeObject<Measurements>(Encoding.UTF8.GetString(message.Body.Array));
            var msg = JsonConvert.DeserializeObject<Dictionary<string, dynamic>>(Encoding.UTF8.GetString(message.Body.Array));
            
            msg.Add("DeviceId",(message.SystemProperties["iothub-connection-device-id"]));
            msg.Add("deviceType",(message.Properties["deviceType"]));
            msg.Add("personName",( message.Properties["personName"]));
            msg.Add("schoolName", (message.Properties["schoolName"]));
            msg.Add("Longitude", (message.Properties["lon"]));
            msg.Add("Latitude", (message.Properties["lat"]));
            

            var json = JsonConvert.SerializeObject(msg);
            
            log.LogInformation(json);

            cosmos = json;
            //cosmos = Encoding.UTF8.GetString(message.Body.Array);
        }
    }
}