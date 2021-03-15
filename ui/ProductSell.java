package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import adapter.ProductAdapter;
import adapter.ProductSaleAdapter;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.Product;
import model.ProductsSell;
import model.Sell;

class ProductSell extends JInternalFrame implements ActionListener {
	private TableRowSorter<TableModel> rowSorter;
	private JPanel panel1, panel2, panel3, panel4;
	private JTable table, table2;
	private JScrollPane scrollPane, scrollPane2;
	private DefaultTableModel tableModel;
	private JLabel percent, labeldisc, proName, labelTotalAmount, quantity,
			name2, pricePro2, labelGrand, labelSearch;
	private Component price;
	private JTextField name, pricePro, discText, quantityText, textboxSearch,
			quantityText2;
	private JButton buttonAddProduct, buttonOk, buttonChangeQuantity,
			buttonChang, buttonRemove, buttonConfirm, buttonCancel, buttonBack;
	private DefaultTableModel tableModel2;
	private double total, grandTotal = 0;
	int i;

	public ProductSell() {
		super("Product Sell", true, true, true, true);
		this.InitializeComponents();
	}

	public void actionPerformed(ActionEvent event) {
		// selection row
		String command = event.getActionCommand();
		if (command.equals("Select")) {
			int z = this.tableModel2.getRowCount();
			i = table.getSelectedRow();
			int selectedRow = i;
			if (selectedRow != -1) {
				// check existing row
				if (z != 0) {
					while (z > 0) {
						if (Integer.parseInt(this.tableModel.getValueAt(
								selectedRow, 0).toString()) == Integer
								.parseInt(this.tableModel2.getValueAt(z - 1, 0)
										.toString())) {
							JOptionPane
									.showMessageDialog(null, "Already Added");
							break;
						}

						else if (z == 1) {
							Panel2();
						}
						z--;
					}
				}

				else {
					Panel2();
				}
			}

			else {
				JOptionPane.showMessageDialog(null, "Please Select a Row");
			}
		}

		if (command.equals("OK")) {
			if (this.quantityText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null,
						"Please Fillup Required Field");
			} else {
				int proId = Integer.parseInt(tableModel.getValueAt(i, 0)
						.toString());
				String proName = this.name.getText().toString();
				double sellPrice = Double.parseDouble(this.pricePro.getText()
						.toString());
				int quantity = Integer.parseInt(this.quantityText.getText()
						.toString());
				int stock = Integer.parseInt(tableModel.getValueAt(i, 3)
						.toString());
				double discount = Double.parseDouble(this.discText.getText());

				if (stock >= quantity) {
					this.panel2.setVisible(false);
					this.panel1.setVisible(true);

					if (discount == 0) {
						total = (sellPrice * quantity);
					} else {
						// discount calculation
						total = (sellPrice * quantity)-(sellPrice * quantity * (discount / 100));
					}

					this.grandTotal = (this.grandTotal + total);
					this.labelTotalAmount.setText(this.grandTotal + " BDT");
					this.tableModel2.addRow(new Object[] { proId, proName,
							sellPrice, quantity, total });
					this.buttonConfirm.setEnabled(true);
					this.buttonRemove.setEnabled(true);
					this.buttonChangeQuantity.setEnabled(true);
				}

				else {
					JOptionPane
							.showMessageDialog(null, "Products out of stock");
				}
			}
		}

		if (command.equals("Change")) {
			i = table2.getSelectedRow();
			int selectedRow = i;
			if (selectedRow != -1) {
				Panel4();
			} else {
				JOptionPane.showMessageDialog(null, "Please Select a Row");
			}
		}

		if (command.equals("Changed")) {
			if (this.quantityText2.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null,
						"Please Fillup Required Field");
			} else {
				int proId2 = Integer.parseInt(tableModel2.getValueAt(i, 0)
						.toString());
				String proname = this.name2.getText().toString();
				double sellprice = Double.parseDouble(this.pricePro2.getText()
						.toString());
				int quantt = Integer.parseInt(this.quantityText2.getText()
						.toString());
				double Total = (sellprice * quantt);
				int j = this.tableModel2.getRowCount();
				int stock = Integer.parseInt(tableModel.getValueAt(i, 3)
						.toString());

				if (stock >= quantt) {
					this.panel4.setVisible(false);
					this.panel1.setVisible(true);

					this.tableModel2.setValueAt(proId2, i, 0);
					this.tableModel2.setValueAt(proname, i, 1);
					this.tableModel2.setValueAt(sellprice, i, 2);
					this.tableModel2.setValueAt(quantt, i, 3);
					this.tableModel2.setValueAt(Total, i, 4);
					this.grandTotal = 0;

					while (j > 0) {
						double tot = Double.parseDouble(this.tableModel2
								.getValueAt(j - 1, 4).toString());
						this.grandTotal = this.grandTotal + tot;
						j--;
					}

					this.labelTotalAmount.setText(this.grandTotal + " BDT");
				}

				else {
					JOptionPane
							.showMessageDialog(null, "Products out of stock");
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

		if (command.equals("Cancel")) {
			this.panel4.setVisible(false);
			this.panel1.setVisible(true);
			this.panel3.setVisible(true);
		}

		if (command.equals("Back")) {
			this.panel2.setVisible(false);
			this.panel1.setVisible(true);
			this.panel3.setVisible(true);
		}

		if (command.equals("Confirm")) {
			int k = this.tableModel2.getRowCount();
			while (k > 0) {
				int productId = Integer.parseInt(tableModel2.getValueAt(k - 1,
						0).toString());
				double sellingPrice = Double.parseDouble(tableModel.getValueAt(
						k - 1, 2).toString());
				int quant = Integer.parseInt(tableModel2.getValueAt(k - 1, 3)
						.toString());

				// for sells DBtable
				Product product = new Product();
				product.setProductId(productId);
				product.setQuantity(quant);

				ProductAdapter productAdapter = new ProductAdapter();
				productAdapter.updateSoldProduct(product);

				// for products_sells DBtable
				ProductsSell proSell = new ProductsSell();
				proSell.setProductId(productId);
				proSell.setSellPrice(sellingPrice);
				proSell.setQuantity(quant);
				ProductSaleAdapter adapter1 = new ProductSaleAdapter();
				adapter1.insertToProductSell(proSell);

				k--;
			}

			Sell sell = new Sell();
			sell.setTotalCharges(this.grandTotal);

			// update quantity from Table-1
			int z = this.tableModel2.getRowCount();
			if (z != 0) {
				while (z > 0) {
					if (Integer.parseInt(this.tableModel.getValueAt(z - 1, 0)
							.toString()) == Integer.parseInt(this.tableModel2
							.getValueAt(z - 1, 0).toString())) {
						int a = (Integer.parseInt(this.tableModel.getValueAt(
								z - 1, 3).toString()))
								- (Integer.parseInt(this.tableModel2
										.getValueAt(z - 1, 3).toString()));
						this.tableModel.setValueAt(a, z - 1, 3);
					}
					z--;
				}
			}

			ConfirmSale ob = new ConfirmSale(this.grandTotal, sell);
			ob.setVisible(true);
			ob.setLocationRelativeTo(null);

			// Table-2 clear
			this.tableModel2.setRowCount(0);
			this.grandTotal = 0.0;
			this.labelTotalAmount.setText(this.grandTotal + " BDT");
		}
	}

	private void InitializeComponents() {
		// -------- Panel-1 ---------//
		this.panel1 = new JPanel();
		this.panel1.setLayout(null);
		this.panel1.setBounds(0, 0, 500, 300);
		this.panel1.setBackground(Color.GRAY);

		// table view
		ProductSaleAdapter adapter = new ProductSaleAdapter();
		Vector<String> columns = new Vector<String>();
		columns.add("Product ID");
		columns.add("Product Name");
		columns.add("Selling Price (TK.)");
		columns.add("Quantity");
		columns.add("Discount (%)");

		this.tableModel = new DefaultTableModel(adapter.getProductList(),
				columns);
		this.table = new JTable(this.tableModel);
		this.rowSorter = new TableRowSorter<>(this.table.getModel());
		this.table.setRowSorter(rowSorter);

		this.scrollPane = new JScrollPane(this.table);
		this.scrollPane.setBounds(0, 30, 590, 250);
		this.panel1.add(this.scrollPane);

		this.labelSearch = new JLabel("Search");
		this.labelSearch.setBounds(80, 5, 150, 20);
		this.panel1.add(this.labelSearch);
		this.labelSearch.setForeground(Color.orange);
		this.labelSearch.setFont(labelSearch.getFont().deriveFont(16.0f));

		this.textboxSearch = new JTextField();
		this.textboxSearch.setBounds(140, 5, 200, 20);
		this.panel1.add(this.textboxSearch);

		this.buttonAddProduct = new JButton("Select");
		this.buttonAddProduct.setBounds(400, 5, 100, 20);
		this.buttonAddProduct.addActionListener(this);
		this.panel1.add(this.buttonAddProduct);

		// -------- Panel-3 ---------//
		if (this.panel3 == null) {
			this.panel3 = new JPanel();
			this.panel3.setLayout(null);
			this.panel3.setBounds(0, 300, 600, 300);
			this.panel3.setBackground(Color.GRAY);

			Vector<String> column = new Vector<String>();
			column.add("Product ID");
			column.add("Name");
			column.add("Selling Price");
			column.add("Quantity");
			column.add("Total");

			this.tableModel2 = new DefaultTableModel(null, column);
			this.table2 = new JTable(this.tableModel2);
			this.scrollPane2 = new JScrollPane(this.table2);
			this.scrollPane2.setBounds(0, 0, 390, 300);
			this.panel3.add(this.scrollPane2);

			this.labelGrand = new JLabel("Grand Total");
			this.labelGrand.setBounds(439, 0, 120, 40);
			this.panel3.add(this.labelGrand);
			this.labelGrand.setForeground(Color.orange);
			this.labelGrand.setFont(labelGrand.getFont().deriveFont(20.0f));

			this.labelTotalAmount = new JLabel();
			this.labelTotalAmount.setBounds(448, 30, 120, 40);
			this.panel3.add(this.labelTotalAmount);
			this.labelTotalAmount.setForeground(Color.GREEN);
			this.labelTotalAmount.setFont(labelTotalAmount.getFont()
					.deriveFont(17.0f));

			this.buttonConfirm = new JButton("Confirm");
			this.buttonConfirm.setBounds(440, 100, 110, 20);
			this.buttonConfirm.addActionListener(this);
			this.buttonConfirm.setEnabled(false);
			this.panel3.add(this.buttonConfirm);

			this.buttonChangeQuantity = new JButton("Change");
			this.buttonChangeQuantity.setBounds(440, 125, 110, 20);
			this.buttonChangeQuantity.addActionListener(this);
			this.buttonChangeQuantity.setEnabled(false);
			this.panel3.add(this.buttonChangeQuantity);

			this.buttonRemove = new JButton("Remove");
			this.buttonRemove.setBounds(440, 150, 110, 20);
			this.buttonRemove.addActionListener(this);
			this.buttonRemove.setEnabled(false);
			this.panel3.add(this.buttonRemove);

			this.add(this.panel3);
		}
		this.panel3.revalidate();
		this.add(this.panel1);
		this.setSize(600, 600);

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

	private void Panel2() {
		// Panel-2 End
		this.panel1.setVisible(false);
		this.panel2 = new JPanel();
		this.panel2.setLayout(null);
		this.panel2.setBounds(0, 0, 600, 300);
		this.panel2.setBackground(Color.LIGHT_GRAY);

		this.proName = new JLabel("Product Name");
		this.proName.setBounds(200, 20, 110, 20);
		this.panel2.add(this.proName);

		this.price = new JLabel("Price");
		this.price.setBounds(200, 60, 110, 20);
		this.panel2.add(this.price);

		this.labeldisc = new JLabel("Discount");
		this.labeldisc.setBounds(200, 100, 110, 20);
		this.panel2.add(this.labeldisc);

		this.quantity = new JLabel("Quantity");
		this.quantity.setBounds(200, 140, 110, 20);
		this.panel2.add(this.quantity);

		this.name = new JTextField(tableModel.getValueAt(i, 1).toString());
		this.name.setBounds(340, 20, 120, 20);
		this.name.setEditable(false);
		this.panel2.add(this.name);

		this.pricePro = new JTextField(tableModel.getValueAt(i, 2).toString());
		this.pricePro.setBounds(340, 60, 120, 20);
		this.pricePro.setEditable(false);
		this.panel2.add(this.pricePro);
		this.pricePro.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		if (tableModel.getValueAt(i, 4) == null) {
			this.discText = new JTextField("0");
		} else {
			this.discText = new JTextField(tableModel.getValueAt(i, 4)
					.toString());
		}
		this.discText.setBounds(340, 100, 100, 20);
		this.discText.setEditable(false);
		this.panel2.add(this.discText);
		this.discText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.percent = new JLabel("%");
		this.percent.setBounds(445, 100, 60, 20);
		this.panel2.add(this.percent);

		this.quantityText = new JTextField(tableModel.getValueAt(i, 3)
				.toString());
		this.quantityText.setBounds(340, 140, 50, 20);
		this.panel2.add(this.quantityText);
		this.quantityText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.buttonOk = new JButton("OK");
		this.buttonOk.setBounds(180, 260, 100, 20);
		this.buttonOk.addActionListener(this);
		this.panel2.add(this.buttonOk);

		this.buttonBack = new JButton("Back");
		this.buttonBack.setBounds(300, 260, 100, 20);
		this.buttonBack.addActionListener(this);
		this.panel2.add(this.buttonBack);

		this.add(this.panel2);
		this.add(this.panel1);
		this.setSize(600, 600);
		this.revalidate();
	}

	private void Panel4() {
		// Panel-4 End
		this.panel1.setVisible(false);
		this.panel4 = new JPanel();
		this.panel4.setLayout(null);
		this.panel4.setBounds(0, 0, 600, 300);
		this.panel4.setBackground(Color.LIGHT_GRAY);

		this.proName = new JLabel("Product Name");
		this.proName.setBounds(200, 20, 110, 20);
		this.panel4.add(this.proName);

		this.price = new JLabel("Price");
		this.price.setBounds(200, 60, 110, 20);
		this.panel4.add(this.price);

		this.quantity = new JLabel("Quantity");
		this.quantity.setBounds(200, 100, 110, 20);
		this.panel4.add(this.quantity);

		this.name2 = new JLabel(tableModel2.getValueAt(i, 1).toString());
		this.name2.setBounds(340, 20, 120, 20);
		this.panel4.add(this.name2);

		this.pricePro2 = new JLabel(tableModel2.getValueAt(i, 2).toString());
		this.pricePro2.setBounds(340, 60, 120, 20);
		this.panel4.add(this.pricePro2);

		this.quantityText2 = new JTextField(tableModel2.getValueAt(i, 3)
				.toString());
		this.quantityText2.setBounds(340, 100, 50, 20);
		this.panel4.add(this.quantityText2);
		this.quantityText2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.buttonChang = new JButton("Changed");
		this.buttonChang.setBounds(180, 260, 100, 20);
		this.buttonChang.addActionListener(this);
		this.panel4.add(this.buttonChang);

		this.buttonCancel = new JButton("Cancel");
		this.buttonCancel.setBounds(300, 260, 100, 20);
		this.buttonCancel.addActionListener(this);
		this.panel4.add(this.buttonCancel);

		this.add(this.panel4);
		this.add(this.panel1);
		this.setSize(600, 600);
		this.revalidate();
	}
}