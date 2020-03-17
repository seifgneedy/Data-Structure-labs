package eg.edu.alexu.csd.datastructure.linkedList.Classes;
import eg.edu.alexu.csd.datastructure.linkedList.Interfaces.ILinkedList;
public class Dlinkedlist implements ILinkedList {
	private static class DNode{
		private DNode next;
		private DNode prev;
		private Object element;
		public DNode(Object element,DNode next,DNode prev) {
			this.element=element;
			this.next=next;
			this.prev=prev;
		}
		public DNode getNext() {
			return next;
		}
		public void setNext(DNode next) {
			this.next = next;
		}
		public DNode getPrev() {
			return prev;
		}
		public void setPrev(DNode prev) {
			this.prev = prev;
		}
		public Object getElement() {
			return element;
		}
		public void setElement(Object element) {
			this.element = element;
		}
	}
	private int size;
	DNode header,trailer;
	public Dlinkedlist() {
		header=new DNode(null,trailer,null);
		trailer=new DNode(null,null,header);
		size=0;
	}
	@Override
	public void add(int index, Object element) {
		if(index>size||index<0) {
			throw new IndexOutOfBoundsException();
			}
		else if (index==0) {
				DNode p=new DNode(element,header.getNext(),header);		
				if(size==0)
					trailer.setPrev(p);
				else {
				DNode q=header.getNext();
				q.setPrev(p);
				}
				header.setNext(p);
				size++;
			}
		else if(index==size)
			add(element);
		else {
			DNode prev=header;
			for(int i=0;i<index;i++)
				prev=prev.getNext();
			DNode next=prev.getNext();
			DNode current=new DNode(element,next,prev);
			prev.setNext(current);
			next.setPrev(current);
			size++;
		}
	}

	@Override
	public void add(Object element) {
		DNode prev=trailer.getPrev();
		DNode current=new DNode(element,trailer,prev);
		prev.setNext(current);
		trailer.setPrev(current);
		size++;
	}

	@Override
	public Object get(int index) {
		if(index>=size||index<0) {
			throw new IndexOutOfBoundsException();
			}
		else {
			if (index>size/2) {
				DNode p=trailer.getPrev();
				for(int i=size;i>index+1;i--) 
					p=p.getPrev();
				return p.getElement();
			}
			else {
				DNode p=header.getNext();
				for(int i=0;i<=index-1;i++)
					p=p.getNext();
				return p.getElement();
			}
		}		
	}

	@Override
	public void set(int index, Object element) {
		if(index>=size||index<0) {
			throw new IndexOutOfBoundsException();
			}
		else {
			if (index>size/2) {
				DNode p=trailer;
				for(int i=size;i>index;i--) 
					p=p.getPrev();
				p.setElement(element);
			}
			else {
				DNode p=header;
				for(int i=0;i<=index;i++)
					p=p.getNext();
				p.setElement(element);
			}
		}
	}
	@Override
	public void clear() {
		while(header.getNext()!=trailer)
			remove(0);
		trailer.setPrev(header);
		size=0;
	}

	@Override
	public boolean isEmpty() {
		return (size==0);
	}

	@Override
	public void remove(int index) {
		if(index>=size||index<0) {
			throw new IndexOutOfBoundsException();
			}
		else {
			if (index==0) {
				DNode p=header.getNext();
				DNode q=p.getNext();
				header.setNext(q);
				q.setPrev(header);
				if (size==1) trailer=q;
				p.setNext(null);
				p.setPrev(null);
			}
			else if(index==size-1) {
				DNode p=trailer.getPrev();
				DNode q=p.getPrev();
				q.setNext(trailer);
				trailer.setPrev(q);
				p.setNext(null);
				p.setPrev(null);
			}
			else {
				DNode current=header.getNext();
				for(int i=0;i<index;i++)
					current=current.getNext();
				DNode next=current.getNext();
				DNode prev=current.getPrev();
				current.setNext(null);
				current.setPrev(null);
				prev.setNext(next);
				next.setPrev(prev);
				}
			size--;
			}
			
		}
	

	@Override
	public ILinkedList sublist(int fromIndex, int toIndex) {
		Dlinkedlist list =new Dlinkedlist();
		if (fromIndex>=size||toIndex>=size||fromIndex<0||toIndex<0) {
			throw new IndexOutOfBoundsException();			
		}
		else {
			DNode p=header.getNext();
			for(int i=0;i<fromIndex;i++)
				p=p.getNext();
			for(int i=fromIndex;i<=toIndex;i++) {
				list.add(p.getElement());
				p=p.getNext();
			}
			return list;
		}
	}
	public int size() {
		return size;
	}

	@Override
	public boolean contains(Object o) {
		DNode p=header.getNext();
		while(p!=trailer) {
		if (p.getElement().equals(o))
			return true;
		p=p.getNext();
		}
		return false;
	}
}
