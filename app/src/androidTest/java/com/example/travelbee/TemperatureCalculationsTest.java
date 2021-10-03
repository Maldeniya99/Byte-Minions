package com.example.travelbee;

import static org.junit.Assert.*;

import org.junit.Test;

public class TemperatureCalculationsTest {

    @Test
    public void convertCelciusToFahrenheit() {
        float input = 100;
        float output;
        float expected = 212;
        double delta = .1;

        Calculations cal = new Calculations();
        output = cal.convertCelciusToFahrenheit(input);

        assertEquals(expected, output, delta);
    }

    @Test
    public void convertFahrenheitToCelcius() {
        float input = 212;
        float output;
        float expected = 100;
        double delta = .1;

        Calculations cal = new Calculations();
        output = cal.convertFahrenheitToCelcius(input);

        assertEquals(expected, output, delta);
    }
}