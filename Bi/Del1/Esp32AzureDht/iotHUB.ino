void initIotHub()
{
  if (!Esp32MQTTClient_Init((const uint8_t *) connectionString))//kollar om länken till azure med nyckenl är godkänd
  {
    _connected = false;
    return;
  }
  else
  {
    _connected = true;
  }
}

void SendConfirmationCallback(IOTHUB_CLIENT_CONFIRMATION_RESULT result)
{
  if(result == IOTHUB_CLIENT_CONFIRMATION_OK)
  {
    Serial.println("Confirmed");
    messagePending = false;
  }
}
