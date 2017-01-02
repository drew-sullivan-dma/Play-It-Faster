package com.drewsullivandma;

import java.util.Scanner;

public class ClientCLI {
	
	private Boolean userKnowsTempo = null;
	private int tempo;
	private char userOpinionOfTempo;
	
	public static void main(String[] args) {
		ClientCLI cli = new ClientCLI();
		cli.runProgram();
		
	}
	
	private void runProgram() {
		while(userKnowsTempo == null) {
			setUserKnowledgeOfTempo();
		}
		while(!tempoIsSet()) {
			setTempoBasedOnUserInput();
		}
		while(!userKnowsTempo) {
			setTempoBasedOnGoalTempo();
			do {
				printPracticeTempoForUserEvaluation();
				setUserOpinionOfTempo();
				adjustTempo(userOpinionOfTempo);
			} while(!tempoIsPerfect()); 
		}
		printPracticeTempos();
	}

	private boolean tempoIsSet() {
		return tempo > 0;
	}
	
	private void setUserKnowledgeOfTempo() {
		System.out.println("Do you know what tempo to start practicing at today? Yes or No?");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		try {
			String userInput = scan.nextLine();
			char shortenedUserInput = InputTranslator.parseUserOpinionOfPracticeTempo(userInput);
			if(shortenedUserInput == 'y') {
				userKnowsTempo = true;
			} else if(shortenedUserInput == 'n') {
				userKnowsTempo = false;
			}
		} catch (IllegalArgumentException ex) {
			printOutOfRangeErrorMessage();
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private void setTempoBasedOnUserInput() {
		System.out.println("What is your goal tempo?");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		try {
			String userInput = scan.nextLine();
			int userInputInt = Integer.parseInt(userInput);
			if(InputTranslator.isValidTempo(userInputInt)) {
				tempo = userInputInt;
			}
		} catch (IllegalArgumentException ex) {
			printOutOfRangeErrorMessage();
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private void printPracticeTempos() {
		try {
			int untranslatedRhythmsTempo = PracticeTempoCalculator.getRhythmsTempo(tempo);
			int untranslatedReachTempo = PracticeTempoCalculator.getReachTempo(tempo);
			int rhythmsTempo = InputTranslator.translateToMetronomeTempo(untranslatedRhythmsTempo);
			int reachTempo = InputTranslator.translateToMetronomeTempo(untranslatedReachTempo);
			System.out.printf("%-15s%5s", "Practice a few reps at this tempo:", tempo + "\n");
			System.out.printf("%-15s%4s", "Then practice in rhythms at this tempo:", rhythmsTempo + "\n");
			System.out.printf("%-15s%5s", "Then bump up the metronome and aim for this tempo:", reachTempo + "\n");
			System.out.printf("%-15s%5s", "Once you can do it a few times at ", reachTempo + ", you're all done with that pattern for the day!");
		} catch (IllegalArgumentException ex) {
			printOutOfRangeErrorMessage();
		}
	}
	
	private void setUserOpinionOfTempo() {
		System.out.println("Is this tempo too slow, too fast, or perfect?");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		try {
			String userInput = scan.nextLine();
			char shortenedUserInput = InputTranslator.parseUserOpinionOfPracticeTempo(userInput);
			userOpinionOfTempo = shortenedUserInput;
		} catch (IllegalArgumentException ex) {
			printOutOfRangeErrorMessage();
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private boolean tempoIsPerfect() {
		if(userOpinionOfTempo == 'p') {
			userKnowsTempo = true;
			return true;
		}
		return false;
	}
	
	private void printPracticeTempoForUserEvaluation() {
		System.out.println("Your practice tempo is: " + tempo + ". Try it out for a few reps to see what you think.");
	}
	
	private void adjustTempo(char userOpinionOfPracticeTempo) {
		int untranslatedTempo = 0;
		try {
			if(userOpinionOfPracticeTempo == 's') {
				untranslatedTempo = PracticeTempoCalculator.speedUp(tempo);
			} else if(userOpinionOfPracticeTempo == 'f') {
				untranslatedTempo = PracticeTempoCalculator.slowDown(tempo);
			} else if(userOpinionOfPracticeTempo == 'p') {
				untranslatedTempo = tempo;
			} 
			tempo = InputTranslator.translateToMetronomeTempo(untranslatedTempo);
		} catch(IllegalArgumentException ex) {
			printOutOfRangeErrorMessage();
		}
	}
	
	private void setTempoBasedOnGoalTempo() {
		try {
			int untranslatedDividedTempo = PracticeTempoCalculator.divideByTwo(tempo);
			tempo = InputTranslator.translateToMetronomeTempo(untranslatedDividedTempo);
		} catch(IllegalArgumentException ex) {
			printOutOfRangeErrorMessage();
		}
	}
	
	private void printOutOfRangeErrorMessage() {
		int TEMPOS_LOWER_BOUND = InputTranslator.getTemposLowerBound();
		int TEMPOS_UPPER_BOUND = InputTranslator.getTemposUpperBound();
		System.out.println("Please enter a tempo between " + TEMPOS_LOWER_BOUND + " and " +  TEMPOS_UPPER_BOUND + ".");
	}
	

}
