package com.neopixels.Vecka1.Uppg8;

public class PERSON {

    private String _Name;
    private String _StreetAddress;
    private String _Address;
    private String _Weight;
    private String _Height;
    private String _age;

    public PERSON(){}

    public PERSON(String _Name, String _StreetAddress, String _Address, String _Weight, String _Height, String _age) {
        this._Name = _Name;
        this._StreetAddress = _StreetAddress;
        this._Address = _Address;
        this._Weight = _Weight;
        this._Height = _Height;
        this._age = _age;
    }

    public String get_Name() {
        return _Name;
    }

    public void set_Name(String _Name) {
        this._Name = _Name;
    }

    public String get_StreetAddress() {
        return _StreetAddress;
    }

    public void set_StreetAddress(String _StreetAddress) {
        this._StreetAddress = _StreetAddress;
    }

    public String get_Address() {
        return _Address;
    }

    public void set_Address(String _Address) {
        this._Address = _Address;
    }

    public String get_Weight() {
        return _Weight;
    }

    public void set_Weight(String _Weight) {
        this._Weight = _Weight;
    }

    public String get_Height() {
        return _Height;
    }

    public void set_Height(String _Height) {
        this._Height = _Height;
    }

    public String get_age() {
        return _age;
    }

    public void set_age(String _age) {
        this._age = _age;
    }
}
