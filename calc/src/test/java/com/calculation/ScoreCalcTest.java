package com.calculation;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class ScoreCalcTest 
{
    @Test
    public void testCalculate0()
    {
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {};
        String[] output = {"Tie between Jim and Pam", "Jim received a score of 0.\nPam received a score of 0.\n"};
        assertArrayEquals(output, ScoreCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate1()
    {
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {{0, 5}, {0, 5}, {0, 5}, {0, 5}, {0, 5}, {0, 5}, {0, 5}, {0, 5}, {5, 0}, {5, 0}, {5, 0}, {5, 0}, {5, 0}, {5, 0}, {5, 0}, {5, 0}, {5, 0}};
        String[] output = {"Jim", "Jim received a score of 45.\nPam received a score of 40.\n"};
        assertArrayEquals(output, ScoreCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate2()
    {
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {{4, 3}, {2, 5}, {1, 4}, {5, 0}};
        String[] output = {"Tie between Jim and Pam", "Jim received a score of 12.\nPam received a score of 12.\n"};
        assertArrayEquals(output, ScoreCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate3()
    {
        String[] choices = {"Jim", "Pam", "Michael"};
        int[][] votes = {{4, 5, 2}, {5, 4, 1}, {5, 5, 0}, {4, 2, 1}, {2, 4, 1}};
        String[] output = {"Tie between Jim and Pam", "Jim received a score of 20.\nPam received a score of 20.\nMichael received a score of 5.\n"};
        assertArrayEquals(output, ScoreCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate4()
    {
        String[] choices = {"Jim", "Pam", "Michael"};
        int[][] votes = {{4, 5, 5}, {5, 4, 5}, {5, 5, 0}, {4, 2, 5}, {2, 4, 5}};
        String[] output = {"Tie between Jim, Pam and Michael", "Jim received a score of 20.\nPam received a score of 20.\nMichael received a score of 20.\n"};
        assertArrayEquals(output, ScoreCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate5()
    {
        String[] choices = {"Jim", "Pam", "Michael","Kevin"};
        int[][] votes = {{4, 5, 5, 0}, {5, 4, 5, 0}, {5, 5, 0, 0}, {4, 2, 5, 0}, {2, 4, 5, 0}, {0, 0, 0, 5}, {0, 0, 0, 5}, {0, 0, 0, 5}, {0, 0, 0, 5}, {0, 0, 0, 5}, {0, 0, 0, 5}, {0, 0, 0, 5}, {0, 0, 0, 5}, {0, 0, 0, 5}, {0, 0, 0, 5}, {0, 0, 0, 5}, {0, 0, 0, 5}, {0, 0, 0, 5}, {0, 0, 0, 5}, {0, 0, 0, 5}, {0, 0, 0, 5}, {0, 0, 0, 5}, {0, 0, 0, 5}, {0, 0, 0, 5}, {0, 0, 0, 5}};
        String[] output = {"Kevin", "Jim received a score of 20.\nPam received a score of 20.\nMichael received a score of 20.\nKevin received a score of 100.\n"};
        assertArrayEquals(output, ScoreCalc.calculate(choices, votes));
    }
}
