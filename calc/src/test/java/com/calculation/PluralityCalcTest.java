package com.calculation;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class PluralityCalcTest 
{
    @Test
    public void testCalculate0()
    {
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {};
        String[] output = {"Tie between Jim and Pam", "Jim got 0 votes.\nPam got 0 votes.\n"};
        assertArrayEquals(output, PluralityCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate1()
    {
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {1}, {1}, {1}, {1}, {1}, {1}, {1}, {1}};
        String[] output = {"Pam", "Jim got 8 votes.\nPam got 9 votes.\n"};
        assertArrayEquals(output, PluralityCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate2()
    {
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {{0}, {0}, {1}, {1}};
        String[] output = {"Tie between Jim and Pam", "Jim got 2 votes.\nPam got 2 votes.\n"};
        assertArrayEquals(output, PluralityCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate3()
    {
        String[] choices = {"Jim", "Pam", "Michael"};
        int[][] votes = {{0}, {0}, {1}, {1}, {2}};
        String[] output = {"Tie between Jim and Pam", "Jim got 2 votes.\nPam got 2 votes.\nMichael got 1 vote.\n"};
        assertArrayEquals(output, PluralityCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate4()
    {
        String[] choices = {"Jim", "Pam", "Michael"};
        int[][] votes = {{0}, {0}, {1}, {1}, {2}, {2}};
        String[] output = {"Tie between Jim, Pam and Michael", "Jim got 2 votes.\nPam got 2 votes.\nMichael got 2 votes.\n"};
        assertArrayEquals(output, PluralityCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate5()
    {
        String[] choices = {"Jim", "Pam", "Michael"};
        int[][] votes = {{2}, {0}, {0}, {2}, {1}, {1}, {2}, {0}, {1}, {2}, {0}, {1}, {2}, {2}, {1}, {0}};
        String[] output = {"Michael", "Jim got 5 votes.\nPam got 5 votes.\nMichael got 6 votes.\n"};
        assertArrayEquals(output, PluralityCalc.calculate(choices, votes));
    }
}
