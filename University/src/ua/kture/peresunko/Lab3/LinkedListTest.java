package ua.kture.peresunko.Lab3;

import ua.kture.peresunko.Lab2.ColourPrinter;
import ua.kture.peresunko.Lab2.InkJetPrinter;
import ua.kture.peresunko.Lab2.LaserPrinter;
import ua.kture.peresunko.Lab2.MonoPrinter;
import ua.kture.peresunko.Lab2.PhotoPrinter;
import ua.kture.peresunko.Lab2.Printer;

public class LinkedListTest {

	public static void main(String[] args) {
		MyList<Printer> myLinkedList=new MyLinkedList();
		
		Printer[] printers = new Printer[5];
		
		printers[0]=new InkJetPrinter("Ink jet printer","Samsung",60,"average",new MonoPrinter());
		printers[1]=new InkJetPrinter("Ink jet printer","HP",70,"good",new ColourPrinter());
		printers[2]=new LaserPrinter("Laser printer","Canon",100,"excellent",new MonoPrinter());
		printers[3]=new LaserPrinter("Laser printer","Canon",85,"good",new ColourPrinter());
		printers[4]=new PhotoPrinter("Ink jet photoprinter","Epson",40,"good",new ColourPrinter());
		
		for(int i=0;i<printers.length;i++){
			myLinkedList.addAtTheTop(printers[i]);
		}
		
		//myLinkedList.add(printers[4]);
		
		System.out.println(myLinkedList);
		
		boolean test = myLinkedList.removeAtTheTop();
		System.out.println(myLinkedList);
		System.out.println(test);
		
		boolean test1 = myLinkedList.contains(printers[4]);
		System.out.println(test1);
//		
//		test = myLinkedList.removeAtTheEnd();
//		System.out.println(myLinkedList);
//		System.out.println(test);
//		
//		myLinkedList.remove(2);
//		System.out.println(myLinkedList);
	}

}
