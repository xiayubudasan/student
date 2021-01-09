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
	private DefaultTableModel model;// 定义表格数据模型
	private TableRowSorter sorter;//定义排序器
	
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
		
		JLabel label = new JLabel("请输入关键字：");
		label.setBounds(15, 31, 132, 21);
		contentPane.add(label);
		
		txt = new JTextField();
		txt.setBounds(147, 28, 260, 27);
		contentPane.add(txt);
		txt.setColumns(10);
		


		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 85, 397, 242);
		contentPane.add(scrollPane);
		

		// 使用动态数组数据（列标题与行数据）
		Vector<String> titles = new Vector<String>();// 定义动态数组表示表格标题
		Collections.addAll(titles, "课程号", "课程名", "学分","教师号");
		PageControllerClass p=new PageControllerClass();
		Vector<Vector> stuInfo =p.getPaegData();//获取第一页的数据
//		使用静态数据创建DefaultTableModel数据模型
		  model = new DefaultTableModel(stuInfo, titles) {// 使用Vector装载表格数据模型，覆写getColumnClass方法，实现按各列的数据类型排序
			public Class getColumnClass(int column) {//获取列的类型
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
			  if(column==0) {//设置第1列不可编辑
			    return false;
			  }
			  return true;
			  }
    	};// 使用DefaultTableModel数据模型实例化表格
    	sorter = new TableRowSorter<DefaultTableModel>(model);//设置表格模型排序器
		table.setRowSorter(sorter);//设置表格排序器	
		scrollPane.setViewportView(table);// 设置使用滚动面板显示表格，如果不使用滚动面板显示，则表格的列标题无法显示

		JButton search = new JButton("查询");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String key=txt.getText().trim();//获取输入关键字文本框的值，trim忽略空白
				if(key.length()!=0) {
					sorter = new TableRowSorter<DefaultTableModel>(model);//设置排序器
					sorter.setRowFilter(RowFilter.regexFilter(key));//是否包含输入框的值
				 	table.setRowSorter(sorter);//设置表格的排序器
				}else {
				sorter = new TableRowSorter<DefaultTableModel>(model);//设置排序器	
				sorter.setRowFilter(null);//不过滤，显示所有数据
			 	table.setRowSorter(sorter);//设置表格的排序器
				}
			}
		});
		search.setBounds(437, 27, 74, 29);
		contentPane.add(search);
		
		JButton pre = new JButton("上一页");
		pre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 model=new DefaultTableModel(new PageControllerSC().prePage(),titles);//设置数据模型中的数据为上一页内容
				table.setModel(model);//设置表格的数据模型
			}
		});
		pre.setBounds(35, 342, 123, 29);
		contentPane.add(pre);
		
		JButton next = new JButton("下一页");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 model=new DefaultTableModel(new PageControllerSC().nextPage(),titles);//设置数据模型中的数据为下一页内容
				table.setModel(model);//设置表格的数据模型
			}
		});
		next.setBounds(219, 342, 123, 29);
		contentPane.add(next);
		
		JLabel label_1 = new JLabel("每页显示:");
		label_1.setBounds(368, 342, 98, 21);
		contentPane.add(label_1);
		
		JComboBox comboBox = new JComboBox(new Integer[] {3,5,10,15,20});
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int pageSize=Integer.valueOf(comboBox.getSelectedItem().toString());//获取下拉框所选的值
				PageControllerClass pcl=new PageControllerClass();
				pcl.setCountPerpage(pageSize);//设置每页显示记录条数
				model=new DefaultTableModel(pcl.getPaegData(),titles);//设置数据模型
				table.setModel(model);//设置表格数据模型
			}
		});
		comboBox.setSelectedIndex(1);//设置下拉框默认值
		comboBox.setBounds(477, 343, 98, 27);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("删除");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
					if(table.getSelectedRow()!=-1)//是否选择了行
					{
						if(JOptionPane.showConfirmDialog(null,"确定要删除数据吗？","",JOptionPane.YES_NO_CANCEL_OPTION)==0)
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
						if(t1.getText().trim().length()!=0)//输入课程号不为0
						{
							String sql="select * from class where cno="+t1.getText().trim();
							Vector v=ScoreDao.getSelectAll(sql);
							if(!v.isEmpty())//如果课程号存在，则可以删除
							{
								 JOptionPane.showMessageDialog(null, "删除成功");
								String s="delete from class where cno="+t1.getText().trim();
							    ScoreDao.deleteScore(s);
							    Vector<Vector> vv=ClassDao.getSelectAll("select * from class");
								p.setList(vv);
								Vector<Vector> stuInfo=p.getPaegData();
								model=new DefaultTableModel(stuInfo,titles);
								sorter = new TableRowSorter<DefaultTableModel>(model);//设置表格模型排序器
								table.setRowSorter(sorter);//设置表格排序器	
								table.setModel(model);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "当前课程信息不存在，请先加入");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "请选择需要删除的行或者在文本框输入");
						}
					}
					
			}
		});
		btnNewButton.setBounds(613, 27, 71, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("添加");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="select * from  class where cno="+t1.getText().trim();	
				Vector row=ClassDao.getSelectAll(sql);
				if(!row.isEmpty())//如果集合不为空，弹出提示信息
				{
					JOptionPane.showMessageDialog(null, "当前课程号已存在");
				}
				else
				{
					CClass c=new CClass(t1.getText().trim(),t2.getText().trim(),Double.parseDouble(t3.getText().trim()),t4.getText().trim());
				    ClassDao.insertClass(c);
				    Vector<Vector> v=ClassDao.getSelectAll("select * from class");
					p.setList(v);
					Vector<Vector> stuInfo=p.getPaegData();
					model=new DefaultTableModel(stuInfo,titles);
					
					sorter = new TableRowSorter<DefaultTableModel>(model);//设置表格模型排序器
					table.setRowSorter(sorter);//设置表格排序器
					table.setModel(model);
				    JOptionPane.showMessageDialog(null, "添加成功");
				}
				
			}
		});
		btnNewButton_1.setBounds(434, 298, 74, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("更新");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
					  if (JOptionPane.showConfirmDialog(null, "确定要更新数据吗？", "",    JOptionPane.YES_NO_OPTION) == 0) 
					  {// 确定对话框
						
							String sql="select * from  class where cno="+t1.getText().trim();	
							Vector row=ClassDao.getSelectAll(sql);
							if(row.isEmpty())//如果集合为空，弹出提示信息
							{
								JOptionPane.showMessageDialog(null, "不存在当前课程号，请先添加");
		
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
								sorter = new TableRowSorter<DefaultTableModel>(model);//设置表格模型排序器
								table.setRowSorter(sorter);//设置表格排序器	
							    JOptionPane.showMessageDialog(null, "更新成功");
					        } 
					  }
							else {
					    JOptionPane.showMessageDialog(null, "请继续您的操作");
					  }
					
				
			}
		});
		btnNewButton_1_1.setBounds(526, 298, 74, 29);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblNewLabel = new JLabel("课程号");
		lblNewLabel.setBounds(437, 83, 71, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("课程名");
		lblNewLabel_1.setBounds(437, 133, 71, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("  学分");
		lblNewLabel_1_1.setBounds(437, 183, 71, 21);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("教师号");
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
		
		JButton find = new JButton("查找");
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
					sorter = new TableRowSorter<DefaultTableModel>(model);//设置表格模型排序器
					table.setRowSorter(sorter);//设置表格排序器	
				}
				else
				{
					JOptionPane.showMessageDialog(null, "未查询到课程号，请先插入");
				}
				
			}
				else
				{
					JOptionPane.showMessageDialog(null, "请先输入查找内容");
				}
			}
		});
		find.setBounds(524, 27, 74, 29);
		contentPane.add(find);
		
		JButton back = new JButton("刷新");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Vector<Vector> v=ClassDao.getSelectAll("select * from class");
					p.setList(v);
					Vector<Vector> stuInfo=p.getPaegData();
					model=new DefaultTableModel(stuInfo,titles);					
					sorter = new TableRowSorter<DefaultTableModel>(model);//设置表格模型排序器
					table.setModel(model);
				    table.setRowSorter(sorter);//设置表格排序器
			}
		});
		back.setBounds(613, 298, 74, 29);
		contentPane.add(back);
		
		JButton back_1 = new JButton("退出");
		back_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		back_1.setBounds(610, 342, 74, 29);
		contentPane.add(back_1);
	}
}
