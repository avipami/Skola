package com.neopixels.Vecka2.v2_Uppg1;

public class avgCalculator
{
    private double todaysTotalMilage;
    private double oneYearAgoMilage;
    private double usedGas;

    public avgCalculator(double todaysTotalMilage, double oneYearAgoMilage, double usedGas) {
        this.todaysTotalMilage = todaysTotalMilage;
        this.oneYearAgoMilage = oneYearAgoMilage;
        this.usedGas = usedGas;
    }

    public double getTodaysTotalMilage() {
        return todaysTotalMilage;
    }

    public void setTodaysTotalMilage(double todaysTotalMilage) {
        this.todaysTotalMilage = todaysTotalMilage;
    }

    public double getOneYearAgoMilage() {
        return oneYearAgoMilage;
    }

    public void setOneYearAgoMilage(double oneYearAgoMilage) {
        this.oneYearAgoMilage = oneYearAgoMilage;
    }

    public double getUsedGas() {
        return usedGas;
    }

    public void setUsedGas(double usedGas) {
        this.usedGas = usedGas;
    }

    public void PrintAvg()
    {
        var drivenMilage = this.todaysTotalMilage - this.oneYearAgoMilage ;
        var avgConsumption = drivenMilage / this.usedGas ;
        System.out.println(avgConsumption);
    }


}
