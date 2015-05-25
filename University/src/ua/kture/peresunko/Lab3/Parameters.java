package ua.kture.peresunko.Lab3;

public class Parameters {
	private String name;
	private String nameOfCompany;
	private double printSpeed;
	private String printQuality;
	
	public Parameters(String name,String nameOfCompany,double printSpeed,String printQuality){
		this.name = name;
		this.nameOfCompany=nameOfCompany;
		this.printSpeed=printSpeed;
		this.printQuality=printQuality;
	} 
	public String getName() {
		return name;
	}
	public String getNameOfCompany() {
		return nameOfCompany;
	}
	public double getPrintSpeed() {
		return printSpeed;
	}
	public String getPrintQuality() {
		return printQuality;
	}
}
