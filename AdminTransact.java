import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import java.awt.Label;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Button;

public class AdminTransact extends JFrame implements ActionListener {

	private JPanel contentPane;
	Connection connection;
	private TextField pass;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminTransact frame = new AdminTransact();
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
	public AdminTransact(String no) {
		connection=SQLConnection.dbConnection();		
		System.out.println(no);
		setTitle("Admin Transact");		
		setTitle("Transact");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Account No:");
		label.setFont(new Font("Dialog", Font.BOLD, 12));
		label.setBounds(49, 24, 95, 22);
		contentPane.add(label);
		
		TextField acc_no = new TextField();
		acc_no.setBounds(175, 24, 164, 22);
		contentPane.add(acc_no);
		acc_no.setEditable(false);
		acc_no.setText(no);
		
		Label label_1 = new Label("Amount :");
		label_1.setFont(new Font("Dialog", Font.BOLD, 12));
		label_1.setBounds(64, 70, 62, 22);
		contentPane.add(label_1);
		
		TextField amt = new TextField();
		amt.setBounds(175, 70, 164, 22);
		contentPane.add(amt);
	
		Button Logout = new Button("Log Out");
		Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminTransact.this.dispose();
				LoginPage l=new LoginPage();
				l.frmLogIn.setVisible(true);
			}
		});
		Logout.setBounds(262, 294, 70, 22);
		contentPane.add(Logout);
		
		Label label_2 = new Label("Transfer Account No:");
		label_2.setFont(new Font("Dialog", Font.BOLD, 12));
		label_2.setBounds(10, 170, 134, 22);
		contentPane.add(label_2);
		label_2.setVisible(false);
		
		TextField transfer = new TextField();
		transfer.setBounds(175, 170, 164, 22);
		contentPane.add(transfer);
		transfer.setVisible(false);
		
		CheckboxGroup action=new CheckboxGroup();
		
		Checkbox cwithdraw = new Checkbox("Withdraw",action,false);
		cwithdraw.setFont(new Font("Dialog", Font.PLAIN, 12));
		cwithdraw.setForeground(Color.BLUE);
		cwithdraw.setBounds(31, 131, 95, 22);
		contentPane.add(cwithdraw);
		cwithdraw.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				
				transfer.setVisible(false);
				label_2.setVisible(false);	
			}
		});
		
		Checkbox cdeposit = new Checkbox("Deposit",action,false);
		cdeposit.setFont(new Font("Dialog", Font.PLAIN, 12));
		cdeposit.setForeground(Color.BLUE);
		cdeposit.setBounds(161, 131, 95, 22);
		contentPane.add(cdeposit);
		cdeposit.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				transfer.setVisible(false);
				label_2.setVisible(false);	
			}
		});
		
		Checkbox ctransfer = new Checkbox("Transfer",action,false);
		ctransfer.setFont(new Font("Dialog", Font.PLAIN, 12));
		ctransfer.setForeground(Color.BLUE);
		ctransfer.setBounds(287, 131, 95, 22);
		contentPane.add(ctransfer);
		ctransfer.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				transfer.setVisible(true);
				label_2.setVisible(true);	
			}
		});
		
	
		
		Label label_3 = new Label("Confirm Admin Password");
		label_3.setFont(new Font("Dialog", Font.BOLD, 12));
		label_3.setBounds(10, 226, 159, 22);
		contentPane.add(label_3);
		
		pass = new TextField();
		pass.setEchoChar('*');
		pass.setBounds(175, 226, 164, 22);
		contentPane.add(pass);
		
		Button transact = new Button("Transact");
		transact.setFont(new Font("Dialog", Font.BOLD, 14));
		transact.setForeground(Color.RED);
		transact.setBounds(101, 294, 70, 22);
		contentPane.add(transact);
		transact.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(pass.getText().equals("admin123"))
				{
					pass.setText(" ");
					pass.setText("");
					//System.out.println(!amt.getText().matches("^[0-9]+$"));
					if(!amt.getText().matches("^[0-9]+$"))
					{
						JOptionPane.showMessageDialog(null, "Invalid Amount");
						amt.setText(" ");
						amt.setText("");
					}
					else
					{
					long bal=0;
					boolean flag=false;
					PreparedStatement pst,pst1;
					long tempBal=0;
					try {
						
						String query1="select * from Bankdata where accountNo=?";
						pst1 = connection.prepareStatement(query1);
						pst1.setString(1,acc_no.getText());
						ResultSet rs1=pst1.executeQuery();
						while(rs1.next())
						{	
							bal=rs1.getLong("balance");
							tempBal=bal;
							if(cwithdraw.getState()||ctransfer.getState())
							{
								if(bal>Integer.parseInt(amt.getText()))
								{bal-=Integer.parseInt(amt.getText());
								flag=true;
								}
								else
									JOptionPane.showMessageDialog(null, "Not Enough Balance");
							}
								else if(cdeposit.getState())
								{
									flag=true;
									bal+=(Integer.parseInt(amt.getText()));
								}else
									JOptionPane.showMessageDialog(null, "Select Action");
						}
						//System.out.println(bal);
					if(flag)
					{
						String query="update Bankdata set balance=? where accountNo=?";
						pst = connection.prepareStatement(query);
						pst.setString(2,acc_no.getText());
						pst.setLong(1, bal);
						pst.execute();
						bal=0;
						boolean flag3=false;
						if(ctransfer.getState())
						{
							query1="select * from Bankdata where accountNo=?";
							pst1 = connection.prepareStatement(query1);
							pst1.setString(1,transfer.getText());
							rs1=pst1.executeQuery();
							while(rs1.next())
							{	
								bal=rs1.getLong("balance");
								flag3=true;
								
									bal+=(Integer.parseInt(amt.getText()));
							}		
							if(flag3==false){
								JOptionPane.showMessageDialog(null, "account no invalid");
								query="update Bankdata set balance=? where accountNo=?";
								pst = connection.prepareStatement(query);
								pst.setString(2,acc_no.getText());
								pst.setLong(1, tempBal);
								pst.execute();
							}
							else
							{//System.out.println(bal);
							
							query="update Bankdata set balance=? where accountNo=?";
							pst = connection.prepareStatement(query);
							pst.setString(2,transfer.getText());
							pst.setLong(1, bal);
							pst.execute();
							JOptionPane.showMessageDialog(null, "Transaction Successfull");
						}
					}else{
						JOptionPane.showMessageDialog(null, "Transaction Successfull");
						amt.setText(null);
					}
						//JOptionPane.showMessageDialog(null, "In here");
						
					}	
					} catch (SQLException e1) {
					// 	TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Admin Password Incorrect");
					pass.setText(null);
				}
				
			
		}
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
