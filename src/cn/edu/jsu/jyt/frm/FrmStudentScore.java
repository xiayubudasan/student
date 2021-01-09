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

import cn.edu.jsu.jyt.dao.ScoreDao;
import cn.edu.jsu.jyt.util.PageControllerSC;

public class FrmStudentScore extends JFrame {

	private JPanel contentPane;
	private JTextField txt;
	private JTable table;
	private DefaultTableModel model;// ����������ģ��
	private TableRowSorter sorter;//����������
	
	private Vector<String> titles;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStudentScore frame = new FrmStudentScore();
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
	public FrmStudentScore() {
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
		setBounds(380, 290, 681, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(getOwner());
		
		JLabel label = new JLabel("�������ѯ�ؼ��֣�");
		label.setBounds(15, 31, 177, 21);
		contentPane.add(label);
		
		txt = new JTextField();
		txt.setBounds(195, 28, 156, 27);
		contentPane.add(txt);
		txt.setColumns(10);
		


		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 85, 629, 253);
		contentPane.add(scrollPane);
		
		Vector<String> titles = new Vector<String>();// ���嶯̬�����ʾ������
		Collections.addAll(titles, "ѧ��", "�γ̺�", "�ɼ�");
		PageControllerSC p=new PageControllerSC();
		Vector<Vector> stuInfo = p.getPaegData();//��ȡ��һҳ������


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
     	table = new JTable(model);// ʹ��DefaultTableModel����ģ��ʵ�������
     	sorter = new TableRowSorter<DefaultTableModel>(model);//���ñ��ģ��������
     	sorter.setRowFilter(null);//�����ˣ���ʾ��������
	 	table.setRowSorter(sorter);//���ñ���������

		scrollPane.setViewportView(table);// ����ʹ�ù��������ʾ��������ʹ�ù��������ʾ��������б����޷���ʾ

		JButton ��ѯ = new JButton("����");
		��ѯ.addActionListener(new ActionListener() {
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
		��ѯ.setBounds(366, 27, 85, 29);
		contentPane.add(��ѯ);
		
		JButton pre = new JButton("��һҳ");
		pre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 model=new DefaultTableModel(new PageControllerSC().prePage(),titles);//��������ģ���е�����Ϊ��һҳ����
				table.setModel(model);//���ñ�������ģ��
				sorter.setRowFilter(null);//�����ˣ���ʾ��������
			 	table.setRowSorter(sorter);//���ñ���������
			}
		});
		pre.setBounds(25, 362, 123, 29);
		contentPane.add(pre);
		
		JButton next = new JButton("��һҳ");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 model=new DefaultTableModel(new PageControllerSC().nextPage(),titles);//��������ģ���е�����Ϊ��һҳ����
				table.setModel(model);//���ñ�������ģ��
				sorter.setRowFilter(null);//�����ˣ���ʾ��������
			 	table.setRowSorter(sorter);//���ñ���������
			}
		});
		next.setBounds(180, 362, 123, 29);
		contentPane.add(next);
		
		JLabel label_1 = new JLabel("ÿҳ��ʾ:");
		label_1.setBounds(347, 366, 98, 21);
		contentPane.add(label_1);
		
		JComboBox comboBox = new JComboBox(new Integer[] {3,5,10,15,20});
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int pageSize=Integer.valueOf(comboBox.getSelectedItem().toString());//��ȡ��������ѡ��ֵ
				PageControllerSC pcl=new PageControllerSC();
				pcl.setCountPerpage(pageSize);//����ÿҳ��ʾ��¼����
				model=new DefaultTableModel(pcl.getPaegData(),titles);//��������ģ��
				table.setModel(model);//���ñ������ģ��
				sorter.setRowFilter(null);//�����ˣ���ʾ��������
			 	table.setRowSorter(sorter);//���ñ���������
			}
		});
		comboBox.setSelectedIndex(1);//����������Ĭ��ֵ
		comboBox.setBounds(460, 363, 71, 27);
		contentPane.add(comboBox);
		
		JButton �˳� = new JButton("�˳�");
		�˳�.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		�˳�.setBounds(553, 362, 91, 29);
		contentPane.add(�˳�);
		
		JButton search_1 = new JButton("��ѯ");
		search_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="select * from score where cj<60 and sno="+FrmStuLogin.getVector().elementAt(0).toString();
				 Vector<Vector> v=ScoreDao.getSelectAll(sql);
					p.setList(v);
					Vector<Vector> stuInfo=p.getPaegData();
					model=new DefaultTableModel(stuInfo,titles);
					sorter = new TableRowSorter<DefaultTableModel>(model);//���ñ��ģ��������
					table.setRowSorter(sorter);//���ñ��������
					table.setModel(model);
				
			}
		});
		search_1.setBounds(466, 27, 85, 29);
		contentPane.add(search_1);
		
		JButton search_1_1 = new JButton("ˢ��");
		search_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql="select * from score where sno="+FrmStuLogin.getVector().elementAt(0).toString();
				 Vector<Vector> v=ScoreDao.getSelectAll(sql);
					p.setList(v);
					Vector<Vector> stuInfo=p.getPaegData();
					model=new DefaultTableModel(stuInfo,titles);
			
					table.setRowSorter(sorter);//���ñ��������
					sorter=new TableRowSorter<DefaultTableModel>(model);
					table.setModel(model);
			}
		});
		search_1_1.setBounds(566, 27, 85, 29);
		contentPane.add(search_1_1);
	}
}
