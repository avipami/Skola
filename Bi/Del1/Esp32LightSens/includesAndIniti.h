//--------------Personal Device things--------------//

#define DEVICE_ID "LightSens"
#define schoolName "Nackademin"
#define personName "Vincent"


// Bibliotek och definieringar
#include <Adafruit_Sensor.h>
#include <AzureIotHub.h>
#include <WiFi.h>
#include <Esp32MQTTClient.h>
#include <ArduinoJson.h>
#include <WifiLocation.h>
//------------------------------//

#define LSDiff 10
#define INTERVAL 6000
#define MESSAGE_LEN_MAX 512
#define photoSens 34
//------------------------------//

float previousLS;
time_t epochTime;
unsigned long previousMillis = 0;

bool messagePending = false;



//------------WIFI--------------//
char* ssid = "Gurka";//wifi namn// <----------------------------------------------------
char* pass = "gintonic";//löösen// <----------------------------------------------------
char* connectionString = "HostName=NeopixelHub.azure-devices.net;DeviceId=Esp32LightSens;SharedAccessKey=mrAEdGAGxFQ6J+WS3b80rikNzall4KPPIu+3FQMqY3M=";//den speciella sträng för förbindelse med azure device// <----------------------------------------------------
static bool _connected = false;
static bool _messagePending =false;



//------------ GPS -------------//

const char* googleAPI = "AIzaSyDyX7T8deydCbU1KrCIPmQBbnosRg2U4wU";

double lat = 35.67127; //sätter en fast location för devicen
double  lon = 139.76143;
double accuracy = 0.0;
WifiLocation location(googleAPI);
//------------------------------//
