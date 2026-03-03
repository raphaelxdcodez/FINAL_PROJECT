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
						"Vehicle Type", "Manufacturer", "Model", "Duration", "Parking Slot", "Services"
				}
				) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);

		JButton btnDeleteSelected = new JButton("Delete Selected");
		btnDeleteSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableMod = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
		            
		            String slotID = tableMod.getValueAt(selectedRow, 4).toString();
		            
		          
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

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setForeground(new Color(64, 0, 64));
		panel_1_1.setBackground(Color.GRAY);
		panel_1_1.setBounds(0, 409, 1117, 69);
		contentPane.add(panel_1_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(139, 139, 139));
		panel_3.setBounds(0, 79, 457, 320);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("LIST A CAR HERE");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(10, 10, 243, 35);
		panel_3.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Manufacturer:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 102, 101, 35);
		panel_3.add(lblNewLabel_2);

		manuField = new JTextField();
		manuField.setBounds(99, 111, 132, 18);
		panel_3.add(manuField);
		manuField.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("Model:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_1.setBounds(10, 137, 101, 35);
		panel_3.add(lblNewLabel_2_1);

		modelField = new JTextField();
		modelField.setColumns(10);
		modelField.setBounds(99, 147, 132, 18);
		panel_3.add(modelField);

		JLabel lblNewLabel_2_1_1 = new JLabel("Parking Spot:");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_1_1.setBounds(10, 182, 101, 35);
		panel_3.add(lblNewLabel_2_1_1);

		parkingSpotField = new JTextField();
		parkingSpotField.setEditable(false);
		parkingSpotField.setToolTipText("");
		parkingSpotField.setColumns(10);
		parkingSpotField.setBorder(BorderFactory.createTitledBorder("Selected"));

		parkingSpotField.setBounds(176, 194, 58, 33);
		panel_3.add(parkingSpotField);

		String[] duration = {"-","Monthly(PHP 100)", "Bi-Monthly(PHP 250)", "Quarterly(PHP 400)", "Semi-Annually(PHP 600)", "Annually(PHP 1000)", "Bi-Annually(PHP 2000)", "Lifetime(PHP 5000)"};
		JComboBox comboDuration = new JComboBox(duration);
		comboDuration.setBounds(99, 245, 132, 20);
		panel_3.add(comboDuration);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("Duration:");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_1_1_1.setBounds(10, 237, 101, 35);
		panel_3.add(lblNewLabel_2_1_1_1);

		JLabel lblNewLabel_2_2 = new JLabel("Vehicle Type:");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_2.setBounds(10, 64, 101, 35);
		panel_3.add(lblNewLabel_2_2);

		String[] vehicleType = {"-", "Sedan", "Coupe", "Hatchback", "Wagon", "SUV", "Truck", "Limousine", "Convertible", "Van"};
		JComboBox comboVehicleType = new JComboBox(vehicleType);
		comboVehicleType.setBounds(99, 72, 132, 20);
		panel_3.add(comboVehicleType);

		JButton btnChooseSlot = new JButton("Choose");
		btnChooseSlot.setBounds(99, 183, 67, 34);
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

		JButton btnList_1 = new JButton("LIST");
		btnList_1.setBounds(310, 275, 126, 35);
		btnList_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 1. Validation check first
				if (manuField.getText().equals("") || modelField.getText().equals("") || 
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
						manuField.getText(),                               // Col 1
						modelField.getText(),                              // Col 2
						selectedDuration,                                  // Col 3
						parkingSpotField.getText(),                        // Col 4
						servicesList + " (PHP " + totalPrice + ")"         // Col 5
				};

				// Add to Table and Reset
				DefaultTableModel tblmod = (DefaultTableModel)table.getModel();
				tblmod.addRow(data);

				JOptionPane.showMessageDialog(null, "Information successfully listed. Total Price is: " + totalPrice, "Success", JOptionPane.INFORMATION_MESSAGE);

				// Reset fields
				manuField.setText("");
				modelField.setText("");
				parkingSpotField.setText("");
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
		
		JButton btnNewButton = new JButton("CLEAR");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnNewButton.addActionListener(new ActionListener() {
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
		                parkingSpotField.setText(""); // Just clear it if parsing fails
		            }
		        }
		    }
		});
		btnNewButton.setBounds(99, 217, 67, 18);
		panel_3.add(btnNewButton);


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

