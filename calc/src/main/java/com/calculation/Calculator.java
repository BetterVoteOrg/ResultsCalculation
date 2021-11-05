package com.calculation;

enum VotingSystem {RCV, STAR, PLURALITY}

/**
 * Hello world!
 *
 */
public class Calculator 
{

    public static int returnFive()
    {
        return 5;
    }
    
    // The first dimension of votes diffentiates each ballot. The second dimension is the vote on the ballot.
    public static String[] calculate(VotingSystem votingSystem, String[] choices, int[][] votes) 
    {
        String[] outcome = new String[2];
        
        switch(votingSystem) {
            case RCV:
                break;
            case STAR:
                break;
            case PLURALITY:
                break;
            default:
                System.out.println("ERROR: INVALID VOTING SYSTEM");
        }

        return outcome;
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

}
