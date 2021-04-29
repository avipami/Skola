package com.neopixels.Vecka3.EgenUppgiftObject;

import com.neopixels.ListNetworkInterface;

public class MyCalculation extends WorkRequest{

int n;
public MyCalculation(int n)
    {
        this.n=n;
    }

    @Override
    public Object execute() {
        return new Integer(this.n*this.n);
    }
}
