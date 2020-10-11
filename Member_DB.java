package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Member_DB {

	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public static void main(String[] args) {}
	
	public Member_DB()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dbms_class?serverTimezone=UTC&useSSL=false","root","3669");
			st = con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("데이터베이스 연결 오류: "+ e.getMessage());
		}
	}
	
	public boolean Try_Login(String input_id, String input_pass)
	{
		String SQL = "SELECT * FROM id_pass";
		try
		{
			ResultSet rs = st.executeQuery(SQL);
			
			while(rs.next()) {
				String id = rs.getString("id");
				System.out.println("id : "+ id);
				String pass = rs.getString("pass");
				System.out.println("pass : "+pass);
				
				if(input_id.equals(id) == true)
				{
					if(input_pass.equals(pass)==true)
					{
						JOptionPane.showMessageDialog(null, "id, pass 일치");
						return true;
					}
					else
					{
						JOptionPane.showMessageDialog(null, "pass 불일치");
						return false;
					}
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "id, pass 불일치");
					return false;
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

}
