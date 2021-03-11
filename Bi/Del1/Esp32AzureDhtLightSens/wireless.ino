void initWiFi()
{
  WiFi.begin(ssid, pass); // drar igång kopplingen mellan datorn o vindkraftverken
  while (WiFi.status() != WL_CONNECTED)
  {
    delay(1000);
    Serial.print(".");
  }


}

void gpsINIT()
{

  location_t loc = location.getGeoFromWiFi();
  //lat = loc.lat;
  //lon = loc.lon;
  Serial.print("\nIP Address: ");
  Serial.println(WiFi.localIP());
  Serial.print("Wifi Mac Address : ");
  Serial.println(WiFi.macAddress());

  Serial.println("Location request data");
  Serial.println(location.getSurroundingWiFiJson());

  //Serial.print("Current Location : ");
  //Serial.println("Latitude: " + String(loc.lat, 7));
  //Serial.println("Longitude: " + String(loc.lon, 7));
  //Serial.println("Accuracy: " + String(loc.accuracy));

}
