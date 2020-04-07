package eg.edu.alexu.csd.datastructure.queue.Classes;
import eg.edu.alexu.csd.datastructure.queue.Interfaces.ILinkedBased;
import eg.edu.alexu.csd.datastructure.queue.Interfaces.IQueue;
public class LinkedQueue implements IQueue,ILinkedBased {
	private static class Node{
		private Object element;
		private Node next;
		public Node(Object element,Node next) {
			this.setElement(element);
			this.setNext(next);
		}
		public Object getElement() {
			return element;
		}
		public void setElement(Object element) {
			this.element = element;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		
	}
	private int size;
	Node head,tail;
	public LinkedQueue() {
		size=0;
		head=null;
		tail=null;
	}
	@Override
	public void enqueue(Object item) {
		Node current=new Node(item,null);
		if(isEmpty())
			head=current;
		else
			tail.setNext(current);
		tail=current;
		size++;
	}

	@Override
	public Object dequeue() {
		if(isEmpty())
			throw new RuntimeException("The Queue is empty");
		Object element=head.getElement();
		head=head.getNext();
		if(head==null)
			tail=head;
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
