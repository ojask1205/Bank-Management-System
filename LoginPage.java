import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


import java.awt.Label;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;


public class LoginPage extends JFrame implements ActionListener
{

	public JFrame frmLogIn;
	private Button btn_create, btn_login, btn_reset;
	private TextField tf_un, tf_pw;
	private Label lbl_un, lbl_pw;
	Connection connection=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frmLogIn.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
		connection=SQLConnection.dbConnection();
	}
	
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals("Reset"))
		{
			tf_un.setText(" ");
			tf_un.setText("");
			tf_pw.setText(" ");
			tf_pw.setText("");
		}
		else if(e.getActionCommand().equals("Log In"))
		{
			//tf_un.setText("Log In");
			//tf_pw.setText("");
			
			//---------------database connection Using Excel--------------
			/*File f=new File("C:\\Users\\Murtaza.taheribrahim-PC\\Desktop\\Book2.xls");		
			Workbook wb = null;
			try {
				wb = Workbook.getWorkbook(f);
			} catch (BiffException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Sheet sh = null;
			try{
				sh=wb.getSheet("Sheet1");
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2);
			}			
			int rows=sh.getRows();			
			System.out.println("rows  = "+ rows);
			String username=tf_un.getText();
			String password=tf_pw.getText();			
			boolean check=false;
			for(int i=1;i<rows;i++){				
				Cell c=sh.getCell(0,i);
				String Input_un=c.getContents();
				c=sh.getCell(1, i);
				String Input_pw=c.getContents();
				
				if(Input_un.equals(username)&&Input_pw.equals(password)){
					check=true;
				}				
			}			
			if(check){
				JOptionPane.showMessageDialog(null, "Username and Password is Correct");
			}else{
				JOptionPane.showMessageDialog(null, "Username and Password is Incorrect");
				tf_un.setText(null);
				tf_pw.setText(null);
			}*/
			
			//-----------------database connection using SQL---------------------------
			if(tf_un.getText().equals("admin") && tf_pw.getText().equals("admin123"))
			{
				
				frmLogIn.dispose();
				tf_un.setText(null);
				tf_pw.setText(null);
				AdminLogin a= new AdminLogin();
				a.setVisible(true);
			}
			else
			{
			try{
				String query="select * from Bankdata where username=?";
				PreparedStatement pst=connection.prepareStatement(query);
				pst.setString(1, tf_un.getText());
				
				ResultSet rs=pst.executeQuery();
				int count=0;
				boolean found=false;
				while(rs.next()){
					//count++;
					found=true;
					if(rs.getString("password").equals(tf_pw.getText())){							
						frmLogIn.dispose();
						UserLogin b=new UserLogin(tf_un.getText());
						tf_un.setText(null);
						tf_pw.setText(null);
						b.setVisible(true);
					}else{
						JOptionPane.showMessageDialog(null, "Password is Incorrect");
						tf_pw.setText(null);
						tf_pw.requestFocus();
					}
				}
				if(!found){
					JOptionPane.showMessageDialog(null, "Username is Incorrect");
					tf_un.setText(null);
					tf_pw.setText("   ");
					tf_pw.setText("");
					tf_un.requestFocus();
				}
			}catch(Exception e1){
				JOptionPane.showMessageDialog(null, e1);
			}/*finally{
				try {
					connection.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}*/
			}
			
			
		}
		else if(e.getActionCommand().equals("Create New"))
		{
			NewAccount newAcc=new NewAccount();
			newAcc.setVisible(true);
			frmLogIn.dispose();
		}
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogIn = new JFrame();
		frmLogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogIn.setTitle("Log In");
		frmLogIn.setResizable(false);
		frmLogIn.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		frmLogIn.setBounds(100, 100, 377, 208);
		frmLogIn.getContentPane().setLayout(null);
		
		lbl_un = new Label("Username:");
		lbl_un.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lbl_un.setBounds(10, 25, 83, 30);
		frmLogIn.getContentPane().add(lbl_un);
		
		lbl_pw = new Label("Password:");
		lbl_pw.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lbl_pw.setBounds(10, 75, 83, 30);
		frmLogIn.getContentPane().add(lbl_pw);
		
		tf_un = new TextField();
		tf_un.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tf_un.setBounds(107, 25, 228, 30);
		frmLogIn.getContentPane().add(tf_un);
		
		tf_pw = new TextField();
		tf_pw.setFont(new Font("Times New Roman", Font.BOLD, 14));
		tf_pw.setEchoChar('*');
		tf_pw.setBounds(107, 75, 228, 30);
		frmLogIn.getContentPane().add(tf_pw);
		
		
		btn_login = new Button("Log In");
		btn_login.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btn_login.setBounds(252, 129, 83, 30);
		btn_login.addActionListener(this);
		frmLogIn.getContentPane().add(btn_login);
		
		btn_create = new Button("Create New");
		btn_create.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btn_create.setBounds(10, 129, 96, 30);
		btn_create.addActionListener(this);
		frmLogIn.getContentPane().add(btn_create);
		
		btn_reset = new Button("Reset");		
		btn_reset.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btn_reset.setBounds(163, 129, 83, 30);
		btn_reset.addActionListener(this);	
		frmLogIn.getContentPane().add(btn_reset);
	}
}
