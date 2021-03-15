package ui;

import javax.swing.*;
import java.awt.event.*;

class CustomerDiscount extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel labelUserId, labelDiscount, labelStartDate, labelEndDate;
	private JTextField textUserId;
	private JTextField textDiscount;
	private JTextField textStartDate;
	private JTextField textEndDate;
	private JButton buttonAdd;

	public CustomerDiscount() {
		super("Customer Diacount", true, true, true, true);
		this.InitializeComponents();
	}

	public void actionPerformed(ActionEvent event) {

	}

	public void InitializeComponents() {

		this.panel = new JPanel();
		this.panel.setLayout(null);

		this.labelUserId = new JLabel("User Id");
		this.labelUserId.setBounds(20, 20, 110, 20);
		this.panel.add(this.labelUserId);

		this.labelDiscount = new JLabel("Customer Discount");
		this.labelDiscount.setBounds(20, 40, 110, 20);
		this.panel.add(this.labelDiscount);

		this.labelStartDate = new JLabel("Start date");
		this.labelStartDate.setBounds(20, 60, 110, 20);
		this.panel.add(this.labelStartDate);

		this.labelEndDate = new JLabel("End Date");
		this.labelEndDate.setBounds(20, 80, 110, 20);
		this.panel.add(this.labelEndDate);

		this.textUserId = new JTextField();
		this.textUserId.setBounds(140, 20, 120, 20);
		this.panel.add(this.textUserId);

		this.textDiscount = new JTextField();
		this.textDiscount.setBounds(140, 40, 120, 20);
		this.panel.add(this.textDiscount);

		this.textStartDate = new JTextField();
		this.textStartDate.setBounds(140, 60, 120, 20);
		this.panel.add(this.textStartDate);

		this.textEndDate = new JTextField();
		this.textEndDate.setBounds(140, 80, 120, 20);
		this.panel.add(this.textEndDate);

		this.buttonAdd = new JButton("Add");
		this.buttonAdd.setBounds(160, 160, 100, 20);
		this.buttonAdd.addActionListener(this);
		this.panel.add(this.buttonAdd);

		this.add(this.panel);

		this.setSize(400, 300);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
