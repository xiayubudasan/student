package cn.edu.jsu.jyt.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import cn.edu.jsu.jyt.dbc.DataBaseConnection02;

public class ScoreDataOperator {
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
    
    /**
     * 随机返回学号
     * @return StringBuider 学号
     */
    public static StringBuilder getStuno() {//不使用String，因为需要大量拼接字符串
    	StringBuilder xh=new StringBuilder("2019401");//学号前7位相同
    	StringBuilder xh1=new StringBuilder(String.valueOf(getNum(9000,10000)));//随机取后四位
    	if(xh1.length()==1) {
    		xh1=xh1.insert(0, "0000");//如果是1位数，前面增加4个0
    		xh=xh.append(xh1);//前6位与后3位拼接成学号
    	}else if(xh1.length()==2) {
    		xh1=xh1.insert(0, "000");//如果是2位数，前面增加3个0
    		xh=xh.append(xh1);
    	}else if(xh1.length()==3){
    		xh1=xh1.insert(0, "00");//如果是3位数
    		xh=xh.append(xh1);
    	}else if(xh1.length()==4){
    		xh1=xh1.insert(0, "0");//如果是3位数
    		xh=xh.append(xh1);
    	}
    	else
    	{
    		xh=xh.append(xh1);
    	}
    	return xh;
    }
    //连接数据库
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
