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
	 * ���뵽Excel
	 */
private static String fileName="E:\\ѧ���ɼ�.xls";
public static void toWriteExcle() {
	try
	{
		WritableWorkbook excelBook=null;
		File file=new File(fileName);
		if(!file.exists())
		{
			file.createNewFile();
		}
		//����һ��Workbook
		excelBook=Workbook.createWorkbook(file);
		
		//����������
		WritableSheet excelSheet=excelBook.createSheet("Test", 0);
		
		String sql="select * from (select student.sno,sname,cname,cj from score,class,student where student.sno=score.sno and score.cno=class.cno)as a  order by cj desc";
		List<EScore> list=new ArrayList<EScore>();//����Ҫ���ص����м�¼����
    	DataBaseConnection02 dbcs=new DataBaseConnection02();//ʹ��1�ж�����������ݿ����
    	try(Connection conn=dbcs.getConnection();//��ȡ���ݿ��
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//ʵ����PreparedStatement
    		//PreparedStatement������Ч��ֹsqlע��
    		ResultSet rs=pstmt.executeQuery();//ִ�в�ѯ��䣬����ŵ����ݼ���
    		while(rs.next()) {//�������ݼ�
    		    EScore sc=new EScore();
    			 sc.setSno(rs.getString(1));
    			 sc.setSname(rs.getString(2));
    			 sc.setCname(rs.getString(3));
    			 sc.setGrade(rs.getDouble(4));
    			list.add(sc);//����������ӵ���¼������
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	
    	//Ҫ���뵽��Excel�����к�
    	Label labelSno = new Label(0, 0 , "ѧ��");
		Label labelSname = new Label(1, 0 , "����");
		Label labelCno = new Label(2, 0 , "�γ�");
		Label labelCj = new Label(3, 0 , "�ɼ�");
		
		//��ӵ�һ�е���Ԫ��
		excelSheet.addCell(labelSno);
		excelSheet.addCell(labelSname);
		excelSheet.addCell(labelCno);
		excelSheet.addCell(labelCj);
		
		//������������
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
		//д���ĵ�
		excelBook.write();
		
		//�ر�Excel����������
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
