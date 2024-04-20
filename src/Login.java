import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {
	
	private JPanel contentPane;
	private JButton btnExit;
	private JPanel panelLogin;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JCheckBox chkToggleShow;
	private JCheckBox chkCaptcha;
	private JButton btnLogin;
	
		
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("LMSIcon.png")));
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(18, 110, 130));
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
		btnExit.setPressedIcon(exitPressedIcon);
		btnExit.setRolloverIcon(exitRolloverIcon);
		btnExit.setRequestFocusEnabled(false);
		btnExit.setBorder(null);
		btnExit.setBackground(new Color(19, 44, 51));
		btnExit.setForeground(new Color(216, 227, 231));
		btnExit.setFocusable(false);
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
		btnExit.setBounds(760, 10, 30, 30);
		contentPane.add(btnExit);
		
		JLabel lblDragBar = new JLabel("");
		lblDragBar.setBounds(0, 0, 758, 45);
		contentPane.add(lblDragBar);
		
		panelLogin = new JPanel();
		panelLogin.setLayout(null);
		panelLogin.setBackground(new Color(19, 44, 51));
		panelLogin.setBounds(380, 0, 420, 500);
		contentPane.add(panelLogin);
		
		JLabel lblTitle = new JLabel("Login System");
		lblTitle.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTitle.setForeground(new Color(216, 227, 231));
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblTitle.setBounds(60, 62, 300, 35);
		panelLogin.add(lblTitle);
		
		JSeparator separatorLogin = new JSeparator();
		separatorLogin.setBackground(new Color(216, 227, 231));
		separatorLogin.setBounds(60, 97, 300, 5);
		panelLogin.add(separatorLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(216, 227, 231));
		lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblUsername.setBackground(new Color(230, 230, 250));
		lblUsername.setBounds(60, 102, 118, 30);
		panelLogin.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setToolTipText("Type your username");
		txtUsername.setCaretColor(new Color(216, 227, 231));
		txtUsername.setBackground(new Color(19, 44, 51));
		txtUsername.setForeground(new Color(216, 227, 231));
		txtUsername.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
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
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUsername.setColumns(10);
		txtUsername.setBounds(60, 142, 300, 30);
		panelLogin.add(txtUsername);
		
		JSeparator separatorLogin_1 = new JSeparator();
		separatorLogin_1.setBackground(new Color(216, 227, 231));
		separatorLogin_1.setBounds(60, 187, 300, 5);
		panelLogin.add(separatorLogin_1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(216, 227, 231));
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblPassword.setBounds(60, 207, 150, 30);
		panelLogin.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setToolTipText("Type your password");
		txtPassword.setCaretColor(new Color(216, 227, 231));
		txtPassword.setForeground(new Color(216, 227, 231));
		txtPassword.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtPassword.setBackground(new Color(19, 44, 51));
		txtPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtPassword.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtPassword.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPassword.setColumns(10);
		txtPassword.setBounds(60, 247, 225, 30);
		panelLogin.add(txtPassword);
		
		chkToggleShow = new JCheckBox("SHOW");
		chkToggleShow.setToolTipText("Click to show your password");
		chkToggleShow.setIconTextGap(7);
		chkToggleShow.setMargin(new Insets(3, 5, 3, 3));
		chkToggleShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chkToggleShow.isSelected()) {
					txtPassword.setEchoChar((char) 0);
					chkToggleShow.setText("HIDE");
					chkToggleShow.setToolTipText("Click to hide your password");
				}else {
					txtPassword.setEchoChar('•');
					chkToggleShow.setText("SHOW");
					chkToggleShow.setToolTipText("Click to show your password");
				}
			}
		});
		chkToggleShow.setForeground(new Color(216, 227, 231));
		chkToggleShow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chkToggleShow.setFocusable(false);
		chkToggleShow.setBackground(new Color(19, 44, 51));
		chkToggleShow.setBounds(285, 250, 75, 25);
		panelLogin.add(chkToggleShow);
		
		JSeparator separatorLogin_2 = new JSeparator();
		separatorLogin_2.setBackground(new Color(216, 227, 231));
		separatorLogin_2.setBounds(60, 292, 300, 5);
		panelLogin.add(separatorLogin_2);
		
		chkCaptcha = new JCheckBox("I'm not a robot");
		chkCaptcha.setToolTipText("Check if you are not a robot");
		chkCaptcha.setRequestFocusEnabled(false);
		chkCaptcha.setMargin(new Insets(10, 10, 10, 10));
		chkCaptcha.setIconTextGap(10);
		chkCaptcha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chkCaptcha.setBackground(new Color(216, 227, 231));
		chkCaptcha.setBounds(60, 312, 150, 50);
		panelLogin.add(chkCaptcha);
		
		btnLogin = new JButton("LOGIN");
		btnLogin.setToolTipText("Click to login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText().trim();
				String password = txtPassword.getText();
				
				int modelUserId = 0;
				String modelFirstName = null;
				String modelUsername = null;
				String modelPassword = null;
				String modelUserType = null;
				
				// Search through the table model if username and password is existing, once found break the loop
				for (int i = 0; i < Main.modelStudentRecord.getRowCount(); i++) {
					modelUserId = Integer.parseInt(Main.modelStudentRecord.getValueAt(i, 0).toString());
					modelFirstName = Main.modelStudentRecord.getValueAt(i, 2).toString();
					modelUsername = Main.modelStudentRecord.getValueAt(i, 7).toString();
					modelPassword = Main.modelStudentRecord.getValueAt(i, 8).toString();
					modelUserType = Main.modelStudentRecord.getValueAt(i, 9).toString();
					
					if (username.equals(modelUsername) && password.equals(modelPassword)) {
						Main.currentAccountIndex = i;
						break;
					}
				}		
				
				if (username.equals(Main.modelAdmin.getValueAt(0, 0)) && password.equals(Main.modelAdmin.getValueAt(0, 1).toString()) && 
						Main.modelAdmin.getValueAt(0, 2).toString().equals("Admin")) {
					Main.currentAccountID = 1;
					Main.currentUserType = "Admin";
					
					JOptionPane.showMessageDialog(contentPane, "Welcome Admin!");
					
					EventQueue.invokeLater(new Runnable() {
						public void run() {	
							try {
								Main.frame = new Main();
								Main.frame.setExtendedState(Main.frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
								Main.frame.setLocationRelativeTo(null);
								Main.frame.setVisible(true);
								dispose();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				} else if (username.equals(modelUsername) && password.equals(modelPassword)) {
					JOptionPane.showMessageDialog(contentPane, "Welcome " + modelFirstName + "!");	
					
					Main.currentAccountID = modelUserId;
					Main.currentUserType = modelUserType;
					
					// Loads the main frame if username and password does exist and correct
					EventQueue.invokeLater(new Runnable() {
						public void run() {	
							try {
								Main.frame = new Main();
								Main.frame.setExtendedState(Main.frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
								Main.frame.setLocationRelativeTo(null);
								Main.frame.setVisible(true);
								dispose();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				} else {
					if (username.isBlank() || password.isBlank()) {
						JOptionPane.showMessageDialog(contentPane, "Please enter username and password!");
					} else {
						JOptionPane.showMessageDialog(contentPane, "Invalid login credentials!");
					}
					
					txtPassword.setText(null);
					chkCaptcha.setSelected(false);
					txtUsername.requestFocus();
				}
			}
		});
		btnLogin.setBackground(new Color(19, 44, 51));
		btnLogin.setForeground(new Color(216, 227, 231));
		btnLogin.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBackground(new Color(81, 196, 211));
				btnLogin.setForeground(new Color(19, 44, 51));
				btnLogin.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(new Color(19, 44, 51));
				btnLogin.setForeground(new Color(216, 227, 231));
				btnLogin.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		btnLogin.setFocusable(false);
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnLogin.setBounds(85, 392, 250, 30);
		panelLogin.add(btnLogin);
		
		JSeparator separatorLogin_3 = new JSeparator();
		separatorLogin_3.setBackground(new Color(216, 227, 231));
		separatorLogin_3.setBounds(60, 377, 300, 5);
		panelLogin.add(separatorLogin_3);
		
		JLabel lblBackgroundLogin = new JLabel("");
		// Creates an icon object from the local source folder
		ImageIcon LoginBackground = new ImageIcon(this.getClass().getResource("LoginRightPanel.png"));
		// Assigns the icon object as the icon image of components
		lblBackgroundLogin.setIcon(LoginBackground);
		lblBackgroundLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBackgroundLogin.setBounds(0, 0, 421, 500);
		panelLogin.add(lblBackgroundLogin);
		
		JLabel lblBackground = new JLabel("");
		// Creates an icon object from the local source folder
		ImageIcon LoginWallpaper = new ImageIcon(this.getClass().getResource("LoginWallpaper.png"));
		// Assigns the icon object as the icon image of components
		lblBackground.setIcon(LoginWallpaper);
		lblBackground.setBounds(0, 0, 800, 500);
		contentPane.add(lblBackground);
	}
}
