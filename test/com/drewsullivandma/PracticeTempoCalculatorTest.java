package com.drewsullivandma;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.drewsullivandma.PracticeTempoCalculator;

public class PracticeTempoCalculatorTest {

	@Test
	public void rhythms_tempo_returns_expected_translated_user_input() {
		assertEquals(72, PracticeTempoCalculator.getRhythmsTempo(96));
		assertEquals(90, PracticeTempoCalculator.getRhythmsTempo(121));
		assertEquals(101, PracticeTempoCalculator.getRhythmsTempo(135));
		assertEquals(128, PracticeTempoCalculator.getRhythmsTempo(171));
	}
	
	@Test
	public void rhythms_tempo_returns_TEMPO_LOWER_BOUND_if_calculation_lower_than_TEMPO_LOWER_BOUND() {
		assertEquals(40, PracticeTempoCalculator.getRhythmsTempo(40));
		assertEquals(40, PracticeTempoCalculator.getRhythmsTempo(53));
	}
	
	@Test
	public void reach_tempo_returns_expected_translated_user_input() {
		assertEquals(70, PracticeTempoCalculator.getReachTempo(60));
		assertEquals(130, PracticeTempoCalculator.getReachTempo(120));
		assertEquals(111, PracticeTempoCalculator.getReachTempo(101));
		assertEquals(103, PracticeTempoCalculator.getReachTempo(93));
		assertEquals(76, PracticeTempoCalculator.getReachTempo(66));
		assertEquals(61, PracticeTempoCalculator.getReachTempo(51));
		assertEquals(50, PracticeTempoCalculator.getReachTempo(40));
		assertEquals(155, PracticeTempoCalculator.getReachTempo(145));
	}
	
	@Test
	public void divide_by_two_accurately_divides_by_two_including_integer_math() {
		assertEquals(40, PracticeTempoCalculator.divideByTwo(80));
		assertEquals(60, PracticeTempoCalculator.divideByTwo(120));
		assertEquals(120, PracticeTempoCalculator.divideByTwo(240));
		assertEquals(50, PracticeTempoCalculator.divideByTwo(101));
	}
	
	@Test
	public void divide_by_two_does_not_divide_by_two_if_input_is_below_TEMPO_ADJUSTMENT_LOWER_BOUND() {
		assertEquals(50, PracticeTempoCalculator.divideByTwo(50));
		assertEquals(79, PracticeTempoCalculator.divideByTwo(79));
	}
	
	@Test
	public void speed_up_speeds_up_by_the_expected_amount() {
		assertEquals(176, PracticeTempoCalculator.speedUp(160));
		assertEquals(144, PracticeTempoCalculator.speedUp(132));
		assertEquals(88, PracticeTempoCalculator.speedUp(80));
		assertEquals(72, PracticeTempoCalculator.speedUp(66));
		assertEquals(69, PracticeTempoCalculator.speedUp(63));
		assertEquals(46, PracticeTempoCalculator.speedUp(40));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void speed_up_throws_error_when_out_of_range() {
		PracticeTempoCalculator.speedUp(30);
		PracticeTempoCalculator.speedUp(241);
		PracticeTempoCalculator.speedUp(225);
	}
	
	@Test
	public void slow_down_slows_down_by_expected_amount() {
		assertEquals(144, PracticeTempoCalculator.slowDown(160));
		assertEquals(120, PracticeTempoCalculator.slowDown(132));
		assertEquals(72, PracticeTempoCalculator.slowDown(80));
		assertEquals(60, PracticeTempoCalculator.slowDown(66));
		assertEquals(40, PracticeTempoCalculator.slowDown(44));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void slow_down_throws_error_when_out_of_range() {
		PracticeTempoCalculator.slowDown(43);
		PracticeTempoCalculator.slowDown(2000);
		PracticeTempoCalculator.slowDown(3);
	}
}
