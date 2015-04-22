package ua.kture.peresunko.Lab3;

import ua.kture.peresunko.Lab2.Printer;

//!!! ABSTRACT!!!
public class MyLinkedList implements MyList{
	private Node first;
	private Node current;
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
		public Node getPrev(){
			return prev;
		}
		public Node getNext(){
			return next;
		}
		public Printer getItem(){
			return this.printerData;
		}
	}
	
	public MyLinkedList(){
		this.first=this.last=this.current=null;
		this.size=0;
	}
	public int size(){
		return this.size;
	}
	public void add(Printer printerData){
		if(size==0){
			Node node=new Node(printerData);
			node.prev=node.next=null;
			first=last=node;
			size++;
		}
		else{
			Node node=new Node(printerData);
			last.next=node;
			node.prev=last;
			node.next=null;
			last=node;
			size++;
		}
	}
	public String toString(){
		return first.getItem().toString();
	}

}
