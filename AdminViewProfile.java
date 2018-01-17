import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Label;
import java.awt.Font;
import java.awt.TextField;
import java.awt.TextArea;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*class Customer
{
	String n,a,gender,add,c,s,em,mob,un,type,pass;
	Customer(String n,String a,String gender,String add,String c,String s,String em,String mob,String un,String pass2,String type)
	{
		this.n=n;
		this.a=a;
		this.gender=gender;
		this.add=add;
		this.c=c;
		this.s=s;
		this.em=em;
		this.mob=mob;
		this.un=un;
		this.pass=pass2;
		this.type=type;
	}
}*/

public class AdminViewProfile extends JFrame {

	private JPanel ContentPane;
	String n,a,gender,add,c,s,em,mob,un,type,pass;
	Connection connection =null;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminViewProfile frame = new AdminViewProfile("temp");					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public AdminViewProfile(String accountNo) {
		setTitle("Admin View Profile");
		connection=SQLConnection.dbConnection();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 418, 409);
		ContentPane = new JPanel();
		ContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ContentPane);
		ContentPane.setLayout(null);
		
		Label label = new Label("Full Name");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label.setBounds(10, 39, 62, 22);
		ContentPane.add(label);
		
		Label label_1 = new Label("Age");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_1.setBounds(10, 67, 62, 22);
		ContentPane.add(label_1);
		
		Label label_2 = new Label("Address");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_2.setBounds(10, 95, 62, 22);
		ContentPane.add(label_2);
		
		TextField name = new TextField();
		name.setBounds(99, 39, 269, 22);
		ContentPane.add(name);
		
		TextField age = new TextField();
		age.setBounds(99, 67, 35, 22);
		ContentPane.add(age);
		
		TextField gender_tf = new TextField();
		gender_tf.setEditable(false);
		gender_tf.setBounds(249, 67, 62, 22);
		ContentPane.add(gender_tf);
		
		TextField ac_type = new TextField();
		ac_type.setEditable(false);
		ac_type.setBounds(99, 263, 86, 22);
		ContentPane.add(ac_type);
		
		Label label_3 = new Label("City");
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_3.setBounds(10, 123, 62, 22);
		ContentPane.add(label_3);
		
		Label label_4 = new Label("State");
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_4.setBounds(208, 123, 35, 22);
		ContentPane.add(label_4);
		
		Label label_5 = new Label("Gender");
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_5.setBounds(181, 67, 46, 22);
		ContentPane.add(label_5);
		
		TextField city = new TextField();
		city.setBounds(99, 123, 86, 22);
		ContentPane.add(city);
		
		TextField state = new TextField();
		state.setEditable(false);
		state.setBounds(249, 123, 124, 22);
		ContentPane.add(state);
		
		TextField address = new TextField();
		address.setBounds(99, 95, 269, 22);
		ContentPane.add(address);
		
		Label label_6 = new Label("E-Mail\r\n");
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_6.setBounds(10, 151, 62, 22);
		ContentPane.add(label_6);
		
		TextField email = new TextField();
		email.setBounds(99, 151, 269, 22);
		ContentPane.add(email);
		
		Label label_7 = new Label("Password");
		label_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_7.setBounds(10, 235, 62, 22);
		ContentPane.add(label_7);
		
		
		Label label_8 = new Label("Username\r\n");
		label_8.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_8.setBounds(10, 207, 62, 22);
		ContentPane.add(label_8);
		
		TextField username = new TextField();
		username.setBounds(99, 207, 139, 22);
		ContentPane.add(username);
		
		Label label_9 = new Label("Account type");
		label_9.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_9.setBounds(10, 263, 75, 22);
		ContentPane.add(label_9);
		
		Label label_10 = new Label("Mobile no.");
		label_10.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_10.setBounds(10, 179, 62, 22);
		ContentPane.add(label_10);
		
		TextField mobile = new TextField();
		mobile.setBounds(99, 179, 86, 22);
		ContentPane.add(mobile);
		
		TextField pwd = new TextField();
		pwd.setBounds(99, 235, 139, 22);
		pwd.setEchoChar('*');
		ContentPane.add(pwd);
		
		
		CheckboxGroup gen = new CheckboxGroup();
		
		CheckboxGroup acc_type = new CheckboxGroup();
		
		Label label_11 = new Label("View Profile");
		label_11.setForeground(Color.BLUE);
		label_11.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_11.setBounds(10, 11, 216, 22);
		ContentPane.add(label_11);
		
		Label label_12 = new Label("Balance");
		label_12.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_12.setBounds(10, 291, 62, 22);
		ContentPane.add(label_12);
		
		TextField balance = new TextField();
		balance.setBounds(99, 291, 86, 22);
		ContentPane.add(balance);
		//JOptionPane.showMessageDialog(null, "In here");
		try{
		String query="select * from Bankdata where accountNo=?";
		PreparedStatement pst=connection.prepareStatement(query);
		pst.setString(1, accountNo);
		ResultSet rs=pst.executeQuery();
		while(rs.next()){
			name.setText(rs.getString("name"));
			age.setText(Integer.toString(rs.getInt("age")));
			address.setText(rs.getString("address"));			
			city.setText(rs.getString("city"));
			state.setText(rs.getString("state"));
			email.setText(rs.getString("email"));
			mobile.setText(Long.toString(rs.getLong("mobile")));
			username.setText(rs.getString("username"));
			pwd.setText(rs.getString("password"));
			balance.setText(Integer.toString(rs.getInt("balance")));
			if(rs.getString("gender").equals("male")){
				gender_tf.setText("Male");
			}else{
				gender_tf.setText("Female");
			}
			if(rs.getString("accType").equals("savings")){
				ac_type.setText("Savings");
			}else{
				ac_type.setText("Current");
			}
		}		
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,e);
		}
		name.setEditable(false);
		age.setEditable(false);
		address.setEditable(false);
		city.setEditable(false);
		email.setEditable(false);
		mobile.setEditable(false);
		username.setEditable(false);
		pwd.setEditable(false);
		balance.setEditable(false);
		
		Button back = new Button(" Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminViewProfile.this.dispose();
				AdminLogin a=new AdminLogin();
				a.setVisible(true);
			}
		});
		back.setBounds(99, 339, 70, 22);
		ContentPane.add(back);
		
		/*Button Logout = new Button("Log Out");
		Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminViewProfile.this.dispose();
				LoginPage l=new LoginPage();
				l.frmLogIn.setVisible(true);
			}
		});
		Logout.setBounds(233, 339, 70, 22);
		ContentPane.add(Logout);*/
		
	
		
		/*Button edit = new Button("Edit");
		edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				name.setEditable(true);
				age.setEditable(true);
				address.setEditable(true);
				city.setEditable(true);
				state.setEditable(true);
				email.setEditable(true);
				mobile.setEditable(true);
				username.setEditable(true);
				pwd.setEditable(true);
				balance.setEditable(true);
				save.setVisible(true);
				
			}
		});
		edit.setForeground(Color.BLUE);
		edit.setBackground(Color.WHITE);
		edit.setFont(new Font("Times New Roman", Font.BOLD, 12));
		edit.setBounds(337, 11, 55, 22);
		contentPane.add(edit);*/
	}
}
