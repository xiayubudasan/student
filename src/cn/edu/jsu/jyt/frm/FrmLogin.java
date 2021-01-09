package cn.edu.jsu.jyt.frm;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.jsu.jyt.dao.StudentDao;
import cn.edu.jsu.jyt.dao.TeacherDao;
import cn.edu.jsu.jyt.dbc.DataBaseConnection02;
import cn.edu.jsu.jyt.io.ManageIO;

import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.ButtonGroup;
																																																import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class FrmLogin extends JFrame {
	private JTextField text;
	private JRadioButton stubtn;
	private JRadioButton teabtn;
	private JRadioButton manbtn;
	private Vector row;
	private ButtonGroup buttonGroup;
	private JPasswordField text2;
	private static JFrame frame;
	public static  JFrame getIns() {//单例
		if(frame==null) {
			frame=new FrmLogin();
		}
		return frame;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
			
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		ImageIcon icon=new ImageIcon("source"+File.separator+"img"+File.separator+"7.jpg");
		Image img=icon.getImage().getScaledInstance(1000, 1000, Image.SCALE_FAST);
		JLabel jlabel=new JLabel(new ImageIcon(img));
		jlabel.setBounds(0, 0, 500, 600);
		this.getLayeredPane().add(jlabel,new Integer(Integer.MIN_VALUE));
		JPanel jp=(JPanel) this.getContentPane();
		JRootPane jpl=(JRootPane) this.getRootPane();
		jp.setOpaque(false);
		jpl.setOpaque(false);
		this.setVisible(true);
		setBounds(380, 210, 450, 300);
		getContentPane().setLayout(null);
		//Container contentPane = this.getContentPane();
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("账号");
		lblNewLabel.setBounds(47, 65, 81, 21);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setBounds(47, 120, 81, 21);
		getContentPane().add(lblNewLabel_1);
		
		text = new JTextField();
		text.setBounds(143, 62, 183, 27);
		getContentPane().add(text);
		text.setColumns(10);
		
		buttonGroup = new ButtonGroup();
		
		stubtn = new JRadioButton("学生");
		stubtn.setBounds(34, 162, 75, 29);
		getContentPane().add(stubtn);
		
		teabtn = new JRadioButton("教师");
		teabtn.setBounds(130, 162, 75, 29);
		getContentPane().add(teabtn);
		
		manbtn = new JRadioButton("管理员");
		manbtn.setBounds(236, 162, 90, 29);
		getContentPane().add(manbtn);
		
		buttonGroup.add(stubtn);
		buttonGroup.add(teabtn);
		buttonGroup.add(manbtn);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkPassword())
				{
					if(stubtn.isSelected())
					{
					
						JFrame s=FrmStuLogin.getIns();
						FrmStuLogin stu=(FrmStuLogin) s;
						stu.setVisible(true);
						stu.setVector(row);
						
					}
					else if(teabtn.isSelected())
					{
						JFrame t=FrmTeaLogin.getIns();
						FrmTeaLogin tea=(FrmTeaLogin) t;
						tea.setVisible(true);
						tea.setVector(row);
					}
					else if(manbtn.isSelected())
					{
						JFrame mm=FrmLogin.getIns();
						FrmManLogin m=(FrmManLogin) mm;
								m.setVisible(true);
					}
				}
			}
		});
		btnNewButton.setBounds(47, 202, 123, 29);
		getContentPane().add(btnNewButton);
		
		text2 = new JPasswordField();
		text2.setBounds(143, 117, 183, 27);
		getContentPane().add(text2);

	}
	public boolean checkPassword()
	{
	
		String sql="";
		if(stubtn.isSelected())
		{
			sql="select * from student where sno="+text.getText().trim()
					+" and password="+text2.getText().trim();
			row=StudentDao.getInData(sql);
		}
		else if(teabtn.isSelected())
		{
			
			sql="select * from teacher where tno="+text.getText().trim()
					+" and password="+text2.getText().trim();
			row=TeacherDao.getInData(sql);
		}
		else if(manbtn.isSelected())
		{
			File file=new File("e:"+File.separator+"act.txt");
			ManageIO man=new ManageIO(file);
			try {
				if(text2.getText().trim().equals(man.get(text.getText().trim()).getPassword()))
				{
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	    
	    if( row.isEmpty())
	    	return false;
	    return true;
	}
}
