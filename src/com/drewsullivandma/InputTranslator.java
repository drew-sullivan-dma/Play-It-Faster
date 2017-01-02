package com.drewsullivandma;

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
	private static final int TEMPOS_LOWER_BOUND = 40;
	private static final int TEMPOS_UPPER_BOUND = 240;
	private static final int LOWEST_ADJUSTABLE_TEMPO = 80;

	public static int getTemposLowerBound() {
		return TEMPOS_LOWER_BOUND;
	}

	public static int getTemposUpperBound() {
		return TEMPOS_UPPER_BOUND;
	}

	public static int getLowestAdjustableTempo() {
		return LOWEST_ADJUSTABLE_TEMPO;
	}

	public static int translateToMetronomeTempo(int tempoToBeTranslated) {
		if(tempoToBeTranslated == TEMPOS_LOWER_BOUND) {
			return TEMPOS_LOWER_BOUND;
		}
		if(tempoToBeTranslated < TEMPOS_LOWER_BOUND) {
			throw new IllegalArgumentException();
		}
		if(tempoToBeTranslated > TEMPOS_UPPER_BOUND) {
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
		if(userInput < TEMPOS_LOWER_BOUND || userInput > TEMPOS_UPPER_BOUND) {
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
				}
			}
		} else {
			throw new IllegalArgumentException();
		}
		return shortenedUserInput;
	} 
	

}
