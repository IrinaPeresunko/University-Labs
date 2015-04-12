package ua.kture.peresunko.Lab2;

import java.util.Comparator;

public class SortedByName implements Comparator<Printer>{
	
	public int compare(Printer p1, Printer p2) {       
        return p2.getName().compareTo(p1.getName());
	}
}
