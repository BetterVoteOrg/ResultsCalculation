package com.calculation;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class StarCalcTest 
{
    @Test
    public void testCalculate0()
    {
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {};
        String[] output = {"Tie between Jim and Pam", "Jim received a score of 0.\nPam received a score of 0.\nIn the runoff between Jim and Pam, Jim received 0 votes and Pam received 0 votes.\n"};
        assertArrayEquals(output, StarCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate1()
    {
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {{0, 5}, {0, 5}, {0, 5}, {0, 5}, {0, 5}, {0, 5}, {0, 5}, {0, 5}, {5, 0}, {5, 0}, {5, 0}, {5, 0}, {5, 0}, {5, 0}, {5, 0}, {5, 0}, {5, 0}};
        String[] output = {"Jim", "Jim received a score of 45.\nPam received a score of 40.\nIn the runoff between Jim and Pam, Jim received 9 votes and Pam received 8 votes.\n"};
        assertArrayEquals(output, StarCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate2()
    {
        String[] choices = {"Jim", "Pam", "Michael"};
        int[][] votes = {{3, 3, 1}, {3, 3, 1}, {3, 3, 1}, {3, 3, 1}, {3, 3, 1}, {2, 2, 2}, {2, 2, 2}, {2, 2, 2}, {5, 4, 0}, {4, 5, 1}};
        String[] output = {"Tie between Jim and Pam", "Jim received a score of 30.\nPam received a score of 30.\nMichael received a score of 12.\nIn the runoff between Jim and Pam, Jim received 1 vote and Pam received 1 vote.\n"};
        assertArrayEquals(output, StarCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate3()
    {
        String[] choices = {"Jim", "Pam", "Michael"};
        int[][] votes = {{3, 3, 1}, {3, 3, 1}, {3, 3, 1}, {3, 3, 1}, {3, 3, 1}, {2, 2, 2}, {2, 2, 2}, {2, 2, 2}, {5, 4, 0}, {4, 5, 1}, {4, 0, 0}, {0, 2, 0}, {0, 2, 0}};
        String[] output = {"Pam", "Jim received a score of 34.\nPam received a score of 34.\nMichael received a score of 12.\nIn the runoff between Jim and Pam, Jim received 2 votes and Pam received 3 votes.\n"};
        assertArrayEquals(output, StarCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate4()
    {
        String[] choices = {"Jim", "Pam", "Michael", "Kevin"};
        int[][] votes = {{3, 3, 1, 2}, {3, 3, 1, 2}, {3, 3, 1, 2}, {3, 3, 1, 2}, {3, 3, 1, 2}, {2, 2, 2, 1}, {2, 2, 2, 1}, {2, 2, 2, 1}, {5, 4, 0, 3}, {4, 5, 1, 3}, {4, 0, 0, 5}, {0, 2, 0, 5}, {0, 2, 0, 5}};
        String[] output = {"Pam", "Jim received a score of 34.\nPam received a score of 34.\nMichael received a score of 12.\nKevin received a score of 34.\nTo break a tie for first place, an elimination round was held where Jim scored lowest on 2 ballot(s), Pam scored lowest on 1 ballot(s) and Kevin scored lowest on 10 ballot(s). Therefore Kevin was eliminated from consideration in the runoff.\nIn the runoff between Jim and Pam, Jim received 2 votes and Pam received 3 votes.\n"};
        assertArrayEquals(output, StarCalc.calculate(choices, votes));
    }
}
