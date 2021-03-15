package ui;

import javax.swing.*;

import java.awt.event.*;

class ProductEdit extends JInternalFrame implements ActionListener

{
	private JPanel panel;
	private JLabel labelBuyPrice, labelSellPrice, labelQuantity, labelProductId;
	private JLabel labelProductName;
	private JTextField textProductId;
	private JTextField textProductName;
	private JTextField textBuyPrice;
	private JTextField textSellPrice;
	private JTextField textQuantity;
	
	
	private JButton buttonAdd;
	
	
	
	public  ProductEdit ()
	{
		super("Edit Product",true,true,true,true);
		this.InitializeComponents();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		
		
	}
	
	public void InitializeComponents()
	
	{
		
		
		this.panel = new JPanel();
		this.panel.setLayout(null);
		
		this.labelProductId = new JLabel ("Product Id");
		this.labelProductId.setBounds(20, 20, 110, 20);
		this.panel.add(this.labelProductId);
		
		this.labelProductName = new JLabel ("Product Name");
		this.labelProductName.setBounds(20, 40, 110, 20);
		this.panel.add(this.labelProductName);
		
		this.labelBuyPrice= new JLabel ("Buy Price");
		this.labelBuyPrice.setBounds(20, 60, 110, 20);
		this.panel.add(this.labelBuyPrice);
		
		this.labelSellPrice= new JLabel ("Sell Price");
		this.labelSellPrice.setBounds(20, 80, 110, 20);
		this.panel.add(this.labelSellPrice);
		
		this.labelQuantity= new JLabel ("Quantity");
		this.labelQuantity.setBounds(20, 100, 110, 20);
		this.panel.add(this.labelQuantity);
		
		this.textProductId = new JTextField();
		this.textProductId.setBounds(140,20,120,20);
		this.panel.add(this.textProductId);
		
		this.textProductName = new JTextField();
		this.textProductName.setBounds(140,40,120,20);
		this.panel.add(this.textProductName);
		
		this.textBuyPrice = new JTextField();
		this.textBuyPrice.setBounds(140,60,120,20);
		this.panel.add(this.textBuyPrice);
		
		this.textSellPrice = new JTextField();
		this.textSellPrice.setBounds(140,80,120,20);
		this.panel.add(this.textSellPrice);
		
		this.textQuantity = new JTextField();
		this.textQuantity.setBounds(140,100,120,20);
		this.panel.add(this.textQuantity);
		
		this.buttonAdd = new JButton("Add");
		this.buttonAdd.setBounds(180,120,80,20);
		this.buttonAdd.addActionListener(this);
		this.panel.add(this.buttonAdd);
		
		this.add(this.panel);
		
		this.setSize(290,200);
	
		 
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
	}
	
}
