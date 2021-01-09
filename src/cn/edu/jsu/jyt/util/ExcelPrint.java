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

	private static StringBuffer fileName = new StringBuffer("E:\\学生成绩.xls");

	public static void toReadExcel(String name) {
		// 插入Excel中的值
		getAllByExcel();
	}


	private static void getAllByExcel() {

		EScore data = new EScore();

		try {
			// 获得工作薄
			Workbook workBook = Workbook.getWorkbook(new File(fileName.toString()));
			// 获得工作表
			Sheet sheet = workBook.getSheet(0);

			// 获得行和列的长度
			int col = sheet.getColumns();
			int row = sheet.getRows();

			System.out.println("col = " + col + " rows = " + row);

			for (int i = 1; i < row; i++) {
				for (int j = 0; j < col; j++) {
					// 获得数据
					String sno = sheet.getCell(j++, i).getContents();
					String sname = sheet.getCell(j++, i).getContents();
					String cname = sheet.getCell(j++, i).getContents();
					String grade=sheet.getCell(j, i).getContents();


					/*
					 * 添加到数据库 
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
    	DataBaseConnection02 dbcs=new DataBaseConnection02();//使用1中定义的连接数据库的类
    	try(Connection conn=dbcs.getConnection();//获取数据库接
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//实例化PreparedStatement
    		//PreparedStatement可以有效防止sql注入

    	
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
