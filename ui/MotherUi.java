package ui;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MotherUi extends JFrame {

	private JDesktopPane jdpDesktop;
	private JLabel label,labelDate;

	public static void main(String[] args) {
		//MotherUi ui = new MotherUi();
		//ui.Ui();
		Login l = new Login();
		l.setVisible(true);
	}

	public void Ui() {
		this.initializeComponents();
		this.add(jdpDesktop);
		this.setVisible(true);
		this.setSize(1150, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initializeComponents() {

		jdpDesktop = new JDesktopPane();
		JMenuBar menubar = new JMenuBar();

		// PROFILE
		JMenu profile = new JMenu("Profile");
		menubar.add(profile);
		JMenuItem profileAdd = new JMenuItem("Add");
		profile.add(profileAdd);
		JMenuItem profileList = new JMenuItem("Customer List");
		profile.add(profileList);
		JMenuItem profileList2 = new JMenuItem("Employee List");
		profile.add(profileList2);
		profileAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProfileAdd profileAdd = new ProfileAdd();

				jdpDesktop.add(profileAdd);
				profileAdd.show();

			}
		});

		profileList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerProfileList pro = new CustomerProfileList();
				jdpDesktop.add(pro);
				pro.show();

			}
		});
		
		profileList2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EmployeeProfileList pro = new EmployeeProfileList();
				jdpDesktop.add(pro);
				pro.show();

			}
		});

		// CATAGORY
		JMenu catagories = new JMenu("Catagories");
		menubar.add(catagories);
		JMenuItem catagorieAdd = new JMenuItem("Add");
		catagories.add(catagorieAdd);
		JMenuItem catagorieList = new JMenuItem("Category List");
		catagories.add(catagorieList);

		catagorieAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CategorieAdd catagorieAdd = new CategorieAdd();
				jdpDesktop.add(catagorieAdd);
				catagorieAdd.show();

			}
		});

		catagorieList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryList catagorieDelete = new CategoryList();
				jdpDesktop.add(catagorieDelete);
				catagorieDelete.show();

			}
		});

		// PRODUCT
		JMenu products = new JMenu("Products");
		menubar.add(products);
		JMenuItem productAdd = new JMenuItem("Add");
		products.add(productAdd);
		JMenuItem productList = new JMenuItem("Product List");
		products.add(productList);

		productAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProductAdd productAdd = new ProductAdd();
				jdpDesktop.add(productAdd);
				productAdd.show();

			}
		});

		productList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProductList productDelete = new ProductList();
				jdpDesktop.add(productDelete);
				productDelete.show();

			}
		});

		// BRAND
		JMenu brands = new JMenu("Brands");
		menubar.add(brands);
		JMenuItem brandAdd = new JMenuItem("Add");
		brands.add(brandAdd);
		JMenuItem brandList = new JMenuItem("Brand List");
		brands.add(brandList);

		brandAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BrandAdd brandAdd = new BrandAdd();

				jdpDesktop.add(brandAdd);
				brandAdd.show();
			}
		});

		brandList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BrandList brandList = new BrandList();
				jdpDesktop.add(brandList);
				brandList.show();
			}
		});
		

		//PRODUCTSALES
	     JMenu productSell = new JMenu("Products Sales");
	     menubar.add(productSell);
	     JMenuItem addProductSell = new JMenuItem("Add");
	     productSell.add(addProductSell);
	     JMenuItem saleList = new JMenuItem("Sale List");
	     productSell.add(saleList);
	     
	     addProductSell.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed (ActionEvent e)
	    	 {
	    		 ProductSell productSell = new ProductSell();
	    		 jdpDesktop.add(productSell);
	    		 productSell.show();
	    	    	
	    	 }
		});
	     
	     saleList.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed (ActionEvent e)
		    	 {
		    		 ProductSalesList productSalesList = new ProductSalesList();
		    		 jdpDesktop.add(productSalesList);
		    		 productSalesList.show();
		    	    	
		    	 }
			});

		// PRODUCT_DISCOUNT
		JMenu productDiscount = new JMenu("Products Discount");
		menubar.add(productDiscount);
		JMenuItem addproductDiscount = new JMenuItem("Add");
		productDiscount.add(addproductDiscount);
		JMenuItem discountList = new JMenuItem("Discount List");
		productDiscount.add(discountList);

		addproductDiscount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProductDiscount productDiscount = new ProductDiscount();
				jdpDesktop.add(productDiscount);
				productDiscount.show();

			}
		});

		discountList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProductDiscountList discountList = new ProductDiscountList();
				jdpDesktop.add(discountList);
				discountList.show();

			}
		});

		// PRODUCT PURCHASE
		JMenu productPurchase = new JMenu("Products Purchase");
		menubar.add(productPurchase);
		JMenuItem addproductPurchase = new JMenuItem("Add");
		productPurchase.add(addproductPurchase);
		JMenuItem purchaseList = new JMenuItem("Purchase List");
		productPurchase.add(purchaseList);
		JMenuItem purchaseShipment = new JMenuItem("Purchase Shipment");
		productPurchase.add(purchaseShipment);

		addproductPurchase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProductPurchase productPurchase = new ProductPurchase();
				jdpDesktop.add(productPurchase);
				productPurchase.show();

			}
		});

		purchaseList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProductPurchaseList productPurchaseList = new ProductPurchaseList();
				jdpDesktop.add(productPurchaseList);
				productPurchaseList.show();

			}
		});

		purchaseShipment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PurchaseShipmentList purchaseShipmentList = new PurchaseShipmentList();
				jdpDesktop.add(purchaseShipmentList);
				purchaseShipmentList.show();

			}
		});

		// PRODUCT SERVICE
		JMenu productService = new JMenu("Products Service");
		menubar.add(productService);
		JMenuItem addproductService = new JMenuItem("Add");
		productService.add(addproductService);
		JMenuItem serviceList = new JMenuItem("Service List");
		productService.add(serviceList);
		JMenuItem serviceShipment = new JMenuItem("Service Shipment");
		productService.add(serviceShipment);

		addproductService.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProductService productService = new ProductService();
				jdpDesktop.add(productService);
				productService.show();

			}
		});

		serviceList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProductServiceList productServiceList = new ProductServiceList();
				jdpDesktop.add(productServiceList);
				productServiceList.show();

			}
		});

		serviceShipment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ServiceShipmentList serviceShipmentList = new ServiceShipmentList();
				jdpDesktop.add(serviceShipmentList);
				serviceShipmentList.show();
			}
		});

		// suppliers
		JMenu suppliers = new JMenu("Suppliers");
		menubar.add(suppliers);
		JMenuItem addSuppliers = new JMenuItem("Add");
		suppliers.add(addSuppliers);
		JMenuItem suppliersList = new JMenuItem("Suppliers List");
		suppliers.add(suppliersList);

		addSuppliers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SuppliersAdd addSuppliers = new SuppliersAdd();
				jdpDesktop.add(addSuppliers);
				addSuppliers.show();

			}
		});

		suppliersList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SuppliersList suppliersList = new SuppliersList();
				jdpDesktop.add(suppliersList);
				suppliersList.show();

			}
		});
			
				// Account
				JMenu Account = new JMenu("Account");
				menubar.add(Account);
				JMenuItem changePass = new JMenuItem("Change Password");
				Account.add(changePass);
				JMenuItem Logout = new JMenuItem("Logout");
				Account.add(Logout);
		
				changePass.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						ChangePassword pass = new ChangePassword();
						jdpDesktop.add(pass);
						pass.show();
					}
				});
				
				Logout.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						//logout;						
						Logout l = new Logout();
						l.logout();
					}
				});
				
		
		this.label = new JLabel("INTELLIGENCE SHOP");
		this.label.setBounds(300, 70, 600, 100);
		this.label.setForeground(Color.orange);
		this.label.setFont(label.getFont().deriveFont(50.0f));
		this.jdpDesktop.add(this.label);
		
		DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
		Calendar cal = Calendar.getInstance();		
		this.labelDate = new JLabel(dateFormat.format(cal.getTime()));
		this.labelDate.setBounds(400, 130, 600, 100);
		this.labelDate.setForeground(Color.GREEN);
		this.labelDate.setFont(labelDate.getFont().deriveFont(40.0f));
		this.jdpDesktop.add(this.labelDate);
		
		this.setJMenuBar(menubar);
		this.add(jdpDesktop);
	}
}
