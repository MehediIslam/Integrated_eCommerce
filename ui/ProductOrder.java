package ui;

import javax.swing.*;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Calendar;
import java.util.Vector;

class ProductOrder extends JInternalFrame implements ActionListener {
	private JPanel panel1, panel2, panel3;
	private JLabel labelProductId, labelProductName, labelSellPrice,
			labelQuantity, labelOrderId, labelOrdererId, labelTotalCharge,
			labelAdvancePay, labelDeliveryDate;
	private JTextField textSellPrice, textProductName, textQuantity,
			textProductId, textOrdererId, textOrderId, textTotalCharge,
			textDeliveryDate, textAdvancePay;
	private JButton buttonBack, buttonOk, buttonAddOrder, buttonAddProduct;
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	private JComboBox list;

	public ProductOrder() {
		super("Product Order", true, true, true, true);
		this.InitializeComponents();
	}

	public void actionPerformed(ActionEvent event) {

	}

	private void InitializeComponents() {

		this.panel1 = new JPanel();
		this.panel1.setLayout(null);
		this.panel1.setBounds(0, 0, 300, 300);
		this.panel1.setBackground(Color.green);

		this.labelProductId = new JLabel("Product Id");
		this.labelProductId.setBounds(20, 20, 110, 20);
		this.panel1.add(this.labelProductId);

		this.labelProductName = new JLabel("Product Name");
		this.labelProductName.setBounds(20, 40, 110, 20);
		this.panel1.add(this.labelProductName);

		this.labelSellPrice = new JLabel("Sell Price");
		this.labelSellPrice.setBounds(20, 60, 110, 20);
		this.panel1.add(this.labelSellPrice);

		this.labelQuantity = new JLabel("Quantity");
		this.labelQuantity.setBounds(20, 80, 110, 20);
		this.panel1.add(this.labelQuantity);

		this.textProductId = new JTextField();
		this.textProductId.setBounds(140, 20, 120, 20);
		this.panel1.add(this.textProductId);

		this.textProductName = new JTextField();
		this.textProductName.setBounds(140, 40, 120, 20);
		this.panel1.add(this.textProductName);

		this.textSellPrice = new JTextField();
		this.textSellPrice.setBounds(140, 60, 120, 20);
		this.panel1.add(this.textSellPrice);

		this.textQuantity = new JTextField();
		this.textQuantity.setBounds(140, 80, 120, 20);
		this.panel1.add(this.textQuantity);

		this.buttonAddProduct = new JButton("Add");
		this.buttonAddProduct.setBounds(180, 120, 80, 20);
		this.buttonAddProduct.addActionListener(this);
		this.panel1.add(this.buttonAddProduct);

		this.panel2 = new JPanel();
		this.panel2.setLayout(null);
		this.panel2.setBounds(300, 0, 300, 300);
		this.panel2.setBackground(Color.red);

		this.labelOrderId = new JLabel("Sell Id");
		this.labelOrderId.setBounds(20, 20, 110, 20);
		this.panel2.add(this.labelOrderId);

		this.labelOrdererId = new JLabel("Buyer Id");
		this.labelOrdererId.setBounds(20, 40, 110, 20);
		this.panel2.add(this.labelOrdererId);

		this.labelTotalCharge = new JLabel("Total Charge");
		this.labelTotalCharge.setBounds(20, 60, 110, 20);
		this.panel2.add(this.labelTotalCharge);

		this.labelAdvancePay = new JLabel("Quantity");
		this.labelAdvancePay.setBounds(20, 80, 110, 20);
		this.panel2.add(this.labelAdvancePay);

		this.labelDeliveryDate = new JLabel("Quantity");
		this.labelDeliveryDate.setBounds(20, 100, 110, 20);
		this.panel2.add(this.labelDeliveryDate);

		this.textOrderId = new JTextField();
		this.textOrderId.setBounds(140, 20, 120, 20);
		this.panel2.add(this.textOrderId);

		this.textOrdererId = new JTextField();
		this.textOrdererId.setBounds(140, 40, 120, 20);
		this.panel2.add(this.textOrdererId);

		this.textTotalCharge = new JTextField();
		this.textTotalCharge.setBounds(140, 60, 120, 20);
		this.panel2.add(this.textTotalCharge);

		this.textAdvancePay = new JTextField();
		this.textAdvancePay.setBounds(140, 80, 120, 20);
		this.panel2.add(this.textAdvancePay);

		this.textDeliveryDate = new JTextField();
		this.textDeliveryDate.setBounds(140, 100, 120, 20);
		this.panel2.add(this.textDeliveryDate);

		this.buttonAddOrder = new JButton("Add");
		this.buttonAddOrder.setBounds(180, 140, 80, 20);
		this.buttonAddOrder.addActionListener(this);
		this.panel2.add(this.buttonAddOrder);

		this.panel3 = new JPanel();
		this.panel3.setLayout(null);
		this.panel3.setBounds(0, 300, 600, 300);
		this.panel3.setBackground(Color.blue);

		this.panel3 = new JPanel();
		this.panel3.setLayout(null);

		Vector<String> columns = new Vector<String>();
		columns.add("Account From");
		columns.add("Account To");
		columns.add("Amount");
		columns.add("Time Of Transaction");

		this.tableModel = new DefaultTableModel();

		this.table = new JTable(this.tableModel);

		this.scrollPane = new JScrollPane(this.table);
		this.scrollPane.setBounds(0, 0, 400, 200);
		this.panel3.add(this.scrollPane);

		this.buttonOk = new JButton("Ok");
		this.buttonOk.addActionListener(this);
		this.buttonOk.setBounds(250, 520, 80, 20);
		this.panel3.add(this.buttonOk);

		this.buttonBack = new JButton("Back");
		this.buttonBack.addActionListener(this);
		this.buttonBack.setBounds(350, 520, 80, 20);
		this.panel3.add(this.buttonBack);
		String[] List = new String[] { "Profiles", "Member", "Login" };

		this.list = new JComboBox(List);
		this.list.setBounds(80, 520, 140, 20);
		this.panel3.add(this.list);

		this.add(this.panel3);
		this.add(this.panel1);
		this.add(this.panel2);
		this.add(this.panel3);

		this.setSize(600, 600);
	}
}