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
	private DefaultTableModel model;// 定义表格数据模型
	private TableRowSorter sorter;//定义排序器
	
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
		
		JLabel label = new JLabel("请输入查询关键字：");
		label.setBounds(15, 31, 177, 21);
		contentPane.add(label);
		
		txt = new JTextField();
		txt.setBounds(195, 28, 156, 27);
		contentPane.add(txt);
		txt.setColumns(10);
		


		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 85, 629, 253);
		contentPane.add(scrollPane);
		
		Vector<String> titles = new Vector<String>();// 定义动态数组表示表格标题
		Collections.addAll(titles, "学号", "课程号", "成绩");
		PageControllerSC p=new PageControllerSC();
		Vector<Vector> stuInfo = p.getPaegData();//获取第一页的数据


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
     	table = new JTable(model);// 使用DefaultTableModel数据模型实例化表格
     	sorter = new TableRowSorter<DefaultTableModel>(model);//设置表格模型排序器
     	sorter.setRowFilter(null);//不过滤，显示所有数据
	 	table.setRowSorter(sorter);//设置表格的排序器

		scrollPane.setViewportView(table);// 设置使用滚动面板显示表格，如果不使用滚动面板显示，则表格的列标题无法显示

		JButton 查询 = new JButton("查找");
		查询.addActionListener(new ActionListener() {
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
		查询.setBounds(366, 27, 85, 29);
		contentPane.add(查询);
		
		JButton pre = new JButton("上一页");
		pre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 model=new DefaultTableModel(new PageControllerSC().prePage(),titles);//设置数据模型中的数据为上一页内容
				table.setModel(model);//设置表格的数据模型
				sorter.setRowFilter(null);//不过滤，显示所有数据
			 	table.setRowSorter(sorter);//设置表格的排序器
			}
		});
		pre.setBounds(25, 362, 123, 29);
		contentPane.add(pre);
		
		JButton next = new JButton("下一页");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 model=new DefaultTableModel(new PageControllerSC().nextPage(),titles);//设置数据模型中的数据为下一页内容
				table.setModel(model);//设置表格的数据模型
				sorter.setRowFilter(null);//不过滤，显示所有数据
			 	table.setRowSorter(sorter);//设置表格的排序器
			}
		});
		next.setBounds(180, 362, 123, 29);
		contentPane.add(next);
		
		JLabel label_1 = new JLabel("每页显示:");
		label_1.setBounds(347, 366, 98, 21);
		contentPane.add(label_1);
		
		JComboBox comboBox = new JComboBox(new Integer[] {3,5,10,15,20});
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int pageSize=Integer.valueOf(comboBox.getSelectedItem().toString());//获取下拉框所选的值
				PageControllerSC pcl=new PageControllerSC();
				pcl.setCountPerpage(pageSize);//设置每页显示记录条数
				model=new DefaultTableModel(pcl.getPaegData(),titles);//设置数据模型
				table.setModel(model);//设置表格数据模型
				sorter.setRowFilter(null);//不过滤，显示所有数据
			 	table.setRowSorter(sorter);//设置表格的排序器
			}
		});
		comboBox.setSelectedIndex(1);//设置下拉框默认值
		comboBox.setBounds(460, 363, 71, 27);
		contentPane.add(comboBox);
		
		JButton 退出 = new JButton("退出");
		退出.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		退出.setBounds(553, 362, 91, 29);
		contentPane.add(退出);
		
		JButton search_1 = new JButton("查询");
		search_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="select * from score where cj<60 and sno="+FrmStuLogin.getVector().elementAt(0).toString();
				 Vector<Vector> v=ScoreDao.getSelectAll(sql);
					p.setList(v);
					Vector<Vector> stuInfo=p.getPaegData();
					model=new DefaultTableModel(stuInfo,titles);
					sorter = new TableRowSorter<DefaultTableModel>(model);//设置表格模型排序器
					table.setRowSorter(sorter);//设置表格排序器
					table.setModel(model);
				
			}
		});
		search_1.setBounds(466, 27, 85, 29);
		contentPane.add(search_1);
		
		JButton search_1_1 = new JButton("刷新");
		search_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql="select * from score where sno="+FrmStuLogin.getVector().elementAt(0).toString();
				 Vector<Vector> v=ScoreDao.getSelectAll(sql);
					p.setList(v);
					Vector<Vector> stuInfo=p.getPaegData();
					model=new DefaultTableModel(stuInfo,titles);
			
					table.setRowSorter(sorter);//设置表格排序器
					sorter=new TableRowSorter<DefaultTableModel>(model);
					table.setModel(model);
			}
		});
		search_1_1.setBounds(566, 27, 85, 29);
		contentPane.add(search_1_1);
	}
}
