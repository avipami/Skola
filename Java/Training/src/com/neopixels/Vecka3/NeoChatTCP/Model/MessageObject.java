package com.neopixels.Vecka3.NeoChatTCP.Model;

public class MessageObject extends Request
{
    private String message;
    public MessageObject(String message)
        {
            this.message = message;
        }

        public String getMessage()
        {
            return this.message;
        }
}
