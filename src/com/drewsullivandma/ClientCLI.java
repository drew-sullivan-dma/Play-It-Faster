package com.drewsullivandma;

import java.util.Scanner;

public class ClientCLI {
	
	private boolean userKnowsWhatTempoToBeginPracticingAt = false;
	private boolean userThinksGivenPracticeTempoIsPerfect = false; 
	private int tempo;
	private char userOpinionOfPracticeTempo;
	
	public static void main(String[] args) {
		ClientCLI cli = new ClientCLI();
		cli.runProgram();
		
	}
	
	private void runProgram() {
		setKnowledgeOfPracticeTempoBasedOnUserInput();
		while(tempo == 0) {
			setGoalTempoBasedOnUserInput();
		}
		while(!userKnowsWhatTempoToBeginPracticingAt) {
			setPracticeTempoBasedOnGoalTempo();
			if(userThinksGivenPracticeTempoIsPerfect) {
				break;
			}
			do {
				printPracticeTempoForUserEvaluation();
				setUserOpinionOfPracticeTempo();
				if(userOpinionOfPracticeTempo == 'p') {
					userKnowsWhatTempoToBeginPracticingAt = true;
					break;
				}
				adjustPracticeTempo(userOpinionOfPracticeTempo);
			} while(!userThinksGivenPracticeTempoIsPerfect); 
		}
		printPracticeTempos();
	}
	
	private void setKnowledgeOfPracticeTempoBasedOnUserInput() {
		System.out.println("Do you know what tempo to start practicing at today? Yes or No?");
		Scanner scan = new Scanner(System.in);
		String userInput = scan.nextLine();
		try {
			char shortenedUserInput = InputTranslator.parseUserOpinionOfPracticeTempo(userInput);
			if(shortenedUserInput == 'y') {
				userKnowsWhatTempoToBeginPracticingAt = true;
			}
		} catch (IllegalArgumentException ex) {
			printOutOfRangeErrorMessage();
		}
	}
	
	private void setGoalTempoBasedOnUserInput() {
		System.out.println("What is your goal tempo?");
		Scanner scan = new Scanner(System.in);
		String userInput = scan.nextLine();
		try {
			int userInputInt = Integer.parseInt(userInput);
			if(InputTranslator.isValidTempo(userInputInt)) {
				tempo = userInputInt;
			}
		} catch(Exception ex) {
			printOutOfRangeErrorMessage();
		}
	}
	
	private void printPracticeTempos() {
		try {
			int untranslatedRhythmsTempo = PracticeTempoCalculator.getRhythmsTempo(tempo);
			int untranslatedReachTempo = PracticeTempoCalculator.getReachTempo(tempo);
			int rhythmsTempo = InputTranslator.translateToMetronomeTempo(untranslatedReachTempo);
			int reachTempo = InputTranslator.translateToMetronomeTempo(untranslatedRhythmsTempo);
			System.out.printf("%-15s%5s", "Practice a few reps at this tempo:", tempo + "\n");
			System.out.printf("%-15s%4s", "Then practice in rhythms at this tempo:", rhythmsTempo + "\n");
			System.out.printf("%-15s%5s", "Then bump up the metronome and aim for this tempo:", reachTempo + "\n");
			System.out.printf("%-15s%5s", "Once you can do it a few times at ", reachTempo + ", you're all done with that pattern for the day!");
		} catch (IllegalArgumentException ex) {
			printOutOfRangeErrorMessage();
		}
	}
	
	private void setUserOpinionOfPracticeTempo() {
		System.out.println("Is this tempo too slow, too fast, or perfect?");
		Scanner scan = new Scanner(System.in);
		String userInput = scan.nextLine();
		try {
			char shortenedUserInput = InputTranslator.parseUserOpinionOfPracticeTempo(userInput);
			if(shortenedUserInput == 'p') {
				userThinksGivenPracticeTempoIsPerfect = true;
			}
			userOpinionOfPracticeTempo = shortenedUserInput;
		} catch(IllegalArgumentException ex) {
			printOutOfRangeErrorMessage();
		}
	}
	
	private void printPracticeTempoForUserEvaluation() {
		System.out.println("Your practice tempo is: " + tempo + ". Try it out for a few reps to see what you think.");
	}
	
	private void adjustPracticeTempo(char userOpinionOfPracticeTempo) {
		try {
			if(userOpinionOfPracticeTempo == 's') {
				tempo = PracticeTempoCalculator.speedUp(tempo);
			} else if(userOpinionOfPracticeTempo == 'f') {
				tempo = PracticeTempoCalculator.slowDown(tempo);
			}
		} catch(IllegalArgumentException ex) {
			printOutOfRangeErrorMessage();
		}
	}
	
	private void setPracticeTempoBasedOnGoalTempo() {
		try {
			int untranslatedDividedTempo = PracticeTempoCalculator.divideByTwo(tempo);
			tempo = InputTranslator.translateToMetronomeTempo(untranslatedDividedTempo);
		} catch(IllegalArgumentException ex) {
			printOutOfRangeErrorMessage();
		}
	}
	
	private void printOutOfRangeErrorMessage() {
		System.out.println("Please enter a tempo between " + InputTranslator.getTEMPO_LOWER_BOUND() + " and " +  InputTranslator.getTEMPO_UPPER_BOUND() + ".");
	}
	

}
