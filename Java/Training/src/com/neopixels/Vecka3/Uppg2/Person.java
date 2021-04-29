package com.neopixels.Vecka3.Uppg2;

public class Person {

    private String name,phoneNumber,address;

    public Person(String name, String telephoneNumber, String address)
    {
        this.name = name;
        this.phoneNumber = telephoneNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
