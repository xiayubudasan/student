package cn.edu.jsu.jyt.frm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import cn.edu.jsu.jyt.dao.ScoreDao;
import cn.edu.jsu.jyt.dao.StudentDao;
import cn.edu.jsu.jyt.dao.TeacherDao;
import cn.edu.jsu.jyt.util.PageControllerSC2;
import cn.edu.jsu.jyt.vo.Score;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class FrmTScoreMassage extends JFrame {

	private JPanel contentPane;
	private JTextField text;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private TableRowSorter sorter;//����������
	private JButton pre;
	private JButton next;
	private JLabel lblNewLabel_1;
	private JButton delete;
	private JButton update;
	private JButton add;
	private JLabel lblNewLabel_2;
	private JTextField t1;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextField t2;
	private JTextField t3;
	private JButton find;
	private JButton back;
	private JButton back_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmTScoreMassage frame = new FrmTScoreMassage();
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
	public FrmTScoreMassage() {
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
		setBounds(100, 100, 676, 303);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(getOwner());
		
		JLabel lblNewLabel = new JLabel("������ؼ��֣�");
		lblNewLabel.setBounds(15, 15, 131, 21);
		contentPane.add(lblNewLabel);
		
		text = new JTextField();
		text.setBounds(158, 12, 209, 27);
		contentPane.add(text);
		text.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 51, 342, 153);
		contentPane.add(scrollPane);
		
		//ʹ�ö�̬����洢�б�����������
		Vector<String> titles=new Vector<String> ();
		Collections.addAll(titles, "ѧ��","�γ̺�","�ɼ�");
		PageControllerSC2 p=new PageControllerSC2();
		Vector<Vector> stuInfo=p.getPaegData();//��ȡ��һҳ����	
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
			
		table = new JTable(model)
				{

					@Override
					public boolean isCellEditable(int row, int column) {
						// TODO Auto-generated method stub
						if(column==0||column==1)
						{
							return false;
						}
						return true;
					}
			
				};
				sorter = new TableRowSorter<DefaultTableModel>(model);//���ñ��ģ��������
				table.setRowSorter(sorter);//���ñ��������
		scrollPane.setViewportView(table);
		
		JButton search = new JButton("��ѯ");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key=text.getText().trim();
				if(key.length()!=0)
				{
					sorter=new TableRowSorter<DefaultTableModel>(model);
					sorter.setRowFilter(RowFilter.regexFilter(key));
					table.setRowSorter(sorter);//���ñ��������
				}
				else
				{
					sorter=new TableRowSorter<DefaultTableModel>(model);
					sorter.setRowFilter(RowFilter.regexFilter(null));
					table.setRowSorter(sorter);//���ñ��������
				}
			}		
		});
		search.setBounds(375, 11, 78, 29);
		contentPane.add(search);
		
		pre = new JButton("��һҳ");
		pre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			model=new DefaultTableModel(new PageControllerSC2().prePage(),titles);
			table.setModel(model);
			}
		});
		pre.setBounds(35, 215, 111, 29);
		contentPane.add(pre);
		
		next = new JButton("��һҳ");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model=new DefaultTableModel(new PageControllerSC2().nextPage(),titles);
				table.setModel(model);
			}
		});
		next.setBounds(238, 215, 111, 29);
		contentPane.add(next);
		
		lblNewLabel_1 = new JLabel("ÿҳ��ʾ:");
		lblNewLabel_1.setBounds(364, 219, 81, 21);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox(new Integer[] {3,5,10,15,20});
		comboBox.addItemListener(new ItemListener() 
		{
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
			   int pageSize=Integer.valueOf(comboBox.getSelectedItem().toString());//��ȡ��������ѡ��ֵ	
			   PageControllerSC2 pcl=new PageControllerSC2();
			   pcl.setCountPerpage(pageSize);
			   model=new DefaultTableModel(pcl.getPaegData(),titles);
			   table.setModel(model);  
			}
		});
		comboBox.setSelectedIndex(1);//����������Ĭ��ֵ
		comboBox.setBounds(460, 216, 86, 27);
		contentPane.add(comboBox);
		
		delete = new JButton("ɾ��");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1)//�Ƿ�ѡ������
				{
					if(JOptionPane.showConfirmDialog(null,"ȷ��Ҫɾ��������","",JOptionPane.YES_NO_CANCEL_OPTION)==0)
					{
						while(table.getSelectedRow()!=-1)
						{
							String sql="delete from score where sno="+table.getValueAt(table.getSelectedRow(), 0)
							+" and cno="+table.getValueAt(table.getSelectedRow(), 1);
							ScoreDao.deleteScore(sql);
							model.removeRow(table.getSelectedRow());
						}
					}
				}
				else
				{
					if(t1.getText().trim().length()!=0&t2.getText().trim().length()!=0)//�����ѧ��,�γ̺Ų�Ϊ0
					{
						String sql="select * from score where sno="+t1.getText().trim()+" and cno="+t2.getText().trim();
						Vector v=ScoreDao.getSelectAll(sql);
						if(!v.isEmpty())//���ѧ�ţ��γ̺Ŵ��ڣ������ɾ��
						{
							String s="delect from score where sno="+t1.getText().trim()+" and cno="+t2.getText().trim();
						    ScoreDao.deleteScore(s);
							   Vector<Vector> vv=ScoreDao.getSelectAll("select * from class");
								p.setList(vv);
								Vector<Vector> stuInfo=p.getPaegData();
								model=new DefaultTableModel(stuInfo,titles);
								sorter = new TableRowSorter<DefaultTableModel>(model);//���ñ��ģ��������
								table.setRowSorter(sorter);//���ñ��������	
								table.setModel(model);
						    
						}
						else
						{
							JOptionPane.showMessageDialog(null, "��ǰѧ���ɼ������ڣ����ȼ���");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ�����л������ı�������");
					}
				}
			}
		});
		delete.setBounds(561, 11, 78, 29);
		contentPane.add(delete);
		
		update = new JButton("����");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if (JOptionPane.showConfirmDialog(null, "ȷ��Ҫ����������", "",    JOptionPane.YES_NO_OPTION) == 0) 
				  {// ȷ���Ի���
					
						Score c=new Score(t1.getText().trim(),t2.getText().trim(),Double.parseDouble(t3.getText().trim()));
						if(ScoreDao.updateScore(c)==-1)//�����ǰ��Ϣ������
						{
							JOptionPane.showMessageDialog(null, "��ǰ�ɼ���Ϣ�����ڣ��������");
						}
						else if(ScoreDao.updateScore(c)!=0)
						{
							JOptionPane.showMessageDialog(null, "���³ɹ�");
							Vector<Vector> v=ScoreDao.getSelectAll("select * from student");
							p.setList(v);
							Vector<Vector> stuInfo=p.getPaegData();
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
				
			}}
		);
		update.setBounds(375, 175, 78, 29);
		contentPane.add(update);
		
		add = new JButton("���");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if (JOptionPane.showConfirmDialog(null, "ȷ��Ҫ����������", "",    JOptionPane.YES_NO_OPTION) == 0) 
				  {// ȷ���Ի���
					
						Score c=new Score(t1.getText().trim(),t2.getText().trim(),Double.parseDouble(t3.getText().trim()));
						if(ScoreDao.insertScore(c)==-1)//�����ǰ��Ϣ����
						{
							JOptionPane.showMessageDialog(null, "��ǰ�ɼ���Ϣ����");
						}
						else if(ScoreDao.insertScore(c)!=0)
						{
							JOptionPane.showMessageDialog(null, "����ɹ�");
							Vector v=new Vector();
							Collections.addAll(v, c.getSno(),c.getCno(),c.getCj());
							stuInfo.add(v);
							model.setDataVector(stuInfo, titles);
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
			
		}	
		);
		add.setBounds(468, 175, 78, 29);
		contentPane.add(add);
		
		lblNewLabel_2 = new JLabel("ѧ��");
		lblNewLabel_2.setBounds(382, 55, 78, 21);
		contentPane.add(lblNewLabel_2);
		
		t1 = new JTextField();
		t1.setBounds(449, 52, 167, 27);
		contentPane.add(t1);
		t1.setColumns(10);
		
		lblNewLabel_3 = new JLabel("�γ̺�");
		lblNewLabel_3.setBounds(382, 100, 78, 21);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("�ɼ�");
		lblNewLabel_4.setBounds(382, 139, 78, 21);
		contentPane.add(lblNewLabel_4);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(449, 97, 167, 27);
		contentPane.add(t2);
		
		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(449, 136, 167, 27);
		contentPane.add(t3);
		
		find = new JButton("����");
		find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(t1.getText().trim().length()!=0 && t2.getText().length()!=0)
				{
				String sql="select * from score where sno="+t1.getText().trim()+" and cno="+t2.getText().trim();
				Vector<Vector> v=ScoreDao.getSelectAll(sql);
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
		find.setBounds(468, 11, 78, 29);
		contentPane.add(find);
		
		back = new JButton("ˢ��");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Vector<Vector> v=ScoreDao.getSelectAll("select * from score");
					p.setList(v);
					Vector<Vector> stuInfo=p.getPaegData();
					model=new DefaultTableModel(stuInfo,titles);
					sorter = new TableRowSorter<DefaultTableModel>(model);//���ñ��ģ��������
					table.setRowSorter(sorter);//���ñ��������
					table.setModel(model);
		
			}
		});
		back.setBounds(561, 175, 78, 29);
		contentPane.add(back);
		
		back_1 = new JButton("�˳�");
		back_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		back_1.setBounds(561, 215, 78, 29);
		contentPane.add(back_1);
	}
}
