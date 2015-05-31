package ua.kture.peresunko.Lab3;

import java.util.Iterator;

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
			myLinkedList.add(printers[i],i);
		}
		
		System.out.print(myLinkedList);
		
//		Parameters param = new Parameters("Laser printer",null,100,null);
//		param = new Parameters(null,null,0,"good");
//		Iterator<Printer> iterator = myLinkedList.iterator();
//		while(iterator.hasNext()){
//			Printer printer = iterator.next();
//			boolean flag = ((MyLinkedList) myLinkedList).search(printer,param);
//			if(flag==true){
//				System.out.println("_______________________"+"\n"+printer.toString());
//			}
//		}
		
		System.out.println("___________________________________________");
		myLinkedList.sort(new SortedByCompany());
		System.out.print("myLinkedList.sort(new SortedByCompany())"+"\n"+myLinkedList);
		
		myLinkedList.add(printers[1], 2);
		System.out.println("___________________________________________");
		System.out.print("Add printers[1] for second position:"+"\n"+myLinkedList);
		
		myLinkedList.remove(myLinkedList.size());
		System.out.println("___________________________________________");
		System.out.print("Remove at the end:"+"\n"+myLinkedList);
		
		boolean test = myLinkedList.contains(printers[4]);
		System.out.println("___________________________________________");
		System.out.println("Does linked list conatin printers[4]:"+test);
		
		myLinkedList.remove(4);
		System.out.println("___________________________________________");
		System.out.print("Remove fourth element in the linked list:"+"\n"+myLinkedList);
		
		Printer[] printers1 = (Printer[]) myLinkedList.toArray(); 
		System.out.println("___________________________________________");
		System.out.println("linked list to Array:");
		for(Printer i:printers1){
			System.out.println(i);
		}
		
		Iterator<Printer> iterator = myLinkedList.iterator();
		System.out.println("___________________________________________");
		System.out.println("Create iterator and print elements, using methods HasNext() and next():");
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
		System.out.println("___________________________________________");
		iterator.remove();
		System.out.print("Remove element using iterator:"+"\n"+myLinkedList);
		
//		System.out.println("___________________________________________");
//		iterator.remove();
//		System.out.print("Remove element using iterator:"+"\n"+myLinkedList);
		
		System.out.println("___________________________________________");
		System.out.println("Size of linked list:"+myLinkedList.size());
		
		myLinkedList.clear();
		System.out.print("After clearing:");
		System.out.print(myLinkedList+"Size of linked list:"+myLinkedList.size());
		
	}
}
