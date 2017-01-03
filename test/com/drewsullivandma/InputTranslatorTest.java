package com.drewsullivandma;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.drewsullivandma.InputTranslator;

public class InputTranslatorTest {

	@Test
	public void chops_off_expected_extra_material_from_user_input() {
		assertEquals('f', InputTranslator.parseUserOpinionOfPracticeTempo("too fast"));
		assertEquals('s', InputTranslator.parseUserOpinionOfPracticeTempo("TEST"));
	}
	
	@Test(expected=IllegalArgumentException.class)
		public void empty_string_throws_error() {
		InputTranslator.parseUserOpinionOfPracticeTempo("");
	}
	
	@Test
	public void returns_expected_int() {
		assertEquals(76, InputTranslator.translateToMetronomeTempo(79));
		assertEquals(100, InputTranslator.translateToMetronomeTempo(103));
		assertEquals(132, InputTranslator.translateToMetronomeTempo(132));
		assertEquals(69, InputTranslator.translateToMetronomeTempo(70));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void throws_error_if_input_int_out_of_range() {
		InputTranslator.translateToMetronomeTempo(38);
		InputTranslator.translateToMetronomeTempo(241);
		InputTranslator.translateToMetronomeTempo(40);
	}
}
