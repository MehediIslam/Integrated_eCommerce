package ui;


import javax.swing.*;

import java.awt.event.*;

class ProfileEdit extends JInternalFrame implements ActionListener

{
	private JPanel panel;
	private JLabel  labelId,labelUserId,labelName,labelPhone,labelEmail,labelAddress,labelCreatorId;
	private JTextField textId;
	private JTextField textUserId;
	private JTextField textName;
	private JTextField textPhone;
	private JTextField textEmail;
	private JTextField textAdderss;
	private JTextField textCreatorId;
	private JButton buttonAdd;
	
	public ProfileEdit()
	
	{
		super("Edit Profile",true,true,true,true);
		this.InitializeComponents();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		
		
	}
	public void InitializeComponents()
	
	{
		
		
		this.panel = new JPanel();
		this.panel.setLayout(null);
		
		this.labelId = new JLabel ("Id");
		this.labelId.setBounds(20, 20, 110, 20);
		this.panel.add(this.labelId);
		
		
		this.labelUserId = new JLabel ("User Id");
		this.labelUserId.setBounds(20, 40, 110, 20);
		this.panel.add(this.labelUserId);
		
		this.labelName = new JLabel ("Name");
		this.labelName.setBounds(20, 60, 110, 20);
		this.panel.add(this.labelName);
		
		this.labelPhone = new JLabel ("Phone");
		this.labelPhone.setBounds(20, 80, 110, 20);
		this.panel.add(this.labelPhone);
		
		this.labelEmail = new JLabel ("Email");
		this.labelEmail.setBounds(20, 100, 110, 20);
		this.panel.add(this.labelEmail);
		
		
		this.labelAddress = new JLabel ("Address");
		this.labelAddress.setBounds(20, 120, 110, 20);
		this.panel.add(this.labelAddress);
		
		this.labelCreatorId = new JLabel ("Creator Id");
		this.labelCreatorId.setBounds(20, 140, 110, 20);
		this.panel.add(this.labelCreatorId);
		
		
		this.textId = new JTextField();
		this.textId.setBounds(140,20,120,20);
		this.panel.add(this.textId);
		
		this.textUserId = new JTextField();
		this.textUserId.setBounds(140,40,120,20);
		this.panel.add(this.textUserId);
		
		this.textName = new JTextField();
		this.textName.setBounds(140,60,120,20);
		this.panel.add(this.textName);
		
		this.textPhone = new JTextField();
		this.textPhone.setBounds(140,80,120,20);
		this.panel.add(this.textPhone);
		
		this.textEmail = new JTextField();
		this.textEmail.setBounds(140,100,120,20);
		this.panel.add(this.textEmail);
		
		this.textAdderss = new JTextField();
		this.textAdderss.setBounds(140,120,120,20);
		this.panel.add(this.textAdderss);
		
		this.textCreatorId = new JTextField();
		this.textCreatorId.setBounds(140,140,120,20);
		this.panel.add(this.textCreatorId);
		
		
		

		
		this.buttonAdd = new JButton("Edit");
		this.buttonAdd.setBounds(160,180,100,20);
		this.buttonAdd.addActionListener(this);
		this.panel.add(this.buttonAdd);
		
		this.add(this.panel);
		
		this.setSize(400,300);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
} 

