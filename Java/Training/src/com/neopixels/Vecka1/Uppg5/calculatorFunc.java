package com.neopixels.Vecka1.Uppg5;

import java.util.List;
import java.lang.String;

public class calculatorFunc {
    public static String calculate(List<String> calcList)
    {
        System.out.println("I CALCULATORN");
        float a = Float.parseFloat(calcList.get(0));
        String operator = calcList.get(1);
        float b = Float.parseFloat(calcList.get(2));

        switch (operator)
        {
            case "+":
                return String.valueOf(a+b);

            case "-":
                return String.valueOf(a-b);

            case "*":
                return String.valueOf(a*b);

            case "/":
                return String.valueOf(a/b);

            default:
                return "NOT POSSIBLE";


        }

    }
}
