using System;
using System.IO;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Azure.WebJobs;
using Microsoft.Azure.WebJobs.Extensions.Http;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using Microsoft.Azure.Cosmos.Table;
using System.Collections.Generic;
using System.Linq;


namespace cosmosDBsendAll
{

    public static class GetFromTable
    {
        [FunctionName("GetFromTable")]
        public static async Task<IActionResult> Run(
            [HttpTrigger(AuthorizationLevel.Anonymous, "get", Route = null)] HttpRequest req,
            [Table("LSMeasurements")]CloudTable table,
            ILogger log)
        {
            string limit = req.Query["limit"];
            string orderby = req.Query["orderby"];

            IEnumerable<Measurements> results = 
                await table.ExecuteQuerySegmentedAsync(new TableQuery<Measurements>(), null);

            if(orderby == "desc")
            {
                results = results.OrderByDescending(ts => ts.TimestampThing);
            }
           
            if(limit != null)
            {
                results = results.Take(Int32.Parse(limit));
            }

            return results != null
                ? (ActionResult) new OkObjectResult(results)
                : new BadRequestObjectResult("[]") ;
        }
    }
}
