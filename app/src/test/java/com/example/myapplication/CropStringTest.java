package com.example.myapplication;


import org.junit.Assert;
import org.junit.Test;

public class CropStringTest {

    private String s;
    private int n;
    @Test
    public void testCalculate() {
        this.s = "Example Text";
        this.n = 7;
        try {
            CropString c = new CropString(n, s);
            Assert.assertEquals(c.calculate(), "Example");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testCalculateException() {
        this.s = "Example Text";
        this.n = s.length() + 1;
        try {
            CropString c = new CropString(n, s);
            String result = c.calculate();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertSame("The number should be smaller than the input string.", e.getMessage());
        }
    }
}