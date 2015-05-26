package ua.kture.peresunko.Lab4;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.*;

import ua.kture.peresunko.Lab2.*;
import ua.kture.peresunko.Lab3.MyLinkedList;
import ua.kture.peresunko.Lab3.MyList;

public class MainFrame extends JFrame{
	
		private static MyList<Printer> printerList=new MyLinkedList();

		Printer[] printers = new Printer[5];
		
		static JList viewList = new JList();
		private static DefaultListModel<String> printerData = new DefaultListModel();
		
		JButton buttonAdd = new JButton("Add");
		JButton buttonDelete = new JButton("Delete");
		JButton buttonClear = new JButton("Clear");
		JButton buttonSearch = new JButton("Search");
		JButton buttonSort = new JButton("Sort");
		JButton buttonEdit = new JButton("Edit");
		
		public static JFrame window;
		Box box = Box.createVerticalBox();
		JPanel buttonPanel = new JPanel();
		JComboBox sortedType = new JComboBox();
		static JComboBox deleteType = new JComboBox();
		static JComboBox searchType = new JComboBox();
		
		
		
		public MainFrame(){
			
			printers[0]=new InkJetPrinter("Ink jet printer","Samsung",60,"average",new MonoPrinter());
			printers[1]=new InkJetPrinter("Ink jet printer","HP",70,"good",new ColourPrinter());
			printers[2]=new LaserPrinter("Laser printer","Canon",100,"excellent",new MonoPrinter());
			printers[3]=new LaserPrinter("Laser printer","Canon",85,"good",new ColourPrinter());
			printers[4]=new PhotoPrinter("Ink jet photoprinter","Epson",40,"good",new ColourPrinter());
			
			for(int i=0;i<printers.length;i++){
				printerList.add(printers[i],i);
			}
			
			for(int i=0;i<printers.length;i++){
				printerData.addElement(toStringPrinterList(printers[i]));
			}
			
			window = new JFrame("Main Window");
			window.setBounds(100, 100, 600, 400);
			window.setLayout(new BorderLayout());
			window.setResizable(false);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setVisible(true);
			
			sortedType.addItem("by speed");
			sortedType.addItem("by company");
			
			searchType.addItem("one field");
			searchType.addItem("few field");
			
			deleteType.addItem("selected");
			deleteType.addItem("by field");
			
			buttonPanel.setLayout(new FlowLayout());
			buttonPanel.add(new JLabel("Sorted by: "));
			buttonPanel.add(sortedType);
			buttonPanel.add(new JLabel("Delete: "));
			buttonPanel.add(deleteType);
			buttonPanel.add(new JLabel("Search by: "));
			buttonPanel.add(searchType);
			
			viewList.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent event){
					if(viewList.getSelectedIndex() < 0) return;
					System.out.println(viewList.getSelectedIndex());
				}
			});
			
			window.add(box, BorderLayout.EAST);
			window.add(buttonPanel, BorderLayout.SOUTH);
			
			window.add(viewList, BorderLayout.CENTER);
			viewList.setModel(printerData);
			
			box.add(buttonAdd);
			box.add(Box.createVerticalStrut(30));
			box.add(buttonSort);
			box.add(Box.createVerticalStrut(30));
			box.add(buttonSearch);
			box.add(Box.createVerticalStrut(30));
			box.add(buttonDelete);
			box.add(Box.createVerticalStrut(30));
			box.add(buttonClear);
			box.add(Box.createVerticalStrut(30));
			box.add(buttonEdit);
			
			buttonAdd.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent event){
					AddFrame addWin = new AddFrame();
				}
			});
			buttonDelete.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent event){
					if(printerList.size() == 0) throw new IllegalStateException("List is empty");
					if(deleteType.getSelectedItem() == "selected"){
						printerList.remove(viewList.getSelectedIndex());
						printerData.remove(viewList.getSelectedIndex());
					}
					if(deleteType.getSelectedItem() == "by field"){
						DeleteFrame deleteFrame = new DeleteFrame();
					}
					
				}
			});
			buttonClear.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent event){
					if(printerList.size() == 0) throw new IllegalStateException("List is empty");
					printerList.clear();
					printerData.clear();
				}
			});
			buttonSearch.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent event){
					SearchFrame frame = new SearchFrame();
				}
			});
			buttonSort.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent event){
					if(sortedType.getSelectedItem() == "by speed") printerList.sort(null);
					if(sortedType.getSelectedItem() == "by company") printerList.sort(new SortedByCompany());
					printerData.clear();
					Iterator<Printer> iterator = printerList.iterator();
					for(int i = 0; i < printerList.size(); i++){
						Printer printer = iterator.next();
						printerData.addElement(toStringPrinterList(printer));
					}
				}
				});
			buttonEdit.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent event){
					EditFrame editFrame = new EditFrame(
							((MyLinkedList) printerList).getElementByIndex(viewList.getSelectedIndex()).getName(),
							((MyLinkedList) printerList).getElementByIndex(viewList.getSelectedIndex()).getNameOfCompany(),
							((MyLinkedList) printerList).getElementByIndex(viewList.getSelectedIndex()).getPrintSpeed(),
							((MyLinkedList) printerList).getElementByIndex(viewList.getSelectedIndex()).getPrintQuality());
				}
			});
		}
		public static void add(Printer printer){
			int size = printerList.size();
			printerList.add(printer,size);
			printerData.addElement(toStringPrinterList(printer));
		}
		public static void setList(MyList resultList){
			printerList = resultList;
		}
		public static MyList getList(){
			return printerList;
		}
		public static String toStringPrinterList(Printer printer){
			return printer.getName()+" company:"+printer.getNameOfCompany()+
					", print speed:"+printer.getPrintSpeed()+", quality:"+printer.getPrintQuality();
		}
		public static void setModel(DefaultListModel model){
			   printerData = model;
		}
		public static void edit(String name, String nameOfCompany, double printSpeed,String quality){
			Printer printer = ((MyLinkedList) printerList).getElementByIndex(viewList.getSelectedIndex());
			printer.editElement(name, nameOfCompany, printSpeed,quality);
			printerData.insertElementAt(toStringPrinterList(printer), viewList.getSelectedIndex());
			printerData.remove(viewList.getSelectedIndex()+1);
		}
		public static  JList getJList(){
			   return viewList;
		}
}


