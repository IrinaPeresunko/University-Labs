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
	     first = last = null;
	     size--;
	     return true; 
	    }
	    first.printerData = null;
	    first = first.next;
	    //first.prev.next = null;
	    first.prev = null;
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
	     first = last = null;
	     size--;
	     return true; 
	    }
	    last.printerData = null;
	    last = last.prev;
	    last.next.prev = null;
	    last.next = null;
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
	     current.printerData = null;
	     current.prev.next = current.next;
	     current.next.prev = current.prev;
	     current.prev = current.next = null;
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
	      private boolean wasRemoved = false;
	      Node current = null;
	      int counter = 0;
	      public boolean hasNext() {
	     //  if(current.next != null) return true;
	    	  if(counter < size) return true;
	       return false;
	      }
	      public Printer next() {
	       if(hasNext() && current == null){
	        current = first;
	        wasRemoved = false;
	        counter++;
	        return current.printerData;
	       } 
	       if(hasNext()){
	        current = current.next;
	        wasRemoved = false;
	        counter++;
	        return current.printerData;
	       }
	       return null;
	     }
	      public void remove() throws IllegalStateException{ 
	       if(wasRemoved){
	        System.out.println("You can't remove element twice in a row");
	           throw new IllegalStateException();
	       }
	       if(current == first) {MyLinkedList.this.removeAtTheTop();wasRemoved = true;counter--;return;}
	       if(current == last) {MyLinkedList.this.removeAtTheEnd();wasRemoved = true;counter--;return;}
	        current.printerData = null;
	    current.prev.next = current.next;
	    current.next.prev = current.prev;
	    //current.prev = current.next = null;
	    counter--;
	    size--;
	        wasRemoved = true;
	       }
	      }
		public int compareByName(Printer printer,String name){
			if(name!=null){
				if(printer.getName().equals(name)){
					return 0;
				}
				return 1;
			}
			return -1;
		}
		public int compareByNameOfCompany(Printer printer,String nameOfCompany){
			if(nameOfCompany!=null){
				if(printer.getNameOfCompany().equals(nameOfCompany)){
					return 0;
				}
			return 1;
			}
			return -1;
		}
		public int compareByPrintSpeed(Printer printer,double speed){
			if(speed != 0){
				if(printer.getPrintSpeed() == speed){
					return 0;
				}	
			return 1;
			}
			return -1;
		}
		public int compareByPrintQuality(Printer printer,String printQuality){
			if(printQuality!=null){
				if(printer.getPrintQuality().equals(printQuality)){
					return 0;
				}
			return 1;
			}
			return -1;
		}
		public boolean search(Printer printer,Parameters param){
			if(compareByName(printer,param.getName())!=1){
				if(compareByNameOfCompany(printer,param.getNameOfCompany())!=1){
					if(compareByPrintSpeed(printer,param.getPrintSpeed())!=1){
						if(compareByPrintQuality(printer,param.getPrintQuality())!=1){
							return true;
						}
					return false;
					}
					return false;
				}
				return false;
			}
			return false;
		}
	public Printer getElementByIndex(int index){
		  Node temp = first;
		  for(int i = 0; i < index; i++) temp = temp.next;
		  return temp.printerData;
	}
	public Printer edit(String name,String company,double speed,String quality,int index){
		Printer printer = getElementByIndex(index);
		printer.setName(name);
		printer.setNameOfCompany(company);
		printer.setPrintSpeed(speed);
		printer.setPrintQuality(quality);
		return printer;
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
