package ua.kture.peresunko.Lab3;

import java.util.Comparator;


import ua.kture.peresunko.Lab2.Printer;

public class SortedByCompany implements Comparator<Printer>{

	public int compare(Printer p1, Printer p2) {
        return p1.getNameOfCompany().compareTo(p2.getNameOfCompany());
	}
}
