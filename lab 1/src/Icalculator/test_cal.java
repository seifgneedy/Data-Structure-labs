package Icalculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class test_cal {
	@Test
	void test() {
		Calc test=new Calc();
		int test1=test.add(26200,4);
		assertEquals(26204,test1);
	}
	@Test
	void test1() {
		Calc test5=new Calc();
		float test1=test5.divide(0,12);
		assertEquals(0,test1);
		
	}
	@Test
	void test2() {
		Calc test6=new Calc();
	assertThrows(ArithmeticException.class,()-> test6.divide(10,00000));
}
}
