package ui;

import javax.swing.*;

import model.Categorie;
import adapter.CategoryAdapter;

import java.awt.event.*;

class CategorieAdd extends JInternalFrame implements ActionListener {
	private JPanel panel;
	private JLabel labelCatagoryname, labelDescription;
	private JTextField textCatagoryname;
	private JTextArea textDescription;
	private JButton buttonAdd;

	public CategorieAdd()

	{
		super("Add Catagory", true, true, true, true);
		this.InitializeComponents();
	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Add")) {

			if (this.textCatagoryname.getText().trim().equals("")
					|| this.textDescription.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null,
						"Please Fillup Required Field");
			}

			else {
				Categorie cat = new Categorie();
				cat.setCatName(this.textCatagoryname.getText().trim()
						.toString());
				cat.setDescription(this.textDescription.getText().trim()
						.toString());
				CategoryAdapter ca = new CategoryAdapter();
				if (!ca.insertCategory(cat)) {
					JOptionPane.showMessageDialog(null, "Success");
					this.textCatagoryname.setText("");
					this.textDescription.setText("");
				}
			}
		}
	}

	public void InitializeComponents() {
		this.panel = new JPanel();
		this.panel.setLayout(null);

		this.labelCatagoryname = new JLabel("Catagory Name");
		this.labelCatagoryname.setBounds(20, 20, 110, 20);
		this.panel.add(this.labelCatagoryname);

		this.labelDescription = new JLabel("Description");
		this.labelDescription.setBounds(20, 50, 110, 20);
		this.panel.add(this.labelDescription);

		this.textCatagoryname = new JTextField();
		this.textCatagoryname.setBounds(140, 20, 120, 20);
		this.panel.add(this.textCatagoryname);

		this.textDescription = new JTextArea();
		this.textDescription.setBounds(140, 50, 120, 30);
		this.panel.add(this.textDescription);

		this.buttonAdd = new JButton("Add");
		this.buttonAdd.setBounds(140, 110, 120, 20);
		this.buttonAdd.addActionListener(this);
		this.panel.add(this.buttonAdd);

		this.add(this.panel);
		this.setSize(290, 200);
	}
}
