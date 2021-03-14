#include "includesAndIniti.h"

void setup() 
{
  Serial.begin(115200); // initiering för seriell monitor
  initWiFi();
  
  initIotHub();
  epochTimeInit();
  gpsINIT();
  delay(2000);
}

void loop() 
{
  epochTime = time(NULL);
  int currentLS = map(analogRead(photoSens), 0, 4095, 100, 0);
  
  makeTheUltimateJsonAndSend (currentLS);
}
