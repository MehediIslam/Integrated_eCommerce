package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

import model.Brand;
import adapter.BrandAdapter;

import java.awt.Color;
import java.awt.event.*;
import java.util.*;

class BrandList extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private TableRowSorter<TableModel> rowSorter;
	private JPanel panel, panel1;
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton buttonEdit, buttonCancel, buttonEdit1;
	private JScrollPane scrollPane;
	private JLabel labelId, labelBrandname, labelDescription, labelSearch;
	private JTextField textBrandname, textboxSearch, textid;
	private JTextArea textDescription;
	int i;

	/* =========== Brandlist table [Done by rup]=========== */
	public BrandList() {
		super("Brand List", true, true, true, true);
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
			Brand brand = new Brand();
			Integer Brndid = Integer.parseInt(this.textid.getText().toString());
			String Brndname = this.textBrandname.getText().toString();
			String Description = this.textDescription.getText().toString();

			brand.setBrandId(Brndid);
			brand.setBrandName(Brndname);
			brand.setDescription(Description);

			// table refresh
			this.tableModel.setValueAt(Brndid, i, 0);
			this.tableModel.setValueAt(Brndname, i, 1);
			this.tableModel.setValueAt(Description, i, 2);

			// brandEdit Query function calling
			BrandAdapter adapter = new BrandAdapter();
			adapter.brandEdit(brand);

			this.panel1.setVisible(false);
			this.setSize(638, 380);
		}

		else if (command.equals("Cancel")) {
			this.panel1.setVisible(false);
			this.setSize(638, 380);
		}
	}

	public void InitializeComponents() {
		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.panel.setBounds(0, 0, 630, 350);
		this.panel.setBackground(Color.GRAY);

		// table view
		BrandAdapter adapter = new BrandAdapter();
		Vector<String> columns = new Vector<String>();
		columns.add("Serial");
		columns.add("Brand Name");
		columns.add("Description");

		this.tableModel = new DefaultTableModel(adapter.getBrandList(), columns);
		this.table = new JTable(this.tableModel);
		this.rowSorter = new TableRowSorter<>(this.table.getModel());
		this.table.setRowSorter(rowSorter);

		this.scrollPane = new JScrollPane(this.table);
		this.scrollPane.setBounds(0, 0, 628, 300);
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
		this.buttonEdit.setBounds(540, 310, 80, 20);
		this.buttonEdit.addActionListener(this);
		this.panel.add(this.buttonEdit);

		this.add(this.panel);
		this.setSize(638, 380);

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
		this.panel1.setBounds(0, 350, 630, 350);
		this.panel1.setBackground(Color.lightGray);

		this.labelId = new JLabel("Brand Id");
		this.labelId.setBounds(20, 30, 110, 20);
		this.panel1.add(this.labelId);

		this.labelBrandname = new JLabel("Brand Name");
		this.labelBrandname.setBounds(20, 70, 110, 20);
		this.panel1.add(this.labelBrandname);

		this.labelDescription = new JLabel("Description");
		this.labelDescription.setBounds(20, 110, 110, 20);
		this.panel1.add(this.labelDescription);

		// Data passing to Edit panel1
		this.textid = new JTextField(tableModel.getValueAt(i, 0).toString());
		this.textid.setBounds(140, 30, 120, 20);
		this.textid.setEditable(false);
		this.panel1.add(this.textid);

		this.textBrandname = new JTextField(tableModel.getValueAt(i, 1)
				.toString());
		this.textBrandname.setBounds(140, 70, 120, 20);
		this.panel1.add(this.textBrandname);

		this.textDescription = new JTextArea(tableModel.getValueAt(i, 2)
				.toString());
		this.textDescription.setBounds(140, 110, 120, 47);
		this.panel1.add(this.textDescription);

		this.buttonEdit1 = new JButton("Done");
		this.buttonEdit1.setBounds(540, 140, 80, 20);
		this.buttonEdit1.addActionListener(this);
		this.panel1.add(this.buttonEdit1);

		this.buttonCancel = new JButton("Cancel");
		this.buttonCancel.setBounds(540, 110, 80, 20);
		this.buttonCancel.addActionListener(this);
		this.panel1.add(this.buttonCancel);

		this.add(this.panel1);
		this.add(this.panel);
		this.setSize(638, 560);
		this.revalidate();
	}
}
