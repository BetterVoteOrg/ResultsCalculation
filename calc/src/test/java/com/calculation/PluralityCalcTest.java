package com.calculation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PluralityCalcTest 
{
    @Test
    public void testCalculate()
    {
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {1}, {1}, {1}, {1}, {1}, {1}, {1}, {1}};
        String[] output = {"Pam", "Jim got 8 votes.\nPam got 9 votes.\n"};
        assertArrayEquals(output, PluralityCalc.calculate(choices, votes));
    }

    @Test
    public void testReturnSeven()
    {
        assertEquals(7, PluralityCalc.returnSeven());
    }
}
