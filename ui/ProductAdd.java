package ui;

import javax.swing.*;

import model.Brand;
import model.Categorie;
import model.Item;
import model.Product;
import model.Session;
import model.Supplier;
import adapter.BrandAdapter;
import adapter.CategoryAdapter;
import adapter.ProductAdapter;
import adapter.SuppliersAdapter;

import java.awt.event.*;
import java.util.Vector;

class ProductAdd extends JInternalFrame implements ActionListener {
	private JPanel panel;
	private JLabel labelCategoryName, labelBrandName, labelSupplierName,
			labelProductName, labelBuyPrice, labelSellPrice, labelQuantity,labelDescription;
	JTextArea textProductName;
	private JTextField textBuyPrice, textSellPrice, textQuantity;
	private JTextArea textDescription;
	public static int brandId, suppId, catId;
	private JButton buttonAdd;
	private JComboBox<Item<Brand>> brandCombo;
	private JComboBox<Item<Supplier>> supplierCombo;
	private JComboBox<Item<Categorie>> categorieCombo;

	public ProductAdd() {
		super("Add Product", true, true, true, true);
		this.InitializeComponents();
	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Add")) {
			if (this.textProductName.getText().trim().equals("")
					|| this.textProductName.getText().trim().equals("")
					|| this.textBuyPrice.getText().trim().equals("")
					|| this.textSellPrice.getText().trim().equals("")
					|| this.textQuantity.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null,
						"Please Fillup Required Field");
			}

			else {
				if (this.brandId == 0) {
					this.brandId = 1;
				}
				if (this.suppId == 0) {
					this.suppId = 1;
				}
				if (this.catId == 0) {
					this.catId = 1;
				}

				Product pro = new Product();
				pro.setProductName(this.textProductName.getText().trim()
						.toString());
				pro.setBuyPrice(Double.parseDouble(this.textBuyPrice.getText()
						.trim().toString()));
				pro.setSellPrice(Double.parseDouble(this.textSellPrice
						.getText().trim().toString()));
				pro.setQuantity(Integer.parseInt(this.textQuantity.getText()
						.trim().toString()));
				pro.setDescription(this.textDescription.getText());

				pro.setBrandId(this.brandId);
				pro.setCatId(this.catId);
				pro.setSupplierId(this.suppId);

				ProductAdapter pa = new ProductAdapter();
				if (!pa.insertProduct(pro)) {
					JOptionPane.showMessageDialog(null, "Success");
					this.textProductName.setText("");
					this.textBuyPrice.setText("");
					this.textSellPrice.setText("");
					this.textQuantity.setText("");
					this.textDescription.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "failed");
				}
				this.brandId = 0;
				this.suppId = 0;
				this.catId = 0;
			}
		}
	}

	public void InitializeComponents() {
		this.panel = new JPanel();
		this.panel.setLayout(null);

		this.labelProductName = new JLabel("Product Name");
		this.labelProductName.setBounds(20, 20, 150, 20);
		this.panel.add(this.labelProductName);

		this.labelBuyPrice = new JLabel("Buy Price");
		this.labelBuyPrice.setBounds(20, 50, 150, 20);
		this.panel.add(this.labelBuyPrice);

		this.labelSellPrice = new JLabel("Sell Price");
		this.labelSellPrice.setBounds(20, 80, 150, 20);
		this.panel.add(this.labelSellPrice);

		this.labelQuantity = new JLabel("Quantity");
		this.labelQuantity.setBounds(20, 110, 150, 20);
		this.panel.add(this.labelQuantity);
		
		this.labelDescription = new JLabel("Description");
		this.labelDescription.setBounds(20, 140, 150, 20);
		this.panel.add(this.labelDescription);

		this.labelBrandName = new JLabel("Brand Name");
		this.labelBrandName.setBounds(20, 230, 150, 20);
		this.panel.add(this.labelBrandName);

		this.labelSupplierName = new JLabel("Supplier Name");
		this.labelSupplierName.setBounds(20, 260, 150, 20);
		this.panel.add(this.labelSupplierName);

		this.labelCategoryName = new JLabel("Category Name");
		this.labelCategoryName.setBounds(20, 290, 150, 20);
		this.panel.add(this.labelCategoryName);

		this.textProductName = new JTextArea();
		this.textProductName.setBounds(140, 20, 150, 20);
		this.panel.add(this.textProductName);

		this.textBuyPrice = new JTextField();
		this.textBuyPrice.setBounds(140, 50, 150, 20);
		this.panel.add(this.textBuyPrice);
		this.textBuyPrice.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.textSellPrice = new JTextField();
		this.textSellPrice.setBounds(140, 80, 150, 20);
		this.panel.add(this.textSellPrice);
		this.textSellPrice.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.textQuantity = new JTextField();
		this.textQuantity.setBounds(140, 110, 150, 20);
		this.panel.add(this.textQuantity);
		this.textQuantity.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.buttonAdd = new JButton("Add");
		this.buttonAdd.setBounds(210, 320, 80, 20);
		this.buttonAdd.addActionListener(this);
		this.panel.add(this.buttonAdd);
		
		this.textDescription = new JTextArea();
		this.textDescription.setBounds(140, 140, 150, 80);
		this.panel.add(this.textDescription);

		// combo brand
		BrandAdapter ba = new BrandAdapter();
		Vector<Vector<String>> brands = ba.getBrandList();
		Vector b = new Vector();
		this.brandCombo = new JComboBox<Item<Brand>>();
		for (int i = 0; i < brands.size(); i++) {
			Brand brand = new Brand();
			brand.setBrandId(Integer.parseInt(brands.elementAt(i).elementAt(0)));
			brand.setBrandName(brands.elementAt(i).elementAt(1));
			b.addElement(brand);
			this.brandCombo.addItem(new Item<Brand>(Integer.parseInt(brands
					.elementAt(i).elementAt(0)), brands.elementAt(i).elementAt(
					1)));
		}
		this.brandCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				Item item = (Item) comboBox.getSelectedItem();
				ProductAdd pa = new ProductAdd();
				pa.brandId = item.getValue();
			}
		});
		this.brandCombo.setBounds(140, 230, 150, 20);
		this.panel.add(this.brandCombo);

		// combo supplier
		SuppliersAdapter sa = new SuppliersAdapter();
		Vector<Vector<String>> suppliers = sa.SuppliersList();
		Vector s = new Vector();
		this.supplierCombo = new JComboBox<Item<Supplier>>();
		for (int k = 0; k < suppliers.size(); k++) {
			Supplier sup = new Supplier();
			sup.setSupplierId(Integer.parseInt(suppliers.elementAt(k)
					.elementAt(0)));
			sup.setSupplierName(suppliers.elementAt(k).elementAt(1));
			s.addElement(sup);
			this.supplierCombo.addItem(new Item<Supplier>(Integer
					.parseInt(suppliers.elementAt(k).elementAt(0)), suppliers
					.elementAt(k).elementAt(1)));
		}
		this.supplierCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				Item item = (Item) comboBox.getSelectedItem();
				ProductAdd pa = new ProductAdd();
				pa.suppId = item.getValue();
			}
		});
		this.supplierCombo.setBounds(140, 260, 150, 20);
		this.panel.add(this.supplierCombo);

		// combo category
		CategoryAdapter ca = new CategoryAdapter();
		Vector<Vector<String>> categories = ca.getCategoryList();
		Vector cate = new Vector();
		this.categorieCombo = new JComboBox<Item<Categorie>>();
		for (int j = 0; j < categories.size(); j++) {
			Categorie cat = new Categorie();
			cat.setCatId(Integer.parseInt(categories.elementAt(j).elementAt(0)));
			cat.setCatName(categories.elementAt(j).elementAt(1));
			cate.addElement(cat);
			this.categorieCombo.addItem(new Item<Categorie>(Integer
					.parseInt(categories.elementAt(j).elementAt(0)), categories
					.elementAt(j).elementAt(1)));
		}
		this.categorieCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
				Item item = (Item) comboBox.getSelectedItem();
				ProductAdd pa = new ProductAdd();
				pa.catId = item.getValue();
			}
		});
		this.categorieCombo.setBounds(140, 290, 150, 20);
		this.panel.add(this.categorieCombo);

		this.add(this.panel);
		this.setSize(340, 400);
	}
}
