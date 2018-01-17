import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.Label;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JLabel;
import java.awt.event.TextListener;
import java.awt.event.TextEvent;



class Customer
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
}

public class NewAccount extends JFrame {

	private JPanel contentPane;
	String n,a,gender,add,c,s,em,mob,un,type,pass;
	Connection connection =null;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewAccount frame = new NewAccount();					
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
	public NewAccount() {
		JLabel pwdLabel;
		setTitle("New Account");
		connection=SQLConnection.dbConnection();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 553, 409);
		contentPane = new JPanel();		
		contentPane.setName("New Account");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		TitledBorder border = new TitledBorder("New Account");
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
		contentPane.setBorder(border);
		Label label = new Label("Full Name");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label.setBounds(10, 39, 62, 22);
		contentPane.add(label);
		
		Label label_1 = new Label("Age");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_1.setBounds(10, 67, 62, 22);
		contentPane.add(label_1);
		
		Label label_2 = new Label("Address");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_2.setBounds(10, 95, 62, 22);
		contentPane.add(label_2);
		
		TextField name = new TextField();
		name.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				name.setBackground(Color.white);
			}
		});
		name.setBounds(99, 39, 269, 22);
		contentPane.add(name);
		
		TextField age = new TextField();
		age.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				name.setBackground(Color.white);
			}
		});
		age.setBounds(99, 67, 35, 22);
		contentPane.add(age);
		
		Label label_3 = new Label("City");
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_3.setBounds(10, 123, 62, 22);
		contentPane.add(label_3);
		
		Label label_4 = new Label("State");
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_4.setBounds(208, 123, 35, 22);
		contentPane.add(label_4);
		
		Label label_5 = new Label("Gender");
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_5.setBounds(181, 67, 46, 22);
		contentPane.add(label_5);
		
		TextField city = new TextField();
		city.setBounds(99, 123, 86, 22);
		contentPane.add(city);
		
		TextField state = new TextField();
		state.setBounds(249, 123, 124, 22);
		contentPane.add(state);
		
		TextField address = new TextField();
		address.setBounds(99, 95, 269, 22);
		contentPane.add(address);
		
		pwdLabel = new JLabel("");
		pwdLabel.setForeground(Color.RED);
		pwdLabel.setBounds(259, 235, 268, 22);
		contentPane.add(pwdLabel);
		
		Label label_6 = new Label("E-Mail\r\n");
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_6.setBounds(10, 151, 62, 22);
		contentPane.add(label_6);
		
		TextField email = new TextField();
		email.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				email.setBackground(Color.white);
			}
		});
		email.setBounds(99, 151, 269, 22);
		contentPane.add(email);
		
		Label label_7 = new Label("Password");
		label_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_7.setBounds(10, 235, 62, 22);
		contentPane.add(label_7);
		
		
		Label label_8 = new Label("Username\r\n");
		label_8.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_8.setBounds(10, 207, 62, 22);
		contentPane.add(label_8);
		
		TextField username = new TextField();
		username.setBounds(99, 207, 139, 22);
		contentPane.add(username);
		username.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				username.setBackground(Color.white);
			}
		});
		Label label_9 = new Label("Account type");
		label_9.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_9.setBounds(10, 263, 70, 22);
		contentPane.add(label_9);
		
		Label label_10 = new Label("Mobile no.");
		label_10.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_10.setBounds(10, 179, 62, 22);
		contentPane.add(label_10);
		
		TextField mobile = new TextField();
		mobile.setBounds(99, 179, 86, 22);
		contentPane.add(mobile);
		mobile.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				mobile.setBackground(Color.white);
			}
		});
		TextField pwd = new TextField();
		pwd.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent arg0) {
				
			}
		});
		pwd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				pwdLabel.setText("Atleast: 8 Character 1 Symbol 1 Number");
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				String regex ="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
				if(!pwd.getText().matches(regex)){
					pwd.setBackground(Color.red);					
				}else{
					pwd.setBackground(Color.WHITE);
					pwdLabel.setText("");
				}
			}
		});
		pwd.setBounds(99, 235, 139, 22);
		pwd.setEchoChar('*');
		contentPane.add(pwd);
		
		
		CheckboxGroup gen = new CheckboxGroup();
		
		Checkbox cmale = new Checkbox("Male",gen,true);
		cmale.setBounds(243, 67, 46, 22);
		contentPane.add(cmale);
		
		Checkbox cfemale = new Checkbox("Female",gen,false);
		cfemale.setBounds(297, 67, 62, 22);
		contentPane.add(cfemale);
		
		CheckboxGroup acc_type = new CheckboxGroup();
		
		Checkbox csaving = new Checkbox("Saving",acc_type,true);
		csaving.setBounds(109, 263, 70, 22);
		contentPane.add(csaving);
		
		Checkbox ccurrent = new Checkbox("Current",acc_type,false);
		ccurrent.setBounds(181, 263, 95, 22);
		contentPane.add(ccurrent);
		
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
						JOptionPane.showMessageDialog(null, "Username should have alteast 4 char");
					else if(tf2==pwd)
						JOptionPane.showMessageDialog(null, "Atleast one char,number and a special char");
					else if(tf2==age)
						JOptionPane.showMessageDialog(null, "Age should be a number");
					else if(tf2==pwd)
						JOptionPane.showMessageDialog(null, "Atleast one char,number and a special char");
					tf2.requestFocus();					
				}			
				
				if(!invalid&&!incomplete){				
					n=name.getText();
					a=age.getText();
					add=address.getText();
					c=city.getText();
					s=state.getText();
					em=email.getText();
					mob=mobile.getText();
					un=username.getText();
					pass=pwd.getText();
					if(cmale.getState())
						gender="male";
					else
						gender="female";
					if(ccurrent.getState())
						type="current";
					else
						type="savings";
					//------------- data update through excel-----------------------
					/*Customer customer=new Customer(n,a,gender,add,c,s,em,mob,un,pass,type);
					//details.addElement(customer);
					//System.out.println(customer);
					/*Filehanl dataupdate=new Filehanl();
					dataupdate.writeData(customer.toString());*/
					/*userinfo userData =new userinfo();
					userData.writeData(customer);*/
					
					//-----------------data update through SQL----------------
					
					boolean duplicateUsername=false;
					try {
						String query="select * from Bankdata where username =?";
						PreparedStatement pst1=connection.prepareStatement(query);
						pst1.setString(1, un);
						ResultSet rst1=pst1.executeQuery();
						while(rst1.next()){
							duplicateUsername=true;
							break;
						}						
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					if(!duplicateUsername){
						try{
							String temp_un=un;
							String query="insert into Bankdata (username,password,name,age,gender,address,city,state,email,mobile,accType,balance) values(?,?,?,?,?,?,?,?,?,?,?,?)";
							PreparedStatement pst=connection.prepareStatement(query);
							pst.setString(1, un);
							pst.setString(2, pass);
							pst.setString(3, n);
							pst.setLong(4,Long.parseLong(a));
							pst.setString(5, gender);
							pst.setString(6, address.getText());
							pst.setString(7, c);
							pst.setString(8, s);
							pst.setString(9, em);
							pst.setLong(10, Long.parseLong(mob));
							pst.setString(11, type);
							pst.setLong(12,0);
							pst.execute();
							
							
							query="select * from Bankdata where username=?";
							pst=connection.prepareStatement(query);
							pst.setString(1, temp_un);
							ResultSet rst =pst.executeQuery();
							
							while(rst.next()){
								JOptionPane.showMessageDialog(null, "Data Saved "+"\nYour Account no is "+rst.getString("accountNo"));
							}
							
							
							name.setText(" ");
							age.setText(" ");
							address.setText(" ");
							city.setText(" ");
							state.setText(" ");
							email.setText(" ");
							mobile.setText(" ");
							username.setText(" ");
							pwd.setText("");
							cmale.setState(false);;
							cfemale.setState(false);
							csaving.setState(false);
							ccurrent.setState(false);
						}catch(Exception e1){
							JOptionPane.showMessageDialog(null, e1);
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
		save.setBounds(163, 291, 75, 22);
		contentPane.add(save);
		
		Label label_11 = new Label("Registration Form");
		label_11.setForeground(Color.BLUE);
		label_11.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_11.setBounds(10, 11, 216, 22);
		contentPane.add(label_11);
		
		Button exit = new Button("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewAccount.this.dispose();
			}
		});
		exit.setBounds(64, 323, 70, 22);
		contentPane.add(exit);
		
		
		
		Button login = new Button("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginPage newLog=new LoginPage();
				newLog.frmLogIn.setVisible(true);
				NewAccount.this.dispose();
			}
		});
		login.setBounds(268, 323, 70, 22);
		contentPane.add(login);	
		
		JLabel label_12 = new JLabel("*");
		label_12.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_12.setForeground(Color.RED);
		label_12.setBounds(78, 39, 18, 14);
		contentPane.add(label_12);
		
		JLabel label_13 = new JLabel("*");
		label_13.setForeground(Color.RED);
		label_13.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_13.setBounds(78, 179, 18, 14);
		contentPane.add(label_13);
		
		JLabel label_14 = new JLabel("*");
		label_14.setForeground(Color.RED);
		label_14.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_14.setBounds(78, 151, 18, 14);
		contentPane.add(label_14);
		
		JLabel label_15 = new JLabel("*");
		label_15.setForeground(Color.RED);
		label_15.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_15.setBounds(78, 235, 18, 14);
		contentPane.add(label_15);
		
		JLabel label_16 = new JLabel("*");
		label_16.setForeground(Color.RED);
		label_16.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_16.setBounds(78, 207, 18, 14);
		contentPane.add(label_16);
		
		JLabel label_18 = new JLabel("*");
		label_18.setForeground(Color.RED);
		label_18.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_18.setBounds(78, 67, 18, 14);
		contentPane.add(label_18);
		
		
	}
}
