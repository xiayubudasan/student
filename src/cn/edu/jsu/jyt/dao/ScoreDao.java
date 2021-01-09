package cn.edu.jsu.jyt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import cn.edu.jsu.jyt.dbc.DataBaseConnection02;
import cn.edu.jsu.jyt.vo.CClass;
import cn.edu.jsu.jyt.vo.Score;

public class ScoreDao {
	/**
	 * 
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
    			row.add(rs.getString(3)); //获取第三个字段		   			
    			rows.add(row);//将行数据添加到记录集合中
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return rows;//返回所有行数据
    }
	
	public static int deleteScore(String sql)
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
	
	public static int updateScore(Score c)
	{
		String sql="update  score set cj=? where cno=? and sno=? ";
		DataBaseConnection02 dbcs=new DataBaseConnection02();//使用1中定义的连接数据库的类
    	try(Connection conn=dbcs.getConnection();//获取数据库接
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//实例化PreparedStatement
    		//PreparedStatement可以有效防止sql注入
    	 String ss="select  * from score where sno="+c.getSno()+" and cno="+c.getCno();
    	Vector v=getSelectAll(ss);
    	 if(v.isEmpty())
    		  return -1;//如果cno,sno存在则可以进行修改
    	 else{
    		 pstmt.setDouble(1, c.getCj());
    	     pstmt.setString(2, c.getCno());
    	     pstmt.setString(3, c.getSno());
    	 return pstmt.executeUpdate();//返回更新的行数
    	 } 
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return 0;
	}
	public static int insertScore(Score c)
	{
		String sql="insert into score(sno,cno,cj) values(?,?,?)";
		DataBaseConnection02 dbcs=new DataBaseConnection02();//使用1中定义的连接数据库的类
    	try(Connection conn=dbcs.getConnection();//获取数据库接
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//实例化PreparedStatement
    		//PreparedStatement可以有效防止sql注入
    		String ss="select  * from score where cno="+c.getCno()+" and sno="+c.getSno();
        	Vector v=getSelectAll(ss);
        	 if(v.isEmpty())
        	 {
        		 //如果cno,sno不存在则可以进行插入
        	  pstmt.setString(1, c.getSno());
    	      pstmt.setString(2, c.getCno());
    	      pstmt.setDouble(3, c.getCj());
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

}
