package ua.kture.peresunko.Lab2;

public class MonoPrinter implements PrinterImpl{

	public void printText(String text) {
		System.out.println(text);		
	}
	public String toString(){
		return "mono printer";
	}
}
