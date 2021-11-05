package com.calculation;

public class PluralityCalc 
{
    public static int findMaxIndex(int[] array)
    {
        int max = 0;
        for (int i=1; i < array.length; i++)
        {
            if (array[i] > array[max])
                max = i;
        }
        return max;
    }

    public static String[] calculate(String[] choices, int[][] votes) {
        String[] outcome = new String[2];
        String winner;
        String results = "";
        int[] tally = new int[choices.length];

        for (int i=0; i < votes.length; i++)
        {
            tally[votes[i][0]]++;
        }

        winner = choices[findMaxIndex(tally)];

        for (int i=0; i < tally.length; i++)
        {
            results = results + choices[i] + " got " + tally[i] + " votes.\n";
        }

        outcome[0] = winner;
        outcome[1] = results;
        return outcome;
    }

    public static int returnSeven() 
    {
        return 7;
    }
}
