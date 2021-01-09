package cn.edu.jsu.jyt.frm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cn.edu.jsu.jyt.dao.ClassDao;
import cn.edu.jsu.jyt.io.ClassIO;
import cn.edu.jsu.jyt.vo.CClass;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class FrmStuClass extends JFrame {

	private JPanel contentPane;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTable table;
	private DefaultTableModel model;
	private File file;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStuClass frame = new FrmStuClass();
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
	public FrmStuClass() {
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
		setBounds(380, 210, 548, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(getOwner());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 73, 386, 267);
		contentPane.add(scrollPane);
		
		file=new File("e:"+File.separator+"课程表.txt");
		if(!file.exists())
		{
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		Vector<String> titles=new Vector<>();
		Collections.addAll(titles, "课程号","课程名","学分");
		Vector<Vector> v=ClassDao.getSelectClass("select cno,cname,ccredit from class where cno in(select cno from score where sno="+FrmStuLogin.getVector().elementAt(0).toString()+")");
		model=new DefaultTableModel(v,titles)
		{
			public Class getColumnClass(int column)
			{
				Class returnValue;
				if((column>=0)&&(column<getColumnCount())						) 
					{
						returnValue=getValueAt(0, column).getClass();
					}
					else
					{
						returnValue=Object.class;
					}
					return returnValue;
				
			}
				};
		table = new JTable(model);
		//int j=v.size();
		CClass [] c=new CClass[v.size()];
		int i=0;
		for(Vector vv:v)
		{

			c[i]=new CClass();
			c[i].setCno(vv.elementAt(0).toString());
			c[i].setCname(vv.elementAt(1).toString());
			c[i].setCcredit(Double.parseDouble(vv.elementAt(2).toString()));
		i++;
		}
		ClassIO.writeClass(file, c);
		scrollPane.setViewportView(table);
		
		t1 = new JTextField();
		t1.setBounds(416, 99, 96, 27);
		contentPane.add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(416, 206, 96, 27);
		contentPane.add(t2);
		
		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(416, 313, 96, 27);
		contentPane.add(t3);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("课程名");
		lblNewLabel_1.setBounds(416, 170, 81, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("学分");
		lblNewLabel_1_1.setBounds(416, 279, 81, 21);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("修改");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ClassIO.updateClass(file, t1.getText().trim(), t2.getText().trim(), t3.getText().trim());
			Vector<Vector> v=ClassIO.readClass(file);
			for(Vector vv:v)
			{
				System.out.println(vv);
			}
			model=new DefaultTableModel(v,titles);
			table.setModel(model);
			}
		});
		btnNewButton.setBounds(15, 29, 86, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("增加");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClassIO.addClass(file, t1.getText().trim(), t2.getText().trim(),t3.getText().trim());
			    Vector<Vector> v= ClassIO.readClass(file);
			    model=new DefaultTableModel(v,titles);
			    table.setModel(model);
			}
		});
		btnNewButton_1.setBounds(116, 29, 86, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("查找");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<Vector> v=ClassIO.searchClass(file, t1.getText());
				for(Vector vv:v)
				{System.out.println(vv);}
				 model=new DefaultTableModel(v,titles);
				  table.setModel(model);
			}
		});
		btnNewButton_2.setBounds(217, 29, 86, 29);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("排序");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<Vector> v=ClassIO.sortClass(file);
				for(Vector vv:v)
				{System.out.println(vv);}
				 model=new DefaultTableModel(v,titles);
				  table.setModel(model);
			}
		});
		btnNewButton_3.setBounds(318, 29, 86, 29);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("删除");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<Vector> v=ClassIO.deleteClass(file, t1.getText());
				CClass [] c=new CClass[v.size()];
				int i=0;
				for(Vector vv:v)
				{

					c[i]=new CClass();
					c[i].setCno(vv.elementAt(0).toString());
					c[i].setCname(vv.elementAt(1).toString());
					c[i].setCcredit(Double.parseDouble(vv.elementAt(2).toString()));
				i++;
				}
				ClassIO.writeClass(file, c);
				 model=new DefaultTableModel(v,titles);
				  table.setModel(model);
		
			}
		});
		btnNewButton_4.setBounds(421, 29, 86, 29);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel = new JLabel("课程号");
		lblNewLabel.setBounds(416, 74, 81, 21);
		contentPane.add(lblNewLabel);
	}
}
