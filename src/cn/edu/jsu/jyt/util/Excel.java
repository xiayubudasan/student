package cn.edu.jsu.jyt.util;

import java.awt.image.WritableRaster;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import cn.edu.jsu.jyt.dbc.DataBaseConnection02;
import cn.edu.jsu.jyt.vo.EScore;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.*;
public class Excel {
	/**
	 * 导入到Excel
	 */
private static String fileName="E:\\学生成绩.xls";
public static void toWriteExcle() {
	try
	{
		WritableWorkbook excelBook=null;
		File file=new File(fileName);
		if(!file.exists())
		{
			file.createNewFile();
		}
		//创建一个Workbook
		excelBook=Workbook.createWorkbook(file);
		
		//创建工作表
		WritableSheet excelSheet=excelBook.createSheet("Test", 0);
		
		String sql="select * from (select student.sno,sname,cname,cj from score,class,student where student.sno=score.sno and score.cno=class.cno)as a  order by cj desc";
		List<EScore> list=new ArrayList<EScore>();//定义要返回的所有记录集合
    	DataBaseConnection02 dbcs=new DataBaseConnection02();//使用1中定义的连接数据库的类
    	try(Connection conn=dbcs.getConnection();//获取数据库接
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//实例化PreparedStatement
    		//PreparedStatement可以有效防止sql注入
    		ResultSet rs=pstmt.executeQuery();//执行查询语句，结果放到数据集中
    		while(rs.next()) {//遍历数据集
    		    EScore sc=new EScore();
    			 sc.setSno(rs.getString(1));
    			 sc.setSname(rs.getString(2));
    			 sc.setCname(rs.getString(3));
    			 sc.setGrade(rs.getDouble(4));
    			list.add(sc);//将行数据添加到记录集合中
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	//要插入到的Excel表格的行号
    	Label labelSno = new Label(0, 0 , "学号");
		Label labelSname = new Label(1, 0 , "姓名");
		Label labelCno = new Label(2, 0 , "课程");
		Label labelCj = new Label(3, 0 , "成绩");
		
		//添加第一列到单元格
		excelSheet.addCell(labelSno);
		excelSheet.addCell(labelSname);
		excelSheet.addCell(labelCno);
		excelSheet.addCell(labelCj);
		
		//导入其他数据
		for(int i=0;i<list.size();i++)
		{
			Label labelSno2 = new Label(0, i+1 , list.get(i).getSno());
			Label labelSname2 = new Label(1, i+1 , list.get(i).getSname());
			Label labelCno2 = new Label(2, i+1 , list.get(i).getCname());
			Label labelCj2 = new Label(3, i+1, String.valueOf(list.get(i).getGrade()));
			
			excelSheet.addCell(labelSno2);
			excelSheet.addCell(labelSname2);
			excelSheet.addCell(labelCno2);
			excelSheet.addCell(labelCj2);
			
		}
		//写入文档
		excelBook.write();
		
		//关闭Excel工作薄对象
		excelBook.close();
		
	}
	catch (Exception e) {
		// TODO: handle exception
	}

}
public static void main(String args[])
{
	toWriteExcle();
}

}
