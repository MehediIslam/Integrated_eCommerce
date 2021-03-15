package ui;

import javax.swing.*;

import adapter.ProductServiceAdapter;
import adapter.ProfileAdapter;
import model.Profile;
import model.Service;

import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ConfirmService extends JFrame implements ActionListener {
	private JPanel panel;
	private JLabel labelPhoneNum, labelGrand, labelPaid;
	private JTextField textGrandTotal, textPhone, textPaid;
	private JButton buttonDone;
	private double grandtotal = 0;
	private double ret = 0;
	private JLabel labelDelivery;
	private JComboBox d, y, m;

	ConfirmService(double grandTotal) {
		this.grandtotal = grandTotal;
		this.setTitle("Confirm Service");
		this.initialize();
	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Done")) {
			if (this.textPhone.getText().trim().equals("")
					|| this.textPaid.getText().trim().equals("")
					|| this.d.getSelectedItem().equals("DD")
					|| this.m.getSelectedItem().equals("MM")
					|| this.y.getSelectedItem().equals("YYYY")) {
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
					Service service = new Service();
					service.setCustomerId(phone);
					service.setTotalCharges(this.grandtotal);
					double paid = Double.parseDouble(textPaid.getText().trim()
							.toString());
					if (paid < this.grandtotal) {
						service.setAdvancePay(paid);
					}

					else {
						service.setAdvancePay(this.grandtotal);
					}

					String dd = this.d.getSelectedItem().toString();
					String mm = this.m.getSelectedItem().toString();
					String yy = this.y.getSelectedItem().toString();
					String deliveryDate = yy + "-" + mm + "-" + dd;

					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-mm-dd");
					try {
						service.setDeliveryDate(formatter.parse(deliveryDate));
					} catch (ParseException e) {
						e.printStackTrace();
					}

					ProductServiceAdapter adapter1 = new ProductServiceAdapter();
					adapter1.insertToService(service);

					// return money
					ret = paid - this.grandtotal;
					JOptionPane.showMessageDialog(null, "Return Money: " + ret
							+ " BDT");
					this.setVisible(false);
					this.updateProductService();
				}

				else {
					JOptionPane.showMessageDialog(null, "User not existed");
				}
			}
		}
	}

	public void updateProductService() {
		ProductServiceAdapter adapter2 = new ProductServiceAdapter();
		adapter2.setServiceId();
	}

	public void initialize() {
		this.panel = new JPanel();
		this.panel.setLayout(null);

		this.labelPhoneNum = new JLabel("Customer Phone");
		this.labelPhoneNum.setBounds(50, 40, 110, 20);
		this.panel.add(this.labelPhoneNum);

		this.labelGrand = new JLabel("Total Cost");
		this.labelGrand.setBounds(50, 70, 110, 20);
		this.panel.add(this.labelGrand);

		this.labelPaid = new JLabel("Advance Paid");
		this.labelPaid.setBounds(50, 100, 110, 20);
		this.panel.add(this.labelPaid);

		this.labelDelivery = new JLabel("Delivery Date");
		this.labelDelivery.setBounds(50, 130, 110, 20);
		this.panel.add(this.labelDelivery);

		this.textPhone = new JTextField();
		this.textPhone.setBounds(210, 40, 155, 20);
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
		this.textGrandTotal.setBounds(210, 70, 155, 20);
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
		this.textPaid.setBounds(210, 100, 155, 20);
		this.panel.add(this.textPaid);

		String[] List1 = new String[] { "DD", "01", "02", "03", "04", "05",
				"06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
				"26", "27", "28", "29", "30", "31" };
		this.d = new JComboBox(List1);
		this.d.setBounds(210, 130, 45, 20);
		this.panel.add(this.d);

		String[] List2 = new String[] { "MM", "01", "02", "03", "04", "05",
				"06", "07", "08", "09", "10", "11", "12" };
		this.m = new JComboBox(List2);
		this.m.setBounds(260, 130, 45, 20);
		this.panel.add(this.m);

		String[] List3 = new String[] { "YYYY", "2014", "2015", "2016", "2017",
				"2018", "2019", "2020", "2021", "2022", "2023", "2024" };
		this.y = new JComboBox(List3);
		this.y.setBounds(310, 130, 55, 20);
		this.panel.add(this.y);

		this.buttonDone = new JButton("Done");
		this.buttonDone.setBounds(190, 210, 80, 20);
		this.buttonDone.addActionListener(this);
		this.panel.add(this.buttonDone);

		this.add(this.panel);
		this.setSize(440, 300);
	}
}
