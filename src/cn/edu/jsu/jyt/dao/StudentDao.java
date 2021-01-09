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
		DataBaseConnection02 dbcs=new DataBaseConnection02();//ʹ��1�ж�����������ݿ����
    	try(Connection conn=dbcs.getConnection();//��ȡ���ݿ��
	    		PreparedStatement pstmt=conn.prepareStatement(sql);)
    	{//ʵ����PreparedStatement
	    		//PreparedStatement������Ч��ֹsqlע��
    		String ss="select * from student where sno="+s.getSno();
            Vector v=getSelectAll(ss);
            if(v.isEmpty())//ѧ�Ų�����
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
        			row.add(rs.getString(7));//��ȡ���߸��ֶ�
        			row.add(rs.getString(8));//��ȡ�ڰ˸��ֶ�
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return row;//���ص�ǰ������
    }


	public static int insertStudent(Student s)
	{
		String sql="insert into student(sno,sname,ssex,sage,birthday,password,sdept ,major) values(?,?,?,?,?,?,?,? )";
		DataBaseConnection02 dbcs=new DataBaseConnection02();//ʹ��1�ж�����������ݿ����
    	try(Connection conn=dbcs.getConnection();//��ȡ���ݿ��
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//ʵ����PreparedStatement
    		//PreparedStatement������Ч��ֹsqlע��
    		String ss="select * from student where sno="+s.getSno();
            Vector v=getSelectAll(ss);
            if(v.isEmpty())//ѧ�Ų����ڣ�����Բ���
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
        			//row.add(rs.getString(6));//��ȡ�������ֶ�
        			row.add(rs.getString(7));//��ȡ���߸��ֶ�
        			row.add(rs.getString(8));//��ȡ���߸��ֶ�
	    			rows.add(row);//����������ӵ���¼������
	    		}
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	return rows;//��������������
	    }
		public static int deleteStudent(String sql)
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
}
