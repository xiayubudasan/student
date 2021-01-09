package cn.edu.jsu.jyt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import cn.edu.jsu.jyt.dbc.DataBaseConnection02;
import cn.edu.jsu.jyt.vo.CClass;
/**
 * 对class表的操作
 * @author J jyt
 *
 */
public class ClassDao {
	/**
	 * class表更新
	 * @param c CClass
	 * @param s String
	 */
	public static void updateClass(CClass c,String s)
	{
		String sql="update  class set cname=?,ccredit=?,tno=? where cno=? ";
		DataBaseConnection02 dbcs=new DataBaseConnection02();//使用1中定义的连接数据库的类
    	try(Connection conn=dbcs.getConnection();//获取数据库接
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//实例化PreparedStatement
    		//PreparedStatement可以有效防止sql注入
    	 
    	  pstmt.setString(1, c.getCname());
    	  pstmt.setDouble(2, c.getCcredit());
    	  pstmt.setString(3, c.getTno());
    	  pstmt.setString(4, s);
    	  pstmt.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
	}
/**
 * class表插入
 * @param c
 */
	public static void insertClass(CClass c)
	{
		String sql="replace into class(cno,cname,ccredit,tno) values(?,?,?,?)";
		DataBaseConnection02 dbcs=new DataBaseConnection02();//使用1中定义的连接数据库的类
    	try(Connection conn=dbcs.getConnection();//获取数据库接
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//实例化PreparedStatement
    		//PreparedStatement可以有效防止sql注入
    	  pstmt.setString(1, c.getCno());
    	  pstmt.setString(2, c.getCname());
    	  pstmt.setDouble(3, c.getCcredit());
    	  pstmt.setString(4, c.getTno());
    	  pstmt.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
	}
	/**
	 * class表删除
	 * @param sql
	 * @return
	 */
	public static int deleteClass(String sql)
	{
		DataBaseConnection02 dbcs=new DataBaseConnection02();//使用1中定义的连接数据库的类
    	try(Connection conn=dbcs.getConnection();//获取数据库接
    		PreparedStatement pstmt=conn.prepareStatement(sql.toString());){//实例化PreparedStatement
    		//PreparedStatement可以有效防止sql注入
    	    return  pstmt.executeUpdate();
  
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return 0;
	}
	/**
	 * class表获得全部信息
	 * @param sql
	 * @return
	 */
		public static Vector<Vector> getSelectAll(String sql){
	    	Vector<Vector> rows=new Vector<Vector>();//定义要返回的所有记录集合
	    	DataBaseConnection02 dbcs=new DataBaseConnection02();//使用1中定义的连接数据库的类
	    	try(Connection conn=dbcs.getConnection();//获取数据库接
	    		PreparedStatement pstmt=conn.prepareStatement(sql);){//实例化PreparedStatement
	    		//PreparedStatement可以有效防止sql注入
	    		ResultSet rs=pstmt.executeQuery();//执行查询语句，结果放到数据集中
	    		while(rs.next()) {//遍历数据集
	    			Vector row=new Vector();//定义行数据				
	    			row.add(rs.getString(1));//获取第一个字段
	    			row.add(rs.getString(2));//获取第二个字段
	    			row.add(rs.getDouble(3)); //获取第三个字段	
	    			row.add(rs.getString(4)); //获取第三个字段	
	    			rows.add(row);//将行数据添加到记录集合中
	    		}
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	return rows;//返回所有行数据
		}
		/**
		 * class表获得全部cno,cname,ccredit
		 * @param sql
		 * @return
		 */
		public static Vector<Vector> getSelectClass(String sql){
	    	Vector<Vector> rows=new Vector<Vector>();//定义要返回的所有记录集合
	    	DataBaseConnection02 dbcs=new DataBaseConnection02();//使用1中定义的连接数据库的类
	    	try(Connection conn=dbcs.getConnection();//获取数据库接
	    		PreparedStatement pstmt=conn.prepareStatement(sql);){//实例化PreparedStatement
	    		//PreparedStatement可以有效防止sql注入
	    		ResultSet rs=pstmt.executeQuery();//执行查询语句，结果放到数据集中
	    		while(rs.next()) {//遍历数据集
	    			Vector row=new Vector();//定义行数据				
	    			row.add(rs.getString(1));//获取第一个字段
	    			row.add(rs.getString(2));//获取第二个字段
	    			row.add(rs.getDouble(3)); //获取第三个字段	
	    			rows.add(row);//将行数据添加到记录集合中
	    		}
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	return rows;//返回所有行数据
		}

}
