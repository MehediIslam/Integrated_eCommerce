package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import adapter.ProfileAdapter;
import adapter.UserAdapter;
import model.Profile;

import java.awt.Color;
import java.awt.event.*;
import java.util.Vector;

class CustomerProfileList extends JInternalFrame implements ActionListener {
	private TableRowSorter<TableModel> rowSorter;
	private JPanel panel, panel1;
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton buttonEdit,buttonDeactivate, buttonCancel, buttonAdd;
	private JScrollPane scrollPane;
	private JLabel labelId, labelName, labelPhone, labelEmail, labelAddress,
			labelCreatorId, labelSearch;
	private JTextField textID, textName, textPhone, textEmail, textAdderss,
			textCreatorId, textboxSearch;
	int i;

	/* =========== Profilelist table [Done by rup]=========== */
	public CustomerProfileList() {
		super("Customer List", true, true, true, true);
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
		
		
		if (command.equals("Deactivate")) {
			i = table.getSelectedRow();
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				
				Integer uid = Integer.parseInt(tableModel.getValueAt(i, 0).toString());
				UserAdapter ad = new UserAdapter();
				ad.userDeactive(uid);
			} else {
				JOptionPane.showMessageDialog(null, "Please Select a Row");
			}
		}

		else if (command.equals("Done")) {
			Profile profile = new Profile();
			Integer id = Integer.parseInt(this.textID.getText().toString());
			String name = this.textName.getText().toString();
			String phone = this.textPhone.getText().toString();
			String email = this.textEmail.getText().toString();
			String address = this.textAdderss.getText().toString();
			Integer creatorId = Integer.parseInt(this.textCreatorId.getText()
					.toString());

			profile.setProfileId(id);
			profile.setName(name);
			profile.setPhone(phone);
			profile.setEmail(email);
			profile.setAddress(address);
			profile.setCreatorId(creatorId);

			// table refresh
			this.tableModel.setValueAt(id, i, 0);
			this.tableModel.setValueAt(name, i, 1);
			this.tableModel.setValueAt(phone, i, 2);
			this.tableModel.setValueAt(email, i, 3);
			this.tableModel.setValueAt(address, i, 4);
			this.tableModel.setValueAt(creatorId, i, 5);
			
			ProfileAdapter adapter1 = new ProfileAdapter();
			adapter1.profileEdit(profile);
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
		this.panel.setBounds(0, 0, 638, 340);
		this.panel.setBackground(Color.GRAY);

		// table view
		ProfileAdapter adapter = new ProfileAdapter();
		Vector<String> columns = new Vector<String>();
		columns.add("Serial");
		columns.add("Name");
		columns.add("Phone");
		columns.add("E-mail");
		columns.add("Address");
		columns.add("Creator ID");

		this.tableModel = new DefaultTableModel(adapter.ConsumerList(),
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
		
	/*	this.buttonDeactivate = new JButton("Deactivate");
		this.buttonDeactivate.setBounds(420, 310, 110, 20);
		this.buttonDeactivate.addActionListener(this);
		this.panel.add(this.buttonDeactivate); */

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
		// Editing Panel
		this.panel1 = new JPanel();
		this.panel1.setLayout(null);
		this.panel1.setBounds(0, 350, 630, 600);
		this.panel1.setBackground(Color.lightGray);

		this.labelId = new JLabel("Id");
		this.labelId.setBounds(20, 30, 110, 20);
		this.panel1.add(this.labelId);

		this.labelName = new JLabel("Name");
		this.labelName.setBounds(20, 70, 110, 20);
		this.panel1.add(this.labelName);

		this.labelPhone = new JLabel("Phone");
		this.labelPhone.setBounds(20, 110, 110, 20);
		this.panel1.add(this.labelPhone);

		this.labelEmail = new JLabel("Email");
		this.labelEmail.setBounds(400, 30, 110, 20);
		this.panel1.add(this.labelEmail);

		this.labelAddress = new JLabel("Address");
		this.labelAddress.setBounds(400, 70, 110, 20);
		this.panel1.add(this.labelAddress);

		this.labelCreatorId = new JLabel("Creator Id");
		this.labelCreatorId.setBounds(20, 150, 120, 20);
		this.panel1.add(this.labelCreatorId);

		// Data passing to Edit panel1
		this.textID = new JTextField(tableModel.getValueAt(i, 0).toString());
		this.textID.setBounds(140, 30, 120, 20);
		this.textID.setEditable(false);
		this.panel1.add(this.textID);

		this.textName = new JTextField(tableModel.getValueAt(i, 1).toString());
		this.textName.setBounds(140, 70, 120, 20);
		this.panel1.add(this.textName);

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
		this.textEmail.setBounds(480, 30, 120, 20);
		this.panel1.add(this.textEmail);

		this.textAdderss = new JTextField(tableModel.getValueAt(i, 4)
				.toString());
		this.textAdderss.setBounds(480, 70, 120, 20);
		this.panel1.add(this.textAdderss);

		this.textCreatorId = new JTextField(tableModel.getValueAt(i, 5)
				.toString());
		this.textCreatorId.setBounds(140, 150, 120, 20);
		this.panel1.add(this.textCreatorId);
		this.textCreatorId.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.buttonAdd = new JButton("Done");
		this.buttonAdd.setBounds(480, 110, 120, 20);
		this.buttonAdd.addActionListener(this);
		this.panel1.add(this.buttonAdd);

		this.buttonCancel = new JButton("Cancel");
		this.buttonCancel.setBounds(480, 150, 120, 20);
		this.buttonCancel.addActionListener(this);
		this.panel1.add(this.buttonCancel);

		this.add(this.panel1);
		this.add(this.panel);
		this.setSize(638, 600);
		this.revalidate();
	}
}
