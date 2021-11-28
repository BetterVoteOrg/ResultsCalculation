package com.calculation;

import java.util.ArrayList;

public class RcvCalc {
    private static String results;
    private static ArrayList<Integer> eliminatedIndexes = new ArrayList<Integer>();

    public static int[] tallyFirstChoices(String[] choices, int[][] votes, int roundTracker)
    {
        int[] tally = new int[choices.length];

        for (int i = 0; i < votes.length; i++)
        {
            for (int j = 0; j < choices.length; j++)
            {
                if (votes[i][j] == 1)
                {
                    tally[j]++;
                }
            }
        }

        results = results + "In round " + roundTracker + ", ";
        if (tally[0] == 1)
            results = results + choices[0] + " received " + tally[0] + " first choice vote";
        else    
            results = results + choices[0] + " received " + tally[0] + " first choice votes";
        for (int i = 1; i < tally.length - 1; i++)
        {
            if (tally[i] == 1)
                results = results + ", " + choices[i] + " received " + tally[i] + " first choice vote";
            else
                results = results + ", " + choices[i] + " received " + tally[i] + " first choice votes";
        }
        if (tally[tally.length - 1] == 1)
            results = results + " and " + choices[tally.length - 1] + " received " + tally[tally.length - 1] + " first choice vote.\n";
        else
            results = results + " and " + choices[tally.length - 1] + " received " + tally[tally.length - 1] + " first choice votes.\n";

        return tally;
    }

    public static boolean checkForWinner (String[] choices, int[] tally)
    {
        boolean choiceReceivedOverFiftyPercent = false;
        int maxIndex = 0;
        double total = 0;

        for (int i = 0; i < tally.length; i++)
        {
            if (tally[maxIndex] < tally[i])
            {
                maxIndex = i;
            }
            total += tally[i];
        }

        if ((double) tally[maxIndex] / total > 0.5)
        {
            choiceReceivedOverFiftyPercent = true;
            results = results + "Since " + choices[maxIndex] + " received over 50% of first choice votes, " + choices[maxIndex] + " is the winner.\n";
        }

        // When a winner cannot be found because all choices were eliminated
        else if (total <= 0)
        {
            choiceReceivedOverFiftyPercent = true;
            results = results + "Since all choices have been eliminated, a winner could not be found.\n";
        }

        else
        {
            results = results + "Since no choice has received over 50% of first choice votes, ";
        }

        return choiceReceivedOverFiftyPercent;
    }

    public static String findWinner (String[] choices, int[] tally)
    {
        String winner;
        int maxIndex = 0;

        for (int i = 1; i < tally.length; i++)
        {
            if (tally[maxIndex] < tally[i])
                maxIndex = i;
        }

        if (tally[maxIndex] != 0)
            winner = choices[maxIndex];
        else
            winner = "A winner could not be found";
        return winner;
    }

    public static ArrayList<Integer> findIndexesToEliminate(String[] choices, int[] tally)
    {
        int minPosIndex = 0;
        ArrayList<Integer> indexesToEliminate = new ArrayList<Integer>();
        indexesToEliminate.add(minPosIndex);

        for (int i = 1; i < tally.length; i++)
        {
            if (tally[i] < tally[minPosIndex] && tally[i] > 0)
            {
                minPosIndex = i;
                indexesToEliminate.clear();
                indexesToEliminate.add(minPosIndex);
            }
            else if (tally[i] == tally[minPosIndex])
            {
                indexesToEliminate.add(i);
            }
        }

        results = results + choices[indexesToEliminate.get(0)];
        for (int i = 1; i < indexesToEliminate.size() - 1; i++)
            results = results + ", " + choices[indexesToEliminate.get(i)];
        if (indexesToEliminate.size() > 1)
        {
            results = results + " and " + choices[indexesToEliminate.get(indexesToEliminate.size() -1)];
            results = results + " were eliminated from consideration and their votes were redistributed to their voters' next ranked choice that has not been eliminated";
        }
        else
            results = results + " was eliminated from consideration and their votes were redistributed to their voters' next ranked choice that has not been eliminated";

        results = results + " because they received the fewest first choice votes.\n";

        return indexesToEliminate;
    }

    public static int[][] adjustVotes(int[][] votes)
    {
        for (int i = 0; i < votes.length; i++)
        {
            // As we count through the eliminatedIndexes, we must adjust the votes for all counted eliminatedindexes a number of times equal to the number of counted eliminatedindexes
            for (int j = 0; j < eliminatedIndexes.size(); j++)
            {
                for (int k = 0; k <= j; k++)
                {
                    for (int l = 0; l <= j; l++)
                    {
                        if (votes[i][eliminatedIndexes.get(l)] == 1)
                        {
                            for (int m = 0; m < votes[0].length; m++)
                                votes[i][m]--;
                        }
                    }
                }
            }
        }
       
        return votes;
    }

    public static String[] calculate(String[] choices, int[][] votes)
    {
        results = "";
        eliminatedIndexes.clear();
        String[] outcome = new String[2];
        boolean winnerFound = false;
        int[] tally;
        ArrayList<Integer> indexesToEliminate = new ArrayList<Integer>();

        for (int roundTracker = 1; winnerFound == false; roundTracker++)
        {
            tally = tallyFirstChoices(choices, votes, roundTracker);

            if (checkForWinner(choices, tally))
            {
                winnerFound = true;
                outcome[0] = findWinner(choices, tally);
            }
            else
            {
                indexesToEliminate = findIndexesToEliminate(choices, tally);

                for (int i = 0; i < indexesToEliminate.size(); i++)
                    eliminatedIndexes.add(indexesToEliminate.get(i));
                indexesToEliminate.clear();

                votes = adjustVotes(votes);
            }
        }

        outcome[1] = results;
        return outcome;
    }
}