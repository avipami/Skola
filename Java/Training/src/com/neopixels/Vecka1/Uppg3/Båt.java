package com.neopixels.Vecka1.Uppg3;

public class Båt  extends NeoFordon
{
    private int dödvikt;

    public void sväng(int grader,String håll)
    {
        String barbord = "barbord";
        String styrbord = "styrbord";

        if(håll.toLowerCase() == "styrbord")
        {
            System.out.println(styrbord + " : "+grader+" grader");

        }
        else if(håll.toLowerCase() == "barbord")
        {
            System.out.println(barbord + " : "+grader+" grader");
        }

    }

    public Båt(int vikt,int hastighet,int dödvikt)
    {
        super(vikt,hastighet);
        this.dödvikt= dödvikt;
    }
    @Override
    public void printMe() {
        System.out.println("Hastighet : "+this.hastighet +", Vikt : "+ this.vikt +", Dödvikt : "
                +this.dödvikt);
    }

    @Override
    public int getAntalHjul() {
        return 0;
    }
}
