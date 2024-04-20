import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BookInventory extends JFrame {
	
	
	// Components
	private JPanel contentPane;
	private static JTable tblBookStocks;
	private JButton btnAddStock, btnChangeStatus, btnExit;
	private JSpinner spinnerStocks;
	private JTextField txtSearchBar;
	private JRadioButton rdoOption1, rdoOption2, rdoOption3, rdoOption4;
	
	// Variables
	private int stockId = 1; 
	private String newStatus;
	private int selectedRow = Main.fetchedIndices[Main.tblBookInventory.getSelectedRow()];
	private int selectedBookId = Integer.parseInt(Main.modelBookRecord.getValueAt(selectedRow, 0).toString());
	
	private static String selectedRdo = "All";
	private int[] fetchedIndices;	
	private JLabel lblFilterIcon;
	

	
	public BookInventory() {
		setUndecorated(true);
		setBounds(100, 100, 800, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblFrameTitle = new JLabel("Manage Book Inventory");
		// Creates an icon object from the local source folder
		ImageIcon lmsframeIcon = new ImageIcon(this.getClass().getResource("LMSFrameIcon.png"));
		// Assigns the icon object as the icon image of components
		lblFrameTitle.setIcon(lmsframeIcon);
		lblFrameTitle.setBackground(new Color(216, 227, 231));
		lblFrameTitle.setIconTextGap(10);
		lblFrameTitle.setForeground(new Color(216, 227, 231));
		lblFrameTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFrameTitle.setBounds(10, 10, 250, 30);
		contentPane.add(lblFrameTitle);
		
		// Creates an icon object from the local source folder
		ImageIcon exitIcon = new ImageIcon(this.getClass().getResource("exitIcon.png"));
		ImageIcon exitPressedIcon = new ImageIcon(this.getClass().getResource("exitPressedIcon.png"));
		ImageIcon exitRolloverIcon = new ImageIcon(this.getClass().getResource("exitRolloverIcon.png"));
		// Assigns the icon object as the icon image of components
		btnExit = new JButton(exitIcon);
		btnExit.setToolTipText("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(contentPane, "Are you sure you want to close?", "Confirmation", JOptionPane.YES_NO_OPTION);
					
				if (response == JOptionPane.YES_OPTION) {
					Main.refreshMain();
					dispose();
					
				} else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		btnExit.setPressedIcon(exitPressedIcon);
		btnExit.setRolloverIcon(exitRolloverIcon);
		btnExit.setRequestFocusEnabled(false);
		btnExit.setBorder(null);
		btnExit.setBackground(new Color(19, 44, 51));
		btnExit.setForeground(new Color(216, 227, 231));
		btnExit.setFocusable(false);
		btnExit.setBounds(760, 10, 30, 30);
		contentPane.add(btnExit);
		
		txtSearchBar = new JTextField();
		txtSearchBar.setToolTipText("Search bar");
		txtSearchBar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				doFilter();
			}
		});
		txtSearchBar.setText(" Search");
		txtSearchBar.setForeground(new Color(216, 227, 231));
		txtSearchBar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtSearchBar.setColumns(10);
		txtSearchBar.setCaretColor(new Color(216, 227, 231));
		txtSearchBar.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtSearchBar.setBackground(new Color(19, 44, 51));
		txtSearchBar.setBounds(295, 50, 250, 30);
		contentPane.add(txtSearchBar);
		txtSearchBar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtSearchBar.getText().isEmpty() == true) {
					txtSearchBar.setText(" Search");
				}else if(txtSearchBar.getText().equals(" Search")) {
					txtSearchBar.setText(null);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtSearchBar.getText().isEmpty() == true) {
					txtSearchBar.setText(" Search");
				}
			}
		});
		
		JPanel panelFilter = new JPanel();
		panelFilter.setVisible(false);
		panelFilter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelFilter.setVisible(false);
			}
		});
		panelFilter.setBackground(new Color(216, 227, 231));
		panelFilter.setBounds(555, 80, 120, 163);
		contentPane.add(panelFilter);
		panelFilter.setLayout(null);
		
		JLabel lblFilters = new JLabel("Filters");
		lblFilters.setForeground(new Color(19, 44, 51));
		lblFilters.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblFilters.setBounds(10, 10, 55, 20);
		panelFilter.add(lblFilters);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(19, 44, 51));
		separator_2.setBackground(new Color(19, 44, 51));
		separator_2.setBounds(10, 31, 55, 2);
		panelFilter.add(separator_2);
		
		rdoOption1 = new JRadioButton("All");
		rdoOption1.setToolTipText("Filter all");
		rdoOption1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRdo = "All";
				doFilter();
				panelFilter.setVisible(false);
			}
		});
		rdoOption1.setSelected(true);
		rdoOption1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelFilter.setVisible(true);
			}
		});
		rdoOption1.setFocusable(false);
		rdoOption1.setForeground(new Color(19, 44, 51));
		rdoOption1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		rdoOption1.setBorder(null);
		rdoOption1.setBackground(new Color(216, 227, 231));
		rdoOption1.setBounds(10, 40, 100, 20);
		panelFilter.add(rdoOption1);
		
		rdoOption2 = new JRadioButton("Available");
		rdoOption2.setToolTipText("Filter available");
		rdoOption2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRdo = "Available";
				doFilter();
				panelFilter.setVisible(false);
			}
		});
		rdoOption2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelFilter.setVisible(true);
			}
		});
		rdoOption2.setFocusable(false);
		rdoOption2.setForeground(new Color(19, 44, 51));
		rdoOption2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		rdoOption2.setBorder(null);
		rdoOption2.setBackground(new Color(216, 227, 231));
		rdoOption2.setBounds(10, 70, 100, 20);
		panelFilter.add(rdoOption2);
		
		rdoOption3 = new JRadioButton("Damaged");
		rdoOption3.setToolTipText("Filter damaged");
		rdoOption3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRdo = "Damaged";
				doFilter();
				panelFilter.setVisible(false);
			}
		});
		rdoOption3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelFilter.setVisible(true);
			}
		});
		rdoOption3.setFocusable(false);
		rdoOption3.setForeground(new Color(19, 44, 51));
		rdoOption3.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		rdoOption3.setBorder(null);
		rdoOption3.setBackground(new Color(216, 227, 231));
		rdoOption3.setBounds(10, 100, 100, 20);
		panelFilter.add(rdoOption3);
		
		rdoOption4 = new JRadioButton("Borrowed");
		rdoOption4.setToolTipText("Filter borrowed");
		
		rdoOption4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRdo = "Borrowed";
				doFilter();
				panelFilter.setVisible(false);
			}
		});
		rdoOption4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelFilter.setVisible(true);
			}
		});
		rdoOption4.setForeground(new Color(19, 44, 51));
		rdoOption4.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		rdoOption4.setFocusable(false);
		rdoOption4.setBorder(null);
		rdoOption4.setBackground(new Color(216, 227, 231));
		rdoOption4.setBounds(10, 128, 100, 20);
		panelFilter.add(rdoOption4);
		
		ButtonGroup rdoGroupFilter = new ButtonGroup();
		rdoGroupFilter.add(rdoOption1);
		rdoGroupFilter.add(rdoOption2);
		rdoGroupFilter.add(rdoOption3);
		rdoGroupFilter.add(rdoOption4);
		
		
		lblFilterIcon = new JLabel("");
		lblFilterIcon.setToolTipText("Filtering");
		// Creates an icon object from the local source folder
		ImageIcon childframefilterIcon = new ImageIcon(this.getClass().getResource("childframefilterIcon.png"));
		// Assigns the icon object as the icon image of components
		lblFilterIcon.setIcon(childframefilterIcon);
		lblFilterIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblFilterIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelFilter.setVisible(true);;
			}
		});
		lblFilterIcon.setBounds(555, 50, 40, 30);
		contentPane.add(lblFilterIcon);
		
		JLabel lblSearchIcon = new JLabel("");
		lblSearchIcon.setToolTipText("Search");
		// Creates an icon object from the local source folder
		ImageIcon childframesearchIcon = new ImageIcon(this.getClass().getResource("childframesearchIcon.png"));
		// Assigns the icon object as the icon image of components
		lblSearchIcon.setIcon(childframesearchIcon);
		lblSearchIcon.setBounds(255, 50, 30, 30);
		contentPane.add(lblSearchIcon);
		
		JScrollPane scrollPaneBookStocks = new JScrollPane();
		scrollPaneBookStocks.setBounds(255, 90, 525, 270);
		contentPane.add(scrollPaneBookStocks);
		
		tblBookStocks = new JTable(Main.modelBookInventoryRecord){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tblBookStocks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblBookStocks.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tblBookStocks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tblBookStocks.getSelectedRow();
				
				if (Main.modelBookInventoryRecord.getValueAt(fetchedIndices[selectedRow], 6).toString().equals("Available")) {
					newStatus = "Damaged";
					btnChangeStatus.setEnabled(true);
				} else if (Main.modelBookInventoryRecord.getValueAt(fetchedIndices[selectedRow], 6).toString().equals("Damaged")){
					newStatus = "Available";	
					btnChangeStatus.setEnabled(true);
				} else {
					btnChangeStatus.setEnabled(false);
				}
			}
		});
		scrollPaneBookStocks.setViewportView(tblBookStocks);
		
		Main.hideTableColumns(tblBookStocks, new int[] {3, 4, 5});
		
		spinnerStocks = new JSpinner();
		spinnerStocks.setToolTipText("Input how many stock");
		spinnerStocks.setForeground(new Color(216, 227, 231));
		spinnerStocks.setBackground(new Color(18, 110, 130));
		spinnerStocks.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		spinnerStocks.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinnerStocks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				spinnerStocks.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				spinnerStocks.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		spinnerStocks.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinnerStocks.setBounds(20, 100, 215, 30);
		contentPane.add(spinnerStocks);
		
		// Disable the editable property
		JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinnerStocks.getEditor();
		editor.getTextField().setEnabled(true);
		editor.getTextField().setEditable(false);
		
		btnAddStock = new JButton("ADD STOCKS");
		btnAddStock.setToolTipText("Click to add stocks");
		btnAddStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				addStocks();
			}
		});
		btnAddStock.setFocusable(false);
		btnAddStock.setForeground(new Color(216, 227, 231));
		btnAddStock.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnAddStock.setBackground(new Color(18, 110, 130));
		btnAddStock.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnAddStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAddStock.setBackground(new Color(81, 196, 211));
				btnAddStock.setForeground(new Color(19, 44, 51));
				btnAddStock.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAddStock.setBackground(new Color(18, 110, 130));
				btnAddStock.setForeground(new Color(216, 227, 231));
				btnAddStock.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		btnAddStock.setBounds(35, 145, 180, 30);
		contentPane.add(btnAddStock);
		
		btnChangeStatus = new JButton("CHANGE STATUS");
		btnChangeStatus.setToolTipText("Click to change status");
		btnChangeStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				int selectedRowStock = tblBookStocks.getSelectedRow();

				int available = (int) Main.modelBookRecord.getValueAt(selectedRow, 6);
				int damaged = (int) Main.modelBookRecord.getValueAt(selectedRow, 7);
				
				int response = JOptionPane.showConfirmDialog(contentPane, "Are you sure to change this stock's status to:   " + 
						newStatus, "Confirmation", JOptionPane.YES_NO_OPTION);
				
				
				if (response == JOptionPane.YES_OPTION) {
					if (newStatus.equals("Available")) {
						available++;
						damaged--;
					} else {
						damaged++;
						available--;
					}
					
					// Update the number of available or damaged books in book record
					Main.modelBookRecord.setValueAt(available, selectedRow, 6);
					Main.modelBookRecord.setValueAt(damaged, selectedRow, 7);
					
					// Update the status of selected stock in modelBookInventoryRecord
					Main.modelBookInventoryRecord.setValueAt(newStatus, fetchedIndices[selectedRowStock], 6);
					
					JOptionPane.showMessageDialog(contentPane, "Selected stock's status updated to:   " + newStatus);
					refreshControls();
				}
				
				tblBookStocks.clearSelection();
			}
		});
		btnChangeStatus.setFocusable(false);
		btnChangeStatus.setForeground(new Color(216, 227, 231));
		btnChangeStatus.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnChangeStatus.setBackground(new Color(18, 110, 130));
		btnChangeStatus.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnChangeStatus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(btnChangeStatus.isEnabled()) {
					btnChangeStatus.setBackground(new Color(81, 196, 211));
					btnChangeStatus.setForeground(new Color(19, 44, 51));
					btnChangeStatus.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(btnChangeStatus.isEnabled()) {
					btnChangeStatus.setBackground(new Color(18, 110, 130));
					btnChangeStatus.setForeground(new Color(216, 227, 231));
					btnChangeStatus.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
				}
			}
		});
		btnChangeStatus.setEnabled(false);
		btnChangeStatus.setBounds(35, 205, 180, 30);
		contentPane.add(btnChangeStatus);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(216, 227, 231));
		separator_1.setBackground(new Color(216, 227, 231));
		separator_1.setBounds(20, 190, 215, 4);
		contentPane.add(separator_1);
		
		JLabel lblInventoryBackground = new JLabel("");
		// Creates an icon object from the local source folder
		ImageIcon inventoryBackground = new ImageIcon(this.getClass().getResource("inventoryBackground.png"));
		// Assigns the icon object as the icon image of components
		lblInventoryBackground.setIcon(inventoryBackground);
		lblInventoryBackground.setBounds(0, 0, 800, 380);
		contentPane.add(lblInventoryBackground);
		
		// Set controls to default and fetch the indices of stocks of a specific book  
		refreshControls();
	}
	
	// METHODS:
	// Method for adding new stock of a book
	private void addStocks() {
		String bookId = Main.modelBookRecord.getValueAt(selectedRow, 0).toString();
		String bookTitle = Main.modelBookRecord.getValueAt(selectedRow, 1).toString();
		
		int stocks = (int) Main.modelBookRecord.getValueAt(selectedRow, 5);
		int available = (int) Main.modelBookRecord.getValueAt(selectedRow, 6);
		int numberOfStocks = (int) spinnerStocks.getValue();
		
		int response = JOptionPane.showConfirmDialog(contentPane, "Are you sure to add stocks to book ID:    " +  bookId +
				" - (" + bookTitle + ").", "Confirmation", JOptionPane.YES_NO_OPTION);
		
		if (response == JOptionPane.YES_OPTION) {
			txtSearchBar.setText(" Search");
			rdoOption1.setSelected(true);
			selectedRdo = "All";
			doFilter();
			
			// Add new stocks in the selected book record
			for (int i = 0; i < numberOfStocks; i++) {
				Object[] newRow = new Object[7];
				
				newRow[0] = Integer.parseInt(bookId);
				newRow[1] = "STK-" + stockId;
				newRow[2] = bookTitle;
				newRow[3] = Main.modelBookRecord.getValueAt(selectedRow, 2).toString();
				newRow[4] = Main.modelBookRecord.getValueAt(selectedRow, 3).toString();
				newRow[5] = Main.modelBookRecord.getValueAt(selectedRow, 4).toString();
				newRow[6] = "Available";
				
				stockId++;
				
				Main.modelBookInventoryRecord.addRow(newRow);
			}	
			
			// Update the number of stocks and available books in Book record
			stocks += numberOfStocks;	
			available += numberOfStocks;
			Main.modelBookRecord.setValueAt(stocks, selectedRow, 5);
			Main.modelBookRecord.setValueAt(available, selectedRow, 6);
			
			JOptionPane.showMessageDialog(contentPane, numberOfStocks + " stock(s) added in book ID:    " + bookId);
			refreshControls();
		}
	}
	
	
	// Method for setting the value of selected properties of necessary controls
	private void refreshControls() {
		// Design
		btnChangeStatus.setBackground(new Color(18, 110, 130));
		btnChangeStatus.setForeground(new Color(216, 227, 231));
		btnChangeStatus.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		
		txtSearchBar.setText(" Search");
		rdoOption1.setSelected(true);
		selectedRdo = "All";
		
		doFilter();
		
		spinnerStocks.setValue(1);
		btnChangeStatus.setEnabled(false);
		tblBookStocks.clearSelection();
	}
	
	
	// Method for filtering the data displayed on table and getting the original index of stocks displayed on filtered table
	private void doFilter() {
		btnChangeStatus.setEnabled(false);
		
		fetchedIndices = Main.filterTable(tblBookStocks, Main.modelBookInventoryRecord, txtSearchBar.getText(), 
				selectedRdo, selectedBookId, 0, new int[] {0, 1, 6});
		
		// Increment the stock id
		stockId = tblBookStocks.getRowCount();
		stockId++;
	}
}
