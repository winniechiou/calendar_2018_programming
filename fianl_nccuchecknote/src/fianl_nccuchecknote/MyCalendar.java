package fianl_nccuchecknote;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;




public class MyCalendar extends javax.swing.JFrame {
	
	private static JDesktopPane desktopPane1;
	private JDesktopPane desktopPane2;
	private static JTextField yearField;
	private static JComboBox monthComboBox;
	private JLabel yearLabel;
	private JLabel warnLabel;
	private JButton addEventBtn;
	private JButton nextMonthBtn;
	private JButton lastMonthBtn;
	private JButton goBtn;
	private JButton eventBtn;
	private String userName;
	
	private static ResultSet result;
	
	
	//public static void main(String[] args) throws IOException
	//{	
		
		//MyCalendar calendar = new MyCalendar("Simon");
		//calendar.setSize(900,850);
		//calendar.setVisible(true);
		//calendar.setDefaultCloseOperation(3);
	//}
	
	
	public JDesktopPane getPanel1() {
		
		return desktopPane1;
	}
	
	public JDesktopPane getPane2() {
		
		return desktopPane2;
	}
	
	
	
	public MyCalendar(String username)
	{
		userName = username;
		createGUI();
	}
	
	public int[] getdate()
	 {
		 int[] date_array = new int[3];
		 Calendar calendar = new GregorianCalendar();
		 date_array[0] = calendar.get(Calendar.YEAR);
		 date_array[1] = calendar.get(Calendar.MONTH);
		 date_array[2] = calendar.get(Calendar.DAY_OF_MONTH);
		 return date_array;
	 }
	
	public void new_btn()
	 {
		try
		{
			int year, month;
			year = Integer.parseInt(yearField.getText());
			month = monthComboBox.getSelectedIndex() + 1;
			date_btn_create(year, month);
			
		}catch(Exception e) {
			warnLabel.setText("請輸入1582以上的年份");
			int[] now = new int[3];
			now = getdate();
			date_btn_create(now[0],now[1]);
		}
			
	 }
	
	public void date_btn_create(int year,int month)
	 {
		int x=0, y=0, week_add=0, dom=0, dow=0, btn_y=0;
		try 
		{
		desktopPane1.remove(desktopPane2);
		desktopPane2 = new JDesktopPane();
		desktopPane1.add(desktopPane2);
		desktopPane2.setBounds(20,100,840,600);
		
		Calendar calendar = new GregorianCalendar(year,month,1); 
		dom = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		dow = calendar.get(Calendar.DAY_OF_WEEK);
		
		switch(dow)
		{
			case 1:
				week_add = 0;
				break;
			case 2:
				week_add = 120;
				break;
			case 3:
				week_add = 240;
				break;
			case 4:
				week_add = 360;
				break;
			case 5:
				week_add = 480;
				break;
			case 6:
				week_add = 600;
				break;
			case 7:
				week_add = 720;
				break;
		}
		
		JPanel[] datePanels = new JPanel[dom];
		JLabel[] dateNumLabel =new JLabel[dom];
		JScrollPane[] scrollPane =new JScrollPane[dom];
		for(int i =0; i < dom; i++)
		{
			datePanels[i] = new JPanel();
			datePanels[i].setLayout(null);
			
			dateNumLabel[i] = new JLabel(" " + (i+1) + " ");
			dateNumLabel[i].setBounds(3,3,20,20);
			scrollPane[i] = new JScrollPane(dateNumLabel[i]);
			datePanels[i].add(dateNumLabel[i]);
			desktopPane2.add(datePanels[i]);			
			
			
			if ((i-(dow-1)>0 && (i+(dow-1))%7==0) || ((i+(dow-1))%7==0 && i != 0))
		      {
		        x=0;
		        y++;
		        week_add=0;
		      }
		     datePanels[i].setBounds(x*120 + week_add, y*100, 120, 100);//(Y���ĴX�ӫ��s*����100)panel�e��120����100
		     datePanels[i].setBorder(new TitledBorder(""));//�]�w���s��r���۰ʽվ�j�p
		     datePanels[i].setBackground(new java.awt.Color(237,238,197));
		    if(check_todo_list(i) != null)
		     {
		    	while(result.next())
		    	{	
		    		eventBtn = new JButton(result.getString("eventName"));
		    		eventBtn.setBounds(2,20+btn_y*25,116,25);
		    		datePanels[i].add(eventBtn);
		    		eventBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonActionPerformed(evt);
						}
					});
	    			btn_y++;
		    	}
		    	btn_y = 0;
		     }
		     int[] now = new int[3];//��Ѥ�����C��
		     now = getdate();
		     if(now[0] == year && now[1] == month && now[2] == i+1)
		    	 datePanels[i].setBackground(new java.awt.Color(31,170,209));
		      
		     x++;
		      //�p�G��Ѥ�����N��ƶ��A��JPanel, addActionPerformed
		}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	 }
	
	
	private void createGUI()//����GUI
	{	
		{//�إ߮ୱ�}�l
		desktopPane1 = new JDesktopPane();
		getContentPane().add(desktopPane1, BorderLayout.CENTER);
		desktopPane1.setPreferredSize(new java.awt.Dimension(800, 530));//�]�w�ୱ�j�p
		desktopPane1.setBackground(new java.awt.Color(239,248,251));
		}//�إ߮ୱ����
		{//�إ߷s�W���ʫ��s�}�l
		addEventBtn = new JButton("新增活動");
		addEventBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		addEventBtn.setBounds(740, 700, 100, 27);
		desktopPane1.add(addEventBtn);
		}//�إ߷s�W���ʫ��s����
		{//�إߩ����ୱ�}�l
	    desktopPane2 = new JDesktopPane();
	    desktopPane1.add(desktopPane2);
	    desktopPane2.setBounds(0, 100, 860, 500);
	    desktopPane2.setBackground(new java.awt.Color(146,205,176));
		}//�إߩ����ୱ����
		
		int[] now = new int[3];//�o��{�b�ɶ�
		now = getdate(); 
		String sYear = String.valueOf(now[0]);
		String sMonth = String.valueOf(now[1]);
		{//�إ߿�J��ܮɶ��϶}�l
		yearField = new JTextField();
		yearField.setText(sYear);
		yearField.setBounds(300, 40, 100, 25);
		desktopPane1.add(yearField);
		yearField.setColumns(10);
		
		yearLabel = new JLabel("年");
		yearLabel.setBounds(418, 46, 22, 19);
		desktopPane1.add(yearLabel);
		
		ComboBoxModel comboBoxModel = new DefaultComboBoxModel<>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
		monthComboBox = new JComboBox<>();
		monthComboBox.setModel(comboBoxModel);
		monthComboBox.setSelectedIndex(now[1]);
		monthComboBox.setBounds(459, 40, 40, 25);
		desktopPane1.add(monthComboBox);
		
		JLabel monthLabel = new JLabel("月");
		monthLabel.setBounds(518, 46, 22, 19);
		desktopPane1.add(monthLabel);
		}//�إ߿�J��ܮɶ��ϵ���
		{//�إߩ��W�U�Ӥ���s�}�l
		lastMonthBtn = new JButton("上");
		lastMonthBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				lastMonthActionPerformed(evt);
			}
		});
		lastMonthBtn.setBounds(50, 40, 55, 25);
		desktopPane1.add(lastMonthBtn);
		
		nextMonthBtn = new JButton("下");
		nextMonthBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{
				nextMonthActionPerformed(evt);
			}
		});
		nextMonthBtn.setBounds(735, 40, 55, 25);
		desktopPane1.add(nextMonthBtn);
		}//�إߩ��W�U�Ӥ���s����
		{//�إ߬d�߫��s�}�l
		goBtn = new JButton("GO");
		goBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) 
			{
				goBtnActionPerformed(evt);
			}
		});
		goBtn.setBounds(555, 40, 55, 25);
		desktopPane1.add(goBtn);
		}//�إ߬d�߫��s����
		{//�إ߬P�����Ҷ}�l
		JLabel lblSun = new JLabel("Sun");
		lblSun.setFont(new Font("Arial", Font.BOLD, 16));
		lblSun.setForeground(Color.RED);
		lblSun.setBounds(45, 78, 40, 19);
		desktopPane1.add(lblSun);
		
		JLabel lblMon = new JLabel("Mon");
		lblMon.setFont(new Font("Arial", Font.BOLD, 16));
		lblMon.setBounds(165, 78, 40, 19);
		desktopPane1.add(lblMon);
		
		JLabel lblTue = new JLabel("Tue");
		lblTue.setFont(new Font("Arial", Font.BOLD, 16));
		lblTue.setBounds(285, 78, 30, 19);
		desktopPane1.add(lblTue);
		
		JLabel lblWed = new JLabel("Wed");
		lblWed.setFont(new Font("Arial", Font.BOLD, 16));
		lblWed.setBounds(405, 78, 40, 19);
		desktopPane1.add(lblWed);
		
		JLabel lblThu = new JLabel("Thu");
		lblThu.setFont(new Font("Arial", Font.BOLD, 16));
		lblThu.setBounds(525, 78, 30, 19);
		desktopPane1.add(lblThu);
		
		JLabel lblFri = new JLabel("Fri");
		lblFri.setFont(new Font("Arial", Font.BOLD, 16));
		lblFri.setBounds(645, 78, 30, 19);
		desktopPane1.add(lblFri);
		
		JLabel lblSat = new JLabel("Sat");
		lblSat.setFont(new Font("Arial", Font.BOLD, 16));
		lblSat.setBounds(765, 78, 30, 19);
		desktopPane1.add(lblSat);
		}//�إ߬P�����ҵ���
		{
		warnLabel = new JLabel("請輸入1582以上的年份");
		warnLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		warnLabel.setForeground(Color.RED);
		warnLabel.setBounds(130, 40, 150, 25);
		desktopPane1.add(warnLabel);
		warnLabel.setVisible(false);
		}
		
		date_btn_create(now[0], now[1]);
		
		
	}
	
	public ResultSet check_todo_list(int day) throws SQLException 
	{	String date = yearField.getText() + "/" + (monthComboBox.getSelectedIndex()+1)
					+ "/" + (day+1);
		String str = "SELECT eventName FROM toDoList WHERE date = '" + date +"' AND userName = '"+ userName +"' ";
		DataSource d = new DataSource();
		Statement stat;
		try
		{
			stat = d.getConnection().createStatement();
			boolean hasResultSet = stat.execute(str);
			if(hasResultSet)
			{
				result = stat.getResultSet();
			}
			
		}
		
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{	
			System.out.println(date);
			return result;
		}
	}
	
	public void executeStatement(String st) 
	{
		DataSource d = new DataSource();
		Statement statement;
		try 
		{
			statement = d.getConnection().createStatement(); 
			boolean hasResultSet = statement.execute(st);
			if(hasResultSet)
			{
				result = statement.getResultSet();
			}
		}
		
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	private void goBtnActionPerformed(ActionEvent evt)
	{	
		try
		{
			warnLabel.setVisible(false);
			if(Integer.parseInt(yearField.getText()) <= 1582)
			{
				warnLabel.setVisible(true);
			}
			else
				date_btn_create(Integer.parseInt(yearField.getText()), monthComboBox.getSelectedIndex());
		}
		catch(NumberFormatException ex)
		{
			warnLabel.setVisible(true);
		}
	}
	
	private void buttonActionPerformed(ActionEvent evt)//�ƥ���s���UĲ�o�ƥ�}�l
	{
		//���t�@��class ��
	}
	
	private void addEventBtnActionPerformed(ActionEvent evt)//�s�W�ƥ���s���UĲ�o�ƥ�}�l
	{
		//instantiate �t�@��class ��
	}
	
	private void nextMonthActionPerformed(ActionEvent evt)//���U�Ӥ���s���UĲ�o�ƥ�}�l
	{
		warnLabel.setVisible(false);
		if(Integer.parseInt(yearField.getText()) <= 1582)
		{
			warnLabel.setVisible(true);
		}
		else
		{
			if(monthComboBox.getSelectedIndex() + 1 == 12)
			{
				yearField.setText("" + (Integer.parseInt(yearField.getText()) + 1));
				monthComboBox.setSelectedIndex(0);
				date_btn_create(Integer.parseInt(yearField.getText()) + 1, 1);
			}
			else
			{
				monthComboBox.setSelectedIndex(monthComboBox.getSelectedIndex() + 1);
				date_btn_create(Integer.parseInt(yearField.getText()), monthComboBox.getSelectedIndex()); //�n�H��J���P�_�з�,�]���b
			}
		}
	}
	
	private void lastMonthActionPerformed(ActionEvent evt)
	{
		if(monthComboBox.getSelectedIndex() + 1 == 1)
		{
			yearField.setText("" + (Integer.parseInt(yearField.getText()) - 1));
			monthComboBox.setSelectedIndex(11);
			date_btn_create(Integer.parseInt(yearField.getText()), 12);
		}
		else
		{
			monthComboBox.setSelectedIndex(monthComboBox.getSelectedIndex() - 1);
			date_btn_create(Integer.parseInt(yearField.getText()), monthComboBox.getSelectedIndex()); //�n�H��J���P�_�з�,�]���b
		}
	}
	
}
