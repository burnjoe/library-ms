import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BookInformation extends JFrame {

	
	// Components
	private JPanel contentPane;
	private JLabel lblId;
	private JTextField txtBookTitle, txtBookAuthor, txtBookEdition, txtSearchBar;
	private JTable tblBookInformation;
	private JComboBox<Object> cboCategory, cboFilterCategory;
	private JList<String> listCategories;
	private JButton btnAdd, btnSave, btnExit, btnRemove;
	
	private DefaultComboBoxModel<Object> modelCategories = new DefaultComboBoxModel<Object>();
	private DefaultComboBoxModel<Object> modelFilterCategories = new DefaultComboBoxModel<Object>();
	private DefaultListModel<String> listModelCategories = new DefaultListModel<String>();
	
	// Variables
	private int selectedRow = -1;
	private int[] fetchedIndices;
	
	
	public BookInformation() {
		setUndecorated(true);
		setBounds(100, 100, 1000, 630);
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
		btnExit.setBounds(960, 10, 30, 30);
		contentPane.add(btnExit);
		
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
		
		JLabel lblSearchIcon = new JLabel("");
		lblSearchIcon.setToolTipText("Search");
		// Creates an icon object from the local source folder
		ImageIcon childframesearchIcon = new ImageIcon(this.getClass().getResource("childframesearchIcon.png"));
		// Assigns the icon object as the icon image of components
		lblSearchIcon.setIcon(childframesearchIcon);
		lblSearchIcon.setBounds(350, 50, 30, 30);
		contentPane.add(lblSearchIcon);
		
		// Get the latest records of categories
		getCategories();
		
		cboFilterCategory = new JComboBox<Object>(modelFilterCategories);
		cboFilterCategory.setToolTipText("Filter category");
		cboFilterCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doFilter();
			}
		});
		cboFilterCategory.setForeground(new Color(216, 227, 231));
		cboFilterCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboFilterCategory.setFocusable(false);
		cboFilterCategory.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		cboFilterCategory.setBackground(new Color(19, 44, 51));
		cboFilterCategory.setBounds(700, 50, 190, 30);
		contentPane.add(cboFilterCategory);
		
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
		txtSearchBar.setBounds(390, 50, 300, 30);
		contentPane.add(txtSearchBar);
		
		
		JLabel lblBookId = new JLabel("Book ID:");
		lblBookId.setForeground(new Color(216, 227, 231));
		lblBookId.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblBookId.setBounds(20, 100, 110, 30);
		contentPane.add(lblBookId);
		
		JLabel lblBookTitle = new JLabel("Book Title:");
		lblBookTitle.setForeground(new Color(216, 227, 231));
		lblBookTitle.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblBookTitle.setBounds(20, 145, 110, 30);
		contentPane.add(lblBookTitle);
		
		JLabel lblBookAuthor = new JLabel("Book Author:");
		lblBookAuthor.setForeground(new Color(216, 227, 231));
		lblBookAuthor.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblBookAuthor.setBounds(20, 190, 110, 30);
		contentPane.add(lblBookAuthor);
		
		JLabel lblBookEdition = new JLabel("Book Edition:");
		lblBookEdition.setForeground(new Color(216, 227, 231));
		lblBookEdition.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblBookEdition.setBounds(20, 235, 110, 30);
		contentPane.add(lblBookEdition);
		
		JLabel lblCategories = new JLabel("Categories:");
		lblCategories.setForeground(new Color(216, 227, 231));
		lblCategories.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblCategories.setBounds(20, 295, 110, 30);
		contentPane.add(lblCategories);
		
		lblId = new JLabel(Main.isAdd ? String.valueOf(Main.bookId) : null);
		lblId.setToolTipText("Book ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setForeground(new Color(216, 227, 231));
		lblId.setBackground(new Color(18, 110, 130));
		lblId.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		lblId.setBounds(140, 100, 190, 30);
		contentPane.add(lblId);
		
		txtBookTitle = new JTextField();
		txtBookTitle.setToolTipText("Book title");
		txtBookTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBookTitle.setCaretColor(new Color(216, 227, 231));
		txtBookTitle.setBackground(new Color(18, 110, 130));
		txtBookTitle.setForeground(new Color(216, 227, 231));
		txtBookTitle.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtBookTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtBookTitle.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtBookTitle.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		txtBookTitle.setColumns(10);
		txtBookTitle.setBounds(140, 145, 190, 30);
		contentPane.add(txtBookTitle);
		
		txtBookAuthor = new JTextField();
		txtBookAuthor.setToolTipText("Bookd author");
		txtBookAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBookAuthor.setCaretColor(new Color(216, 227, 231));
		txtBookAuthor.setBackground(new Color(18, 110, 130));
		txtBookAuthor.setForeground(new Color(216, 227, 231));
		txtBookAuthor.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtBookAuthor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtBookAuthor.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtBookAuthor.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		txtBookAuthor.setColumns(10);
		txtBookAuthor.setBounds(140, 190, 190, 30);
		contentPane.add(txtBookAuthor);
		
		txtBookEdition = new JTextField();
		txtBookEdition.setToolTipText("Book edition");
		txtBookEdition.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBookEdition.setCaretColor(new Color(216, 227, 231));
		txtBookEdition.setBackground(new Color(18, 110, 130));
		txtBookEdition.setForeground(new Color(216, 227, 231));
		txtBookEdition.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		txtBookEdition.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtBookEdition.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtBookEdition.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		txtBookEdition.setColumns(10);
		txtBookEdition.setBounds(140, 235, 190, 30);
		contentPane.add(txtBookEdition);
		
		cboCategory = new JComboBox<Object>(modelCategories);
		cboCategory.setToolTipText("Categories");
		cboCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboCategory.setForeground(new Color(216, 227, 231));
		cboCategory.setBackground(new Color(18, 110, 130));
		cboCategory.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		cboCategory.setFocusable(false);
		cboCategory.setBounds(140, 295, 190, 30);
		contentPane.add(cboCategory);
		
		btnAdd = new JButton("ADD"); 
		btnAdd.setToolTipText("Click to add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listModelCategories.getSize() == 0) {
					listModelCategories.addElement(cboCategory.getSelectedItem().toString());
					btnRemove.setEnabled(true);
				} else {
					if(isCategoryInList()) {
						JOptionPane.showMessageDialog(contentPane, "Selected category is already in the list!");
					} else {
						listModelCategories.addElement(cboCategory.getSelectedItem().toString());
						btnRemove.setEnabled(true);
					}
				}
			}
		});
		btnAdd.setFocusable(false);
		btnAdd.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnAdd.setBackground(new Color(18, 110, 130));
		btnAdd.setForeground(new Color(216, 227, 231));
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAdd.setBackground(new Color(81, 196, 211));
				btnAdd.setForeground(new Color(19, 44, 51));
				btnAdd.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAdd.setBackground(new Color(18, 110, 130));
				btnAdd.setForeground(new Color(216, 227, 231));
				btnAdd.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(216, 227, 231));
		separator.setForeground(new Color(216, 227, 231));
		separator.setBounds(20, 280, 310, 2);
		contentPane.add(separator);
		btnAdd.setBounds(230, 340, 100, 30);
		contentPane.add(btnAdd);
		
		btnRemove = new JButton("REMOVE");
		btnRemove.setToolTipText("Click to remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listCategories.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(contentPane, "Please select a category in the list to remove!");
				} else {
					listModelCategories.remove(listCategories.getSelectedIndex());
					
					if(listModelCategories.getSize() == 0) {
						btnRemove.setEnabled(false);
						
						// button design
						btnRemove.setBackground(new Color(18, 110, 130));
						btnRemove.setForeground(new Color(216, 227, 231));
						btnRemove.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
					}
				}
			}
		});
		btnRemove.setFocusable(false);
		btnRemove.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		btnRemove.setBackground(new Color(18, 110, 130));
		btnRemove.setForeground(new Color(216, 227, 231));
		btnRemove.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(btnRemove.isEnabled()) {
				btnRemove.setBackground(new Color(81, 196, 211));
				btnRemove.setForeground(new Color(19, 44, 51));
				btnRemove.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(btnRemove.isEnabled()) {
				btnRemove.setBackground(new Color(18, 110, 130));
				btnRemove.setForeground(new Color(216, 227, 231));
				btnRemove.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
				}
			}
		});
		btnRemove.setBounds(230, 380, 100, 30);
		contentPane.add(btnRemove);
		
		JScrollPane scrollPaneList = new JScrollPane();
		scrollPaneList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneList.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
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
		        
		        //  Get the Action from the scrollbar ActionMap for the given key
		        
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
		scrollPaneList.setBounds(20, 340, 200, 200);
		contentPane.add(scrollPaneList);
		
		listCategories = new JList<String>(listModelCategories);
		listCategories.setFixedCellHeight(20);
		listCategories.setFont(new Font("Tahoma", Font.PLAIN, 14));
		listCategories.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listCategories.setBackground(new Color(18, 110, 130));
		listCategories.setForeground(new Color(216, 227, 231));
		listCategories.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		scrollPaneList.setViewportView(listCategories);
		
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
				btnSave.setBackground(new Color(81, 196, 211));
				btnSave.setForeground(new Color(19, 44, 51));
				btnSave.setBorder(new LineBorder(new Color(81, 196, 211), 1, true));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSave.setBackground(new Color(18, 110, 130));
				btnSave.setForeground(new Color(216, 227, 231));
				btnSave.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
			}
		});
		btnSave.setBounds(45, 560, 270, 30);
		contentPane.add(btnSave);
		
		JScrollPane scrollPaneBookInformation = new JScrollPane();
		scrollPaneBookInformation.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		scrollPaneBookInformation.setBounds(350, 90, 630, 520);
		contentPane.add(scrollPaneBookInformation);
		
		tblBookInformation = new JTable(Main.modelBookRecord) {
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tblBookInformation.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblBookInformation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tblBookInformation.setBorder(new LineBorder(new Color(216, 227, 231), 1, true));
		tblBookInformation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Main.isEdit || Main.isDelete) {
					// Display the selected row to controls: textBox...
					selectedRow = tblBookInformation.getSelectedRow();
					int row = fetchedIndices[selectedRow];
					
					lblId.setText(Main.modelBookRecord.getValueAt(row, 0).toString());
					txtBookTitle.setText(Main.modelBookRecord.getValueAt(row, 1).toString());
					txtBookAuthor.setText(Main.modelBookRecord.getValueAt(row, 2).toString());
					txtBookEdition.setText(Main.modelBookRecord.getValueAt(row, 3).toString());
					
					listModelCategories.removeAllElements();
					String[] categories = Main.modelBookRecord.getValueAt(row, 4).toString().split(", ");
					
					for (int i = 0; i < categories.length; i++) {
						listModelCategories.addElement(categories[i]);
					}				
					
					boolean condition = Main.isDelete ? false : true;
					
					txtBookTitle.setEditable(condition);
					txtBookAuthor.setEditable(condition);
					txtBookEdition.setEditable(condition);	
					btnSave.setEnabled(true);
					btnAdd.setEnabled(condition);
					btnRemove.setEnabled(condition);
				}
			}
		});
		scrollPaneBookInformation.setViewportView(tblBookInformation);
		
		// Hides the given column of the given table
		Main.hideTableColumns(tblBookInformation, new int[] {5, 6, 7, 8});
		
		JLabel lblInformationBackground = new JLabel("");
		// Creates an icon object from the local source folder
		ImageIcon informationBackground = new ImageIcon(this.getClass().getResource("informationBackground.png"));
		// Assigns the icon object as the icon image of components
		lblInformationBackground.setIcon(informationBackground);
		lblInformationBackground.setBounds(0, 0, 1000, 630);
		contentPane.add(lblInformationBackground);
		
		
		// Set the controls that corresponds to the current operation 
		refreshControls();
	}
	
	
	// METHODS
	private void save() {
		if (Main.isAdd) {
			if (txtBookTitle.getText().isBlank() || txtBookAuthor.getText().isBlank() || txtBookEdition.getText().isBlank() ||
					modelCategories.getSize() == 0 || listModelCategories.getSize() == 0) {
				JOptionPane.showMessageDialog(contentPane, "Please fill up all needed information!");
				txtBookTitle.requestFocus();
			} else {
				if (isRecordExisting()) {
					JOptionPane.showMessageDialog(contentPane, "Book record is already existing!");
					txtBookTitle.requestFocus();
				} else {	
					String categories = "";
					Object[] newRow = new Object[9];
					
					newRow[0] = Integer.parseInt(lblId.getText());
					newRow[1] = txtBookTitle.getText().trim();
					newRow[2] = txtBookAuthor.getText().trim();
					newRow[3] = txtBookEdition.getText().trim();
					
					if (listModelCategories.getSize() == 1) {
						categories = listModelCategories.getElementAt(0);
					} else {
						for (int i = 0; i < listModelCategories.getSize(); i++) {
							if (i == 0) {
								categories = listModelCategories.getElementAt(i);
							} else {
								categories = categories + ", " + listModelCategories.getElementAt(i);
							}
						}
					}
					
					newRow[4] = categories;
					newRow[5] = 0;
					newRow[6] = 0;
					newRow[7] = 0;
					newRow[8] = 0;
					
					Main.bookId++;
					
					Main.modelBookRecord.addRow(newRow);
					
					JOptionPane.showMessageDialog(contentPane, "New book added!");
					updateCategoriesNumberOfBooks();
					refreshControls();
				}
			}
		} else if (Main.isEdit) {
			if (txtBookTitle.getText().isBlank() || txtBookAuthor.getText().isBlank() || txtBookEdition.getText().isBlank() ||
					modelCategories.getSize() == 0 || listModelCategories.getSize() == 0) {
				JOptionPane.showMessageDialog(contentPane, "Please fill up all needed information!");
				txtBookTitle.requestFocus();
			} else {
				int row = fetchedIndices[selectedRow];
				// if the book selected is borrowed. Updating record not possible
				if (Integer.parseInt(Main.modelBookRecord.getValueAt(row, 8).toString()) > 0) {
					JOptionPane.showMessageDialog(contentPane, "Unable to edit record. Selected book title is currently "
							+ "borrowed or is in transaction requests.");
					txtBookTitle.requestFocus();
				} else {
					// Ask for confirmation
					int response = JOptionPane.showConfirmDialog(contentPane, "Are you sure to update this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
					
					if (response == JOptionPane.YES_OPTION) {			
						if (isRecordExisting()) {
							JOptionPane.showMessageDialog(contentPane, "Record is already existing!");
							txtBookTitle.requestFocus();
						} else {				
							String categories = "";
							
							Main.modelBookRecord.setValueAt(txtBookTitle.getText().trim(), row, 1);
							Main.modelBookRecord.setValueAt(txtBookAuthor.getText().trim(), row, 2);
							Main.modelBookRecord.setValueAt(txtBookEdition.getText().trim(), row, 3);
							
							if (listModelCategories.getSize() == 1) {
								categories = listModelCategories.getElementAt(0);
							} else {
								for (int i = 0; i < listModelCategories.getSize(); i++) {
									if (i == 0) {
										categories = listModelCategories.getElementAt(i);
									} else {
										categories = categories + ", " + listModelCategories.getElementAt(i);
									}
								}
							}
							
							Main.modelBookRecord.setValueAt(categories, row, 4);
							
							JOptionPane.showMessageDialog(contentPane, "Selected record updated!");
							updateCategoriesNumberOfBooks();
							refreshControls();
						}
					}
				}
			}
		} else if (Main.isDelete) {
			int row = fetchedIndices[selectedRow];
			
			// if the book selected has borrowed stocks. Deletion of record not possible
			if (Integer.parseInt(Main.modelBookRecord.getValueAt(row, 8).toString()) > 0) {
				JOptionPane.showMessageDialog(contentPane, "Unable to delete record. Selected book title is currently "
						+ "borrowed or is in transaction requests.");
			} else {
				int response = JOptionPane.showConfirmDialog(contentPane, "Are you sure to delete this record? Deleting"
						+ " this book title will also delete its stocks.", "Confirmation", JOptionPane.YES_NO_OPTION);
			
				if (response == JOptionPane.YES_OPTION) {
					// move the selected book data to another tableModel for deleted books
					Object[] deletedBookRow = new Object[Main.modelBookRecord.getColumnCount()];
					
					for (int i = 0; i < deletedBookRow.length; i++) {
						deletedBookRow[i] = Main.modelBookRecord.getValueAt(row, i);
					}
					
					Main.modelDeletedBooks.addRow(deletedBookRow);
					
					
					// move the selected book's stocks to another tableModel for deleted book's stocks 
					int bookId = Integer.parseInt(Main.modelBookRecord.getValueAt(row, 0).toString());
					
					for (int i = 0; i < Main.modelBookInventoryRecord.getRowCount(); i++) {
						int stockBookId = Integer.parseInt(Main.modelBookInventoryRecord.getValueAt(i, 0).toString());
						
						if (stockBookId == bookId) {
							Object[] deletedStockRow = new Object[Main.modelBookInventoryRecord.getColumnCount()];
							
							for (int column = 0; column < deletedStockRow.length; column++) {
								deletedStockRow[column] = Main.modelBookInventoryRecord.getValueAt(i, column);
							}
							
							// add to modelDeletedStocks
							Main.modelDeletedStocks.addRow(deletedStockRow);
							
							// delete row to modelBookInventory (stocks)
							Main.modelBookInventoryRecord.removeRow(i);
							
							// decrement i to check again the row
							i--;
						}
					}
					
					
					// then delete it here in modelBookRecord		
					Main.modelBookRecord.removeRow(row);
					
					JOptionPane.showMessageDialog(contentPane, "Selected book record and its stocks deleted!");
					updateCategoriesNumberOfBooks();
					refreshControls();
				}
			}
			
		}
	}
	
	
	private void refreshControls() {
		boolean condition = Main.isAdd ? true : false;
		
		lblId.setText(Main.isAdd ? String.valueOf(Main.bookId) : null);
		btnSave.setText(Main.isDelete ? "DELETE" : "SAVE");
		txtBookTitle.setEditable(condition);
		txtBookAuthor.setEditable(condition);
		txtBookEdition.setEditable(condition);	
		btnSave.setEnabled(condition);
		btnAdd.setEnabled(condition);
		btnRemove.setEnabled(false);
		
		txtBookTitle.setText(null);
		txtBookAuthor.setText(null);
		txtBookEdition.setText(null);
		txtBookTitle.requestFocus();
		
		cboCategory.setSelectedIndex(modelCategories.getSize() == 0 ? -1 : 0);
		listModelCategories.removeAllElements();
		
		txtSearchBar.setText(" Search");
		cboFilterCategory.setSelectedIndex(0);
		
		tblBookInformation.clearSelection();
		
		doFilter();
		
		// Updates the categories displayed on list of Main frame
		Main.getCategories();
	}
	
	// Method for checking: if category is in the list or not
	private boolean isCategoryInList() {
		String category = modelCategories.getSelectedItem().toString();
		
		for (int i = 0; i < listModelCategories.getSize(); i++) {
			String elementCategory = listModelCategories.getElementAt(i);
			
			if (category.equals(elementCategory)) {
				return true;
			}
		}
		
		return false;
	}
	
	// Method for checking: if book record is already existing in model or not
	private boolean isRecordExisting() {
		String newBookTitle = txtBookTitle.getText().toLowerCase().trim();
		String newBookAuthor = txtBookAuthor.getText().toLowerCase().trim();
		String newBookEdition = txtBookEdition.getText().toLowerCase().trim();
		
		for (int i = 0; i < Main.modelBookRecord.getRowCount(); i++) {
			String id = Main.modelBookRecord.getValueAt(i, 0).toString();
			String bookTitle = Main.modelBookRecord.getValueAt(i, 1).toString().toLowerCase();
			String bookAuthor = Main.modelBookRecord.getValueAt(i, 2).toString().toLowerCase();
			String bookEdition = Main.modelBookRecord.getValueAt(i, 3).toString().toLowerCase();
			
			if (bookTitle.equals(newBookTitle) && bookAuthor.equals(newBookAuthor) && bookEdition.equals(newBookEdition) && 
					!id.equals(lblId.getText())) {
				return true;
			}
		}
		
		return false;
	}
	
	// Method for getting the categories and stores it to model of comboBox 
	private void getCategories() {
		// Add the categories in modelCategories
		for (int i = 0; i < Main.modelBookCategoryRecord.getRowCount(); i++) {
			String categoryName = Main.modelBookCategoryRecord.getValueAt(i, 1).toString();
			
			modelCategories.addElement(categoryName);
		}
		
		// Add the categories in modelFilterCategories
		for (int i = -1; i < Main.modelBookCategoryRecord.getRowCount(); i++) {
			if (i == -1) {
				modelFilterCategories.addElement("-- All --");
			} else {
				String categoryName = Main.modelBookCategoryRecord.getValueAt(i, 1).toString();
				
				modelFilterCategories.addElement(categoryName);
			}
		}
	}
	
	
	private void updateCategoriesNumberOfBooks() {		
		int categoryCount = 0;
		
		for (int i = 0; i < Main.modelBookCategoryRecord.getRowCount(); i++) {
			String categoryName = Main.modelBookCategoryRecord.getValueAt(i, 1).toString();
			
			for (int j = 0; j < Main.modelBookRecord.getRowCount(); j++) {
				String[] categories = Main.modelBookRecord.getValueAt(j, 4).toString().split(", ");
				
				for (int k = 0; k < categories.length; k++) {
					if (categories[k].equals(categoryName)) {
						categoryCount++;
					}
				}
			}
						
			Main.modelBookCategoryRecord.setValueAt(String.valueOf(categoryCount), i, 2);
			
			categoryCount = 0;
		}
	}
	
	
	private void doFilter() {
		if (Main.isEdit || Main.isDelete) {
			lblId.setText(null);
			txtBookTitle.setText(null);
			txtBookAuthor.setText(null);
			txtBookEdition.setText(null);
			cboCategory.setSelectedIndex(modelCategories.getSize() == 0 ? -1 : 0);
			listModelCategories.removeAllElements();
			
			txtBookTitle.setEditable(false);
			txtBookAuthor.setEditable(false);
			txtBookEdition.setEditable(false);	
			btnSave.setEnabled(false);
			btnAdd.setEnabled(false);
			btnRemove.setEnabled(false);
		}
		
		fetchedIndices = Main.filterTable(tblBookInformation, Main.modelBookRecord, txtSearchBar.getText(), 
				cboFilterCategory.getSelectedItem().toString(), -1, -1, new int[] {0, 1, 2, 4});
	}
}
