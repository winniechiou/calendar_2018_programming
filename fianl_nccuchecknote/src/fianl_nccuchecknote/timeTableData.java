package fianl_nccuchecknote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.sql.Statement;

public class timeTableData implements TableModel {
	private String[] title = { "時間", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" };
	private String[][] data = new String[17][6];

	public timeTableData() {
		for (int i = 0; i < data[i].length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = "";
			}
		}
	}

//	public String[][] setRowName() {
////		data[1][0] = "A";
////		data[2][0] = "A";
//		return data;
//	}

	public void addTableModelListener(TableModelListener arg0) {
//		class nActionListener implements ActionListener {
//			public void actionPerformed(ActionEvent event) {
//				String content = timeTable.isSelected.getText();
//				PrintWriter out = new PrintWriter("Output.txt");
//				out.println(content);
//			}
//		}
	}

	public Class<?> getColumnClass(int arg0) {
		return String.class;
	}

	public int getColumnCount() {
		// 返回表的列數
		return 6;//
	}

	public String getColumnName(int day) {
		// 返回每一個列的名稱
//		executeStatement("SELECT classDay FROM Class_Schedule_new WHERE classDay=" + day);
		return title[day];
	}

	public int getRowCount() {
		// 返回表的行数
		return 17;//
	}

	public String getValueAt(int arg0, int arg1) {
		return data[arg0][arg1];
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return true;
	}

	public void removeTableModelListener(TableModelListener arg0) {

	}

	public void setValueAt(String arg0, int arg1, int arg2) {
		data[arg1][arg2] = arg0;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}

//	public void executeStatement(String st) {
//		SimpleDataSource s = new SimpleDataSource();
//		Statement stat;
//		try {
//			stat = s.getConnection().createStatement();
//			boolean hasResultSet = stat.execute(st);
//			if (hasResultSet) {
//				ResultSet result = stat.getResultSet();
//			}
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}
//	}

//	public static void main(String[] args) {
//	       
//	    }
	// set row name
	// get row name
}