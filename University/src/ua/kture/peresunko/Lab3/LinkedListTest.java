package ua.kture.peresunko.Lab3;

import ua.kture.peresunko.Lab2.LaserPrinter;
import ua.kture.peresunko.Lab2.MonoPrinter;

public class LinkedListTest {

	public static void main(String[] args) {
		MyList myLinkedList=new MyLinkedList();
		myLinkedList.add(new LaserPrinter("Laser printer","Canon",100,"excellent",new MonoPrinter()));
		System.out.println(myLinkedList);
	}

}
