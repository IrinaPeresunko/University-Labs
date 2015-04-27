package ua.kture.peresunko.Lab3;

import java.util.Iterator;
//import java.util.NoSuchElementException;
import ua.kture.peresunko.Lab2.Printer;

public class MyLinkedList implements MyList<Printer>,Iterable<Printer>{
	private Node first;
	private Node last;
	private int size;
	//private int currentPosition=-1;
	
	private class Node{
		private Printer printerData;
		private Node prev;
		private Node next;
		
		public Node(Printer printer){
			this.next=null;
			this.printerData=printer;
		}
//		public Node getPrev(){
//			return prev;
//		}
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
	public boolean addAtTheTop(Printer printer){
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
	public boolean addAtTheEnd(Printer printer){
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
		if(index<1 || index> size){
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
			node = new Node(printerData);
			current.prev.next=node;
			node.prev=current.prev;
			current.prev=node;
			node.next=current;
			size++;			
		}	
	}
	public boolean removeAtTheTop() {
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
	public boolean removeAtTheEnd() {
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
			//return false;
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
			//return true;
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
		return new ListIterator();
	}
	
	public class ListIterator implements Iterator<Printer> {
		//private boolean wasRemoved;
		private Node current=first;
		public ListIterator(){
			//currentPosition=-1;
		}
		public boolean hasNext() {
			return current!=null;
		}
		public Printer next() {
			if(!hasNext()) return null;
			Printer printer = current.printerData;
			current = current.next;
			return printer;
		}
		public void remove() {	
			//new MyLinkedList().remove(int in);
		}
	}
}
