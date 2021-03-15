package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

import adapter.ProductServiceAdapter;

import java.awt.Color;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class ServiceShipmentList extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private TableRowSorter<TableModel> rowSorter;
	private JPanel panel;
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel labelSearch;
	private JTextField textboxSearch;
	private JButton buttonDone;
	int i;

	/* =========== Product Sale list table [Done by rup]=========== */
	public ServiceShipmentList() {
		super("Service Shipment", true, true, true, true);
		this.InitializeComponents();
		//this.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		// selection row
		String command = event.getActionCommand();
		if (command.equals("Deliver")) {
			i = table.getSelectedRow();
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				Integer shipmentID = Integer.parseInt(tableModel.getValueAt(i,
						0).toString());

				ProductServiceAdapter adapter1 = new ProductServiceAdapter();
				adapter1.updateServiceShipmentDeliverDate(shipmentID);

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();

				// table refresh
				this.tableModel.setValueAt(dateFormat.format(date), i, 6);

				JOptionPane.showMessageDialog(null, "Successfully Delivered");
			} else {
				JOptionPane.showMessageDialog(null, "Please Select a Row");
			}
		}
	}

	public void InitializeComponents() {
		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.panel.setBounds(0, 0, 630, 350);
		this.panel.setBackground(Color.GRAY);

		// table view
		ProductServiceAdapter adapter = new ProductServiceAdapter();
		Vector<String> columns = new Vector<String>();
		columns.add("Serial");
		columns.add("Customer");
		columns.add("Total Charges");
		columns.add("Advance Pay");
		columns.add("Receive Date");
		columns.add("Receiver");
		columns.add("Delivery Date");
		columns.add("Status");

		this.tableModel = new DefaultTableModel(
				adapter.getServiceShipmentList(), columns);
		this.table = new JTable(this.tableModel);
		this.rowSorter = new TableRowSorter<>(this.table.getModel());
		this.table.setRowSorter(rowSorter);

		this.scrollPane = new JScrollPane(this.table);
		this.scrollPane.setBounds(0, 0, 670, 320);
		this.panel.add(this.scrollPane);

		this.labelSearch = new JLabel("Search");
		this.labelSearch.setBounds(20, 335, 150, 20);
		this.panel.add(this.labelSearch);
		this.labelSearch.setForeground(Color.orange);
		this.labelSearch.setFont(labelSearch.getFont().deriveFont(16.0f));

		this.textboxSearch = new JTextField();
		this.textboxSearch.setBounds(80, 335, 250, 20);
		this.panel.add(this.textboxSearch);

		this.buttonDone = new JButton("Deliver");
		this.buttonDone.setBounds(565, 335, 80, 20);
		this.buttonDone.addActionListener(this);
		this.panel.add(this.buttonDone);

		this.add(this.panel);
		this.setSize(680, 400);

		// Table Filter
		textboxSearch.getDocument().addDocumentListener(new DocumentListener() {
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
}
