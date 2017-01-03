package com.drewsullivandma;


public class PracticeTempoCalculator {
	
	public static int getRhythmsTempo(int inputTempo) {
		int rhythmsTempo = inputTempo * 3/4;
		if(rhythmsTempo < InputTranslator.TEMPOS_LOWER_BOUND) {
			return InputTranslator.TEMPOS_LOWER_BOUND;	
		} else {
			return rhythmsTempo;
		}
	}

	public static int getReachTempo(int inputTempo) {
		int reachTempo = inputTempo + 10;
		return reachTempo;
	}
	
	public static int divideByTwo(int inputTempo) {
		if(inputTempo >= InputTranslator.LOWEST_ADJUSTABLE_TEMPO) {
			return inputTempo / 2;
		} else {
			return inputTempo;
		}
	}
	
	public static int speedUp(int tempo) {
		if(tempo > InputTranslator.TEMPOS_UPPER_BOUND) {
			tempo = InputTranslator.TEMPOS_UPPER_BOUND;
		} else if(tempo >= 160) {
			tempo += 16;
		} else if(tempo >= 132) {
			tempo += 12;
		} else if(tempo >= 80) {
			tempo += 8;
		} else if(tempo >= 40) {
			tempo += 6;
		} else {
			tempo = InputTranslator.TEMPOS_LOWER_BOUND;
		}
		return tempo;
	}
	
	public static int slowDown(int tempo) {
		if(tempo >= 160) {
			tempo -= 16;
		} else if(tempo >= 132) {
			tempo -= 12;
		} else if(tempo >= 80) {
			tempo -= 8;
		} else if(tempo >= 66) {
			tempo -= 6;
		} else if(tempo >= 44){
			tempo -= 4;
		} else {
			tempo = InputTranslator.TEMPOS_LOWER_BOUND;
		}
		return tempo;
	}	
		

}
