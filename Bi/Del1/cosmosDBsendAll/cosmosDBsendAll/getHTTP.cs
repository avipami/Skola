using System;
using System.IO;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Azure.WebJobs;
using Microsoft.Azure.WebJobs.Extensions.Http;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using System.Collections.Generic;

namespace cosmosDBsendAll
{
    public static class getHTTP
    {
        [FunctionName("getHTTP")]
        public static async Task<IActionResult> Run(
            [HttpTrigger(AuthorizationLevel.Anonymous, "get",  Route = null)] HttpRequest req, 
            [CosmosDB(
            databaseName: "neopixelcosmosdb",
            collectionName: "Messages",
            ConnectionStringSetting = "CosmosDB",
            
            SqlQuery = "SELECT *FROM c WHERE c.DeviceId = 'esp32Temp' ORDER BY c._ts DESC OFFSET 0 LIMIT 10"
            )]IEnumerable<dynamic>cosmos,
          ILogger log)
        {
            
            
            log.LogInformation("C# HTTP trigger function processed a request.");
            return new OkObjectResult(cosmos);
        }
    }

}
