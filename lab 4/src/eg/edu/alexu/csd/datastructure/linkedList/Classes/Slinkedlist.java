package eg.edu.alexu.csd.datastructure.linkedList.Classes;
import eg.edu.alexu.csd.datastructure.linkedList.Interfaces.ILinkedList;
public class Slinkedlist implements ILinkedList{
   private static class Node {
		private Object element;
		private Node next;
		public Node(Object value,Node n) {
			setElement(value);
			setNext(n);
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node n) {
			next = n;
		}
		public Object getElement() {
			return element;
		}
		public void setElement(Object value) {
			element = value;
		}
		
    }
    private int size;
    Node head,tail;
    public Slinkedlist() {
    	head=null;
    	tail=null;
    	size=0;
    }
	@Override
	//index starts from zero as usual
	public void add(int index, Object element) {
		Node v= new Node(element,null);
		if(index>size||index<0)
			throw new IndexOutOfBoundsException();	
		else if (index==0) {	
			v.setNext(head);
			head=v;
			if (size==0)
				tail=v;
			size++;
		}
		else if (index==size)
			add(element); 	
		else {
			Node p = head;
			for(int i=0;i<index-1;i++)
				p=p.getNext();
			Node q=p;
			p=p.getNext();
			q.setNext(v);
			v.setNext(p);
			size++;
		}
	}

	@Override
	public void add(Object element) {
		Node v= new Node(element,null);
		if(tail!=null) {
		tail.setNext(v);
		tail=v;
		}
		else {
			head=v;
			tail=v;
		}
		size++;
	}
	@Override
	public Object get(int index) {
		if(index>=size||index<0) {
			throw new IndexOutOfBoundsException();
			}
		else {
			Node p=head;
			for(int i=0;i<index;i++)
				p=p.getNext();
			return p.getElement();
		}
	}
	@Override
	public void set(int index, Object element) {
		if(index>=size||index<0)
			throw new IndexOutOfBoundsException();
		else {
			Node p=head;
			for(int i=0;i<index;i++)
				p=p.getNext();
			p.setElement(element);
		}
	}
	@Override
	public void clear() {
		while(head!=null) {
		Node v=head;
		head=head.getNext();
		v.setNext(null);
		}
		tail=null;
		size=0;
	}

	@Override
	public boolean isEmpty() {
		return (size==0);
	}

	@Override
	public void remove(int index) {
		if(index>=size||index<0)
			throw new IndexOutOfBoundsException();
		else {
			if (index==0) {
				Node v=head;
				head=head.getNext();
				if(size==1)
					tail=null;
				v.setNext(null);
			}
			else if(index==size-1) {
				Node p=head;
				for(int i=0;i<index-1;i++) 
					p=p.getNext();
				tail=p;
				tail.setNext(null);
			}
			else {
				Node p=head;
				for(int i=0;i<index-1;i++) 
					p=p.getNext();
				Node s=p;
				p=p.getNext();
				s.setNext(p.getNext());
			}
			size--;
		}
		
	}

	@Override
	public ILinkedList sublist(int fromIndex, int toIndex) {
		Slinkedlist list = new Slinkedlist();
		if (fromIndex>=size||toIndex>=size||fromIndex<0||toIndex<0) {
			throw new IndexOutOfBoundsException();			
		}
		else {	
		Node p=head;
		for(int i=0;i<fromIndex;i++)
			p=p.getNext();
		for(int i=fromIndex;i<=toIndex;i++) {
			list.add(p.getElement());
			p=p.getNext();
		}
		return list;
		}
	}

	@Override
	public boolean contains(Object o) {
		Node p=head;
		while(p!=null) {
		if (p.getElement().equals(o))
			return true;
		p=p.getNext();
		}
		return false;
	}
}
