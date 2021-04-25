#include "includesAndIniti.h"

void setup()
{
  Serial.begin(115200); // initiering för seriell monitor
  initWiFi();

  initIotHub();
  epochTimeInit();
  dht.begin(); // initierar dht sensor
  //gpsINIT();
  delay(2000);
}

void loop()
{
  epochTime = time(NULL);
 

  makeTheUltimateJsonAndSend ();
  delay(INTERVAL);
}
