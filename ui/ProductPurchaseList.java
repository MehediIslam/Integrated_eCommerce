package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

import model.Product;
import model.ProductPurchases;
import adapter.ProductAdapter;
import adapter.ProductPurchaseAdapter;

import java.awt.Color;
import java.awt.event.*;
import java.util.*;

class ProductPurchaseList extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private TableRowSorter<TableModel> rowSorter;
	private JPanel panel, panel1;
	private DefaultTableModel tableModel;
	private JTable table;
	private JComboBox statusCombo;
	private JScrollPane scrollPane;
	private JButton buttonDone, buttonCancel, buttonEdit;
	private JLabel labelPurchaseId, labelProductId, labelBuyingPrice,
			labelQuantity, labelStatus, labelSearch;
	private JTextField textPurchaseId, textProductId, textlBuyingPrice,
			textQuantity, textStatus, textboxSearch;
	int i;

	/* =========== Produst Purchase List table [Done by rup]=========== */

	public ProductPurchaseList() {
		super("Purchase List", true, true, true, true);
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
			ProductPurchases pur = new ProductPurchases();
			Product product = new Product();

			Integer sl = Integer.parseInt(tableModel.getValueAt(i, 0)
					.toString());
			Integer purchaseID = Integer.parseInt(this.textPurchaseId.getText()
					.toString());
			Integer proID = Integer.parseInt(this.textProductId.getText()
					.toString());
			double BuyPrice = Double.parseDouble(this.textlBuyingPrice
					.getText().toString());
			Integer Quantity = Integer.parseInt(this.textQuantity.getText()
					.toString());
			String sts = this.statusCombo.getSelectedItem().toString();

			pur.setId(sl);
			pur.setPurchaseId(purchaseID);
			pur.setProductId(proID);
			pur.setBuyPrice(BuyPrice);
			pur.setQuantity(Quantity);
			pur.setStatus(sts);

			// table refresh
			this.tableModel.setValueAt(BuyPrice, i, 4);
			this.tableModel.setValueAt(Quantity, i, 5);
			this.tableModel.setValueAt(sts, i, 6);

			// edit query function calling
			ProductPurchaseAdapter adapter = new ProductPurchaseAdapter();
			adapter.productpurchaseEdit(pur);

			if (sts == "Complete") {
				product.setProductId(proID);
				product.setQuantity(Quantity);
				product.setBuyPrice(BuyPrice);
				ProductAdapter adpt = new ProductAdapter();
				adpt.updateLastPurchase(product);

				int purchase_id = Integer.parseInt(tableModel.getValueAt(i, 1)
						.toString());
				ProductPurchaseAdapter productPurchaseAdapter = new ProductPurchaseAdapter();
				productPurchaseAdapter
						.updatePurchaseShipmentStatus(purchase_id);
				productPurchaseAdapter.setLastSuppId(proID,purchaseID);
			}

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
		this.panel.setBackground(Color.GRAY);

		// table view
		ProductPurchaseAdapter adapter = new ProductPurchaseAdapter();
		Vector<String> columns = new Vector<String>();
		columns.add("Serial");
		columns.add("Purchase ID");
		columns.add("Product ID");
		columns.add("Product");
		columns.add("Buying Price");
		columns.add("Quantity");
		columns.add("Status");

		this.tableModel = new DefaultTableModel(adapter.getPurchaseList(),
				columns);
		this.table = new JTable(this.tableModel);
		this.rowSorter = new TableRowSorter<>(this.table.getModel());
		this.table.setRowSorter(rowSorter);

		this.scrollPane = new JScrollPane(this.table);
		this.scrollPane.setBounds(0, 0, 628, 320);
		this.panel.add(this.scrollPane);

		this.labelSearch = new JLabel("Search");
		this.labelSearch.setBounds(5, 340, 150, 20);
		this.panel.add(this.labelSearch);
		this.labelSearch.setForeground(Color.orange);
		this.labelSearch.setFont(labelSearch.getFont().deriveFont(16.0f));

		this.textboxSearch = new JTextField();
		this.textboxSearch.setBounds(70, 340, 180, 20);
		this.panel.add(this.textboxSearch);

		this.buttonEdit = new JButton("Edit");
		this.buttonEdit.setBounds(540, 340, 80, 20);
		this.buttonEdit.addActionListener(this);
		this.panel.add(this.buttonEdit);

		this.add(this.panel);
		this.setSize(638, 400);

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

	public void InitializeComponentsPanel1() {
		// Editing Panel1
		this.panel1 = new JPanel();
		this.panel1.setLayout(null);
		this.panel1.setBounds(0, 370, 630, 370);
		this.panel1.setBackground(Color.lightGray);

		this.labelPurchaseId = new JLabel("Purchase ID");
		this.labelPurchaseId.setBounds(20, 30, 110, 20);
		this.panel1.add(this.labelPurchaseId);

		this.labelProductId = new JLabel("Product ID");
		this.labelProductId.setBounds(20, 70, 110, 20);
		this.panel1.add(this.labelProductId);

		this.labelBuyingPrice = new JLabel("Buying Price");
		this.labelBuyingPrice.setBounds(20, 110, 110, 20);
		this.panel1.add(this.labelBuyingPrice);

		this.labelQuantity = new JLabel("Quantity");
		this.labelQuantity.setBounds(20, 150, 110, 20);
		this.panel1.add(this.labelQuantity);

		this.labelStatus = new JLabel("Status");
		this.labelStatus.setBounds(20, 190, 110, 20);
		this.panel1.add(this.labelStatus);

		// Data passing to Edit panel1
		this.textPurchaseId = new JTextField(tableModel.getValueAt(i, 1)
				.toString());
		this.textPurchaseId.setBounds(140, 30, 120, 20);
		this.textPurchaseId.setEditable(false);
		this.panel1.add(this.textPurchaseId);

		this.textProductId = new JTextField(tableModel.getValueAt(i, 2)
				.toString());
		this.textProductId.setBounds(140, 70, 120, 20);
		this.textProductId.setEditable(false);
		this.panel1.add(this.textProductId);

		this.textlBuyingPrice = new JTextField(tableModel.getValueAt(i, 4)
				.toString());
		this.textlBuyingPrice.setBounds(140, 110, 120, 20);
		this.panel1.add(this.textlBuyingPrice);
		this.textlBuyingPrice.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.textQuantity = new JTextField(tableModel.getValueAt(i, 5)
				.toString());
		this.textQuantity.setBounds(140, 150, 120, 20);
		this.panel1.add(this.textQuantity);
		this.textQuantity.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		String[] List = new String[] { "Processing", "Incomplete", "Complete" };
		this.statusCombo = new JComboBox(List);
		this.statusCombo.setBounds(140, 190, 120, 20);
		this.panel1.add(this.statusCombo);

		this.buttonDone = new JButton("Done");
		this.buttonDone.setBounds(540, 190, 80, 20);
		this.buttonDone.addActionListener(this);
		this.panel1.add(this.buttonDone);

		this.buttonCancel = new JButton("Cancel");
		this.buttonCancel.setBounds(540, 160, 80, 20);
		this.buttonCancel.addActionListener(this);
		this.panel1.add(this.buttonCancel);

		this.add(this.panel1);
		this.add(this.panel);
		this.setSize(638, 630);
		this.revalidate();
	}
}
