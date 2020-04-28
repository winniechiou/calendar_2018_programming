package fianl_nccuchecknote;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Welcome extends JFrame {
	private JPanel main;
	

	public static void main(String[] args) {
		Welcome window = new Welcome();
		window.setSize(700,500);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

	public Welcome() {
		

		main = new JPanel();
		main.setLayout(null);

		JLabel iconLabel = new JLabel();
		ImageIcon imageIcon = new ImageIcon("icon.png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageIcon = new ImageIcon(newimg);
		iconLabel.setIcon(imageIcon);
		main.add(iconLabel);
		iconLabel.setBounds(300, 30, 100, 100);

		JLabel name = new JLabel("NCCU Checknote");
		name.setFont(new Font("Arial", Font.BOLD, 16));
		name.setBounds(283, 140, 135, 20);
		main.add(name);

		JLabel introLabel = new JLabel(
				"<html>歡迎！\n你現在正在使用NCCU Check Note，一個針對學生，結合課表、行事曆以及筆記的程式，讓您的生活更便利！相信大家平常生活中，一定會覺得隨身帶上一個記事本、筆記本、課本又加上筆電，背上的書包十分沈重。為了減輕學生除了未來、課業、愛情以外也很沈重的書包負擔，我們研發了NCCU Check Note！如果在使用中有如何問題，請不要聯絡我們，我們沒有客服。</html>",
				SwingConstants.CENTER);
		introLabel.setBackground(new Color(0, 0, 0));
		introLabel.setFont(new Font("AppleGothic", Font.PLAIN, 20));
		introLabel.setForeground(new Color(0, 0, 0));
		main.add(introLabel);
		introLabel.setBounds(100, 200, 500, 200);

		JButton next = new JButton("Next");
		next.setBackground(new Color(237, 238, 197));
		main.add(next);
		next.setBounds(310, 400, 80, 40);
		
		class next implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
				//remove(main);
				Login login= new Login();
				login.setSize(700,500);
				login.setVisible(true);
				login.setDefaultCloseOperation(3);
				
				
				
			}
		}
		ActionListener add= new next();
		next.addActionListener(add);
		
		
		
		
		
		
		
		
		

		main.setBackground(new Color(239, 248, 251));

		//initialize();
		//JFrame frame = new JFrame("NCCUCheckNote");
		//frame.setSize(700, 500);
		//frame.setLocation(400, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setVisible(true);

		add(main);
		//frame.setVisible(true);
	}

	
	//private void initialize() {
		//frame = new JFrame("NCCUCheckNote");
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//}
}
