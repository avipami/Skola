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

namespace cosmosDBsendAll
{
    public static class GetFromTable
    {
        [FunctionName("GetFromTable")]
        public static async Task<IActionResult> Run(
            [HttpTrigger(AuthorizationLevel.Anonymous, "get", Route = null)] HttpRequest req,
            [Table("Measurements")]CloudTable table,
            ILogger log)
        {
            IEnumerable<Measurements> results = await table.ExecuteQuerySegmentedAsync(new TableQuery<Measurements>(), null);
            return new OkObjectResult(results);
        }
    }
}
