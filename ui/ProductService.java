package ui;

import java.awt.Color;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import adapter.ProductAdapter;
import adapter.ProductServiceAdapter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import model.ProductServices;

class ProductService extends JInternalFrame implements ActionListener
{
	private JPanel panel1,panel2,panel3;
	private JScrollPane scrollPane,scrollPane2;
	private JTable table,table2;
	private DefaultTableModel tableModel,tableModel2;
	private JLabel labelSearch,proId,proName,problem,repCost,status,labelGrand,labelTotalAmount;
	private JTextField textboxSearch,proIdText,proNameText,repCostText;
	JTextArea probDesText;
	private JButton buttonAddProduct,buttonCancel,buttonSelectProduct,buttonRemove,buttonConfirm;
	private TableRowSorter<TableModel> rowSorter; 
	private JComboBox statusCombo;
	private double grandTotal = 0;
	int i;
	
	public ProductService()
	{
		super("Product Service",true,true,true,true);
		this.InitializeComponents();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		//selection row
		String command = event.getActionCommand();
		if(command.equals("Select"))
		{
			i = table.getSelectedRow();
			int selectedRow = table.getSelectedRow();
			if(selectedRow != -1) 
			{
				this.panel1.setVisible(false);
				panel3();	
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please Select a Row");
			}	
		}
		
		if(command.equals("Cancel"))
		{
			this.panel1.setVisible(true);
			this.panel3.setVisible(false);
		}
		
		if(command.equals("ADD"))
		{	
			if(this.repCostText.getText().trim().equals("") || this.probDesText.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please Fillup Required Field");
			}
			
			else
			{
				int id = Integer.parseInt(this.proIdText.getText().toString());
				String name = this.proNameText.getText().toString();
				String proDescription = this.probDesText.getText().toString();
				double cost = Double.parseDouble(this.repCostText.getText().toString());
				String status = this.statusCombo.getSelectedItem().toString();
				
				this.grandTotal = (this.grandTotal + cost);
				this.labelTotalAmount.setText(this.grandTotal+" BDT");	
				this.tableModel2.addRow(new Object[]{id,name,proDescription,cost,status});
				
				this.buttonConfirm.setEnabled(true);
				this.buttonRemove.setEnabled(true);			
														
				this.panel1.setVisible(true);
				this.panel3.setVisible(false);
			}
		}
		
		if(command.equals("Remove"))
		{	
			int selectedRow2 = table2.getSelectedRow();
			if(selectedRow2 != -1) 
			{					
				double deduct = Double.parseDouble (tableModel2.getValueAt(selectedRow2, 3).toString());
				this.grandTotal = this.grandTotal - deduct;
				this.labelTotalAmount.setText((this.grandTotal)+" BDT");
				this.tableModel2.removeRow(selectedRow2);
			}
			
			else
			{
				JOptionPane.showMessageDialog(null, "Please Select a Row");
			}	
		}
		
		if(command.equals("Confirm"))
		{	
			int k = this.tableModel2.getRowCount();
			while (k>0)
			{			
				int productId = Integer.parseInt(tableModel2.getValueAt(k-1, 0).toString());
				String proDescription = tableModel2.getValueAt(k-1, 2).toString();
				double cost = Double.parseDouble(tableModel2.getValueAt(k-1, 3).toString());
				String status = tableModel2.getValueAt(k-1, 4).toString();
				
				//insert product_services
				ProductServices ProductService = new ProductServices();
				ProductService.setProductId(productId);
				ProductService.setProblemDescription(proDescription);
				ProductService.setRepairCost(cost);
				ProductService.setStatus(status);
				
				ProductServiceAdapter adapter = new ProductServiceAdapter();
				adapter.insertToProductService(ProductService);
								
				k--;
			}
			
			ConfirmService confirm = new ConfirmService(this.grandTotal);
			confirm.setVisible(true);
			confirm.setLocationRelativeTo(null);
			
			//Table-2 clear
			this.tableModel2.setRowCount(0);
			this.grandTotal = 0.0;
			this.labelTotalAmount.setText(this.grandTotal+" BDT");
		}
	}
	
	
	private void InitializeComponents()
	{		
		this.panel1 = new JPanel();
		this.panel1.setLayout(null);
		this.panel1.setBounds(0, 0, 600, 300);
		this.panel1.setBackground(Color.GRAY);
		
		//Panel-1 Initialize
		this.labelSearch = new JLabel ("Search");
		this.labelSearch.setBounds(80,5,150,20);
		this.panel1.add(this.labelSearch);
		this.labelSearch.setForeground(Color.orange);
		this.labelSearch.setFont(labelSearch.getFont ().deriveFont (16.0f));
		
		this.textboxSearch = new JTextField();
		this.textboxSearch.setBounds(140,5,200,20);
		this.panel1.add(this.textboxSearch);
		
		this.buttonSelectProduct = new JButton("Select");
		this.buttonSelectProduct.setBounds(400,5,100,20);
		this.buttonSelectProduct.addActionListener(this);
		this.panel1.add(this.buttonSelectProduct);
		
		//table-1 view
		ProductAdapter adapter= new ProductAdapter ();		
		Vector<String> columns= new Vector<String>();
		columns.add("Serial");
		columns.add("Product Name");		
		columns.add("Brand");
		
		this.tableModel= new DefaultTableModel(adapter.getProductList3(),columns);	
		this.table= new JTable (this.tableModel);
		this.rowSorter = new TableRowSorter<>(this.table.getModel());
		this.table.setRowSorter(rowSorter);

		this.scrollPane= new JScrollPane (this.table);
		this.scrollPane.setBounds(0	, 30,590, 270);
		this.panel1.add(this.scrollPane);

		
		//-------- Panel-2 ---------//
		if(this.panel2 == null)
		{
			this.panel2 = new JPanel();
			this.panel2.setLayout(null);
			this.panel2.setBounds(0, 310, 600, 300);
			this.panel2.setBackground(Color.GRAY);
			
			Vector<String> column= new Vector<String>();
			column.add("Product ID");
			column.add("Name");
			column.add("Problem");
			column.add("Cost");
			column.add("Status");
			
			this.tableModel2= new DefaultTableModel(null,column);	
			this.table2= new JTable (this.tableModel2);
			this.scrollPane2= new JScrollPane (this.table2);
			this.scrollPane2.setBounds(0,0,390,258);
			this.panel2.add(this.scrollPane2);
			
			this.labelGrand = new JLabel ("Total Cost");
			this.labelGrand.setBounds(439, 0, 120, 40);
			this.panel2.add(this.labelGrand);
			this.labelGrand.setForeground(Color.orange);
			this.labelGrand.setFont(labelGrand.getFont ().deriveFont (20.0f));
			
			this.labelTotalAmount = new JLabel ();
			this.labelTotalAmount.setBounds(448, 30, 120, 40);
			this.panel2.add(this.labelTotalAmount);
			this.labelTotalAmount.setForeground(Color.GREEN);
			this.labelTotalAmount.setFont(labelTotalAmount.getFont ().deriveFont (17.0f));
			
			this.buttonConfirm = new JButton("Confirm");
			this.buttonConfirm.setBounds(440, 125, 110, 20);
			this.buttonConfirm.addActionListener(this);
			this.buttonConfirm.setEnabled(false);
			this.panel2.add(this.buttonConfirm);
			
			this.buttonRemove = new JButton("Remove");
			this.buttonRemove.setBounds(440, 150, 110, 20);
			this.buttonRemove.addActionListener(this);
			this.buttonRemove.setEnabled(false);
			this.panel2.add(this.buttonRemove);
			
			this.add(this.panel2);
		}	
		this.panel2.revalidate();
		
		
		this.add(this.panel1);
		this.setSize(600, 600);	
			
		
		//Table Filter
		textboxSearch.getDocument().addDocumentListener(new DocumentListener()
		{	        
			@Override
			public void insertUpdate(DocumentEvent e) {
				String text = textboxSearch.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}
		  
			@Override
			public void removeUpdate(DocumentEvent e) {
				String text = textboxSearch.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}	        
        });
	}
	
	private void panel3()
	{
		this.panel3 = new JPanel();
		this.panel3.setLayout(null);
		this.panel3.setBounds(0, 0, 600, 310);
		this.panel3.setBackground(Color.LIGHT_GRAY);
			
		this.proId = new JLabel ("Product ID");
		this.proId.setBounds(120, 20, 110, 20);
		this.panel3.add(this.proId);
		
		this.proName= new JLabel ("Product Name");
		this.proName.setBounds(120, 50, 110, 20);
		this.panel3.add(this.proName);	
		
		this.problem= new JLabel ("Problem Description");
		this.problem.setBounds(120, 80, 140, 20);
		this.panel3.add(this.problem);
		
		this.repCost= new JLabel ("Repair Cost");
		this.repCost.setBounds(120, 120, 110, 20);
		this.panel3.add(this.repCost);
		
		this.status= new JLabel ("Status");
		this.status.setBounds(120, 150, 110, 20);
		this.panel3.add(this.status);
		
		this.proIdText = new JTextField(this.tableModel.getValueAt(i, 0).toString());
		this.proIdText.setBounds(340,20,120,20);
		this.proIdText.setEditable(false);
		this.panel3.add(this.proIdText);
		
		this.proNameText = new JTextField(this.tableModel.getValueAt(i, 1).toString());
		this.proNameText.setBounds(340,50,120,20);
		this.proNameText.setEditable(false);
		this.panel3.add(this.proNameText);
		
		this.probDesText = new JTextArea();
		this.probDesText.setBounds(340,80,120,30);
		this.panel3.add(this.probDesText);
		
		this.repCostText = new JTextField();
		this.repCostText.setBounds(340,120,120,20);
		this.panel3.add(this.repCostText);
		this.repCostText.addKeyListener(new KeyAdapter() {
			   public void keyTyped(KeyEvent e) {
			      char c = e.getKeyChar();
			      if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
			    		  && (c !='+'))
			      {
			         e.consume();  // ignore event
			      }
			   }
			});
		
		String[] List = new String[]{"Emergency","Normal"};
		this.statusCombo = new JComboBox(List);
		this.statusCombo.setBounds(340,150,120,20);
		this.panel3.add(this.statusCombo);
		
		this.buttonAddProduct = new JButton("ADD");
		this.buttonAddProduct.setBounds(180,260,100,20);
		this.buttonAddProduct.addActionListener(this);
		this.panel3.add(this.buttonAddProduct);
		
		this.buttonCancel = new JButton("Cancel");
		this.buttonCancel.setBounds(300,260,100,20);
		this.buttonCancel.addActionListener(this);
		this.panel3.add(this.buttonCancel);
		
		this.add(this.panel3);
		this.add(this.panel1);
		this.setSize(600, 600);	
	}	
}