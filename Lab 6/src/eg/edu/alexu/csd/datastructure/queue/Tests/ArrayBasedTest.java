package eg.edu.alexu.csd.datastructure.queue.Tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import eg.edu.alexu.csd.datastructure.queue.Classes.ArrQueue;
class ArrayBasedTest {
	@Test
	void emptyConstructor() {
		assertThrows(RuntimeException.class,() -> new ArrQueue() );
	}
	@Test
	void arrayFullTest() {
		ArrQueue test = new ArrQueue(2);
		test.enqueue(1500);
		test.enqueue(15);
		assertThrows(RuntimeException.class,() ->test.enqueue(32));
	}
	@Test
	void emptyArrayTest() {
		ArrQueue test = new ArrQueue(4);
		test.enqueue(15);
		test.dequeue();
		assertThrows(RuntimeException.class,() ->test.dequeue());
	}
	@Test
	void sizeTest() {
		ArrQueue test=new ArrQueue(6);
		test.enqueue(51);
		test.enqueue(51);
		test.enqueue(51);
		assertEquals(test.size(),3);
	}
	@Test
	void enqueueAndDequeueTest() {
		ArrQueue test = new ArrQueue(6);
		test.enqueue(1);
		test.enqueue(4);
		test.enqueue(18);
		assertEquals(test.dequeue(),1);
		assertEquals(test.dequeue(),4);
		test.enqueue(20);
		test.enqueue(25);
		test.enqueue(5);
		assertEquals(test.dequeue(),18);
		assertEquals(test.dequeue(),20);
		assertEquals(test.dequeue(),25);
		test.enqueue(4);
		test.enqueue(8);
		test.enqueue(15);
		test.enqueue(12);
		assertEquals(test.dequeue(),5);
		assertEquals(test.dequeue(),4);
		test.enqueue(1);
		assertEquals(test.dequeue(),8);
		assertEquals(test.size(),3);
	}
}
