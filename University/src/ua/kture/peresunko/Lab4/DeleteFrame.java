package ua.kture.peresunko.Lab4;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.*;

import ua.kture.peresunko.Lab2.*;
import ua.kture.peresunko.Lab3.MyLinkedList;
import ua.kture.peresunko.Lab3.MyList;
import ua.kture.peresunko.Lab3.Parameters;

public class DeleteFrame extends JFrame{
	
	JButton ok = new JButton("OK");
	JButton cancel = new JButton("Cancel");
	
	JLabel labelName =new JLabel("name");
	JLabel labelNameOfCompany =new JLabel("company");
	JLabel labelPrintSpeed =new JLabel("print speed");
	JLabel labelPrintQuality =new JLabel("print quality");
	
	JTextField name = new JTextField();
	JTextField nameOfCompany = new JTextField();
	JTextField printSpeed = new JTextField();
	JTextField printQuality = new JTextField();
	
	public DeleteFrame(){
		
		final JFrame jFrameWindow = new JFrame("Delete element");
		
		jFrameWindow.setBounds(100, 100, 400, 300);
		jFrameWindow.setLayout(null);
		jFrameWindow.setResizable(false);
		jFrameWindow.setVisible(true);
		
		labelName.setBounds(20,30,100,15);
		labelNameOfCompany.setBounds(20,70,100,15);
		labelPrintSpeed.setBounds(20,110,100,15);
		labelPrintQuality.setBounds(20,150,100,15);
		
		jFrameWindow.add(labelName);
		jFrameWindow.add(labelNameOfCompany);
		jFrameWindow.add(labelPrintSpeed);
		jFrameWindow.add(labelPrintQuality);
		
		name.setBounds(130,30,100,20);
		nameOfCompany.setBounds(130,70,100,20);
		printSpeed.setBounds(130,110,100,20);
		printQuality.setBounds(130,150,100,20);
		
		jFrameWindow.add(name);
		jFrameWindow.add(nameOfCompany);
		jFrameWindow.add(printSpeed);
		jFrameWindow.add(printQuality);
		
		ok.setBounds(50, 200, 60, 20);
		cancel.setBounds(150, 200, 100, 20);
		
		jFrameWindow.add(ok);
		jFrameWindow.add(cancel);
		
		ok.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
			
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
				 
				 DefaultListModel<String> resultList = new DefaultListModel();
				 
				 while(iterator.hasNext()){
					// System.err.println("__________"+"\n"+printerList.toString());
						Printer printer = iterator.next();
						boolean flag = ((MyLinkedList) printerList).search(printer,parameters);
						if(flag==true){
							//System.err.println(printer);
							iterator.remove();
						}
						else{
							resultList.addElement(MainFrame.toStringPrinterList(printer));
						}
				}
				 MainFrame.getJList().setModel(resultList);
			 }
	   });
		cancel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
				jFrameWindow.setVisible(false);
			}
		});
			
	}
}
