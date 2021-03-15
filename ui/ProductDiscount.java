package ui;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

import model.Categorie;
import model.ProductDiscounts;
import adapter.CategoryAdapter;
import adapter.ProductSaleAdapter;
import adapter.ProductsDiscountAdapter;

class ProductDiscount extends JInternalFrame implements ActionListener

{
	private TableRowSorter<TableModel> rowSorter;
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel LabelDiscount, LabelStartDate, LabelEndDate, LabelProductId,
			Labelpercent, labelSearch;
	private JTextField textDiscount, textProductId, textboxSearch;
	private JComboBox d, m, y, d1, m1, y1;
	private JButton buttonAdd, buttonAddProduct;
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	int i;

	public ProductDiscount() {
		super("Product Discount", true, true, true, true);
		this.InitializeComponents();
	}

	public void actionPerformed(ActionEvent event) {
		i = table.getSelectedRow();
		int selectedRow = i;

		String command = event.getActionCommand();
		if (command.equals("Select")) {
			if (selectedRow != -1) {
				this.textProductId.setText(tableModel.getValueAt(i, 0)
						.toString());
				this.buttonAdd.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "Please Select a Row");
			}
		}

		if (command.equals("Add")) {

			if (this.textProductId.getText().trim().equals("")
					|| this.textDiscount.getText().trim().equals("")
					|| this.d.getSelectedItem().equals("DD")
					|| this.m.getSelectedItem().equals("MM")
					|| this.y.getSelectedItem().equals("YYYY")
					|| this.d1.getSelectedItem().equals("DD")
					|| this.m1.getSelectedItem().equals("MM")
					|| this.y1.getSelectedItem().equals("YYYY")) {
				JOptionPane.showMessageDialog(null,
						"Please Fillup Required Field");
			}

			else {
				int proID = Integer.parseInt(this.textProductId.getText()
						.toString());
				ProductsDiscountAdapter existAdapter = new ProductsDiscountAdapter();
				existAdapter.checkExisting(proID);
				if(existAdapter.checkExisting(proID))
				{
					JOptionPane.showMessageDialog(null,
							"This Product Discount already Exists");
				}
				
				else
				{
					String dd = this.d.getSelectedItem().toString();
					String mm = this.m.getSelectedItem().toString();
					String yy = this.y.getSelectedItem().toString();

					String dd1 = this.d1.getSelectedItem().toString();
					String mm1 = this.m1.getSelectedItem().toString();
					String yy1 = this.y1.getSelectedItem().toString();

					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					int proId = Integer.parseInt(this.textProductId.getText()
							.toString());
					double proDiscount = Double.parseDouble(this.textDiscount
							.getText().trim().toString());
					String endDate = yy + "-" + mm + "-" + dd;
					String startDate = yy1 + "-" + mm1 + "-" + dd1;

					ProductDiscounts dis = new ProductDiscounts();
					dis.setId(proId);
					dis.setDiscount(proDiscount);

					try {
						dis.setStartDate(formatter.parse(startDate));
						dis.setEndDate(formatter.parse(endDate));
					} catch (ParseException e) {
						e.printStackTrace();
					}

					ProductsDiscountAdapter adapter1 = new ProductsDiscountAdapter();
					if (!adapter1.insertProductsDiscount(dis)) {
						JOptionPane.showMessageDialog(null, "Success");
						this.textProductId.setText("");
						this.textDiscount.setText("");
					}
				}
				
			}
		}
	}

	public void InitializeComponents() {
		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.panel.setBackground(Color.LIGHT_GRAY);

		this.labelSearch = new JLabel("Search");
		this.labelSearch.setBounds(80, 5, 150, 20);
		this.panel.add(this.labelSearch);
		this.labelSearch.setFont(labelSearch.getFont().deriveFont(16.0f));

		this.textboxSearch = new JTextField();
		this.textboxSearch.setBounds(140, 5, 200, 20);
		this.panel.add(this.textboxSearch);

		this.buttonAddProduct = new JButton("Select");
		this.buttonAddProduct.setBounds(400, 5, 100, 20);
		this.buttonAddProduct.addActionListener(this);
		this.panel.add(this.buttonAddProduct);

		ProductSaleAdapter adapter = new ProductSaleAdapter();
		Vector<String> columns = new Vector<String>();
		columns.add("Product ID");
		columns.add("Product Name");
		columns.add("Selling Price");
		columns.add("Quantity");

		this.tableModel = new DefaultTableModel(adapter.getProductList(),
				columns);
		this.table = new JTable(this.tableModel);
		this.rowSorter = new TableRowSorter<>(this.table.getModel());
		this.table.setRowSorter(rowSorter);

		this.scrollPane = new JScrollPane(this.table);
		this.scrollPane.setBounds(0, 30, 590, 250);
		this.panel.add(this.scrollPane);

		this.LabelProductId = new JLabel("Product Id");
		this.LabelProductId.setBounds(20, 300, 110, 20);
		this.panel.add(this.LabelProductId);

		this.LabelDiscount = new JLabel("Product Discount");
		this.LabelDiscount.setBounds(20, 330, 110, 20);
		this.panel.add(this.LabelDiscount);

		this.Labelpercent = new JLabel("%");
		this.Labelpercent.setBounds(280, 330, 110, 20);
		this.panel.add(this.Labelpercent);
		this.Labelpercent.setFont(Labelpercent.getFont().deriveFont(15.0f));

		this.LabelStartDate = new JLabel("Start Date");
		this.LabelStartDate.setBounds(20, 360, 110, 20);
		this.panel.add(this.LabelStartDate);

		this.LabelEndDate = new JLabel("End Date");
		this.LabelEndDate.setBounds(20, 390, 110, 20);
		this.panel.add(this.LabelEndDate);

		this.textProductId = new JTextField();
		this.textProductId.setBounds(140, 300, 155, 20);
		this.panel.add(this.textProductId);
		this.textProductId.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		this.textDiscount = new JTextField();
		this.textDiscount.setBounds(140, 330, 135, 20);
		this.panel.add(this.textDiscount);
		this.textDiscount.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
						&& (c != '+')) {
					e.consume(); // ignore event
				}
			}
		});

		String[] List1 = new String[] { "DD", "01", "02", "03", "04", "05",
				"06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
				"26", "27", "28", "29", "30", "31" };
		this.d = new JComboBox(List1);
		this.d.setBounds(140, 360, 45, 20);
		this.panel.add(this.d);

		String[] List2 = new String[] { "MM", "01", "02", "03", "04", "05",
				"06", "07", "08", "09", "10", "11", "12" };
		this.m = new JComboBox(List2);
		this.m.setBounds(190, 360, 45, 20);
		this.panel.add(this.m);

		String[] List3 = new String[] { "YYYY", "2014", "2015", "2016", "2017",
				"2018", "2019", "2020", "2021", "2022", "2023", "2024" };
		this.y = new JComboBox(List3);
		this.y.setBounds(240, 360, 55, 20);
		this.panel.add(this.y);

		String[] List11 = new String[] { "DD", "01", "02", "03", "04", "05",
				"06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
				"26", "27", "28", "29", "30", "31" };
		this.d1 = new JComboBox(List11);
		this.d1.setBounds(140, 390, 45, 20);
		this.panel.add(this.d1);

		String[] List22 = new String[] { "MM", "01", "02", "03", "04", "05",
				"06", "07", "08", "09", "10", "11", "12" };
		this.m1 = new JComboBox(List22);
		this.m1.setBounds(190, 390, 45, 20);
		this.panel.add(this.m1);

		String[] List33 = new String[] { "YYYY", "2014", "2015", "2016",
				"2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024" };
		this.y1 = new JComboBox(List33);
		this.y1.setBounds(240, 390, 55, 20);
		this.panel.add(this.y1);

		this.buttonAdd = new JButton("Add");
		this.buttonAdd.setBounds(450, 335, 60, 60);
		this.buttonAdd.addActionListener(this);
		this.buttonAdd.setEnabled(false);
		this.panel.add(this.buttonAdd);

		this.add(this.panel);
		this.setSize(600, 500);

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
}
