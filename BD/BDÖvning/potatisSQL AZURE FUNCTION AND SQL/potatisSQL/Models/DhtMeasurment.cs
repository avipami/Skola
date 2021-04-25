using System;
using System.Collections.Generic;
using System.Text;

namespace potatisSQL.Models
{

    public class DhtMeasurement
    {
        public float Temp { get; set; }
        public float Hum { get; set; }
        public int EpochT { get; set; }
        public int TempAlert { get; set; }
    }



}
