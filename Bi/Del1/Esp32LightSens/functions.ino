

//------------------------------//
void epochTimeInit()
{
  configTime(3600, 0, "pool.ntp.org", "time.nist.gov");

  while (true)
  {
    epochTime = time(NULL);

    if (epochTime == 28800) 
    {
      delay(2000);
    }
    else
    {
      break;
    }
  }
}

//------------------------------//
void makeTheUltimateJsonAndSend (float currentLS)
{

      if(!(std::isnan(currentLS)))
      {
        if (currentLS > (previousLS + LSDiff) || currentLS < (previousLS - LSDiff))
         {
          char postNord[MESSAGE_LEN_MAX];
      
          DynamicJsonDocument doc(sizeof(postNord));

          doc["LS"] = currentLS;
          doc["EpochT"] = epochTime;
     
          serializeJson(doc, postNord);
          if(!messagePending)
          {
            sendMessage(postNord,currentLS);
          }
         }
      }
    delay(INTERVAL);
  
  Esp32MQTTClient_Check();
  delay(10);
}

//------------------------------//
void sendMessage (char* postNord, int currentLS)
{
  
  messagePending = true;
  EVENT_INSTANCE *message = Esp32MQTTClient_Event_Generate(postNord, MESSAGE);
    
    Esp32MQTTClient_Event_AddProp(message, "deviceType", "LightS");
    Esp32MQTTClient_Event_AddProp(message, "personName", personName);
    Esp32MQTTClient_Event_AddProp(message, "schoolName", schoolName);
    Esp32MQTTClient_Event_AddProp(message, "lon",String(lon).c_str());
    Esp32MQTTClient_Event_AddProp(message, "lat",String(lat).c_str());

    if (Esp32MQTTClient_SendEventInstance(message) )
    {
      Serial.println("Sent");
      Serial.println(postNord);
      messagePending = false;
    }

    previousLS = currentLS;

   
}
