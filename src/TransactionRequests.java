import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Cursor;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.ListSelectionModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TransactionRequests extends JFrame {

	private CollectPenalty frameCollectPenalty;
	
	private static JPanel contentPane;
	private JPanel panelFilter;
	private static JTable tblTransactionRequests;
	private JLabel lblFilter, lblNewLabel;
	private static JButton btnAccept;
	private static JButton btnDecline;
	private JButton btnExit;
	private static JTextField txtSearchBar;
	private static JRadioButton rdoAll;
	private JRadioButton rdbtnBorrowing;
	private JRadioButton rdbtnReturning;
	private JSeparator separator;

	private static String selectedRdo = "All";
	public static int selectedRow = -1;
	public static int[] fetchedIndices;
	
	
	public TransactionRequests() {
		setUndecorated(true);
		setBounds(100, 100, 1200, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
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
		btnExit.setBounds(1160, 10, 30, 30);
		contentPane.add(btnExit);
		
		panelFilter = new JPanel();
		panelFilter.setVisible(false);
		panelFilter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelFilter.setVisible(false);
			}
		});
		panelFilter.setBackground(new Color(216, 227, 231));
		panelFilter.setBounds(510, 80, 120, 130);
		contentPane.add(panelFilter);
		panelFilter.setLayout(null);
		
		lblNewLabel = new JLabel("Filters");
		lblNewLabel.setForeground(new Color(19, 44, 51));
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 10, 55, 20);
		panelFilter.add(lblNewLabel);
		
		separator = new JSeparator();
		separator.setForeground(new Color(19, 44, 51));
		separator.setBackground(new Color(19, 44, 51));
		separator.setBounds(10, 31, 55, 2);
		panelFilter.add(separator);
		
		rdoAll = new JRadioButton("All");
		rdoAll.setToolTipText("Filter all");
		rdoAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRdo = "All";
				doFilter();
			}
		});
		rdoAll.setSelected(true);
		rdoAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelFilter.setVisible(true);;
			}
		});
		rdoAll.setFocusable(false);
		rdoAll.setForeground(new Color(19, 44, 51));
		rdoAll.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		rdoAll.setBorder(null);
		rdoAll.setBackground(new Color(216, 227, 231));
		rdoAll.setBounds(10, 40, 100, 20);
		panelFilter.add(rdoAll);
		
		rdbtnBorrowing = new JRadioButton("Borrowing");
		rdbtnBorrowing.setToolTipText("Filter borrowing");
		rdbtnBorrowing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRdo = "Borrowing";
				doFilter();
			}
		});
		rdbtnBorrowing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelFilter.setVisible(true);
			}
		});
		rdbtnBorrowing.setFocusable(false);
		rdbtnBorrowing.setForeground(new Color(19, 44, 51));
		rdbtnBorrowing.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		rdbtnBorrowing.setBorder(null);
		rdbtnBorrowing.setBackground(new Color(216, 227, 231));
		rdbtnBorrowing.setBounds(10, 70, 100, 20);
		panelFilter.add(rdbtnBorrowing);
		
		rdbtnReturning = new JRadioButton("Returning");
		rdbtnReturning.setToolTipText("Filter returning");
		rdbtnReturning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRdo = "Returning";
				doFilter();
			}
		});
		rdbtnReturning.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelFilter.setVisible(true);;
			}
		});
		rdbtnReturning.setFocusable(false);
		rdbtnReturning.setForeground(new Color(19, 44, 51));
		rdbtnReturning.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		rdbtnReturning.setBorder(null);
		rdbtnReturning.setBackground(new Color(216, 227, 231));
		rdbtnReturning.setBounds(10, 100, 100, 20);
		panelFilter.add(rdbtnReturning);
		
		ButtonGroup rdoGroupFilter = new ButtonGroup();
		rdoGroupFilter.add(rdoAll);
		rdoGroupFilter.add(rdbtnBorrowing);
		rdoGroupFilter.add(rdbtnReturning);
		
		lblFilter = new JLabel("");
		lblFilter.setToolTipText("Filtering");
		// Creates an icon object from the local source folder
		ImageIcon childframefilterIcon = new ImageIcon(this.getClass().getResource("childframefilterIcon.png"));
		// Assigns the icon object as the icon image of components
		lblFilter.setIcon(childframefilterIcon);
		lblFilter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblFilter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelFilter.setVisible(true);;
			}
		});
		lblFilter.setBounds(510, 50, 40, 30);
		contentPane.add(lblFilter);
		
		JLabel lblFrameTitle = new JLabel("Transaction Request");
		// Creates an icon object from the local source folder
		ImageIcon lmsframeIcon = new ImageIcon(this.getClass().getResource("LMSFrameIcon.png"));
		// Assigns the icon object as the icon image of components
		lblFrameTitle.setIcon(lmsframeIcon);
		lblFrameTitle.setForeground(new Color(216, 227, 231));
		lblFrameTitle.setIconTextGap(10);
		lblFrameTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFrameTitle.setBounds(10, 10, 250, 30);
		contentPane.add(lblFrameTitle);
		
		txtSearchBar = new JTextField();
		txtSearchBar.setToolTipText("Search bar");
		txtSearchBar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				doFilter();
			}
		});
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
		txtSearchBar.setText(" Search");
		txtSearchBar.setForeground(new Color(216, 227, 231));
		txtSearchBar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtSearchBar.setColumns(10);
		txtSearchBar.setCaretColor(new Color(216, 227, 231));
		txtSearchBar.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtSearchBar.setBackground(new Color(19, 44, 51));
		txtSearchBar.setBounds(200, 50, 300, 30);
		contentPane.add(txtSearchBar);
		
		JLabel lblSearchIcon = new JLabel("");
		lblSearchIcon.setToolTipText("Search");
		// Creates an icon object from the local source folder
		ImageIcon childframesearchIcon = new ImageIcon(this.getClass().getResource("childframesearchIcon.png"));
		// Assigns the icon object as the icon image of components
		lblSearchIcon.setIcon(childframesearchIcon);
		lblSearchIcon.setBounds(160, 50, 30, 30);
		contentPane.add(lblSearchIcon);
		
		JScrollPane scrollPaneTransactionRequests = new JScrollPane();
		scrollPaneTransactionRequests.setBounds(160, 90, 1020, 320);
		contentPane.add(scrollPaneTransactionRequests);
		
		tblTransactionRequests = new JTable(Main.modelTransactionRequestsRecord) {
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tblTransactionRequests.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblTransactionRequests.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedRow = tblTransactionRequests.getSelectedRow();
				int row = fetchedIndices[selectedRow];
				
				if (Main.modelTransactionRequestsRecord.getValueAt(row, 12).toString().equals("Borrowing")) {
					btnAccept.setEnabled(true);
					btnDecline.setEnabled(true);
				} else {
					btnAccept.setEnabled(true);
					btnDecline.setEnabled(false);
				}	
			}
		});
		tblTransactionRequests.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPaneTransactionRequests.setViewportView(tblTransactionRequests);
		
		btnAccept = new JButton("ACCEPT");
		btnAccept.setToolTipText("Click to accept");
		btnAccept.setEnabled(false);
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acceptRequest();
			}
		});
		btnAccept.setFocusable(false);
		btnAccept.setBackground(new Color(18, 110, 130));
		btnAccept.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnAccept.setForeground(new Color(216, 227, 231));
		btnAccept.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnAccept.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAccept.setBackground(new Color(81, 196, 211));
				btnAccept.setForeground(new Color(19, 44, 51));
				btnAccept.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAccept.setBackground(new Color(18, 110, 130));
				btnAccept.setForeground(new Color(216, 227, 231));
				btnAccept.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		btnAccept.setBounds(20, 100, 120, 30);
		contentPane.add(btnAccept);
		
		btnDecline = new JButton("DECLINE");
		btnDecline.setToolTipText("Click to decline");
		btnDecline.setEnabled(false);
		btnDecline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				declineRequest();
			}
		});
		btnDecline.setFocusable(false);
		btnDecline.setBackground(new Color(18, 110, 130));
		btnDecline.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnDecline.setForeground(new Color(216, 227, 231));
		btnDecline.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnDecline.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDecline.setBackground(new Color(81, 196, 211));
				btnDecline.setForeground(new Color(19, 44, 51));
				btnDecline.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnDecline.setBackground(new Color(18, 110, 130));
				btnDecline.setForeground(new Color(216, 227, 231));
				btnDecline.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		btnDecline.setBounds(20, 145, 120, 30);
		contentPane.add(btnDecline);
		
		JLabel lblTransactionRequestBackground = new JLabel("");
		// Creates an icon object from the local source folder
		ImageIcon transactionrequestBackground = new ImageIcon(this.getClass().getResource("transactionrequestBackground.png"));
		// Assigns the icon object as the icon image of components
		lblTransactionRequestBackground.setIcon(transactionrequestBackground);
		lblTransactionRequestBackground.setBounds(0, 0, 1200, 430);
		contentPane.add(lblTransactionRequestBackground);
		
		selectedRdo = "All";
		doFilter();
	}
	
	// METHODS:
	// Method for accepting request
	private void acceptRequest() {	
		int row = fetchedIndices[selectedRow];
		
		int response = JOptionPane.showConfirmDialog(contentPane, "Accept request?", "Confirmation", JOptionPane.YES_NO_OPTION);
		
		if (response == JOptionPane.YES_OPTION) {
			// For borrowing requests
			if (Main.modelTransactionRequestsRecord.getValueAt(row, 12).toString().equals("Borrowing")) {
				DateTimeFormatter format = DateTimeFormatter.ofPattern("MMMM-dd-yyyy");
				String date = Main.modelTransactionRequestsRecord.getValueAt(row, 9).toString();
				
				LocalDate todayDate = LocalDate.now();
				LocalDate borrowDate = LocalDate.parse(date, format); 
				
				int daysBetween = Period.between(borrowDate, todayDate).getDays();
				
				// if the borrowing request was yesterday. Admin has no choice but to decline it, and borrower must request another one
				if (daysBetween != 0) {
					JOptionPane.showMessageDialog(contentPane, "Unable to process the borrowing request! This was requested in: " + 
								borrowDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")) + "\nPlease decline the request.");
				} else {
					// Create new row for Borrowers Transaction Record
					Object[] newRow = new Object[12];
					
					for (int i = 0; i < newRow.length; i++) {
						newRow[i] = Main.modelTransactionRequestsRecord.getValueAt(row, i);
					}
					
					// Pass the values to Borrowers Transactions
					Main.modelBorrowersTransactionsRecord.addRow(newRow);
					
					
					// Create new row for recording the borrowed transaction in Transactions history
					Object[] newRecord = new Object[13];
					
					for (int i = 0; i < newRecord.length; i++) {
						if (i == 10) {
							newRecord[i] = "Borrowed";
						} else if (i == 12){
							newRecord[i] = 0;
						} else {
							newRecord[i] = Main.modelTransactionRequestsRecord.getValueAt(row, i);
						}						
					}
					
					// Pass the values in Transactions history
					Main.modelTransactionHistoryRecord.addRow(newRecord);
					
					// Then, delete the row to Transaction Requests
					Main.modelTransactionRequestsRecord.removeRow(row);
					
					JOptionPane.showMessageDialog(contentPane, "Selected borrowing request accepted!");
					
					tblTransactionRequests.clearSelection();
					txtSearchBar.setText(" Search");
					rdoAll.setSelected(true);
					selectedRdo = "All";
					doFilter();
				}
				
			// For returning requests
			} else {
				if (Integer.parseInt(Main.modelTransactionRequestsRecord.getValueAt(row, 11).toString()) > 0) {
					// load frame for collecting penalty
					frameCollectPenalty = new CollectPenalty();
					frameCollectPenalty.setVisible(true);
					setEnabled(false);	
				} else {
					returnBooks();
				}
			}
		}
	}
	
	
	// Method for returning books - will also be used in CollectPenalty frame
	public static void returnBooks() {
		int row = fetchedIndices[selectedRow];
		
		// Create new row Transaction History
		Object[] newRow = new Object[13];
		
		for (int i = 0; i < 12; i++) {
			if (i == 9) {
				newRow[i] = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM-dd-yyyy"));
			} else if (i == 10) {
				newRow[i] = "Returned";
			} else {
				newRow[i] = Main.modelTransactionRequestsRecord.getValueAt(row, i);					
			}
		}
		
		
		// Update the total penalties collected in selected student
		String studentId = Main.modelTransactionRequestsRecord.getValueAt(row, 1).toString(); 
		int lateFee = Integer.parseInt(Main.modelTransactionRequestsRecord.getValueAt(row, 11).toString());
		
		for (int i = 0; i < Main.modelStudentRecord.getRowCount(); i++) {
			String id = Main.modelStudentRecord.getValueAt(i, 0).toString();
			int penaltiesCollected = Integer.parseInt(Main.modelStudentRecord.getValueAt(i, 11).toString());
			
			if(studentId.equals(id)) {
				Main.modelStudentRecord.setValueAt(penaltiesCollected + lateFee, i, 11);
				break;
			}
		}
		
		
		// Date formatter for the string date
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MMMM-dd-yyyy");
		String date = Main.modelTransactionRequestsRecord.getValueAt(row, 10).toString();
		
		LocalDate todayDate = LocalDate.now();
		LocalDate returnDate = LocalDate.parse(date, format); 
		
		int daysLate = Period.between(returnDate, todayDate).getDays();
		
		if (daysLate > 0) {
			newRow[12] = daysLate;
		} else {
			newRow[12] = 0;
		}
		
		// Pass the values to Borrowers Transactions
		Main.modelTransactionHistoryRecord.addRow(newRow);
		
		
		// Delete the borrowers' transaction record in modelBorrowersTransactionsRecord
		String selectedTransactionId = Main.modelTransactionRequestsRecord.getValueAt(row, 0).toString();
		
		for (int i = 0; i < Main.modelBorrowersTransactionsRecord.getRowCount(); i++) {
			String transactionId = Main.modelBorrowersTransactionsRecord.getValueAt(i, 0).toString();
			
			if (selectedTransactionId.equals(transactionId)) {
				Main.modelBorrowersTransactionsRecord.removeRow(i);
				break;
			}
		}
		
		// Delete the row to Transaction Requests and put back the values 
		removeRequest(row);
		
		JOptionPane.showMessageDialog(contentPane, "Selected returning request accepted!");
		
		tblTransactionRequests.clearSelection();
		txtSearchBar.setText(" Search");
		rdoAll.setSelected(true);
		selectedRdo = "All";
		doFilter();
	}
	
	
	// Method for declining borrowing requests only
	private void declineRequest() {
		int response = JOptionPane.showConfirmDialog(contentPane, "Decline request?", "Confirmation", JOptionPane.YES_NO_OPTION);
		
		if (response == JOptionPane.YES_OPTION) {
			// decline request only enables for request type: borrowing	
			// Method to remove the request and set all the values back
			removeRequest(fetchedIndices[selectedRow]);
			
			JOptionPane.showMessageDialog(contentPane, "Selected borrowing request declined!");
			
			tblTransactionRequests.clearSelection();
			txtSearchBar.setText(" Search");
			rdoAll.setSelected(true);
			selectedRdo = "All";
			doFilter();	
		}
	}
	
	
	// Method will be used for declining request and accepting return request by admin and-
	// canceling request by student.
	// This will be used to avoid lengthy code as it will be used for 3 operations
	public static void removeRequest(int row) {
		// Decrement user's borrowed books of student in studentRecord
		int transactionStudentId = Integer.parseInt(Main.modelTransactionRequestsRecord.getValueAt(row, 1).toString());
		
		for (int i = 0; i < Main.modelStudentRecord.getRowCount(); i++) {
			int studentId = Integer.parseInt(Main.modelStudentRecord.getValueAt(i, 0).toString());
			
			if (transactionStudentId == studentId) {
				int borrowedBooks = Integer.parseInt(Main.modelStudentRecord.getValueAt(i, 10).toString()) - 1;
				
				Main.modelStudentRecord.setValueAt(borrowedBooks , i, 10);
				break;
			}
		}

		
		// Increment the book's available stocks and decrement the book's borrowed
		int transactionBookId = Integer.parseInt(Main.modelTransactionRequestsRecord.getValueAt(row, 4).toString());
		
		for (int i = 0; i < Main.modelBookRecord.getRowCount(); i++) {
			int bookId = Integer.parseInt(Main.modelBookRecord.getValueAt(i, 0).toString());
			
			if (transactionBookId == bookId) {
				int available = Integer.parseInt(Main.modelBookRecord.getValueAt(i, 6).toString()) + 1;
				int borrowed = Integer.parseInt(Main.modelBookRecord.getValueAt(i, 8).toString()) - 1;
				
				Main.modelBookRecord.setValueAt(available, i, 6);
				Main.modelBookRecord.setValueAt(borrowed, i, 8);
				break;
			}
		}
		
		
		// Set the specific book stock's status to available
		String transactionStockId = Main.modelTransactionRequestsRecord.getValueAt(row, 5).toString();
		
		for (int i = 0; i < Main.modelBookInventoryRecord.getRowCount(); i++) {
			int bookId = Integer.parseInt(Main.modelBookInventoryRecord.getValueAt(i, 0).toString());
			String stockId = Main.modelBookInventoryRecord.getValueAt(i, 1).toString();
			
			if (transactionBookId == bookId && transactionStockId.equals(stockId)) {
				Main.modelBookInventoryRecord.setValueAt("Available", i, 6);
				break;
			}	
		}
		
		
		// Then, delete the selected transaction to this model: modelTransactionRequests
		Main.modelTransactionRequestsRecord.removeRow(row);
	}
		
	
	// Filter the table and get its indices
	private static void doFilter() {
		btnAccept.setEnabled(false);
		btnDecline.setEnabled(false);
		
		fetchedIndices = Main.filterTable(tblTransactionRequests, Main.modelTransactionRequestsRecord, txtSearchBar.getText(), 
				selectedRdo, -1, -1, new int[] {0, 1, 12});
	}
}
