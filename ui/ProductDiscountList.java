package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

import model.ProductDiscounts;
import adapter.ProductsDiscountAdapter;

import java.awt.Color;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class ProductDiscountList extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private TableRowSorter<TableModel> rowSorter;
	private JPanel panel, panel1;
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel labelpercent, labelSerial, labelDiscount, labelStartDate,
			labelEndtDate, labelSearch;
	private JTextField textserial, textDiscount, textEndtDate, textStartDate,
			textboxSearch;
	private JButton buttonDone, buttonCancel, buttonEdit;
	int i;

	/* =========== Product Discount List table [Done by rup]=========== */

	public ProductDiscountList() {
		super("Product Discount List", true, true, true, true);
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
			ProductDiscounts dis = new ProductDiscounts();
			Integer serial = Integer.parseInt(this.textserial.getText()
					.toString());
			double disc = Double.parseDouble(this.textDiscount.getText()
					.toString());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String startDate = this.textStartDate.getText().toString();
			String endDate = this.textEndtDate.getText().toString();

			dis.setId(serial);
			dis.setDiscount(disc);
			try {

				dis.setStartDate(formatter.parse(this.textStartDate.getText()));
				dis.setEndDate(formatter.parse(this.textEndtDate.getText()));

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// table refresh
			this.tableModel.setValueAt(serial, i, 0);
			this.tableModel.setValueAt(disc, i, 2);
			this.tableModel.setValueAt(startDate, i, 3);
			this.tableModel.setValueAt(endDate, i, 4);

			// brandEdit Query function calling
			ProductsDiscountAdapter adapter = new ProductsDiscountAdapter();
			adapter.productsdiscountEdit(dis);

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
		this.panel.setBounds(0, 0, 630, 370);
		this.panel.setBackground(Color.GRAY);

		// table view
		ProductsDiscountAdapter adapter = new ProductsDiscountAdapter();
		Vector<String> columns = new Vector<String>();
		columns.add("Serial");
		columns.add("Product Name");
		columns.add("Discount (%)");
		columns.add("Start Date");
		columns.add("End Date");

		this.tableModel = new DefaultTableModel(adapter.getProDiscountList(),
				columns);
		this.table = new JTable(this.tableModel);
		this.rowSorter = new TableRowSorter<>(this.table.getModel());
		this.table.setRowSorter(rowSorter);

		this.scrollPane = new JScrollPane(this.table);
		this.scrollPane.setBounds(0, 0, 628, 300);
		this.panel.add(this.scrollPane);

		this.labelSearch = new JLabel("Search");
		this.labelSearch.setBounds(5, 330, 150, 20);
		this.panel.add(this.labelSearch);
		this.labelSearch.setForeground(Color.orange);
		this.labelSearch.setFont(labelSearch.getFont().deriveFont(16.0f));

		this.textboxSearch = new JTextField();
		this.textboxSearch.setBounds(70, 330, 180, 20);
		this.panel.add(this.textboxSearch);

		this.buttonEdit = new JButton("Edit");
		this.buttonEdit.setBounds(540, 330, 80, 20);
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
		// Editing Panel1
		this.panel1 = new JPanel();
		this.panel1.setLayout(null);
		this.panel1.setBounds(0, 370, 630, 370);
		this.panel1.setBackground(Color.lightGray);

		this.labelSerial = new JLabel("Discount ID");
		this.labelSerial.setBounds(20, 30, 110, 20);
		this.panel1.add(this.labelSerial);

		this.labelDiscount = new JLabel("Discount");
		this.labelDiscount.setBounds(20, 70, 110, 20);
		this.panel1.add(this.labelDiscount);

		this.labelStartDate = new JLabel("Start Date");
		this.labelStartDate.setBounds(20, 110, 110, 20);
		this.panel1.add(this.labelStartDate);

		this.labelEndtDate = new JLabel("Endt Date");
		this.labelEndtDate.setBounds(20, 150, 110, 20);
		this.panel1.add(this.labelEndtDate);

		// Data passing to edit Panel1
		this.textserial = new JTextField(tableModel.getValueAt(i, 0).toString());
		this.textserial.setBounds(140, 30, 120, 20);
		this.textserial.setEditable(false);
		this.panel1.add(this.textserial);

		this.textDiscount = new JTextField(tableModel.getValueAt(i, 2)
				.toString());
		this.textDiscount.setBounds(140, 70, 100, 20);
		this.panel1.add(this.textDiscount);
		this.textDiscount.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.labelpercent = new JLabel("%");
		this.labelpercent.setBounds(245, 70, 110, 20);
		this.panel1.add(this.labelpercent);
		this.labelpercent.setFont(labelpercent.getFont().deriveFont(14.0f));

		this.textStartDate = new JTextField(tableModel.getValueAt(i, 3)
				.toString());
		this.textStartDate.setBounds(140, 110, 120, 20);
		this.panel1.add(this.textStartDate);

		this.textEndtDate = new JTextField(tableModel.getValueAt(i, 4)
				.toString());
		this.textEndtDate.setBounds(140, 150, 120, 20);
		this.panel1.add(this.textEndtDate);

		this.buttonDone = new JButton("Done");
		this.buttonDone.setBounds(540, 150, 80, 20);
		this.buttonDone.addActionListener(this);
		this.panel1.add(this.buttonDone);

		this.buttonCancel = new JButton("Cancel");
		this.buttonCancel.setBounds(540, 120, 80, 20);
		this.buttonCancel.addActionListener(this);
		this.panel1.add(this.buttonCancel);

		this.add(this.panel1);
		this.add(this.panel);
		this.setSize(638, 590);
		this.revalidate();
	}
}
