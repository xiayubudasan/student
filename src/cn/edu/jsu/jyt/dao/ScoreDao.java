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
    			rows.add(row);//����������ӵ���¼������
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return rows;//��������������
    }
	
	public static int deleteScore(String sql)
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
	
	public static int updateScore(Score c)
	{
		String sql="update  score set cj=? where cno=? and sno=? ";
		DataBaseConnection02 dbcs=new DataBaseConnection02();//ʹ��1�ж�����������ݿ����
    	try(Connection conn=dbcs.getConnection();//��ȡ���ݿ��
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//ʵ����PreparedStatement
    		//PreparedStatement������Ч��ֹsqlע��
    	 String ss="select  * from score where sno="+c.getSno()+" and cno="+c.getCno();
    	Vector v=getSelectAll(ss);
    	 if(v.isEmpty())
    		  return -1;//���cno,sno��������Խ����޸�
    	 else{
    		 pstmt.setDouble(1, c.getCj());
    	     pstmt.setString(2, c.getCno());
    	     pstmt.setString(3, c.getSno());
    	 return pstmt.executeUpdate();//���ظ��µ�����
    	 } 
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return 0;
	}
	public static int insertScore(Score c)
	{
		String sql="insert into score(sno,cno,cj) values(?,?,?)";
		DataBaseConnection02 dbcs=new DataBaseConnection02();//ʹ��1�ж�����������ݿ����
    	try(Connection conn=dbcs.getConnection();//��ȡ���ݿ��
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//ʵ����PreparedStatement
    		//PreparedStatement������Ч��ֹsqlע��
    		String ss="select  * from score where cno="+c.getCno()+" and sno="+c.getSno();
        	Vector v=getSelectAll(ss);
        	 if(v.isEmpty())
        	 {
        		 //���cno,sno����������Խ��в���
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
