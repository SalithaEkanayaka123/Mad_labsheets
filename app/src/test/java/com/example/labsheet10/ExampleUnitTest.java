package com.example.labsheet10;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    private MainActivity mainActivity;
    @Before
    public void setUp(){
        mainActivity = new MainActivity();
    }
    @Test
    public void convertingCelciusSuccess(){
        float value =  100;
        float result = mainActivity.convertToCelsius(value);
        assertEquals(37.7778,result,0.001);
    }
    @Test
    public void convertingFarenheitSuccess(){
        float value =  100;
        float result = mainActivity.convertToFarenheit(value);
        assertEquals(212.0,result,0.001);
    }
}