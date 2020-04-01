package eg.edu.alexu.csd.datastructure.stack.Classes;
import eg.edu.alexu.csd.datastructure.stack.Interfaces.IStack;
public class Stack implements IStack{
	private static class Node{
	private Object element;
	private Node next;
	public Node(Object element2, Node next) {
		element=element2;
		this.next=next;
	}
	public Object getElement() {
		return element;
	}
	public Node getNext() {
		return next;
	}
}
	private Node top;
	private int size;
	public Stack() {
		top=null;
		size=0;
	}
	@Override
	public Object pop() {
		if (top==null)
			throw new RuntimeException("The stack is empty");
		Object temp=top.getElement();
		top=top.getNext();
		size--;
		return temp;
	}

	@Override
	public Object peek() {
		if(top==null)
			throw new RuntimeException("The stack is empty");
		return top.getElement();
	}

	@Override
	public void push(Object element) {
		Node temp=new Node(element,top);
		top=temp;
		size++;
		
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
