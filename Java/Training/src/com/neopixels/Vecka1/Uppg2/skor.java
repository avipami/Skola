package com.neopixels.Vecka1.Uppg2;

public class skor {
    private int size;
    private String Type;
    private String color;

    public skor(int size, String Type, String color) {
        this.size = size;
        this.Type = Type;
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getColor() {
        return color;
    }

    public void setTotalPairs(String color) {
        this.color = color;
    }

    public String printMaShoe ()
    {
        return "Brand : "+ this.getType()+", Size : "+ this.getSize()+", Pairs : "+ this.getColor();
    }
}
