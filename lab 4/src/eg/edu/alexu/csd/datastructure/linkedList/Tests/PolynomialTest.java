package eg.edu.alexu.csd.datastructure.linkedList.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eg.edu.alexu.csd.datastructure.linkedList.Classes.PolynomialSolver;

class PolynomialTest {

	@Test
	void test() {
		PolynomialSolver test = new PolynomialSolver();
		int[][]terms = {{-4, 0},{-3, -1}, {5, -2}};
		test.setPolynomial('A', terms);
		String p = test.print('A');
		String r1 = "-4-3X^-1+5X^-2";
		assertEquals(r1, p);
	}

}
