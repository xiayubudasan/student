package cn.edu.jsu.jyt.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import cn.edu.jsu.jyt.dbc.DataBaseConnection02;

public class ClassDataOperator {
	    //�γ���
	    private static String cname[] = {"���������","��ѧӢ��1A","��ѧӢ��1B","�й����ִ�ʷ��Ҫ","�ߵ���ѧ(��)","����1","��ѧӢ��2A","��ѧӢ��2B"," ˼����������뷨�ɻ���","��ѧ����A1","�ߵ���ѧ(��)2","����2","������ƻ���(C)","��ѧӢ��3A","��ѧӢ��3B","ë��˼����й���ɫ�������������ϵ����(��)","��ѧ����ʵ��","��ѧ����A2","����3","��ѧӢ��4A","��ѧӢ��4B","ë��˼����й���ɫ�������������ϵ����(��)","(��)����4","���������","��ҵ����","��ѧӢ��5","���˼�������ԭ�����","����������"," ѧ��רҵǰ��֪ʶ��ְҵ����"};//�γ���

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

	    //����ccredit
	    public static double getccredit(){
	        double ccredict = getNum(1,4);
	        return ccredict;
	    }

	    //����tno
	    public static StringBuilder getTno(){
	        StringBuilder tno = new StringBuilder(String.valueOf(getNum(1,10000)));
	        if(tno.length()==1) {
	            tno = tno.insert(0, "0000");//�����1λ����ǰ������4��0
	        }else if(tno.length()==2){
	            tno = tno.insert(0,"000");
	        }else if(tno.length()==3){
	            tno = tno.insert(0,"00");
	        }else if(tno.length()==4){
	            tno = tno.insert(0,"0");
	        }
	        return tno;
	    }

	    //�������ݿ�
	    public static void Connection(){
	    	DataBaseConnection02 dbcs=new DataBaseConnection02();
	    	String sql="insert into class(cno,cname,ccredit,tno) values(?,?,?,?)";
	        try(Connection conn=dbcs.getConnection();
	        		PreparedStatement pstmt=conn.prepareStatement(sql);)
	        {
	        	
	        	for(int i=0;i<10000;i++)
	        	{
	        		   pstmt.setString(1, getCno().toString());
	        		    pstmt.setString(2,cname[i]);
	        		    pstmt.setDouble(3,getccredit());
	        		    pstmt.setString(4, getTno().toString());
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

