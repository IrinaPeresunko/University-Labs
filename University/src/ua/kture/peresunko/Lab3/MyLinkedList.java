package ua.kture.peresunko.Lab3;

import java.util.Comparator;
import java.util.Iterator;

import ua.kture.peresunko.Lab2.Printer;

public class MyLinkedList implements MyList<Printer>,Iterable<Printer>{
	private Node first;
	private Node last;
	private int size;
	
	private class Node{
		private Printer printerData;
		private Node prev;
		private Node next;
		
		public Node(Printer printer){
			this.next=null;
			this.printerData=printer;
		}
		public Node getNext(){
			return next;
		}
		public Printer getItem(){
			return this.printerData;
		}
	}
	
	public MyLinkedList(){
		this.first=this.last=null;
		this.size=0;
	}
	public int size(){
		return this.size;
	}
	private boolean addAtTheTop(Printer printer){
		Node node=new Node(printer);
		
		if(size==0){
			node.prev=node.next=null;
			first=last=node;
			size++;
			return true;
		}
		node.next = first;
		first = node;
		node.next.prev = first;
		size++;
		return true;
	}
	private boolean addAtTheEnd(Printer printer){
		Node node=new Node(printer);
		
		if(size==0){
			node.prev=node.next=null;
			first=last=node;
			size++;
			return true;
		}
		last.next=node;
		node.prev = last;
		last = node;
		size++;
		return true;
	}
	public void add(Printer printerData,int index){
		Node node=new Node(printerData);
		if(index<-1 || index>this.size){
			System.out.println("index<1 || index> size");
		}
		else if(index==1){
			addAtTheTop(printerData);
		}
		else if(index==size){
			addAtTheEnd(printerData);
		}
		else{
			Node current=first;
			int i=1;
			while(i<index && current !=null){
				current=current.getNext();
				i++;
			}
			//node = new Node(printerData);
			current.prev.next=node;
			node.prev=current.prev;
			current.prev=node;
			node.next=current;
			size++;			
		}	
	}
	private boolean removeAtTheTop() {
		if(size==0){
			System.out.println("No elements");
			return false;
		}
		if(size==1){
			first.printerData=last.printerData=null;
			size--;
			return true;	
		}
		first.next.prev = null;
		first = first.next;
		size--;
		return true;
	}
	private boolean removeAtTheEnd() {
		if(size==0){
			System.out.println("No elements");
			return false;
		}
		if(size==1){
			first.printerData=last.printerData=null;
			size--;
			return true;	
		}
		last.prev.next = null;
		last = last.prev;
		size--;
		return true;	
	}
	public void remove(int index){
		if(index<1 || index>size){
			System.out.println("index<1 || index>size");
		}
		else if(index==1){
			removeAtTheTop();
		}
		else if(index==size){
			removeAtTheEnd();
		}
		else{
			Node current = first;
			int i=1;
			while(current!=null && i<index){
				if(current.getNext()==null){
					System.out.println("current.getNext()==null");
					break;
				}
				else{
					current=current.getNext();					
					i++;
				}
			}
			current.prev.next = current.next;
			current.next.prev = current.prev;
			size--;
		}
	}
	public boolean contains(Printer printer){
		Node current = first;
		while(current!=null){
			if(current.printerData.equals(printer)){
				return true;
			}
			else{
				current = current.getNext();
			}
		}
		return false;
	}
	public void clear(){
		Node current = first;
		while(current!=null && current.getNext()!=null){
			current.printerData=null;
			current.next.prev = null;
			size--;
			
			current = current.next;	
		}
		current=first=last=null;
		size--;
	}
	public String toString(){
		if(first==null){
			System.out.println("Linked List is empty");
		}
		String output="";
		Node current=first;
		while(current!=null){
			output+=current.getItem().toString()+"\n";
			current=current.getNext();		
		}
		return output;
	}
	public Object[] toArray(){
		Printer[] printers = new Printer[size];
		Node current = first;
		int i=0;
		while(current!=null && i<size){
			printers[i] = current.printerData;
			i++;
			current = current.getNext();
		}
		return printers;
	}
	
	public Iterator<Printer> iterator() {
		return new MyLinkedListIterator();
	}
	
	private class MyLinkedListIterator implements Iterator<Printer> {
		private int currentPosition;
		private boolean wasRemoved = false;
		
		public MyLinkedListIterator(){
			currentPosition=-1;
		}
		public boolean hasNext() {
			if(size==0 || currentPosition>=size-1){
				return false;
			}
			else{
				return true;
			}
		}
		public Printer next() {
			if(!hasNext()) return null;
			
			Node current=first;
			currentPosition++;
			wasRemoved = false;
			for(int i=0;i<currentPosition;i++){
				current = current.next;
			}
			return current.printerData;
		}
		public void remove() throws IllegalStateException{	
			if(wasRemoved!=true && currentPosition>0){
				MyLinkedList.this.remove(currentPosition+1);
				//currentPosition--;
				wasRemoved = true;
			}
			else{
				System.out.println("You can't remove element twice in a row");
				throw new IllegalStateException();
			}
		}
	}

	public void sort(Comparator<Printer> comparator) {
		Node temp1 = first;
		Node temp2 = first.next;
		
		if(comparator==null){
			for(int i=0;i<size;i++){
				temp1 = first;
				temp2 = first.next;
				while(temp1.next!=null){
					if(temp1.printerData.compareTo(temp2.printerData)>0){
						Printer printerData = temp1.printerData;
						temp1.printerData = temp2.printerData;
						temp2.printerData = printerData;
						
						temp1 = temp1.next;
						temp2 = temp2.next;
					}
					else{
						temp1 = temp1.next;
						temp2 = temp2.next;
					}
				}
			}
		}
		else{
			for(int i=0;i<size;i++){
				temp1 = first;
				temp2 = first.next;
				while(temp1.next!=null){
					if(comparator.compare(temp1.printerData,temp2.printerData)>0){
						Printer printerData = temp1.printerData;
						temp1.printerData = temp2.printerData;
						temp2.printerData = printerData;
					
						temp1 = temp1.next;
						temp2 = temp2.next;
					}
					else{
						temp1 = temp1.next;
						temp2 = temp2.next;
					}
				}
			}	
		}
	}
}
