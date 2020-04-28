package fianl_nccuchecknote;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class timeTable {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPanel tablePanel = new JPanel();
		JTable table = new JTable(new timeTableData());
		JScrollPane pane = new JScrollPane(table);
		JPanel adjustPanel = new JPanel();
		JButton add = new JButton("Add");
		JButton delete = new JButton("Delete");
		JPanel allPanel = new JPanel();

		tablePanel.setBounds(100, 100, 700, 500);
		tablePanel.add(pane);
		adjustPanel.add(add);
		adjustPanel.add(delete);
		adjustPanel.setBounds(100, 100, 700, 500);
		allPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		allPanel.add(tablePanel);
		allPanel.add(adjustPanel);

		frame.setSize(900, 850);

		frame.add(allPanel);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
