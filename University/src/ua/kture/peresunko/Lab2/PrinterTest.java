package ua.kture.peresunko.Lab2;

import java.util.Arrays;

public class PrinterTest {
	public static void main(String[] args){
		
		Printer[] printers = new Printer[4];
		
		printers[0]=new InkJetPrinter("Ink jet printer","Samsung",60,"average",new MonoPrinter());
		printers[1]=new InkJetPrinter("Ink jet printer","HP",70,"good",new ColourPrinter());
		printers[2]=new LaserPrinter("Laser printer","Canon",100,"excellent",new MonoPrinter());
		printers[3]=new LaserPrinter("Laser printer","Canon",85,"good",new ColourPrinter());
		
		for(int i=0;i<printers.length;i++){
			System.out.println(printers[i]);
		}
		
		for(int i=0;i<printers.length;i++){
			printers[i].print(printers[i].getName()+"("+printers[i].getColourOfPrinting()+")");
		}
		
		Arrays.sort(printers);
		System.out.println("\n"+"Sorted by print speed(ascending):(Comparable)");
		for(int i=0;i<printers.length;i++){
			System.out.println(printers[i]);
		}
		
		Arrays.sort(printers, new SortedByName());
		System.out.println("\n"+"Sorted by name(descending):");
		for(int i=0;i<printers.length;i++){
			System.out.println(printers[i]);
		}
		
		Arrays.sort(printers, new SortedByCompany());
		System.out.println("\n"+"Sorted by company(ascending):");
		for(int i=0;i<printers.length;i++){
			System.out.println(printers[i]);
		}
		
		Arrays.sort(printers,new SortedByPrintSpeed());
		System.out.println("\n"+"Sorted by print speed(descending):");
		for(int i=0;i<printers.length;i++){
			System.out.println(printers[i]);
		}
	}	
}
