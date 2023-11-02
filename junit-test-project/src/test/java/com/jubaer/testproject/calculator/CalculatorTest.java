package com.jubaer.testproject.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
	
	Calculator calculator;
	
//	@BeforeEach
//	public void setUp() {
//		calculator = new Calculator();
//	}
	
	@Test
	public void testMultiply() {
		calculator = new Calculator();
		assertEquals(20, calculator.multiply(4, 5));
//		assertEquals(25, calculator.multiply(5, 5));
	}
	
//	@Test
//	public void testMultiplyDifferent() {
//		calculator = new Calculator();
//		assertEquals(25, calculator.multiply(5, 5));
//	}
	
	@Test
	public void testDivide() {
		calculator = new Calculator();
		assertEquals(1, calculator.divide(5, 0));
	}

}
