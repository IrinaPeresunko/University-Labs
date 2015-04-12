package ua.kture.peresunko.Lab2;

public class InkJetPrinter extends Printer{
	public InkJetPrinter(String name,String nameOfCompany,double printSpeed,String printQuality,PrinterImpl colour){
		super(name,nameOfCompany,printSpeed,printQuality,colour);
	}
	public String toString(){
		return super.toString();
	}
	public void getDescription(){
		System.out.println("Inkjet printing is a type of computer printing that recreates"
				+ "\n"+"a digital image by propelling droplets of ink onto paper, plastic, or other substrates."
				+ "\n"+"Inkjet printers are the most commonly used type of printer, and range "
				+ "\n"+"from small inexpensive consumer models to very large professional machines"
				+ "\n"+"that can cost tens of thousands of dollars, or more.");
	}
	public boolean equals(Object otherObject){
		if (!super.equals(otherObject)) return false;
		else return true;
	}
	public void print(String text) {
		colourOfPrinting.printText(text);
		
	}
	

//	public int compareTo(Object obj) {
//		double otherPrintSpeed = ((InkJetPrinter)obj).getPrintSpeed();
//	    
//		if(this.getPrintSpeed()==otherPrintSpeed){
//			return 0;
//		}
//		else{
//			return (this.getPrintSpeed() > otherPrintSpeed)?1:-1;
//		}
//	}
}
