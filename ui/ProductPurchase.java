package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.ProductPurchases;
import adapter.ProductAdapter;
import adapter.ProductPurchaseAdapter;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

class ProductPurchase extends JInternalFrame implements ActionListener {
	private JPanel panel1, panel2;
	private JTable table, table2;
	private JComboBox statusCombo;
	private JScrollPane scrollPane, scrollPane3;
	private TableRowSorter<TableModel> rowSorter;
	private DefaultTableModel tableModel, tableModel2;
	private JButton buttonSelectProduct, buttonAdd, buttonChange, buttonRemove,
			buttonConfirm;
	private JTextField textboxSearch, textProId, textBuyPrice, textQuantity;
	private JLabel labelStatus, labelSearch, labelProID, labelBuyPrice,
			labelQuantity, labelGrand, labelTotalAmount;
	private double grandTotal = 0;
	int i;

	public ProductPurchase() {
		super("Product Purchase", true, true, true, true);
		this.InitializeComponents();
	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Add")) {
			if (this.textProId.getText().trim().equals("")
					|| this.textBuyPrice.getText().trim().equals("")
					|| this.textQuantity.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null,
						"Please Fillup Required Field");
			}

			else {
				try {
					int z = this.tableModel2.getRowCount();
					if (z != 0) {
						while (z > 0) {
							if (Integer.parseInt(this.textProId.getText()
									.toString()) == Integer
									.parseInt(this.tableModel2.getValueAt(
											z - 1, 0).toString())) {
								JOptionPane.showMessageDialog(null,
										"Already Added");
								break;
							}

							else {
								int proId = Integer.parseInt(this.textProId
										.getText().toString());
								double buyPrice = Double
										.parseDouble(this.textBuyPrice
												.getText().toString());
								int quantity = Integer
										.parseInt(this.textQuantity.getText()
												.toString());
								String status = this.statusCombo
										.getSelectedItem().toString();
								double total = (buyPrice * quantity);

								this.grandTotal = (this.grandTotal + total);
								this.labelTotalAmount.setText(this.grandTotal
										+ " BDT");
								this.tableModel2.addRow(new Object[] { proId,
										buyPrice, quantity, status, total });

								this.textProId.setText("");
								this.textBuyPrice.setText("");
								this.textQuantity.setText("");
								this.buttonConfirm.setEnabled(true);
								this.buttonChange.setEnabled(true);
								this.buttonRemove.setEnabled(true);
								this.buttonAdd.setEnabled(false);
							}
						}
					}

					else {
						int proId = Integer.parseInt(this.textProId.getText()
								.toString());
						double buyPrice = Double.parseDouble(this.textBuyPrice
								.getText().toString());
						int quantity = Integer.parseInt(this.textQuantity
								.getText().toString());
						String status = this.statusCombo.getSelectedItem()
								.toString();
						double total = (buyPrice * quantity);

						this.grandTotal = (this.grandTotal + total);
						this.labelTotalAmount.setText(this.grandTotal + " BDT");
						this.tableModel2.addRow(new Object[] { proId, buyPrice,
								quantity, status, total });

						this.textProId.setText("");
						this.textBuyPrice.setText("");
						this.textQuantity.setText("");
						this.buttonConfirm.setEnabled(true);
						this.buttonChange.setEnabled(true);
						this.buttonRemove.setEnabled(true);
						this.buttonAdd.setEnabled(false);
					}

				}

				catch (Exception ex) {

				}
			}
		}

		if (command.equals("Remove")) {
			int selectedRow2 = table2.getSelectedRow();
			if (selectedRow2 != -1) {
				double deduct = Double.parseDouble(tableModel2.getValueAt(
						selectedRow2, 4).toString());
				this.grandTotal = this.grandTotal - deduct;
				this.labelTotalAmount.setText((this.grandTotal) + " BDT");
				this.tableModel2.removeRow(selectedRow2);
			}

			else {
				JOptionPane.showMessageDialog(null, "Please Select a Row");
			}
		}

		if (command.equals("Change")) {
			i = table2.getSelectedRow();
			int selectedRow1 = i;
			if (selectedRow1 != -1) {
				this.buttonAdd.setEnabled(true);
				this.buttonAdd.setText("OK");
				this.buttonAdd.setBackground(Color.GREEN);

				this.textProId.setText(tableModel2.getValueAt(i, 0).toString());
				this.textBuyPrice.setText(tableModel2.getValueAt(i, 1)
						.toString());
				this.textQuantity.setText(tableModel2.getValueAt(i, 2)
						.toString());
				this.statusCombo.setSelectedItem(tableModel2.getValueAt(i, 3)
						.toString());
			}

			else {
				JOptionPane.showMessageDialog(null, "Please Select a Row");
			}
		}

		if (command.equals("Select")) {
			i = table.getSelectedRow();
			int selectedRow1 = i;
			if (selectedRow1 != -1) {
				this.textProId.setText(tableModel.getValueAt(i, 0).toString());
				this.textBuyPrice.setText(tableModel.getValueAt(i, 2)
						.toString());
				this.buttonAdd.setEnabled(true);
			}

			else {
				JOptionPane.showMessageDialog(null, "Please Select a Row");
			}
		}

		if (command.equals("OK")) {
			if (this.textProId.getText().trim().equals("")
					|| this.textBuyPrice.getText().trim().equals("")
					|| this.textQuantity.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null,
						"Please Fillup Required Field");
			}

			else {
				int changeProId = Integer.parseInt(this.textProId.getText());
				double changeBuyPrice = Double.parseDouble(this.textBuyPrice
						.getText());
				int changeQuantity = Integer.parseInt(this.textQuantity
						.getText());
				String sts = this.statusCombo.getSelectedItem().toString();
				double Total = (changeBuyPrice * changeQuantity);

				this.tableModel2.setValueAt(changeProId, i, 0);
				this.tableModel2.setValueAt(changeBuyPrice, i, 1);
				this.tableModel2.setValueAt(changeQuantity, i, 2);
				this.tableModel2.setValueAt(sts, i, 3);
				this.tableModel2.setValueAt(Total, i, 4);
				this.grandTotal = 0;

				int j = this.tableModel2.getRowCount();
				while (j > 0) {
					double tot = Double.parseDouble(this.tableModel2
							.getValueAt(j - 1, 4).toString());
					this.grandTotal = this.grandTotal + tot;
					j--;
				}
				this.labelTotalAmount.setText(this.grandTotal + " BDT");
				this.textProId.setText("");
				this.textBuyPrice.setText("");
				this.textQuantity.setText("");
				this.buttonAdd.setText("Add");
				this.buttonAdd.setEnabled(false);
				this.buttonAdd.setBackground(getBackground());
			}
		}

		if (command.equals("Confirm")) {
			int k = this.tableModel2.getRowCount();
			while (k > 0) {
				int productId = Integer.parseInt(tableModel2.getValueAt(k - 1,
						0).toString());
				double buyPrice = Double.parseDouble(tableModel2.getValueAt(
						k - 1, 1).toString());
				int quantity = Integer.parseInt(tableModel2
						.getValueAt(k - 1, 2).toString());
				String status = tableModel2.getValueAt(k - 1, 3).toString();

				ProductPurchases proPurchase = new ProductPurchases();
				proPurchase.setProductId(productId);
				proPurchase.setBuyPrice(buyPrice);
				proPurchase.setQuantity(quantity);
				proPurchase.setStatus(status);

				ProductPurchaseAdapter adapter1 = new ProductPurchaseAdapter();
				adapter1.insertToProductPurchases(proPurchase);

				k--;
			}

			ConfirmPurchase ob = new ConfirmPurchase(this.grandTotal);
			ob.setVisible(true);
			ob.setLocationRelativeTo(null);

			// Table-2 clear
			this.tableModel2.setRowCount(0);
			this.grandTotal = 0.0;
			this.labelTotalAmount.setText(this.grandTotal + " BDT");
		}
	}

	private void InitializeComponents() {

		/*------------- Panel-1 --------------*/
		this.panel1 = new JPanel();
		this.panel1.setLayout(null);
		this.panel1.setBounds(0, 0, 300, 230);
		this.panel1.setBackground(Color.gray);

		// table view
		ProductAdapter adapter = new ProductAdapter();
		Vector<String> columns = new Vector<String>();
		columns.add("Product ID");
		columns.add("Name");
		columns.add("Buying Price");
		columns.add("Quantity");

		this.tableModel = new DefaultTableModel(adapter.getProductList2(),
				columns);
		this.table = new JTable(this.tableModel);
		this.rowSorter = new TableRowSorter<>(this.table.getModel());
		this.table.setRowSorter(rowSorter);

		this.scrollPane = new JScrollPane(this.table);
		this.scrollPane.setBounds(0, 30, 590, 200);
		this.panel1.add(this.scrollPane);

		this.labelSearch = new JLabel("Search");
		this.labelSearch.setBounds(80, 5, 150, 20);
		this.panel1.add(this.labelSearch);
		this.labelSearch.setForeground(Color.orange);
		this.labelSearch.setFont(labelSearch.getFont().deriveFont(16.0f));

		this.textboxSearch = new JTextField();
		this.textboxSearch.setBounds(140, 5, 200, 20);
		this.panel1.add(this.textboxSearch);

		this.buttonSelectProduct = new JButton("Select");
		this.buttonSelectProduct.setBounds(400, 5, 100, 20);
		this.buttonSelectProduct.addActionListener(this);
		this.panel1.add(this.buttonSelectProduct);

		/*------------- Panel-2 --------------*/
		this.panel2 = new JPanel();
		this.panel2.setLayout(null);
		this.panel2.setBounds(0, 230, 600, 370);
		this.panel2.setBackground(Color.LIGHT_GRAY);

		this.labelProID = new JLabel("Product ID");
		this.labelProID.setBounds(20, 10, 110, 20);
		this.panel2.add(this.labelProID);

		this.labelBuyPrice = new JLabel("Buy Price");
		this.labelBuyPrice.setBounds(20, 35, 110, 20);
		this.panel2.add(this.labelBuyPrice);

		this.labelQuantity = new JLabel("Quantity");
		this.labelQuantity.setBounds(20, 60, 110, 20);
		this.panel2.add(this.labelQuantity);

		this.labelStatus = new JLabel("Status");
		this.labelStatus.setBounds(20, 85, 110, 20);
		this.panel2.add(this.labelStatus);

		this.textProId = new JTextField();
		this.textProId.setBounds(110, 10, 70, 20);
		this.panel2.add(this.textProId);
		this.textProId.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.textBuyPrice = new JTextField();
		this.textBuyPrice.setBounds(110, 35, 70, 20);
		this.panel2.add(this.textBuyPrice);
		this.textBuyPrice.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.textQuantity = new JTextField();
		this.textQuantity.setBounds(110, 60, 70, 20);
		this.panel2.add(this.textQuantity);
		this.textQuantity.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		String[] List = new String[] { "Processing", "Incomplete" };
		this.statusCombo = new JComboBox(List);
		this.statusCombo.setBounds(110, 85, 70, 20);
		this.panel2.add(this.statusCombo);

		this.buttonAdd = new JButton("Add");
		this.buttonAdd.setBounds(110, 110, 70, 20);
		this.buttonAdd.addActionListener(this);
		this.buttonAdd.setEnabled(false);
		this.panel2.add(this.buttonAdd);

		this.buttonChange = new JButton("Change");
		this.buttonChange.setBounds(450, 35, 110, 20);
		this.buttonChange.addActionListener(this);
		this.buttonChange.setEnabled(false);
		this.panel2.add(this.buttonChange);

		this.buttonRemove = new JButton("Remove");
		this.buttonRemove.setBounds(450, 60, 110, 20);
		this.buttonRemove.addActionListener(this);
		this.buttonRemove.setEnabled(false);
		this.panel2.add(this.buttonRemove);

		this.buttonConfirm = new JButton("Confirm");
		this.buttonConfirm.setBounds(450, 85, 110, 30);
		this.buttonConfirm.addActionListener(this);
		this.buttonConfirm.setEnabled(false);
		this.panel2.add(this.buttonConfirm);

		this.labelGrand = new JLabel("Grand Total");
		this.labelGrand.setBounds(250, 25, 120, 40);
		this.panel2.add(this.labelGrand);
		// this.labelGrand.setForeground(Color.orange);
		this.labelGrand.setFont(labelGrand.getFont().deriveFont(20.0f));

		this.labelTotalAmount = new JLabel();
		this.labelTotalAmount.setBounds(260, 50, 120, 40);
		this.panel2.add(this.labelTotalAmount);
		this.labelTotalAmount.setForeground(Color.gray);
		this.labelTotalAmount.setFont(labelTotalAmount.getFont().deriveFont(
				17.0f));

		// table view
		Vector<String> col = new Vector<String>();
		col.add("Product ID");
		col.add("Buy Price");
		col.add("Quantity");
		col.add("Status");
		col.add("Total");

		this.tableModel2 = new DefaultTableModel(null, col);
		this.table2 = new JTable(this.tableModel2);
		this.scrollPane3 = new JScrollPane(this.table2);
		this.scrollPane3.setBounds(0, 150, 590, 190);
		this.panel2.add(this.scrollPane3);

		this.add(this.panel2);
		this.add(this.panel1);
		this.setSize(600, 600);

		/*
		 * --------------------------------- Search
		 * ------------------------------------
		 */

		// Table Filter for Products
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