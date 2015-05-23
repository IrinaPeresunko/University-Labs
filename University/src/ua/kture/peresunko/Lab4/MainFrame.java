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
		
		JList ls = new JList();
		private static DefaultListModel<String> printerData = new DefaultListModel();
		JButton buttonAdd = new JButton("Add");
		JButton buttonDelete = new JButton("Delete");
		JButton buttonClear = new JButton("Clear");
		JButton buttonSearch = new JButton("Search");
		JButton buttonSort = new JButton("Sort");
		
		Box box = Box.createVerticalBox();
		JPanel p1 = new JPanel();
		JComboBox box1 = new JComboBox();
		JComboBox box2 = new JComboBox();
		public static JFrame window;
		
		
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
			
			box1.addItem("by speed");
			box1.addItem("by company");
			p1.setLayout(new FlowLayout());
			p1.add(new JLabel("Sorted by: "));
			p1.add(box1);
			//p1.add(box2);
			
			ls.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent event){
					if(ls.getSelectedIndex() < 0) return;
					System.out.println(ls.getSelectedIndex());
				}
			});
			
			window.add(box, BorderLayout.EAST);
			window.add(p1, BorderLayout.SOUTH);
			
			window.add(ls, BorderLayout.CENTER);
			ls.setModel(printerData);
			
			box.add(buttonAdd);
			box.add(Box.createVerticalStrut(30));
			box.add(buttonSort);
			box.add(Box.createVerticalStrut(30));
			box.add(buttonSearch);
			box.add(Box.createVerticalStrut(30));
			box.add(buttonDelete);
			box.add(Box.createVerticalStrut(30));
			box.add(buttonClear);
			
			
			
			buttonAdd.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent event){
					AddFrame addWin = new AddFrame();
				}
			});
			buttonDelete.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent event){
					if(printerList.size() == 0) throw new IllegalStateException("List is empty");
					printerList.remove(ls.getSelectedIndex());
					printerData.remove(ls.getSelectedIndex());
					System.out.println(printerList);
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
					if(box1.getSelectedItem() == "by speed") printerList.sort(null);
					if(box1.getSelectedItem() == "by company") printerList.sort(new SortedByCompany());
					printerData.clear();
					Iterator<Printer> iterator = printerList.iterator();
					for(int i = 0; i < printerList.size(); i++){
						Printer printer = iterator.next();
						printerData.addElement(toStringPrinterList(printer));
					}
				}
				});
		}
		public static void add(Printer printer){
			int size = printerList.size();
			printerList.add(printer,size);
			printerData.addElement(toStringPrinterList(printer));
		}
		public static MyList getList(){
			return printerList;
		}
		public static String toStringPrinterList(Printer printer){
			return printer.getName()+" company:"+printer.getNameOfCompany()+
					", print speed:"+printer.getPrintSpeed()+", quality:"+printer.getPrintQuality();
		}
}


