package com.neopixels.Vecka1.Uppg3;

public class Tåg extends NeoFordon
{
    private int antalVagnar;

    public void kopplaVagn(){ }

    public Tåg(int vikt,int hastighet, int antalVagnar)
    {
        super(vikt,hastighet);
        this.antalVagnar=antalVagnar;
    }

    @Override
    public void printMe() {
        System.out.println("Hastighet : "+this.hastighet +", Vikt : "+ this.vikt +", Antal Vagnar : "
                +this.antalVagnar);
    }

    @Override
    public int getAntalHjul() {
        return 666;
    }
}
