package ui;

import javax.swing.*;

import adapter.ChangePassAdapter;
import java.awt.event.*;

class ChangePassword extends JInternalFrame implements ActionListener {
	private JPanel panel;
	private JLabel labelOldPass, labelRetypePass, labelNewPass;
	private JPasswordField textOldPass, textNewPass, textRetypePass;
	private JButton buttonAdd;

	public ChangePassword() {
		super("Change Password", true, true, true, true);
		this.InitializeComponents();
	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Confirm")) {
			if (this.textOldPass.getText().trim().equals("")
					|| this.textNewPass.getText().trim().equals("")
					|| this.textRetypePass.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null,
						"Please Fillup Required Field");
			}

			else {

				ChangePassAdapter adapter = new ChangePassAdapter();
				String oldpass = adapter.getOldPass();

				if (this.textOldPass.getText().toString().trim()
						.equals(oldpass)) {
					if (this.textNewPass
							.getText()
							.toString()
							.trim()
							.equals(this.textRetypePass.getText().toString()
									.trim())) {
						String updatePass = this.textRetypePass.getText()
								.toString().trim();
						if (!adapter.updatePassword(updatePass)) {
							JOptionPane.showMessageDialog(null,
									"Password Changed Successfully");
							this.textOldPass.setText("");
							this.textNewPass.setText("");
							this.textRetypePass.setText("");
						}
					}

					else {
						JOptionPane.showMessageDialog(null,
								"Passwords not matched");
					}
				}

				else {
					JOptionPane.showMessageDialog(null,
							"Incorrect Old Password");
				}

			}
		}
	}

	public void InitializeComponents() {

		this.panel = new JPanel();
		this.panel.setLayout(null);

		this.labelOldPass = new JLabel("Old Password");
		this.labelOldPass.setBounds(20, 20, 110, 20);
		this.panel.add(this.labelOldPass);

		this.labelNewPass = new JLabel("New Password");
		this.labelNewPass.setBounds(20, 50, 110, 20);
		this.panel.add(this.labelNewPass);

		this.labelRetypePass = new JLabel("Re-type Password");
		this.labelRetypePass.setBounds(20, 80, 110, 20);
		this.panel.add(this.labelRetypePass);

		this.textOldPass = new JPasswordField();
		this.textOldPass.setBounds(140, 20, 120, 20);
		this.panel.add(this.textOldPass);

		this.textNewPass = new JPasswordField();
		this.textNewPass.setBounds(140, 50, 120, 20);
		this.panel.add(this.textNewPass);

		this.textRetypePass = new JPasswordField();
		this.textRetypePass.setBounds(140, 80, 120, 20);
		this.panel.add(this.textRetypePass);

		this.buttonAdd = new JButton("Confirm");
		this.buttonAdd.setBounds(140, 120, 120, 20);
		this.buttonAdd.addActionListener(this);
		this.panel.add(this.buttonAdd);

		this.add(this.panel);
		this.setSize(290, 200);
	}
}
