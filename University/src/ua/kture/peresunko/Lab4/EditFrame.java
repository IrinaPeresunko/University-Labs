package ua.kture.peresunko.Lab4;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class EditFrame extends JFrame{
	
	JButton ok = new JButton("OK");
	JButton cancel = new JButton("Cancel");
	
	JTextField name = new JTextField();
	JTextField nameOfCompany = new JTextField();
	JTextField printSpeed = new JTextField();
	JTextField printQuality = new JTextField();
	
	
	JLabel labelName = new JLabel("name");
	JLabel labelNameOfCompany = new JLabel("company");
	JLabel labelPrintSpeed = new JLabel("print speed");
	JLabel labelPrintQuality = new JLabel("print quality");
	
	public EditFrame(String inputName, String inputCompany, double inputSpeed,String inputQuality){
		
		final JFrame jFrameWindow = new JFrame("Edit element");
		
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
		
		name.setText(inputName);
		nameOfCompany.setText(String.valueOf(inputCompany));
		printSpeed.setText(String.valueOf(inputSpeed));
		printQuality.setText(String.valueOf(inputQuality));
		
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
				//Printer temp = printerList.getElement(ls.getSelectedIndex());
				MainFrame.edit(name.getText(),nameOfCompany.getText(), Double.parseDouble(printSpeed.getText()), printQuality.getText());
				jFrameWindow.setVisible(false);
			}
		});
		
		cancel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
				name.setText(null);
				nameOfCompany.setText(null);
				printSpeed.setText(null);
				printQuality.setText(null);
				jFrameWindow.setVisible(false);
			}
		});
			
	}
}
