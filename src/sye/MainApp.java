package sye;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ListSelectionModel;
import javax.swing.OverlayLayout;

import java.awt.List;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.Component;

public class MainApp extends JFrame implements ParkingSpotListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField manuField;
	private JTextField modelField;
	private JTextField parkingSpotField;
	private boolean[][] masterParkingData = new boolean[8][10];
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField colorField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp frame = new MainApp();
					frame.setVisible(true);
					frame.setResizable(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */


	public MainApp() {
		setTitle("APARTMENT PARKING MANAGER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1131, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new CompoundBorder(
				new CompoundBorder(
						new LineBorder(new Color(64, 64, 64), 2),        // outer dark gray line
						new BevelBorder(BevelBorder.RAISED,
								new Color(105, 105, 105),                    // highlight outer
								new Color(80, 80, 80),                       // highlight inner
								new Color(40, 40, 40),                       // shadow outer
								new Color(20, 20, 20))                       // shadow inner
						),
				new LineBorder(new Color(90, 90, 90), 1)             // inner subtle line
				));
		panel_1.setBackground(new Color(45, 45, 48));
		panel_1.setForeground(new Color(45, 45, 48));
		panel_1.setBounds(0, 0, 1117, 69);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		// OverlayLayout
		JPanel overlayPanel = new JPanel();
		overlayPanel.setBackground(new Color(45, 45, 48));
		overlayPanel.setLayout(new OverlayLayout(overlayPanel));

		// Text label
		JLabel textLabel = new JLabel("APARTMENT PARKING MANAGER");
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
		textLabel.setFont(new Font("Impact", Font.BOLD, 30));
		textLabel.setForeground(Color.WHITE);
		textLabel.setAlignmentX(0.5f);

		// Image label
		JLabel imageLabel = new JLabel();
		imageLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		imageLabel.setIcon(new ImageIcon(MainApp.class.getResource("/sye/Parking_lot_Main-1.png")));
		imageLabel.setAlignmentX(0.5f);

		overlayPanel.add(textLabel);
		overlayPanel.add(imageLabel);

		panel_1.add(overlayPanel);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new CompoundBorder(
				new CompoundBorder(
						new LineBorder(new Color(64, 64, 64), 2),        // outer dark gray line
						new BevelBorder(BevelBorder.RAISED,
								new Color(105, 105, 105),                    // highlight outer
								new Color(80, 80, 80),                       // highlight inner
								new Color(40, 40, 40),                       // shadow outer
								new Color(20, 20, 20))                       // shadow inner
						),
				new LineBorder(new Color(90, 90, 90), 1)             // inner subtle line
				));
		panel_2.setBackground(new Color(45, 45, 48));
		panel_2.setBounds(467, 79, 650, 320);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(192, 192, 192));
		panel_4.setBounds(10, 10, 630, 255);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 632, 255);
		panel_4.add(scrollPane);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);
		table.setForeground(new Color(0, 0, 0));
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Vehicle Type", "Manufacturer", "Model", "Color", "Duration", "Parking Slot", "Services"
				}
				) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(170);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JLabel label = new JLabel("New label");
		scrollPane.setColumnHeaderView(label);

		JLabel label_1 = new JLabel("New label");
		scrollPane.setColumnHeaderView(label_1);

		JButton btnDeleteSelected = new JButton("Delete Selected");
		btnDeleteSelected.setBorder(new CompoundBorder(
				new CompoundBorder(
						new LineBorder(new Color(192, 192, 192), 2),     // outer gray line
						new BevelBorder(BevelBorder.RAISED,
								new Color(192, 192, 192),                    // highlight outer
								new Color(160, 160, 160),                    // highlight inner
								new Color(128, 128, 128),                    // shadow outer
								new Color(100, 100, 100))                    // shadow inner
						),
				new LineBorder(new Color(192, 192, 192), 1)          // inner subtle line
				));
		btnDeleteSelected.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
		btnDeleteSelected.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDeleteSelected.setText("🗑️ Delete Selected");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnDeleteSelected.setText("Delete Selected");
			}
		});
		btnDeleteSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableMod = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {

					String slotID = tableMod.getValueAt(selectedRow, 5).toString();


					String[] parts = slotID.split("-");
					int r = Integer.parseInt(parts[0]) - 1;
					int c = Integer.parseInt(parts[1]) - 1;


					masterParkingData[r][c] = false;


					tableMod.removeRow(selectedRow);
					table.setRowSelectionAllowed(false);

					JOptionPane.showMessageDialog(null, "Row deleted and Spot " + slotID + " is now free.");
					btnDeleteSelected.setVisible(false);
					int[] selectedRows = table.getSelectedRows();
					for (int i = selectedRows.length - 1; i >= 0; i--) {
						int modelRowIndex = table.convertRowIndexToModel(selectedRows[i]); 
						table.remove(modelRowIndex);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Note", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		});
		btnDeleteSelected.setBounds(396, 275, 117, 35);
		btnDeleteSelected.setVisible(false);
		panel_2.add(btnDeleteSelected);

		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnSelect) {
					btnDeleteSelected.setVisible(true);
					table.setRowSelectionAllowed(true);

				}
			}
		});
		btnSelect.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
		btnSelect.setBorder(new CompoundBorder(
				new CompoundBorder(
						new LineBorder(new Color(192, 192, 192), 2),     // outer gray line
						new BevelBorder(BevelBorder.RAISED,
								new Color(192, 192, 192),                    // highlight outer
								new Color(160, 160, 160),                    // highlight inner
								new Color(128, 128, 128),                    // shadow outer
								new Color(100, 100, 100))                    // shadow inner
						),
				new LineBorder(new Color(192, 192, 192), 1)          // inner subtle line
				));
		btnSelect.setBounds(523, 275, 117, 35);
		panel_2.add(btnSelect);



		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(45, 45, 48));
		panel_3.setBounds(0, 79, 457, 399);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("TAKE A SPOT");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Agency FB", Font.BOLD, 25));
		lblNewLabel_1.setBounds(10, 10, 243, 35);
		panel_3.add(lblNewLabel_1);

		JLabel lblManu = new JLabel("Manufacturer:");
		lblManu.setForeground(new Color(255, 255, 255));
		lblManu.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblManu.setBounds(10, 102, 101, 35);
		panel_3.add(lblManu);

		manuField = new JTextField();
		manuField.setFont(new Font("Arial", Font.BOLD, 12));
		manuField.setBounds(99, 111, 132, 18);
		panel_3.add(manuField);
		manuField.setColumns(10);

		JLabel lblMdl = new JLabel("Model:");
		lblMdl.setForeground(new Color(255, 255, 255));
		lblMdl.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblMdl.setBounds(10, 139, 101, 35);
		panel_3.add(lblMdl);

		modelField = new JTextField();
		modelField.setFont(new Font("Arial", Font.BOLD, 12));
		modelField.setColumns(10);
		modelField.setBounds(99, 147, 132, 18);
		panel_3.add(modelField);

		JLabel lblPSpot = new JLabel("Parking Spot:");
		lblPSpot.setForeground(new Color(255, 255, 255));
		lblPSpot.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblPSpot.setBounds(10, 225, 101, 35);
		panel_3.add(lblPSpot);

		parkingSpotField = new JTextField();
		parkingSpotField.setFont(new Font("Arial", Font.BOLD, 20));
		parkingSpotField.setEditable(false);
		parkingSpotField.setToolTipText("");
		parkingSpotField.setColumns(10);
		parkingSpotField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Selected", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		parkingSpotField.setBounds(173, 210, 58, 59);
		panel_3.add(parkingSpotField);

		String[] duration = {"-","Monthly(PHP 100)", "Bi-Monthly(PHP 250)", "Quarterly(PHP 400)", "Semi-Annually(PHP 600)", "Annually(PHP 1000)", "Bi-Annually(PHP 2000)", "Lifetime(PHP 5000)"};
		JComboBox comboDuration = new JComboBox(duration);
		comboDuration.setFont(new Font("Arial", Font.BOLD, 12));
		comboDuration.setBounds(99, 279, 132, 20);
		panel_3.add(comboDuration);

		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setForeground(new Color(255, 255, 255));
		lblDuration.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblDuration.setBounds(10, 271, 101, 35);
		panel_3.add(lblDuration);

		JLabel lblVType = new JLabel("Vehicle Type:");
		lblVType.setForeground(new Color(255, 255, 255));
		lblVType.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblVType.setBounds(10, 64, 101, 35);
		panel_3.add(lblVType);

		String[] vehicleType = {"-", "Sedan", "Coupe", "Hatchback", "Wagon", "SUV", "Truck", "Limousine", "Convertible", "Van"};
		JComboBox comboVehicleType = new JComboBox(vehicleType);
		comboVehicleType.setFont(new Font("Arial", Font.BOLD, 12));
		comboVehicleType.setBounds(99, 72, 132, 20);
		panel_3.add(comboVehicleType);
		JButton btnChooseSlot = new JButton("Choose");
		btnChooseSlot.setBorder(new CompoundBorder(
				new CompoundBorder(
						new LineBorder(new Color(192, 192, 192), 2),     // outer gray line
						new BevelBorder(BevelBorder.RAISED,
								new Color(192, 192, 192),                    // highlight outer
								new Color(160, 160, 160),                    // highlight inner
								new Color(128, 128, 128),                    // shadow outer
								new Color(100, 100, 100))                    // shadow inner
						),
				new LineBorder(new Color(192, 192, 192), 1)          // inner subtle line
				));
		btnChooseSlot.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
		btnChooseSlot.setForeground(new Color(0, 0, 0));
		btnChooseSlot.setBounds(99, 210, 67, 34);
		btnChooseSlot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnChooseSlot.setText("6 7"); 
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnChooseSlot.setText("Choose"); // 
			}
		});
		panel_3.add(btnChooseSlot);

		JCheckBox chckEVC = new JCheckBox("EV Charger(PHP 55)");
		chckEVC.setFont(new Font("Agency FB", Font.PLAIN, 15));
		chckEVC.setForeground(new Color(255, 255, 255));
		chckEVC.setBounds(276, 81, 160, 18);
		chckEVC.setOpaque(false);
		panel_3.add(chckEVC);

		JLabel lblNewLabel_1_1 = new JLabel("SERVICES");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Agency FB", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(263, 10, 184, 35);
		panel_3.add(lblNewLabel_1_1);

		JCheckBox chckCC = new JCheckBox("Car Cover(PHP 50)");
		chckCC.setFont(new Font("Agency FB", Font.PLAIN, 15));
		chckCC.setForeground(new Color(255, 255, 255));
		chckCC.setBounds(276, 102, 160, 18);
		chckCC.setOpaque(false);
		panel_3.add(chckCC);

		JRadioButton rdbtnSCW = new JRadioButton("Standard Car Wash(PHP 100)");
		rdbtnSCW.setFont(new Font("Agency FB", Font.PLAIN, 15));
		rdbtnSCW.setForeground(new Color(255, 255, 255));
		buttonGroup.add(rdbtnSCW);
		rdbtnSCW.setOpaque(false);
		rdbtnSCW.setBounds(276, 167, 160, 20);
		panel_3.add(rdbtnSCW);

		JRadioButton rdbtnCWT = new JRadioButton("Car Wash Tunnel(PHP 500)");
		rdbtnCWT.setFont(new Font("Agency FB", Font.PLAIN, 15));
		rdbtnCWT.setForeground(new Color(255, 255, 255));
		buttonGroup.add(rdbtnCWT);
		rdbtnCWT.setOpaque(false);
		rdbtnCWT.setBounds(276, 190, 160, 20);
		panel_3.add(rdbtnCWT);

		colorField = new JTextField();
		colorField.setFont(new Font("Arial", Font.BOLD, 12));
		colorField.setColumns(10);
		colorField.setBounds(99, 182, 132, 18);
		panel_3.add(colorField);

		JLabel lblColor = new JLabel("Color:");
		lblColor.setForeground(new Color(255, 255, 255));
		lblColor.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblColor.setBounds(10, 175, 101, 35);
		panel_3.add(lblColor);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(276, 225, 160, 109);
		scrollPane_1.setVisible(false);
		panel_3.add(scrollPane_1);

		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		textArea.setWrapStyleWord(true);
		textArea.setOpaque(false);
		textArea.setFocusable(false);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setVisible(false);

		JButton btnList_1 = new JButton("LIST");
		btnList_1.setBorder(new CompoundBorder(
				new CompoundBorder(
						new LineBorder(new Color(192, 192, 192), 2),     // outer gray line
						new BevelBorder(BevelBorder.RAISED,
								new Color(192, 192, 192),                    // highlight outer
								new Color(160, 160, 160),                    // highlight inner
								new Color(128, 128, 128),                    // shadow outer
								new Color(100, 100, 100))                    // shadow inner
						),
				new LineBorder(new Color(192, 192, 192), 1)          // inner subtle line
				));
		btnList_1.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
		btnList_1.setToolTipText("");
		btnList_1.setBounds(310, 344, 126, 35);
		btnList_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnList_1.setText("✅ LIST");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnList_1.setText("LIST");
			}
		});
		btnList_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 1. Validation check first
				if (manuField.getText().equals("") || modelField.getText().equals("") || colorField.getText().equals("") ||
						comboVehicleType.getSelectedItem().equals("-") || comboDuration.getSelectedItem().equals("-") ||
						parkingSpotField.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Please fill up everything and choose a parking spot!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				//Calculate Pricing
				int totalPrice = 0;
				String selectedDuration = String.valueOf(comboDuration.getSelectedItem());
				if (selectedDuration.contains("100")) totalPrice += 100;
				else if (selectedDuration.contains("250")) totalPrice += 250;
				else if (selectedDuration.contains("400")) totalPrice += 400;
				else if (selectedDuration.contains("600")) totalPrice += 600;
				else if (selectedDuration.contains("1000")) totalPrice += 1000;
				else if (selectedDuration.contains("2000")) totalPrice += 2000;
				else if (selectedDuration.contains("5000")) totalPrice += 5000;

				// Service prices
				if (rdbtnSCW.isSelected()) totalPrice += 100;
				if (rdbtnCWT.isSelected()) totalPrice += 500;
				if (chckEVC.isSelected()) totalPrice += 55;
				if (chckCC.isSelected()) totalPrice += 50;

				// Services String
				StringBuilder services = new StringBuilder();
				if (rdbtnSCW.isSelected()) services.append("Std Wash, ");
				if (rdbtnCWT.isSelected()) services.append("Tunnel Wash, ");
				if (chckEVC.isSelected()) services.append("EV Charge, ");
				if (chckCC.isSelected()) services.append("Cover, ");

				textArea.setVisible(false);
				scrollPane_1.setVisible(false);
				btnChooseSlot.setEnabled(true);

				String servicesList = services.toString();
				if (servicesList.length() > 0) {
					servicesList = servicesList.substring(0, servicesList.length() - 2);
				} else {
					servicesList = "None";
				}

				// Data Array 
				String data[] = {
						String.valueOf(comboVehicleType.getSelectedItem()), 
						manuField.getText(),                               
						modelField.getText(), 
						colorField.getText(),
						selectedDuration,                                  
						parkingSpotField.getText(),                        
						servicesList + "TOTAL: " + totalPrice 
				};

				// Add to Table and Reset
				DefaultTableModel tblmod = (DefaultTableModel)table.getModel();
				tblmod.addRow(data);

				JOptionPane.showMessageDialog(null, "Information successfully listed. Total Price is: " + totalPrice, "Success", JOptionPane.INFORMATION_MESSAGE);

				// Reset fields
				manuField.setText("");
				modelField.setText("");
				parkingSpotField.setText("");
				colorField.setText("");
				comboVehicleType.setSelectedItem("-");
				comboDuration.setSelectedItem("-");
				buttonGroup.clearSelection(); 
				chckEVC.setSelected(false);
				chckCC.setSelected(false);
			}
		});

		panel_3.add(btnList_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Car Wash");
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblNewLabel_1_1_1.setBounds(263, 141, 184, 20);
		panel_3.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Extras");
		lblNewLabel_1_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblNewLabel_1_1_1_1.setBounds(263, 55, 184, 20);
		panel_3.add(lblNewLabel_1_1_1_1);

		JButton btnClear = new JButton("CLEAR");
		btnClear.setBorder(new CompoundBorder(
				new CompoundBorder(
						new LineBorder(new Color(192, 192, 192), 2),     // outer gray line
						new BevelBorder(BevelBorder.RAISED,
								new Color(192, 192, 192),                    // highlight outer
								new Color(160, 160, 160),                    // highlight inner
								new Color(128, 128, 128),                    // shadow outer
								new Color(100, 100, 100))                    // shadow inner
						),
				new LineBorder(new Color(192, 192, 192), 1)          // inner subtle line
				));
		btnClear.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 8));
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnClear.setText("❌ CLEAR");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnClear.setText("CLEAR");
			}
		});
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentSpot = parkingSpotField.getText();

				if (!currentSpot.equals("") && !currentSpot.equals("Selected")) {
					try {

						String[] parts = currentSpot.split("-");
						int r = Integer.parseInt(parts[0].trim()) - 1;
						int c = Integer.parseInt(parts[1].trim()) - 1;


						masterParkingData[r][c] = false;


						parkingSpotField.setText("");

						JOptionPane.showMessageDialog(null, "Selection cleared. Spot " + currentSpot + " is now available.");
					} catch (Exception ex) {
						parkingSpotField.setText(""); 
					}
					btnChooseSlot.setEnabled(true);

				}
			}
		});
		btnClear.setBounds(99, 244, 67, 25);
		panel_3.add(btnClear);



		JButton btnGetReceipt = new JButton("Get Receipt");
		btnGetReceipt.setBorder(new CompoundBorder(
				new CompoundBorder(
						new LineBorder(new Color(192, 192, 192), 2),     // outer gray line
						new BevelBorder(BevelBorder.RAISED,
								new Color(192, 192, 192),                    // highlight outer
								new Color(160, 160, 160),                    // highlight inner
								new Color(128, 128, 128),                    // shadow outer
								new Color(100, 100, 100))                    // shadow inner
						),
				new LineBorder(new Color(192, 192, 192), 1)          // inner subtle line
				));
		btnGetReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (manuField.getText().equals("") || modelField.getText().equals("") || colorField.getText().equals("") ||
						comboVehicleType.getSelectedItem().equals("-") || comboDuration.getSelectedItem().equals("-") ||
						parkingSpotField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill up everything and choose a parking spot!", "Error", JOptionPane.ERROR_MESSAGE);


				} else {
					int totalPrice = 0;
					String selectedDuration = String.valueOf(comboDuration.getSelectedItem());
					if (selectedDuration.contains("100")) totalPrice += 100;
					else if (selectedDuration.contains("250")) totalPrice += 250;
					else if (selectedDuration.contains("400")) totalPrice += 400;
					else if (selectedDuration.contains("600")) totalPrice += 600;
					else if (selectedDuration.contains("1000")) totalPrice += 1000;
					else if (selectedDuration.contains("2000")) totalPrice += 2000;
					else if (selectedDuration.contains("5000")) totalPrice += 5000;


					StringBuilder services = new StringBuilder();
					if (rdbtnSCW.isSelected()) { services.append("\n - Std Car Wash (PHP 100)"); totalPrice += 100; }
					if (rdbtnCWT.isSelected()) { services.append("\n - Tunnel Wash (PHP 500)"); totalPrice += 500; }
					if (chckEVC.isSelected())  { services.append("\n - EV Charging (PHP 55)"); totalPrice += 55; }
					if (chckCC.isSelected())   { services.append("\n - Car Cover (PHP 50)"); totalPrice += 50; }

					String servicesList = services.length() > 0 ? services.toString() : "\n - None";


					textArea.setVisible(true);
					scrollPane_1.setVisible(true);
					textArea.setText("********** RECEIPT **********\n"
							+ "\nCar: " + manuField.getText() + " " + modelField.getText()
							+ "\nColor: " + colorField.getText()
							+"\nVehicle Type: " + String.valueOf(comboVehicleType.getSelectedItem())
							+ "\nParking Spot: " + parkingSpotField.getText()
							+ "\n-----------------------------"
							+ "\nDuration: " + selectedDuration
							+ "\nServices Included:" + servicesList
							+ "\n-----------------------------"
							+ "\nTOTAL AMOUNT: PHP " + totalPrice
							+ "\n*****************************");
				}
			}
		});
		btnGetReceipt.setBounds(99, 309, 132, 35);
		panel_3.add(btnGetReceipt);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBorder(new CompoundBorder(
				new CompoundBorder(
						new LineBorder(new Color(192, 192, 192), 2),     // outer gray line
						new BevelBorder(BevelBorder.RAISED,
								new Color(192, 192, 192),                    // highlight outer
								new Color(160, 160, 160),                    // highlight inner
								new Color(128, 128, 128),                    // shadow outer
								new Color(100, 100, 100))                    // shadow inner
						),
				new LineBorder(new Color(192, 192, 192), 1)          // inner subtle line
				));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(MainApp.class.getResource("/sye/images (1).jpg")));
		lblNewLabel_2.setBounds(0, 0, 457, 399);
		panel_3.add(lblNewLabel_2);

		Image original = new ImageIcon(MainApp.class.getResource("/sye/images (1).jpg")).getImage();

		BufferedImage img = new BufferedImage(457, 399, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();


		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f)); 
		g2d.drawImage(original, 0, 0, 457, 399, null);
		g2d.dispose();

		lblNewLabel_2.setIcon(new ImageIcon(img));



		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 45, 48));
		panel.setBounds(467, 409, 670, 69);
		contentPane.add(panel);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 79, 9, 9);
		contentPane.add(panel_5);

		ImageIcon image = new ImageIcon("images(1).png");

		btnChooseSlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btnChooseSlot) {
					ParkingLotUI picker = new ParkingLotUI(MainApp.this, masterParkingData);
					picker.setVisible(true);
					btnChooseSlot.setEnabled(false);
				}
			}

			public void onSpotSelected(int row, int col, String spotID) {
				parkingSpotField.setText(spotID);
				masterParkingData[row][col] = true;

			}

		});
	}

	@Override
	public void onSpotSelected(int row, int col, String spotID) {
		// Update the TextField in MainApp
		this.parkingSpotField.setText(spotID);
		String oldSpot = parkingSpotField.getText();
		if (!oldSpot.equals("") && !oldSpot.equals("Selected")) {
			try {

				String[] parts = oldSpot.split("-");
				int oldR = Integer.parseInt(parts[0]) - 1;
				int oldC = Integer.parseInt(parts[1]) - 1;
				masterParkingData[oldR][oldC] = false;
			} catch (Exception e) {

			}
		}
		this.parkingSpotField.setText(spotID);
		this.masterParkingData[row][col] = true;


	}
}