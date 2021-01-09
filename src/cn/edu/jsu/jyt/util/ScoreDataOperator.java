package cn.edu.jsu.jyt.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import cn.edu.jsu.jyt.dbc.DataBaseConnection02;

public class ScoreDataOperator {
	   //���������
    public static int getNum(int start,int end){
        return (int)(Math.random()*(end-start+1)+start);
    }

 
    //����cno
    public static StringBuilder getCno(){
        StringBuilder cno = new StringBuilder(String.valueOf(getNum(1,100)));
        if(cno.length()==1){
            cno = cno.insert(0,"00");
        }else if(cno.length()==2){
            cno = cno.insert(0,"0");
        }
        return cno;
    }
    
    /**
     * �������ѧ��
     * @return StringBuider ѧ��
     */
    public static StringBuilder getStuno() {//��ʹ��String����Ϊ��Ҫ����ƴ���ַ���
    	StringBuilder xh=new StringBuilder("2019401");//ѧ��ǰ7λ��ͬ
    	StringBuilder xh1=new StringBuilder(String.valueOf(getNum(9000,10000)));//���ȡ����λ
    	if(xh1.length()==1) {
    		xh1=xh1.insert(0, "0000");//�����1λ����ǰ������4��0
    		xh=xh.append(xh1);//ǰ6λ���3λƴ�ӳ�ѧ��
    	}else if(xh1.length()==2) {
    		xh1=xh1.insert(0, "000");//�����2λ����ǰ������3��0
    		xh=xh.append(xh1);
    	}else if(xh1.length()==3){
    		xh1=xh1.insert(0, "00");//�����3λ��
    		xh=xh.append(xh1);
    	}else if(xh1.length()==4){
    		xh1=xh1.insert(0, "0");//�����3λ��
    		xh=xh.append(xh1);
    	}
    	else
    	{
    		xh=xh.append(xh1);
    	}
    	return xh;
    }
    //�������ݿ�
    public static void Connection(){
    	DataBaseConnection02 dbcs=new DataBaseConnection02();
    	String sql="replace into score(sno,cno,cj) values(?,?,?)";
        try(Connection conn=dbcs.getConnection();
        		PreparedStatement pstmt=conn.prepareStatement(sql);)
        {
        	
        	for(int i=1;i<100;i++)
        	{
        		   pstmt.setString(1, getStuno().toString());
        		    pstmt.setString(2,getCno().toString());
      		       pstmt.setDouble(3,getNum(30, 100));
        		    pstmt.addBatch();
        		}
        		pstmt.executeBatch();
        		JOptionPane.showMessageDialog(null, ""
        				+ "�ɹ�");
        	
        }
        catch (Exception e) {
			// TODO: handle exception
		}
    }
    public static void main(String[] args)throws Exception{
        Connection();
    }

}
