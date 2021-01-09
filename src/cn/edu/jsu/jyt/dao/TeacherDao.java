package cn.edu.jsu.jyt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import cn.edu.jsu.jyt.dbc.DataBaseConnection02;
import cn.edu.jsu.jyt.vo.CClass;
import cn.edu.jsu.jyt.vo.Score;
import cn.edu.jsu.jyt.vo.Teacher;

public class TeacherDao {
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
    			row.add(rs.getString(3)); //获取第三个字段		   			
    			row.add(rs.getInt(4));//获取第四个字段
    			row.add(rs.getString(5));//获取第五个字段	   			
    			rows.add(row);//将行数据添加到记录集合中
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return rows;//返回所有行数据
    }
	
	public static Vector getInData(String sql)
	{
		Vector row=new Vector();
		DataBaseConnection02 dbcs=new DataBaseConnection02();//使用1中定义的连接数据库的类
    	try(Connection conn=dbcs.getConnection();//获取数据库接
    		PreparedStatement pstmt=conn.prepareStatement(sql);)
    	//实例化PreparedStatement
    		//PreparedStatement可以有效防止sql注入
    		{
    		ResultSet rs=pstmt.executeQuery();//执行查询语句，结果放到数据集中
    		while(rs.next()) 
    		{//遍历数据集
    				row=new Vector();//定义行数据    				
        			row.add(rs.getString(1));//获取第一个字段
        			row.add(rs.getString(2));//获取第二个字段
        			row.add(rs.getString(3)); //获取第三个字段		   			
        			row.add(rs.getInt(4));//获取第四个字段
        			row.add(rs.getString(5));//获取第五个字段
        			row.add(rs.getString(6));//获取第六个字段
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return row;//返回当前行数据
    }
	public static int deleteTeacher(String sql)
	{
		DataBaseConnection02 dbcs=new DataBaseConnection02();//使用1中定义的连接数据库的类
    	try(Connection conn=dbcs.getConnection();//获取数据库接
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//实例化PreparedStatement
    		//PreparedStatement可以有效防止sql注入
    	    return  pstmt.executeUpdate();
  
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return 0;
	}
	public static int insertTeacher(Teacher t)
	{
		String sql="insert into teacher(tno,tname,tsex,tage,tpt,password) values(?,?,?,?,?,? )";
		DataBaseConnection02 dbcs=new DataBaseConnection02();//使用1中定义的连接数据库的类
    	try(Connection conn=dbcs.getConnection();//获取数据库接
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//实例化PreparedStatement
    		//PreparedStatement可以有效防止sql注入
    		String ss="select  * from teacher where tno="+t.getTno();
        	Vector v=getSelectAll(ss);
          if(v.isEmpty())
          { 
        	 pstmt.setString(1, t.getTno());
    	  pstmt.setString(2, t.getTname());
    	  pstmt.setString(3, t.getTsex());
    	  pstmt.setInt(4, t.getTage());
    	  pstmt.setString(5, t.getTpt());
    	  pstmt.setString(6, t.getPassword());
    	  return pstmt.executeUpdate();
          }
          else
          {
        	  return -1;
          }
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return 0;
	}
	public static int updateTeacher(Teacher t)
	{
		String sql="update Teacher set tname=?,tsex=?,tage=?,tpt=? where tno=? ";
		DataBaseConnection02 dbcs=new DataBaseConnection02();//使用1中定义的连接数据库的类
    	try(Connection conn=dbcs.getConnection();//获取数据库接
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//实例化PreparedStatement
    		//PreparedStatement可以有效防止sql注入
    	 String ss="select  * from teacher where tno="+t.getTno();
    	 Vector v=getSelectAll(ss);
    	 if(!v.isEmpty())
    		  return -1;//如果tno存在则可以进行修改
    	 else{
    		
       	  pstmt.setString(1, t.getTname());
       	  pstmt.setString(2, t.getTsex());
       	  pstmt.setInt(3, t.getTage());
       	  pstmt.setString(4, t.getTpt());
       	 pstmt.setString(5, t.getTno());
    	 return pstmt.executeUpdate();//返回更新的行数
    	 }
    	  
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return 0;
	}
}
