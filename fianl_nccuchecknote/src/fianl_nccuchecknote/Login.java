package fianl_nccuchecknote;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class Login extends JFrame {
	
	private JTextField text_name;
	private JPasswordField text_password;
	private ResultSet loginResult;
	private String userName;
	private JLabel failedMsg;
	
	
	public Login()
	{
		create_login();
		setSize(700,500);
		setVisible(true);
		setDefaultCloseOperation(3);
	}
	
	public void create_login()
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		add(mainPanel);
		
		JLabel iconLabel = new JLabel();
		ImageIcon imageIcon = new ImageIcon("icon.png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  
		iconLabel.setIcon(imageIcon);
		mainPanel.add(iconLabel);
		iconLabel.setBounds(300, 10, 100, 100);
		JLabel name =new JLabel("NCCU Checknote"); 
		name.setFont(new Font("Arial", Font.BOLD, 16));
		name.setBounds(280, 120, 135, 20);
		mainPanel.add(name);
		
		JLabel lablname = new JLabel("帳號");
		lablname.setFont(new Font("密碼",Font.BOLD,16));
		lablname.setBounds(180, 214, 90, 22);
		mainPanel.add(lablname);
		//lablname.setBounds();
		
		//¨Ò¶µ¤ÆJTextField¼ÐÅÒª«¥ó¤Æ
		text_name = new JTextField();
		mainPanel.add(text_name);
		text_name.setColumns(10);
		text_name.setBounds(301, 214, 179, 25);
		
		//¨Ò¶µ¤ÆJLabel¼ÐÅÒª«¥ó¡A¸Óª«¥óÅã¥Ü¡§±K½X¡¨
		JLabel lablpass = new JLabel("密碼");
		lablpass.setFont(new Font("帳號",Font.BOLD,16));
		lablpass.setBounds(200, 262, 90, 22);
		mainPanel.add(lablpass);
		
		//¨Ò¶µ¤ÆJPasswordField
		text_password = new JPasswordField();
		mainPanel.add(text_password);
		text_password.setColumns(10);
		text_password.setBounds(301, 262, 179, 25);
		
		//¨Ò¶µ¤ÆJButton¤¸¥ó
		JButton loginBtn = new JButton("～～～");
		loginBtn.setFont(new Font("～～～～～～～～～",Font.BOLD,16));
		//³]©w«öÁä¤j¤p
		mainPanel.add(loginBtn);
		loginBtn.setBounds(301, 350, 99, 27);
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				loginActionPerformed();
			}
		});
		failedMsg = new JLabel("！！！！！！");
		mainPanel.add(failedMsg);
		failedMsg.setVisible(false);
		failedMsg.setFont(new Font("·s²Ó©úÅé",Font.BOLD,14));
		failedMsg.setForeground(Color.RED);
		failedMsg.setBounds(485, 262, 179, 25);
	}
	
	public void loginActionPerformed() 
	{	
		String input_userName = text_name.getText();
		String input_password = text_password.getText();
		String str = "SELECT userName, password FROM login WHERE userName ='"+ input_userName +"'AND password ='"+ input_password +"' ";
		DataSource d = new DataSource();
		Statement stat;
		try
		{
			stat = d.getConnection().createStatement();
			boolean hasResultSet = stat.execute(str);
			if(hasResultSet)
			{
				loginResult = stat.getResultSet();
				while(loginResult.next())
					userName = loginResult.getString("userName");
			}
			else
			{
				failedMsg.setVisible(true);
			}	
			
		}
		
		catch(SQLException e)
		{	
			failedMsg.setVisible(true);
			System.out.println(e.getMessage());
		}
	}
	
}
