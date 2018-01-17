import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Label;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLogin extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private TextField acc_no,name,balance,amount,trans_acc;
	private CheckboxGroup action;
	private Checkbox withdraw,transfer;
	Label label_7=null;
	Connection connection=null;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin frame = new UserLogin();
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
	public UserLogin(String username) {
		connection=SQLConnection.dbConnection();		
		
		setTitle("User Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 466);
		
		contentPane = new JPanel();
		contentPane.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				// ****do whatever applicable for button transact**
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Account No:");
		label.setFont(new Font("Dialog", Font.PLAIN, 12));
		label.setBounds(35, 67, 80, 22);
		contentPane.add(label);
		
		acc_no = new TextField();
		acc_no.setBounds(134, 67, 211, 22);
		contentPane.add(acc_no);
		acc_no.setEditable(false);;
		
		Label label_1 = new Label("Name");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_1.setBounds(35, 105, 62, 22);
		contentPane.add(label_1);
		
		Label label_2 = new Label("Balance");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_2.setBounds(35, 145, 62, 22);
		contentPane.add(label_2);
		
		name = new TextField();
		name.setBounds(132, 105, 213, 22);
		contentPane.add(name);
		name.setEditable(false);
		
		balance = new TextField();
		balance.setBounds(134, 145, 211, 22);
		contentPane.add(balance);
		balance.setEditable(false);
		
		Label label_3 = new Label("Account Summary");
		label_3.setForeground(Color.BLUE);
		label_3.setFont(new Font("Dialog", Font.BOLD, 14));
		label_3.setBounds(10, 24, 137, 22);
		contentPane.add(label_3);
		
		Label label_4 = new Label("Transact");
		label_4.setForeground(Color.BLUE);
		label_4.setFont(new Font("Dialog", Font.BOLD, 14));
		label_4.setBounds(10, 205, 127, 22);
		contentPane.add(label_4);
		
		CheckboxGroup action=new CheckboxGroup();
		
		Checkbox withdraw = new Checkbox("Withdraw",action,true);
		withdraw.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				//trans_acc.setEditable(false);
				trans_acc.setVisible(false);
				label_7.setVisible(false);				
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				trans_acc.setVisible(false);
				label_7.setVisible(false);
			}
		});
		
		
		withdraw.setFont(new Font("Dialog", Font.PLAIN, 12));
		withdraw.setBounds(136, 233, 95, 22);
		contentPane.add(withdraw);
		
		Checkbox transfer = new Checkbox("Transfer",action,false);
		transfer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				//trans_acc.setEditable(true);
				trans_acc.setVisible(true);
				label_7.setVisible(true);	
			}
		});
		
		transfer.setFont(new Font("Dialog", Font.PLAIN, 12));
		transfer.setBounds(237, 233, 95, 22);
		contentPane.add(transfer);
		
		
		Label label_5 = new Label("Select Action :");
		label_5.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_5.setBounds(20, 233, 127, 22);
		contentPane.add(label_5);
		
		Label label_6 = new Label("Enter Amount");
		label_6.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_6.setBounds(20, 273, 95, 22);
		contentPane.add(label_6);
		
		amount = new TextField();
		amount.setBounds(134, 273, 211, 22);
		contentPane.add(amount);
		
		label_7 = new Label("Transfer Acc. No");
		label_7.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_7.setBounds(20, 305, 95, 22);
		label_7.setVisible(false);
		contentPane.add(label_7);
		
		trans_acc = new TextField();
		trans_acc.setBounds(132, 305, 213, 22);
		trans_acc.setVisible(false);
		contentPane.add(trans_acc);		
		
		
		
		try {
			System.out.println("in2");
			String query="select * from Bankdata  where username=?";
			PreparedStatement pst=connection.prepareStatement(query);
			System.out.println("in1");
			pst.setString(1, username);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				name.setText(rs.getString("name"));
				balance.setText(rs.getString("balance"));
				acc_no.setText(rs.getString("accountNo"));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		Button transact = new Button("Transact");
		transact.setForeground(Color.RED);
		transact.setFont(new Font("Dialog", Font.BOLD, 15));
		transact.setBackground(Color.CYAN);
		transact.setBounds(167, 364, 70, 22);
		transact.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					{
						
						long bal=0,temp_bal,temp_bal1 = 0;
						boolean enoughBalance=false;
						boolean transferaccExists=false;
						PreparedStatement pst,pst1;
						if(!amount.getText().matches("^[0-9]+$"))
						{
							JOptionPane.showMessageDialog(null, "Invalid Amount");
							amount.setText(" ");
							amount.setText("");
						}else{
						
						try {
							/*/if(transfer.getState())
							{
								String query2="select * from Bankdata where accountNo=?";
								pst2 = connection.prepareStatement(query2);
								pst2.setString(1,trans_acc.getText());
								ResultSet rs2=pst2.executeQuery();
								while(rs2.next()){	
									transferaccExists=true;
								}
							}*/							
							
							String query1="select * from Bankdata where accountNo=?";
							pst1 = connection.prepareStatement(query1);
							pst1.setString(1,acc_no.getText());
							ResultSet rs1=pst1.executeQuery();
							while(rs1.next())
							{	
								bal=rs1.getLong("balance");
								if(withdraw.getState()||transfer.getState())
								{
									temp_bal1=bal;
									if(bal>Integer.parseInt(amount.getText())){
										bal-=Integer.parseInt(amount.getText());
										enoughBalance=true;
									}else
										JOptionPane.showMessageDialog(null, "Not Enough Balance");
								}else
										JOptionPane.showMessageDialog(null, "Select Action");
							}
							//System.out.println(bal);
							if(enoughBalance){
								String query="update Bankdata set balance=? where accountNo=?";
								pst = connection.prepareStatement(query);
								pst.setString(2,acc_no.getText());
								pst.setLong(1, bal);
								pst.execute();
								if(withdraw.getState()){
									JOptionPane.showMessageDialog(null, "Transaction Successfull");
									balance.setText(""+bal);
								}
								temp_bal=bal;
								bal=0;
								if(transfer.getState())
								{
									query1="select * from Bankdata where accountNo=?";
									pst1 = connection.prepareStatement(query1);
									pst1.setString(1,trans_acc.getText());
									rs1=pst1.executeQuery();
									while(rs1.next())
									{	
										bal=rs1.getLong("balance");										
										bal+=(Integer.parseInt(amount.getText()));
										transferaccExists=true;
									}		
									//System.out.println(bal);
									if(transferaccExists){
										query="update Bankdata set balance=? where accountNo=?";
										pst = connection.prepareStatement(query);
										pst.setString(2,trans_acc.getText());
										pst.setLong(1, bal);
										pst.execute();
										JOptionPane.showMessageDialog(null, "Transaction Successfull");
									}else{
										JOptionPane.showMessageDialog(null, "Enter valid account No");
										trans_acc.requestFocus();
										query="update Bankdata set balance=? where accountNo=?";
										pst1 = connection.prepareStatement(query);
										pst1.setString(2,acc_no.getText());
										pst1.setLong(1, temp_bal1);
										pst1.execute();
										temp_bal=temp_bal1;
									}
								}
								//JOptionPane.showMessageDialog(null, "In here");
								//JOptionPane.showMessageDialog(null, "Transaction Successfull");
								balance.setText(""+temp_bal);
							}	
						}catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					}
				}				
			});
		contentPane.add(transact);
		
		Button editProfile = new Button("View Profile");
		editProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				UserViewProfile uvp =new UserViewProfile(acc_no.getText());
				uvp.setVisible(true);
				UserLogin.this.dispose();
			}
		});
		editProfile.setFont(new Font("Times New Roman", Font.BOLD, 12));
		editProfile.setBounds(329, 10, 95, 29);
		contentPane.add(editProfile);
		
		Button logout = new Button("Log Out");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserLogin.this.dispose();
				LoginPage l=new LoginPage();
				l.frmLogIn.setVisible(true);
			}
		});
		logout.setFont(new Font("Times New Roman", Font.BOLD, 13));
		logout.setBounds(275, 364, 70, 22);
		contentPane.add(logout);
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}