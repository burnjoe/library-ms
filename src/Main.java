import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.ComponentOrientation;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Cursor;
import java.awt.CardLayout;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.RowFilter.ComparisonType;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JList;
import java.awt.event.MouseWheelListener;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JCheckBox;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.Icon;

public class Main extends JFrame {
	
	// Frames
	public static Main frame;
	private Student frameStudent;
	private BookCategories frameBookCategories;
	private BookInformation frameBookInformation;
	private BookInventory frameBookInventory;
	public static TransactionRequests frameTransactionRequests;
	private MyCart frameMyCart;
	private MyProfile frameMyProfile;
	
	// Components
	private JPanel contentPane;
	private JPanel panelFilter, panelStudentRecord, panelBookInformation, panelBookInventory, panelBooks;
	private JMenuItem mntmPendingRequests, mntmTransactionHistory, mntmBooksToReturn;
	private JMenuItem mntmBooksAvailableDamaged, mntmBorrowedTransaction, mntmTransactions, mntmPenaltiesCollected, mntmDeletedData;
	private JMenuItem mntmEditProfile, mntmLogout, mntmExit;
	private JMenuItem mntmStudents, mntmTransactionRequest;
	private JMenuItem mntmInventory, mntmInformation;
	public static JMenu mnView, mnBooks, mnViewReports, mnAllBooks, mnCart, mnManage, mnAccount;
	public static JTextField txtSearchBar;
	private JScrollPane scrollPaneBookInformation;
	private static JCheckBox chkBookToggle;
	private static JButton btnAdd, btnEdit, btnDelete, btnManageStocks, btnAddToCart;
	public static JList<String> listCategories;
	public static DefaultListModel<String> listModelCategories = new DefaultListModel<String>();
	
	private JPanel panelView;
	private static JTable tblView; 
	private JRadioButton rdoOption1, rdoOption2, rdoOption3, rdoOption4;
	private JButton btnReturnBook, btnCancelRequest;
	private JLabel lblFilter;
	
	// STUDENTS model
	private JLayeredPane layeredPane;
	public static JTable tblStudentRecord;
	public static DefaultTableModel modelAdmin = new DefaultTableModel();
	public static DefaultTableModel modelStudentRecord = new DefaultTableModel();
	
	// BOOK INFORMATION model
	public static JTable tblBookInformation, tblBookCategory;
	public static DefaultTableModel modelBookCategoryRecord = new DefaultTableModel();
	public static DefaultTableModel modelBookRecord = new DefaultTableModel();
	
	// BOOK INVENTORY model
	public static JTable tblBookInventory;
	public static DefaultTableModel modelBookInventoryRecord = new DefaultTableModel();
	
	// TRANSACTION REQUESTS model
	public static DefaultTableModel modelTransactionRequestsRecord = new DefaultTableModel();
	
	// BORROWER'S TRANSACTIONS model
	public static DefaultTableModel modelBorrowersTransactionsRecord = new DefaultTableModel();
	
	// ALL BOOKS (Students)
	public static JTable tblBooksRecord;
	
	// ADD TO CART model
	public static DefaultTableModel modelMyCart = new DefaultTableModel();
	
	// TRANSACTION HISTORY model
	public static DefaultTableModel modelTransactionHistoryRecord = new DefaultTableModel();
	
	// DELETED DATA model
	public static DefaultTableModel modelDeletedStudents = new DefaultTableModel();
	public static DefaultTableModel modelDeletedCategory = new DefaultTableModel();
	public static DefaultTableModel modelDeletedBooks = new DefaultTableModel();
	public static DefaultTableModel modelDeletedStocks = new DefaultTableModel();
	
	// Arrays: Dummy data
	private static Object[][] dummyAdmin = {
			{"admin", "admin", "Admin"}
	};
	
	private static Object[][] dummyStudents = {
			{1, "Sabana", "Joe Lawrence", "Morena", "Mabuhay, Mamatid, Cabuyao, Laguna", "09214150005", "sabanajholo@gmail.com", "joe25", "burnj", "User", 2, 0},
			{2, "Derla", "Julius", "Alcances", "Mabuhay, Mamatid, Cabuyao, Laguna", "09184494126", "derlajulius10@gmail.com", "juls", "jules10", "User", 1, 0}, 
			{3, "De Jesus", "Michael Ivan", "Ambay", "Mamatid, Cabuyao, Laguna", "09480490227", "dejesusmichaelivan@gmail.com", "ivan04", "0404", "User", 2, 0},
			{4, "Ferreras", "Vince Austin", "Romero", "Mamatid, Cabuyao, Laguna", "09162973613", "bns.stn@gmail.com", "bnstn", "bns9", "User", 0, 0},
			{5, "Balajadia", "Nicolle", "Dolon", "Mabuhay, Mamatid, Cabuyao, Laguna", "09215135616", "balajadianicole28@gmail.com", "nics", "nics28", "User", 0, 0}
			};
	
	private static Object[][] dummyCategories = {
			{1, "Art", 1},
			{2, "Biography", 0},
			{3, "Children's Literature", 3},
			{4, "Science fiction", 4},
			{5, "Horror fiction", 1},
			{6, "Fantasy fiction", 3},
			{7, "Mystery", 1},
			{8, "Novel", 4},
			{9, "Fable", 1}
	};
	
	private static Object[][] dummyBooks = {
		{1, "Brave new world", "Aldous Huxley", "1", "Science fiction", 4, 1, 1, 2},
		{2, "The Invisible Man", "H. G. Wells", "1", "Science fiction, Horror fiction", 3, 2, 1, 0},
		{3, "Alice in Wonderland", "Lewis Carroll", "1", "Fantasy fiction, Children's Literature, Mystery", 6, 3, 3, 0},
		{4, "Macbeth", "William Shakespeare", "1", "Art", 4, 3, 1, 0},
		{5, "Harry Potter and the Philosopher's Stone", "J.K. Rowling", "1", "Novel, Fantasy fiction", 3, 2, 1, 0},
		{6, "The Little Prince", "Antoine de Saint-Exupéry", "1", "Fable, Novel, Children's Literature, Fantasy fiction", 6, 6, 0, 0},
		{7, "I, Robot", "Isaac Asimov", "1", "Science fiction", 2, 2, 0, 0},
		{8, "The Cat in the Hat Comes Back", "Dr. Seuss", "1", "Children's Literature", 4, 4, 0, 0},
		{9, "A Little Life", "Hanya Yanagihara", "1", "Novel", 5, 4, 0, 1},
		{10, "The People in the Trees", "Hanya Yanagihara", "1", "Novel, Science fiction", 1, 0, 0, 1},
		{11, "Steve Jobs", "Walter Isaacson", "1", "Biography", 3, 3, 0, 0}
	};
	
	private static Object[][] dummyStocks = {
		{1, "STK-1", "Brave new world", "Aldous Huxley", "1", "Science fiction", "Borrowed"},
		{1, "STK-2", "Brave new world", "Aldous Huxley", "1", "Science fiction", "Damaged"},
		{1, "STK-3", "Brave new world", "Aldous Huxley", "1", "Science fiction", "Borrowed"},
		{1, "STK-4", "Brave new world", "Aldous Huxley", "1", "Science fiction", "Available"},
		{2, "STK-1", "The Invisible Man", "H. G. Wells", "1", "Science fiction, Horror fiction", "Available"},
		{2, "STK-2", "The Invisible Man", "H. G. Wells", "1", "Science fiction, Horror fiction", "Damaged"},
		{2, "STK-3", "The Invisible Man", "H. G. Wells", "1", "Science fiction, Horror fiction", "Available"},
		{3, "STK-1", "Alice in Wonderland", "Lewis Carroll", "1", "Fantasy fiction, Children's Literature, Mystery", "Borrowed"},
		{3, "STK-2", "Alice in Wonderland", "Lewis Carroll", "1", "Fantasy fiction, Children's Literature, Mystery", "Damaged"},
		{3, "STK-3", "Alice in Wonderland", "Lewis Carroll", "1", "Fantasy fiction, Children's Literature, Mystery", "Damaged"},
		{3, "STK-4", "Alice in Wonderland", "Lewis Carroll", "1", "Fantasy fiction, Children's Literature, Mystery", "Available"},
		{3, "STK-5", "Alice in Wonderland", "Lewis Carroll", "1", "Fantasy fiction, Children's Literature, Mystery", "Damaged"},
		{3, "STK-6", "Alice in Wonderland", "Lewis Carroll", "1", "Fantasy fiction, Children's Literature, Mystery", "Available"},
		{4, "STK-1", "Macbeth", "William Shakespeare", "1", "Art", "Damaged"},
		{4, "STK-2", "Macbeth", "William Shakespeare", "1", "Art", "Available"},
		{4, "STK-3", "Macbeth", "William Shakespeare", "1", "Art", "Available"},
		{4, "STK-4", "Macbeth", "William Shakespeare", "1", "Art", "Available"},
		{5, "STK-1", "Harry Potter and the Philosopher's Stone", "J.K. Rowling", "1", "Novel, Fantasy fiction", "Available"},
		{5, "STK-2", "Harry Potter and the Philosopher's Stone", "J.K. Rowling", "1", "Novel, Fantasy fiction", "Available"},
		{5, "STK-3", "Harry Potter and the Philosopher's Stone", "J.K. Rowling", "1", "Novel, Fantasy fiction", "Damaged"},
		{6, "STK-1", "The Little Prince", "Antoine de Saint-Exupéry", "1", "Fable, Novel, Children's Literature, Fantasy fiction", "Available"},
		{6, "STK-2", "The Little Prince", "Antoine de Saint-Exupéry", "1", "Fable, Novel, Children's Literature, Fantasy fiction", "Available"},
		{6, "STK-3", "The Little Prince", "Antoine de Saint-Exupéry", "1", "Fable, Novel, Children's Literature, Fantasy fiction", "Available"},
		{6, "STK-4", "The Little Prince", "Antoine de Saint-Exupéry", "1", "Fable, Novel, Children's Literature, Fantasy fiction", "Available"},
		{6, "STK-5", "The Little Prince", "Antoine de Saint-Exupéry", "1", "Fable, Novel, Children's Literature, Fantasy fiction", "Available"},
		{6, "STK-6", "The Little Prince", "Antoine de Saint-Exupéry", "1", "Fable, Novel, Children's Literature, Fantasy fiction", "Available"},
		{7, "STK-1", "I, Robot", "Isaac Asimov", "1", "Science fiction", "Available"},
		{7, "STK-2", "I, Robot", "Isaac Asimov", "1", "Science fiction", "Available"},
		{8, "STK-1", "The Cat in the Hat Comes Back", "Dr. Seuss", "1", "Children's Literature", "Available"},
		{8, "STK-2", "The Cat in the Hat Comes Back", "Dr. Seuss", "1", "Children's Literature", "Available"},
		{8, "STK-3", "The Cat in the Hat Comes Back", "Dr. Seuss", "1", "Children's Literature", "Available"},
		{8, "STK-4", "The Cat in the Hat Comes Back", "Dr. Seuss", "1", "Children's Literature", "Available"},
		{9, "STK-1", "A Little Life", "Hanya Yanagihara", "1", "Novel", "Borrowed"},
		{9, "STK-2", "A Little Life", "Hanya Yanagihara", "1", "Novel", "Available"},
		{9, "STK-3", "A Little Life", "Hanya Yanagihara", "1", "Novel", "Available"},
		{9, "STK-4", "A Little Life", "Hanya Yanagihara", "1", "Novel", "Available"},
		{9, "STK-5", "A Little Life", "Hanya Yanagihara", "1", "Novel", "Available"},
		{10, "STK-1", "The People in the Trees", "Hanya Yanagihara", "1", "Novel, Science fiction", "Borrowed"},
		{11, "STK-1", "Steve Jobs", "Walter Isaacson", "1", "Biography", "Available"},
		{11, "STK-2", "Steve Jobs", "Walter Isaacson", "1", "Biography", "Available"},
		{11, "STK-3", "Steve Jobs", "Walter Isaacson", "1", "Biography", "Available"}
	};
	
	private static Object[][] dummyTransactionRequests = {
			{"TR-1", 2, "Derla", "Julius", 1, "STK-3", "Brave new world", "Aldous Huxley", "1", "June-17-2021", "June-20-2021", 0, "Borrowing"},
			{"TR-2", 1, "Sabana", "Joe Lawrence", 1, "STK-1", "Brave new world", "Aldous Huxley", "1", "June-15-2021", "June-17-2021", 50, "Returning"},
			{"TR-5", 1, "Sabana", "Joe Lawrence", 3, "STK-1", "Alice in Wonderland", "Lewis Carroll", "1", "June-22-2021", "June-24-2021", 0, "Borrowing"},
	};
	
	private static Object[][] dummyBorrowersTransactions = {
			{"TR-2", 1, "Sabana", "Joe Lawrence", 1, "STK-1", "Brave new world", "Aldous Huxley", "1", "June-15-2021", "June-17-2021", 50},
			{"TR-3", 3, "De Jesus", "Michael Ivan", 9, "STK-1", "A Little Life", "Hanya Yanagihara", "1", "June-15-2021", "June-17-2021", 50},
			{"TR-4", 3, "De Jesus", "Michael Ivan", 10, "STK-1", "The People in the Trees", "Hanya Yanagihara", "1", "June-22-2021", "June-24-2021", 0}
	};
	
	private static Object[][] dummyTransactionHistory = {
			{"TR-2", 1, "Sabana", "Joe Lawrence", 1, "STK-1", "Brave new world", "Aldous Huxley", "1", "June-18-2021", "Borrowed", 0, 0},
			{"TR-3", 3, "De Jesus", "Michael Ivan", 9, "STK-1", "A Little Life", "Hanya Yanagihara", "1", "June-15-2021", "Borrowed", 0, 0},
			{"TR-4", 3, "De Jesus", "Michael Ivan", 10, "STK-1", "The People in the Trees", "Hanya Yanagihara", "1", "June-22-2021", "Borrowed", 0, 0}
	};
	
	// Arrays: Table Columns
	private static String[] columnAdmin = {"Username", "Password", "User Type"};
	private static String[] columnStudentRecord = {"Student ID", "Last Name", "First Name", "Middle Name", "Address", "Contact Number", "Email Address", "Username", 
			"Password", "User Type", "Borrowed Books", "Penalties Collected"};
	private static String[] columnBookCategoryRecord = {"Category ID", "Category Name", "Number of Books"};
	private static String[] columnBookRecord = {"Book ID", "Book Title", "Book Author", "Edition", "Categories", "Stocks", "Available", "Damaged", "Borrowed"};
	private static String[] columnBookInventoryRecord = {"Book ID", "Stock ID", "Book Title", "Book Author", "Edition", "Categories", "Status"};
	private static String[] columnTransactionRequestsRecord = {"Transaction ID", "Student ID", "Last Name", "First Name", "Book ID", "Stock ID", "Book Title", 
			"Book Author", "Book Edition", "Borrow Date", "Return Date", "Late Fee", "Request Type"};
	private static String[] columnBorrowersTransactionsRecord = {"Transaction ID", "Student ID", "Last Name", "First Name", "Book ID", "Stock ID", "Book Title", 
			"Book Author", "Book Edition", "Borrow Date", "Return Date", "Late Fee (To Collect)"};
	private static String[] columnTransactionHistoryRecord = {"Transaction ID", "Student ID", "Last Name", "First Name", "Book ID", "Stock ID", "Book Title", 
			"Book Author", "Book Edition", "Date Borrowed/Returned", "Borrowed/Returned", "Late Fee (Collected)", "Days (Late)"};
			
		
	// Variables
	public static boolean isAdd = false, isEdit = false, isDelete = false;
	public static String selectedMenuItem = "", selectedRdo = "All";
	public static int adminId = 1, studentId = 1, bookCategoryId = 1, bookId = 1, bookStockId = 1, transactionRequestsId = 1;
	public static int selectedRow = -1;
	
	// Variable for current user
	public static int currentAccountID = 0, currentAccountIndex = 0; 
	public static String currentUserType = "";
	
	public static int[] fetchedIndices;
	private JSeparator separator_3;
	private JPanel panel;
		
	
	// Main method
	public static void main(String[] args) {
		// Setting models' column identifiers
		modelAdmin.setColumnIdentifiers(columnAdmin);
		modelStudentRecord.setColumnIdentifiers(columnStudentRecord); // Students
		modelBookCategoryRecord.setColumnIdentifiers(columnBookCategoryRecord); // Book Categories
		modelBookRecord.setColumnIdentifiers(columnBookRecord); // Books
		modelBookInventoryRecord.setColumnIdentifiers(columnBookInventoryRecord); // Book Inventory
		modelTransactionRequestsRecord.setColumnIdentifiers(columnTransactionRequestsRecord); // Transaction Requests
		modelBorrowersTransactionsRecord.setColumnIdentifiers(columnBorrowersTransactionsRecord); // Borrowers' Transactions
		modelTransactionHistoryRecord.setColumnIdentifiers(columnTransactionHistoryRecord); // Transaction History (Admin & Student)
		modelMyCart.setColumnIdentifiers(columnBookRecord);
		
		modelDeletedStudents.setColumnIdentifiers(columnStudentRecord);  // Deleted students
		modelDeletedCategory.setColumnIdentifiers(columnBookCategoryRecord);  // Deleted book categories
		modelDeletedBooks.setColumnIdentifiers(columnBookRecord);  // Deleted books
		modelDeletedStocks.setColumnIdentifiers(columnBookInventoryRecord);  // Deleted books' stocks
		
		// Adding dummy data
		adminId = addDummy(dummyAdmin, modelAdmin, adminId);
		studentId = addDummy(dummyStudents, modelStudentRecord, studentId); // Students
		bookCategoryId = addDummy(dummyCategories, modelBookCategoryRecord, bookCategoryId); // Book Categories
		bookId = addDummy(dummyBooks, modelBookRecord, bookId); // Books
		bookStockId = addDummy(dummyStocks, modelBookInventoryRecord, bookStockId); // Book Inventory
		transactionRequestsId = addDummy(dummyTransactionRequests, modelTransactionRequestsRecord, transactionRequestsId); // Transaction Requests
		
		// Add the dummy data to tblView (Borrowed Transactions)
		int borrowersTransactions = 1;
		borrowersTransactions = addDummy(dummyBorrowersTransactions, modelBorrowersTransactionsRecord, borrowersTransactions);
		
		// Add the dummy data to tblView (Transaction History)
		int transactionHistory = 1;
		transactionHistory = addDummy(dummyTransactionHistory, modelTransactionHistoryRecord, transactionHistory);

		// Loads the Login frame
		Login frameLogin = new Login();
		frameLogin.setVisible(true);
	}

	
	
	/**
	 * Create the frame.
	 */
	public Main() {
		setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("LMSIcon.png")));
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1365, 745);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(81, 196, 211)));
		panel.setBackground(new Color(19, 44, 51));
		panel.setBounds(0, 0, 1366, 50);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Creates an icon object from the local source folder
		ImageIcon exitIcon = new ImageIcon(this.getClass().getResource("exitIcon.png"));
		ImageIcon exitPressedIcon = new ImageIcon(this.getClass().getResource("exitPressedIcon.png"));
		ImageIcon exitRolloverIcon = new ImageIcon(this.getClass().getResource("exitRolloverIcon.png"));
		// Assigns the icon object as the icon image of components
		JButton btnExit = new JButton(exitIcon);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(contentPane, "Are you sure you want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
				
				if (response == JOptionPane.YES_OPTION) {
					System.exit(EXIT_ON_CLOSE);
				} else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		btnExit.setToolTipText("Exit");
		btnExit.setPressedIcon(exitPressedIcon);
		btnExit.setRolloverIcon(exitRolloverIcon);
		btnExit.setRequestFocusEnabled(false);
		btnExit.setForeground(new Color(216, 227, 231));
		btnExit.setFocusable(false);
		btnExit.setBorder(null);
		btnExit.setBackground(new Color(19, 44, 51));
		btnExit.setBounds(1326, 11, 30, 30);
		panel.add(btnExit);
		
		// Creates an icon object from the local source folder
		ImageIcon restoredownIcon = new ImageIcon(this.getClass().getResource("restoredownIcon.png"));
		ImageIcon restoredownPressedIcon = new ImageIcon(this.getClass().getResource("restoredownPressedIcon.png"));
		ImageIcon restoredownRolloverIcon = new ImageIcon(this.getClass().getResource("restoredownRolloverIcon.png"));
		// Assigns the icon object as the icon image of components
		JButton btnRestoreDown = new JButton(restoredownIcon);
		btnRestoreDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (frame.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
	                frame.setSize(1365, 745);
	            } else {
	                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	            }
			}
		});
		btnRestoreDown.setToolTipText("Restore Down");
		btnRestoreDown.setPressedIcon(restoredownPressedIcon);
		btnRestoreDown.setRolloverIcon(restoredownRolloverIcon);
		btnRestoreDown.setRequestFocusEnabled(false);
		btnRestoreDown.setForeground(new Color(216, 227, 231));
		btnRestoreDown.setFocusable(false);
		btnRestoreDown.setBorder(null);
		btnRestoreDown.setBackground(new Color(19, 44, 51));
		btnRestoreDown.setBounds(1286, 11, 30, 30);
		panel.add(btnRestoreDown);
		
		// Creates an icon object from the local source folder
		ImageIcon minimizeIcon = new ImageIcon(this.getClass().getResource("minimizeIcon.png"));
		ImageIcon minimizePressedIcon = new ImageIcon(this.getClass().getResource("minimizePressedIcon.png"));
		ImageIcon minimizeRolloverIcon = new ImageIcon(this.getClass().getResource("minimizeRolloverIcon.png"));
		// Assigns the icon object as the icon image of components
		JButton btnMinimize = new JButton(minimizeIcon);
		btnMinimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		btnMinimize.setToolTipText("Exit");
		btnMinimize.setPressedIcon(minimizePressedIcon);
		btnMinimize.setRolloverIcon(minimizeRolloverIcon);
		btnMinimize.setRequestFocusEnabled(false);
		btnMinimize.setForeground(new Color(216, 227, 231));
		btnMinimize.setFocusable(false);
		btnMinimize.setBorder(null);
		btnMinimize.setBackground(new Color(19, 44, 51));
		btnMinimize.setBounds(1246, 11, 30, 30);
		panel.add(btnMinimize);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(81, 196, 211)));
		menuBar.setBackground(new Color(18, 110, 130));
		menuBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuBar.setBounds(0, 50, 1366, 100);
		contentPane.add(menuBar);
		
		JMenu mnAccount = new JMenu("Account");
		mnAccount.setIconTextGap(20);
		mnAccount.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mnAccount.setBorder(null);
		mnAccount.setForeground(new Color(216, 227, 231));
		mnAccount.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnAccount);
		
		mntmEditProfile = new JMenuItem("Edit Profile");
		mntmEditProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedMenuItem = "Edit Profile";
				
				frameMyProfile = new MyProfile();
				frameMyProfile.setVisible(true);
				
				Main.frame.setEnabled(false);
			}
		});
		mntmEditProfile.setBorder(null);
		mntmEditProfile.setForeground(new Color(216, 227, 231));
		mntmEditProfile.setBackground(new Color(18, 110, 130));
		mntmEditProfile.setIconTextGap(10);
		mntmEditProfile.setPreferredSize(new Dimension(170, 40));
		mntmEditProfile.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mntmEditProfile.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnAccount.add(mntmEditProfile);
		
		mntmLogout = new JMenuItem("Log Out");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(contentPane, "Are you sure to log out?", "Confirmation", JOptionPane.YES_NO_OPTION);
				
				if (response == JOptionPane.YES_OPTION) {
					currentAccountID = 0;
					currentUserType = null;
					selectedRow = -1;
					
					// Removes the books inside myCart
					for (int selectedRow = modelMyCart.getRowCount() - 1; selectedRow > -1; selectedRow--) {
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
					}
					
					// Update the text displayed on menuMyCart
					Main.mnCart.setText("My Cart (" + Main.modelMyCart.getRowCount() + ")");
		
					dispose();
					
					Login frameLogin = new Login();
					frameLogin.setVisible(true);		
				}		
			}
		});
		mntmLogout.setBorder(null);
		mntmLogout.setForeground(new Color(216, 227, 231));
		mntmLogout.setBackground(new Color(18, 110, 130));
		mntmLogout.setIconTextGap(10);
		mntmLogout.setPreferredSize(new Dimension(170, 40));
		mntmLogout.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mntmLogout.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnAccount.add(mntmLogout);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(81, 196, 211));
		mnAccount.add(separator);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(contentPane, "Are you sure to exit application?", "Confirmation", JOptionPane.YES_NO_OPTION);
				
				if (response == JOptionPane.YES_OPTION) {
					System.exit(JFrame.EXIT_ON_CLOSE);
				}
			}
		});
		mntmExit.setBorder(null);
		mntmExit.setForeground(new Color(216, 227, 231));
		mntmExit.setBackground(new Color(18, 110, 130));
		mntmExit.setIconTextGap(10);
		mntmExit.setPreferredSize(new Dimension(170, 40));
		mntmExit.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnAccount.add(mntmExit);
		
		mnManage = new JMenu("Manage");
		mnManage.setIconTextGap(20);
		mnManage.setBorder(null);
		mnManage.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mnManage.setForeground(new Color(216, 227, 231));
		mnManage.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnManage);
		
		mntmStudents = new JMenuItem("Students");
		mntmStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchBar.setText(" Search");
				listCategories.setSelectedIndex(0);
				selectedMenuItem = "Students";
				crudButtonHandling();
				doFilter();
				
				layeredPane.removeAll();
				layeredPane.add(panelStudentRecord);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		mntmStudents.setBorder(null);
		mntmStudents.setForeground(new Color(216, 227, 231));
		mntmStudents.setBackground(new Color(18, 110, 130));
		mntmStudents.setIconTextGap(10);
		mntmStudents.setPreferredSize(new Dimension(185, 40));
		mntmStudents.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mntmStudents.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnManage.add(mntmStudents);
		
		mnBooks = new JMenu("Books");
		mnBooks.setBorder(null);
		mnBooks.setOpaque(true);
		mnBooks.setForeground(new Color(216, 227, 231));
		mnBooks.setBackground(new Color(18, 110, 130));
		mnBooks.setIconTextGap(10);
		mnBooks.setPreferredSize(new Dimension(185, 40));
		mnBooks.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mnBooks.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnManage.add(mnBooks);
		
		mntmInformation = new JMenuItem("Information");
		mntmInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchBar.setText(" Search");
				listCategories.setSelectedIndex(0);
				selectedMenuItem = "Information";
				crudButtonHandling();
				doFilter();
				
				layeredPane.removeAll();
				layeredPane.add(panelBookInformation);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		mntmInformation.setBorder(null);
		mntmInformation.setBackground(new Color(18, 110, 130));
		mntmInformation.setForeground(new Color(216, 227, 231));
		mntmInformation.setIconTextGap(10);
		mntmInformation.setPreferredSize(new Dimension(140, 40));
		mntmInformation.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnBooks.add(mntmInformation);
		
		mntmInventory = new JMenuItem("Inventory");
		mntmInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchBar.setText(" Search");
				listCategories.setSelectedIndex(0);
				selectedMenuItem = "Inventory";
				crudButtonHandling();
				doFilter();
				
				layeredPane.removeAll();
				layeredPane.add(panelBookInventory);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		mntmInventory.setBorder(null);
		mntmInventory.setBackground(new Color(18, 110, 130));
		mntmInventory.setForeground(new Color(216, 227, 231));
		mntmInventory.setIconTextGap(10);
		mntmInventory.setPreferredSize(new Dimension(140, 40));
		mntmInventory.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnBooks.add(mntmInventory);
		
		mntmTransactionRequest = new JMenuItem("Transaction Requests");
		mntmTransactionRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchBar.setText(" Search");
				listCategories.setSelectedIndex(0);
				
				frameTransactionRequests = new TransactionRequests();
				frameTransactionRequests.setVisible(true);
				
				Main.frame.setEnabled(false);
			}
		});
		mntmTransactionRequest.setBorder(null);
		mntmTransactionRequest.setForeground(new Color(216, 227, 231));
		mntmTransactionRequest.setBackground(new Color(18, 110, 130));
		mntmTransactionRequest.setIconTextGap(10);
		mntmTransactionRequest.setPreferredSize(new Dimension(185, 40));
		mntmTransactionRequest.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mntmTransactionRequest.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnManage.add(mntmTransactionRequest);
		
		mnCart = new JMenu("My Cart (0)");
		mnCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mnCart.setSelected(false);
				
				frameMyCart = new MyCart();
				frameMyCart.setVisible(true);
				
				Main.frame.setEnabled(false);
			}
		});
		mnCart.setIconTextGap(20);
		mnCart.setBorder(null);
		mnCart.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mnCart.setForeground(new Color(216, 227, 231));
		mnCart.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnCart);
		
		mnAllBooks = new JMenu("Books");
		mnAllBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSearchBar.setText(" Search");
				listCategories.setSelectedIndex(0);
				selectedMenuItem = "Books";
				crudButtonHandling();
				doFilter();
				
				mnAllBooks.setSelected(false);
				layeredPane.removeAll();
				layeredPane.add(panelBooks);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		mnAllBooks.setIconTextGap(20);
		mnAllBooks.setBorder(null);
		mnAllBooks.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mnAllBooks.setForeground(new Color(216, 227, 231));
		mnAllBooks.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnAllBooks);
		
		mnViewReports = new JMenu("View Reports");
		mnViewReports.setIconTextGap(20);
		mnViewReports.setBorder(null);
		mnViewReports.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mnViewReports.setForeground(new Color(216, 227, 231));
		mnViewReports.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnViewReports);
		
		mnView = new JMenu("View");
		mnView.setIconTextGap(20);
		mnView.setBorder(null);
		mnView.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mnView.setForeground(new Color(216, 227, 231));
		mnView.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnView);
		
		mntmBooksAvailableDamaged = new JMenuItem("Books Available/Damaged");
		mntmBooksAvailableDamaged.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchBar.setText(" Search");
				listCategories.setSelectedIndex(0);
				selectedMenuItem = "Books Available/Damaged";
				rdoOption1.setText("All");
				rdoOption2.setText("Available");
				rdoOption3.setText("Damaged");
				rdoOption4.setText("Borrowed");
				rdoOption4.setVisible(true);
				crudButtonHandling();
	
				tblView.setModel(modelBookInventoryRecord);
				doFilter();
				
				layeredPane.removeAll();
				layeredPane.add(panelView);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		mntmBooksAvailableDamaged.setBorder(null);
		mntmBooksAvailableDamaged.setBackground(new Color(18, 110, 130));
		mntmBooksAvailableDamaged.setForeground(new Color(216, 227, 231));
		mntmBooksAvailableDamaged.setIconTextGap(10);
		mntmBooksAvailableDamaged.setPreferredSize(new Dimension(230, 40));
		mntmBooksAvailableDamaged.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mntmBooksAvailableDamaged.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnViewReports.add(mntmBooksAvailableDamaged);
		
		mntmBorrowedTransaction = new JMenuItem("Borrowed Transactions");
		mntmBorrowedTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchBar.setText(" Search");
				listCategories.setSelectedIndex(0);
				selectedMenuItem = "Borrowed Transactions";
				crudButtonHandling();
				
				tblView.setModel(modelBorrowersTransactionsRecord);
				doFilter();
				
				calculateLateFee();
				
				layeredPane.removeAll();
				layeredPane.add(panelView);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		mntmBorrowedTransaction.setBorder(null);
		mntmBorrowedTransaction.setForeground(new Color(216, 227, 231));
		mntmBorrowedTransaction.setBackground(new Color(18, 110, 130));
		mntmBorrowedTransaction.setIconTextGap(10);
		mntmBorrowedTransaction.setPreferredSize(new Dimension(230, 40));
		mntmBorrowedTransaction.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mntmBorrowedTransaction.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnViewReports.add(mntmBorrowedTransaction);
		
		mntmTransactions = new JMenuItem("Transactions");
		mntmTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchBar.setText(" Search");
				listCategories.setSelectedIndex(0);
				selectedMenuItem = "Transactions";
				rdoOption1.setText("All");
				rdoOption2.setText("Borrowed");
				rdoOption3.setText("Returned");
				rdoOption4.setVisible(false);
				crudButtonHandling();
				
				tblView.setModel(modelTransactionHistoryRecord);
				doFilter();
				
				layeredPane.removeAll();
				layeredPane.add(panelView);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		mntmTransactions.setBorder(null);
		mntmTransactions.setForeground(new Color(216, 227, 231));
		mntmTransactions.setBackground(new Color(18, 110, 130));
		mntmTransactions.setIconTextGap(10);
		mntmTransactions.setPreferredSize(new Dimension(230, 40));
		mntmTransactions.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mntmTransactions.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnViewReports.add(mntmTransactions);
		
		mntmPenaltiesCollected = new JMenuItem("Penalties Collected");
		mntmPenaltiesCollected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchBar.setText(" Search");
				listCategories.setSelectedIndex(0);
				selectedMenuItem = "Penalties Collected";
				crudButtonHandling();
				
				tblView.setModel(modelStudentRecord);
				hideTableColumns(tblView, new int[] {4, 5, 6, 8, 9, 10});
				doFilter();
				
				layeredPane.removeAll();
				layeredPane.add(panelView);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		mntmPenaltiesCollected.setBorder(null);
		mntmPenaltiesCollected.setForeground(new Color(216, 227, 231));
		mntmPenaltiesCollected.setBackground(new Color(18, 110, 130));
		mntmPenaltiesCollected.setIconTextGap(10);
		mntmPenaltiesCollected.setPreferredSize(new Dimension(230, 40));
		mntmPenaltiesCollected.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mntmPenaltiesCollected.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnViewReports.add(mntmPenaltiesCollected);
		
		mntmDeletedData = new JMenuItem("Deleted Data");
		mntmDeletedData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchBar.setText(" Search");
				listCategories.setSelectedIndex(0);
				selectedMenuItem = "Deleted Data";
				rdoOption1.setText("Students");
				rdoOption2.setText("Categories");
				rdoOption3.setText("Books");
				rdoOption4.setText("Stocks");
				rdoOption4.setVisible(true);
				crudButtonHandling();
				
				tblView.setModel(modelDeletedStudents);
				hideTableColumns(tblView, new int[] {8, 9, 10});
				doFilter();
				
				layeredPane.removeAll();
				layeredPane.add(panelView);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		
		separator_3 = new JSeparator();
		separator_3.setBackground(new Color(81, 196, 211));
		mnViewReports.add(separator_3);
		mntmDeletedData.setBorder(null);
		mntmDeletedData.setForeground(new Color(216, 227, 231));
		mntmDeletedData.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mntmDeletedData.setIconTextGap(10);
		mntmDeletedData.setPreferredSize(new Dimension(230, 40));
		mntmDeletedData.setBackground(new Color(18, 110, 130));
		mntmDeletedData.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnViewReports.add(mntmDeletedData);
		
		mntmPendingRequests = new JMenuItem("Pending Requests");
		mntmPendingRequests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchBar.setText(" Search");
				listCategories.setSelectedIndex(0);
				selectedMenuItem = "Pending Requests";
				crudButtonHandling();
				
				tblView.setModel(modelTransactionRequestsRecord);
				hideTableColumns(tblView, new int[] {1, 2, 3});
				doFilter();
								
				layeredPane.removeAll();
				layeredPane.add(panelView);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		mntmPendingRequests.setBorder(null);
		mntmPendingRequests.setForeground(new Color(216, 227, 231));
		mntmPendingRequests.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mntmPendingRequests.setIconTextGap(10);
		mntmPendingRequests.setPreferredSize(new Dimension(230, 40));
		mntmPendingRequests.setBackground(new Color(18, 110, 130));
		mntmPendingRequests.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnView.add(mntmPendingRequests);
		
		mntmTransactionHistory = new JMenuItem("Transaction History");
		mntmTransactionHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchBar.setText(" Search");
				listCategories.setSelectedIndex(0);
				selectedMenuItem = "Transaction History";
				crudButtonHandling();
				
				tblView.setModel(modelTransactionHistoryRecord);
				hideTableColumns(tblView, new int[] {1, 2, 3});
				doFilter();
				
				layeredPane.removeAll();
				layeredPane.add(panelView);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		mntmTransactionHistory.setBorder(null);
		mntmTransactionHistory.setForeground(new Color(216, 227, 231));
		mntmTransactionHistory.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mntmTransactionHistory.setIconTextGap(10);
		mntmTransactionHistory.setPreferredSize(new Dimension(230, 40));
		mntmTransactionHistory.setBackground(new Color(18, 110, 130));
		mntmTransactionHistory.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnView.add(mntmTransactionHistory);
		
		mntmBooksToReturn = new JMenuItem("Books To Return");
		mntmBooksToReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearchBar.setText(" Search");
				listCategories.setSelectedIndex(0);
				selectedMenuItem = "Books To Return";
				crudButtonHandling();
				
				tblView.setModel(modelBorrowersTransactionsRecord);
				hideTableColumns(tblView, new int[] {1, 2, 3});
				doFilter();
				
				// Update the late fee every time user clicks the menu item: Books to return
				calculateLateFee();
				
				layeredPane.removeAll();
				layeredPane.add(panelView);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		mntmBooksToReturn.setBorder(null);
		mntmBooksToReturn.setForeground(new Color(216, 227, 231));
		mntmBooksToReturn.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mntmBooksToReturn.setIconTextGap(10);
		mntmBooksToReturn.setPreferredSize(new Dimension(230, 40));
		mntmBooksToReturn.setBackground(new Color(18, 110, 130));
		mntmBooksToReturn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnView.add(mntmBooksToReturn);
		
		JSeparator separatorMainbar_1 = new JSeparator();
		separatorMainbar_1.setForeground(new Color(18, 110, 130));
		separatorMainbar_1.setBackground(new Color(18, 110, 130));
		menuBar.add(separatorMainbar_1);
		
		JLabel lblMenubarTitle = new JLabel("Library Management System  ");
		lblMenubarTitle.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(216, 227, 231)));
		lblMenubarTitle.setForeground(new Color(216, 227, 231));
		lblMenubarTitle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMenubarTitle.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		menuBar.add(lblMenubarTitle);
		
		JLabel lblLogo = new JLabel();
		lblLogo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLogo.setLabelFor(lblMenubarTitle);
		// Creates an icon object from the local source folder
		ImageIcon menubarLogo = new ImageIcon(this.getClass().getResource("menubarLogo.png"));
		// Assigns the icon object as the icon image of components
		lblLogo.setIcon(menubarLogo);
		lblLogo.setFont(new Font("Agency FB", Font.PLAIN, 35));
		menuBar.add(lblLogo);
		
		panelFilter = new JPanel();
		panelFilter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelFilter.setVisible(false);
			}
		});
		panelFilter.setVisible(false);
		panelFilter.setBackground(new Color(216, 227, 231));
		panelFilter.setBounds(710, 210, 120, 190);
		contentPane.add(panelFilter);
		panelFilter.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Filters");
		lblNewLabel.setForeground(new Color(19, 44, 51));
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 10, 55, 25);
		panelFilter.add(lblNewLabel);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(19, 44, 51));
		separator_2.setBackground(new Color(19, 44, 51));
		separator_2.setBounds(10, 32, 55, 2);
		panelFilter.add(separator_2);
		
		rdoOption1 = new JRadioButton("All");
		rdoOption1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRdo = rdoOption1.getText();
				
				if (selectedMenuItem.equals("Deleted Data")) {
					tblView.setModel(modelDeletedStudents);
					hideTableColumns(tblView, new int[] {8, 9, 10});
				}
				
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
		rdoOption1.setBackground(new Color(216, 227, 231));
		rdoOption1.setBorder(null);
		rdoOption1.setForeground(new Color(19, 44, 51));
		rdoOption1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdoOption1.setBounds(10, 45, 100, 25);
		panelFilter.add(rdoOption1);
		
		rdoOption2 = new JRadioButton("Available");
		rdoOption2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRdo = rdoOption2.getText();
				
				if (selectedMenuItem.equals("Deleted Data")) {
					tblView.setModel(modelDeletedCategory);
					hideTableColumns(tblView, new int[] {2});
				} 
				
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
		rdoOption2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdoOption2.setBorder(null);
		rdoOption2.setBackground(new Color(216, 227, 231));
		rdoOption2.setBounds(10, 80, 100, 25);
		panelFilter.add(rdoOption2);
		
		rdoOption3 = new JRadioButton("Damaged");
		rdoOption3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRdo = rdoOption3.getText();
				
				if (selectedMenuItem.equals("Deleted Data")) {
					tblView.setModel(modelDeletedBooks);
					hideTableColumns(tblView, new int[] {6, 7, 8});
				}
						
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
		rdoOption3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdoOption3.setBorder(null);
		rdoOption3.setBackground(new Color(216, 227, 231));
		rdoOption3.setBounds(10, 115, 100, 25);
		panelFilter.add(rdoOption3);
		
		rdoOption4 = new JRadioButton("Borrowed");
		rdoOption4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRdo = rdoOption4.getText();
				
				if (selectedMenuItem.equals("Deleted Data")) {
					tblView.setModel(modelDeletedStocks);
					hideTableColumns(tblView, new int[] {6});
				}
	
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
		rdoOption4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdoOption4.setFocusable(false);
		rdoOption4.setBorder(null);
		rdoOption4.setBackground(new Color(216, 227, 231));
		rdoOption4.setBounds(10, 151, 100, 25);
		panelFilter.add(rdoOption4);
		
		ButtonGroup rdoGroupFilter = new ButtonGroup();
		rdoGroupFilter.add(rdoOption1);
		rdoGroupFilter.add(rdoOption2);
		rdoGroupFilter.add(rdoOption3);
		rdoGroupFilter.add(rdoOption4);

		JPanel panelToolBar = new JPanel();
		panelToolBar.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(81, 196, 211)));
		panelToolBar.setBackground(new Color(19, 44, 51));
		panelToolBar.setBounds(230, 150, 1136, 80);
		contentPane.add(panelToolBar);
		panelToolBar.setLayout(null);
		
		txtSearchBar = new JTextField();
		txtSearchBar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				doFilter();
			}
		});
		txtSearchBar.setText(" Search");
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
		txtSearchBar.setBackground(new Color(19, 44, 51));
		txtSearchBar.setCaretColor(new Color(216, 227, 231));
		txtSearchBar.setForeground(new Color(216, 227, 231));
		txtSearchBar.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtSearchBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtSearchBar.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtSearchBar.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		txtSearchBar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSearchBar.setBounds(70, 20, 400, 40);
		panelToolBar.add(txtSearchBar);
		txtSearchBar.setColumns(10);
		
		JLabel lblSearchIcon = new JLabel("");
		// Creates an icon object from the local source folder
		ImageIcon searchIcon = new ImageIcon(this.getClass().getResource("searchIcon.png"));
		// Assigns the icon object as the icon image of components
		lblSearchIcon.setIcon(searchIcon);
		lblSearchIcon.setBounds(20, 20, 40, 40);
		panelToolBar.add(lblSearchIcon);
		
		lblFilter = new JLabel("");
		lblFilter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelFilter.setVisible(true);;
			}
		});
		lblFilter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// Creates an icon object from the local source folder
		ImageIcon filterIcon = new ImageIcon(this.getClass().getResource("filterIcon.png"));
		// Assigns the icon object as the icon image of components
		lblFilter.setIcon(filterIcon);
		lblFilter.setBounds(480, 20, 50, 40);
		lblFilter.setVisible(false);
		panelToolBar.add(lblFilter);
		
		btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isAdd = true;

				loadFrame();		
			}
		});
		btnAdd.setBackground(new Color(19, 44, 51));
		btnAdd.setForeground(new Color(216, 227, 231));
		btnAdd.setFocusable(false);
		btnAdd.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAdd.setBackground(new Color(81, 196, 211));
				btnAdd.setForeground(new Color(19, 44, 51));
				btnAdd.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAdd.setBackground(new Color(19, 44, 51));
				btnAdd.setForeground(new Color(216, 227, 231));
				btnAdd.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnAdd.setBounds(825, 25, 100, 30);
		panelToolBar.add(btnAdd);
		
		btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isEdit = true;
				
				loadFrame();
			}
		});
		btnEdit.setBackground(new Color(19, 44, 51));
		btnEdit.setForeground(new Color(216, 227, 231));
		btnEdit.setFocusable(false);
		btnEdit.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEdit.setBackground(new Color(81, 196, 211));
				btnEdit.setForeground(new Color(19, 44, 51));
				btnEdit.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnEdit.setBackground(new Color(19, 44, 51));
				btnEdit.setForeground(new Color(216, 227, 231));
				btnEdit.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		btnEdit.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnEdit.setBounds(935, 25, 90, 30);
		panelToolBar.add(btnEdit);
		
		btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isDelete = true;
				
				loadFrame();
			}
		});
		btnDelete.setBackground(new Color(19, 44, 51));
		btnDelete.setForeground(new Color(216, 227, 231));
		btnDelete.setFocusable(false);
		btnDelete.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDelete.setBackground(new Color(81, 196, 211));
				btnDelete.setForeground(new Color(19, 44, 51));
				btnDelete.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnDelete.setBackground(new Color(19, 44, 51));
				btnDelete.setForeground(new Color(216, 227, 231));
				btnDelete.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnDelete.setBounds(1036, 25, 90, 30);
		panelToolBar.add(btnDelete);
		
		btnManageStocks = new JButton("MANAGE STOCKS");
		btnManageStocks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblBookInventory.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(contentPane, "Please select a book to manage its stocks!");
				} else {
					frameBookInventory = new BookInventory();
					frameBookInventory.setVisible(true);
					
					Main.frame.setEnabled(false);
				}		
			}
		});
		btnManageStocks.setVisible(false);
		btnManageStocks.setForeground(new Color(216, 227, 231));
		btnManageStocks.setFocusable(false);
		btnManageStocks.setBackground(new Color(19, 44, 51));
		btnManageStocks.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnManageStocks.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnManageStocks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnManageStocks.setBackground(new Color(81, 196, 211));
				btnManageStocks.setForeground(new Color(19, 44, 51));
				btnManageStocks.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnManageStocks.setBackground(new Color(19, 44, 51));
				btnManageStocks.setForeground(new Color(216, 227, 231));
				btnManageStocks.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		btnManageStocks.setBounds(946, 25, 180, 30);
		panelToolBar.add(btnManageStocks);
		
		btnAddToCart = new JButton("ADD TO CART");
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblBooksRecord.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(contentPane, "Please select a book to add to cart!");
				} else {
					selectedRow = tblBooksRecord.getSelectedRow();
					addBookToCart();
				}		
			}
		});
		btnAddToCart.setVisible(false);
		btnAddToCart.setForeground(new Color(216, 227, 231));
		btnAddToCart.setFocusable(false);
		btnAddToCart.setBackground(new Color(19, 44, 51));
		btnAddToCart.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnAddToCart.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnAddToCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAddToCart.setBackground(new Color(81, 196, 211));
				btnAddToCart.setForeground(new Color(19, 44, 51));
				btnAddToCart.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAddToCart.setBackground(new Color(19, 44, 51));
				btnAddToCart.setForeground(new Color(216, 227, 231));
				btnAddToCart.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		btnAddToCart.setBounds(946, 25, 180, 30);
		panelToolBar.add(btnAddToCart);
		
		chkBookToggle = new JCheckBox("Book Information");
		chkBookToggle.setSelected(true);
		chkBookToggle.setVisible(false);
		chkBookToggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chkBookToggle.isSelected()) {
					chkBookToggle.setText("Book Information");
					selectedMenuItem = "Information";
				}else {
					chkBookToggle.setText("Book Category");
					selectedMenuItem = "Category";
				}
			}
		});
		chkBookToggle.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		chkBookToggle.setBorder(null);
		chkBookToggle.setForeground(new Color(216, 227, 231));
		chkBookToggle.setRequestFocusEnabled(false);
		chkBookToggle.setMargin(new Insets(10, 10, 10, 10));
		chkBookToggle.setIconTextGap(5);
		chkBookToggle.setFont(new Font("Segoe UI", Font.BOLD, 18));
		chkBookToggle.setBackground(new Color(19, 44, 51));
		chkBookToggle.setBounds(635, 25, 180, 30);
		panelToolBar.add(chkBookToggle);
		
		btnReturnBook = new JButton("RETURN BOOK");
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblView.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(contentPane, "Please select a book to return!");
				} else {
					selectedRow = tblView.getSelectedRow();
					returnBook();
				}		
			}
		});
		btnReturnBook.setFocusable(false);
		btnReturnBook.setVisible(false);
		btnReturnBook.setBackground(new Color(19, 44, 51));
		btnReturnBook.setForeground(new Color(216, 227, 231));
		btnReturnBook.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnReturnBook.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnReturnBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReturnBook.setBackground(new Color(81, 196, 211));
				btnReturnBook.setForeground(new Color(19, 44, 51));
				btnReturnBook.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnReturnBook.setBackground(new Color(19, 44, 51));
				btnReturnBook.setForeground(new Color(216, 227, 231));
				btnReturnBook.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		btnReturnBook.setBounds(946, 25, 180, 30);
		panelToolBar.add(btnReturnBook);
		
		btnCancelRequest = new JButton("CANCEL REQUEST");
		btnCancelRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblView.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(contentPane, "Please select a request to cancel!");
				} else {
					selectedRow = tblView.getSelectedRow();
					cancelRequest();
				}		
			}
		});
		btnCancelRequest.setFocusable(false);
		btnCancelRequest.setVisible(false);
		btnCancelRequest.setBackground(new Color(19, 44, 51));
		btnCancelRequest.setForeground(new Color(216, 227, 231));
		btnCancelRequest.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnCancelRequest.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnCancelRequest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCancelRequest.setBackground(new Color(81, 196, 211));
				btnCancelRequest.setForeground(new Color(19, 44, 51));
				btnCancelRequest.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCancelRequest.setBackground(new Color(19, 44, 51));
				btnCancelRequest.setForeground(new Color(216, 227, 231));
				btnCancelRequest.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		btnCancelRequest.setBounds(946, 25, 180, 30);
		panelToolBar.add(btnCancelRequest);
		
		JPanel panelCategories = new JPanel();
		panelCategories.setBorder(new MatteBorder(0, 0, 0, 5, (Color) new Color(81, 196, 211)));
		panelCategories.setBackground(new Color(19, 44, 51));
		panelCategories.setBounds(0, 150, 230, 620);
		contentPane.add(panelCategories);
		panelCategories.setLayout(null);
		
		JLabel lblBookCategories = new JLabel("Book Categories");
		lblBookCategories.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBookCategories.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblBookCategories.setForeground(new Color(216, 227, 231));
		lblBookCategories.setBounds(15, 15, 190, 40);
		panelCategories.add(lblBookCategories);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(216, 227, 231));
		separator_1.setBounds(15, 55, 190, 2);
		panelCategories.add(separator_1);
		
		JScrollPane scrollPane_3;
		JScrollPane scrollPaneList = new JScrollPane();
		scrollPaneList.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				
				//  Ignore events generated with a rotation of 0
				
		        int rotation = e.getWheelRotation();
		        
		        if (rotation == 0)
		            return;
		        
		        //  Get the appropriate Action key for the given rotation
		        //  (unit/block scroll is system dependent)
		        
		        String key = null;
		        
		        if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL)
		        {
		            key = (rotation < 0) ? "negativeUnitIncrement" : "positiveUnitIncrement";
		        }
		        else
		        {
		            key = (rotation < 0) ? "negativeBlockIncrement" : "positiveBlockIncrement";
		        }
		        
		        //  Get the Action from the scrollBar ActionMap for the given key
		        
		        JScrollPane scrollPane = (JScrollPane)e.getComponent();
		        JScrollBar vertical = scrollPane.getVerticalScrollBar();
		        
		        ActionMap map = vertical.getActionMap();
		        Action action = (Action)map.get( key );
		        ActionEvent event = new ActionEvent(vertical, ActionEvent.ACTION_PERFORMED, "");
		        
		        //  Invoke the Action the appropriate number of times to simulate
		        //  default mouse wheel scrolling
		        
		        int unitsToScroll = Math.abs( e.getUnitsToScroll() );
		        
		        for (int i = 0; i < unitsToScroll; i++)
		            action.actionPerformed( event );
		        
			}
		});
		scrollPaneList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneList.setBackground(new Color(19, 44, 51));
		scrollPaneList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneList.setBorder(null);
		scrollPaneList.setBounds(25, 66, 180, 525);
		panelCategories.add(scrollPaneList);
		
		listCategories = new JList<String>(listModelCategories);
		listCategories.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (selectedMenuItem.equals("Students") || selectedMenuItem.equals("Borrowed Transactions") ||
						selectedMenuItem.equals("Transactions") || selectedMenuItem.equals("Penalties Collected") || selectedMenuItem.equals("Deleted Data")) {
					int response = JOptionPane.showConfirmDialog(contentPane, "Proceed to viewing books available/damaged?", "Confirmation", JOptionPane.YES_NO_OPTION);
					
					if (response == JOptionPane.YES_OPTION) {
						selectedMenuItem = "Books Available/Damaged";
						rdoOption2.setText("Available");
						rdoOption3.setText("Damaged");
						rdoOption4.setVisible(true);
						crudButtonHandling();
			
						tblView.setModel(modelBookInventoryRecord);
						
						layeredPane.removeAll();
						layeredPane.add(panelView);
						layeredPane.repaint();
						layeredPane.revalidate();
						
						txtSearchBar.setText(" Search");
						listCategories.setSelectedIndex(0);
					}
					
					
				} else if (selectedMenuItem.equals("Pending Requests") || selectedMenuItem.equals("Books To Return") || selectedMenuItem.equals("Transaction History")) {
					int response = JOptionPane.showConfirmDialog(contentPane, "Proceed to view books?", "Confirmation", JOptionPane.YES_NO_OPTION);
					
					if (response == JOptionPane.YES_OPTION) {
						selectedMenuItem = "Books";
						crudButtonHandling();
						
						mnAllBooks.setSelected(false);
						layeredPane.removeAll();
						layeredPane.add(panelBooks);
						layeredPane.repaint();
						layeredPane.revalidate();
						
						txtSearchBar.setText(" Search");
						listCategories.setSelectedIndex(0);
					}
				}	
				
				doFilter();
			}
		});
		listCategories.setVisibleRowCount(20);
		listCategories.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		listCategories.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listCategories.setFixedCellWidth(180);
		listCategories.setFixedCellHeight(40);
		listCategories.setFocusable(false);
		listCategories.setRequestFocusEnabled(false);
		listCategories.setSelectionForeground(new Color(81, 196, 211));
		listCategories.setSelectionBackground(new Color(19, 44, 51));
		listCategories.setForeground(new Color(216, 227, 231));
		listCategories.setBackground(new Color(19, 44, 51));
		listCategories.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		scrollPaneList.setViewportView(listCategories);	
		
		layeredPane = new JLayeredPane();
		layeredPane.setBackground(new Color(216, 227, 231));
		layeredPane.setBounds(240, 240, 1115, 538);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		
		// PANEL STUDENT RECORDS
		panelStudentRecord = new JPanel();
		panelStudentRecord.setVisible(false);
		layeredPane.setLayer(panelStudentRecord, 1);
		layeredPane.add(panelStudentRecord);
		panelStudentRecord.setLayout(null);
		
		JScrollPane scrollPaneStudentRecord = new JScrollPane();
		scrollPaneStudentRecord.setBounds(10, 10, 1095, 500);
		panelStudentRecord.add(scrollPaneStudentRecord);
		
		tblStudentRecord = new JTable(modelStudentRecord){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tblStudentRecord.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblStudentRecord.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPaneStudentRecord.setViewportView(tblStudentRecord);
		
		// hides the column for password & user type
		hideTableColumns(tblStudentRecord, new int[] {8, 9, 10, 11});
		
		
		// PANEL BOOK INFORMATION (BOOK INFORMATION)
		panelBookInformation = new JPanel();
		layeredPane.setLayer(panelBookInformation, 3);
		layeredPane.add(panelBookInformation);
		panelBookInformation.setLayout(null);

		scrollPaneBookInformation = new JScrollPane();
		scrollPaneBookInformation.setBounds(320, 10, 785, 500);
		panelBookInformation.add(scrollPaneBookInformation);
				
		tblBookInformation = new JTable(modelBookRecord){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tblBookInformation.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblBookInformation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPaneBookInformation.setViewportView(tblBookInformation);
				
		
		// (BOOK CATEGORY)
		JScrollPane scrollPaneBookCategory = new JScrollPane();
		scrollPaneBookCategory.setBounds(10, 10, 300, 500);
		panelBookInformation.add(scrollPaneBookCategory);
				
		tblBookCategory = new JTable(modelBookCategoryRecord){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tblBookCategory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblBookCategory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPaneBookCategory.setViewportView(tblBookCategory);
				
		// hide the given columns in the given table
		hideTableColumns(tblBookInformation, new int[] {5, 6, 7, 8});
		

		// PANEL BOOK INVENTORY
		panelBookInventory = new JPanel();
		panelBookInventory.setVisible(false);
		layeredPane.setLayer(panelBookInventory, 2);
		layeredPane.add(panelBookInventory);
		panelBookInventory.setLayout(null);
		
		JScrollPane scrollPaneBookInventory = new JScrollPane();
		scrollPaneBookInventory.setBounds(10, 10, 1095, 500);
		panelBookInventory.add(scrollPaneBookInventory);
		
		tblBookInventory = new JTable(modelBookRecord){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tblBookInventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblBookInventory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPaneBookInventory.setViewportView(tblBookInventory);	
		
		// hide the given columns in the given table
		hideTableColumns(tblBookInventory, new int[] {4});
		
		
		// PANEL ALL BOOKS
		panelBooks = new JPanel();
		panelBooks.setVisible(true);
		layeredPane.setLayer(panelBooks, 2);
		layeredPane.add(panelBooks);
		panelBooks.setLayout(null);
		
		JScrollPane scrollPaneBooksRecord = new JScrollPane();
		scrollPaneBooksRecord.setBounds(10, 10, 1095, 500);
		panelBooks.add(scrollPaneBooksRecord);
		
		tblBooksRecord = new JTable(modelBookRecord){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tblBooksRecord.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblBooksRecord.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPaneBooksRecord.setViewportView(tblBooksRecord);
		
		// hide the given columns in the given table
		hideTableColumns(tblBooksRecord, new int[] {5, 7, 8});
		
		// PANEL VIEW
		panelView = new JPanel();
		layeredPane.add(panelView, "name_89363638550400");
		panelView.setLayout(null);
		
		JScrollPane scrollPaneView = new JScrollPane();
		scrollPaneView.setBounds(10, 11, 1095, 499);
		panelView.add(scrollPaneView);
		
		tblView = new JTable(){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tblView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblView.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPaneView.setViewportView(tblView);
		
		// Get the categories that have books and display it on a list
		getCategories();
		
		// Set the application display appropriate for either admin or user 
		setUserView(currentUserType);
	}
	
	
	// METHODS
	// Method for setting identifiers' value back to false and controls back to default
	public static void refreshMain() {
		Main.frame.setEnabled(true);
		
		tblStudentRecord.clearSelection();
		tblBookInformation.clearSelection();
		tblBookCategory.clearSelection();
		tblBookInventory.clearSelection();
		tblBooksRecord.clearSelection();
		tblView.clearSelection();
		
		isAdd = false;
		isEdit = false;
		isDelete = false;
	}
	
	// Method for setting different view for 2 different user type: Admin and User
	private void setUserView(String userType) {
		boolean bool;
		
		txtSearchBar.setText(" Search");
		listCategories.setSelectedIndex(0);
		
		if (userType.equals("User")) {
			chkBookToggle.setVisible(false);
			btnManageStocks.setVisible(false);
			btnAddToCart.setVisible(true);
			btnReturnBook.setVisible(false);
			btnCancelRequest.setVisible(false);

			selectedMenuItem = "Books";
			doFilter();
			
			layeredPane.removeAll();
			layeredPane.add(panelBooks);
			layeredPane.repaint();
			layeredPane.revalidate();
			bool = false;
		} else {
			chkBookToggle.setVisible(false);
			btnManageStocks.setVisible(false);
			btnAddToCart.setVisible(false);
			btnReturnBook.setVisible(false);
			btnCancelRequest.setVisible(false);
			
			selectedMenuItem = "Students";
			doFilter();
			
			layeredPane.removeAll();
			layeredPane.add(panelStudentRecord);
			layeredPane.repaint();
			layeredPane.revalidate();
			bool = true;
		}
		
		// buttons 
		btnAdd.setVisible(bool);
		btnEdit.setVisible(bool);
		btnDelete.setVisible(bool);
		
		// menus
		mnCart.setVisible(!bool);
		mnManage.setVisible(bool);
		mnAllBooks.setVisible(!bool);
		mnViewReports.setVisible(bool);
		mnView.setVisible(!bool);
		mntmEditProfile.setVisible(!bool);
	}
	
	
	// Method for handling the visibility of controls: checkBox and buttons
	private void crudButtonHandling(){
		boolean bool = false;
		
		if (selectedMenuItem.equals("Students")) {
			chkBookToggle.setVisible(false);
			btnManageStocks.setVisible(false);
			btnAddToCart.setVisible(false);
			btnReturnBook.setVisible(false);
			btnCancelRequest.setVisible(false);
			
			bool = true;
		} else if (selectedMenuItem.equals("Information")) {
			chkBookToggle.setVisible(true);
			chkBookToggle.setSelected(true);
			chkBookToggle.setText("Book Information");
			btnManageStocks.setVisible(false);
			btnAddToCart.setVisible(false);
			btnReturnBook.setVisible(false);
			btnCancelRequest.setVisible(false);
			
			bool = true;
		} else if (selectedMenuItem.equals("Inventory")) {
			chkBookToggle.setVisible(false);
			btnManageStocks.setVisible(true);
			btnAddToCart.setVisible(false);
			btnReturnBook.setVisible(false);
			btnCancelRequest.setVisible(false);
		} else if (selectedMenuItem.equals("Books")) {			
			chkBookToggle.setVisible(false);
			btnManageStocks.setVisible(false);
			btnAddToCart.setVisible(true);
			btnReturnBook.setVisible(false);
			btnCancelRequest.setVisible(false);
		} else if (selectedMenuItem.equals("Books To Return")) {
			chkBookToggle.setVisible(false);
			btnManageStocks.setVisible(false);
			btnAddToCart.setVisible(false);
			btnReturnBook.setVisible(true);
			btnCancelRequest.setVisible(false);
		} else if (selectedMenuItem.equals("Pending Requests")){
			chkBookToggle.setVisible(false);
			btnManageStocks.setVisible(false);
			btnAddToCart.setVisible(false);
			btnReturnBook.setVisible(false);
			btnCancelRequest.setVisible(true);
		} else {
			chkBookToggle.setVisible(false);
			btnManageStocks.setVisible(false);
			btnAddToCart.setVisible(false);
			btnReturnBook.setVisible(false);
			btnCancelRequest.setVisible(false);
		}
		
		if (selectedMenuItem.equals("Transactions") || selectedMenuItem.equals("Books Available/Damaged")) {
			panelFilter.setVisible(false);
			lblFilter.setVisible(true);
			selectedRdo = "All";
		} else if (selectedMenuItem.equals("Deleted Data")) {
			panelFilter.setVisible(false);
			lblFilter.setVisible(true);
			selectedRdo = "Students";
		} else {
			panelFilter.setVisible(false);
			lblFilter.setVisible(false);
		}
		
		rdoOption1.setSelected(true);
		btnAdd.setVisible(bool);
		btnEdit.setVisible(bool);
		btnDelete.setVisible(bool);
	}
	
	
	// Method for loading the separate form of a selected operation 
	private void loadFrame() {
		if(selectedMenuItem.equals("Students")) {
			frameStudent = new Student();
			frameStudent.setVisible(true);
			
		} else if(selectedMenuItem.equals("Information")) {
			frameBookInformation = new BookInformation();
			frameBookInformation.setVisible(true);
			
		} else if(selectedMenuItem.equals("Category")) {
			frameBookCategories = new BookCategories();
			frameBookCategories.setVisible(true);
			
		} else if(selectedMenuItem.equals("Inventory")) {
			frameBookInventory = new BookInventory();
			frameBookInventory.setVisible(true);
			
		} 
		
		Main.frame.setEnabled(false);
	}
	
	
	// Method for adding the given array's data to a specific tableModel
	private static int addDummy(Object[][] array, Object model, Object id) {
		DefaultTableModel tableModel = (DefaultTableModel) model;
		int selectedId = (int) id;
		
		for (int i = 0; i < array.length; i++) {
			tableModel.addRow(array[i]);
			selectedId++;
		}
		
		return selectedId;
	}
	
	
	// Method for hiding the given columns of a table
	public static void hideTableColumns(Object table, int[] columns) {
		JTable selectedTable = (JTable) table;
		
		for (int i = 0; i < columns.length; i++) {
			selectedTable.getColumnModel().getColumn(columns[i]).setMinWidth(0);
			selectedTable.getColumnModel().getColumn(columns[i]).setMaxWidth(0);
		}
	}
	
	
	// Method for getting the categories that have books and displays it on a list - will be used for table filtering
	public static void getCategories() {
		listModelCategories.removeAllElements();
		
		if (listModelCategories.getSize() == 0) {
			listModelCategories.addElement("-- All --");	
		}
		
		// Adds all categories that have at least one book
		for (int i = 0; i < modelBookCategoryRecord.getRowCount(); i++) {
			int categoryNumberOfBooks = Integer.parseInt(modelBookCategoryRecord.getValueAt(i, 2).toString());
			
			if (categoryNumberOfBooks > 0) {
				String categoryName = "- " + modelBookCategoryRecord.getValueAt(i, 1).toString();
				
				listModelCategories.addElement(categoryName);
			}
		}
		
		listCategories.setSelectedIndex(0);
	}
	
	// Method for filtering tables using method filterTable() and getFilteredIndices()
	private void doFilter() {	
		if(selectedMenuItem.equals("Books")) {
			fetchedIndices = filterTable(tblBooksRecord, modelBookRecord, listModelCategories.getElementAt(listCategories.getSelectedIndex()), 
					txtSearchBar.getText(), -1, -1, new int[] {0, 1, 2, 4});
			
		} else if (selectedMenuItem.equals("Students")) {
			fetchedIndices = filterTable(tblStudentRecord, modelStudentRecord, txtSearchBar.getText(), "", -1, -1, new int[] {0, 1, 2, 3, 4, 5, 6, 7});	
			
		} else if (selectedMenuItem.equals("Category")){
			fetchedIndices = filterTable(tblBookCategory, modelBookCategoryRecord, listModelCategories.getElementAt(listCategories.getSelectedIndex()), 
					txtSearchBar.getText(), -1, -1, new int[] {0, 1});	
			
		} else if (selectedMenuItem.equals("Information")) {
			fetchedIndices = filterTable(tblBookInformation, modelBookRecord, listModelCategories.getElementAt(listCategories.getSelectedIndex()), 
					txtSearchBar.getText(), -1, -1, new int[] {0, 1, 2, 4});	
			
		} else if (selectedMenuItem.equals("Inventory")) {
			fetchedIndices = filterTable(tblBookInventory, modelBookRecord, listModelCategories.getElementAt(listCategories.getSelectedIndex()), 
					txtSearchBar.getText(), -1, -1, new int[] {0, 1, 2, 4});	
			
		} else if (selectedMenuItem.equals("Pending Requests") || selectedMenuItem.equals("Books To Return") || selectedMenuItem.equals("Transaction History")) {
			fetchedIndices = filterTable(tblView, tblView.getModel(), txtSearchBar.getText(), "", currentAccountID, 1, new int[] {0, 1, 4, 5, 6, 7});	
			
		} else if (selectedMenuItem.equals("Books Available/Damaged")) {
			fetchedIndices = filterTable(tblView, tblView.getModel(),  txtSearchBar.getText(), 
					selectedRdo, -1, -1, new int[] {0, 1, 2, 3, 5, 6});
			
		} else if (selectedMenuItem.equals("Borrowed Transactions")) {
			fetchedIndices = filterTable(tblView, tblView.getModel(), txtSearchBar.getText(), 
					"", -1, -1, new int[] {0, 1, 2, 3, 5, 6, 7});
			
		} else if (selectedMenuItem.equals("Transactions")) {
			fetchedIndices = filterTable(tblView, tblView.getModel(), txtSearchBar.getText(), 
					selectedRdo, -1, -1, new int[] {0, 1, 2, 3, 5, 6, 7, 10});
			
		} else if (selectedMenuItem.equals("Penalties Collected")) {
			fetchedIndices = filterTable(tblView, tblView.getModel(), txtSearchBar.getText(),
					"", -1, -1, new int[] {0, 1, 2, 3, 7});
			
		} else if (selectedMenuItem.equals("Deleted Data")) {
			int[] columns = new int[0];
			
			if (selectedRdo.equals("Categories")) {
				columns = new int[] {0, 1};
			} else {
				columns = new int[] {0, 1, 2, 3};
			}
			
			fetchedIndices = filterTable(tblView, tblView.getModel(), txtSearchBar.getText(),
					"", -1, -1, columns);
		}
	}
	
	// Method for filtering the table and returning the fetched indices of the filtered table
	public static int[] filterTable(Object table, Object tableModel, String string1, String string2, int intId, int intIdColumn, int[] columnsToSearch) {
		// Filtering of selected table
		JTable selectedTable = (JTable) table;
		DefaultTableModel model = (DefaultTableModel) tableModel;
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
		
		if (string1.equals("-- All --") || string1.equals(" Search") || string1.equals("All")) {
			string1 = "";
		}
		
		if (string2.equals("-- All --") || string2.equals(" Search") || string2.equals("All")) {
			string2 = "";
		}
		
		if (string1.contains("- ")) {
			string1 = string1.replace("- ", "");
		} else if (string2.contains("- ")) {
			string2 = string2.replace("- ", "");
		}
		
		// For 3rd string if selectedMenuItem is Books available/damaged
		String string3 = "";
		if (selectedMenuItem.equals("Books Available/Damaged")) {
			string3 = listModelCategories.getElementAt(listCategories.getSelectedIndex());
			if (string3.equals("-- All --")) {
				string3 = "";
			} else if (string3.contains("- ")) {
				string3 = string3.replace("- ", "");
			}
		}
			
		
		// RowFilter
		ArrayList<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>();
		filters.add(RowFilter.regexFilter(string1.trim(), columnsToSearch));
		filters.add(RowFilter.regexFilter(string2.trim(), columnsToSearch));
		filters.add(RowFilter.regexFilter(string3.trim(), columnsToSearch));
		// Searching for intId and not a string
		if (intId > 0) {
			filters.add(RowFilter.numberFilter(ComparisonType.EQUAL, intId, intIdColumn));
		}
		RowFilter<Object,Object> filter = RowFilter.andFilter(filters);
		
		
		selectedTable.setRowSorter(sorter);
		sorter.setRowFilter(filter);
		
		// Set the columns of table to not sortable
		for (int i = 0; i < selectedTable.getColumnCount(); i++) {
			sorter.setSortable(i, false);
		}
		
		
		
		// Fetching original indices of the sorted table from model
		int[] fetchedIndices = new int[selectedTable.getRowCount()];
		String allElements = "";
		int arrayIndex = 0;
		
		// Search through rows and columns to find if it contains the given string1, string2, string3, and/or intId
		// If it contains, fetch the index of the model and store it to fetchedIndices
		for (int row = 0; row < model.getRowCount(); row++) {
			for (int column = 0; column < columnsToSearch.length; column++) {
				String element = model.getValueAt(row, columnsToSearch[column]).toString();
				
				allElements += element;
			}
			
			// if you have to search the specific id (int)
			if (intId > 0) {
				int intCurrentId = Integer.parseInt(model.getValueAt(row, intIdColumn).toString()); 
				
				if (allElements.contains(string1.trim()) && allElements.contains(string2.trim()) && allElements.contains(string3.trim()) &&				intId == intCurrentId) {
					fetchedIndices[arrayIndex] = row;
					arrayIndex++;
				}
			} else {
				if (allElements.contains(string1.trim()) && allElements.contains(string2.trim()) && allElements.contains(string3.trim())) {
					fetchedIndices[arrayIndex] = row;
					arrayIndex++;
				}
			}
			
			allElements = "";
		}
		
		return fetchedIndices;
	}
	
	
	// Method to add selected book to cart (Student)
	private void addBookToCart() {
		if (Integer.parseInt(modelBookRecord.getValueAt(fetchedIndices[selectedRow], 6).toString()) == 0) {
			JOptionPane.showMessageDialog(contentPane, "Sorry, selected book is not available at the moment.");
		} else {
			if (modelMyCart.getRowCount() == 3) {
				JOptionPane.showMessageDialog(contentPane, "You already have reached the maximum books to borrow!");
			} else {
				 if (MyCart.isBookBorrowed()) {
					 JOptionPane.showMessageDialog(contentPane, "You already have borrowed this book!");
				 } else {
					if (MyCart.isBookInRequest()) {
						JOptionPane.showMessageDialog(contentPane, "You already have borrow request of this book title!");
					} else {
						if (MyCart.isBookInCart()) {
							JOptionPane.showMessageDialog(contentPane, "Selected book title is already in your cart!");
							tblBooksRecord.clearSelection();
						} else {										
							// Creates a new row and stores it to modelMyCart
							Object[] newRow = new Object[9];
							
							newRow[0] = modelBookRecord.getValueAt(fetchedIndices[selectedRow], 0);
							newRow[1] = modelBookRecord.getValueAt(fetchedIndices[selectedRow], 1);
							newRow[2] = modelBookRecord.getValueAt(fetchedIndices[selectedRow], 2);
							newRow[3] = modelBookRecord.getValueAt(fetchedIndices[selectedRow], 3);
							newRow[4] = modelBookRecord.getValueAt(fetchedIndices[selectedRow], 4);
							newRow[5] = null;
							newRow[6] = null; 
							newRow[7] = null; 
							newRow[8] = null;
							
							modelMyCart.addRow(newRow);
							mnCart.setText("My Cart (" + modelMyCart.getRowCount() + ")");
							
							
							// Updates the value of available and borrowed books count
							int available = Integer.parseInt(modelBookRecord.getValueAt(fetchedIndices[selectedRow], 6).toString()) - 1;
							int borrowed = Integer.parseInt(modelBookRecord.getValueAt(fetchedIndices[selectedRow], 8).toString()) + 1;
							
							modelBookRecord.setValueAt(available, fetchedIndices[selectedRow], 6);
							modelBookRecord.setValueAt(borrowed, fetchedIndices[selectedRow], 8);
						}
					}
				}
			}
		}
		
		tblBooksRecord.clearSelection();
	}
	
	
	// Method for returning the borrowed book(s) (Student)
	private void returnBook() {
		int row = fetchedIndices[selectedRow];
		String selectedTransactionId = modelBorrowersTransactionsRecord.getValueAt(row, 0).toString();
		
		
		// Search through transaction requests, and if return request found in transaction request
		// User will be notified that she/he already requested the returning book
		for (int trId = 0; trId < modelTransactionRequestsRecord.getRowCount(); trId++) {
			String transactionRequestsId = modelTransactionRequestsRecord.getValueAt(trId, 0).toString();
			
			if (selectedTransactionId.equals(transactionRequestsId)) {
				// once requests is found in pending requests
				// return
				JOptionPane.showMessageDialog(contentPane, "Selected book to return is already requested!");
				return;
			}
		}
		
		// Once request is not found continue to returning book request
		int response = JOptionPane.showConfirmDialog(contentPane, "Return borrowed book?", "Confirmation", JOptionPane.YES_NO_OPTION);
		
		if (response == JOptionPane.YES_OPTION) {
			Object[] newRow = new Object[13];
			
			for (int i = 0; i < newRow.length; i ++) {
				if (i == 12) {
					newRow[i] = "Returning";
				} else {
					newRow[i] = modelBorrowersTransactionsRecord.getValueAt(row, i);
				}
			}
			
			modelTransactionRequestsRecord.addRow(newRow);
			
			JOptionPane.showMessageDialog(contentPane, "Your returning request has been sent!\n               Status: Pending");
		}
		
		tblView.clearSelection();
	}
	
	
	// Method for canceling the sent requests.
	private void cancelRequest() {
		int selectedRow = fetchedIndices[tblView.getSelectedRow()];
		
		if (modelTransactionRequestsRecord.getValueAt(selectedRow, 12).toString().equals("Borrowing")) {
			int response = JOptionPane.showConfirmDialog(contentPane, "Are you sure to cancel this request?", "Confirmation", JOptionPane.YES_NO_OPTION);
			
			if (response == JOptionPane.YES_OPTION) {
				// Remove the borrowing request 
				TransactionRequests.removeRequest(selectedRow);
				
				JOptionPane.showMessageDialog(contentPane, "Selected request canceled!");	
				
				doFilter(); // refresh the filtered table
			}
		} else {
			int response = JOptionPane.showConfirmDialog(contentPane, "Are you sure to cancel this request?", "Confirmation", JOptionPane.YES_NO_OPTION);
			
			if (response == JOptionPane.YES_OPTION) {
				modelTransactionRequestsRecord.removeRow(selectedRow);
			}
		}
	}
	
	
	// Calculate the late fee every time student clicks the Books to return and admin clicks borrowed transactions
	private void calculateLateFee() {
		for (int i = 0; i < modelBorrowersTransactionsRecord.getRowCount(); i++) {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("MMMM-dd-yyyy");
			String date = Main.modelBorrowersTransactionsRecord.getValueAt(i, 10).toString();
			
			LocalDate todayDate = LocalDate.now();
			LocalDate returnDate = LocalDate.parse(date, format); 
			
			// Calculate and get the days late
			int daysLate = Period.between(returnDate, todayDate).getDays();
			
			// Set the calculated penalty fee / late fee to row if daysLate > 0
			if (daysLate > 0) {
				Main.modelBorrowersTransactionsRecord.setValueAt(daysLate * 10, i, 11);
			}			
		}
	}
}

