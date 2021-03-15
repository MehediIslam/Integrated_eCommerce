package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

import adapter.ProductPurchaseAdapter;
import adapter.ProductServiceAdapter;

import java.awt.Color;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class PurchaseShipmentList extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private TableRowSorter<TableModel> rowSorter;
	private JPanel panel;
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel labelSearch;
	private JTextField textboxSearch;
	int i;

	/* =========== Product Sale list table [Done by rup]=========== */
	public PurchaseShipmentList() {
		super("Purchase Shipment", true, true, true, true);
		this.InitializeComponents();
		//this.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {

	}

	public void InitializeComponents() {
		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.panel.setBounds(0, 0, 630, 350);
		this.panel.setBackground(Color.GRAY);

		// table view
		ProductPurchaseAdapter adapter = new ProductPurchaseAdapter();
		Vector<String> columns = new Vector<String>();
		columns.add("Serial");
		columns.add("Purchaser");
		columns.add("Supplier");
		columns.add("Total Charge");
		columns.add("Advance Pay");
		columns.add("Receiver");
		columns.add("Approx Date");
		columns.add("Receive Date");
		columns.add("Status");

		this.tableModel = new DefaultTableModel(
				adapter.getPurchaseShipmentList(), columns);
		this.table = new JTable(this.tableModel);
		this.rowSorter = new TableRowSorter<>(this.table.getModel());
		this.table.setRowSorter(rowSorter);

		this.scrollPane = new JScrollPane(this.table);
		this.scrollPane.setBounds(0, 45, 670, 320);
		this.panel.add(this.scrollPane);

		this.labelSearch = new JLabel("Search");
		this.labelSearch.setBounds(200, 10, 150, 20);
		this.panel.add(this.labelSearch);
		this.labelSearch.setForeground(Color.orange);
		this.labelSearch.setFont(labelSearch.getFont().deriveFont(16.0f));

		this.textboxSearch = new JTextField();
		this.textboxSearch.setBounds(260, 10, 180, 20);
		this.panel.add(this.textboxSearch);

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
