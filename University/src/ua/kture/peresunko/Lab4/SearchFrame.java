package ua.kture.peresunko.Lab4;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ua.kture.peresunko.Lab2.Printer;
import ua.kture.peresunko.Lab3.MyLinkedList;
import ua.kture.peresunko.Lab3.MyList;
import ua.kture.peresunko.Lab3.Parameters;

public class SearchFrame extends JFrame{
	 JComboBox printers = new JComboBox();
	 JComboBox type = new JComboBox();
	 JButton buttonOK = new JButton("ok");
	 JLabel labelType = new JLabel("Search by: ");
	 JLabel labelValue = new JLabel("Value");
	 JTextField valueField = new JTextField();
	 
	 static JFrame JFrameWindow;
	 
	 JTextField name = new JTextField();
	 JTextField nameOfCompany = new JTextField();
	 JTextField printSpeed = new JTextField();
	 JTextField printQuality = new JTextField();
		
	 JLabel labelName =new JLabel("name");
	 JLabel labelNameOfCompany =new JLabel("company");
	 JLabel labelPrintSpeed =new JLabel("print speed");
	 JLabel labelPrintQuality =new JLabel("print quality");
	 
	 public SearchFrame(){
	
		 if(MainFrame.searchType.getSelectedItem() == "few field"){
			 JFrameWindow = new JFrame("Поиск");
			 JFrameWindow.setBounds(100, 100, 480, 300);
			 JFrameWindow.setLayout(null);
			 JFrameWindow.setResizable(false);
			 JFrameWindow.setVisible(true);
			   
			   buttonOK.setBounds(20, 180, 80, 20);
			   
			   labelName.setBounds(10, 30, 100, 20);
			   labelNameOfCompany.setBounds(10, 70, 100, 20);
			   labelPrintSpeed.setBounds(10, 110, 100, 20);
			   labelPrintQuality.setBounds(10,150,100,20);

			   
			   name.setBounds(80,30,100,20);
			   nameOfCompany.setBounds(80,70,100,20);
			   printSpeed.setBounds(80,110,100,20);
			   printQuality.setBounds(80,150,100,20);
			   
			   printers.setBounds(20, 210, 400, 25);
			   
			   JFrameWindow.add(name);
			   JFrameWindow.add(nameOfCompany);
			   JFrameWindow.add(printSpeed);
			   JFrameWindow.add(printQuality);
			   
			   JFrameWindow.add(labelName);
			   JFrameWindow.add(labelNameOfCompany);
			   JFrameWindow.add(labelPrintSpeed);
			   JFrameWindow.add(labelPrintQuality);
			   
			   JFrameWindow.add(printers);
			   JFrameWindow.add(buttonOK);
			   
			   buttonOK.addMouseListener(new MouseAdapter(){
					 public void mouseClicked(MouseEvent event){
						 printers.removeAllItems();
						 
						 MyList printerList = MainFrame.getList();
						 Iterator<Printer> iterator = printerList.iterator();
						 
						 String gettingName = null;
						 String gettingNameOfCompany = null;
						 double gettingPrintSpeed = 0;
						 String gettingPrintQuality = null;
						 
						if(!name.getText().equals("")){
							gettingName=name.getText();
						}
						if(!nameOfCompany.getText().equals("")){
							gettingNameOfCompany=nameOfCompany.getText();
						}
						if(!printSpeed.getText().equals("")){
							gettingPrintSpeed=Double.parseDouble(printSpeed.getText());
						}
						if(!printQuality.getText().equals("")){
							gettingPrintQuality=printQuality.getText();
						}
						
						 Parameters parameters = new Parameters(gettingName,gettingNameOfCompany,
								 gettingPrintSpeed, gettingPrintQuality);
						 while(iterator.hasNext()){
								Printer printer = iterator.next();
								boolean flag = ((MyLinkedList) printerList).search(printer,parameters);
								if(flag==true){
									printers.addItem(MainFrame.toStringPrinterList(printer));
								}
						}
					 }
			   });
			   return;
		}
		 
		 JFrameWindow = new JFrame("Search");
		 JFrameWindow.setBounds(100, 100, 500, 300);
		 JFrameWindow.setLayout(null);
		 JFrameWindow.setResizable(false);
		 JFrameWindow.setVisible(true);
		  
		 buttonOK.setBounds(10, 120, 80, 20);
		  
		 labelValue.setBounds(10, 50, 100, 20);
		 labelType.setBounds(10, 10, 120, 20);
		  
		 valueField.setBounds(80, 50, 140, 20);
		  
		 printers.setBounds(10,180, 400, 25);
		 type.setBounds(80, 10, 100, 20);
		  
		 type.addItem("name");
		 type.addItem("company");
		 type.addItem("speed");
		 type.addItem("quality");
		 
		JFrameWindow.add(printers);
		JFrameWindow.add(type);
		
		JFrameWindow.add(labelType);
		JFrameWindow.add(labelValue);
		
		JFrameWindow.add(valueField);
		JFrameWindow.add(buttonOK);
	  
		 buttonOK.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent event){
				 String parameter = (String) type.getSelectedItem(); 
				 
				 printers.removeAllItems();
				 MyList printerList = MainFrame.getList();
				 Iterator<Printer> iterator = printerList.iterator();
				 
				 if(parameter.equals("name")){
					 String value = valueField.getText();
					for(int i = 0; i < printerList.size(); i++){
						Printer printer = iterator.next();
						if(printer.getName().equals(value)){
							printers.addItem(MainFrame.toStringPrinterList(printer));
						}
					}
				}
				 if(parameter.equals("company")){
					 String value = valueField.getText();
					for(int i = 0; i < printerList.size(); i++){
						Printer printer = iterator.next();
						if(printer.getNameOfCompany().equals(value)){
							printers.addItem(MainFrame.toStringPrinterList(printer));
						}
					}
				}
				 if(parameter.equals("speed")){
					 double value = Double.parseDouble(valueField.getText());
					for(int i = 0; i < printerList.size(); i++){
						Printer printer = iterator.next();
						if(printer.getPrintSpeed()==value){
							printers.addItem(MainFrame.toStringPrinterList(printer));
						}
					}
				}
				 if(parameter.equals("quality")){
					 String value = valueField.getText();
					for(int i = 0; i < printerList.size(); i++){
						Printer printer = iterator.next();
						if(printer.getPrintQuality().equals(value)){
							printers.addItem(MainFrame.toStringPrinterList(printer));
						}
					}
				}
				 
			 }
		  
		 });
	 }
}