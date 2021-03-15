package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Purchase;
import adapter.ProductPurchaseAdapter;
import adapter.SuppliersAdapter;

import java.awt.Color;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class ConfirmPurchase extends JFrame implements ActionListener {
	private JPanel panel;
	private JLabel labelSuppID, labelGrand, labelPaid, labelappDate,
			labelSearch;
	private JTextField textGrandTotal, textSupId, textPaid, textboxSearch;
	private JButton buttonConfirm;
	private double grandtotal = 0;
	private JComboBox d, m, y;
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	private TableRowSorter<TableModel> rowSorter;
	private double ret = 0;

	ConfirmPurchase(double grandTotal) {
		this.grandtotal = grandTotal;
		this.setTitle("Confirm Purchase");
		this.initialize();
	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Purchase")) {

			if (this.textSupId.getText().trim().equals("")
					|| this.textPaid.getText().trim().equals("")
					|| this.d.getSelectedItem().equals("DD")
					|| this.m.getSelectedItem().equals("MM")
					|| this.y.getSelectedItem().equals("YYYY")) {
				JOptionPane.showMessageDialog(null,
						"Please Fillup Required Field");
			}

			else {
				String dd = this.d.getSelectedItem().toString();
				String mm = this.m.getSelectedItem().toString();
				String yy = this.y.getSelectedItem().toString();

				int supplierId = Integer.parseInt(this.textSupId.getText()
						.toString());
				double totalCharge = Double.parseDouble(this.textGrandTotal
						.getText().toString());
				double advancePay = Double.parseDouble(this.textPaid.getText()
						.toString());
				String appDate = yy + "-" + mm + "-" + dd;

				Purchase purchase = new Purchase();
				if (advancePay < this.grandtotal) {
					purchase.setAdvancePay(advancePay);
				}

				else {
					purchase.setAdvancePay(this.grandtotal);
				}

				purchase.setSupplierId(supplierId);
				purchase.setTotalCharge(totalCharge);

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
				try {
					purchase.setApproxReceiveDate(formatter.parse(appDate));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				ProductPurchaseAdapter adapter = new ProductPurchaseAdapter();
				adapter.insertToPurchases(purchase);

				// return money
				ret = advancePay - this.grandtotal;
				JOptionPane.showMessageDialog(null, "Return Money: " + ret
						+ " BDT");
				this.setVisible(false);
				this.updateProductPurchase();
			}
		}
	}

	public void updateProductPurchase() {
		int supId = Integer.parseInt(this.textSupId.getText().toString());
		ProductPurchaseAdapter adapter2 = new ProductPurchaseAdapter();
		adapter2.setPurchaseId();
		// adapter2.setSuppId(supId);
	}

	public void initialize() {
		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.panel.setBackground(Color.LIGHT_GRAY);

		this.labelSuppID = new JLabel("Supplier ID");
		this.labelSuppID.setBounds(10, 40, 110, 20);
		this.panel.add(this.labelSuppID);

		this.labelGrand = new JLabel("Grand Total");
		this.labelGrand.setBounds(10, 70, 110, 20);
		this.panel.add(this.labelGrand);

		this.labelPaid = new JLabel("Advance Paid");
		this.labelPaid.setBounds(10, 100, 110, 20);
		this.panel.add(this.labelPaid);

		this.labelappDate = new JLabel("Approx Date");
		this.labelappDate.setBounds(10, 130, 110, 20);
		this.panel.add(this.labelappDate);

		this.textSupId = new JTextField();
		this.textSupId.setBounds(120, 40, 155, 20);
		this.panel.add(this.textSupId);
		this.textSupId.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.textGrandTotal = new JTextField(this.grandtotal + "");
		this.textGrandTotal.setBounds(120, 70, 155, 20);
		this.textGrandTotal.setEditable(false);
		this.panel.add(this.textGrandTotal);

		this.textPaid = new JTextField();
		this.textPaid.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.textPaid.setBounds(120, 100, 155, 20);
		this.panel.add(this.textPaid);

		String[] List1 = new String[] { "DD", "01", "02", "03", "04", "05",
				"06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
				"26", "27", "28", "29", "30", "31" };
		this.d = new JComboBox(List1);
		this.d.setBounds(120, 130, 45, 20);
		this.panel.add(this.d);

		String[] List2 = new String[] { "MM", "01", "02", "03", "04", "05",
				"06", "07", "08", "09", "10", "11", "12" };
		this.m = new JComboBox(List2);
		this.m.setBounds(170, 130, 45, 20);
		this.panel.add(this.m);

		String[] List3 = new String[] { "YYYY", "2014", "2015", "2016", "2017",
				"2018", "2019", "2020", "2021", "2022", "2023", "2024" };
		this.y = new JComboBox(List3);
		this.y.setBounds(220, 130, 55, 20);
		this.panel.add(this.y);

		this.buttonConfirm = new JButton("Purchase");
		this.buttonConfirm.setBounds(140, 210, 110, 20);
		this.buttonConfirm.addActionListener(this);
		this.panel.add(this.buttonConfirm);

		// table view
		SuppliersAdapter adapter1 = new SuppliersAdapter();
		Vector<String> column = new Vector<String>();
		column.add("Supplier ID");
		column.add("Name");
		column.add("Phone");

		this.tableModel = new DefaultTableModel(adapter1.SuppliersList(),
				column);
		this.table = new JTable(this.tableModel);
		this.rowSorter = new TableRowSorter<>(this.table.getModel());
		this.table.setRowSorter(rowSorter);

		this.scrollPane = new JScrollPane(this.table);
		this.scrollPane.setBounds(300, 40, 280, 190);
		this.panel.add(this.scrollPane);

		this.labelSearch = new JLabel("Search");
		this.labelSearch.setBounds(320, 10, 150, 20);
		this.panel.add(this.labelSearch);
		this.labelSearch.setFont(labelSearch.getFont().deriveFont(16.0f));

		this.textboxSearch = new JTextField();
		this.textboxSearch.setBounds(380, 10, 170, 20);
		this.panel.add(this.textboxSearch);

		this.add(this.panel);
		this.setSize(610, 300);

		// table filter
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
