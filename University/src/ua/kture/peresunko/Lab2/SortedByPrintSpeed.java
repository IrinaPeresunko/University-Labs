package ua.kture.peresunko.Lab2;

import java.util.Comparator;

public class SortedByPrintSpeed implements Comparator<Printer>{

	public int compare(Printer p1, Printer p2) {
		return p1.getPrintSpeed()>p2.getPrintSpeed() ? -11 : p1.getPrintSpeed()==p2.getPrintSpeed() ? 0 : 1;
	}
}
