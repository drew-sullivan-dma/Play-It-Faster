package com.techelevator;


public class PracticeTempoCalculator {
	
	public static int getRhythmsTempo(int inputTempo) {
		int rhythmsTempo = inputTempo * 3/4;
		return rhythmsTempo;
	}

	public static int getReachTempo(int inputTempo) {
		int reachTempo = inputTempo + 10;
		return reachTempo;
	}
	
	public static int divideByTwo(int inputTempo) {
		return inputTempo / 2;
	}
	
	public static int speedUp(int tempo) {
		if(tempo >= 160) {
			tempo += 16;
		} else if(tempo >= 132) {
			tempo += 12;
		} else if(tempo >= 80) {
			tempo += 8;
		} else if(tempo >= 40) {
			tempo += 6;
		} else {
			throw new IllegalArgumentException();
		}
		return InputTranslator.translateToMetronomeTempo(tempo);
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
		} 
		else {
			throw new IllegalArgumentException();
		}
		return InputTranslator.translateToMetronomeTempo(tempo);
	}	
		

}
