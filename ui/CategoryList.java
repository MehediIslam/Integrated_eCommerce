package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import adapter.CategoryAdapter;
import model.Categorie;

import java.awt.Color;
import java.awt.event.*;
import java.util.Vector;

/* =========== Category list table [Done by rup]=========== */

class CategoryList extends JInternalFrame implements ActionListener {
	private TableRowSorter<TableModel> rowSorter;
	private static final long serialVersionUID = 1L;
	private JPanel panel, panel1;
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel labelId, labelCatagoryname, labelDescription, labelSearch;
	private JTextField textCatagoryName, textboxSearch, textDescription,
			textID;
	private JButton buttonDone, buttonCancel, buttonEdit;
	private CategoryAdapter adapter;
	int i;

	public CategoryList() {
		super("Category List", true, true, true, true);
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
			Categorie cat = new Categorie();
			Integer id = Integer.parseInt(this.textID.getText().toString());
			String name = this.textCatagoryName.getText().toString();
			String description = this.textDescription.getText().toString();

			cat.setCatId(id);
			cat.setCatName(name);
			cat.setDescription(description);

			// table refresh
			this.tableModel.setValueAt(id, i, 0);
			this.tableModel.setValueAt(name, i, 1);
			this.tableModel.setValueAt(description, i, 2);

			// update query function calling
			this.adapter.catagoryEdit(cat);

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
		this.panel.setBounds(0, 0, 638, 300);
		this.panel.setBackground(Color.GRAY);

		// table view
		adapter = new CategoryAdapter();
		Vector<String> columns = new Vector<String>();
		columns.add("Serial");
		columns.add("Category Name");
		columns.add("Description");

		this.tableModel = new DefaultTableModel(adapter.getCategoryList(),
				columns);
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
		this.revalidate();

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
		// Editing Panel
		this.panel1 = new JPanel();
		this.panel1.setLayout(null);
		this.panel1.setBounds(0, 340, 638, 300);
		this.panel1.setBackground(Color.lightGray);

		this.labelId = new JLabel("Catagory Id");
		this.labelId.setBounds(20, 30, 110, 20);
		this.panel1.add(this.labelId);

		this.labelCatagoryname = new JLabel("Catagory Name");
		this.labelCatagoryname.setBounds(20, 70, 110, 20);
		this.panel1.add(this.labelCatagoryname);

		this.labelDescription = new JLabel("Description");
		this.labelDescription.setBounds(20, 110, 110, 20);
		this.panel1.add(this.labelDescription);

		// Data passing to Edit panel1
		this.textID = new JTextField(tableModel.getValueAt(i, 0).toString());
		this.textID.setBounds(140, 30, 120, 20);
		this.textID.setEditable(false);
		this.panel1.add(this.textID);

		this.textCatagoryName = new JTextField(tableModel.getValueAt(i, 1)
				.toString());
		this.textCatagoryName.setBounds(140, 70, 120, 20);
		this.panel1.add(this.textCatagoryName);

		this.textDescription = new JTextField(tableModel.getValueAt(i, 2)
				.toString());
		this.textDescription.setBounds(140, 110, 120, 20);
		this.panel1.add(this.textDescription);

		this.buttonDone = new JButton("Done");
		this.buttonDone.setBounds(540, 110, 80, 20);
		this.buttonDone.addActionListener(this);
		this.panel1.add(this.buttonDone);

		this.buttonCancel = new JButton("Cancel");
		this.buttonCancel.setBounds(540, 80, 80, 20);
		this.buttonCancel.addActionListener(this);
		this.panel1.add(this.buttonCancel);

		this.add(this.panel1);
		this.add(this.panel);
		this.setSize(638, 520);
		this.revalidate();
	}
}
