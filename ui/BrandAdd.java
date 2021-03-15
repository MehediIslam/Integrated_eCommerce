package ui;

import javax.swing.*;

import adapter.BrandAdapter;
import model.Brand;

import java.awt.event.*;

class BrandAdd extends JInternalFrame implements ActionListener {
	private JPanel panel;
	private JLabel labelBrandName, labelDescription;
	private JTextField textBrandName;
	private JTextArea textDescription;
	private JButton buttonAdd;

	public BrandAdd() {
		super("Add Brand", true, true, true, true);
		this.InitializeComponents();
	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Add")) {
			if (this.textBrandName.getText().trim().equals("")
					|| this.textDescription.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null,
						"Please Fillup Required Field");
			}

			else {
				Brand brand = new Brand();
				brand.setBrandName(this.textBrandName.getText().trim()
						.toString());
				brand.setDescription(this.textDescription.getText().trim()
						.toString());
				BrandAdapter ba = new BrandAdapter();
				if (!ba.insertBrand(brand)) {
					JOptionPane.showMessageDialog(null, "Success");
					this.textBrandName.setText("");
					this.textDescription.setText("");
				}
			}
		}
	}

	public void InitializeComponents() {
		this.panel = new JPanel();
		this.panel.setLayout(null);

		this.labelBrandName = new JLabel("Brand Name");
		this.labelBrandName.setBounds(20, 20, 110, 20);
		this.panel.add(this.labelBrandName);

		this.labelDescription = new JLabel("Description");
		this.labelDescription.setBounds(20, 60, 110, 20);
		this.panel.add(this.labelDescription);

		this.textBrandName = new JTextField();
		this.textBrandName.setBounds(140, 20, 120, 20);
		this.panel.add(this.textBrandName);

		this.textDescription = new JTextArea();
		this.textDescription.setBounds(140, 60, 120, 30);
		this.panel.add(this.textDescription);

		this.buttonAdd = new JButton("Add");
		this.buttonAdd.setBounds(140, 120, 120, 20);
		this.buttonAdd.addActionListener(this);
		this.panel.add(this.buttonAdd);

		this.add(this.panel);
		this.setSize(290, 200);
	}
}
