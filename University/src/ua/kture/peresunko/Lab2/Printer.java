package ua.kture.peresunko.Lab2;

public abstract class Printer implements Comparable<Printer>{
	
	private String name;
	private String nameOfCompany;
	private double printSpeed;
	private String printQuality;
	
	protected PrinterImpl colourOfPrinting;
	
	public abstract void print(String text);
	
	public Printer(String name,String nameOfCompany,double printSpeed,String printQuality,PrinterImpl colour){
		this.name=name;
		this.nameOfCompany=nameOfCompany;
		this.printSpeed=printSpeed;
		this.printQuality=printQuality;
		this.colourOfPrinting=colour;
	} 
	public String toString(){
		return "[name = "+name+" , "+"company = "+nameOfCompany+" , "+
				"print speed = "+printSpeed+" , "+ "print quality = "+printQuality+
				" , "+ colourOfPrinting+"]";
	}
	public String getName() {
		return name;
	}
	public PrinterImpl getColourOfPrinting(){
		return colourOfPrinting;
	}
	public double getPrintSpeed(){
		return printSpeed;
	}
	public String getNameOfCompany(){
		return nameOfCompany;
	}
	public String getPrintQuality(){
		return printQuality;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setNameOfCompany(String nameOfCompany){
		this.nameOfCompany = nameOfCompany;
	}
	public void setPrintSpeed(double speed){
		this.printSpeed = speed;
	}
	public void setPrintQuality(String printQuality){
		this.printQuality = printQuality;
	}
	public int compareTo(Printer p) {
		return this.getPrintSpeed()>p.getPrintSpeed() ? 1 : this.getPrintSpeed()==p.getPrintSpeed() ? 0 : -1;
	}
	public void editElement(String name, String nameOfCompany, double speed,String printQuality){
		this.setName(name);
		this.setNameOfCompany(nameOfCompany);
		this.setPrintSpeed(speed);
		this.setPrintQuality(printQuality);
	}
}
