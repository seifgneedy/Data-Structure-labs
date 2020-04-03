package eg.edu.alexu.csd.datastructure.stack.Tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import eg.edu.alexu.csd.datastructure.stack.Classes.Stack;
class StackTest {
	@Test
	void testpop() {
		Stack test = new Stack();
		int[] arr= {150,123,2646,9898};
		test.push(150);
		test.push(123);
		test.push(2646);
		test.push(9898);
		for(int i=0;i<arr.length;i++)
			assertEquals(test.pop(),arr[arr.length-1-i]);
	}
	@Test
	void testisEmpty() {
		Stack test = new Stack();
		test.push(562);
		test.pop();
		assertTrue(test.isEmpty());
	}
	@Test
	void testsize() {
		Stack test = new Stack();
		int[] arr= {150,123,2646,9898};
		test.push(150);
		test.push(123);
		test.pop();
		test.push(2646);
		test.push(9898);
		assertEquals(test.size(),3);
	}
	@Test
	void testpeek() {
		Stack test = new Stack();
		test.push(654);
		test.push(2138);
		test.push(9632);
		test.push(1478);
		test.pop();
		test.pop();
		assertEquals(test.peek(),2138);
	}
	@Test
	void testthrow() {
		Stack test = new Stack();
		test.push(41651);
		test.pop();
		test.push(212);
		test.push(963);
		test.pop();
		test.pop();
		assertThrows(RuntimeException.class,()-> test.pop());
	}
}
