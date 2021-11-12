package com.bettervote.resultcalculation;

/**
 * Hello world!
 *
 */

enum VotingSystem {
	RCV, STAR, PLURALITY
};
public class Calculator

{
	

	// The first dimension of votes diffentiates each ballot. The second dimension
	// is the vote on the ballot.
	public static ResultCalculationOutput calculate(VotingSystem votingSystem, String[] choices, int[][] votes) {
		ResultCalculationOutput outcome = new ResultCalculationOutput(null, null);

		switch (votingSystem) {
		case RCV:
//			outcome = RCVCalc.calculate(choices, votes);
			break;
		case STAR:
			outcome =  StarCalc.calculate(choices, votes);
			break;
		case PLURALITY:
			outcome =  PluralityCalc.calculate(choices, votes);
			break;
			
//		default:
			//Throw exception instead
//			System.out.println("ERROR: INVALID VOTING SYSTEM");
		}

		return outcome;
	}



}