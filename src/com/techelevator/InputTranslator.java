package com.techelevator;

public class InputTranslator {
	
	private static final int[] METRONOME_TEMPOS = new int[]  {40, 42, 44, 46, 48, //0-4
														      50, 52, 54, 56, 58, //5-9
														      60, 63, 66, 69, 72, //10-14
														      76, 80, 84, 88, 92, //15-19
														      96, 100, 104, 108, 112, //20-24
														      116, 120, 126, 132, 138, //25-29
														      144, 152, 160, 168, 176, //30-34
														      184, 192, 200, 208, 216, //35-39
														      224, 232, 240}; //40-42

	public static int translateToMetronomeTempo(int tempoToBeTranslated) {
		if(tempoToBeTranslated == 40) {
			return 40;
		}
		if(tempoToBeTranslated < 40) {
			throw new IllegalArgumentException();
		}
		if(tempoToBeTranslated > 240) {
			throw new IllegalArgumentException();
		}
		for(int i = METRONOME_TEMPOS.length - 1; tempoToBeTranslated != METRONOME_TEMPOS[i]; i--) {
			if(tempoToBeTranslated >= METRONOME_TEMPOS[i]) {
				tempoToBeTranslated = METRONOME_TEMPOS[i];
				break;
			}
		}
		return tempoToBeTranslated;
		
	}
	
	public static boolean isValidTempo(int userInput) {
		if(userInput < 40 || userInput > 240) {
			throw new IllegalArgumentException();
		} else {
			return true;
		}
	}
	
	public static char parseUserOpinionOfPracticeTempo(String inputString) {
		char shortenedUserInput = ' ';
		char[] chars = inputString.toLowerCase().toCharArray();
		if(chars.length > 0) {
			for(int i = 0; i < inputString.length(); i++) {
				if(chars[i] == 'f' ||
				   chars[i] == 's' ||
				   chars[i] == 'p' ||
				   chars[i] == 'y' ||
				   chars[i] == 'n') {
					   shortenedUserInput = chars[i]; 
					   break;
				} else {
					shortenedUserInput = '#';
				}
			}
		} else {
			throw new IllegalArgumentException();
		}
		return shortenedUserInput;
	} 
	

}
