package ui;


import javax.swing.*;
import java.awt.event.*;

class BrandEdit extends JInternalFrame implements ActionListener

{
	private JPanel panel;
	private JLabel labelId,labelBrandname,labelDescription;
	private JTextField textBrandname;
	private JTextArea textDescription;
	private JTextField textId;
	private JButton buttonEdit;
	
	public BrandEdit()
	
	{
		super("Edit Brand",true,true,true,true);
		this.InitializeComponents();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		
		
	}
	public void InitializeComponents()
	
	{
		
		
		this.panel = new JPanel();
		this.panel.setLayout(null);
		
		this.labelId = new JLabel ("Brand Id");
		this.labelId.setBounds(20, 20, 110, 20);
		this.panel.add(this.labelId);
		
		this.labelBrandname = new JLabel ("Brand Name");
		this.labelBrandname.setBounds(20, 40, 110, 20);
		this.panel.add(this.labelBrandname);
		
		this.labelDescription = new JLabel ("Description");
		this.labelDescription.setBounds(20, 60, 110, 20);
		this.panel.add(this.labelDescription);
		
		this.textId = new JTextField();
		this.textId.setBounds(140,20,120,20);
		this.panel.add(this.textId);
		
		this.textBrandname = new JTextField();
		this.textBrandname.setBounds(140,40,120,20);
		this.panel.add(this.textBrandname);
		
		this.textDescription = new JTextArea();
		this.textDescription.setBounds(140,60,120,47);
		this.panel.add(this.textDescription);
		
		
		

		
		this.buttonEdit = new JButton("Edit");
		this.buttonEdit.setBounds(180,80,80,20);
		this.buttonEdit.addActionListener(this);
		this.panel.add(this.buttonEdit);
		
		this.add(this.panel);
		
		this.setSize(300,200);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
} 

