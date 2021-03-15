package ui;

import javax.swing.*;
import javax.swing.table.*;
import adapter.ProductOrderAdapter;

import java.awt.Color;
import java.awt.event.*;
import java.util.*;


class OrderList extends JInternalFrame implements ActionListener

{
	private static final long serialVersionUID = 1L;
	private JPanel panel,panel1;
	private DefaultTableModel tableModel ;
	private JTable table;
	private JButton buttonEdit;	
	private JScrollPane scrollPane;
	
	//
	private JLabel labelOrderId, labelProductId, labelOrderPrice, labelQuantity;
	private JLabel labelStatus;
	private JTextField textOrderId;
	private JTextField textProductId;
	private JTextField textOrderPrice;
	private JTextField textQuantity;
	private JTextField textStatus;
	
	
	private JButton buttonDone;
	//
	
	
/* =========== Orderlist table [Done by rup]=========== */
		
	public OrderList()
	{
		super("Order List",true,true,true,true);
		this.InitializeComponents();
		//this.setVisible(true);
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
		ProductOrderAdapter adapter= new ProductOrderAdapter ();		
		Vector<String> columns= new Vector<String>();
		columns.add("Serial");
		columns.add("Order ID");
		columns.add("Product ID");		
		columns.add("Order Price");
		columns.add("Quantity");
		columns.add("Status");

		this.tableModel= new DefaultTableModel(adapter.getOrderList(),columns);	
		this.table= new JTable (this.tableModel);
	
		this.scrollPane= new JScrollPane (this.table);
		this.scrollPane.setBounds(0	, 0,630, 300);
		this.panel.add(this.scrollPane);

		
		this.buttonEdit = new JButton("Edit");
		this.buttonEdit.setBounds(540,340,80,20);
		this.buttonEdit.addActionListener(this);
		this.panel.add(this.buttonEdit);
		
		//
		this.panel1 = new JPanel();
		this.panel1.setLayout(null);
		this.panel1.setBounds(0,370,630,370);
		this.panel1.setBackground(Color.lightGray);
		
		this.labelOrderId = new JLabel ("Order ID");
		this.labelOrderId.setBounds(20, 30, 110, 20);
		this.panel1.add(this.labelOrderId);
		
		this.labelProductId = new JLabel ("Product ID");
		this.labelProductId.setBounds(20, 70, 110, 20);
		this.panel1.add(this.labelProductId);
		
		this.labelOrderPrice= new JLabel ("Order Price");
		this.labelOrderPrice.setBounds(20, 110, 110, 20);
		this.panel1.add(this.labelOrderPrice);
		
		this.labelQuantity= new JLabel ("Quantity");
		this.labelQuantity.setBounds(20, 150, 110, 20);
		this.panel1.add(this.labelQuantity);
		
		this.labelStatus= new JLabel ("Status");
		this.labelStatus.setBounds(20, 190, 110, 20);
		this.panel1.add(this.labelStatus);
		
		this.textOrderId = new JTextField();
		this.textOrderId.setBounds(140,30,120,20);
		this.panel1.add(this.textOrderId);
		
		this.textProductId = new JTextField();
		this.textProductId.setBounds(140,70,120,20);
		this.panel1.add(this.textProductId);
		
		this.textOrderPrice = new JTextField();
		this.textOrderPrice.setBounds(140,110,120,20);
		this.panel1.add(this.textOrderPrice);
		
		this.textQuantity = new JTextField();
		this.textQuantity.setBounds(140,150,120,20);
		this.panel1.add(this.textQuantity);
		
		this.textStatus = new JTextField();
		this.textStatus.setBounds(140,190,120,20);
		this.panel1.add(this.textStatus);
		
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

