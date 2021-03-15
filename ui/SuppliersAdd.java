package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Supplier;
import adapter.SuppliersAdapter;

class SuppliersAdd extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel LabelName, LabelPhone, LabelEmail, LabelAdders,
			LabelDeposit;
	private JTextField textName, textPhone, textEmail, textAdders, textDeposit;
	private JButton buttonAdd;

	public SuppliersAdd() {
		super("Supplier", true, true, true, true);
		this.InitializeComponents();
	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Add")) {
			if (this.textDeposit.getText().trim().equals("")
					|| this.textName.getText().trim().equals("")
					|| this.textPhone.getText().trim().equals("")
					|| this.textEmail.getText().trim().equals("")
					|| this.textAdders.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null,
						"Please Fillup Required Field");
			}

			else {
				Supplier sup = new Supplier();
				sup.setSupplierName(this.textName.getText().trim().toString());
				sup.setSupplierPhone(this.textPhone.getText().trim().toString());
				sup.setSupplierEmail(this.textEmail.getText().trim().toString());
				sup.setSupplierAddress(this.textAdders.getText().trim()
						.toString());
				sup.setSecurityDeposit(Double.parseDouble(this.textDeposit
						.getText().trim().toString()));

				SuppliersAdapter sa = new SuppliersAdapter();
				if (!sa.insertSupplier(sup)) {
					JOptionPane.showMessageDialog(null, "Success");
					this.textName.setText("");
					this.textPhone.setText("");
					this.textEmail.setText("");
					this.textAdders.setText("");
					this.textDeposit.setText("");
				}
			}
		}
	}

	public void InitializeComponents() {
		this.panel = new JPanel();
		this.panel.setLayout(null);

		this.LabelName = new JLabel("Name");
		this.LabelName.setBounds(20, 20, 110, 20);
		this.panel.add(this.LabelName);

		this.LabelPhone = new JLabel("Phone");
		this.LabelPhone.setBounds(20, 40, 110, 20);
		this.panel.add(this.LabelPhone);

		this.LabelEmail = new JLabel("Email");
		this.LabelEmail.setBounds(20, 60, 110, 20);
		this.panel.add(this.LabelEmail);

		this.LabelAdders = new JLabel("Address");
		this.LabelAdders.setBounds(20, 80, 110, 20);
		this.panel.add(this.LabelAdders);

		this.LabelDeposit = new JLabel("Deposit");
		this.LabelDeposit.setBounds(20, 100, 110, 20);
		this.panel.add(this.LabelDeposit);

		this.textName = new JTextField();
		this.textName.setBounds(140, 20, 120, 20);
		this.panel.add(this.textName);

		this.textPhone = new JTextField();
		this.textPhone.setBounds(140, 40, 120, 20);
		this.panel.add(this.textPhone);
		this.textPhone.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.textEmail = new JTextField();
		this.textEmail.setBounds(140, 60, 120, 20);
		this.panel.add(this.textEmail);

		this.textAdders = new JTextField();
		this.textAdders.setBounds(140, 80, 120, 20);
		this.panel.add(this.textAdders);

		this.textDeposit = new JTextField();
		this.textDeposit.setBounds(140, 100, 120, 20);
		this.panel.add(this.textDeposit);
		this.textDeposit.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.buttonAdd = new JButton("Add");
		this.buttonAdd.setBounds(140, 160, 120, 20);
		this.buttonAdd.addActionListener(this);
		this.panel.add(this.buttonAdd);

		this.add(this.panel);
		this.setSize(320, 250);
	}
}
