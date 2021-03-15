package ui;

import javax.swing.*;
import javax.swing.table.*;
import adapter.ProductSaleAdapter;

import java.awt.Color;
import java.awt.event.*;
import java.util.*;


class SaleList extends JInternalFrame implements ActionListener

{
	private static final long serialVersionUID = 1L;
	private JPanel panel,panel1;
	private DefaultTableModel tableModel ;
	private JTable table;
	private JButton buttonEdit;	
	private JScrollPane scrollPane;
	//
	private JLabel labelSerial, labelSellId, labelProductId, labelSellPrice;
	private JLabel labelQuantity;
	private JTextField textSerial;
	private JTextField textSellId;
	private JTextField textProductId;
	private JTextField textSellPrice;
	private JTextField textQuantity;
	
	
	private JButton buttonDone;
	//
	
	
	
/* =========== Product Sale list table [Done by rup]=========== */
		
	public SaleList()
	{
		super("Product Sell List",true,true,true,true);
		this.InitializeComponents();
		this.setVisible(true);
	//	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		//selection row
		String command = event.getActionCommand();
		if(command.equals("Edit"))
		{	
			//int i = table.getSelectedRow();
			//JOptionPane.showMessageDialog(null, "Selected Row "+(tableModel.getValueAt(i, 0).toString()));
			
			this.panel1.setVisible(true);
			this.setSize(630,650);
		}
		else if(command.equals("Done"))
		{	
			
			this.panel1.setVisible(false);
			this.setSize(630,400);
		}
		
		
	}
	
	
	public void InitializeComponents()
	
	{
		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.panel.setBounds(0,0,630,350);
		//table view
		ProductSaleAdapter adapter= new ProductSaleAdapter ();		
		Vector<String> columns= new Vector<String>();
		columns.add("Serial");
		columns.add("Sell ID");
		columns.add("Product ID");
		columns.add("Sell Price");
		columns.add("Quantity");

		this.tableModel= new DefaultTableModel(adapter.getSaleList(),columns);	
		this.table= new JTable (this.tableModel);
	
		this.scrollPane= new JScrollPane (this.table);
		this.scrollPane.setBounds(0	, 0,630, 300);
		this.panel.add(this.scrollPane);

		
		this.buttonEdit = new JButton("Edit");
		this.buttonEdit.setBounds(540,320,80,20);
		this.buttonEdit.addActionListener(this);
		this.panel.add(this.buttonEdit);
		
		//
		this.panel1 = new JPanel();
		this.panel1.setLayout(null);
		this.panel1.setBounds(0,370,630,370);
		this.panel1.setBackground(Color.lightGray);
		
		this.labelSerial = new JLabel ("Serial");
		this.labelSerial.setBounds(20, 30, 110, 20);
		this.panel1.add(this.labelSerial);
		
		this.labelSellId = new JLabel ("Sell ID");
		this.labelSellId.setBounds(20, 70, 110, 20);
		this.panel1.add(this.labelSellId);
		
		this.labelProductId= new JLabel ("Product ID");
		this.labelProductId.setBounds(20, 110, 110, 20);
		this.panel1.add(this.labelProductId);
		
		this.labelSellPrice= new JLabel ("Sell Price");
		this.labelSellPrice.setBounds(20, 150, 110, 20);
		this.panel1.add(this.labelSellPrice);
		
		this.labelQuantity= new JLabel ("Quantity");
		this.labelQuantity.setBounds(20, 190, 110, 20);
		this.panel1.add(this.labelQuantity);
		
		this.textSerial = new JTextField();
		this.textSerial.setBounds(140,30,120,20);
		this.panel1.add(this.textSerial);
		
		this.textSellId = new JTextField();
		this.textSellId.setBounds(140,70,120,20);
		this.panel1.add(this.textSellId);
		
		this.textProductId = new JTextField();
		this.textProductId.setBounds(140,110,120,20);
		this.panel1.add(this.textProductId);
		
		this.textSellPrice = new JTextField();
		this.textSellPrice.setBounds(140,150,120,20);
		this.panel1.add(this.textSellPrice);
		
		this.textQuantity = new JTextField();
		this.textQuantity.setBounds(140,190,120,20);
		this.panel1.add(this.textQuantity);
		
		this.buttonDone = new JButton("Done");
		this.buttonDone.setBounds(540,190,80,20);
		this.buttonDone.addActionListener(this);
		this.panel1.add(this.buttonDone);

		//
		this.add(this.panel1);
		this.add(this.panel);		
		this.setSize(638,400);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
} 

