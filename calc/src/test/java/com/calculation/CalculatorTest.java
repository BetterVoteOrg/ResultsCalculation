package com.calculation;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class CalculatorTest 
{
    @Test
    public void testCalculate0p()
    {
        VotingSystem votingSystem = VotingSystem.PLURALITY;
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {};
        String[] output = {"Tie between Jim and Pam", "Jim got 0 votes.\nPam got 0 votes.\n"};
        assertArrayEquals(output, Calculator.calculate(votingSystem, choices, votes));
    }

    @Test
    public void testCalculate1p()
    {
        VotingSystem votingSystem = VotingSystem.PLURALITY;
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {1}, {1}, {1}, {1}, {1}, {1}, {1}, {1}, {1}};
        String[] output = {"Pam", "Jim got 8 votes.\nPam got 9 votes.\n"};
        assertArrayEquals(output, Calculator.calculate(votingSystem, choices, votes));
    }

    @Test
    public void testCalculate2p()
    {
        VotingSystem votingSystem = VotingSystem.PLURALITY;
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {{0}, {0}, {1}, {1}};
        String[] output = {"Tie between Jim and Pam", "Jim got 2 votes.\nPam got 2 votes.\n"};
        assertArrayEquals(output, Calculator.calculate(votingSystem, choices, votes));
    }

    @Test
    public void testCalculate3p()
    {
        VotingSystem votingSystem = VotingSystem.PLURALITY;
        String[] choices = {"Jim", "Pam", "Michael"};
        int[][] votes = {{0}, {0}, {1}, {1}, {2}};
        String[] output = {"Tie between Jim and Pam", "Jim got 2 votes.\nPam got 2 votes.\nMichael got 1 vote.\n"};
        assertArrayEquals(output, Calculator.calculate(votingSystem, choices, votes));
    }

    @Test
    public void testCalculate4p()
    {
        VotingSystem votingSystem = VotingSystem.PLURALITY;
        String[] choices = {"Jim", "Pam", "Michael"};
        int[][] votes = {{0}, {0}, {1}, {1}, {2}, {2}};
        String[] output = {"Tie between Jim, Pam and Michael", "Jim got 2 votes.\nPam got 2 votes.\nMichael got 2 votes.\n"};
        assertArrayEquals(output, Calculator.calculate(votingSystem, choices, votes));
    }

    @Test
    public void testCalculate5p()
    {
        VotingSystem votingSystem = VotingSystem.PLURALITY;
        String[] choices = {"Jim", "Pam", "Michael"};
        int[][] votes = {{2}, {0}, {0}, {2}, {1}, {1}, {2}, {0}, {1}, {2}, {0}, {1}, {2}, {2}, {1}, {0}};
        String[] output = {"Michael", "Jim got 5 votes.\nPam got 5 votes.\nMichael got 6 votes.\n"};
        assertArrayEquals(output, Calculator.calculate(votingSystem, choices, votes));
    }

    @Test
    public void testCalculate0s()
    {
        VotingSystem votingSystem = VotingSystem.STAR;
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {};
        String[] output = {"Tie between Jim and Pam", "Jim received a score of 0.\nPam received a score of 0.\nIn the runoff between Jim and Pam, Jim received 0 votes and Pam received 0 votes.\n"};
        assertArrayEquals(output, Calculator.calculate(votingSystem, choices, votes));
    }

    @Test
    public void testCalculate1s()
    {
        VotingSystem votingSystem = VotingSystem.STAR;
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {{0, 5}, {0, 5}, {0, 5}, {0, 5}, {0, 5}, {0, 5}, {0, 5}, {0, 5}, {5, 0}, {5, 0}, {5, 0}, {5, 0}, {5, 0}, {5, 0}, {5, 0}, {5, 0}, {5, 0}};
        String[] output = {"Jim", "Jim received a score of 45.\nPam received a score of 40.\nIn the runoff between Jim and Pam, Jim received 9 votes and Pam received 8 votes.\n"};
        assertArrayEquals(output, Calculator.calculate(votingSystem, choices, votes));
    }

    @Test
    public void testCalculate2s()
    {
        VotingSystem votingSystem = VotingSystem.STAR;
        String[] choices = {"Jim", "Pam", "Michael"};
        int[][] votes = {{3, 3, 1}, {3, 3, 1}, {3, 3, 1}, {3, 3, 1}, {3, 3, 1}, {2, 2, 2}, {2, 2, 2}, {2, 2, 2}, {5, 4, 0}, {4, 5, 1}};
        String[] output = {"Tie between Jim and Pam", "Jim received a score of 30.\nPam received a score of 30.\nMichael received a score of 12.\nIn the runoff between Jim and Pam, Jim received 1 vote and Pam received 1 vote.\n"};
        assertArrayEquals(output, Calculator.calculate(votingSystem, choices, votes));
    }

    @Test
    public void testCalculate3s()
    {
        VotingSystem votingSystem = VotingSystem.STAR;
        String[] choices = {"Jim", "Pam", "Michael"};
        int[][] votes = {{3, 3, 1}, {3, 3, 1}, {3, 3, 1}, {3, 3, 1}, {3, 3, 1}, {2, 2, 2}, {2, 2, 2}, {2, 2, 2}, {5, 4, 0}, {4, 5, 1}, {4, 0, 0}, {0, 2, 0}, {0, 2, 0}};
        String[] output = {"Pam", "Jim received a score of 34.\nPam received a score of 34.\nMichael received a score of 12.\nIn the runoff between Jim and Pam, Jim received 2 votes and Pam received 3 votes.\n"};
        assertArrayEquals(output, Calculator.calculate(votingSystem, choices, votes));
    }

    @Test
    public void testCalculate4s()
    {
        VotingSystem votingSystem = VotingSystem.STAR;
        String[] choices = {"Jim", "Pam", "Michael", "Kevin"};
        int[][] votes = {{3, 3, 1, 2}, {3, 3, 1, 2}, {3, 3, 1, 2}, {3, 3, 1, 2}, {3, 3, 1, 2}, {2, 2, 2, 1}, {2, 2, 2, 1}, {2, 2, 2, 1}, {5, 4, 0, 3}, {4, 5, 1, 3}, {4, 0, 0, 5}, {0, 2, 0, 5}, {0, 2, 0, 5}};
        String[] output = {"Pam", "Jim received a score of 34.\nPam received a score of 34.\nMichael received a score of 12.\nKevin received a score of 34.\nTo break a tie for first place, an elimination round was held where Jim scored lowest on 2 ballot(s), Pam scored lowest on 1 ballot(s) and Kevin scored lowest on 10 ballot(s). Therefore Kevin was eliminated from consideration in the runoff.\nIn the runoff between Jim and Pam, Jim received 2 votes and Pam received 3 votes.\n"};
        assertArrayEquals(output, Calculator.calculate(votingSystem, choices, votes));
    }

    @Test
    public void testCalculate0r()
    {
        VotingSystem votingSystem = VotingSystem.RCV;
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {};
        String[] output = {"A winner could not be found", "In round 1, Jim received 0 first choice votes and Pam received 0 first choice votes.\nSince all choices have been eliminated, a winner could not be found.\n"};
        assertArrayEquals(output, Calculator.calculate(votingSystem, choices, votes));
    }

    @Test
    public void testCalculate1r()
    {
        VotingSystem votingSystem = VotingSystem.RCV;
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {{2, 1}, {2, 1}, {2, 1}, {2, 1}, {2, 1}, {2, 1}, {2, 1}, {2, 1}, {1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}, {1, 2}};
        String[] output = {"Jim", "In round 1, Jim received 9 first choice votes and Pam received 8 first choice votes.\nSince Jim received over 50% of first choice votes, Jim is the winner.\n"};
        assertArrayEquals(output, Calculator.calculate(votingSystem, choices, votes));
    }

    @Test
    public void testCalculate2r()
    {
        VotingSystem votingSystem = VotingSystem.RCV;
        String[] choices = {"Jim", "Pam"};
        int[][] votes = {{2, 1}, {2, 1}, {2, 1}, {0, 1}, {2, 1}, {0, 1}, {2, 1}, {2, 1}, {1, 2}, {1, 2}, {1, 0}, {1, 0}, {1, 0}, {1, 0}, {1, 0}, {1, 2}, {1, 2}};
        String[] output = {"Jim", "In round 1, Jim received 9 first choice votes and Pam received 8 first choice votes.\nSince Jim received over 50% of first choice votes, Jim is the winner.\n"};
        assertArrayEquals(output, Calculator.calculate(votingSystem, choices, votes));
    }

    @Test
    public void testCalculate3r()
    {
        VotingSystem votingSystem = VotingSystem.RCV;
        String[] choices = {"Jim", "Pam", "Michael"};
        int[][] votes = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}, {1, 2, 3}, {2, 1, 3}, {2, 1, 3}, {2, 1, 3}, {2, 1, 3}, {2, 3, 1}, {3, 2, 1}};
        String[] output = {"A winner could not be found", "In round 1, Jim received 4 first choice votes, Pam received 4 first choice votes and Michael received 2 first choice votes.\nSince no choice has received over 50% of first choice votes, Michael was eliminated from consideration and their votes were redistributed to their voters' next ranked choice that has not been eliminated because they received the fewest first choice votes.\nIn round 2, Jim received 5 first choice votes, Pam received 5 first choice votes and Michael received 0 first choice votes.\nSince no choice has received over 50% of first choice votes, Jim and Pam were eliminated from consideration and their votes were redistributed to their voters' next ranked choice that has not been eliminated because they received the fewest first choice votes.\nIn round 3, Jim received 0 first choice votes, Pam received 0 first choice votes and Michael received 0 first choice votes.\nSince all choices have been eliminated, a winner could not be found.\n"};
        assertArrayEquals(output, Calculator.calculate(votingSystem, choices, votes));
    }

    @Test
    public void testCalculate4r()
    {
        VotingSystem votingSystem = VotingSystem.RCV;
        String[] choices = {"Jim", "Pam", "Michael"};
        int[][] votes = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}, {1, 2, 3}, {1, 2, 3}, {2, 1, 3}, {2, 1, 3}, {2, 1, 3}, {2, 3, 1}, {3, 2, 1}};
        String[] output = {"Jim", "In round 1, Jim received 5 first choice votes, Pam received 3 first choice votes and Michael received 2 first choice votes.\nSince no choice has received over 50% of first choice votes, Michael was eliminated from consideration and their votes were redistributed to their voters' next ranked choice that has not been eliminated because they received the fewest first choice votes.\nIn round 2, Jim received 6 first choice votes, Pam received 4 first choice votes and Michael received 0 first choice votes.\nSince Jim received over 50% of first choice votes, Jim is the winner.\n"};
        assertArrayEquals(output, Calculator.calculate(votingSystem, choices, votes));
    }

    @Test
    public void testCalculate5r()
    {
        VotingSystem votingSystem = VotingSystem.RCV;
        String[] choices = {"Jim", "Pam", "Michael"};
        int[][] votes = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}, {2, 1, 3}, {2, 1, 3}, {2, 1, 3}, {2, 1, 3}, {2, 1, 3}, {2, 3, 1}, {3, 2, 1}};
        String[] output = {"Pam", "In round 1, Jim received 3 first choice votes, Pam received 5 first choice votes and Michael received 2 first choice votes.\nSince no choice has received over 50% of first choice votes, Michael was eliminated from consideration and their votes were redistributed to their voters' next ranked choice that has not been eliminated because they received the fewest first choice votes.\nIn round 2, Jim received 4 first choice votes, Pam received 6 first choice votes and Michael received 0 first choice votes.\nSince Pam received over 50% of first choice votes, Pam is the winner.\n"};
        assertArrayEquals(output, Calculator.calculate(votingSystem, choices, votes));
    }

    @Test
    public void testCalculate6r()
    {
        VotingSystem votingSystem = VotingSystem.RCV;
        String[] choices = {"Jim", "Pam", "Michael", "Kevin", "Dwight"};
        int[][] votes = {{1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {4, 1, 2, 3, 5}, {4, 1, 2, 3, 5}, {5, 2, 1, 3, 4}, {5, 2, 1, 3, 4}, {5, 3, 4, 1, 2}, {5, 4, 3, 2, 1}, {5, 4, 3, 2, 1}, {5, 4, 3, 2, 1}, {5, 4, 3, 2, 1}, {5, 4, 3, 2, 1}};
        String[] output = {"Dwight", "In round 1, Jim received 5 first choice votes, Pam received 2 first choice votes, Michael received 2 first choice votes, Kevin received 1 first choice vote and Dwight received 5 first choice votes.\nSince no choice has received over 50% of first choice votes, Kevin was eliminated from consideration and their votes were redistributed to their voters' next ranked choice that has not been eliminated because they received the fewest first choice votes.\nIn round 2, Jim received 5 first choice votes, Pam received 2 first choice votes, Michael received 2 first choice votes, Kevin received 0 first choice votes and Dwight received 6 first choice votes.\nSince no choice has received over 50% of first choice votes, Pam and Michael were eliminated from consideration and their votes were redistributed to their voters' next ranked choice that has not been eliminated because they received the fewest first choice votes.\nIn round 3, Jim received 7 first choice votes, Pam received 0 first choice votes, Michael received 0 first choice votes, Kevin received 0 first choice votes and Dwight received 8 first choice votes.\nSince Dwight received over 50% of first choice votes, Dwight is the winner.\n"};
        assertArrayEquals(output, Calculator.calculate(votingSystem, choices, votes));
    }
}
