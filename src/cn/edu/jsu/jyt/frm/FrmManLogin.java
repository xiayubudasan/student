package cn.edu.jsu.jyt.frm;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import cn.edu.jsu.jyt.util.Excel;
import cn.edu.jsu.jyt.util.ExcelPrint;

public class FrmManLogin extends JFrame {
	private static JFrame frame;
	public static  JFrame getIns() {//单例
		if(frame==null) {
			frame=new FrmManLogin();
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
					FrmManLogin frame = new FrmManLogin();
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
	public FrmManLogin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 402);
		//getContentPane().setLayout(null);
		//Container contentPane = this.getContentPane();
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("学生信息");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("信息修改");
		mnNewMenu.add(mntmNewMenuItem);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("账号修改");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("教师信息");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("信息修改");
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JSeparator separator_1 = new JSeparator();
		mnNewMenu_1.add(separator_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("账号修改");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_1_1 = new JMenu("课程信息");
		menuBar.add(mnNewMenu_1_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("课程修改");
		mnNewMenu_1_1.add(mntmNewMenuItem_4);
		
		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton Sbtn = new JButton("学生信息修改");
		Sbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMStuMassage fmsm=new FrmMStuMassage();
				fmsm.setVisible(true);
			}
		});
		toolBar.add(Sbtn);
		
		JButton Tbtn = new JButton("教师信息修改");
		Tbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmMTeaMassage mts=new FrmMTeaMassage();
				mts.setVisible(true);
			}
		});
		toolBar.add(Tbtn);
		
		JButton Cbtn = new JButton("课程信息修改");
		Cbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmClassMassage c=new FrmClassMassage();
				c.setVisible(true);
			}
		});
		toolBar.add(Cbtn);
		
		JButton btnNewButton = new JButton("成绩导出");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Excel excle=new Excel();
				excle.toWriteExcle();
				JOptionPane.showMessageDialog(null, "导出成功");
			}
		});
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("成绩导入");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ExcelPrint ep=new ExcelPrint();
				ep.main(null);
			}
		});
		toolBar.add(btnNewButton_1);

	}

}
