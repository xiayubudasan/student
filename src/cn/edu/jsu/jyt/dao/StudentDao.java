package cn.edu.jsu.jyt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import cn.edu.jsu.jyt.dbc.DataBaseConnection02;
import cn.edu.jsu.jyt.vo.Student;
import cn.edu.jsu.jyt.vo.Teacher;

public class StudentDao {
	
	public static int updateStudent(Student s)
	{
		String sql="update student set sname=?,ssex=?,sage=?,birthday=?,sdept=? ,major=? where sno=?";
		DataBaseConnection02 dbcs=new DataBaseConnection02();//使用1中定义的连接数据库的类
    	try(Connection conn=dbcs.getConnection();//获取数据库接
	    		PreparedStatement pstmt=conn.prepareStatement(sql);)
    	{//实例化PreparedStatement
	    		//PreparedStatement可以有效防止sql注入
    		String ss="select * from student where sno="+s.getSno();
            Vector v=getSelectAll(ss);
            if(v.isEmpty())//学号不存在
            	return -1;
            else
            {
            	pstmt.setString(1, s.getSname());
            	pstmt.setString(2, s.getSsex());
            	pstmt.setInt(3, s.getSage());
            	pstmt.setString(4, s.getBirthday());
            	pstmt.setString(5, s.getSdept());
            	pstmt.setString(6, s.getMajor());
            	pstmt.setString(7, s.getSno());
            	return pstmt.executeUpdate();
            }
	
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
    	return 0;
	}
	public static Vector getInData(String sql)
	{
		Vector row=new Vector<>();
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
        			row.add(rs.getString(7));//获取第七个字段
        			row.add(rs.getString(8));//获取第八个字段
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return row;//返回当前行数据
    }


	public static int insertStudent(Student s)
	{
		String sql="insert into student(sno,sname,ssex,sage,birthday,password,sdept ,major) values(?,?,?,?,?,?,?,? )";
		DataBaseConnection02 dbcs=new DataBaseConnection02();//使用1中定义的连接数据库的类
    	try(Connection conn=dbcs.getConnection();//获取数据库接
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//实例化PreparedStatement
    		//PreparedStatement可以有效防止sql注入
    		String ss="select * from student where sno="+s.getSno();
            Vector v=getSelectAll(ss);
            if(v.isEmpty())//学号不存在，则可以插入
            { 
            pstmt.setString(1, s.getSno());
    	    pstmt.setString(2, s.getSname());
    	    pstmt.setString(3, s.getSsex());
    	    pstmt.setInt(4, s.getSage());
    	    pstmt.setString(5, s.getBirthday());
    	    pstmt.setString(6, s.getPassword());
    	    pstmt.setString(7, s.getSdept());
    	    pstmt.setString(8, s.getMajor());
    	   return pstmt.executeUpdate();
            }
            else
            	return -1;
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return 0;
	}

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
        			//row.add(rs.getString(6));//获取第六个字段
        			row.add(rs.getString(7));//获取第七个字段
        			row.add(rs.getString(8));//获取第七个字段
	    			rows.add(row);//将行数据添加到记录集合中
	    		}
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	return rows;//返回所有行数据
	    }
		public static int deleteStudent(String sql)
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
}
