package com.bettervote.resultcalculation;

import java.util.ArrayList;
import java.lang.Math;

public class StarCalc {
    private static boolean tieOne;
    private static boolean tieTwo;
    private static String results;

    public static int[] findTopTwo(int[] array)
    {
        // maxIndex[0] contains the index of greatest number in the array. maxIndex[1] contains the index of the second greatest number in the array that is not equal to the first.
        int[] maxIndex = new int[2];

        for (int i=1; i < array.length; i++)
        {
            if (array[i] > array[maxIndex[0]])
            {
                maxIndex[1] = maxIndex[0];
                if (tieOne == true)
                    tieTwo = true;
                else
                    tieTwo = false;

                maxIndex[0] = i;
                tieOne = false;
            }
            else if (array[i] == array[maxIndex[0]])
                tieOne = true;
            else if (array[i] == array[maxIndex[1]])
                tieTwo = true;
        }

        if (maxIndex[0] == 0)
        {
            maxIndex[1] = 1;

            for(int i=2; i < array.length; i++)
            {
                if (i != maxIndex[0])
                {
                    if (array[i] > maxIndex[1])
                        maxIndex[1] = i;
                }
            }
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

    public static String[] runoff(String[] choices, int choiceOne, int choiceTwo, int[][] votes)
    {
        String[] outcome = new String[2];
        int[] tally = new int[2];

        for(int i=0; i < votes.length; i++)
        {
            if (votes[i][choiceOne] > votes[i][choiceTwo])
                tally[0]++;
            else if (votes[i][choiceOne] < votes[i][choiceTwo])
                tally[1]++;
        }

        if (tally[0] > tally[1])
            outcome[0] = choices[choiceOne];
        else if (tally[0] < tally[1])
            outcome[0] = choices[choiceTwo];
        else
            outcome[0] = "Tie between " + choices[choiceOne] + " and " + choices[choiceTwo];

        if (tally[0] != 1 && tally[1] != 1)
            outcome[1] = "In the runoff between " + choices[choiceOne] + " and " + choices[choiceTwo] + ", " + choices[choiceOne] + " received " + tally[0] + " votes and " + choices[choiceTwo] + " received " + tally[1] + " votes.\n";
        else if (tally[0] == 1 && tally[1] != 1)
            outcome[1] = "In the runoff between " + choices[choiceOne] + " and " + choices[choiceTwo] + ", " + choices[choiceOne] + " received " + tally[0] + " vote and " + choices[choiceTwo] + " received " + tally[1] + " votes.\n";
        else if (tally[0] != 1 && tally[1] == 1)
            outcome[1] = "In the runoff between " + choices[choiceOne] + " and " + choices[choiceTwo] + ", " + choices[choiceOne] + " received " + tally[0] + " votes and " + choices[choiceTwo] + " received " + tally[1] + " vote.\n";
        else
            outcome[1] = "In the runoff between " + choices[choiceOne] + " and " + choices[choiceTwo] + ", " + choices[choiceOne] + " received " + tally[0] + " vote and " + choices[choiceTwo] + " received " + tally[1] + " vote.\n";

        return outcome;
    }

    public static ArrayList<Integer> eliminationRounds(String[] choices, ArrayList<Integer> tiedWinnerIndexes, int[][] votes, int desiredSize)
    {
        while (tiedWinnerIndexes.size() != desiredSize)
        {
            int[] tally = new int[votes[0].length];

            for (int i=0; i < votes.length; i++)
            {
                int min = tiedWinnerIndexes.get(0);

                for (int j=1; j < tiedWinnerIndexes.size(); j++)
                {
                    // ADD CODE TO CHECK FOR TIES
                    if (votes[i][tiedWinnerIndexes.get(j)] < votes[i][min])
                        min = tiedWinnerIndexes.get(j);
                }

                tally[min]--;
            }

            int eliminationIndex = 0;
            for (int i=1; i < tally.length; i++)
            {
                if (tally[i] < tally[eliminationIndex])
                    eliminationIndex = i;
            }

            // ADD CODE TO HANDLE CASE FOR 1 BALLOT SO IT DOESN'T READ 1 BALLOTS
            if (desiredSize == 2)
            {
                results = results + "To break a tie for first place, an elimination round was held where ";

                for (int i = 0; i < tiedWinnerIndexes.size()-1; i++)
                    results = results + choices[tiedWinnerIndexes.get(i)] + " scored lowest on " + Math.abs(tally[tiedWinnerIndexes.get(i)]) + " ballots, ";
                results = results + "and " + choices[tiedWinnerIndexes.get(tiedWinnerIndexes.size()-1)] + " scored lowest on " + Math.abs(tally[tiedWinnerIndexes.get(tiedWinnerIndexes.size()-1)]) + " ballots. ";
                results = results + "Therefore " + choices[eliminationIndex] + " was eliminated from consideration in the runoff.\n";
            }
            else if (desiredSize == 1)
            {
                results = results + "To break a tie for second place, an elimination round was held where ";

                for (int i = 0; i < tiedWinnerIndexes.size()-1; i++)
                    results = results + choices[tiedWinnerIndexes.get(i)] + " scored lowest on " + Math.abs(tally[tiedWinnerIndexes.get(i)]) + " ballots, ";
                // ADD CODE TO HANDLE CASE WHERE THERE ARE TWO CHOICES: 1 AND 2. NOT 1, AND 2
                results = results + "and " + choices[tiedWinnerIndexes.get(tiedWinnerIndexes.size()-1)] + " scored lowest on " + Math.abs(tally[tiedWinnerIndexes.get(tiedWinnerIndexes.size()-1)]) + " ballots. ";
                results = results + "Therefore " + choices[eliminationIndex] + " was eliminated from consideration in the runoff.\n";
            }
            else
            {
                System.out.println("ERROR: INVALID DESIRED SIZE FOR ELIMINATION ROUNDS");
            }

            for(int i=0; i < tiedWinnerIndexes.size(); i++)
            {
                if (tiedWinnerIndexes.get(i) == eliminationIndex)
                    tiedWinnerIndexes.remove(i);
            }
        }

        return tiedWinnerIndexes;
    }

    public static ResultCalculationOutput calculate(String[] choices, int[][] votes) 
    {
        tieOne = false;
        tieTwo = false;
        results = "";
        String[] outcome = new String[2];
        int[] tally = new int[choices.length];
        int[] winnerIndex = new int[2];
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

        winnerIndex = findTopTwo(tally);

        for (int i=0; i < tally.length; i++)
        {
            results = results + choices[i] + " received a score of " + tally[i] + ".\n";
        }

        if (tieOne == true)
        {
            tiedWinnerIndexes = findTiedWinnerIndexes(tally[winnerIndex[0]], tally);
            
            tiedWinnerIndexes = eliminationRounds(choices, tiedWinnerIndexes, votes, 2);

            outcome = runoff(choices, tiedWinnerIndexes.get(0), tiedWinnerIndexes.get(1), votes);
            results = results + outcome[1];
        }
        else if(tieTwo == true)
        {
            tiedWinnerIndexes = findTiedWinnerIndexes(tally[winnerIndex[1]], tally);

            tiedWinnerIndexes = eliminationRounds(choices, tiedWinnerIndexes, votes, 1);

            outcome = runoff(choices, winnerIndex[0], tiedWinnerIndexes.get(0), votes);
            results = results + outcome[1];
        }
        else
        {
            outcome = runoff(choices, winnerIndex[0], winnerIndex[1], votes);
            results = results + outcome[1];
        }

        outcome[1] = results;
        return new ResultCalculationOutput(outcome[0], outcome[1]);
    }
}