package ui;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.event.*;
import java.text.*;
import java.util.*;


public class Test extends JFrame implements ActionListener
{
	private JPanel panel;
	private DefaultTableModel tableModel ;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton buttonOk;
	private JButton buttonBack;
	private JComboBox list;
	
	
	Test ()
	{
		this.initialize();
		this.setSize(650,400);
		this.setTitle("Transaction List");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[]args)
	{
			Test t= new Test();
			
	 	t.initialize();	
	}
	public void actionPerformed (ActionEvent event)
	{
		
	}
	
	public void initialize()
	{
		this.panel = new JPanel ();
		this.panel.setLayout(null);
		
	//	DataBase db= new DataBase ();
		//db.DataBaseConnector();
		Vector<String> columns= new Vector<String>();
		columns.add("Account From");
		columns.add("Account To");
		columns.add("Amount");
		columns.add("Time Of Transaction");
	
		this.tableModel= new DefaultTableModel();
		
		this.table= new JTable (this.tableModel);
		
		
		this.scrollPane= new JScrollPane (this.table);
		this.scrollPane.setBounds(0	, 0,600, 300);
		this.panel.add(this.scrollPane);
		
		this.buttonOk= new JButton("Ok");
		this.buttonOk.addActionListener(this);
		this.buttonOk.setBounds(300,320, 80, 20);
		this.panel.add(this.buttonOk);
		
		this.buttonBack= new JButton("Back");
	 	this.buttonBack.addActionListener(this);
		this.buttonBack.setBounds(450,320, 80, 20);
		this.panel.add(this.buttonBack);
		String[] List = new String[]{"Profiles","Member","Login"};
		
		this.list = new JComboBox(List);
		this.list.setBounds(150,320, 140, 20);
		this.panel.add(this.list);
		
		this.add(this.panel);
		
	}
}
