package com.calculation;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class RcvCalcTest 
{
    @Test
    public void testCalculate0()
    {
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {};
        String[] output = {"A winner could not be found", "In round 1, Jim received 0 first choice votes and Pam received 0 first choice votes.\nSince all choices have been eliminated, a winner could not be found.\n"};
        assertArrayEquals(output, RcvCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate1()
    {
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {{2, 1}, {2, 1}, {2, 1}, {2, 1}, {2, 1}, {2, 1}, {2, 1}, {2, 1}, {1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}};
        String[] output = {"Jim", "In round 1, Jim received 9 first choice votes and Pam received 8 first choice votes.\nSince Jim received over 50% of first choice votes, Jim is the winner.\n"};
        assertArrayEquals(output, RcvCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate2()
    {
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {{2, 1}, {2, 1}, {2, 1}, {0, 1}, {2, 1}, {0, 1}, {2, 1}, {2, 1}, {1, 2}, {1, 2}, {1, 0}, {1, 0}, {1, 0}, {1, 0}, {1, 0}, {1, 2}, {1, 2}};
        String[] output = {"Jim", "In round 1, Jim received 9 first choice votes and Pam received 8 first choice votes.\nSince Jim received over 50% of first choice votes, Jim is the winner.\n"};
        assertArrayEquals(output, RcvCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate3()
    {
        String[] choices = {"Jim", "Pam", "Michael"};
        int[][] votes = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}, {1, 2, 3}, {2, 1, 3}, {2, 1, 3}, {2, 1, 3}, {2, 1, 3}, {2, 3, 1}, {3, 2, 1}};
        String[] output = {"A winner could not be found", "In round 1, Jim received 4 first choice votes, Pam received 4 first choice votes and Michael received 2 first choice votes.\nSince no choice has received over 50% of first choice votes, Michael was eliminated from consideration and their votes were redistributed to their voters' next ranked choice that has not been eliminated because they received the fewest first choice votes.\nIn round 2, Jim received 5 first choice votes, Pam received 5 first choice votes and Michael received 0 first choice votes.\nSince no choice has received over 50% of first choice votes, Jim and Pam were eliminated from consideration and their votes were redistributed to their voters' next ranked choice that has not been eliminated because they received the fewest first choice votes.\nIn round 3, Jim received 0 first choice votes, Pam received 0 first choice votes and Michael received 0 first choice votes.\nSince all choices have been eliminated, a winner could not be found.\n"};
        assertArrayEquals(output, RcvCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate4()
    {
        String[] choices = {"Jim", "Pam", "Michael"};
        int[][] votes = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}, {1, 2, 3}, {1, 2, 3}, {2, 1, 3}, {2, 1, 3}, {2, 1, 3}, {2, 3, 1}, {3, 2, 1}};
        String[] output = {"Jim", "In round 1, Jim received 5 first choice votes, Pam received 3 first choice votes and Michael received 2 first choice votes.\nSince no choice has received over 50% of first choice votes, Michael was eliminated from consideration and their votes were redistributed to their voters' next ranked choice that has not been eliminated because they received the fewest first choice votes.\nIn round 2, Jim received 6 first choice votes, Pam received 4 first choice votes and Michael received 0 first choice votes.\nSince Jim received over 50% of first choice votes, Jim is the winner.\n"};
        assertArrayEquals(output, RcvCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate5()
    {
        String[] choices = {"Jim", "Pam", "Michael"};
        int[][] votes = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}, {2, 1, 3}, {2, 1, 3}, {2, 1, 3}, {2, 1, 3}, {2, 1, 3}, {2, 3, 1}, {3, 2, 1}};
        String[] output = {"Pam", "In round 1, Jim received 3 first choice votes, Pam received 5 first choice votes and Michael received 2 first choice votes.\nSince no choice has received over 50% of first choice votes, Michael was eliminated from consideration and their votes were redistributed to their voters' next ranked choice that has not been eliminated because they received the fewest first choice votes.\nIn round 2, Jim received 4 first choice votes, Pam received 6 first choice votes and Michael received 0 first choice votes.\nSince Pam received over 50% of first choice votes, Pam is the winner.\n"};
        assertArrayEquals(output, RcvCalc.calculate(choices, votes));
    }

    @Test
    public void testCalculate6()
    {
        String[] choices = {"Jim", "Pam", "Michael", "Kevin", "Dwight"};
        int[][] votes = {{1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {4, 1, 2, 3, 5}, {4, 1, 2, 3, 5}, {5, 2, 1, 3, 4}, {5, 2, 1, 3, 4}, {5, 3, 4, 1, 2}, {5, 4, 3, 2, 1}, {5, 4, 3, 2, 1}, {5, 4, 3, 2, 1}, {5, 4, 3, 2, 1}, {5, 4, 3, 2, 1}};
        String[] output = {"Dwight", "In round 1, Jim received 5 first choice votes, Pam received 2 first choice votes, Michael received 2 first choice votes, Kevin received 1 first choice vote and Dwight received 5 first choice votes.\nSince no choice has received over 50% of first choice votes, Kevin was eliminated from consideration and their votes were redistributed to their voters' next ranked choice that has not been eliminated because they received the fewest first choice votes.\nIn round 2, Jim received 5 first choice votes, Pam received 2 first choice votes, Michael received 2 first choice votes, Kevin received 0 first choice votes and Dwight received 6 first choice votes.\nSince no choice has received over 50% of first choice votes, Pam and Michael were eliminated from consideration and their votes were redistributed to their voters' next ranked choice that has not been eliminated because they received the fewest first choice votes.\nIn round 3, Jim received 7 first choice votes, Pam received 0 first choice votes, Michael received 0 first choice votes, Kevin received 0 first choice votes and Dwight received 8 first choice votes.\nSince Dwight received over 50% of first choice votes, Dwight is the winner.\n"};
        assertArrayEquals(output, RcvCalc.calculate(choices, votes));
    }
}
