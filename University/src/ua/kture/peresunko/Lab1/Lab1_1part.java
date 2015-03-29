package ua.kture.peresunko.Lab1;

import java.util.Scanner;

public class Lab1_1part{
	public static void main(String[] args){
		double x=0;
		int c = 0;
		double y=0;
		
		Scanner cin = new Scanner(System.in);
		System.out.println("Enter c:");
		if(cin.hasNextInt()){
			c=cin.nextInt();
		}
		System.out.println("You entered:"+c);
		cin.close();
		
		for(double a=0;a<5;a=a+0.1){
			for(double b=-2;b<5;b=b+0.2){
				if(b<=0){
					x=3*a*b-(Math.abs(3.5*c-56)+Math.sqrt(Math.abs(b)));
				}
				else if(b>0.5){
					x=Math.pow(Math.cos(a/2),2)+Math.pow(Math.cos((3/2)*b), 2)-Math.pow(Math.sin(2*a*b), 2)-
							Math.pow(Math.sin(4*b), 2);
				}
				else{
					x=3.5*a*b;
				}
				
				if(x!=0 && a!=Math.PI){
					y=Math.cos(9*x)-Math.cos(7*x)+Math.cos(3*a)-Math.cos(x);
				}
				else{
					y=Math.sin(3*x)+Math.sin(5*a)-Math.sin(4*x);
				}
				
				System.out.println("a = "+ (float)a+ " b = "+ (float)b + " c = "+c+
						" x = "+ (float)x + "; y = "+(float)y);
			}
		}
	}
}
