using Microsoft.Azure.Cosmos.Table;
using System;
using System.Collections.Generic;
using System.Text;

namespace cosmosDBsendAll
{
    class Measurements : TableEntity
    {
        public string DeviceId { get; set; }
        public string DeviceType { get; set; }
        public float Temperature { get; set; }
        public string Name { get; set; }
        public float Humidity { get; set; }
        public long TimestampThing { get; set; }
    }
}
