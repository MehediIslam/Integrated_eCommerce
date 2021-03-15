package ui;

import javax.swing.*;
import javax.swing.table.*;

import model.CustomerDiscount;
import adapter.CustomerDiscountAdapter;

import java.awt.Color;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class CustomerDiscountList extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel panel, panel1;
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton buttonEdit;
	private JScrollPane scrollPane;
	private JLabel labelUserId, labelDiscount, labelStartDate, labelEndDate;
	private JButton buttonDone, buttonCancel;
	private JTextField textUserId;
	private JTextField textDiscount;
	private JTextField textStartDate;
	private JTextField textEndDate;
	int i;

	/* =========== Customer Discount List table [Done by rup]=========== */

	public CustomerDiscountList() {
		super("Customer Discount List", true, true, true, true);
		this.InitializeComponents();
		//this.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		// selection row
		String command = event.getActionCommand();
		if (command.equals("Edit")) {
			i = table.getSelectedRow();
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				InitializeComponentsPanel1();
			} else {
				JOptionPane.showMessageDialog(null, "Please Select a Row");
			}
		}

		else if (command.equals("Done")) {
			CustomerDiscount dis = new CustomerDiscount();
			Integer serial = Integer.parseInt(tableModel.getValueAt(i, 0)
					.toString());
			Integer userID = Integer.parseInt(this.textUserId.getText()
					.toString());
			double disc = Double.parseDouble(this.textDiscount.getText()
					.toString());

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String startDate = this.textStartDate.getText().toString();
			String endDate = this.textEndDate.getText().toString();

			dis.setId(serial);
			dis.setUserId(userID);
			dis.setDiscount(disc);

			try {
				dis.setStartDate(formatter.parse(this.textStartDate.getText()));
				dis.setEndDate(formatter.parse(this.textEndDate.getText()));

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// table refresh
			this.tableModel.setValueAt(serial, i, 0);
			this.tableModel.setValueAt(userID, i, 1);
			this.tableModel.setValueAt(disc, i, 2);
			this.tableModel.setValueAt(startDate, i, 3);
			this.tableModel.setValueAt(endDate, i, 4);

			// Update Query function calling
			CustomerDiscountAdapter adapter = new CustomerDiscountAdapter();
			adapter.customerdiscountEdit(dis);

			this.panel1.setVisible(false);
			this.setSize(638, 400);
		}

		else if (command.equals("Cancel")) {
			this.panel1.setVisible(false);
			this.setSize(638, 400);
		}
	}

	public void InitializeComponents() {
		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.panel.setBounds(0, 0, 630, 350);

		// table view
		CustomerDiscountAdapter adapter = new CustomerDiscountAdapter();
		Vector<String> columns = new Vector<String>();
		columns.add("Serial");
		columns.add("User ID");
		columns.add("Discount");
		columns.add("Start Date");
		columns.add("End Date");

		this.tableModel = new DefaultTableModel(adapter.getCustDiscountList(),
				columns);
		this.table = new JTable(this.tableModel);

		this.scrollPane = new JScrollPane(this.table);
		this.scrollPane.setBounds(0, 0, 630, 300);
		this.panel.add(this.scrollPane);

		this.buttonEdit = new JButton("Edit");
		this.buttonEdit.setBounds(540, 340, 80, 20);
		this.buttonEdit.addActionListener(this);
		this.panel.add(this.buttonEdit);

		this.add(this.panel);
		this.setSize(638, 400);
	}

	public void InitializeComponentsPanel1() {
		// Editing Panel1
		this.panel1 = new JPanel();
		this.panel1.setLayout(null);
		this.panel1.setBounds(0, 370, 630, 370);
		this.panel1.setBackground(Color.lightGray);

		this.labelUserId = new JLabel("User ID");
		this.labelUserId.setBounds(20, 30, 110, 20);
		this.panel1.add(this.labelUserId);

		this.labelDiscount = new JLabel("Discount");
		this.labelDiscount.setBounds(20, 70, 110, 20);
		this.panel1.add(this.labelDiscount);

		this.labelStartDate = new JLabel("Star tDate");
		this.labelStartDate.setBounds(20, 110, 110, 20);
		this.panel1.add(this.labelStartDate);

		this.labelEndDate = new JLabel("End Date");
		this.labelEndDate.setBounds(20, 150, 110, 20);
		this.panel1.add(this.labelEndDate);

		// Data passing to edit Panel1
		this.textUserId = new JTextField(tableModel.getValueAt(i, 1).toString());
		this.textUserId.setBounds(140, 30, 120, 20);
		this.panel1.add(this.textUserId);

		this.textDiscount = new JTextField(tableModel.getValueAt(i, 2)
				.toString());
		this.textDiscount.setBounds(140, 70, 120, 20);
		this.panel1.add(this.textDiscount);

		this.textStartDate = new JTextField(tableModel.getValueAt(i, 3)
				.toString());
		this.textStartDate.setBounds(140, 110, 120, 20);
		this.panel1.add(this.textStartDate);

		this.textEndDate = new JTextField(tableModel.getValueAt(i, 4)
				.toString());
		this.textEndDate.setBounds(140, 150, 120, 20);
		this.panel1.add(this.textEndDate);

		this.buttonDone = new JButton("Done");
		this.buttonDone.setBounds(540, 150, 80, 20);
		this.buttonDone.addActionListener(this);
		this.panel1.add(this.buttonDone);

		this.buttonCancel = new JButton("Cancel");
		this.buttonCancel.setBounds(540, 120, 80, 20);
		this.buttonCancel.addActionListener(this);
		this.panel1.add(this.buttonCancel);

		this.add(this.panel1);
		this.add(this.panel);
		this.setSize(638, 590);
		this.revalidate();
	}
}
