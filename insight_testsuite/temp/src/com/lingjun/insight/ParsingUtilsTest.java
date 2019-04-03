package com.lingjun.insight;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParsingUtilsTest {
	
	@Test
	public void testTryParseInt() {
		int actual = ParsingUtils.tryParseInt("100");
		int expected = 100;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testTryParseIntWithNegativeNumber() {
		int actual = ParsingUtils.tryParseInt("-100");
		int expected = -100;
		assertEquals(expected, actual);
	}

	@Test
	public void testTryParseIntFailsWithNonNumericalString() {
		assertNull(ParsingUtils.tryParseInt("abc012"));
	}
	
	@Test
	public void testTryParseIntFailsWithIllFormedNumericalString() {
		assertNull(ParsingUtils.tryParseInt("0-12"));
	}
	
	@Test
	public void testTryParseIntFailsWithNonIntegerString() {
		assertNull(ParsingUtils.tryParseInt("1.2"));
	}
	
	@Test
	public void testTryParseIntFailsWithOverRangeIntegerString() {
		assertNull(ParsingUtils.tryParseInt("9999999999999999999"));
	}

	@Test
	public void testParseCsvLine() {
		String[] actual = ParsingUtils.parseCsvLine("9327,Garlic Powder,104,13", 4);
		String[] expected = {"9327", "Garlic Powder", "104", "13"};
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testParseCsvLineWithCommaInsideQuotes() {
		String[] actual = ParsingUtils.parseCsvLine("9327,\"Garlic, Powder\",104,13", 4);
		String[] expected = {"9327", "\"Garlic, Powder\"", "104", "13"};
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testParseCsvLineWithMultipleCommaInsideQuotes() {
		String[] actual = ParsingUtils.parseCsvLine("9327,\"Garlic, Pepper, Powder\",104,13", 4);
		String[] expected = {"9327", "\"Garlic, Pepper, Powder\"", "104", "13"};
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testParseCsvLineWithCommaInsideQuotesForMultipleSegs() {
		String[] actual = ParsingUtils.parseCsvLine("9327,\"Garlic, Pepper, Powder\",\"10,400\",13", 4);
		String[] expected = {"9327", "\"Garlic, Pepper, Powder\"", "\"10,400\"", "13"};
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testParseCsvLineReturnsEmptyWithLessThanExpectedNumOfSegs() {
		String[] actual = ParsingUtils.parseCsvLine("9327,\"Garlic, Pepper, Powder\",104", 4);
		assertNull(actual);
	}
	
	@Test
	public void testParseCsvLineReturnsEmptyWithMoreThanExpectedNumOfSegs() {
		String[] actual = ParsingUtils.parseCsvLine("9327,\"Garlic, Pepper, Powder\",104,13,99", 4);
		assertNull(actual);
	}
}
