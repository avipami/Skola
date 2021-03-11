using System;
using System.Collections.Generic;
using Microsoft.Azure.Documents;
using Microsoft.Azure.WebJobs;
using Microsoft.Azure.WebJobs.Host;
using Microsoft.Extensions.Logging;

namespace CosmosDBSendGet
{
    public static class sendToCosmos
    {
        [FunctionName("sendToCosmos")]
        public static void Run([IoTHubTrigger("messages/events", Connection = "IotHubConnection", ConsumerGroup = "visualstudio")] EventData message,
            [CosmosDB(
            databaseName: "neopixelcosmosdb",
            collectionName: "Messages",
            ConnectionStringSetting = "CosmosDBConnection",
            CreateIfNotExists = true
            )] out dynamic cosmos,
            ILogger log
            )
        {
            log.LogInformation($"messages/events: {Encoding.UTF8.GetString(message.Body.Array)}");


            cosmos = Encoding.UTF8.GetString(message.Body.Array);
        }
    }
}
