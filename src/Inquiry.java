import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class Inquiry extends JPanel {
	int a;
	private JTable table;
    JButton back;
    JScrollPane scrollPane;
    DefaultTableModel model;
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public Inquiry() throws SQLException {
		setLayout(null);
		String[] array = {"번호" , "과목명", "교수이름" , "학점"};
		model = new DefaultTableModel(array,0);
		
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		
		JLabel lblNewLabel = new JLabel("\uC218\uAC15\uC2E0\uCCAD\uB0B4\uC5ED");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel.setBounds(37, 24, 170, 25);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\uC0AD\uC81C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rainString = "DELETE FROM `sumin`.`subject` WHERE `subject`.`sno` ="+a+" LIMIT 1 ;";
				try {
					Database.query("d", rainString);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					view();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 13));
		btnNewButton.setBounds(541, 297, 84, 25);
		add(btnNewButton);
		
		back = new JButton("\uC774\uC804");
		back.setFont(new Font("굴림", Font.BOLD, 13));
		back.setBounds(37, 298, 84, 25);
		add(back);
		
		JPanel panel = new JPanel();
		panel.setBounds(37, 68, 588, 191);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		panel.add(scrollPane);
		table.addMouseListener(new TableMouse());
		Database.dbConnect();
		//불러오기
		view();
	}
	public void view() throws SQLException {

		model.setNumRows(0);
		String min = "select * from subject order by 1;"; 
		Database.query("select", min);
		while(Database.rs.next()) {
			model.addRow(new Object[] {
					Database.rs.getInt("sno"),
					Database.rs.getString("Subject"),
					Database.rs.getString("Professor"),
					Database.rs.getInt("credit"),
			});
		}
	}
	private class TableMouse implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			JTable jTable = (JTable)e. getSource();
			int row = jTable.getSelectedRow();
			a = (Integer) model.getValueAt(row, 0);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
