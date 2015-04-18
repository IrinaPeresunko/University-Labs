package ua.kture.peresunko.Lab2;

import java.util.Comparator;

public class SortedByNameAndPrintSpeed implements Comparator<Printer>{
	
	public int compare(Printer p1, Printer p2) {
		if(p1.getPrintSpeed()==p2.getPrintSpeed()){
			return p1.getNameOfCompany().compareTo(p2.getNameOfCompany());
		}
		return (int) (p2.getPrintSpeed()-p1.getPrintSpeed()); 
	}

}
