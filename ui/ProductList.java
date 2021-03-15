package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Brand;
import model.Categorie;
import model.Item;
import model.Item2;
import model.Item3;
import model.Product;
import model.Supplier;
import adapter.BrandAdapter;
import adapter.CategoryAdapter;
import adapter.ProductAdapter;
import adapter.SuppliersAdapter;

import java.awt.Color;
import java.awt.event.*;
import java.util.Vector;

class ProductList extends JInternalFrame implements ActionListener {
	private TableRowSorter<TableModel> rowSorter;
	private JPanel panel, panel1;
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton buttonEdit, buttonCancel, buttonDone;
	private JScrollPane scrollPane;
	private JLabel labelProductName, labelSearch, labelBuyPrice,
			labelSellPrice, labelQuantity, labelProductId, labelProductID,
			labelBrandID, labelSuppID, labelImgloc, labelCatID,labelDescription;
	JTextArea textImg,textProductName;
	private JTextField textQuantity, textSellPrice, textBuyPrice,textboxSearch;
	private JTextArea textDescription;
	private static int brandId, suppId, catId;
	private JComboBox<Item<Brand>> brandCombo;
	private JComboBox<Item2<Supplier>> supplierCombo;
	private JComboBox<Item3<Categorie>> categorieCombo;
	int selectedIndex1 = -1;
	int selectedIndex2 = -1;
	int selectedIndex3 = -1;
	int i;

	/* =========== ProductList table [Done by rup]=========== */

	public ProductList() {
		super("Product List", true, true, true, true);
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
				InitializeComponentPanel1();
			} else {
				JOptionPane.showMessageDialog(null, "Please Select a Row");
			}
		}

		else if (command.equals("Done")) {
			Product product = new Product();
			Integer proId = Integer.parseInt(this.labelProductID.getText()
					.toString());
			String proName = this.textProductName.getText().toString();
			double bPrice = Double.parseDouble(this.textBuyPrice.getText()
					.toString());
			double selPrice = Double.parseDouble(this.textSellPrice.getText()
					.toString());
			Integer quaantity = Integer.parseInt(this.textQuantity.getText()
					.toString());
			String lastPur = tableModel.getValueAt(i, 5).toString();
			String LastSold = tableModel.getValueAt(i, 6).toString();
			String imagLocation = this.textImg.getText().toString();
			String description = this.textDescription.getText().toString();

			product.setProductId(proId);
			product.setProductName(proName);
			product.setBuyPrice(bPrice);
			product.setSellPrice(selPrice);
			product.setQuantity(quaantity);
			product.setImgLocation(imagLocation);
			product.setBrandId(this.brandId);
			product.setCatId(this.catId);
			product.setSupplierId(this.suppId);
			product.setDescription(description);

			// table refresh
			this.tableModel.setValueAt(proId, i, 0);
			this.tableModel.setValueAt(proName, i, 1);
			this.tableModel.setValueAt(bPrice, i, 2);
			this.tableModel.setValueAt(selPrice, i, 3);
			this.tableModel.setValueAt(quaantity, i, 4);
			this.tableModel.setValueAt(lastPur, i, 5);
			this.tableModel.setValueAt(LastSold, i, 6);
			this.tableModel.setValueAt(imagLocation, i, 10);
			this.tableModel.setValueAt(description, i, 11);

			this.tableModel.setValueAt(this.brandCombo.getSelectedItem()
					.toString(), i, 7);
			this.tableModel.setValueAt(this.supplierCombo.getSelectedItem()
					.toString(), i, 8);
			this.tableModel.setValueAt(this.categorieCombo.getSelectedItem()
					.toString(), i, 9);

			// proEdit Query function calling
			ProductAdapter adapter = new ProductAdapter();
			adapter.proEdit(product);

			this.selectedIndex1 = -1;
			this.selectedIndex2 = -1;
			this.selectedIndex3 = -1;

			this.panel1.setVisible(false);
			this.setSize(1100, 370);
		}

		else if (command.equals("Cancel")) {
			this.panel1.setVisible(false);
			this.setSize(1100, 370);
		}
	}

	public void InitializeComponents() {
		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.panel.setBounds(0, 0, 1100, 400);
		this.panel.setBackground(Color.GRAY);

		// table view
		ProductAdapter adapter = new ProductAdapter();
		Vector<String> columns = new Vector<String>();
		columns.add("Serial");
		columns.add("Product Name");
		columns.add("Buying Price");
		columns.add("Selling Price");
		columns.add("Quantity");
		columns.add("Last Purchased");
		columns.add("Last Sold");
		columns.add("Brand");
		columns.add("Supplier");
		columns.add("Category");
		columns.add("Image Location");
		columns.add("Description");

		this.tableModel = new DefaultTableModel(adapter.getProductList(),
				columns);
		this.table = new JTable(this.tableModel);
		this.rowSorter = new TableRowSorter<>(this.table.getModel());
		this.table.setRowSorter(rowSorter);

		this.scrollPane = new JScrollPane(this.table);
		this.scrollPane.setBounds(0, 0, 1090, 300);
		this.panel.add(this.scrollPane);

		this.labelSearch = new JLabel("Search");
		this.labelSearch.setBounds(5, 310, 150, 20);
		this.panel.add(this.labelSearch);
		this.labelSearch.setForeground(Color.orange);
		this.labelSearch.setFont(labelSearch.getFont().deriveFont(16.0f));

		this.textboxSearch = new JTextField();
		this.textboxSearch.setBounds(70, 310, 180, 20);
		this.panel.add(this.textboxSearch);

		this.buttonEdit = new JButton("Edit");
		this.buttonEdit.setBounds(965, 310, 80, 20);
		this.buttonEdit.addActionListener(this);
		this.panel.add(this.buttonEdit);

		this.add(this.panel);
		this.setSize(1100, 370);

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

	public void InitializeComponentPanel1() {
		// editing Panel1
		this.panel1 = new JPanel();
		this.panel1.setLayout(null);
		this.panel1.setBounds(0, 350, 1100, 400);
		this.panel1.setBackground(Color.lightGray);

		this.labelProductId = new JLabel("Product ID");
		this.labelProductId.setBounds(20, 30, 110, 20);
		this.panel1.add(this.labelProductId);

		this.labelProductName = new JLabel("Product Name");
		this.labelProductName.setBounds(20, 70, 110, 20);
		this.panel1.add(this.labelProductName);

		this.labelBuyPrice = new JLabel("Buy Price");
		this.labelBuyPrice.setBounds(20, 110, 110, 20);
		this.panel1.add(this.labelBuyPrice);

		this.labelSellPrice = new JLabel("Sell Price");
		this.labelSellPrice.setBounds(20, 150, 110, 20);
		this.panel1.add(this.labelSellPrice);

		this.labelQuantity = new JLabel("Quantity");
		this.labelQuantity.setBounds(20, 190, 110, 20);
		this.panel1.add(this.labelQuantity);

		this.labelDescription = new JLabel("Drisciption");
		this.labelDescription.setBounds(480, 30, 120, 20);
		this.panel1.add(this.labelDescription);
		
		this.labelBrandID = new JLabel("Brand");
		this.labelBrandID.setBounds(480, 70, 120, 20);
		this.panel1.add(this.labelBrandID);

		this.labelSuppID = new JLabel("Supplier");
		this.labelSuppID.setBounds(480, 110, 120, 20);
		this.panel1.add(this.labelSuppID);

		this.labelCatID = new JLabel("Category");
		this.labelCatID.setBounds(480, 150, 120, 20);
		this.panel1.add(this.labelCatID);

		this.labelImgloc = new JLabel("Image Location");
		this.labelImgloc.setBounds(480, 190, 120, 20);
		this.panel1.add(this.labelImgloc);

		// Data passing to Edit panel1
		this.labelProductID = new JLabel(tableModel.getValueAt(i, 0).toString());
		this.labelProductID.setBounds(140, 30, 120, 20);
		this.panel1.add(this.labelProductID);

		this.textProductName = new JTextArea(tableModel.getValueAt(i, 1)
				.toString());
		this.textProductName.setBounds(140, 70, 120, 20);
		this.panel1.add(this.textProductName);

		this.textBuyPrice = new JTextField(tableModel.getValueAt(i, 2)
				.toString());
		this.textBuyPrice.setBounds(140, 110, 120, 20);
		this.panel1.add(this.textBuyPrice);
		this.textBuyPrice.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.textSellPrice = new JTextField(tableModel.getValueAt(i, 3)
				.toString());
		this.textSellPrice.setBounds(140, 150, 120, 20);
		this.panel1.add(this.textSellPrice);
		this.textSellPrice.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.textQuantity = new JTextField(tableModel.getValueAt(i, 4)
				.toString());
		this.textQuantity.setBounds(140, 190, 120, 20);
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

		this.textImg = new JTextArea(tableModel.getValueAt(i, 10).toString());
		this.textImg.setBounds(600, 190, 120, 20);
		this.panel1.add(this.textImg);

		
		
		//
		this.textDescription = new JTextArea(tableModel.getValueAt(i, 11).toString());
		this.textDescription.setBounds(600, 30, 120, 20);
		this.panel1.add(this.textDescription);
		// combo brand
		BrandAdapter ba = new BrandAdapter();
		Vector<Vector<String>> brands = ba.getBrandList();
		Vector b = new Vector();
		this.brandCombo = new JComboBox<Item<Brand>>();
		String bname = this.tableModel.getValueAt(i, 7).toString();
		for (int n = 0; n < brands.size(); n++) {
			Brand brand = new Brand();
			brand.setBrandId(Integer.parseInt(brands.elementAt(n).elementAt(0)));
			brand.setBrandName(brands.elementAt(n).elementAt(1));
			Item<Brand> item = new Item<Brand>(brand.getBrandId(),
					brand.getBrandName());
			this.brandCombo.addItem(item);
			if (bname.equals(brand.getBrandName())) {
				this.selectedIndex1 = n;
				this.brandId = brand.getBrandId();
			}
		}
		this.brandCombo.setSelectedIndex(this.selectedIndex1);

		this.brandCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				Item item = (Item) comboBox.getSelectedItem();
				ProductList pa = new ProductList();
				pa.brandId = item.getValue();
			}
		});
		this.brandCombo.setBounds(600, 70, 120, 20);
		this.brandCombo.addActionListener(this);
		this.panel1.add(this.brandCombo);

		// combo supplier
		SuppliersAdapter sa = new SuppliersAdapter();
		Vector<Vector<String>> suppliers = sa.SuppliersList();
		Vector s = new Vector();
		this.supplierCombo = new JComboBox<Item2<Supplier>>();
		String sname = this.tableModel.getValueAt(i, 8).toString();
		for (int k = 0; k < suppliers.size(); k++) {
			Supplier sup = new Supplier();
			sup.setSupplierId(Integer.parseInt(suppliers.elementAt(k)
					.elementAt(0)));
			sup.setSupplierName(suppliers.elementAt(k).elementAt(1));
			Item2<Supplier> item2 = new Item2<Supplier>(sup.getSupplierId(),
					sup.getSupplierName());

			this.supplierCombo.addItem(item2);

			if (sname.equals(sup.getSupplierName())) {
				this.selectedIndex2 = k;
				this.suppId = sup.getSupplierId();
			}
		}

		this.supplierCombo.setSelectedIndex(this.selectedIndex2);

		this.supplierCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				Item2 item2 = (Item2) comboBox.getSelectedItem();
				ProductList pa = new ProductList();
				pa.suppId = item2.getValue();
			}
		});
		this.supplierCombo.setBounds(600, 110, 120, 20);
		this.supplierCombo.addActionListener(this);
		this.panel1.add(this.supplierCombo);

		// combo category
		CategoryAdapter ca = new CategoryAdapter();
		Vector<Vector<String>> categories = ca.getCategoryList();
		Vector cate = new Vector();
		this.categorieCombo = new JComboBox<Item3<Categorie>>();
		String cname = this.tableModel.getValueAt(i, 9).toString();
		for (int j = 0; j < categories.size(); j++) {
			Categorie cat = new Categorie();
			cat.setCatId(Integer.parseInt(categories.elementAt(j).elementAt(0)));
			cat.setCatName(categories.elementAt(j).elementAt(1));
			Item3<Categorie> item3 = new Item3<Categorie>(cat.getCatId(),
					cat.getCatName());

			this.categorieCombo.addItem(item3);

			if (cname.equals(cat.getCatName())) {
				this.selectedIndex3 = j;
				this.catId = cat.getCatId();
			}
		}
		this.categorieCombo.setSelectedIndex(this.selectedIndex3);

		this.categorieCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				Item3 item3 = (Item3) comboBox.getSelectedItem();
				ProductList pa = new ProductList();
				pa.catId = item3.getValue();
			}
		});
		this.categorieCombo.setBounds(600, 150, 120, 20);
		this.categorieCombo.addActionListener(this);
		this.panel1.add(this.categorieCombo);

		this.buttonDone = new JButton("Done");
		this.buttonDone.setBounds(965, 190, 80, 20);
		this.buttonDone.addActionListener(this);
		this.panel1.add(this.buttonDone);

		this.buttonCancel = new JButton("Cancel");
		this.buttonCancel.setBounds(965, 160, 80, 20);
		this.buttonCancel.addActionListener(this);
		this.panel1.add(this.buttonCancel);

		this.add(this.panel1);
		this.add(this.panel);
		this.setSize(1100, 610);
		this.revalidate();
	}
}
