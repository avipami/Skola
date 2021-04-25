package com.neopixels.Vecka1.Uppg3;

public abstract class NeoFordon implements Printable, Hjulburen
{
    protected int vikt;
    protected int hastighet;

    public NeoFordon(int vikt, int hastighet)
    {
        this.vikt = vikt;
        this.hastighet = hastighet;
    }
    public NeoFordon(int vikt)
    {
        this.vikt = vikt;
    }

    public void ändraHastighet(){}


}
