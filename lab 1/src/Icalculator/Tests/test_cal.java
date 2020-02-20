package Icalculator.Tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Icalculator.Classes.Calc;
class test_cal {
	Calc test=new Calc();
	@Test
	void testpositive() {
		int test1=test.add(26200,4);
		assertEquals(26204,test1);
	}
	@Test
	void testnegative() {
		int test1=test.add(-55,4);
		assertEquals(-51,test1);
	}
	@Test
	void testpositivefloat() {
		float test1=test.divide(2,4);
		assertEquals(0.5,test1);
		
	}
	@Test
	void testnegativefloat() {
		float test1=test.divide(-5,4);
		assertEquals(-1.25,test1);
		
	}
	@Test
	void test2() {
	assertThrows(ArithmeticException.class,()-> test.divide(2,0));
}
}

