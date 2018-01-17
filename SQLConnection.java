import java.sql.*;
import javax.swing.*;
public class SQLConnection {
	
	Connection connection=null;
	
	public static Connection dbConnection(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection connection=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\kapre\\workspace\\BankManagementSystem\\Bankdata.sqlite");
			//JOptionPane.showMessageDialog(null,"Connection Successful");
			return connection;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
}
