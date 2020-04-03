package eg.edu.alexu.csd.datastructure.stack.Tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.InputMismatchException;

import org.junit.jupiter.api.Test;
import eg.edu.alexu.csd.datastructure.stack.Classes.Evaluator;
class EvaluatorTest {
	Evaluator evaluate = new Evaluator();
	@Test
	void labExamplesTestInConversion() {
		String[] result= {"2 3 4 * +","a b * 5 +","1 2 + 7 *","a b * c /","a b c - d + / e a - * c *","a b / c - d e * + a c * -"};
		String[] input = {"2 + 3 * 4","a * b + 5","(1 + 2) * 7","a * b / c","(a / (b - c + d)) * (e - a) * c","a / b - c + d * e - a * c"};
		for(int i=0;i<input.length;i++){
			assertEquals(evaluate.infixToPostfix(input[i]),result[i]);
		}
	}
	@Test
	void otherExamplesTest() {
		String[] result= {"1 2 3 + * 3 - 4 + 5 *","3 2 + 3 4 * +","3 1 - 2 / 3 * 5 +","3 1 - 2 / 3 5 + *","1 2 3 + * 3 - 4 + 5 *"};
		String[] input = {"((((1*(2+3))-3)+4)*5)","3+2+3*4","((3-1)/2)*3+5","((3-1)/2)*(3+5)","((((1*(2+3))-3)+4)*5)"};
		for(int i=0;i<input.length;i++){
			assertEquals(evaluate.infixToPostfix(input[i]),result[i]);
		}
	}
	@Test 
	void dummyTest() {
		String[] input= {"(-1+2)*7","(-5+-6)-7+-7*12-15*-4","-1+-2"};
		String[] result= {"0 1 - 2 + 7 *","0 5 - 0 6 - + 7 - 0 7 - 12 * + 15 0 4 - * -","0 1 - 0 2 - +"};
		for(int i=0;i<input.length;i++){
			assertEquals(evaluate.infixToPostfix(input[i]),result[i]);
		}
	}
	@Test
	void invalidInputTest() {
		String[] input= {"-565++6","-5+(53-10))","(321+12)(5132)","4564+151-*152","*112-45","()*65-152","562**2"};
			assertThrows(InputMismatchException.class,()-> evaluate.infixToPostfix(input[0]));
			assertThrows(InputMismatchException.class,()-> evaluate.infixToPostfix(input[1]));
			assertThrows(InputMismatchException.class,()-> evaluate.infixToPostfix(input[2]));
			assertThrows(InputMismatchException.class,()-> evaluate.infixToPostfix(input[3]));
			assertThrows(InputMismatchException.class,()-> evaluate.infixToPostfix(input[4]));
			assertThrows(InputMismatchException.class,()-> evaluate.infixToPostfix(input[5]));
			assertThrows(InputMismatchException.class,()-> evaluate.infixToPostfix(input[6]));
	}
	@Test
	void invalidPostfixEvaluate() {
		String[] input= {"5 + 60","5 6 - -","15 5 + 6 0 + 0 -","4 5 6 + 5 4 - 5 *","4 58 g 6 / * % $","4 7 8 - - - -","4 5 + 5 5 5 5 5 8"};
		assertThrows(InputMismatchException.class,()-> evaluate.evaluate(input[0]));
		assertThrows(InputMismatchException.class,()-> evaluate.evaluate(input[1]));
		assertThrows(InputMismatchException.class,()-> evaluate.evaluate(input[2]));
		assertThrows(InputMismatchException.class,()-> evaluate.evaluate(input[3]));
		assertThrows(InputMismatchException.class,()-> evaluate.evaluate(input[4]));
		assertThrows(InputMismatchException.class,()-> evaluate.evaluate(input[5]));
		assertThrows(InputMismatchException.class,()-> evaluate.evaluate(input[6]));
	}
	@Test 
	void evaluateTest() {
		String [] input= {"0 1 - 2 + 7 *","1 2 3 + * 3 - 4 + 5 *","45 15 + 40 - 2 *","3 2 / 3 2 / +"};
		int [] result= {7,30,40,3};
		for(int i=0;i<input.length;i++){
			assertEquals(evaluate.evaluate(input[i]),result[i]);
		}
	}

}
