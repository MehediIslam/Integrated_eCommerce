package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

import model.ProductServices;
import adapter.ProductServiceAdapter;

import java.awt.Color;
import java.awt.event.*;
import java.util.*;

class ProductServiceList extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private TableRowSorter<TableModel> rowSorter;
	private JPanel panel, panel1;
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel labelServiceId, labelProductId, labelProblemDescription,
			labelRepairCost, labelStatus, labelSearch;
	private JTextField textProductId, textServiceId, textProblemDescription,
			textRepairCost, textboxSearch;
	private JButton buttonDone, buttonCancel, buttonEdit;
	private JComboBox status;
	int i;

	/* =========== Servicelist table [Done by rup]=========== */

	public ProductServiceList() {
		super("Service List", true, true, true, true);
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
			ProductServices ser = new ProductServices();
			Integer sl = Integer.parseInt(tableModel.getValueAt(i, 0)
					.toString());
			Integer servID = Integer.parseInt(this.textServiceId.getText()
					.toString());
			String proID = this.textProductId.getText().toString();
			String prob = this.textProblemDescription.getText().toString();
			double repCost = Double.parseDouble(this.textRepairCost.getText()
					.toString());
			String sts = this.status.getSelectedItem().toString();

			ser.setId(sl);
			ser.setProblemDescription(prob);
			ser.setRepairCost(repCost);
			ser.setStatus(sts);

			// table refresh
			this.tableModel.setValueAt(sl, i, 0);
			this.tableModel.setValueAt(servID, i, 1);
			this.tableModel.setValueAt(proID, i, 2);
			this.tableModel.setValueAt(prob, i, 3);
			this.tableModel.setValueAt(repCost, i, 4);
			this.tableModel.setValueAt(sts, i, 5);

			// update query function calling
			ProductServiceAdapter adapter = new ProductServiceAdapter();
			adapter.productserviceEdit(ser);

			// update status from shipment
			if (this.status.getSelectedItem().toString() == "Complete") {
				int service_id = Integer.parseInt(tableModel.getValueAt(i, 1)
						.toString());
				ProductServiceAdapter adapter1 = new ProductServiceAdapter();
				adapter1.updateServiceStatus(service_id);
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
		ProductServiceAdapter adapter = new ProductServiceAdapter();
		Vector<String> columns = new Vector<String>();
		columns.add("Serial");
		columns.add("Service ID");
		columns.add("Product Name");
		columns.add("Problem Details");
		columns.add("Repair Cost");
		columns.add("Status");

		this.tableModel = new DefaultTableModel(adapter.getServiceList(),
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
		// Edit Panel1
		this.panel1 = new JPanel();
		this.panel1.setLayout(null);
		this.panel1.setBounds(0, 370, 630, 370);
		this.panel1.setBackground(Color.lightGray);

		this.labelServiceId = new JLabel("Service ID");
		this.labelServiceId.setBounds(20, 30, 110, 20);
		this.panel1.add(this.labelServiceId);

		this.labelProductId = new JLabel("Product ID");
		this.labelProductId.setBounds(20, 70, 110, 20);
		this.panel1.add(this.labelProductId);

		this.labelProblemDescription = new JLabel("Problem Details");
		this.labelProblemDescription.setBounds(20, 110, 110, 20);
		this.panel1.add(this.labelProblemDescription);

		this.labelRepairCost = new JLabel("Repair Cost");
		this.labelRepairCost.setBounds(20, 150, 110, 20);
		this.panel1.add(this.labelRepairCost);

		this.labelStatus = new JLabel("Status");
		this.labelStatus.setBounds(20, 190, 110, 20);
		this.panel1.add(this.labelStatus);

		// Data passing to Edit panel1
		this.textServiceId = new JTextField(tableModel.getValueAt(i, 1)
				.toString());
		this.textServiceId.setBounds(160, 30, 120, 20);
		this.textServiceId.setEditable(false);
		this.panel1.add(this.textServiceId);

		this.textProductId = new JTextField(tableModel.getValueAt(i, 2)
				.toString());
		this.textProductId.setBounds(160, 70, 120, 20);
		this.textProductId.setEditable(false);
		this.panel1.add(this.textProductId);

		this.textProblemDescription = new JTextField(tableModel
				.getValueAt(i, 3).toString());
		this.textProblemDescription.setBounds(160, 110, 120, 20);
		this.panel1.add(this.textProblemDescription);

		this.textRepairCost = new JTextField(tableModel.getValueAt(i, 4)
				.toString());
		this.textRepairCost.setBounds(160, 150, 120, 20);
		this.panel1.add(this.textRepairCost);
		this.textRepairCost.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		String[] List = new String[] { "Normal", "Emergency", "Complete",
				"Processing" };
		this.status = new JComboBox(List);
		this.status.setBounds(160, 190, 120, 20);
		this.panel1.add(this.status);

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
