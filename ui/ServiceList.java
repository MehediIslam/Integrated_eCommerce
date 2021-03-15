package ui;

import javax.swing.*;
import javax.swing.table.*;
import adapter.ProductServiceAdapter;

import java.awt.Color;
import java.awt.event.*;
import java.util.*;


class ServiceList extends JInternalFrame implements ActionListener

{
	private static final long serialVersionUID = 1L;
	private JPanel panel,panel1;
	private DefaultTableModel tableModel ;
	private JTable table;
	private JButton buttonEdit;	
	private JScrollPane scrollPane;
	
	
	//
	private JLabel labelServiceId, labelProductId, labelProblemDescription, labelRepairCost;
	private JLabel labelStatus;
	private JTextField textServiceId;
	private JTextField textProductId;
	private JTextField textProblemDescription;
	private JTextField textRepairCost;
	private JTextField textStatus;
	private JButton buttonDone;
	//
	
	
/* =========== Servicelist table [Done by rup]=========== */
		
	public ServiceList()
	{
		super("Service List",true,true,true,true);
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
		ProductServiceAdapter adapter= new ProductServiceAdapter ();		
		Vector<String> columns= new Vector<String>();
		columns.add("Serial");
		columns.add("Service ID");
		columns.add("Product ID");
		columns.add("Problem Description");
		columns.add("Repair Cost");
		columns.add("Status");

		this.tableModel= new DefaultTableModel(adapter.getServiceList(),columns);	
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
		
		this.labelServiceId = new JLabel ("Service ID");
		this.labelServiceId.setBounds(20, 30, 110, 20);
		this.panel1.add(this.labelServiceId);
		
		this.labelProductId = new JLabel ("Product ID");
		this.labelProductId.setBounds(20, 70, 110, 20);
		this.panel1.add(this.labelProductId);
		
		this.labelProblemDescription= new JLabel ("Problem Description");
		this.labelProblemDescription.setBounds(20, 110, 110, 20);
		this.panel1.add(this.labelProblemDescription);
		
		this.labelRepairCost= new JLabel ("Repair Cost");
		this.labelRepairCost.setBounds(20, 150, 110, 20);
		this.panel1.add(this.labelRepairCost);
		
		this.labelStatus= new JLabel ("Status");
		this.labelStatus.setBounds(20, 190, 110, 20);
		this.panel1.add(this.labelStatus);
		
		this.textServiceId = new JTextField();
		this.textServiceId.setBounds(140,30,120,20);
		this.panel1.add(this.textServiceId);
		
		this.textProductId = new JTextField();
		this.textProductId.setBounds(140,70,120,20);
		this.panel1.add(this.textProductId);
		
		this.textProblemDescription = new JTextField();
		this.textProblemDescription.setBounds(140,110,120,20);
		this.panel1.add(this.textProblemDescription);
		
		this.textRepairCost = new JTextField();
		this.textRepairCost.setBounds(140,150,120,20);
		this.panel1.add(this.textRepairCost);
		
		this.textStatus = new JTextField();
		this.textStatus.setBounds(140,190,120,20);
		this.panel1.add(this.textStatus);
		
		this.buttonDone = new JButton("Done");
		this.buttonDone.setBounds(540,190,80,20);
		this.buttonDone.addActionListener(this);
		this.panel1.add(this.buttonDone);
		
		this.add(this.panel1);
		this.add(this.panel);		
		this.setSize(638,400);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
} 

