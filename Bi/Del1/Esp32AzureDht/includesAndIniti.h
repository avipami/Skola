//--------------Personal Device things--------------//

#define DEVICE_ID "BestRamenJoint"
#define schoolName "ChinaCrap"
#define personName "Neopix"


// Bibliotek och definieringar
#include <Adafruit_Sensor.h>
#include <AzureIotHub.h>
#include <DHT.h>
#include <WiFi.h>
#include <Esp32MQTTClient.h>
#include <ArduinoJson.h>
#include <WifiLocation.h>
//------------------------------//
#define DHT_PIN 22
#define DHT_TYPE DHT11
#define tempDiff 0.1
#define INTERVAL 10000
#define MESSAGE_LEN_MAX 512
#define photoSens 34

//------------------------------//

float previousTemp;
time_t epochTime;
unsigned long previousMillis = 0;

bool messagePending = false;

DHT dht(DHT_PIN, DHT_TYPE);//skapar en instans av DHT med namd dht och sätter in pin och typ av sensor



//------------WIFI--------------//
char* ssid = "Gurka";//wifi namn// <----------------------------------------------------
char* pass = "gintonic";//löösen// <----------------------------------------------------
char* connectionString = "HostName=NeopixelHub.azure-devices.net;DeviceId=3C:61:05:12:74:2C;SharedAccessKey=v3oUYPw83zsdmtsWM/S5gMAYo/AGP4iZkXkQZkUJf6g=";//den speciella sträng för förbindelse med azure device// <----------------------------------------------------
static bool _connected = false;
static bool _messagePending =false;



//------------ GPS -------------//

const char* googleAPI = "AIzaSyDyX7T8deydCbU1KrCIPmQBbnosRg2U4wU";

double lat = 35.67127; //sätter en fast location för devicen
double  lon = 139.76143;
double accuracy = 0.0;
WifiLocation location(googleAPI);
//------------------------------//
