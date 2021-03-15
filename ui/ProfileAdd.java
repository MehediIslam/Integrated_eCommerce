package ui;

import javax.swing.*;

import model.Profile;
import model.Session;
import model.User;
import adapter.ProfileAdapter;
import adapter.UserAdapter;

import java.awt.event.*;

class ProfileAdd extends JInternalFrame implements ActionListener {
	private JPanel panel;
	private JLabel labelName, labelPhone, labelEmail, labelAddress,
			labelPassword, labelType;
	private JTextField textName, textPhone, textEmail, textAddress,
			textPassword;
	private JButton buttonAdd;
	private JComboBox type;

	public ProfileAdd() {
		super("Add Profile", true, true, true, true);
		this.InitializeComponents();
	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Add")) {
			if (this.textName.getText().trim().equals("")
					|| this.textPhone.getText().trim().equals("")
					|| this.textEmail.getText().trim().equals("")
					|| this.textAddress.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null,
						"Please Fillup Required Field");
			}

			else {

				// get user type
				Session session = new Session();
				int sess_id = session.getUserId();
				UserAdapter typeAdapter = new UserAdapter();
				int type_id = typeAdapter.getUserType(sess_id);

				Profile profile = new Profile();

				User user = new User();
				user.setUsername(this.textPhone.getText().toString());
				user.setPassword(this.textPassword.getText().trim().toString());

				// Manager role -> Access
				if (type_id == 200) 
				{
					if (this.type.getSelectedItem().toString().equals("Manager")) 
					{
						JOptionPane.showMessageDialog(null,"You can not add this user type");
					}

					else {
						user.setType(this.type.getSelectedItem().toString());
						UserAdapter userAdapter = new UserAdapter();
						userAdapter.insertUser(user);

						int id = userAdapter.getUserId(user);
						profile.setUserId(id);
						profile.setName(textName.getText().trim());
						profile.setPhone(textPhone.getText().trim());
						profile.setEmail(textEmail.getText().trim());
						profile.setAddress(textAddress.getText().trim());
						profile.setCreatorId(sess_id);

						ProfileAdapter profileAdapter = new ProfileAdapter();
						if (!profileAdapter.insertProfile(profile)) {
							JOptionPane.showMessageDialog(null, "Success");
							this.textName.setText("");
							this.textPhone.setText("");
							this.textEmail.setText("");
							this.textAddress.setText("");
							this.textPassword.setText("");
						}

						else {
							JOptionPane.showMessageDialog(null, "Failed");
						}
					}
				}

				// Employer role -> Access
				else if (type_id == 300) {
					if (this.type.getSelectedItem().toString()
							.equals("Manager")
							|| this.type.getSelectedItem().toString()
									.equals("Employee")) {
						JOptionPane.showMessageDialog(null,
								"You can not add this user type");
					}

					else {
						user.setType(this.type.getSelectedItem().toString());
						UserAdapter userAdapter = new UserAdapter();
						userAdapter.insertUser(user);

						int id = userAdapter.getUserId(user);
						profile.setUserId(id);
						profile.setName(textName.getText().trim());
						profile.setPhone(textPhone.getText().trim());
						profile.setEmail(textEmail.getText().trim());
						profile.setAddress(textAddress.getText().trim());
						profile.setCreatorId(sess_id);

						ProfileAdapter profileAdapter = new ProfileAdapter();
						if (!profileAdapter.insertProfile(profile)) {
							JOptionPane.showMessageDialog(null, "Success");
							this.textName.setText("");
							this.textPhone.setText("");
							this.textEmail.setText("");
							this.textAddress.setText("");
							this.textPassword.setText("");
						}

						else {
							JOptionPane.showMessageDialog(null, "Failed");
						}
					}
				}

				// Admin role -> Access
				else if (type_id == 100) {
					user.setType(this.type.getSelectedItem().toString());
					UserAdapter userAdapter = new UserAdapter();
					userAdapter.insertUser(user);

					int id = userAdapter.getUserId(user);
					profile.setUserId(id);
					profile.setName(textName.getText().trim());
					profile.setPhone(textPhone.getText().trim());
					profile.setEmail(textEmail.getText().trim());
					profile.setAddress(textAddress.getText().trim());
					profile.setCreatorId(sess_id);

					ProfileAdapter profileAdapter = new ProfileAdapter();
					if (!profileAdapter.insertProfile(profile)) {
						JOptionPane.showMessageDialog(null, "Success");
						this.textName.setText("");
						this.textPhone.setText("");
						this.textEmail.setText("");
						this.textAddress.setText("");
						this.textPassword.setText("");
					}

					else {
						JOptionPane.showMessageDialog(null, "Failed");
					}
				}
			}
		}
	}

	public void InitializeComponents() {
		this.panel = new JPanel();
		this.panel.setLayout(null);

		this.labelName = new JLabel("Name");
		this.labelName.setBounds(20, 40, 110, 20);
		this.panel.add(this.labelName);

		this.labelPhone = new JLabel("Phone");
		this.labelPhone.setBounds(20, 60, 110, 20);
		this.panel.add(this.labelPhone);

		this.labelEmail = new JLabel("Email");
		this.labelEmail.setBounds(20, 80, 110, 20);
		this.panel.add(this.labelEmail);

		this.labelAddress = new JLabel("Address");
		this.labelAddress.setBounds(20, 100, 110, 20);
		this.panel.add(this.labelAddress);

		this.labelPassword = new JLabel("Password");
		this.labelPassword.setBounds(20, 120, 110, 20);
		this.panel.add(this.labelPassword);

		this.labelType = new JLabel("Type");
		this.labelType.setBounds(20, 140, 110, 20);
		this.panel.add(this.labelType);

		this.textName = new JTextField();
		this.textName.setBounds(140, 40, 120, 20);
		this.panel.add(this.textName);

		this.textPhone = new JTextField();
		this.textPhone.setBounds(140, 60, 120, 20);
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
		this.textEmail.setBounds(140, 80, 120, 20);
		this.panel.add(this.textEmail);

		this.textAddress = new JTextField();
		this.textAddress.setBounds(140, 100, 120, 20);
		this.panel.add(this.textAddress);

		this.textPassword = new JTextField();
		this.textPassword.setBounds(140, 120, 120, 20);
		this.panel.add(this.textPassword);

		String[] List = new String[] { "Consummer", "Employee", "Manager" };
		this.type = new JComboBox(List);
		this.type.setBounds(140, 140, 120, 20);
		this.panel.add(this.type);

		this.buttonAdd = new JButton("Add");
		this.buttonAdd.setBounds(140, 200, 120, 20);
		this.buttonAdd.addActionListener(this);
		this.panel.add(this.buttonAdd);

		this.add(this.panel);
		this.setSize(320, 300);
	}
}
