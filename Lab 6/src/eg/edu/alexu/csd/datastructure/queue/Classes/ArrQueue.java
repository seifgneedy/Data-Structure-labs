package eg.edu.alexu.csd.datastructure.queue.Classes;
import eg.edu.alexu.csd.datastructure.queue.Interfaces.IArrayBased;
import eg.edu.alexu.csd.datastructure.queue.Interfaces.IQueue;
public class ArrQueue implements IQueue,IArrayBased {
	// the length of the array
	private int length; 
	//the number of exact elements in the array 
	private int size;
	private Object[] queue;
	//insert and dequeue pointers
	private int insp=0;
	private int outp=0;
	public ArrQueue() {
		throw new RuntimeException("no length entered!");
	}
	public ArrQueue(int length) {
		this.length=length;
		queue=new Object[length];
	}
	@Override
	public void enqueue(Object item) {
		if(size==length)
			throw new RuntimeException("The array is already full");
		queue[insp]=item;
		insp++;
		if(insp==length)
			insp=0;
		size++;
	}
	@Override
	public Object dequeue() {
		if(isEmpty())
			throw new RuntimeException("The array is empty");
		Object element=queue[outp];
		queue[outp]=null;
		outp++;
		if(outp==length)
			outp=0;
		size--;
		return element;
	}
	@Override
	public boolean isEmpty() {
	return (size==0);
	}
	@Override
	public int size() {
		return size;
	}

}
