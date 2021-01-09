package cn.edu.jsu.jyt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import cn.edu.jsu.jyt.dbc.DataBaseConnection02;
import cn.edu.jsu.jyt.vo.CClass;
/**
 * ��class��Ĳ���
 * @author J jyt
 *
 */
public class ClassDao {
	/**
	 * class�����
	 * @param c CClass
	 * @param s String
	 */
	public static void updateClass(CClass c,String s)
	{
		String sql="update  class set cname=?,ccredit=?,tno=? where cno=? ";
		DataBaseConnection02 dbcs=new DataBaseConnection02();//ʹ��1�ж�����������ݿ����
    	try(Connection conn=dbcs.getConnection();//��ȡ���ݿ��
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//ʵ����PreparedStatement
    		//PreparedStatement������Ч��ֹsqlע��
    	 
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
 * class�����
 * @param c
 */
	public static void insertClass(CClass c)
	{
		String sql="replace into class(cno,cname,ccredit,tno) values(?,?,?,?)";
		DataBaseConnection02 dbcs=new DataBaseConnection02();//ʹ��1�ж�����������ݿ����
    	try(Connection conn=dbcs.getConnection();//��ȡ���ݿ��
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//ʵ����PreparedStatement
    		//PreparedStatement������Ч��ֹsqlע��
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
	 * class��ɾ��
	 * @param sql
	 * @return
	 */
	public static int deleteClass(String sql)
	{
		DataBaseConnection02 dbcs=new DataBaseConnection02();//ʹ��1�ж�����������ݿ����
    	try(Connection conn=dbcs.getConnection();//��ȡ���ݿ��
    		PreparedStatement pstmt=conn.prepareStatement(sql.toString());){//ʵ����PreparedStatement
    		//PreparedStatement������Ч��ֹsqlע��
    	    return  pstmt.executeUpdate();
  
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return 0;
	}
	/**
	 * class����ȫ����Ϣ
	 * @param sql
	 * @return
	 */
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
	    			row.add(rs.getDouble(3)); //��ȡ�������ֶ�	
	    			row.add(rs.getString(4)); //��ȡ�������ֶ�	
	    			rows.add(row);//����������ӵ���¼������
	    		}
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	return rows;//��������������
		}
		/**
		 * class����ȫ��cno,cname,ccredit
		 * @param sql
		 * @return
		 */
		public static Vector<Vector> getSelectClass(String sql){
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
	    			row.add(rs.getDouble(3)); //��ȡ�������ֶ�	
	    			rows.add(row);//����������ӵ���¼������
	    		}
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	return rows;//��������������
		}

}
