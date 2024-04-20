import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ListSelectionModel;

public class BookCategories extends JFrame {

	
	// Components
	private JPanel contentPane;
	private JLabel lblId;
	private JTextField txtCategoryName, txtSearchBar;
	private JTable tblBookCategory;
	private JButton btnSave, btnExit;
	
	// Variables
	private int selectedRow = -1;
	private int[] fetchedIndices;

	
	public BookCategories() {
		setUndecorated(true);
		setBounds(100, 100, 700, 360);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFrameTitle = new JLabel(Main.isAdd ? "Add Book" : (Main.isEdit ? "Edit Book" : (Main.isDelete ? "Delete Book" : null)));
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
		btnExit.setBounds(660, 10, 30, 30);
		contentPane.add(btnExit);
		
		txtSearchBar = new JTextField();
		txtSearchBar.setToolTipText("Search bar");
		txtSearchBar.setOpaque(false);
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
		txtSearchBar.setBounds(410, 49, 200, 30);
		contentPane.add(txtSearchBar);
		
		JLabel lblSearchIcon = new JLabel("");
		lblSearchIcon.setToolTipText("Search");
		// Creates an icon object from the local source folder
		ImageIcon childframesearchIcon = new ImageIcon(this.getClass().getResource("childframesearchIcon.png"));
		// Assigns the icon object as the icon image of components
		lblSearchIcon.setIcon(childframesearchIcon);
		lblSearchIcon.setBounds(370, 49, 30, 30);
		contentPane.add(lblSearchIcon);
		
		JScrollPane scrollPaneBookCategory = new JScrollPane();
		scrollPaneBookCategory.setBounds(370, 90, 310, 250);
		contentPane.add(scrollPaneBookCategory);
		
		tblBookCategory = new JTable(Main.modelBookCategoryRecord){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tblBookCategory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblBookCategory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tblBookCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Main.isEdit || Main.isDelete) {
					selectedRow = tblBookCategory.getSelectedRow();
					int row = fetchedIndices[selectedRow];
					doFilter();
					
					lblId.setText(Main.modelBookCategoryRecord.getValueAt(row, 0).toString());
					txtCategoryName.setText(Main.modelBookCategoryRecord.getValueAt(row, 1).toString());
					
					txtCategoryName.setEditable(true);
					txtCategoryName.requestFocus();
					btnSave.setEnabled(true);
				}
			}
		});
		scrollPaneBookCategory.setViewportView(tblBookCategory);
		
		JLabel lblCategoryId = new JLabel("Category ID:");
		lblCategoryId.setForeground(new Color(216, 227, 231));
		lblCategoryId.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblCategoryId.setBounds(20, 100, 130, 30);
		contentPane.add(lblCategoryId);
		
		JLabel lblCategoryName = new JLabel("Category Name:");
		lblCategoryName.setForeground(new Color(216, 227, 231));
		lblCategoryName.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblCategoryName.setBounds(20, 145, 130, 30);
		contentPane.add(lblCategoryName);
		
		lblId = new JLabel(Main.isAdd ? String.valueOf(Main.bookCategoryId) : null);
		lblId.setToolTipText("Category ID");
		lblId.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setForeground(new Color(216, 227, 231));
		lblId.setBounds(160, 100, 190, 30);
		contentPane.add(lblId);
		
		txtCategoryName = new JTextField();
		txtCategoryName.setToolTipText("Type the category name");
		txtCategoryName.setOpaque(false);
		txtCategoryName.setCaretColor(new Color(216, 227, 231));
		txtCategoryName.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtCategoryName.setBackground(new Color(18, 110, 130));
		txtCategoryName.setForeground(new Color(216, 227, 231));
		txtCategoryName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCategoryName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtCategoryName.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtCategoryName.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		txtCategoryName.setColumns(10);
		txtCategoryName.setBounds(160, 145, 190, 30);
		contentPane.add(txtCategoryName);
		
		btnSave = new JButton("SAVE");
		btnSave.setToolTipText("Click to save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		btnSave.setFocusable(false);
		btnSave.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnSave.setBackground(new Color(18, 110, 130));
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
		btnSave.setBounds(35, 205, 300, 30);
		contentPane.add(btnSave);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(216, 227, 231));
		separator.setBackground(new Color(216, 227, 231));
		separator.setBounds(20, 190, 330, 2);
		contentPane.add(separator);
		
		JLabel lblCategoryBackground = new JLabel("");
		// Creates an icon object from the local source folder
		ImageIcon categoryBackground = new ImageIcon(this.getClass().getResource("categoryBackground.png"));
		// Assigns the icon object as the icon image of components
		lblCategoryBackground.setIcon(categoryBackground);
		lblCategoryBackground.setBounds(0, 0, 700, 360);
		contentPane.add(lblCategoryBackground);
		
		// Set the controls that corresponds to the current operation 
		refreshControls();
	}
	
	
	// METHODS:
	private void save() {		
		if (Main.isAdd) {	
			if (txtCategoryName.getText().isBlank()) {
				JOptionPane.showMessageDialog(contentPane, "Please fill up all needed information!");
				txtCategoryName.requestFocus();
			} else {
				if (isRecordExisting()) {
					JOptionPane.showMessageDialog(contentPane, "Book category name is already existing!");
					txtCategoryName.requestFocus();
				} else {
					Object[] newRow = new Object[3];
					
					newRow[0] = Integer.parseInt(lblId.getText());
					newRow[1] = txtCategoryName.getText().trim();
					newRow[2] = 0;
					
					Main.bookCategoryId++;
					
					Main.modelBookCategoryRecord.addRow(newRow);
					
					JOptionPane.showMessageDialog(contentPane, "New book category added!");
					refreshControls();
				}
			}
			
		} else if (Main.isEdit) {
			int row = fetchedIndices[selectedRow];
			
			if (txtCategoryName.getText().isBlank()) {
				JOptionPane.showMessageDialog(contentPane, "Please fill up all needed information!");
				txtCategoryName.requestFocus();
			} else {
				// Unable to edit selected category if there are books under that category
				if (Integer.parseInt(Main.modelBookCategoryRecord.getValueAt(row, 2).toString()) > 0) {
					JOptionPane.showMessageDialog(contentPane, "Unable to edit the selected book category! There are books under this category.");
					txtCategoryName.requestFocus();
				} else {
					int response = JOptionPane.showConfirmDialog(contentPane, "Are you sure to update this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
					
					if (response == JOptionPane.YES_OPTION) {
						if (isRecordExisting()) {
							JOptionPane.showMessageDialog(contentPane, "Book category name is already existing!");
							txtCategoryName.requestFocus();
						} else {
							Main.modelBookCategoryRecord.setValueAt(txtCategoryName.getText().trim(), row, 1);
							
							JOptionPane.showMessageDialog(contentPane, "Selected book category updated!");
							refreshControls();
						}
					}
				}
			}
			
		} else if (Main.isDelete) {
			int row = fetchedIndices[selectedRow];
			
			if (Integer.parseInt(Main.modelBookCategoryRecord.getValueAt(row, 2).toString()) > 0) {
				JOptionPane.showMessageDialog(contentPane, "Unable to delete the selected book category! There are books under this category.");
			} else {
				int response = JOptionPane.showConfirmDialog(contentPane, "Are you sure to delete this category?", "Confirmation", JOptionPane.YES_NO_OPTION);
				
				if (response == JOptionPane.YES_OPTION) {
					// Move the selected data to another tableModel for deleted book categories
					Object[] deletedRow = new Object[Main.modelBookCategoryRecord.getColumnCount()];
					
					for (int i = 0; i < deletedRow.length; i++) {
						deletedRow[i] = Main.modelBookCategoryRecord.getValueAt(row, i);
					}
					
					Main.modelDeletedCategory.addRow(deletedRow);
					
					// then delete it here in modelBookCategoryRecord
					Main.modelBookCategoryRecord.removeRow(row);
					
					JOptionPane.showMessageDialog(contentPane, "Selected book category deleted!");
					refreshControls();
				}
			}
		}
	}
	
	// Method for setting all controls back to default
	private void refreshControls() {
		lblId.setText(Main.isAdd ? String.valueOf(Main.bookCategoryId) : null);
		txtCategoryName.setEditable(Main.isAdd ? true : false);
		txtCategoryName.setText(null);
		txtSearchBar.setText(" Search");
		txtCategoryName.requestFocus();
		
		// Button design
		btnSave.setBackground(new Color(18, 110, 130));
		btnSave.setForeground(new Color(216, 227, 231));
		btnSave.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		
		doFilter();
		
		btnSave.setText(Main.isDelete ? "DELETE" : "SAVE");
		btnSave.setEnabled(Main.isAdd ? true : false);
		tblBookCategory.clearSelection();
	}
	
	private boolean isRecordExisting() {
		String newCategoryName = txtCategoryName.getText().toLowerCase().trim();
		
		for (int i = 0; i < Main.modelBookCategoryRecord.getRowCount(); i++) {
			String id = Main.modelBookCategoryRecord.getValueAt(i, 0).toString();
			String bookTitle = Main.modelBookCategoryRecord.getValueAt(i, 1).toString().toLowerCase();
			
			if (bookTitle.equals(newCategoryName) && !id.equals(lblId.getText())) {
				return true;
			}
		}
		
		return false;
	}
	
	// Method for filtering table for categories - Category ID/ Name
	private void doFilter() {		
		if (Main.isEdit || Main.isDelete) {
			lblId.setText(null);
			txtCategoryName.setEditable(false);
			txtCategoryName.setText(null);
			btnSave.setEnabled(false);
		}
		
		fetchedIndices = Main.filterTable(tblBookCategory, Main.modelBookCategoryRecord, txtSearchBar.getText(), "", -1, -1, new int[] {0, 1});
	}
}
