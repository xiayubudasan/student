package cn.edu.jsu.jyt.frm;

import java.awt.EventQueue;
import java.awt.Image;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JToolBar;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class FrmStuLogin extends JFrame {
   private static Vector ve;
	private static JFrame frame;
	public static JFrame getIns() {//单例
		if(frame==null) {
			frame=new FrmStuLogin();
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
					FrmStuLogin frame = new FrmStuLogin();
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
	public FrmStuLogin() {
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(380, 210, 460, 300);
		getContentPane().setLayout(null);
		setLocationRelativeTo(getOwner());
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 434, 23);
		getContentPane().add(toolBar);
		
		JButton btnNewButton = new JButton("个人信息查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmStuMassage.setVector(ve);
				FrmStuMassage stu=new FrmStuMassage();
				
				stu.setVisible(true);
				
			}
		});
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("学生选课");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			FrmSClassMassage scl=new FrmSClassMassage();
			scl.setVisible(true);
			}
		});
		toolBar.add(btnNewButton_1);
		
		JButton button = new JButton("成绩查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmStudentScore score=new FrmStudentScore();
				score.setVisible(true);
			}
		});
		toolBar.add(button);
		
		JButton btnNewButton_2 = new JButton("课表信息");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmStuClass stc=new FrmStuClass();
				stc.setVisible(true);
			}
		});
		toolBar.add(btnNewButton_2);

	}
	public static void setVector(Vector v)
	{
		ve=v;

	}
	public static Vector getVector()
	{
		return ve;
	}
}
