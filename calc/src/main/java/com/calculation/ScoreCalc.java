package com.calculation;

import java.util.ArrayList;

public class ScoreCalc 
{
    private static boolean tie;

    public static int findMaxIndex(int[] array)
    {
        int maxIndex = 0;
        for (int i=1; i < array.length; i++)
        {
            if (array[i] > array[maxIndex])
            {
                maxIndex = i;
                tie = false;
            }
            else if (array[i] == array[maxIndex])
                tie = true;
        }
        return maxIndex;
    }

    public static ArrayList<Integer> findTiedWinnerIndexes(int winningVote, int[] tally)
    {
        ArrayList<Integer> tiedWinnerIndexes = new ArrayList<Integer>();

        for (int i=0; i < tally.length; i++)
        {
            if (tally[i] == winningVote)
                tiedWinnerIndexes.add(i);
        }

        return tiedWinnerIndexes;
    }

    public static String[] calculate(String[] choices, int[][] votes) 
    {
        tie = false;
        String[] outcome = new String[2];
        String winner;
        String results = "";
        int[] tally = new int[choices.length];
        int winnerIndex;
        ArrayList<Integer> tiedWinnerIndexes;

        for (int i=0; i < votes.length; i++)
        {
            for(int j=0; j < choices.length; j++)
            {
                if (votes[i][j] < 0 || votes[i][j] > 5)
                    System.out.println("ERROR: SCORE OUT OF BOUNDS");
                else
                    tally[j] = tally[j] + votes[i][j];
            }
        }

        winnerIndex = findMaxIndex(tally);

        if (tie == false)
            winner = choices[winnerIndex];
        else
        {
            tiedWinnerIndexes = findTiedWinnerIndexes(tally[winnerIndex], tally);
            winner = "Tie between " + choices[tiedWinnerIndexes.get(0)];
            for (int i=1; i < tiedWinnerIndexes.size()-1; i++)
            {
                winner = winner + ", " + choices[tiedWinnerIndexes.get(i)];
            }
            if (tiedWinnerIndexes.size() > 1)
                winner = winner + " and " + choices[tiedWinnerIndexes.get(tiedWinnerIndexes.size()-1)];
        }

        for (int i=0; i < tally.length; i++)
        {
            results = results + choices[i] + " received a score of " + tally[i] + ".\n";
        }

        outcome[0] = winner;
        outcome[1] = results;
        return outcome;
    }
}
