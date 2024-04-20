import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ListSelectionModel;

public class MyCart extends JFrame {

	private JPanel contentPane;
	private JTable tblMyCart;
	private JLabel lblReturnDate;
	private JButton btnBorrow;
	private JDateChooser dateChooserReturnDate;
	private JButton btnRemove, btnExit;
	private JScrollPane scrollPaneMyCart;
	private JSeparator separator;
	
	public static int[] fetchedIndices; 
	
	
	public MyCart() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 430);
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
					Main.selectedMenuItem = "Books";
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
		btnExit.setBounds(860, 10, 30, 30);
		contentPane.add(btnExit);
		
		JLabel lblFrameTitle = new JLabel("My Cart");
		// Creates an icon object from the local source folder
		ImageIcon lmsframeIcon = new ImageIcon(this.getClass().getResource("LMSFrameIcon.png"));
		// Assigns the icon object as the icon image of components
		lblFrameTitle.setIcon(lmsframeIcon);
		lblFrameTitle.setForeground(new Color(216, 227, 231));
		lblFrameTitle.setIconTextGap(10);
		lblFrameTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFrameTitle.setBounds(10, 10, 250, 30);
		contentPane.add(lblFrameTitle);
		// Creates an icon object from the local source folder
		ImageIcon childframesearchIcon = new ImageIcon(this.getClass().getResource("childframesearchIcon.png"));
		
		scrollPaneMyCart = new JScrollPane();
		scrollPaneMyCart.setBounds(210, 90, 670, 320);
		contentPane.add(scrollPaneMyCart);
		
		tblMyCart = new JTable(Main.modelMyCart) {
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tblMyCart.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblMyCart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPaneMyCart.setViewportView(tblMyCart);

		Main.hideTableColumns(tblMyCart, new int[] {5, 6, 7, 8});
		
		dateChooserReturnDate = new JDateChooser();
		dateChooserReturnDate.getCalendarButton().setToolTipText("Input the date");
		dateChooserReturnDate.getCalendarButton().setPreferredSize(new Dimension(30, 30));
		dateChooserReturnDate.getCalendarButton().setBackground(new Color(18, 110, 130));
		dateChooserReturnDate.getCalendarButton().setForeground(new Color(216, 227, 231));
		dateChooserReturnDate.getCalendarButton().setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		dateChooserReturnDate.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateChooserReturnDate.getCalendarButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				dateChooserReturnDate.getCalendarButton().setBackground(new Color(81, 196, 211));
				dateChooserReturnDate.getCalendarButton().setForeground(new Color(19, 44, 51));
				dateChooserReturnDate.getCalendarButton().setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				dateChooserReturnDate.getCalendarButton().setBackground(new Color(18, 110, 130));
				dateChooserReturnDate.getCalendarButton().setForeground(new Color(216, 227, 231));
				dateChooserReturnDate.getCalendarButton().setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		dateChooserReturnDate.setBounds(20, 145, 170, 30);
		contentPane.add(dateChooserReturnDate);
		
		lblReturnDate = new JLabel("Return Date :");
		lblReturnDate.setForeground(new Color(216, 227, 231));
		lblReturnDate.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblReturnDate.setBounds(20, 100, 170, 30);
		contentPane.add(lblReturnDate);
		
		btnBorrow = new JButton("BORROW");
		btnBorrow.setToolTipText("Click to borrow");
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrowBooks();
			}
		});
		btnBorrow.setFocusable(false);
		btnBorrow.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnBorrow.setBackground(new Color(18, 110, 130));
		btnBorrow.setForeground(new Color(216, 227, 231));
		btnBorrow.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnBorrow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBorrow.setBackground(new Color(81, 196, 211));
				btnBorrow.setForeground(new Color(19, 44, 51));
				btnBorrow.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBorrow.setBackground(new Color(18, 110, 130));
				btnBorrow.setForeground(new Color(216, 227, 231));
				btnBorrow.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		btnBorrow.setBounds(35, 200, 140, 30);
		contentPane.add(btnBorrow);
		
		btnRemove = new JButton("REMOVE");
		btnRemove.setToolTipText("Click to remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tblMyCart.getSelectedRow();

				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(contentPane, "Please select a book to remove in cart!");
				} else {
					
					// First, updates the value of available and borrowed books count of the selected book
					for (int i = 0; i < Main.modelBookRecord.getRowCount(); i++) {
						String id = Main.modelBookRecord.getValueAt(i, 0).toString();
						
						if (id.equals(Main.modelMyCart.getValueAt(selectedRow, 0).toString())) {
							int available = Integer.parseInt(Main.modelBookRecord.getValueAt(i, 6).toString()) + 1;
							int borrowed = Integer.parseInt(Main.modelBookRecord.getValueAt(i, 8).toString()) - 1;		
							
							Main.modelBookRecord.setValueAt(available, i, 6);
							Main.modelBookRecord.setValueAt(borrowed, i, 8);
							break;
						}
					}
					
					// Then, remove it from the modelMyCart
					Main.modelMyCart.removeRow(selectedRow);
					
					// Update the text displayed on menuMyCart
					Main.mnCart.setText("My Cart (" + Main.modelMyCart.getRowCount() + ")");	
				}
			}
		});
		btnRemove.setFocusable(false);
		btnRemove.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnRemove.setBackground(new Color(18, 110, 130));
		btnRemove.setForeground(new Color(216, 227, 231));
		btnRemove.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRemove.setBackground(new Color(81, 196, 211));
				btnRemove.setForeground(new Color(19, 44, 51));
				btnRemove.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRemove.setBackground(new Color(18, 110, 130));
				btnRemove.setForeground(new Color(216, 227, 231));
				btnRemove.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		btnRemove.setBounds(35, 245, 140, 30);
		contentPane.add(btnRemove);
		
		separator = new JSeparator();
		separator.setForeground(new Color(216, 227, 231));
		separator.setBackground(new Color(216, 227, 231));
		separator.setBounds(20, 187, 170, 2);
		contentPane.add(separator);
		
		JLabel lblMyCartBackground = new JLabel("");
		lblMyCartBackground.setLocation(0, 0);
		lblMyCartBackground.setSize(900, 430);
		// Creates an icon object from the local source folder
		ImageIcon mycartBackground = new ImageIcon(this.getClass().getResource("mycartBackground.png"));
		// Assigns the icon object as the icon image of components
		lblMyCartBackground.setIcon(mycartBackground);
		contentPane.add(lblMyCartBackground);
	}
	
	
	// METHODS
	// Performs the operation of borrow books
	private void borrowBooks() {		
		int studentBorrowedBooks = Integer.parseInt(Main.modelStudentRecord.getValueAt(Main.currentAccountIndex, 10).toString());
		
		if (studentBorrowedBooks == 3) {
			JOptionPane.showMessageDialog(contentPane, "You already have reached the maximum books to borrow!");
		} else if (Main.modelMyCart.getRowCount() + studentBorrowedBooks > 3) {
			JOptionPane.showMessageDialog(contentPane, "You already have borrowed " + studentBorrowedBooks + " book(s). Please remove " +
					(Main.modelMyCart.getRowCount() + studentBorrowedBooks - 3) + " book(s) in cart to continue!");
		} else {
			if (Main.modelMyCart.getRowCount() == 0) {
				JOptionPane.showMessageDialog(contentPane, "Please add books to cart before borrowing");
			} else {
				if (dateChooserReturnDate.getDate() == null) {
					JOptionPane.showMessageDialog(contentPane, "Please provide return date to continue!");
				} else {
					// Formats to isolate year, month, and day
					SimpleDateFormat formatYear = new SimpleDateFormat("YYYY");
					SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
					SimpleDateFormat formatDay = new SimpleDateFormat("dd");
					
					// Isolates the year, month, day from the selected date in dateChooser and stores it to integer variables
					int year = Integer.parseInt(formatYear.format(dateChooserReturnDate.getDate()));
					int month = Integer.parseInt(formatMonth.format(dateChooserReturnDate.getDate()));
					int day = Integer.parseInt(formatDay.format(dateChooserReturnDate.getDate()));
					
					LocalDate borrowDate = LocalDate.now();
					LocalDate returnDate = LocalDate.of(year, month, day);
					// identify the number of days with today's date and return date entered by the user 
					int daysBorrowed = Period.between(borrowDate, returnDate).getDays();
					
					if (daysBorrowed <= 0) {
						JOptionPane.showMessageDialog(contentPane, "Please enter valid return date!");
						dateChooserReturnDate.requestFocus();
					} else if (daysBorrowed > 2) {
						JOptionPane.showMessageDialog(contentPane, "Unable to borrow books for " + daysBorrowed + " days!\n" +
									"Note: You can only borrow books for 2 days maximum");
					} else {
						for (int i = 0; i < Main.modelMyCart.getRowCount(); i++) {
							Object[] newRow = new Object[13];
							
							// Get the information of the current borrower
							newRow[0] = "TR-" + Main.transactionRequestsId;
							newRow[1] = Main.currentAccountID;
							newRow[2] = Main.modelStudentRecord.getValueAt(Main.currentAccountIndex, 1);
							newRow[3] = Main.modelStudentRecord.getValueAt(Main.currentAccountIndex, 2);
							
							int selectedBookId = Integer.parseInt(Main.modelMyCart.getValueAt(i, 0).toString());
							
							// Search through inventory and get the book stock that's available
							for (int stockIndex = 0; stockIndex < Main.modelBookInventoryRecord.getRowCount(); stockIndex++) {
								int stockBookId = Integer.parseInt(Main.modelBookInventoryRecord.getValueAt(stockIndex, 0).toString());
								
								if (selectedBookId == stockBookId) {
									boolean isStockAvailable = Main.modelBookInventoryRecord.getValueAt(stockIndex, 6).toString().equals("Available");
									
									if (isStockAvailable) {
										newRow[4] = stockBookId;
										newRow[5] = Main.modelBookInventoryRecord.getValueAt(stockIndex, 1).toString();
										newRow[6] = Main.modelBookInventoryRecord.getValueAt(stockIndex, 2).toString();
										newRow[7] = Main.modelBookInventoryRecord.getValueAt(stockIndex, 3).toString();
										newRow[8] = Main.modelBookInventoryRecord.getValueAt(stockIndex, 4).toString();
										
										// Update the stock's status to borrowed
										Main.modelBookInventoryRecord.setValueAt("Borrowed", stockIndex, 6);
										
										break;
									}
								}
							}
			
							newRow[9] = borrowDate.format(DateTimeFormatter.ofPattern("MMMM-dd-yyyy"));
							newRow[10] = returnDate.format(DateTimeFormatter.ofPattern("MMMM-dd-yyyy"));
							newRow[11] = 0;
							newRow[12] = "Borrowing";
							
							// Increment the transaction id per book borrowing requests
							Main.transactionRequestsId++;
							
							Main.modelTransactionRequestsRecord.addRow(newRow);
						}
						
					// Updates the borrowed books of the current student record
					Main.modelStudentRecord.setValueAt(Main.modelMyCart.getRowCount() + studentBorrowedBooks, Main.currentAccountIndex, 10);
					
					// Remove all the books in my cart - Set all controls back to default
					Main.modelMyCart.setRowCount(0);
					Main.mnCart.setText("My Cart (0)");
					dateChooserReturnDate.setDate(null);

					JOptionPane.showMessageDialog(contentPane, "Your borrowing request has been sent!\n               Status: Pending");					}
				}
			}
		}	
	}
	
	
	// Method to avoid duplication of book title in cart
	public static boolean isBookInCart() {
		for (int i = 0; i < Main.modelMyCart.getRowCount(); i++) {
			String id = Main.modelMyCart.getValueAt(i, 0).toString();
			
			if (id.equals(Main.modelBookRecord.getValueAt(Main.fetchedIndices[Main.selectedRow], 0).toString())) {
				return true;
			}
		}
		
		return false;
	}
	
	// Method to avoid duplication of book title in transaction requests
	public static boolean isBookInRequest() {
		for (int i = 0; i < Main.modelTransactionRequestsRecord.getRowCount(); i++) {
			String studentId = Main.modelTransactionRequestsRecord.getValueAt(i, 1).toString();
			String bookId = Main.modelTransactionRequestsRecord.getValueAt(i, 4).toString();
			
			if (bookId.equals(Main.modelBookRecord.getValueAt(Main.fetchedIndices[Main.selectedRow], 0).toString()) &&
					String.valueOf(Main.currentAccountID).equals(studentId)) {
				return true;
			}
		}
		
		return false;
	}
	
	// Method to avoid duplication of book title in borrowers' transaction
	public static boolean isBookBorrowed() {
		for (int i = 0; i < Main.modelBorrowersTransactionsRecord.getRowCount(); i++) {
			String studentId = Main.modelBorrowersTransactionsRecord.getValueAt(i, 1).toString();
			String bookId = Main.modelBorrowersTransactionsRecord.getValueAt(i, 4).toString();
			
			if (studentId.equals(String.valueOf(Main.currentAccountID)) && 
				bookId.equals(Main.modelBookRecord.getValueAt(Main.fetchedIndices[Main.selectedRow], 0).toString())) {
				return true;
			}
		}
		
		return false;
	}
}
