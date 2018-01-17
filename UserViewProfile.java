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
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

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

public class UserViewProfile extends JFrame {

	private JPanel ContentPane;
	String n,a,gender,add,c,s,em,mob,un,type,pass;
	Connection connection =null;
	String tempUsername;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserViewProfile frame = new UserViewProfile("1");					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 */
	public UserViewProfile(String accountNo) {
		setBounds(100, 100, 418, 409);
		connection=SQLConnection.dbConnection();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setTitle("User View Profile");
		ContentPane = new JPanel();
		ContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ContentPane);
		ContentPane.setLayout(null);
		
		TextField username = new TextField();
		username.setBounds(99, 207, 139, 22);
		ContentPane.add(username);
		
		
		
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
		
		Button back = new Button("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserViewProfile.this.dispose();
				UserLogin l=new UserLogin(username.getText());
				l.setVisible(true);
			}
		});
		back.setBounds(233, 339, 70, 22);
		ContentPane.add(back);
		
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
		//Vector details=new Vector(11,3);
		Button save = new Button("Save details");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean invalid=false, incomplete=false;
				TextField tf=null,tf2=null;				
				
				if(name.getText().length()==0){
					//JOptionPane.showMessageDialog(null, "Name cannot be empty");
					if(tf==null)
						tf=name;
					name.setBackground(Color.red);
					incomplete=true;
					
				}
				else if(!name.getText().matches("^[a-zA-Z_ ]*$")){
					invalid=true;
					if(tf2==null)
						tf2=name;
					//name.setText(null);
					//name.selectAll();
				}
				
				if(age.getText().length()==0){
					//JOptionPane.showMessageDialog(null, "Name cannot be empty");
					if(tf==null)
						tf=age;
					age.setBackground(Color.red);
					incomplete=true;
					
				}
				else if(!age.getText().matches("^[0-9]*$")){
					invalid=true;
					if(tf2==null)
						tf2=age;
					//name.setText(null);
					//name.selectAll();
				}
				
				if(email.getText().length()==0){
					//JOptionPane.showMessageDialog(null, "Name cannot be empty");
					if(tf==null)
						tf=email;
					incomplete=true;
					email.setBackground(Color.red);
					
				}else if(!email.getText().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")){
					invalid=true;
					if(tf2==null)
						tf2=email;
					//name.setText(null);
					//email.selectAll();
				}
				if(mobile.getText().length()==0){
					//JOptionPane.showMessageDialog(null, "Name cannot be empty");
					if(tf==null)
						tf=mobile;
					mobile.setBackground(Color.red);
					incomplete=true;
					
				}else if(!mobile.getText().matches("^[0-9]{10}$")){
					invalid=true;
					if(tf2==null)
						tf2=mobile;
					//mobile.setText(null);
					//mobile.selectAll();
				}
				if(username.getText().length()==0){
					//JOptionPane.showMessageDialog(null, "Name cannot be empty");
					if(tf==null)
						tf=username;
					username.setBackground(Color.red);
					incomplete=true;
					
				}else if(!username.getText().matches("[A-Za-z\\d$@$!%*#?&]{4,}$")){
					invalid=true;
					if(tf2==null)
						tf2=username;
					//username.setText(null);
					//username.selectAll();
				}
				if(pwd.getText().length()==0){
					//JOptionPane.showMessageDialog(null, "Name cannot be empty");
					if(tf==null)
						tf=pwd;
					pwd.setBackground(Color.red);
					incomplete=true;					
				}else if(!pwd.getText().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")){
					invalid=true;
					if(tf2==null)
						tf2=pwd;
				}
				
				if(incomplete){
					JOptionPane.showMessageDialog(null, "Fields marked with * cannot be empty");
					tf.requestFocus();
				}else if(invalid){
					if(tf2==name)
						JOptionPane.showMessageDialog(null, "Name should only contain alplabets");
					else if(tf2==email)
						JOptionPane.showMessageDialog(null, "Invalid email format");
					else if(tf2==mobile)
						JOptionPane.showMessageDialog(null, "Mobile number should contain numeric values and length 10");
					else if(tf2==username)
						JOptionPane.showMessageDialog(null, "Username should have alteast 8 char");
					else if(tf2==age)
						JOptionPane.showMessageDialog(null, "Age should be a number");
					else if(tf2==pwd)
						JOptionPane.showMessageDialog(null, "Password should contain atleast one char,number and a special char");
					tf2.requestFocus();					
				}				
				
				if(!invalid&&!incomplete){
					boolean duplicateUsername=false;
					if(username.getText().equals(tempUsername)){
						
					}else{
					
					
					
					try {
						String query="select * from Bankdata where username =?";
						PreparedStatement pst1=connection.prepareStatement(query);
						pst1.setString(1, username.getText());
						ResultSet rst1=pst1.executeQuery();
						while(rst1.next()){
							duplicateUsername=true;
							break;
						}						
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					}
					/*try {
						connection.close();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}*/
					if(!duplicateUsername||username.getText().equals(tempUsername)){
						try {
							//System.out.println(""+1);
							String query="update Bankdata set name=?,age=?,address=?,city=?,state=?,email=?,mobile=?,username=?,password=?,gender=?,accType=? where accountNo=?";
							PreparedStatement pst=connection.prepareStatement(query);
							pst.setString(1, name.getText());
							pst.setLong(2, Long.parseLong(age.getText()));
							pst.setString(3, address.getText());
							pst.setString(4,city.getText());
							pst.setString(5, state.getText());
							pst.setString(6, email.getText());
							pst.setLong(7, Long.parseLong(mobile.getText()));
							pst.setString(8, username.getText());
							pst.setString(9, pwd.getText());
							pst.setString(10, gender_tf.getText());
							pst.setString(11,ac_type.getText());
							pst.setString(12, accountNo);
							pst.execute();
							JOptionPane.showMessageDialog(null, "Data Saved");
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(null, "Username already exists");
						username.requestFocus();
					}
				}
			}
		});
		save.setForeground(Color.BLUE);
		save.setFont(new Font("Times New Roman", Font.BOLD, 12));
		save.setBounds(110, 339, 75, 22);
		ContentPane.add(save);
		save.setVisible(false);
		
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
			tempUsername=username.getText();
			pwd.setText(rs.getString("password"));
			balance.setText(Integer.toString(rs.getInt("balance")));
			if(rs.getString("gender").equals("Male")){
				gender_tf.setText("male");
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
			JOptionPane.showMessageDialog(null, e);
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
		
		JLabel label_13 = new JLabel("*");
		label_13.setForeground(Color.RED);
		label_13.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_13.setBounds(78, 39, 18, 14);
		ContentPane.add(label_13);
		label_13.setVisible(false);
		
		JLabel label_14 = new JLabel("*");
		label_14.setForeground(Color.RED);
		label_14.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_14.setBounds(78, 67, 18, 14);
		ContentPane.add(label_14);
		label_14.setVisible(false);
		
		JLabel label_15 = new JLabel("*");
		label_15.setForeground(Color.RED);
		label_15.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_15.setBounds(78, 151, 18, 14);
		ContentPane.add(label_15);
		label_15.setVisible(false);
		
		JLabel label_16 = new JLabel("*");
		label_16.setForeground(Color.RED);
		label_16.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_16.setBounds(78, 179, 18, 14);
		ContentPane.add(label_16);
		label_16.setVisible(false);
		
		JLabel label_17 = new JLabel("*");
		label_17.setForeground(Color.RED);
		label_17.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_17.setBounds(78, 207, 18, 14);
		ContentPane.add(label_17);
		label_17.setVisible(false);
		
		JLabel label_18 = new JLabel("*");
		label_18.setForeground(Color.RED);
		label_18.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_18.setBounds(78, 235, 18, 14);
		ContentPane.add(label_18);
		label_18.setVisible(false);
		
		Button edit = new Button("Edit");
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
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
				balance.setEditable(false);
				save.setVisible(true);
				label_18.setVisible(true);
				label_17.setVisible(true);
				label_16.setVisible(true);
				label_15.setVisible(true);
				label_14.setVisible(true);
				label_13.setVisible(true);
				
			}
		});
		edit.setForeground(Color.BLUE);
		edit.setBackground(Color.WHITE);
		edit.setFont(new Font("Times New Roman", Font.BOLD, 12));
		edit.setBounds(337, 11, 55, 22);
		ContentPane.add(edit);
		
		System.out.println(tempUsername);
		
	}
}
