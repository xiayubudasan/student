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
    	Vector<Vector> rows=new Vector<Vector>();//����Ҫ���ص����м�¼����
    	DataBaseConnection02 dbcs=new DataBaseConnection02();//ʹ��1�ж�����������ݿ����
    	try(Connection conn=dbcs.getConnection();//��ȡ���ݿ��
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//ʵ����PreparedStatement
    		//PreparedStatement������Ч��ֹsqlע��
    		ResultSet rs=pstmt.executeQuery();//ִ�в�ѯ��䣬����ŵ����ݼ���
    		while(rs.next()) {//�������ݼ�
    			Vector row=new Vector();//����������				
    			row.add(rs.getString(1));//��ȡ��һ���ֶ�
    			row.add(rs.getString(2));//��ȡ�ڶ����ֶ�
    			row.add(rs.getString(3)); //��ȡ�������ֶ�		   			
    			row.add(rs.getInt(4));//��ȡ���ĸ��ֶ�
    			row.add(rs.getString(5));//��ȡ������ֶ�	   			
    			rows.add(row);//����������ӵ���¼������
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return rows;//��������������
    }
	
	public static Vector getInData(String sql)
	{
		Vector row=new Vector();
		DataBaseConnection02 dbcs=new DataBaseConnection02();//ʹ��1�ж�����������ݿ����
    	try(Connection conn=dbcs.getConnection();//��ȡ���ݿ��
    		PreparedStatement pstmt=conn.prepareStatement(sql);)
    	//ʵ����PreparedStatement
    		//PreparedStatement������Ч��ֹsqlע��
    		{
    		ResultSet rs=pstmt.executeQuery();//ִ�в�ѯ��䣬����ŵ����ݼ���
    		while(rs.next()) 
    		{//�������ݼ�
    				row=new Vector();//����������    				
        			row.add(rs.getString(1));//��ȡ��һ���ֶ�
        			row.add(rs.getString(2));//��ȡ�ڶ����ֶ�
        			row.add(rs.getString(3)); //��ȡ�������ֶ�		   			
        			row.add(rs.getInt(4));//��ȡ���ĸ��ֶ�
        			row.add(rs.getString(5));//��ȡ������ֶ�
        			row.add(rs.getString(6));//��ȡ�������ֶ�
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return row;//���ص�ǰ������
    }
	public static int deleteTeacher(String sql)
	{
		DataBaseConnection02 dbcs=new DataBaseConnection02();//ʹ��1�ж�����������ݿ����
    	try(Connection conn=dbcs.getConnection();//��ȡ���ݿ��
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//ʵ����PreparedStatement
    		//PreparedStatement������Ч��ֹsqlע��
    	    return  pstmt.executeUpdate();
  
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return 0;
	}
	public static int insertTeacher(Teacher t)
	{
		String sql="insert into teacher(tno,tname,tsex,tage,tpt,password) values(?,?,?,?,?,? )";
		DataBaseConnection02 dbcs=new DataBaseConnection02();//ʹ��1�ж�����������ݿ����
    	try(Connection conn=dbcs.getConnection();//��ȡ���ݿ��
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//ʵ����PreparedStatement
    		//PreparedStatement������Ч��ֹsqlע��
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
		DataBaseConnection02 dbcs=new DataBaseConnection02();//ʹ��1�ж�����������ݿ����
    	try(Connection conn=dbcs.getConnection();//��ȡ���ݿ��
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//ʵ����PreparedStatement
    		//PreparedStatement������Ч��ֹsqlע��
    	 String ss="select  * from teacher where tno="+t.getTno();
    	 Vector v=getSelectAll(ss);
    	 if(!v.isEmpty())
    		  return -1;//���tno��������Խ����޸�
    	 else{
    		
       	  pstmt.setString(1, t.getTname());
       	  pstmt.setString(2, t.getTsex());
       	  pstmt.setInt(3, t.getTage());
       	  pstmt.setString(4, t.getTpt());
       	 pstmt.setString(5, t.getTno());
    	 return pstmt.executeUpdate();//���ظ��µ�����
    	 }
    	  
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return 0;
	}
}
