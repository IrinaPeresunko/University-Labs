package ua.kture.peresunko.Lab2;

public class InkJetPrinter extends Printer{
	
	public InkJetPrinter(String name,String nameOfCompany,double printSpeed,String printQuality,PrinterImpl colour){
		super(name,nameOfCompany,printSpeed,printQuality,colour);
	}
	public String toString(){
		return super.toString();
	}
	public void print(String text) {
		colourOfPrinting.printText(text);
	}
}
