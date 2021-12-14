import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTabbedPane;
import javax.swing.JButton;

public class Student extends JFrame {
    static String Subject,Professor;
    static int credit;
	Application application;
	Inquiry inquiry;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		new Student();	
		}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Student() throws SQLException {
		System.out.println("test");
		application = new Application();
		inquiry = new Inquiry();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0)); 
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		tabbedPane.addTab("수강신청", application);
		tabbedPane.addTab("수강신청내역", inquiry);
		setVisible(true);


		application.back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				setVisible(false); 
				
			}
		});
		
		inquiry.back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				setVisible(false); 
				
			}
		});
		application.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string = "INSERT INTO `sumin`.`subject` (`sno` ,`Subject` ,`Professor` ,`credit`) "
						+ "VALUES (NULL , '"
						+Student.Subject+"', '"
						+Student.Professor+"', '"
						+Student.credit+"');";
				System.out.println(string);
				try {
					Database.query("insert", string);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					inquiry.view();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
}
