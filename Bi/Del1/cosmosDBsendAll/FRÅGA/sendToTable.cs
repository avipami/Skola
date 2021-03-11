using IoTHubTrigger = Microsoft.Azure.WebJobs.EventHubTriggerAttribute;

using Microsoft.Azure.WebJobs;
using Microsoft.Azure.WebJobs.Host;
using Microsoft.Azure.EventHubs;
using System.Text;
using System.Net.Http;
using Microsoft.Azure.Cosmos.Table;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;


namespace cosmosDBsendAll
{
    public static class sendToTable
    {
        private static HttpClient client = new HttpClient();

        [FunctionName("sendToTable")]
        [return: Table("Measurements")]
        public static void Run([IoTHubTrigger("messages/events", 
            Connection = "CosmosDB", 
            ConsumerGroup = "table")]
            EventData message, ILogger log)
        {
            var msg = JsonConvert.DeserializeObject()
            log.LogInformation($"C# IoT Hub trigger function processed a message: {Encoding.UTF8.GetString(message.Body.Array)}");
        }
    }
}