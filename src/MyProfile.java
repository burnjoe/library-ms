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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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

public class MyProfile extends JFrame {

	
	// Components
	private JPanel contentPane;
	private JLabel lblId, lblMyLastName, lblMyFirstName, lblMyMiddleName, lblMyAddress, lblMyContactNumber, lblMyEmailAddress;
	private JPasswordField txtOldPassword, txtNewPassword, txtRetypePassword;
	private JTextField txtOldUsername, txtNewUsername;
	private JButton btnSave;
	private JCheckBox chkShowPassword, chkEditUsername, chkEditPassword;
	
	// Variables
	private int selectedRow = -1;

	
	public MyProfile() {
		setUndecorated(true);
		setBounds(100, 100, 765, 420);
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
		JButton btnExit = new JButton(exitIcon);
		btnExit.setToolTipText("Exit");
		btnExit.setPressedIcon(exitPressedIcon);
		btnExit.setRolloverIcon(exitRolloverIcon);
		btnExit.setRequestFocusEnabled(false);
		btnExit.setBorder(null);
		btnExit.setBackground(new Color(19, 44, 51));
		btnExit.setForeground(new Color(216, 227, 231));
		btnExit.setFocusable(false);
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
		btnExit.setBounds(725, 10, 30, 30);
		contentPane.add(btnExit);
		
		JLabel lblFrameTitle = new JLabel("Edit Profile");
		// Creates an icon object from the local source folder
		ImageIcon lmsframeIcon = new ImageIcon(this.getClass().getResource("LMSFrameIcon.png"));
		// Assigns the icon object as the icon image of components
		lblFrameTitle.setIcon(lmsframeIcon);
		lblFrameTitle.setForeground(new Color(216, 227, 231));
		lblFrameTitle.setIconTextGap(10);
		lblFrameTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFrameTitle.setBounds(10, 10, 250, 30);
		contentPane.add(lblFrameTitle);
		
		chkEditUsername = new JCheckBox("Edit Username");
		chkEditUsername.setToolTipText("Click to edit username");
		chkEditUsername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkEditUsername.isSelected()) {
					txtOldUsername.setEnabled(true);
					txtNewUsername.setEnabled(true);
					chkEditPassword.setEnabled(false);
				} else {
					txtOldUsername.setEnabled(false);
					txtNewUsername.setEnabled(false);
					txtOldUsername.setText(null);
					txtNewUsername.setText(null);
					chkEditPassword.setEnabled(true);
				}
			}
		});
		chkEditUsername.setOpaque(false);
		chkEditUsername.setMargin(new Insets(2, 5, 2, 2));
		chkEditUsername.setIconTextGap(7);
		chkEditUsername.setForeground(new Color(216, 227, 231));
		chkEditUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chkEditUsername.setFocusable(false);
		chkEditUsername.setBorder(null);
		chkEditUsername.setBackground((Color) null);
		chkEditUsername.setBounds(410, 140, 135, 25);
		contentPane.add(chkEditUsername);
		
		txtNewUsername = new JTextField();
		txtNewUsername.setEnabled(false);
		txtNewUsername.setToolTipText("Type your new username");
		txtNewUsername.setOpaque(false);
		txtNewUsername.setText((String) null);
		txtNewUsername.setForeground(new Color(216, 227, 231));
		txtNewUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNewUsername.setColumns(10);
		txtNewUsername.setCaretColor(new Color(216, 227, 231));
		txtNewUsername.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtNewUsername.setBackground(new Color(18, 110, 130));
		txtNewUsername.setBounds(555, 105, 190, 30);
		contentPane.add(txtNewUsername);
		
		txtOldUsername = new JTextField();
		// Button will enable if txtUsername is not empty
		txtOldUsername.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(txtOldUsername.getText().isEmpty()) {
					btnSave.setEnabled(false);
				}else {
					btnSave.setEnabled(true);
				}
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(txtOldUsername.getText().isEmpty()) {
					btnSave.setEnabled(false);
				}else {
					btnSave.setEnabled(true);
				}
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(txtOldUsername.getText().isEmpty()) {
					btnSave.setEnabled(false);
				}else {
					btnSave.setEnabled(true);
				}
			}
		});
		txtOldUsername.setEnabled(false);
		txtOldUsername.setToolTipText("Type your old username");
		txtOldUsername.setOpaque(false);
		txtOldUsername.setText((String) null);
		txtOldUsername.setForeground(new Color(216, 227, 231));
		txtOldUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtOldUsername.setColumns(10);
		txtOldUsername.setCaretColor(new Color(216, 227, 231));
		txtOldUsername.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtOldUsername.setBackground(new Color(18, 110, 130));
		txtOldUsername.setBounds(555, 60, 190, 30);
		contentPane.add(txtOldUsername);
		
		JLabel lblNewUsername = new JLabel("New Username:");
		lblNewUsername.setForeground(new Color(216, 227, 231));
		lblNewUsername.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblNewUsername.setBounds(400, 105, 145, 30);
		contentPane.add(lblNewUsername);
		
		txtRetypePassword = new JPasswordField();
		txtRetypePassword.setEnabled(false);
		txtRetypePassword.setToolTipText("Retype your new password");
		txtRetypePassword.setOpaque(false);
		txtRetypePassword.setForeground(new Color(216, 227, 231));
		txtRetypePassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRetypePassword.setCaretColor(new Color(216, 227, 231));
		txtRetypePassword.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtRetypePassword.setBackground(new Color(18, 110, 130));
		txtRetypePassword.setBounds(555, 285, 190, 30);
		contentPane.add(txtRetypePassword);
		
		JLabel lblRetypePassword = new JLabel("Retype Password:");
		lblRetypePassword.setForeground(new Color(216, 227, 231));
		lblRetypePassword.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblRetypePassword.setBounds(400, 285, 145, 30);
		contentPane.add(lblRetypePassword);
		
		JLabel lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setForeground(new Color(216, 227, 231));
		lblNewPassword.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblNewPassword.setBounds(400, 240, 145, 30);
		contentPane.add(lblNewPassword);
		
		txtNewPassword = new JPasswordField();
		txtNewPassword.setEnabled(false);
		txtNewPassword.setToolTipText("Type your new password");
		txtNewPassword.setOpaque(false);
		txtNewPassword.setForeground(new Color(216, 227, 231));
		txtNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNewPassword.setCaretColor(new Color(216, 227, 231));
		txtNewPassword.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtNewPassword.setBackground(new Color(18, 110, 130));
		txtNewPassword.setBounds(555, 240, 190, 30);
		contentPane.add(txtNewPassword);
		
		
		btnSave = new JButton("SAVE");
		btnSave.setToolTipText("Click to save your edit");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		btnSave.setEnabled(false);
		btnSave.setFocusable(false);
		btnSave.setBackground(new Color(19, 44, 51));
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
				btnSave.setBackground(new Color(19, 44, 51));
				btnSave.setForeground(new Color(216, 227, 231));
				btnSave.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
				}
			}
		});
		btnSave.setBounds(415, 370, 315, 30);
		contentPane.add(btnSave);
		
		
		JLabel lblStudentId = new JLabel("Student ID:");
		lblStudentId.setForeground(new Color(216, 227, 231));
		lblStudentId.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblStudentId.setBounds(20, 60, 140, 30);
		contentPane.add(lblStudentId);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setForeground(new Color(216, 227, 231));
		lblLastName.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblLastName.setBounds(20, 105, 140, 30);
		contentPane.add(lblLastName);
		
		JLabel lblMiddleName = new JLabel("Middle Name:");
		lblMiddleName.setForeground(new Color(216, 227, 231));
		lblMiddleName.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblMiddleName.setBounds(20, 195, 140, 30);
		contentPane.add(lblMiddleName);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setForeground(new Color(216, 227, 231));
		lblFirstName.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblFirstName.setBounds(20, 150, 140, 30);
		contentPane.add(lblFirstName);
		
		JLabel lblContactNumber = new JLabel("Contact Number:");
		lblContactNumber.setForeground(new Color(216, 227, 231));
		lblContactNumber.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblContactNumber.setBounds(20, 285, 140, 30);
		contentPane.add(lblContactNumber);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setForeground(new Color(216, 227, 231));
		lblAddress.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblAddress.setBounds(20, 240, 140, 30);
		contentPane.add(lblAddress);
		
		JLabel lblEmail = new JLabel("Email Address:");
		lblEmail.setForeground(new Color(216, 227, 231));
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblEmail.setBounds(20, 330, 140, 30);
		contentPane.add(lblEmail);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(216, 227, 231));
		separator_2.setBackground(new Color(216, 227, 231));
		separator_2.setBounds(400, 355, 345, 2);
		contentPane.add(separator_2);
		
		JLabel lblOldUsername = new JLabel("Old Username:");
		lblOldUsername.setForeground(new Color(216, 227, 231));
		lblOldUsername.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblOldUsername.setBounds(400, 60, 145, 30);
		contentPane.add(lblOldUsername);
		
		lblId = new JLabel((String) null);
		lblId.setToolTipText("Student ID");
		lblId.setForeground(new Color(216, 227, 231));
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		lblId.setBackground(new Color(18, 110, 130));
		lblId.setBounds(170, 60, 190, 30);
		contentPane.add(lblId);
		
		lblMyLastName = new JLabel();
		lblMyLastName.setToolTipText("Last Name");
		lblMyLastName.setForeground(new Color(216, 227, 231));
		lblMyLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMyLastName.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		lblMyLastName.setBackground(new Color(18, 110, 130));
		lblMyLastName.setBounds(170, 105, 190, 30);
		contentPane.add(lblMyLastName);
		
		lblMyFirstName = new JLabel();
		lblMyFirstName.setToolTipText("First Name");
		lblMyFirstName.setForeground(new Color(216, 227, 231));
		lblMyFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMyFirstName.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		lblMyFirstName.setBackground(new Color(18, 110, 130));
		lblMyFirstName.setBounds(170, 150, 190, 30);
		contentPane.add(lblMyFirstName);
		
		lblMyMiddleName = new JLabel();
		lblMyMiddleName.setToolTipText("Middle Name");
		lblMyMiddleName.setForeground(new Color(216, 227, 231));
		lblMyMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMyMiddleName.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		lblMyMiddleName.setBackground(new Color(18, 110, 130));
		lblMyMiddleName.setBounds(170, 195, 190, 30);
		contentPane.add(lblMyMiddleName);
		
		lblMyAddress = new JLabel();
		lblMyAddress.setToolTipText("Address");
		lblMyAddress.setForeground(new Color(216, 227, 231));
		lblMyAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMyAddress.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		lblMyAddress.setBackground(new Color(18, 110, 130));
		lblMyAddress.setBounds(170, 240, 190, 30);
		contentPane.add(lblMyAddress);
		
		lblMyContactNumber = new JLabel();
		lblMyContactNumber.setToolTipText("Contact Number");
		lblMyContactNumber.setForeground(new Color(216, 227, 231));
		lblMyContactNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMyContactNumber.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		lblMyContactNumber.setBackground(new Color(18, 110, 130));
		lblMyContactNumber.setBounds(170, 285, 190, 30);
		contentPane.add(lblMyContactNumber);
		
		lblMyEmailAddress = new JLabel();
		lblMyEmailAddress.setToolTipText("Email Address");
		lblMyEmailAddress.setForeground(new Color(216, 227, 231));
		lblMyEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMyEmailAddress.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		lblMyEmailAddress.setBackground(new Color(18, 110, 130));
		lblMyEmailAddress.setBounds(170, 330, 190, 30);
		contentPane.add(lblMyEmailAddress);
		
		JLabel lblOldPassword = new JLabel("Old Password:");
		lblOldPassword.setForeground(new Color(216, 227, 231));
		lblOldPassword.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblOldPassword.setBounds(400, 195, 145, 30);
		contentPane.add(lblOldPassword);
		
		chkShowPassword = new JCheckBox("Show Password");
		chkShowPassword.setEnabled(false);
		chkShowPassword.setToolTipText("Click to show your password");
		chkShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkShowPassword.isSelected()) {
					txtOldPassword.setEchoChar((char) 0);
					txtNewPassword.setEchoChar((char) 0);
					txtRetypePassword.setEchoChar((char) 0);
					chkShowPassword.setText("Hide Password");
					chkShowPassword.setToolTipText("Click to hide your password");
				} else {
					txtOldPassword.setEchoChar('•');
					txtNewPassword.setEchoChar('•');
					txtRetypePassword.setEchoChar('•');
					chkShowPassword.setText("Show Password");
					chkShowPassword.setToolTipText("Click to show your password");
				}
			}
		});
		
		txtOldPassword = new JPasswordField();
		// Button will enable if txtUsername is not empty
		txtOldPassword.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(txtOldPassword.getText().isEmpty()) {
					btnSave.setEnabled(false);
				}else {
					btnSave.setEnabled(true);
				}
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(txtOldPassword.getText().isEmpty()) {
					btnSave.setEnabled(false);
				}else {
					btnSave.setEnabled(true);
				}
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(txtOldPassword.getText().isEmpty()) {
					btnSave.setEnabled(false);
				}else {
					btnSave.setEnabled(true);
				}
			}
		});
		txtOldPassword.setEnabled(false);
		txtOldPassword.setToolTipText("Type your old password");
		txtOldPassword.setOpaque(false);
		txtOldPassword.setForeground(new Color(216, 227, 231));
		txtOldPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtOldPassword.setCaretColor(new Color(216, 227, 231));
		txtOldPassword.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtOldPassword.setBackground(new Color(18, 110, 130));
		txtOldPassword.setBounds(555, 195, 190, 30);
		contentPane.add(txtOldPassword);
		
		chkShowPassword.setOpaque(false);
		chkShowPassword.setMargin(new Insets(2, 5, 2, 2));
		chkShowPassword.setIconTextGap(7);
		chkShowPassword.setForeground(new Color(216, 227, 231));
		chkShowPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chkShowPassword.setFocusable(false);
		chkShowPassword.setBorder(null);
		chkShowPassword.setBackground(null);
		chkShowPassword.setBounds(565, 320, 180, 25);
		contentPane.add(chkShowPassword);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(216, 227, 231));
		separator_1.setBackground(new Color(216, 227, 231));
		separator_1.setBounds(400, 180, 345, 2);
		contentPane.add(separator_1);
		
		chkEditPassword = new JCheckBox("Edit Password");
		chkEditPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkEditPassword.isSelected()) {
					txtOldPassword.setEnabled(true);
					txtNewPassword.setEnabled(true);
					txtRetypePassword.setEnabled(true);
					
					chkShowPassword.setEnabled(true);
					chkEditUsername.setEnabled(false);
				} else {
					txtOldPassword.setEnabled(false);
					txtNewPassword.setEnabled(false);
					txtRetypePassword.setEnabled(false);
					txtOldPassword.setText(null);
					txtNewPassword.setText(null);
					txtRetypePassword.setText(null);
					
					chkShowPassword.setEnabled(false);
					chkShowPassword.setSelected(false);
					txtNewPassword.setEchoChar('•');
					txtOldPassword.setEchoChar('•');
					txtRetypePassword.setEchoChar('•');
					chkEditUsername.setEnabled(true);
				}
			}
		});
		chkEditPassword.setToolTipText("Click to edit password");
		chkEditPassword.setOpaque(false);
		chkEditPassword.setMargin(new Insets(2, 5, 2, 2));
		chkEditPassword.setIconTextGap(7);
		chkEditPassword.setForeground(new Color(216, 227, 231));
		chkEditPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chkEditPassword.setFocusable(false);
		chkEditPassword.setBorder(null);
		chkEditPassword.setBackground((Color) null);
		chkEditPassword.setBounds(410, 320, 135, 25);
		contentPane.add(chkEditPassword);
		
		// Creates an icon object from the local source folder
		ImageIcon myprofileBackground = new ImageIcon(this.getClass().getResource("myprofileBackground.png"));
		JLabel lblMyProfileBackground = new JLabel("");
		// Assigns the icon object as the icon image of components
		lblMyProfileBackground.setIcon(myprofileBackground);
		lblMyProfileBackground.setBounds(0, 0, 765, 420);
		contentPane.add(lblMyProfileBackground);
		
		// Display the student's information in labels
		displayInformation();
	}
	
	
	// METHOD
	// Set the controls back to default
	private void refreshControls() {
		txtOldUsername.setText(null);
		txtNewUsername.setText(null);
		txtOldUsername.setEnabled(false);
		txtNewUsername.setEnabled(false);
		
		txtOldPassword.setText(null);
		txtNewPassword.setText(null);
		txtRetypePassword.setText(null);
		txtOldPassword.setEnabled(false);
		txtNewPassword.setEnabled(false);
		txtRetypePassword.setEnabled(false);
		
		chkEditUsername.setSelected(false);
		chkEditPassword.setSelected(false);
		chkShowPassword.setSelected(false);
		txtNewPassword.setEchoChar('•');
		txtOldPassword.setEchoChar('•');
		txtRetypePassword.setEchoChar('•');
		
		btnSave.setEnabled(false);
	}
	
	
	// Display the student's information in labels
	private void displayInformation() {
		lblId.setText(Main.modelStudentRecord.getValueAt(Main.currentAccountIndex, 0).toString());
		lblMyLastName.setText(Main.modelStudentRecord.getValueAt(Main.currentAccountIndex, 1).toString());
		lblMyFirstName.setText(Main.modelStudentRecord.getValueAt(Main.currentAccountIndex, 2).toString());
		lblMyMiddleName.setText(Main.modelStudentRecord.getValueAt(Main.currentAccountIndex, 3).toString());
		lblMyAddress.setText(Main.modelStudentRecord.getValueAt(Main.currentAccountIndex, 4).toString());
		lblMyContactNumber.setText(Main.modelStudentRecord.getValueAt(Main.currentAccountIndex, 5).toString());
		lblMyEmailAddress.setText(Main.modelStudentRecord.getValueAt(Main.currentAccountIndex, 6).toString());
	}
	
	
	// Method for saving updated username/password
	private void save() {
		if (chkEditUsername.isSelected()) {
			if (txtOldUsername.getText().isBlank() || txtNewUsername.getText().isBlank()) {
				JOptionPane.showMessageDialog(contentPane, "Please fill up username!");
				txtOldUsername.requestFocus();
			} else {
				if (Main.modelStudentRecord.getValueAt(Main.currentAccountIndex, 7).toString().equals(txtOldUsername.getText())) {
					int response = JOptionPane.showConfirmDialog(contentPane, "Are you sure to change your username?", "Confirmation", JOptionPane.YES_NO_OPTION);
					
					if (response == JOptionPane.YES_OPTION) {
						if (isUsernameExisting()) {
							JOptionPane.showMessageDialog(contentPane, "Username is already existing!");
							txtNewUsername.requestFocus();
						} else {
							Main.modelStudentRecord.setValueAt(txtNewUsername.getText().trim(), Main.currentAccountIndex, 7);
							
							JOptionPane.showMessageDialog(contentPane, "Your username has been changed successfully!");
							refreshControls();
						}
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Invalid username!");
					txtOldUsername.setText(null);
					txtOldUsername.requestFocus();
					txtOldPassword.setText(null);
				}			
			}
		} else {
			if (txtOldPassword.getText().isBlank() || txtNewPassword.getText().isBlank() || txtRetypePassword.getText().isBlank()) {
					JOptionPane.showMessageDialog(contentPane, "Please fill up password!");
					txtOldPassword.requestFocus();
			} else {
				if (Main.modelStudentRecord.getValueAt(Main.currentAccountIndex, 8).toString().equals(txtOldPassword.getText())) {
					if (txtNewPassword.getText() != txtRetypePassword.getText()) {
						int response = JOptionPane.showConfirmDialog(contentPane, "Are you sure to change your password?", "Confirmation", JOptionPane.YES_NO_OPTION);
						
						if (response == JOptionPane.YES_OPTION) {
							Main.modelStudentRecord.setValueAt(txtNewPassword.getText(), Main.currentAccountIndex, 8);
							
							JOptionPane.showMessageDialog(contentPane, "Your password has been changed successfully!");
							refreshControls();
						}
					} else {
						JOptionPane.showMessageDialog(contentPane, "Retyped password did not match!");
						txtRetypePassword.setText(null);
						txtRetypePassword.requestFocus();
					}
					
				} else {
					JOptionPane.showMessageDialog(contentPane, "Invalid password!");
					txtOldPassword.requestFocus();
					txtOldPassword.setText(null);
				}
			}
		}
		
		// Design
		btnSave.setBackground(new Color(19, 44, 51));
		btnSave.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnSave.setForeground(new Color(216, 227, 231));
	}
	
	
	// Method for checking: if the username is already used by other students
	private boolean isUsernameExisting() {
		String newUsername = txtNewUsername.getText().trim();
		
		for (int i = 0; i < Main.modelStudentRecord.getRowCount(); i++) {
			String id = Main.modelStudentRecord.getValueAt(i, 0).toString();
			String username = Main.modelStudentRecord.getValueAt(i, 7).toString();
			
			if (username.equals(newUsername) && !id.equals(lblId.getText())) {
				return true;
			}
		}
		
		return false;
	}
}
