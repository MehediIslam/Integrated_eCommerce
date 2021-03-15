package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Supplier;
import adapter.SuppliersAdapter;

class SuppliersList extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private TableRowSorter<TableModel> rowSorter;
	private JPanel panel, panel1;
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton buttonEdit, buttonCancel;
	private JScrollPane scrollPane;
	private JLabel labelSupName, labelSupPhone, labelSupEmail, labelSupAddress,
			labelSupId, labelDeposit, labelSearch;
	private JTextField textSupName, textDeposit, textPhone, textEmail,
			textAddress, textboxSearch, textSupID;
	private JButton buttonAdd;
	int i;

	/* =========== SupplierList table ========== */

	public SuppliersList() {
		super("Supplier List", true, true, true, true);
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
			Supplier sup = new Supplier();
			Integer supId = Integer.parseInt(this.textSupID.getText()
					.toString());
			String supName = this.textSupName.getText().toString();
			String phone = (this.textPhone.getText().toString());
			String email = this.textEmail.getText().toString();
			String address = (this.textAddress.getText().toString());
			double deposit = Double.parseDouble(this.textDeposit.getText()
					.toString());

			sup.setSupplierId(supId);
			sup.setSupplierName(supName);
			sup.setSupplierPhone(phone);
			sup.setSecurityDeposit(deposit);
			sup.setSupplierAddress(address);
			sup.setSupplierEmail(email);

			// table refresh
			this.tableModel.setValueAt(supId, i, 0);
			this.tableModel.setValueAt(supName, i, 1);
			this.tableModel.setValueAt(phone, i, 2);
			this.tableModel.setValueAt(email, i, 3);
			this.tableModel.setValueAt(address, i, 4);
			this.tableModel.setValueAt(deposit, i, 5);

			// Edit Query function calling
			SuppliersAdapter adapter = new SuppliersAdapter();
			adapter.SuppliersEdit(sup);

			this.panel1.setVisible(false);
			this.setSize(1060, 370);
		}

		else if (command.equals("Cancel")) {
			this.panel1.setVisible(false);
			this.setSize(1060, 370);
		}
	}

	public void InitializeComponents() {
		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.panel.setBounds(0, 0, 1060, 400);
		this.panel.setBackground(Color.GRAY);

		// table view
		SuppliersAdapter adapter = new SuppliersAdapter();
		Vector<String> columns = new Vector<String>();
		columns.add("Serial");
		columns.add("Supplier Name");
		columns.add("Phone");
		columns.add("Email");
		columns.add("Address");
		columns.add("Security Deposit");

		this.tableModel = new DefaultTableModel(adapter.SuppliersList(),
				columns);
		this.table = new JTable(this.tableModel);
		this.rowSorter = new TableRowSorter<>(this.table.getModel());
		this.table.setRowSorter(rowSorter);

		this.scrollPane = new JScrollPane(this.table);
		this.scrollPane.setBounds(0, 0, 1050, 300);
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
		this.setSize(1060, 370);

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
		this.panel1.setBounds(0, 350, 1060, 400);
		this.panel1.setBackground(Color.lightGray);

		this.labelSupId = new JLabel("Supplier ID");
		this.labelSupId.setBounds(20, 30, 110, 20);
		this.panel1.add(this.labelSupId);

		this.labelSupName = new JLabel("Supplier Name");
		this.labelSupName.setBounds(20, 70, 110, 20);
		this.panel1.add(this.labelSupName);

		this.labelSupPhone = new JLabel("Phone");
		this.labelSupPhone.setBounds(20, 110, 110, 20);
		this.panel1.add(this.labelSupPhone);

		this.labelSupEmail = new JLabel("Email");
		this.labelSupEmail.setBounds(20, 150, 110, 20);
		this.panel1.add(this.labelSupEmail);

		this.labelSupAddress = new JLabel("Address");
		this.labelSupAddress.setBounds(20, 190, 110, 20);
		this.panel1.add(this.labelSupAddress);

		this.labelDeposit = new JLabel("Deposit");
		this.labelDeposit.setBounds(480, 70, 120, 20);
		this.panel1.add(this.labelDeposit);

		// Data passing to Edit panel1
		this.textSupID = new JTextField(tableModel.getValueAt(i, 0).toString());
		this.textSupID.setBounds(140, 30, 120, 20);
		this.textSupID.setEditable(false);
		this.panel1.add(this.textSupID);

		this.textSupName = new JTextField(tableModel.getValueAt(i, 1)
				.toString());
		this.textSupName.setBounds(140, 70, 120, 20);
		this.panel1.add(this.textSupName);

		this.textPhone = new JTextField(tableModel.getValueAt(i, 2).toString());
		this.textPhone.setBounds(140, 110, 120, 20);
		this.panel1.add(this.textPhone);
		this.textPhone.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.textEmail = new JTextField(tableModel.getValueAt(i, 3).toString());
		this.textEmail.setBounds(140, 150, 120, 20);
		this.panel1.add(this.textEmail);

		this.textAddress = new JTextField(tableModel.getValueAt(i, 4)
				.toString());
		this.textAddress.setBounds(140, 190, 120, 20);
		this.panel1.add(this.textAddress);

		this.textDeposit = new JTextField(tableModel.getValueAt(i, 5)
				.toString());
		this.textDeposit.setBounds(600, 70, 120, 20);
		this.panel1.add(this.textDeposit);
		this.textDeposit.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.buttonAdd = new JButton("Done");
		this.buttonAdd.setBounds(965, 190, 80, 20);
		this.buttonAdd.addActionListener(this);
		this.panel1.add(this.buttonAdd);

		this.buttonCancel = new JButton("Cancel");
		this.buttonCancel.setBounds(965, 160, 80, 20);
		this.buttonCancel.addActionListener(this);
		this.panel1.add(this.buttonCancel);

		this.add(this.panel1);
		this.add(this.panel);
		this.setSize(1060, 610);
		this.revalidate();
	}
}
