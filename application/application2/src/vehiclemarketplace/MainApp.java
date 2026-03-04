package vehiclemarketplace;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
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
import java.awt.List;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

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
					frame.setResizable(false);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1131, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 128, 128));
		panel_1.setForeground(new Color(64, 0, 64));
		panel_1.setBounds(0, 0, 1117, 69);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("APARTMENT PARKING MANAGER");
		lblNewLabel.setLabelFor(this);
		lblNewLabel.setFont(new Font("Impact", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(192, 192, 192));
		panel_2.setBounds(467, 79, 650, 320);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 10, 630, 255);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 632, 255);
		panel_4.add(scrollPane);

		table = new JTable();
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

		JButton btnDeleteSelected = new JButton("Delete Selected");
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

					JOptionPane.showMessageDialog(null, "Row deleted and Spot " + slotID + " is now free.");
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Note", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnDeleteSelected.setBounds(523, 275, 117, 35);
		panel_2.add(btnDeleteSelected);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(139, 139, 139));
		panel_3.setBounds(0, 79, 457, 399);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("TAKE A SPOT");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(10, 10, 243, 35);
		panel_3.add(lblNewLabel_1);

		JLabel lblManu = new JLabel("Manufacturer:");
		lblManu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblManu.setBounds(10, 102, 101, 35);
		panel_3.add(lblManu);

		manuField = new JTextField();
		manuField.setBounds(99, 111, 132, 18);
		panel_3.add(manuField);
		manuField.setColumns(10);

		JLabel lblMdl = new JLabel("Model:");
		lblMdl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMdl.setBounds(10, 139, 101, 35);
		panel_3.add(lblMdl);

		modelField = new JTextField();
		modelField.setColumns(10);
		modelField.setBounds(99, 147, 132, 18);
		panel_3.add(modelField);

		JLabel lblPSpot = new JLabel("Parking Spot:");
		lblPSpot.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPSpot.setBounds(10, 225, 101, 35);
		panel_3.add(lblPSpot);

		parkingSpotField = new JTextField();
		parkingSpotField.setEditable(false);
		parkingSpotField.setToolTipText("");
		parkingSpotField.setColumns(10);
		parkingSpotField.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Selected", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		parkingSpotField.setBounds(173, 210, 58, 59);
		panel_3.add(parkingSpotField);

		String[] duration = {"-","Monthly(PHP 100)", "Bi-Monthly(PHP 250)", "Quarterly(PHP 400)", "Semi-Annually(PHP 600)", "Annually(PHP 1000)", "Bi-Annually(PHP 2000)", "Lifetime(PHP 5000)"};
		JComboBox comboDuration = new JComboBox(duration);
		comboDuration.setBounds(99, 279, 132, 20);
		panel_3.add(comboDuration);

		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDuration.setBounds(10, 271, 101, 35);
		panel_3.add(lblDuration);

		JLabel lblVType = new JLabel("Vehicle Type:");
		lblVType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVType.setBounds(10, 64, 101, 35);
		panel_3.add(lblVType);

		String[] vehicleType = {"-", "Sedan", "Coupe", "Hatchback", "Wagon", "SUV", "Truck", "Limousine", "Convertible", "Van"};
		JComboBox comboVehicleType = new JComboBox(vehicleType);
		comboVehicleType.setBounds(99, 72, 132, 20);
		panel_3.add(comboVehicleType);

		JButton btnChooseSlot = new JButton("Choose");
		btnChooseSlot.setForeground(new Color(0, 0, 0));
		btnChooseSlot.setBounds(99, 210, 67, 34);
		panel_3.add(btnChooseSlot);

		JCheckBox chckEVC = new JCheckBox("EV Charger(PHP 50)");
		chckEVC.setBounds(276, 81, 160, 18);
		panel_3.add(chckEVC);

		JLabel lblNewLabel_1_1 = new JLabel("SERVICES");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(263, 10, 184, 35);
		panel_3.add(lblNewLabel_1_1);

		JCheckBox chckCC = new JCheckBox("Car Cover(PHP 50)");
		chckCC.setBounds(276, 102, 160, 18);
		panel_3.add(chckCC);

		JRadioButton rdbtnSCW = new JRadioButton("Standard Car Wash(PHP 100)");
		buttonGroup.add(rdbtnSCW);
		rdbtnSCW.setBounds(276, 167, 160, 20);
		panel_3.add(rdbtnSCW);

		JRadioButton rdbtnCWT = new JRadioButton("Car Wash Tunnel(PHP 500)");
		buttonGroup.add(rdbtnCWT);
		rdbtnCWT.setBounds(276, 190, 160, 20);
		panel_3.add(rdbtnCWT);

		colorField = new JTextField();
		colorField.setColumns(10);
		colorField.setBounds(99, 182, 132, 18);
		panel_3.add(colorField);

		JLabel lblColor = new JLabel("Color:");
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
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
		btnList_1.setToolTipText("");
		btnList_1.setBounds(310, 344, 126, 35);
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
				if (chckEVC.isSelected()) totalPrice += 50;
				if (chckCC.isSelected()) totalPrice += 50;

				// Services String
				StringBuilder services = new StringBuilder();
				if (rdbtnSCW.isSelected()) services.append("Std Wash, ");
				if (rdbtnCWT.isSelected()) services.append("Tunnel Wash, ");
				if (chckEVC.isSelected()) services.append("EV Charge, ");
				if (chckCC.isSelected()) services.append("Cover, ");

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
				buttonGroup.clearSelection(); // Resets Radio Buttons
				chckEVC.setSelected(false);
				chckCC.setSelected(false);
			}
		});

		panel_3.add(btnList_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Car Wash");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(263, 141, 184, 20);
		panel_3.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Extras");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1.setBounds(263, 55, 184, 20);
		panel_3.add(lblNewLabel_1_1_1_1);

		JButton btnClear = new JButton("CLEAR");
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentSpot = parkingSpotField.getText();

				if (!currentSpot.equals("") && !currentSpot.equals("Selected")) {
					try {
						// Parse the coordinates from the text field (e.g., "1-1")
						String[] parts = currentSpot.split("-");
						int r = Integer.parseInt(parts[0].trim()) - 1;
						int c = Integer.parseInt(parts[1].trim()) - 1;

						// 1. Mark the spot as FREE in the array
						masterParkingData[r][c] = false;

						// 2. Clear the text field
						parkingSpotField.setText("");

						JOptionPane.showMessageDialog(null, "Selection cleared. Spot " + currentSpot + " is now available.");
					} catch (Exception ex) {
						parkingSpotField.setText(""); 
					}
				}
			}
		});
		btnClear.setBounds(99, 244, 67, 25);
		panel_3.add(btnClear);



		JButton btnGetReceipt = new JButton("Get Receipt");
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
					if (chckEVC.isSelected())  { services.append("\n - EV Charging (PHP 50)"); totalPrice += 50; }
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
		

		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(467, 409, 650, 69);
		contentPane.add(panel);

		btnChooseSlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btnChooseSlot) {
					ParkingLotUI picker = new ParkingLotUI(MainApp.this, masterParkingData);
					picker.setVisible(true);
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
				// Parse the old spot ID to free it up
				String[] parts = oldSpot.split("-");
				int oldR = Integer.parseInt(parts[0]) - 1;
				int oldC = Integer.parseInt(parts[1]) - 1;
				masterParkingData[oldR][oldC] = false;
			} catch (Exception e) {
				// In case the text format was different
			}
		}
		this.parkingSpotField.setText(spotID);
		this.masterParkingData[row][col] = true;


	}
}

