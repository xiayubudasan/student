package cn.edu.jsu.jyt.frm;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;

public class FrmStuMassage extends JFrame {
     private static Vector ve;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStuMassage frame = new FrmStuMassage();
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
	public FrmStuMassage() {
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
		getContentPane().setLayout(null);
		setBounds(380, 210, 488, 369);
		setLocationRelativeTo(getOwner());
		
		JLabel lblNewLabel = new JLabel("学生信息");
		lblNewLabel.setBounds(166, 25, 81, 21);
		getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("学号");
		label.setBounds(68, 61, 81, 21);
		getContentPane().add(label);
		
		JLabel lblNewLabel_1 = new JLabel("姓名");
		lblNewLabel_1.setBounds(68, 106, 81, 21);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("性别");
		lblNewLabel_1_1.setBounds(68, 152, 81, 21);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("年龄");
		lblNewLabel_1_2.setBounds(68, 198, 81, 21);
		getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("\u7CFB\u522B");
		lblNewLabel_1_3.setBounds(68, 241, 81, 21);
		getContentPane().add(lblNewLabel_1_3);
		
		JLabel sno = new JLabel("");
		sno.setBounds(236, 61, 120, 21);
		getContentPane().add(sno);
		sno.setText(ve.elementAt(0).toString());
		
		JLabel sname = new JLabel("");
		sname.setBounds(236, 106, 120, 21);
		getContentPane().add(sname);
		sname.setText(ve.elementAt(1).toString());
		
		JLabel ssex = new JLabel("");
		ssex.setBounds(236, 152, 120, 21);
		getContentPane().add(ssex);
		ssex.setText(ve.elementAt(2).toString());
		
		JLabel sage = new JLabel("");
		sage.setBounds(236, 198, 120, 21);
		getContentPane().add(sage);
		sage.setText(ve.elementAt(3).toString());
		
		JLabel sdept = new JLabel("");
		sdept.setBounds(236, 241, 120, 21);
		getContentPane().add(sdept);
		sdept.setText(ve.elementAt(6).toString());
		
		JLabel lblNewLabel_1_3_1 = new JLabel("专业");
		lblNewLabel_1_3_1.setBounds(68, 277, 81, 21);
		getContentPane().add(lblNewLabel_1_3_1);
		
		JLabel major = new JLabel((String) null);
		major.setBounds(236, 277, 120, 21);
		getContentPane().add(major);
		major.setText(ve.elementAt(7).toString());

	}
	public static void setVector(Vector v)
	{
		 ve=v;
	}
}
