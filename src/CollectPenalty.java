import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;

public class CollectPenalty extends JFrame {

	private JPanel contentPane;
	private JTextField txtInputFee;
	private JLabel lblChange;
	private JLabel lblLateFee;
	private JButton btnCollectFee;
	
	private int row = TransactionRequests.fetchedIndices[TransactionRequests.selectedRow];
	private double lateFee = Integer.parseInt(Main.modelTransactionRequestsRecord.getValueAt(row, 11).toString());
	private JSeparator separator;
	
	public CollectPenalty() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 240, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtInputFee = new JTextField();
		txtInputFee.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtInputFee.setForeground(new Color(216, 227, 231));
		txtInputFee.setBackground(new Color(18, 110, 130));
		txtInputFee.setOpaque(false);
		txtInputFee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtInputFee.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 
				
				if (isNumeric(txtInputFee.getText())) {
					double input = Double.parseDouble(txtInputFee.getText());
					
					if (input >= lateFee) {
						lblChange.setText("Change:   " + (input - lateFee));
						btnCollectFee.setEnabled(true);
					} else {
						lblChange.setText("Change:   0");
						btnCollectFee.setEnabled(false);
					}
				} else {
					lblChange.setText("Change:   0");
					btnCollectFee.setEnabled(false);
				}
			}
		});
		txtInputFee.setBounds(20, 115, 200, 30);
		contentPane.add(txtInputFee);
		txtInputFee.setColumns(10);
		
		lblLateFee = new JLabel("Late Fee:   " + lateFee);
		lblLateFee.setForeground(new Color(216, 227, 231));
		lblLateFee.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblLateFee.setBounds(20, 70, 200, 30);
		contentPane.add(lblLateFee);
		
		lblChange = new JLabel("Change:   0.0");
		lblChange.setForeground(new Color(216, 227, 231));
		lblChange.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblChange.setBounds(20, 160, 200, 30);
		contentPane.add(lblChange);
		
		btnCollectFee = new JButton("Collect Fee");
		btnCollectFee.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnCollectFee.setBackground(new Color(18, 110, 130));
		btnCollectFee.setForeground(new Color(216, 227, 231));
		btnCollectFee.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnCollectFee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
					btnCollectFee.setBackground(new Color(81, 196, 211));
					btnCollectFee.setForeground(new Color(19, 44, 51));
					btnCollectFee.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btnCollectFee.setBackground(new Color(18, 110, 130));
					btnCollectFee.setForeground(new Color(216, 227, 231));
					btnCollectFee.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
				}
		});
		btnCollectFee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionRequests.returnBooks();
				Main.frameTransactionRequests.setEnabled(true);
				dispose();
			}
		});
		btnCollectFee.setBounds(35, 205, 170, 30);
		btnCollectFee.setEnabled(false);
		contentPane.add(btnCollectFee);		
		
		// Creates an icon object from the local source folder
		ImageIcon exitIcon = new ImageIcon(this.getClass().getResource("exitIcon.png"));
		ImageIcon exitPressedIcon = new ImageIcon(this.getClass().getResource("exitPressedIcon.png"));
		ImageIcon exitRolloverIcon = new ImageIcon(this.getClass().getResource("exitRolloverIcon.png"));
		// Assigns the icon object as the icon image of components
		JButton btnExit = new JButton(exitIcon);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.frameTransactionRequests.setEnabled(true);
				dispose();
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
		btnExit.setBounds(200, 10, 30, 30);
		contentPane.add(btnExit);
		
		JLabel lblFrameTitle = new JLabel("Collect Penalty");
		// Creates an icon object from the local source folder
		ImageIcon lmsframeIcon = new ImageIcon(this.getClass().getResource("LMSFrameIcon.png"));
		// Assigns the icon object as the icon image of components
		lblFrameTitle.setIcon(lmsframeIcon);
		lblFrameTitle.setIconTextGap(10);
		lblFrameTitle.setForeground(new Color(216, 227, 231));
		lblFrameTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFrameTitle.setBounds(10, 10, 180, 30);
		contentPane.add(lblFrameTitle);
		
		separator = new JSeparator();
		separator.setForeground(new Color(216, 227, 231));
		separator.setBackground(new Color(216, 227, 231));
		separator.setBounds(20, 195, 200, 2);
		contentPane.add(separator);
		
		// Creates an icon object from the local source folder
		ImageIcon collectpenaltyBackground = new ImageIcon(this.getClass().getResource("collectpenaltyBackground.png"));
		JLabel lblCollectPenaltyBackground = new JLabel("");
		// Assigns the icon object as the icon image of components
		lblCollectPenaltyBackground.setIcon(collectpenaltyBackground);
		lblCollectPenaltyBackground.setBounds(0, 0, 240, 250);
		contentPane.add(lblCollectPenaltyBackground);
	}
	
	
	private boolean isNumeric(String string) {
		try {
			double number = Double.parseDouble(string);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
