package ua.kture.peresunko.Lab2;

public class ColourPrinter implements PrinterImpl {
	
	public void printText(String text) {
		System.err.println(text);	
	}
	public String toString(){
		return "colour printer";
	}
}
