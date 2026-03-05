package sye;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.EmptyBorder;
import java.awt.Dialog.ModalExclusionType;

public class ParkingLotUI extends JFrame {
	private boolean[][] savedData;
	private JTextField parkingSpotField;
	private ParkingSpotListener listener;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParkingLotUI frame = new ParkingLotUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();

				}
				SwingUtilities.invokeLater(() -> new ParkingLotUI().setVisible(true));
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ParkingLotUI(ParkingSpotListener listener, boolean[][] savedData) {

		this.parkingSpotField = parkingSpotField;
		this.savedData = savedData;
		this.listener = listener;

		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Digital Parking Lot Manager");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);



		// Data Model (8 rows, 10 columns)
		String[] columnNames = new String[10];
		for(int i = 0; i < 10; i++) columnNames[i] = "Col " + (i + 1);

		DefaultTableModel model = new DefaultTableModel(8, 10) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; 
			}
		};

		// JTable
		JTable table = new JTable(model);
		table.setRowHeight(70); 
		table.setIntercellSpacing(new Dimension(10, 10));
		table.setGridColor(Color.YELLOW); 
		table.setBackground(Color.LIGHT_GRAY);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setTableHeader(null);

		// Custom Renderer for "Asphalt" and "Cars"
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				c.setHorizontalAlignment(JLabel.CENTER);

				if ("OCCUPIED".equals(value)) {
					c.setBackground(new Color(200, 50, 50)); 
					c.setForeground(Color.WHITE);
					c.setText("TAKEN");
				} else {
					c.setBackground(new Color(60, 60, 60)); 
					c.setForeground(Color.LIGHT_GRAY);
					c.setText((row + 1) + "-" + (column + 1));
				}
				return c;
			}
		});
		
		// Click Interaction
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());

				if (row != -1 && col != -1) {
					// Check if already taken
					if (savedData[row][col]) {
						JOptionPane.showMessageDialog(null, "Wala na taken na sya :(", "Aray mo", JOptionPane.INFORMATION_MESSAGE);
						return;
					}

					// Main Window (The TextField)
					String spotID = (row + 1) + "-" + (col + 1);
					if (listener != null) {
						listener.onSpotSelected(row, col, spotID);
					}

					
					dispose(); 
				}
			}
		});
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 10; c++) {
				if (this.savedData[r][c]) { 
					table.setValueAt("OCCUPIED", r, c); 
				}
			}
		}

		// Wrap in ScrollPane and display
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		getContentPane().add(scrollPane);
		pack();
		setLocationRelativeTo(null);
	}

	public ParkingLotUI() {
		setResizable(false);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		// TODO Auto-generated constructor stub
	}


}

