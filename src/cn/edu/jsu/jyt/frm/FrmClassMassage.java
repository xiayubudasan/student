package cn.edu.jsu.jyt.frm;


import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import cn.edu.jsu.jyt.dao.ClassDao;
import cn.edu.jsu.jyt.dao.ScoreDao;
import cn.edu.jsu.jyt.dao.StudentDao;
import cn.edu.jsu.jyt.dao.TeacherDao;
import cn.edu.jsu.jyt.util.PageControllerClass;
import cn.edu.jsu.jyt.util.PageControllerSC;
import cn.edu.jsu.jyt.vo.CClass;

import javax.swing.JTextArea;

public class FrmClassMassage extends JFrame {

	private JPanel contentPane;
	private JTextField txt;
	private JTable table;
	private DefaultTableModel model;// ����������ģ��
	private TableRowSorter sorter;//����������
	
	private Vector<String> titles;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmClassMassage frame = new FrmClassMassage();
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
	public FrmClassMassage() {
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
		setBounds(100, 100, 721, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("������ؼ��֣�");
		label.setBounds(15, 31, 132, 21);
		contentPane.add(label);
		
		txt = new JTextField();
		txt.setBounds(147, 28, 260, 27);
		contentPane.add(txt);
		txt.setColumns(10);
		


		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 85, 397, 242);
		contentPane.add(scrollPane);
		

		// ʹ�ö�̬�������ݣ��б����������ݣ�
		Vector<String> titles = new Vector<String>();// ���嶯̬�����ʾ������
		Collections.addAll(titles, "�γ̺�", "�γ���", "ѧ��","��ʦ��");
		PageControllerClass p=new PageControllerClass();
		Vector<Vector> stuInfo =p.getPaegData();//��ȡ��һҳ������
//		ʹ�þ�̬���ݴ���DefaultTableModel����ģ��
		  model = new DefaultTableModel(stuInfo, titles) {// ʹ��Vectorװ�ر������ģ�ͣ���дgetColumnClass������ʵ�ְ����е�������������
			public Class getColumnClass(int column) {//��ȡ�е�����
				Class returnValue;
				if ((column >= 0) && (column < getColumnCount())) {
					returnValue = getValueAt(0, column).getClass();
				} else {
					returnValue = Object.class;
				}
				return returnValue;
			}
		};
		
     	table = new JTable(model)
    	{
			  @Override
			  public boolean isCellEditable(int row, int column) {
			  if(column==0) {//���õ�1�в��ɱ༭
			    return false;
			  }
			  return true;
			  }
    	};// ʹ��DefaultTableModel����ģ��ʵ�������
    	sorter = new TableRowSorter<DefaultTableModel>(model);//���ñ��ģ��������
		table.setRowSorter(sorter);//���ñ��������	
		scrollPane.setViewportView(table);// ����ʹ�ù��������ʾ��������ʹ�ù��������ʾ��������б����޷���ʾ

		JButton search = new JButton("��ѯ");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String key=txt.getText().trim();//��ȡ����ؼ����ı����ֵ��trim���Կհ�
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
		search.setBounds(437, 27, 74, 29);
		contentPane.add(search);
		
		JButton pre = new JButton("��һҳ");
		pre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 model=new DefaultTableModel(new PageControllerSC().prePage(),titles);//��������ģ���е�����Ϊ��һҳ����
				table.setModel(model);//���ñ�������ģ��
			}
		});
		pre.setBounds(35, 342, 123, 29);
		contentPane.add(pre);
		
		JButton next = new JButton("��һҳ");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 model=new DefaultTableModel(new PageControllerSC().nextPage(),titles);//��������ģ���е�����Ϊ��һҳ����
				table.setModel(model);//���ñ�������ģ��
			}
		});
		next.setBounds(219, 342, 123, 29);
		contentPane.add(next);
		
		JLabel label_1 = new JLabel("ÿҳ��ʾ:");
		label_1.setBounds(368, 342, 98, 21);
		contentPane.add(label_1);
		
		JComboBox comboBox = new JComboBox(new Integer[] {3,5,10,15,20});
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int pageSize=Integer.valueOf(comboBox.getSelectedItem().toString());//��ȡ��������ѡ��ֵ
				PageControllerClass pcl=new PageControllerClass();
				pcl.setCountPerpage(pageSize);//����ÿҳ��ʾ��¼����
				model=new DefaultTableModel(pcl.getPaegData(),titles);//��������ģ��
				table.setModel(model);//���ñ������ģ��
			}
		});
		comboBox.setSelectedIndex(1);//����������Ĭ��ֵ
		comboBox.setBounds(477, 343, 98, 27);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("ɾ��");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
					if(table.getSelectedRow()!=-1)//�Ƿ�ѡ������
					{
						if(JOptionPane.showConfirmDialog(null,"ȷ��Ҫɾ��������","",JOptionPane.YES_NO_CANCEL_OPTION)==0)
						{
							while(table.getSelectedRow()!=-1)
							{
								String sql="delete from class where cno="+table.getValueAt(table.getSelectedRow(), 0);
								ClassDao.deleteClass(sql);
								model.removeRow(table.getSelectedRow());
							}
						}
					}
					else
					{
						if(t1.getText().trim().length()!=0)//����γ̺Ų�Ϊ0
						{
							String sql="select * from class where cno="+t1.getText().trim();
							Vector v=ScoreDao.getSelectAll(sql);
							if(!v.isEmpty())//����γ̺Ŵ��ڣ������ɾ��
							{
								 JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
								String s="delete from class where cno="+t1.getText().trim();
							    ScoreDao.deleteScore(s);
							    Vector<Vector> vv=ClassDao.getSelectAll("select * from class");
								p.setList(vv);
								Vector<Vector> stuInfo=p.getPaegData();
								model=new DefaultTableModel(stuInfo,titles);
								sorter = new TableRowSorter<DefaultTableModel>(model);//���ñ��ģ��������
								table.setRowSorter(sorter);//���ñ��������	
								table.setModel(model);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "��ǰ�γ���Ϣ�����ڣ����ȼ���");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ�����л������ı�������");
						}
					}
					
			}
		});
		btnNewButton.setBounds(613, 27, 71, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("���");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="select * from  class where cno="+t1.getText().trim();	
				Vector row=ClassDao.getSelectAll(sql);
				if(!row.isEmpty())//������ϲ�Ϊ�գ�������ʾ��Ϣ
				{
					JOptionPane.showMessageDialog(null, "��ǰ�γ̺��Ѵ���");
				}
				else
				{
					CClass c=new CClass(t1.getText().trim(),t2.getText().trim(),Double.parseDouble(t3.getText().trim()),t4.getText().trim());
				    ClassDao.insertClass(c);
				    Vector<Vector> v=ClassDao.getSelectAll("select * from class");
					p.setList(v);
					Vector<Vector> stuInfo=p.getPaegData();
					model=new DefaultTableModel(stuInfo,titles);
					
					sorter = new TableRowSorter<DefaultTableModel>(model);//���ñ��ģ��������
					table.setRowSorter(sorter);//���ñ��������
					table.setModel(model);
				    JOptionPane.showMessageDialog(null, "��ӳɹ�");
				}
				
			}
		});
		btnNewButton_1.setBounds(434, 298, 74, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("����");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
					  if (JOptionPane.showConfirmDialog(null, "ȷ��Ҫ����������", "",    JOptionPane.YES_NO_OPTION) == 0) 
					  {// ȷ���Ի���
						
							String sql="select * from  class where cno="+t1.getText().trim();	
							Vector row=ClassDao.getSelectAll(sql);
							if(row.isEmpty())//�������Ϊ�գ�������ʾ��Ϣ
							{
								JOptionPane.showMessageDialog(null, "�����ڵ�ǰ�γ̺ţ��������");
		
							}
							else
							{
								
								CClass c=new CClass(t1.getText().trim(),t2.getText().trim(),Double.parseDouble(t3.getText().trim()),t4.getText().trim());
							    ClassDao.updateClass(c, t1.getText().trim());
							    Vector<Vector> v=ClassDao.getSelectAll("select * from class");
								p.setList(v);
								Vector<Vector> stuInfo=p.getPaegData();
								model=new DefaultTableModel(stuInfo,titles);
								table.setModel(model);
								sorter = new TableRowSorter<DefaultTableModel>(model);//���ñ��ģ��������
								table.setRowSorter(sorter);//���ñ��������	
							    JOptionPane.showMessageDialog(null, "���³ɹ�");
					        } 
					  }
							else {
					    JOptionPane.showMessageDialog(null, "��������Ĳ���");
					  }
					
				
			}
		});
		btnNewButton_1_1.setBounds(526, 298, 74, 29);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblNewLabel = new JLabel("�γ̺�");
		lblNewLabel.setBounds(437, 83, 71, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("�γ���");
		lblNewLabel_1.setBounds(437, 133, 71, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("  ѧ��");
		lblNewLabel_1_1.setBounds(437, 183, 71, 21);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("��ʦ��");
		lblNewLabel_1_2.setBounds(440, 233, 71, 21);
		contentPane.add(lblNewLabel_1_2);
		
		t1 = new JTextField();
		t1.setBounds(523, 85, 161, 27);
		contentPane.add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(523, 130, 161, 27);
		contentPane.add(t2);
		
		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(523, 180, 161, 27);
		contentPane.add(t3);
		
		t4 = new JTextField();
		t4.setColumns(10);
		t4.setBounds(523, 230, 161, 27);
		contentPane.add(t4);
		
		JButton find = new JButton("����");
		find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(t1.getText().trim().length()!=0)
				{
				String sql="select * from class where cno="+t1.getText().trim();
				Vector<Vector> v=ClassDao.getSelectAll(sql);
				if(!v.isEmpty())
				{
					 p.setList(v);
				 		Vector<Vector> stuInfo=p.getPaegData();
				 		model=new DefaultTableModel(stuInfo,titles);	
				    	 table.setModel(model);
					sorter = new TableRowSorter<DefaultTableModel>(model);//���ñ��ģ��������
					table.setRowSorter(sorter);//���ñ��������	
				}
				else
				{
					JOptionPane.showMessageDialog(null, "δ��ѯ���γ̺ţ����Ȳ���");
				}
				
			}
				else
				{
					JOptionPane.showMessageDialog(null, "���������������");
				}
			}
		});
		find.setBounds(524, 27, 74, 29);
		contentPane.add(find);
		
		JButton back = new JButton("ˢ��");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Vector<Vector> v=ClassDao.getSelectAll("select * from class");
					p.setList(v);
					Vector<Vector> stuInfo=p.getPaegData();
					model=new DefaultTableModel(stuInfo,titles);					
					sorter = new TableRowSorter<DefaultTableModel>(model);//���ñ��ģ��������
					table.setModel(model);
				    table.setRowSorter(sorter);//���ñ��������
			}
		});
		back.setBounds(613, 298, 74, 29);
		contentPane.add(back);
		
		JButton back_1 = new JButton("�˳�");
		back_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		back_1.setBounds(610, 342, 74, 29);
		contentPane.add(back_1);
	}
}
