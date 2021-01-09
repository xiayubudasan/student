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

import cn.edu.jsu.jyt.dao.ClassDao;
import cn.edu.jsu.jyt.dao.ScoreDao;
import cn.edu.jsu.jyt.util.PageControllerClass;
import cn.edu.jsu.jyt.util.PageControllerSC;
import cn.edu.jsu.jyt.util.PageControllerSC2;
import cn.edu.jsu.jyt.vo.Score;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.ItemListener;
import java.io.File;
import java.awt.event.ItemEvent;

public class FrmSClassMassage extends JFrame {

	private JPanel contentPane;
	private JTextField text;
	private JTable table;
	private JTable table2;
	private DefaultTableModel model;
	private DefaultTableModel model2;
    private TableRowSorter<DefaultTableModel> sorter;
    private TableRowSorter<DefaultTableModel> sorter2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSClassMassage frame = new FrmSClassMassage();
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
	public FrmSClassMassage() {
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
		setBounds(380, 210, 690, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(getOwner());
		
		JLabel lblNewLabel = new JLabel("请输入关键字:");
		lblNewLabel.setBounds(15, 15, 120, 34);
		contentPane.add(lblNewLabel);
		
		text = new JTextField();
		text.setBounds(151, 15, 234, 34);
		contentPane.add(text);
		text.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 69, 370, 221);
		contentPane.add(scrollPane);
		
		Vector<String> titles=new Vector<>();
		Collections.addAll(titles,"课程号","课程名","学分","教师号");
		PageControllerClass p=new PageControllerClass();
		Vector<Vector> v=ClassDao.getSelectAll("select * from class where cno not in(select cno from score where sno="+FrmStuLogin.getVector().elementAt(0).toString()+")");
		p.setList(v);
		Vector<Vector> stuInfo=p.getPaegData();
		model=new DefaultTableModel(stuInfo,titles)
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
		table =new JTable(model);
		sorter=new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(sorter);
		
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(400, 69, 253, 221);
		contentPane.add(scrollPane_1);
		
		
		Vector<Vector> vv=ClassDao.getSelectAll("select * from class where cno in(select cno from score where sno="+FrmStuLogin.getVector().elementAt(0).toString()+")");

		model2=new DefaultTableModel(vv,titles)
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
				table2 = new JTable(model2);
		sorter2=new TableRowSorter<DefaultTableModel>(model2);
		table2.setRowSorter(sorter2);
		
		scrollPane_1.setViewportView(table2);
		
		JButton search = new JButton("查询");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key=text.getText().trim();
				if(key.length()!=0)
				{
					sorter=new TableRowSorter<DefaultTableModel>(model);
					sorter.setRowFilter(RowFilter.regexFilter(key));//
				    table.setRowSorter(sorter);
				}
				else
				{
					sorter=new TableRowSorter<DefaultTableModel>(model);
					sorter.setRowFilter(null);
					table.setRowSorter(sorter);
				}
				
			}
		});
		search.setBounds(398, 18, 82, 29);
		contentPane.add(search);
		
		JButton pre = new JButton("上一页");
		pre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model=new DefaultTableModel(new PageControllerClass().prePage(),titles);
				sorter=new TableRowSorter<DefaultTableModel>(model);
			    table.setRowSorter(sorter);
			    table.setModel(model);
			}
		});
		pre.setBounds(15, 305, 92, 29);
		contentPane.add(pre);
		
		JButton next = new JButton("下一页");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			model=new DefaultTableModel(new PageControllerClass().nextPage(),titles);
			table.setModel(model);
			table.setRowSorter(sorter);
			}
		});
		next.setBounds(122, 305, 92, 29);
		contentPane.add(next);
		
		JButton add = new JButton("增加");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=0)
				{
					while(table.getSelectedRow()!=-1)
					{
					Score s=new Score(FrmStuLogin.getVector().elementAt(0).toString(),table.getValueAt(table.getSelectedRow(), 0).toString());
					if(ScoreDao.insertScore(s)!=0)
					{
						JOptionPane.showMessageDialog(null, "添加课程成功");
					  
					}
					else if(ScoreDao.insertScore(s)==-1)
					{
						JOptionPane.showMessageDialog(null, "已选择该门课程");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "添加课程失败");
					}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "请先选择所需要添加的课程");
				}
			}
		});
		add.setBounds(398, 305, 92, 29);
		contentPane.add(add);
		
		JButton delete = new JButton("删除");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(table2.getSelectedRow()!=-1)//是否选择了行
				{
					if(JOptionPane.showConfirmDialog(null,"确定要删除数据吗？","",JOptionPane.YES_NO_CANCEL_OPTION)==0)
					{
						while(table2.getSelectedRow()!=-1)
						{
							String sql="delete from class where cno="+table2.getValueAt(table2.getSelectedRow(), 0);
							ClassDao.deleteClass(sql);
							model2.removeRow(table2.getSelectedRow());
						}
					}
				}
				else
				{	
					JOptionPane.showMessageDialog(null, "请选择需要删除的行或者在文本框输入");
				}
			}
		});
		delete.setBounds(561, 305, 92, 29);
		contentPane.add(delete);
		
		JComboBox comboBox = new JComboBox(new Integer[] {3,5,10,15,20});
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			   int pageSize=Integer.valueOf(comboBox.getSelectedItem().toString());
			   PageControllerClass pcl=new PageControllerClass();
			   pcl.setCountPerpage(pageSize);
			   model=new DefaultTableModel(pcl.getPaegData(),titles);
			   table.setModel(model);
			   sorter=new TableRowSorter<DefaultTableModel>(model);
			   table.setRowSorter(sorter);
			}
		});
		comboBox.setSelectedIndex(1);
		comboBox.setBounds(287, 306, 82, 27);
		contentPane.add(comboBox);
		
		JButton fresh = new JButton("刷新");
		fresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 Vector<Vector> v=ClassDao.getSelectAll("select * from class where cno not in(select cno from score where sno="+FrmStuLogin.getVector().elementAt(0).toString()+")");
					p.setList(v);
					Vector<Vector> stuInfo=p.getPaegData();
					model=new DefaultTableModel(stuInfo,titles);					
					sorter = new TableRowSorter<DefaultTableModel>(model);//设置表格模型排序器
					table.setModel(model);
					table.setRowSorter(sorter);//设置表格排序器
					Vector<Vector> vv=ClassDao.getSelectAll("select * from class where cno not in(select cno from score where sno="+FrmStuLogin.getVector().elementAt(0).toString()+")");
			       sorter2=new TableRowSorter<DefaultTableModel>(model2);
			       table2.setModel(model2);
					table2.setRowSorter(sorter2);//设置表格排序器
			}
		});
		fresh.setBounds(561, 18, 92, 29);
		contentPane.add(fresh);
	}
}
