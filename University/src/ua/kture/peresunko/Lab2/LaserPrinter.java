package ua.kture.peresunko.Lab2;

public class LaserPrinter extends Printer{
	
	public LaserPrinter(String name,String nameOfCompany,double printSpeed,String printQuality,PrinterImpl pi){
		super(name,nameOfCompany,printSpeed,printQuality,pi);
	}
	public String toString(){
		return super.toString();
	}
	public void print(String text) {
		colourOfPrinting.printText(text);
	}
}
