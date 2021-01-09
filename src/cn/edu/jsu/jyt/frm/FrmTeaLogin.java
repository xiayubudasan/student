package cn.edu.jsu.jyt.frm;

import java.awt.EventQueue;
import java.awt.Image;

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
import java.util.Vector;
import java.awt.event.ActionEvent;

public class FrmTeaLogin extends JFrame {
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
					FrmTeaLogin frame = new FrmTeaLogin();
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
	public FrmTeaLogin() {
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
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(getOwner());
		
		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton button = new JButton("课程信息管理");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmClassMassage massage=new FrmClassMassage();
				massage.setVisible(true);
			}
		});
		toolBar.add(button);
		
		JButton btnNewButton = new JButton("成绩录入");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmTScoreMassage ts=new FrmTScoreMassage();
				ts.setVisible(true);
			}
		});
		toolBar.add(btnNewButton);

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
