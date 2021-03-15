package ui;

import javax.swing.*;

import adapter.ProfileAdapter;
import adapter.ProductSaleAdapter;
import model.Profile;
import model.Sell;

import java.awt.event.*;

public class ConfirmSale extends JFrame implements ActionListener {
	private JPanel panel;
	private JLabel labelPhoneNum, labelGrand, labelPaid;
	private JTextField textGrandTotal, textPhone, textPaid;
	private JButton buttonConfirm;
	private double grandtotal = 0;
	private double ret = 0;
	private Sell sell = null;

	ConfirmSale(double grandTotal, Sell sell) {
		// JOptionPane.showMessageDialog(null, grandTotal);
		this.grandtotal = grandTotal;
		this.sell = sell;
		this.setTitle("Confirm Sale");
		this.initialize();
	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Sold")) {
			if (this.textPhone.getText().trim().equals("")
					|| this.textPaid.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null,
						"Please Fillup Required Field");
			}

			else {
				// check existing phone number
				Profile phn = new Profile();
				String phone = this.textPhone.getText();
				phn.setPhone(phone);

				ProfileAdapter adapter = new ProfileAdapter();
				if (adapter.checkExisting(phn)) {
					double paid = Double.parseDouble(textPaid.getText().trim()
							.toString());

					if (paid < this.grandtotal) {
						sell.setPaidAmount(paid);
					}

					else {
						sell.setPaidAmount(this.grandtotal);
					}

					sell.setBuyerId(phone);

					ProductSaleAdapter adapter1 = new ProductSaleAdapter();
					adapter1.insertToSells(sell);

					// return money
					ret = paid - this.grandtotal;
					JOptionPane.showMessageDialog(null, "Return Money: " + ret
							+ " BDT");
					this.setVisible(false);
					this.updateProductSell();
				}

				else {
					JOptionPane.showMessageDialog(null, "User not existed");
				}
			}
		}
	}

	public void updateProductSell() {
		ProductSaleAdapter adapter2 = new ProductSaleAdapter();
		adapter2.setSellId();
	}

	public void initialize() {
		this.panel = new JPanel();
		this.panel.setLayout(null);

		this.labelPhoneNum = new JLabel("Customer Phone");
		this.labelPhoneNum.setBounds(50, 40, 110, 20);
		this.panel.add(this.labelPhoneNum);

		this.labelGrand = new JLabel("Grand Total");
		this.labelGrand.setBounds(50, 70, 110, 20);
		this.panel.add(this.labelGrand);

		this.labelPaid = new JLabel("Paid Amount");
		this.labelPaid.setBounds(50, 100, 110, 20);
		this.panel.add(this.labelPaid);

		this.textPhone = new JTextField();
		this.textPhone.setBounds(210, 40, 110, 20);
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

		this.textGrandTotal = new JTextField(this.grandtotal + "");
		this.textGrandTotal.setBounds(210, 70, 110, 20);
		this.textGrandTotal.setEditable(false);
		this.panel.add(this.textGrandTotal);

		this.textPaid = new JTextField();
		this.textPaid.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.textPaid.setBounds(210, 100, 110, 20);
		this.panel.add(this.textPaid);

		this.buttonConfirm = new JButton("Sold");
		this.buttonConfirm.setBounds(155, 210, 80, 20);
		this.buttonConfirm.addActionListener(this);
		this.panel.add(this.buttonConfirm);

		this.add(this.panel);
		this.setSize(400, 300);
	}
}
