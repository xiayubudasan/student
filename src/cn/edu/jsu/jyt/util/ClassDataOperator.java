package cn.edu.jsu.jyt.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import cn.edu.jsu.jyt.dbc.DataBaseConnection02;

public class ClassDataOperator {
	    //课程名
	    private static String cname[] = {"计算机基础","大学英语1A","大学英语1B","中国近现代史纲要","高等数学(上)","体育1","大学英语2A","大学英语2B"," 思想道德徳修养与法律基础","大学物理A1","高等数学(上)2","体育2","程序设计基础(C)","大学英语3A","大学英语3B","毛泽东思想和中国特色社会主义理论体系概论(上)","大学物理实验","大学物理A2","体育3","大学英语4A","大学英语4B","毛泽东思想和中国特色社会主义理论体系概论(下)","(下)体育4","计算机网络","企业管理","大学英语5","马克思主义基本原理概论","形势与政策"," 学科专业前沿知识及职业导航"};//课程名

	    //随机生成数
	    public static int getNum(int start,int end){
	        return (int)(Math.random()*(end-start+1)+start);
	    }

	    //生成cno
	    public static StringBuilder getCno(){
	        StringBuilder cno = new StringBuilder(String.valueOf(getNum(1,100)));
	        if(cno.length()==1){
	            cno = cno.insert(0,"00");
	        }else if(cno.length()==2){
	            cno = cno.insert(0,"0");
	        }
	        return cno;
	    }

	    //生成ccredit
	    public static double getccredit(){
	        double ccredict = getNum(1,4);
	        return ccredict;
	    }

	    //生成tno
	    public static StringBuilder getTno(){
	        StringBuilder tno = new StringBuilder(String.valueOf(getNum(1,10000)));
	        if(tno.length()==1) {
	            tno = tno.insert(0, "0000");//如果是1位数，前面增加4个0
	        }else if(tno.length()==2){
	            tno = tno.insert(0,"000");
	        }else if(tno.length()==3){
	            tno = tno.insert(0,"00");
	        }else if(tno.length()==4){
	            tno = tno.insert(0,"0");
	        }
	        return tno;
	    }

	    //连接数据库
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
	        				+ "成功");
	        	
	        }
	        catch (Exception e) {
				// TODO: handle exception
			}
	    }

	    public static void main(String[] args)throws Exception{
	        Connection();
	    }


}

