import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Label;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JProgressBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminLogin extends JFrame implements ActionListener{

	private JPanel contentPane;
	private TextField acc_no;
	Connection connection=null;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
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
	public AdminLogin() {
		setTitle("Admin Login Page");
		connection=SQLConnection.dbConnection();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Button Logout = new Button("Log Out");
		Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminLogin.this.dispose();
				LoginPage l=new LoginPage();
				l.frmLogIn.setVisible(true);
			}
		});
		Logout.setBounds(354, 10, 70, 22);
		contentPane.add(Logout);
		
		Label label = new Label("Welcome Admin!");
		label.setForeground(new Color(255, 0, 0));
		label.setFont(new Font("Dialog", Font.BOLD, 18));
		label.setAlignment(Label.CENTER);
		label.setBounds(106, 10, 238, 48);
		contentPane.add(label);
		
		Label label_1 = new Label("Account No:");
		label_1.setFont(new Font("Dialog", Font.BOLD, 14));
		label_1.setBounds(32, 86, 116, 22);
		contentPane.add(label_1);
		
		Button profile = new Button("View Profile");
		profile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					String query="select * from Bankdata where accountNo=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, acc_no.getText());
					ResultSet rs=pst.executeQuery();
					int count=0;
					while(rs.next()){
						count++;
					}
					if(count!=0){
						//JOptionPane.showMessageDialog(null, "Username is Correct");
						AdminLogin.this.dispose();
						AdminViewProfile profile=new AdminViewProfile(acc_no.getText());
						profile.setVisible(true);
						//contentPane.setVisible(false);
					}else{
						JOptionPane.showMessageDialog(null, "AccountNo is Incorrect");
						acc_no.setText(null);
						
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
				acc_no.setText(null);
			}
		});
		profile.setFont(new Font("Dialog", Font.BOLD, 12));
		profile.setBounds(106, 187, 85, 29);
		contentPane.add(profile);
		profile.addActionListener(this);
		
		Button transact = new Button("Transact");
		transact.setFont(new Font("Dialog", Font.BOLD, 12));
		transact.setBounds(222, 187, 85, 29);
		contentPane.add(transact);
		transact.addActionListener(this);
		
		acc_no = new TextField();
		acc_no.setBounds(167, 86, 177, 22);
		contentPane.add(acc_no);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Transact"))
			{
			
			try{
				String query="select * from Bankdata where accountNo=?";
				PreparedStatement pst=connection.prepareStatement(query);
				pst.setString(1, acc_no.getText());
				ResultSet rs=pst.executeQuery();
				int count=0;
				while(rs.next()){
					count++;
				}
				if(count!=0){
					//JOptionPane.showMessageDialog(null, "Username is Correct");
					AdminLogin.this.dispose();
					AdminTransact at=new AdminTransact(acc_no.getText());
					at.setVisible(true);
					//contentPane.setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null, "AccountNo is Incorrect");
					acc_no.setText(null);
					
				}
			}catch(Exception e1){
				JOptionPane.showMessageDialog(null, e1);
			}
			acc_no.setText(null);
			
			}
	}

	
	
}