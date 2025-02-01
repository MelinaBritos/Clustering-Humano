package view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewGrupos {

	private static JFrame frame;
	private HashSet<HashSet<String>> grupos;

	public static void iniciar(HashSet<HashSet<String>> gruposPersonas, ArrayList<Double> estadisticas) {
		new ViewGrupos(gruposPersonas, estadisticas);
		ViewGrupos.frame.setVisible(true);
	}

	public ViewGrupos(HashSet<HashSet<String>> gruposPersonas, ArrayList<Double> estadisticas) {
		grupos = gruposPersonas;
		initialize(estadisticas);
	}

	@SuppressWarnings("serial")
	private void initialize(ArrayList<Double> estadisticas) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 128, 192));
		frame.setBounds(0, 0, 428, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblgrupos = new JLabel("Grupos");
		lblgrupos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblgrupos.setHorizontalAlignment(SwingConstants.CENTER);
		lblgrupos.setBounds(26, 11, 340, 25);
		frame.getContentPane().add(lblgrupos);

		JButton btnvolver = new JButton("Volver");
		btnvolver.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnvolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View view = new View();
				view.iniciar();
				frame.dispose();
			}
		});
		btnvolver.setBounds(74, 627, 89, 23);
		frame.getContentPane().add(btnvolver);

		JButton btnestadisticas = new JButton("Ver estadisticas");
		btnestadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewEstadisticas viewEstadisticas = new ViewEstadisticas(estadisticas);
				viewEstadisticas.iniciar(estadisticas);
				frame.setVisible(false);
			}
		});
		btnestadisticas.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnestadisticas.setBounds(208, 627, 130, 23);
		frame.getContentPane().add(btnestadisticas);

		int y = 40;

		for (HashSet<String> grupo : grupos) {

			DefaultTableModel model = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			model.addColumn("Nombres");

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(26, y, 340, 129);
			frame.getContentPane().add(scrollPane);

			JTable nombres = new JTable();
			scrollPane.setViewportView(nombres);
			nombres.setModel(model);

			nombres.setBounds(26, y, 340, 129);
			y = y + 150;

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			int columnCount = nombres.getColumnCount();
			for (int j = 0; j < columnCount; j++) {
				nombres.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
			}

			for (String nombre : grupo) {
				model.addRow(new String[] { nombre });
			}

			nombres.setModel(model);

		}
		frame.setLocationRelativeTo(null);
	}

	public static void setVisible(boolean b) {
		frame.setVisible(true);

	}
}
