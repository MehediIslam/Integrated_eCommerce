package ui;

import javax.swing.*;

import adapter.UserAdapter;
import model.Session;
import model.User;

import java.awt.event.*;

public class Login extends JFrame implements ActionListener

{
	private JPanel panel;
	private JLabel labelUsername, labelPassword;
	private JTextField textUsername;
	private JPasswordField textPassword;
	private JButton buttonLogin;

	public Login() {
		this.InitializeComponents();
	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Login")) {
			User user = new User();
			user.setUsername(textUsername.getText());
			user.setPassword(textPassword.getText());

			UserAdapter userAdapter = new UserAdapter();
			if (userAdapter.validateUser(user)) {

				Session session = new Session();
				session.setUserId(userAdapter.getUserId(user));
				userAdapter.insertLastDate(user);
				
				MotherUi ui = new MotherUi();
				ui.Ui();
				Session session2 = new Session();
				session2.setUi(ui);
				ui.setLocationRelativeTo(null);
				
				this.setVisible(false);
			} 
			
			else if (userAdapter.validateUserEmploye(user)) {

				Session session = new Session();
				session.setUserId(userAdapter.getUserId(user));
				userAdapter.insertLastDate(user);
				
				MotherUiofEmployer eui = new MotherUiofEmployer();
				eui.Ui();
				Session session3 = new Session();
				session3.setEUi(eui);
				eui.setLocationRelativeTo(null);
				
				this.setVisible(false);
			} 
			
			else {
				JOptionPane.showMessageDialog(null, "Invalid Username or Password");
			}
		}
	}
	
	public void InitializeComponents() {
		this.setTitle("Login");
		this.panel = new JPanel();
		this.panel.setLayout(null);

		this.labelUsername = new JLabel("Username");
		this.labelUsername.setBounds(20, 20, 110, 20);
		this.panel.add(this.labelUsername);

		this.labelPassword = new JLabel("Password");
		this.labelPassword.setBounds(20, 50, 110, 20);
		this.panel.add(this.labelPassword);

		this.textUsername = new JTextField();
		this.textUsername.setBounds(140, 20, 120, 20);
		this.panel.add(this.textUsername);

		this.textPassword = new JPasswordField();
		this.textPassword.setBounds(140, 50, 120, 20);
		this.panel.add(this.textPassword);

		this.buttonLogin = new JButton("Login");
		this.buttonLogin.setBounds(140, 90, 120, 20);
		this.buttonLogin.addActionListener(this);
		this.panel.add(this.buttonLogin);
		
		this.add(this.panel);
		this.setSize(290, 180);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class Start {
	public static void main(String[] args) {
		Login t = new Login();
		t.setVisible(true);
	}
}