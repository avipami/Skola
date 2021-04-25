package com.neopixels.Vecka1.Uppg3;

public class Cykel extends NeoFordon
{
    private int antalVäxlar;
    private int växelJustNu;

    public void växla (int inputVäxel){
        växelJustNu = inputVäxel;
    }

    public Cykel (int vikt,int hastighet, int antalVäxlar, int växelJustNu)
    {
        super(vikt,hastighet);
        this.antalVäxlar = antalVäxlar;
        this.växelJustNu=växelJustNu;
    }
    public Cykel(int vikt,int antalVäxlar,int växelJustNu)
    {
        super(vikt);
        this.antalVäxlar = antalVäxlar;
        this.växelJustNu = växelJustNu;
    }

    @Override
    public void printMe() {
        System.out.println("Hastighet : "+this.hastighet +", Vikt : "+ this.vikt +", AntalVäxlar : "
                +this.antalVäxlar+", Växel just nu : "+this.växelJustNu);
    }

    @Override
    public int getAntalHjul() {
        return 7;
    }
}
