using Microsoft.Azure.Cosmos.Table;
using System;
using System.Collections.Generic;
using System.Text;
namespace cosmosDBsendAll
{
    public class Measurements : TableEntity
    {
        public string DeviceId { get; set; }
        public string DeviceType { get; set; }
        public string SchoolName { get; set; }
        public string PersonName { get; set; }
        public double Temperature { get; set; }
        public string Name { get; set; }
        public double Humidity { get; set; }
        public Int64 LightSens { get; set; }
        public long TimestampThing { get; set; }
        public double Longitude { get; set; }
        public double Latitude { get; set; }
        

    }

    public class LSMeasurements : TableEntity
    {
        public string DeviceId { get; set; }
        public string DeviceType { get; set; }
        public string SchoolName { get; set; }
        public string PersonName { get; set; }
        public string Name { get; set; }
        public Int64 LightSens { get; set; }
        public long TimestampThing { get; set; }
        public double Longitude { get; set; }
        public double Latitude { get; set; }


    }
}
