package cn.edu.jsu.jyt.frm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import cn.edu.jsu.jyt.dao.StudentDao;
import cn.edu.jsu.jyt.dao.TeacherDao;
import cn.edu.jsu.jyt.util.PageControllerStudent;
import cn.edu.jsu.jyt.util.PageControllerTeacher;
import cn.edu.jsu.jyt.vo.Student;
import cn.edu.jsu.jyt.vo.Teacher;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Label;

public class FrmMStuMassage extends JFrame {

	private JPanel contentPane;
	private JTextField text;
	private JTable table;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private JTextField t5;
	private JTextField t6;
	private JTextField t7;
	private DefaultTableModel model;
	private PageControllerStudent p;
	private Vector<Vector> v;
	private Vector<Vector> stuInfo;
	private Vector<String> titles;
	private TableRowSorter sorter;//����������

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMStuMassage frame = new FrmMStuMassage();
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
	public FrmMStuMassage() {
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
		setBounds(100, 100, 795, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("������ؼ���:");
		lblNewLabel.setBounds(15, 25, 134, 21);
		contentPane.add(lblNewLabel);
		
		text = new JTextField();
		text.setBounds(146, 22, 285, 27);
		contentPane.add(text);
		text.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 61, 482, 313);
		contentPane.add(scrollPane);
		
		//ʹ�ö�̬����洢�б�����������
		titles=new Vector<String> ();
		Collections.addAll(titles, "ѧ��","����","�Ա�","����","����","ϵ��","רҵ");
		p=new PageControllerStudent();
	    v=StudentDao.getSelectAll("select * from student");
		p.setList(v);
		stuInfo=p.getPaegData();
		model=new DefaultTableModel(stuInfo,titles)
				{
			public Class getColumnClass(int column)
			{
				Class returnValue;
				if((column>=0)&&(column<getColumnCount()))
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
		 sorter=new TableRowSorter<DefaultTableModel>(model);
    	 table.setRowSorter(sorter);
		
		scrollPane.setViewportView(table);
		
		JButton search = new JButton("��ѯ");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key=text.getText().trim();//��ȡ����ؼ����ı����ֵ��trim���Կհ�
				if(key.length()!=0) {
					sorter = new TableRowSorter<DefaultTableModel>(model);//����������
					sorter.setRowFilter(RowFilter.regexFilter(key));//�Ƿ����������ֵ
				 	table.setRowSorter(sorter);//���ñ���������
				}else {
				sorter = new TableRowSorter<DefaultTableModel>(model);//����������	
				sorter.setRowFilter(null);//�����ˣ���ʾ��������
			 	table.setRowSorter(sorter);//���ñ���������
				}
			}
		});
		search.setBounds(458, 21, 90, 29);
		contentPane.add(search);
		
		JButton update = new JButton("����");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 if (JOptionPane.showConfirmDialog(null, "ȷ��Ҫ����������", "",    JOptionPane.YES_NO_OPTION) == 0) 
				  {// ȷ���Ի���
					
					 Student s=new Student(t1.getText().trim(),t2.getText().trim(),t3.getText().trim(),Integer.parseInt(t4.getText().trim()),t5.getText().trim(),t6.getText().trim(),t7.getText().trim());
						if(StudentDao.updateStudent(s)==-1)//�����ǰ��Ϣ������
						{
							JOptionPane.showMessageDialog(null, "��ǰѧ����Ϣ�����ڣ��������");
						}
						else if(StudentDao.updateStudent(s)!=0)
						{
							JOptionPane.showMessageDialog(null, "���³ɹ�");
							v=StudentDao.getSelectAll("select * from student");
							p.setList(v);
							stuInfo=p.getPaegData();
							model=new DefaultTableModel(stuInfo,titles);
							table.setModel(model);
							sorter=new TableRowSorter<DefaultTableModel>(model);
					    	table.setRowSorter(sorter);
						}
						else
						{
							JOptionPane.showConfirmDialog(null, "����ʧ�ܣ����Ժ�����");
						}
				  }
						else {
				    JOptionPane.showMessageDialog(null, "��������Ĳ���");
				  }
			}
		});
		update.setBounds(668, 21, 90, 29);
		contentPane.add(update);
		
		JButton delete = new JButton("ɾ��");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow()!=-1)//�Ƿ�ѡ������
				{
					if(JOptionPane.showConfirmDialog(null,"ȷ��Ҫɾ��������","",JOptionPane.YES_NO_CANCEL_OPTION)==0)
					{
						while(table.getSelectedRow()!=-1)
						{
							String sql="delete from student where sno="+table.getValueAt(table.getSelectedRow(), 0).toString();
							StudentDao.deleteStudent(sql);
							model.removeRow(table.getSelectedRow());
							
						}
					}
				}
				else
				{
					if(t1.getText().trim().length()!=0)//�����ѧ�Ź��ų��Ȳ�Ϊ0
					{
						String sql="select * from student where sno="+t1.getText();
						Vector v=StudentDao.getSelectAll(sql);
						if(!v.isEmpty())//���ѧ�Ŵ��ڣ������ɾ��
						{
							String s="delete from student where sno="+t1.getText().trim();
						   StudentDao.deleteStudent(s);
						   Vector<Vector> vv=StudentDao.getSelectAll("select * from student");
							p.setList(vv);
							Vector<Vector> stuInfo=p.getPaegData();
							model=new DefaultTableModel(stuInfo,titles);
							table.setModel(model);
							sorter = new TableRowSorter<DefaultTableModel>(model);//���ñ��ģ��������
							table.setRowSorter(sorter);//���ñ��������	
							
						}
						else
						{
							JOptionPane.showMessageDialog(null, "��ǰѧ����Ų����ڣ����ȼ���");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ�����л������ı�������");
					}
				}
			}
		});
		delete.setBounds(458, 389, 90, 29);
		contentPane.add(delete);
		
		JButton add = new JButton("���");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "ȷ��Ҫ����������", "",    JOptionPane.YES_NO_OPTION) == 0) 
				  {// ȷ���Ի���
					if(t1.getText().trim().length()!=0)
					{Student s=new Student(t1.getText().trim(),t2.getText().trim(),t3.getText().trim(),Integer.parseInt(t4.getText().trim()),t5.getText().trim(),t6.getText().trim(),t7.getText().trim());
						if(StudentDao.insertStudent(s)==-1)//�����ǰ��Ϣ����
						{
							JOptionPane.showMessageDialog(null, "��ǰѧ����Ϣ�Ѿ�����");
						}
						else if(StudentDao.insertStudent(s)!=0)
						{
							JOptionPane.showMessageDialog(null, "����ɹ�");
							v=StudentDao.getSelectAll("select * from student");
							p.setList(v);
							stuInfo=p.getPaegData();
							model=new DefaultTableModel(stuInfo,titles);
							table.setModel(model);
							sorter = new TableRowSorter<DefaultTableModel>(model);//���ñ��ģ��������
						   table.setRowSorter(sorter);
						}
						else
						{
							JOptionPane.showConfirmDialog(null, "����ʧ�ܣ����Ժ�����");
						}
				  }
						else {
				    JOptionPane.showMessageDialog(null, "��������Ĳ���");
				  }
				  }
			}
		});
		add.setBounds(563, 389, 90, 29);
		contentPane.add(add);
		
		JButton btnNewButton = new JButton("��һҳ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model=new DefaultTableModel(new PageControllerStudent().prePage(),titles);
				sorter = new TableRowSorter<DefaultTableModel>(model);//���ñ��ģ��������
				table.setRowSorter(sorter);//���ñ��������
				table.setModel(model);
				
			}
		});
		btnNewButton.setBounds(15, 389, 96, 29);
		contentPane.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("��һҳ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model=new DefaultTableModel(new PageControllerStudent().nextPage(),titles);				sorter = new TableRowSorter<DefaultTableModel>(model);//���ñ��ģ��������
				table.setRowSorter(sorter);//���ñ��������
				table.setModel(model);
			}
		});
		btnNewButton_1.setBounds(146, 389, 90, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("ÿҳ��ʾ:");
		
		lblNewLabel_1.setBounds(266, 393, 81, 21);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox(new Integer[] {3,5,10,15,20});
		comboBox.addItemListener(new ItemListener() 
		{
			
			@Override
			public void itemStateChanged(ItemEvent arg0) 
			{
				// TODO Auto-generated method stub
				   int pageSize=Integer.valueOf(comboBox.getSelectedItem().toString());//��ȡ��������ѡ��ֵ	
				   PageControllerStudent pcl=new PageControllerStudent();
				   pcl.setCountPerpage(pageSize);
				   model=new DefaultTableModel(pcl.getPaegData(),titles);
					
					sorter = new TableRowSorter<DefaultTableModel>(model);//���ñ��ģ��������
					table.setRowSorter(sorter);//���ñ��������
				   table.setModel(model);  

			}
		});
		comboBox.setSelectedIndex(1);//����������Ĭ��ֵ
		comboBox.setBounds(362, 389, 87, 27);
		contentPane.add(comboBox);
		
		Label label = new Label("ѧ��:");
		label.setBounds(503, 61, 64, 30);
		contentPane.add(label);
		
		t1 = new JTextField();
		t1.setBounds(573, 56, 174, 27);
		contentPane.add(t1);
		t1.setColumns(10);
		
		Label label_1 = new Label("����:");
		label_1.setBounds(503, 97, 64, 30);
		contentPane.add(label_1);
		
		Label label_2 = new Label("�Ա�:");
		label_2.setBounds(503, 147, 64, 30);
		contentPane.add(label_2);
		
		Label label_2_1 = new Label("����:");
		label_2_1.setBounds(503, 198, 64, 30);
		contentPane.add(label_2_1);
		
		Label label_2_2 = new Label("����:");
		label_2_2.setBounds(503, 250, 64, 30);
		contentPane.add(label_2_2);
		
		Label label_2_3 = new Label("ϵ��:");
		label_2_3.setBounds(503, 296, 64, 30);
		contentPane.add(label_2_3);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(574, 100, 173, 27);
		contentPane.add(t2);
		
		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(573, 147, 174, 27);
		contentPane.add(t3);
		
		t4 = new JTextField();
		t4.setColumns(10);
		t4.setBounds(573, 198, 174, 27);
		contentPane.add(t4);
		
		t5 = new JTextField();
		t5.setColumns(10);
		t5.setBounds(573, 250, 174, 27);
		contentPane.add(t5);
		
		t6 = new JTextField();
		t6.setColumns(10);
		t6.setBounds(573, 299, 174, 27);
		contentPane.add(t6);
		
		Label label_2_3_1 = new Label("רҵ:");
		label_2_3_1.setBounds(503, 344, 64, 30);
		contentPane.add(label_2_3_1);
		
		t7 = new JTextField();
		t7.setColumns(10);
		t7.setBounds(573, 347, 174, 27);
		contentPane.add(t7);
		
		JButton find = new JButton("����");
		find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t1.getText().trim().length()!=0)
				{
				String sql="select * from student where sno="+t1.getText().trim();
				Vector<Vector> v=StudentDao.getSelectAll(sql);
				if(!v.isEmpty())
				{
					    p.setList(v);
				 		Vector<Vector> stuInfo=p.getPaegData();
				 		model=new DefaultTableModel(stuInfo,titles);	
				    	 table.setModel(model);
				    	 sorter=new TableRowSorter<DefaultTableModel>(model);
				    	 table.setRowSorter(sorter);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "δ��ѯ��ѧ�ţ��������");
				}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "�����������������");
				}
			}
		});
		find.setBounds(563, 21, 90, 29);
		contentPane.add(find);
		
		JButton add_1 = new JButton("ˢ��");
		add_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Vector<Vector> v=StudentDao.getSelectAll("select * from student");
					p.setList(v);
					Vector<Vector> stuInfo=p.getPaegData();
					model=new DefaultTableModel(stuInfo,titles);
					sorter = new TableRowSorter<DefaultTableModel>(model);//���ñ��ģ��������
					table.setRowSorter(sorter);//���ñ��������
					table.setModel(model);
				
			}
		});
		add_1.setBounds(668, 389, 90, 29);
		contentPane.add(add_1);
	}
}
