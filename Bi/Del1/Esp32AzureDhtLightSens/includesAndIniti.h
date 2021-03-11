//--------------Personal Device things--------------//

#define DEVICE_ID "BestRamenJoint"
#define schoolName "Nackademin"
#define personName "Vincent"


// Bibliotek och definieringar
#include <Adafruit_Sensor.h>
#include <AzureIotHub.h>
#include <DHT.h>
#include <WiFi.h>
#include <Esp32MQTTClient.h>
#include <ArduinoJson.h>
#include <WifiLocation.h>
//------------------------------//
#define DHT_PIN 8
#define DHT_TYPE DHT11
#define tempDiff 1
#define INTERVAL 6000
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
char* connectionString = "HostName=NeopixelHub.azure-devices.net;DeviceId=esp32Temp;SharedAccessKey=nEh5AZ6o+fnFPqiEPrLNTd8iGWx8QQS9FE5jR/nnbx4=";//den speciella sträng för förbindelse med azure device// <----------------------------------------------------
static bool _connected = false;
static bool _messagePending =false;



//------------ GPS -------------//

const char* googleAPI = "AIzaSyDyX7T8deydCbU1KrCIPmQBbnosRg2U4wU";

double lat = 35.67127; //sätter en fast location för devicen
double  lon = 139.76143;
double accuracy = 0.0;
WifiLocation location(googleAPI);
//------------------------------//
