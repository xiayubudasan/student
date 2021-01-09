package cn.edu.jsu.jyt.util;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.util.DnsSrv.SrvRecord;

import cn.edu.jsu.jyt.dbc.DataBaseConnection02;
import cn.edu.jsu.jyt.vo.EScore;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelPrint {

	private static StringBuffer fileName = new StringBuffer("E:\\ѧ���ɼ�.xls");

	public static void toReadExcel(String name) {
		// ����Excel�е�ֵ
		getAllByExcel();
	}


	private static void getAllByExcel() {

		EScore data = new EScore();

		try {
			// ��ù�����
			Workbook workBook = Workbook.getWorkbook(new File(fileName.toString()));
			// ��ù�����
			Sheet sheet = workBook.getSheet(0);

			// ����к��еĳ���
			int col = sheet.getColumns();
			int row = sheet.getRows();

			System.out.println("col = " + col + " rows = " + row);

			for (int i = 1; i < row; i++) {
				for (int j = 0; j < col; j++) {
					// �������
					String sno = sheet.getCell(j++, i).getContents();
					String sname = sheet.getCell(j++, i).getContents();
					String cname = sheet.getCell(j++, i).getContents();
					String grade=sheet.getCell(j, i).getContents();


					/*
					 * ��ӵ����ݿ� 
					 * 
					 */
					String[] str = new String[] { sno,sname,cname,grade};
			        insertToDB(str);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void insertToDB(String[] str) {
		String sql = "insert into sc (sno,sname,cname,grade) values(?,?,?,?)";
    	DataBaseConnection02 dbcs=new DataBaseConnection02();//ʹ��1�ж�����������ݿ����
    	try(Connection conn=dbcs.getConnection();//��ȡ���ݿ��
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//ʵ����PreparedStatement
    		//PreparedStatement������Ч��ֹsqlע��

    	
    		pstmt.setString(1,str[0]);
    		pstmt.setString(2,str[1]);
    		pstmt.setString(3,str[2]);
    		pstmt.setDouble(4,Double.parseDouble(str[3]));
    			 pstmt.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
	}


	public static void main(String args[]) {
		toReadExcel(fileName.toString());
	}

}
