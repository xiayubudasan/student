package cn.edu.jsu.jyt.frm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


import cn.edu.jsu.jyt.dao.TeacherDao;
import cn.edu.jsu.jyt.util.PageControllerTeacher;
import cn.edu.jsu.jyt.vo.Teacher;

public class FrmMTeaMassage extends JFrame {

	private JPanel contentPane;
	private JTextField text;
	private JTable table;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private JTextField t5;
	private DefaultTableModel model;
	private PageControllerTeacher p;
	private TableRowSorter sorter;//定义排序器
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMTeaMassage frame = new FrmMTeaMassage();
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
	public FrmMTeaMassage() {
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
		setBounds(100, 100, 775, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("请输出关键字:");
		lblNewLabel.setBounds(25, 25, 134, 21);
		contentPane.add(lblNewLabel);
		
		text = new JTextField();
		text.setBounds(174, 22, 266, 27);
		contentPane.add(text);
		text.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 61, 424, 233);
		contentPane.add(scrollPane);
		
		//使用动态数组存储列标题与行数据
		Vector<String> titles=new Vector<String> ();
		Collections.addAll(titles, "工号","姓名","性别","年龄","职称");
		PageControllerTeacher p=new PageControllerTeacher();
		Vector<Vector> v=TeacherDao.getSelectAll("select * from teacher");
		p.setList(v);
		Vector<Vector> stuInfo=p.getPaegData();
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
		sorter = new TableRowSorter<DefaultTableModel>(model);//设置表格模型排序器
		table.setRowSorter(sorter);//设置表格排序器
		
		scrollPane.setViewportView(table);
		
		JButton search = new JButton("查询");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key=text.getText().trim();
				if(key.length()!=0)
				{
					sorter=new TableRowSorter<DefaultTableModel>(model);
					sorter.setRowFilter(RowFilter.regexFilter(key));
					table.setRowSorter(sorter);//设置表格排序器
				}
				else
				{
					sorter=new TableRowSorter<DefaultTableModel>(model);
					sorter.setRowFilter(RowFilter.regexFilter(null));
					table.setRowSorter(sorter);//设置表格排序器
				}
			}
		});
		search.setBounds(455, 21, 90, 29);
		contentPane.add(search);
		
		JButton update = new JButton("更新");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 if (JOptionPane.showConfirmDialog(null, "确定要更新数据吗？", "",    JOptionPane.YES_NO_OPTION) == 0) 
				  {// 确定对话框
					
						Teacher t=new Teacher(t1.getText().trim(),t2.getText().trim(),t3.getText().trim(),Integer.parseInt(t4.getText().trim()),t5.getText().trim());
						if(TeacherDao.updateTeacher(t)==-1)//如果当前信息不存在
						{
							JOptionPane.showMessageDialog(null, "当前老师信息不存在，请先添加");
						}
						else if(TeacherDao.updateTeacher(t)!=0)
						{
							JOptionPane.showMessageDialog(null, "更新成功");
							Vector<Vector> v=TeacherDao.getSelectAll("select * from teacher");
							p.setList(v);
							Vector<Vector> stuInfo=p.getPaegData();
							sorter = new TableRowSorter<DefaultTableModel>(model);//设置表格模型排序器
							table.setRowSorter(sorter);//设置表格排序器
							model=new DefaultTableModel(stuInfo,titles);
							table.setModel(model);
						}
						else
						{
							JOptionPane.showConfirmDialog(null, "更新失败，请稍后再试");
						}
				  }
						else {
				    JOptionPane.showMessageDialog(null, "请继续您的操作");
				  }
			}
		});
	
		update.setBounds(663, 21, 90, 29);
		contentPane.add(update);
		
		JButton delete = new JButton("删除");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow()!=-1)//是否选择了行
				{
					if(JOptionPane.showConfirmDialog(null,"确定要删除数据吗？","",JOptionPane.YES_NO_CANCEL_OPTION)==0)
					{
						while(table.getSelectedRow()!=-1)
						{
							String sql="delete from tno where tno="+table.getValueAt(table.getSelectedRow(), 0).toString();
							TeacherDao.deleteTeacher(sql);
							model.removeRow(table.getSelectedRow());
						}
					}
				}
				else
				{
					if(t1.getText().trim().length()!=0)//输入的学号工号长度不为0
					{
						String sql="select * from teacher where tno="+t1.getText();
						Vector v=TeacherDao.getSelectAll(sql);
						if(!v.isEmpty())//如果工号存在，则可以删除
						{
							String s="delete from teacher where tno="+t1.getText().trim();
						    TeacherDao.deleteTeacher(s);
						    Vector<Vector> vv=TeacherDao.getSelectAll("select * from class");
							p.setList(vv);
							Vector<Vector> stuInfo=p.getPaegData();
							model=new DefaultTableModel(stuInfo,titles);
							sorter = new TableRowSorter<DefaultTableModel>(model);//设置表格模型排序器
							table.setRowSorter(sorter);//设置表格排序器	
							table.setModel(model);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "当前教师编号不存在，请先加入");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "请选择需要删除的行或者在文本框输入");
					}
				}
			}
		});
		delete.setBounds(455, 309, 90, 29);
		contentPane.add(delete);
		
		JButton add = new JButton("添加");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "确定要插入数据吗？", "",    JOptionPane.YES_NO_OPTION) == 0) 
				  {// 确定对话框
					
					Teacher t=new Teacher(t1.getText().trim(),t2.getText().trim(),t3.getText().trim(),Integer.parseInt(t4.getText().trim()),t5.getText().trim());
						if(TeacherDao.insertTeacher(t)==-1)//如果当前信息存在
						{
							JOptionPane.showMessageDialog(null, "当前老师信息已经存在");
						}
						else if(TeacherDao.insertTeacher(t)!=0)
						{
							JOptionPane.showMessageDialog(null, "插入成功");
							Vector<Vector> v=TeacherDao.getSelectAll("select * from teacher");
							p.setList(v);
							Vector<Vector> stuInfo=p.getPaegData();
							model=new DefaultTableModel(stuInfo,titles);
							
							sorter = new TableRowSorter<DefaultTableModel>(model);//设置表格模型排序器
							table.setRowSorter(sorter);//设置表格排序器
							table.setModel(model);
						}
						else
						{
							JOptionPane.showConfirmDialog(null, "插入失败，请稍后再试");
						}
				  }
						else {
				    JOptionPane.showMessageDialog(null, "请继续您的操作");
				  }
				
			}
		});
		add.setBounds(560, 309, 90, 29);
		contentPane.add(add);
		
		JButton pre = new JButton("上一页");
		pre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model=new DefaultTableModel(new PageControllerTeacher().prePage(),titles);
				
				table.setModel(model);
				sorter = new TableRowSorter<DefaultTableModel>(model);//设置表格模型排序器
				table.setRowSorter(sorter);//设置表格排序器
			}
		});
		pre.setBounds(25, 309, 96, 29);
		contentPane.add(pre);
		
		JButton next = new JButton("下一页");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model=new DefaultTableModel(new PageControllerTeacher().nextPage(),titles);				
				
				table.setModel(model);
				sorter = new TableRowSorter<DefaultTableModel>(model);//设置表格模型排序器
				table.setRowSorter(sorter);//设置表格排序器
			}
		});
		next.setBounds(148, 309, 90, 29);
		contentPane.add(next);
		
		JLabel lblNewLabel_1 = new JLabel("每页显示:");
		lblNewLabel_1.setBounds(253, 309, 81, 21);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox(new Integer[] {3,5,10,15,20});
		comboBox.addItemListener(new ItemListener() 
		{
			
			@Override
			public void itemStateChanged(ItemEvent arg0) 
			{
				// TODO Auto-generated method stub
				   int pageSize=Integer.valueOf(comboBox.getSelectedItem().toString());//获取下拉框所选的值	
				   PageControllerTeacher pcl=new PageControllerTeacher();
				   pcl.setCountPerpage(pageSize);
				   model=new DefaultTableModel(pcl.getPaegData(),titles);
				
				   table.setModel(model); 
				   sorter=new TableRowSorter<DefaultTableModel>(model);
                   table.setRowSorter(sorter);

			}
		});
		comboBox.setSelectedIndex(1);//设置下拉框默认值
		lblNewLabel_1 = new JLabel("每页显示:");
		comboBox.setBounds(362, 310, 87, 27);
		contentPane.add(comboBox);
		
		Label label = new Label("工号:");
		label.setBounds(455, 61, 64, 30);
		contentPane.add(label);
		
		t1 = new JTextField();
		t1.setBounds(525, 65, 184, 27);
		contentPane.add(t1);
		t1.setColumns(10);
		
		Label label_1 = new Label("姓名:");
		label_1.setBounds(455, 107, 64, 30);
		contentPane.add(label_1);
		
		Label label_2 = new Label("性别:");
		label_2.setBounds(455, 155, 64, 30);
		contentPane.add(label_2);
		
		Label label_2_1 = new Label("年龄:");
		label_2_1.setBounds(455, 208, 64, 30);
		contentPane.add(label_2_1);
		
		Label label_2_3 = new Label("职称:");
		label_2_3.setBounds(455, 255, 64, 30);
		contentPane.add(label_2_3);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(525, 110, 184, 27);
		contentPane.add(t2);
		
		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(525, 158, 184, 27);
		contentPane.add(t3);
		
		t4 = new JTextField();
		t4.setColumns(10);
		t4.setBounds(525, 208, 184, 27);
		contentPane.add(t4);
		
		t5 = new JTextField();
		t5.setColumns(10);
		t5.setBounds(525, 258, 184, 27);
		contentPane.add(t5);
		
		JButton find = new JButton("查找");
		find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(t1.getText().trim().length()!=0)
				 {
					 String sql="select * from teacher where tno= "+t1.getText().trim();
				     Vector<Vector> v=TeacherDao.getSelectAll(sql);
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
				    	 JOptionPane.showMessageDialog(null, "未查询到学号，请先添加");
				     }
				 }
				 else
				 {
					 JOptionPane.showMessageDialog(null, "请先输入查找内容");
				 }
			}
		});
		find.setBounds(558, 21, 90, 29);
		contentPane.add(find);
		
		JButton add_1 = new JButton("刷新");
		add_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Vector<Vector> v=TeacherDao.getSelectAll("select * from teacher");
					p.setList(v);
					Vector<Vector> stuInfo=p.getPaegData();
					model=new DefaultTableModel(stuInfo,titles);
					sorter = new TableRowSorter<DefaultTableModel>(model);//设置表格模型排序器
					table.setRowSorter(sorter);//设置表格排序器
					table.setModel(model);
			}
		});
		add_1.setBounds(663, 309, 90, 29);
		contentPane.add(add_1);
	}
}
