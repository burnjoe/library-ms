import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Insets;
import javax.swing.JSeparator;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Student extends JFrame {

	
	// Components
	private JPanel contentPane;
	private JLabel lblId;
	private JTextField txtLastName, txtFirstName, txtMiddleName, txtAddress, txtEmailAddress, txtContactNumber, txtUsername;
	private JTable tblStudentRecord;
	private JPasswordField txtPassword;
	private JButton btnSave, btnExit;
	private JCheckBox chkShowPassword, chkLockUnlock;
	private JTextField txtSearchBar;
	
	private int selectedRow = -1;
	private int[] fetchedIndices;
	
	
	public Student() {
		setUndecorated(true);
		setBounds(100, 100, 1200, 640);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
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
		
		JLabel lblFrameTitle = new JLabel(Main.isAdd ? "Add Account" : (Main.isEdit ? "Edit Account" : (Main.isDelete ? "Delete Account" : null)));
		// Creates an icon object from the local source folder
		ImageIcon lmsframeIcon = new ImageIcon(this.getClass().getResource("LMSFrameIcon.png"));
		// Assigns the icon object as the icon image of components
		lblFrameTitle.setIcon(lmsframeIcon);
		lblFrameTitle.setForeground(new Color(216, 227, 231));
		lblFrameTitle.setIconTextGap(10);
		lblFrameTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFrameTitle.setBounds(10, 10, 250, 30);
		contentPane.add(lblFrameTitle);
		
		JLabel lblSearchIcon = new JLabel("");
		lblSearchIcon.setToolTipText("Search");
		// Creates an icon object from the local source folder
		ImageIcon childframesearchIcon = new ImageIcon(this.getClass().getResource("childframesearchIcon.png"));
		// Assigns the icon object as the icon image of components
		lblSearchIcon.setIcon(childframesearchIcon);
		lblSearchIcon.setBounds(380, 50, 30, 30);
		contentPane.add(lblSearchIcon);
		
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
		txtSearchBar.setBounds(420, 50, 300, 30);
		contentPane.add(txtSearchBar);
		
		JLabel lblStudentId = new JLabel("Student ID:");
		lblStudentId.setForeground(new Color(216, 227, 231));
		lblStudentId.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblStudentId.setBounds(20, 100, 140, 30);
		contentPane.add(lblStudentId);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setForeground(new Color(216, 227, 231));
		lblLastName.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblLastName.setBounds(20, 145, 140, 30);
		contentPane.add(lblLastName);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setForeground(new Color(216, 227, 231));
		lblFirstName.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblFirstName.setBounds(20, 190, 140, 30);
		contentPane.add(lblFirstName);
		
		JLabel lblMiddleName = new JLabel("Middle Name:");
		lblMiddleName.setForeground(new Color(216, 227, 231));
		lblMiddleName.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblMiddleName.setBounds(20, 235, 140, 30);
		contentPane.add(lblMiddleName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setForeground(new Color(216, 227, 231));
		lblAddress.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblAddress.setBounds(20, 280, 140, 30);
		contentPane.add(lblAddress);
		
		JLabel lblContactNumber = new JLabel("Contact Number:");
		lblContactNumber.setForeground(new Color(216, 227, 231));
		lblContactNumber.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblContactNumber.setBounds(20, 325, 140, 30);
		contentPane.add(lblContactNumber);
		
		JLabel lblEmail = new JLabel("Email Address:");
		lblEmail.setForeground(new Color(216, 227, 231));
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblEmail.setBounds(20, 370, 140, 30);
		contentPane.add(lblEmail);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(new Color(216, 227, 231));
		lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblUsername.setBounds(20, 430, 140, 30);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(216, 227, 231));
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblPassword.setBounds(20, 475, 140, 30);
		contentPane.add(lblPassword);
		
		lblId = new JLabel(Main.isAdd ? String.valueOf(Main.studentId) : null);
		lblId.setToolTipText("Student ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setForeground(new Color(216, 227, 231));
		lblId.setBackground(new Color(18, 110, 130));
		lblId.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));	
		lblId.setBounds(170, 100, 190, 30);
		contentPane.add(lblId);
		
		txtLastName = new JTextField();
		txtLastName.setToolTipText("Last name");
		txtLastName.setCaretColor(new Color(216, 227, 231));
		txtLastName.setBackground(new Color(18, 110, 130));
		txtLastName.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtLastName.setForeground(new Color(216, 227, 231));
		txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLastName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtLastName.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtLastName.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		txtLastName.setColumns(10);
		txtLastName.setBounds(170, 145, 190, 30);
		contentPane.add(txtLastName);
		
		txtFirstName = new JTextField();
		txtFirstName.setToolTipText("First name");
		txtFirstName.setCaretColor(new Color(216, 227, 231));
		txtFirstName.setBackground(new Color(18, 110, 130));
		txtFirstName.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtFirstName.setForeground(new Color(216, 227, 231));
		txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtFirstName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtFirstName.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtFirstName.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(170, 190, 190, 30);
		contentPane.add(txtFirstName);
		
		txtMiddleName = new JTextField();
		txtMiddleName.setToolTipText("Middle name");
		txtMiddleName.setCaretColor(new Color(216, 227, 231));
		txtMiddleName.setBackground(new Color(18, 110, 130));
		txtMiddleName.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtMiddleName.setForeground(new Color(216, 227, 231));
		txtMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMiddleName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtMiddleName.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtMiddleName.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		txtMiddleName.setColumns(10);
		txtMiddleName.setBounds(170, 235, 190, 30);
		contentPane.add(txtMiddleName);
		
		txtAddress = new JTextField();
		txtAddress.setToolTipText("Address");
		txtAddress.setCaretColor(new Color(216, 227, 231));
		txtAddress.setBackground(new Color(18, 110, 130));
		txtAddress.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtAddress.setForeground(new Color(216, 227, 231));
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAddress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtAddress.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtAddress.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		txtAddress.setColumns(10);
		txtAddress.setBounds(170, 280, 190, 30);
		contentPane.add(txtAddress);
		
		txtEmailAddress = new JTextField();
		txtEmailAddress.setToolTipText("Email address");
		txtEmailAddress.setCaretColor(new Color(216, 227, 231));
		txtEmailAddress.setBackground(new Color(18, 110, 130));
		txtEmailAddress.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtEmailAddress.setForeground(new Color(216, 227, 231));
		txtEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmailAddress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtEmailAddress.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtEmailAddress.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		txtEmailAddress.setColumns(10);
		txtEmailAddress.setBounds(170, 370, 190, 30);
		contentPane.add(txtEmailAddress);
		
		txtContactNumber = new JTextField();
		txtContactNumber.setToolTipText("Contact number");
		txtContactNumber.setCaretColor(new Color(216, 227, 231));
		txtContactNumber.setBackground(new Color(18, 110, 130));
		txtContactNumber.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtContactNumber.setForeground(new Color(216, 227, 231));
		txtContactNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtContactNumber.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtContactNumber.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtContactNumber.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		txtContactNumber.setColumns(10);
		txtContactNumber.setBounds(170, 325, 190, 30);
		contentPane.add(txtContactNumber);

		txtUsername = new JTextField();
		txtUsername.setToolTipText("Username");
		txtUsername.setCaretColor(new Color(216, 227, 231));
		txtUsername.setBackground(new Color(18, 110, 130));
		txtUsername.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtUsername.setForeground(new Color(216, 227, 231));
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtUsername.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtUsername.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		txtUsername.setColumns(10);
		txtUsername.setBounds(170, 430, 190, 30);
		contentPane.add(txtUsername);
		
		txtPassword = new JPasswordField();
		txtPassword.setToolTipText("Password");
		txtPassword.setCaretColor(new Color(216, 227, 231));
		txtPassword.setBackground(new Color(18, 110, 130));
		txtPassword.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtPassword.setForeground(new Color(216, 227, 231));
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(txtPassword.isEnabled()) {
				txtPassword.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(txtPassword.isEnabled()) {
				txtPassword.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
				}
			}
		});
		txtPassword.setBounds(170, 475, 190, 30);
		contentPane.add(txtPassword);
		
		btnSave = new JButton(Main.isDelete ? "DELETE" : "SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		btnSave.setFocusable(false);
		btnSave.setBackground(new Color(18, 110, 130));
		btnSave.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnSave.setForeground(new Color(216, 227, 231));
		btnSave.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(btnSave.isEnabled()) {
				btnSave.setBackground(new Color(81, 196, 211));
				btnSave.setForeground(new Color(19, 44, 51));
				btnSave.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(btnSave.isEnabled()) {
				btnSave.setBackground(new Color(18, 110, 130));
				btnSave.setForeground(new Color(216, 227, 231));
				btnSave.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
				}
			}
		});
		btnSave.setBounds(35, 555, 300, 30);
		contentPane.add(btnSave);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(216, 227, 231));
		separator.setForeground(new Color(216, 227, 231));
		separator.setBounds(20, 415, 340, 2);
		contentPane.add(separator);
		
		JScrollPane scrollPaneStudentRecord = new JScrollPane();
		scrollPaneStudentRecord.setBorder(null);
		scrollPaneStudentRecord.setBounds(380, 91, 800, 529);
		contentPane.add(scrollPaneStudentRecord);
		
		tblStudentRecord = new JTable(Main.modelStudentRecord) {
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tblStudentRecord.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblStudentRecord.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tblStudentRecord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Main.isEdit || Main.isDelete) {
					selectedRow = tblStudentRecord.getSelectedRow();
					int row = fetchedIndices[selectedRow];
					
					lblId.setText(Main.modelStudentRecord.getValueAt(row, 0).toString());
					txtLastName.setText(Main.modelStudentRecord.getValueAt(row, 1).toString());
					txtFirstName.setText(Main.modelStudentRecord.getValueAt(row, 2).toString());
					txtMiddleName.setText(Main.modelStudentRecord.getValueAt(row, 3).toString());
					txtAddress.setText(Main.modelStudentRecord.getValueAt(row, 4).toString());
					txtContactNumber.setText(Main.modelStudentRecord.getValueAt(row, 5).toString());
					txtEmailAddress.setText(Main.modelStudentRecord.getValueAt(row, 6).toString());
					txtUsername.setText(Main.modelStudentRecord.getValueAt(row, 7).toString());
					txtPassword.setText(Main.modelStudentRecord.getValueAt(row, 8).toString());
					
					boolean condition = Main.isDelete ? false : true;
					
					txtLastName.setEditable(condition);
					txtFirstName.setEditable(condition);
					txtMiddleName.setEditable(condition);
					txtAddress.setEditable(condition);
					txtContactNumber.setEditable(condition);
					txtEmailAddress.setEditable(condition);
					txtUsername.setEditable(false);
					txtPassword.setEditable(false);
					btnSave.setEnabled(condition);
					chkLockUnlock.setEnabled(true);
					chkLockUnlock.setText("LOCKED");
					chkLockUnlock.setSelected(true);
					btnSave.setEnabled(true);
				}
			}
		});
		scrollPaneStudentRecord.setViewportView(tblStudentRecord);
		
		chkShowPassword = new JCheckBox("Show Password");
		chkShowPassword.setToolTipText("Click to show your password");
		chkShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkShowPassword.isSelected()) {
					txtPassword.setEchoChar((char) 0);
					chkShowPassword.setText("Hide Password");
					chkShowPassword.setToolTipText("Click to hide your password");
				} else {
					txtPassword.setEchoChar('•');
					chkShowPassword.setText("Show Password");
					chkShowPassword.setToolTipText("Click to show your password");
				}
			}
		});
		chkShowPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chkShowPassword.setFocusable(false);
		chkShowPassword.setIconTextGap(7);
		chkShowPassword.setMargin(new Insets(2, 5, 2, 2));
		chkShowPassword.setBackground(new Color(18, 110, 130));
		chkShowPassword.setBorder(null);
		chkShowPassword.setForeground(new Color(216, 227, 231));
		chkShowPassword.setBounds(180, 510, 180, 25);
		contentPane.add(chkShowPassword);
		
		chkLockUnlock = new JCheckBox("Locked");
		chkLockUnlock.setToolTipText("Click to locked");
		chkLockUnlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkLockUnlock.isSelected()) {
					chkLockUnlock.setText("Locked");
					
					txtUsername.setEditable(false);
					txtPassword.setEditable(false);
					chkLockUnlock.setToolTipText("Click to locked");
				} else {
					chkLockUnlock.setText("Unlocked");
					
					txtUsername.setEditable(true);
					txtPassword.setEditable(true);
					chkLockUnlock.setToolTipText("Click to unlocked");
				}
			}
		});
		chkLockUnlock.setOpaque(false);
		chkLockUnlock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chkLockUnlock.setFocusable(false);
		chkLockUnlock.setIconTextGap(7);
		chkLockUnlock.setMargin(new Insets(2, 5, 2, 2));
		chkLockUnlock.setBackground(null);
		chkLockUnlock.setBorder(null);
		chkLockUnlock.setForeground(new Color(216, 227, 231));
		chkLockUnlock.setEnabled(false);
		chkLockUnlock.setSelected(true);
		chkLockUnlock.setBounds(20, 510, 140, 25);
		chkLockUnlock.setVisible(Main.isEdit ? true : false);
		contentPane.add(chkLockUnlock);
		
		// hides the column for password & user type
		Main.hideTableColumns(tblStudentRecord, new int[] {8, 9, 10, 11});
		
		JLabel lblStudentBackground = new JLabel("");
		// Creates an icon object from the local source folder
		ImageIcon studentBackground = new ImageIcon(this.getClass().getResource("studentBackground.png"));
		// Assigns the icon object as the icon image of components
		lblStudentBackground.setIcon(studentBackground);
		lblStudentBackground.setBounds(0, 0, 1200, 640);
		contentPane.add(lblStudentBackground);
		
		// Set the controls to default and filter the table
		refreshControls();
	}
	
	
	// METHODS
	// Method for saving: add, update, delete
	private void save() {
		if (Main.isAdd) {
			if (txtLastName.getText().isBlank() || txtFirstName.getText().isBlank() || txtAddress.getText().isBlank() ||
					txtContactNumber.getText().isBlank() || txtEmailAddress.getText().isBlank() || txtUsername.getText().isBlank() || txtPassword.getText().isBlank()) {
				JOptionPane.showMessageDialog(contentPane, "Please fill up all needed information!");
				txtLastName.requestFocus();
			} else {
				if (isRecordExisting()) {
					JOptionPane.showMessageDialog(contentPane, "Record is already existing!");
					txtLastName.requestFocus();
				} else {
					if (isUsernameExisting()) {
						JOptionPane.showMessageDialog(contentPane, "Username is already existing!");
						txtUsername.requestFocus();
						txtPassword.setText(null);
					} else {
						Object[] newRow = new Object[12];
						
						newRow[0] = Integer.parseInt(lblId.getText());
						newRow[1] = txtLastName.getText().trim();
						newRow[2] = txtFirstName.getText().trim();
						newRow[3] = txtMiddleName.getText().isBlank() ? "N/A" : txtMiddleName.getText().trim();
						newRow[4] = txtAddress.getText().trim();
						newRow[5] = txtContactNumber.getText().trim();
						newRow[6] = txtEmailAddress.getText().trim();
						newRow[7] = txtUsername.getText().trim();
						newRow[8] = txtPassword.getText();
						newRow[9] = "User";
						newRow[10] = 0;
						newRow[11] = 0;
						
						Main.studentId++;
						
						Main.modelStudentRecord.addRow(newRow);
						
						JOptionPane.showMessageDialog(contentPane, "New student/account added!");
						refreshControls();
					}
				}
			}
		} else if (Main.isEdit) {
			if (txtLastName.getText().isBlank() || txtFirstName.getText().isBlank() || txtAddress.getText().isBlank() ||
					txtContactNumber.getText().isBlank() || txtEmailAddress.getText().isBlank() || txtUsername.getText().isBlank() || txtPassword.getText().isBlank()) {
				JOptionPane.showMessageDialog(contentPane, "Please fill up all needed information!");
				txtLastName.requestFocus();
			} else {
				int response = JOptionPane.showConfirmDialog(contentPane, "Are you sure to update this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
				
				if (response == JOptionPane.YES_OPTION) {
					if (isRecordExisting()) {
						JOptionPane.showMessageDialog(contentPane, "Record is already existing!");
						txtLastName.requestFocus();
					} else {
						if (isUsernameExisting()) {
							JOptionPane.showMessageDialog(contentPane, "Username is already existing!");
							txtUsername.requestFocus();
							txtPassword.setText(null);
						} else {
							int row = fetchedIndices[selectedRow];
							
							// Updates the record of the Student record
							Main.modelStudentRecord.setValueAt(txtLastName.getText().trim(), row, 1);
							Main.modelStudentRecord.setValueAt(txtFirstName.getText().trim(), row, 2);
							Main.modelStudentRecord.setValueAt(txtMiddleName.getText().trim(), row, 3);
							Main.modelStudentRecord.setValueAt(txtAddress.getText().trim(), row, 4);
							Main.modelStudentRecord.setValueAt(txtContactNumber.getText().trim(), row, 5);
							Main.modelStudentRecord.setValueAt(txtEmailAddress.getText().trim(), row, 6);
							Main.modelStudentRecord.setValueAt(txtUsername.getText(), row, 7);
							Main.modelStudentRecord.setValueAt(txtPassword.getText(), row, 8);
							
							// Updates the record in transaction requests
							int studentId = Integer.parseInt(lblId.getText());
							
							for (int i = 0; i < Main.modelTransactionRequestsRecord.getRowCount(); i++) {
								int transactStudentId = Integer.parseInt(Main.modelTransactionRequestsRecord.getValueAt(i, 1).toString());
								
								if (studentId == transactStudentId) {
									Main.modelTransactionRequestsRecord.setValueAt(txtLastName.getText().trim(), i, 2);
									Main.modelTransactionRequestsRecord.setValueAt(txtFirstName.getText().trim(), i, 3);
								}
							}
							
							// Updates the record in borrower's transaction
							for (int i = 0; i < Main.modelBorrowersTransactionsRecord.getRowCount(); i++) {
								int borrowersStudentId = Integer.parseInt(Main.modelBorrowersTransactionsRecord.getValueAt(i, 1).toString()) ;
									
								if (studentId == borrowersStudentId) {
									Main.modelBorrowersTransactionsRecord.setValueAt(txtLastName.getText().trim(), i, 2);
									Main.modelBorrowersTransactionsRecord.setValueAt(txtFirstName.getText().trim(), i, 3);
								}
							}
							
							// Updates the record in transaction history
							for (int i = 0; i < Main.modelTransactionHistoryRecord.getRowCount(); i++) {
								int historyStudentId = Integer.parseInt(Main.modelTransactionHistoryRecord.getValueAt(i, 1).toString());
								
								if (studentId == historyStudentId) {
									Main.modelTransactionHistoryRecord.setValueAt(txtLastName.getText().trim(), i, 2);
									Main.modelTransactionHistoryRecord.setValueAt(txtFirstName.getText().trim(), i, 3);
								}
							}
							
							JOptionPane.showMessageDialog(contentPane, "Selected record updated!");
							refreshControls();
						}
					}
				}				
			}
		} else if (Main.isDelete) {
			// Look it up if student is currently have borrowed books, if there is one, you won't be able to delete record
			int row = fetchedIndices[selectedRow];
			
			if (Integer.parseInt(Main.modelStudentRecord.getValueAt(row, 10).toString()) > 0) {
				JOptionPane.showMessageDialog(contentPane, "Unable to delete record! Selected student is currently have borrowed books.");
				refreshControls();
			} else {
				if (Integer.parseInt(Main.modelStudentRecord.getValueAt(row, 10).toString()) > 0) {
					JOptionPane.showMessageDialog(contentPane, "Unable to delete the selected student. Student may have pending request "
							+ "or have not yet returned the borrowed book(s).");
					refreshControls();
				} else {
					int response = JOptionPane.showConfirmDialog(contentPane, "Are you sure to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
					
					if (response == JOptionPane.YES_OPTION) {
						// Move the selected data to another tableModel for deleted student record
						Object[] deletedRow = new Object[Main.modelStudentRecord.getColumnCount()];
						
						for (int i = 0; i < deletedRow.length; i++) {
							deletedRow[i] = Main.modelStudentRecord.getValueAt(row, i);
						}
						
						Main.modelDeletedStudents.addRow(deletedRow);
						
						// then delete it here in modelStudentRecord
						Main.modelStudentRecord.removeRow(row);
						
						JOptionPane.showMessageDialog(contentPane, "Selected student record deleted!");
						refreshControls();
					}
				}	
			}
		}
	}
	
	
	private void refreshControls() {
		btnSave.setBackground(new Color(18, 110, 130));
		btnSave.setForeground(new Color(216, 227, 231));
		btnSave.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		
		lblId.setText(Main.isAdd ? String.valueOf(Main.studentId) : null);
		txtLastName.setText(null);
		txtFirstName.setText(null);
		txtMiddleName.setText(null);
		txtAddress.setText(null);
		txtContactNumber.setText(null);
		txtEmailAddress.setText(null);
		txtUsername.setText(null);
		txtPassword.setText(null);
		txtPassword.setEchoChar('•');
		
		chkLockUnlock.setText("LOCKED");
		chkLockUnlock.setEnabled(false);
		chkLockUnlock.setSelected(true);
		chkShowPassword.setSelected(false);
		
		boolean condition = Main.isAdd ? true : false;
		
		txtLastName.setEditable(condition);
		txtFirstName.setEditable(condition);
		txtMiddleName.setEditable(condition);
		txtAddress.setEditable(condition);
		txtContactNumber.setEditable(condition);
		txtEmailAddress.setEditable(condition);
		txtUsername.setEditable(condition);
		txtPassword.setEditable(condition);
		btnSave.setEnabled(condition);
		btnSave.setText(Main.isDelete ? "DELETE" : "SAVE");
		txtSearchBar.setText(" Search");
		
		txtLastName.requestFocus();
		tblStudentRecord.clearSelection();
		
		// Refresh the filtered table and indices
		doFilter();
	}
	
	
	// Method for checking: if the record is already existing in the model
	private boolean isRecordExisting() {
		String newLastName = txtLastName.getText().toLowerCase().trim();
		String newFirstName = txtFirstName.getText().toLowerCase().trim();
		String newMiddleName = txtMiddleName.getText().toLowerCase().trim();
		
		for (int i = 0; i < Main.modelStudentRecord.getRowCount(); i++) {
			String id = Main.modelStudentRecord.getValueAt(i, 0).toString();
			String lastName = Main.modelStudentRecord.getValueAt(i, 1).toString().toLowerCase();
			String firstName = Main.modelStudentRecord.getValueAt(i, 2).toString().toLowerCase();
			String middleName = Main.modelStudentRecord.getValueAt(i, 3).toString().toLowerCase();
			
			if (lastName.equals(newLastName) && firstName.equals(newFirstName) && middleName.equals(newMiddleName) && 
					!id.equals(lblId.getText())) {
				return true;
			}
		}
		
		return false;
	}
	
	
	// Method for checking: if the username is already used by other students
	private boolean isUsernameExisting() {
		String newUsername = txtUsername.getText().trim();
		
		for (int i = 0; i < Main.modelStudentRecord.getRowCount(); i++) {
			String id = Main.modelStudentRecord.getValueAt(i, 0).toString();
			String username = Main.modelStudentRecord.getValueAt(i, 7).toString();
			
			if (username.equals(newUsername) && !id.equals(lblId.getText())) {
				return true;
			}
		}
		
		return false;
	}
	
	
	// Filter the table and get the indices of the filtered table. using the method filterTable()
	private void doFilter() {
		if (Main.isEdit || Main.isDelete) {
			lblId.setText(null);
			txtLastName.setText(null);
			txtFirstName.setText(null);
			txtMiddleName.setText(null);
			txtAddress.setText(null);
			txtContactNumber.setText(null);
			txtEmailAddress.setText(null);
			txtUsername.setText(null);
			txtPassword.setText(null);
			txtPassword.setEchoChar('•');
			
			chkLockUnlock.setText("LOCKED");
			chkLockUnlock.setEnabled(false);
			chkLockUnlock.setSelected(true);
			chkShowPassword.setSelected(false);
			
			txtLastName.setEditable(false);
			txtFirstName.setEditable(false);
			txtMiddleName.setEditable(false);
			txtAddress.setEditable(false);
			txtContactNumber.setEditable(false);
			txtEmailAddress.setEditable(false);
			txtUsername.setEditable(false);
			txtPassword.setEditable(false);
			btnSave.setEnabled(false);
		}
		
		fetchedIndices = Main.filterTable(tblStudentRecord, Main.modelStudentRecord, txtSearchBar.getText(), 
				"", -1, -1, new int[] {0, 1, 2, 3, 4, 5, 6, 7});
	}
}
