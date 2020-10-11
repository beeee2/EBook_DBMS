package main_panel;

import java.awt.*;
import javax.swing.*;

import com.sun.tools.javac.Main;

import java.awt.event.*;
import DB.*;

class Panel extends JPanel
{
	Member_DB memberdb = new Member_DB();
	
	private CardLayout card;
	private final Panel me;
	
	private JButton Login_Button = new JButton(); // 로그인 버튼
	
	private JPanel Login_Panel = new JPanel(); // 첫번째 판넬
	private JPanel BookList_Panel= new JPanel(); // 두번째 판넬
	
	private JTextField ID_field = new JTextField(); // ID 필드
	private JPasswordField PASS_field = new JPasswordField(); // PASSWORD 필드
	
	private JLabel ID_Label = new JLabel(); // id 라벨
	private JLabel PASS_Label = new JLabel(); // pass 라벨
	
	private JLabel EBOOK_Photo_Label = new JLabel(); // 메인 EBOOK 그림
	
	public void EBOOK_Photo()
	{
		EBOOK_Photo_Label.setIcon(new ImageIcon("C:\\Users\\hyunb\\eclipse-workspace\\Card_Ebook\\src\\image\\EBOOK.png"));
		Login_Panel.add(EBOOK_Photo_Label);
		EBOOK_Photo_Label.setBounds(50, 0, 300, 300);
	}
	
	public void ID_PASS()
	{
		Login_Panel.add(ID_field);
		ID_field.setBounds(200, 250, 100, 40);
		
		Login_Panel.add(PASS_field);
		PASS_field.setBounds(200, 300, 100, 40);
		PASS_field.setEchoChar('*');
	}
	
	public void ID_Label()
	{
		ID_Label.setText("ID");
		Login_Panel.add(ID_Label);
		ID_Label.setBounds(150, 250, 100, 40);
	}
	
	public void PASS_Label()
	{
		PASS_Label.setText("PASSWORD");
		Login_Panel.add(PASS_Label);
		PASS_Label.setBounds(100, 300, 150, 40);
	}
	
	public void Login_Button()
	{
		Login_Button.setText("로그인");
		Login_Panel.add(Login_Button);
		Login_Button.setBounds(200, 350, 100, 40);
	}
	
	public void Login_Panel()
	{
		Login_Panel.setPreferredSize(new Dimension(500, 500));
		Login_Panel.setLayout(null);
	}
	
	public void Second_Panel()
	{
		BookList_Panel.setPreferredSize(new Dimension(500, 500));
		BookList_Panel.setLayout(null);
	}
	
	public Panel()
	{
		me = this;
		card = new CardLayout(0, 0);  //Card 형식 Layout
		setLayout(card);
		
		Login_Panel();
		Second_Panel();
		Login_Button();
		ID_PASS();
		ID_Label();
		PASS_Label();
		EBOOK_Photo();
		
		handler hand = new handler();
		
		Login_Button.addActionListener(hand);
		
		add("FP", Login_Panel);
		add("SP", BookList_Panel);
	}
	
	private class handler extends Frame implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			if(evt.getSource()==Login_Button)
			{
				boolean correct = false;
				correct = memberdb.Try_Login(ID_field.getText(), PASS_field.getText());
				if(correct == true)
				{
					ID_field.setText("");
					PASS_field.setText("");
					
					card.show(me, "SP");
				}
				else
				{
					// 로그인 실패
					ID_field.setText("");
					PASS_field.setText("");
				}
			}
		}
	}
}

public class main_panel {

	public static void main(String[] args) 
	{
		Member_DB memberdb = new Member_DB();
		
		JFrame frame = new JFrame("E북 프로젝트");
		Panel panel = new Panel();
		frame.add(panel);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
