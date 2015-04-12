package ua.kture.peresunko.Lab2;

import java.util.Comparator;

public class SortedByCompany implements Comparator<Printer>{
	
	public int compare(Printer p1, Printer p2) {
        return p1.getNameOfCompany().compareTo(p2.getNameOfCompany());
	}
	
}
