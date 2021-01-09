package cn.edu.jsu.jyt.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.cj.xdevapi.PreparableStatement;

import cn.edu.jsu.jyt.dbc.DataBaseConnection02;

public class TeacherDataOperator {
	        /**
	         * ��������
	         */
			private static String firstName="��Ǯ��������֣��������������������������ʩ�ſײ��ϻ���κ�ս���л������ˮ��������˸��ɷ�����³Τ������ﻨ������Ԭ��ۺ��ʷ�Ʒ����Ѧ�׺����������ޱϺ�����������ʱ��Ƥ���뿵����Ԫ������ƽ�ƺ�������Ҧ��տ����ë����ױ���갼Ʒ��ɴ�̸��é���ܼ�������ף������������ϯ����ǿ��·¦Σ��ͯ�չ�÷ʢ�ֵ�����������Ĳ��﷮���������֧�¾̹�¬Ī�������Ѹɽ�Ӧ�������ڵ��������������ʯ�޼�ť�������ϻ���½��������춻���κ�ӷ����ഢ���������ɾ��θ����ڽ��͹�����ɽ�ȳ������ȫۭ�����������������ﱩ�����������������ղ����Ҷ��˾��۬�輻��ӡ�ް׻���̨�Ӷ����̼���׿�����ɳ����������ܲ�˫��ݷ����̷�����̼������Ƚ��۪Ӻȴ�ɣ���ţ��ͨ�����༽ۣ����ũ�±�ׯ�̲����ֳ�Ľ����ϰ�°���������������θ����߾Ӻⲽ�����������Ŀܹ�»�ڶ�Ź�����εԽ��¡ʦ�������˹��������������Ǽ��Ŀ�����ɳؿ������ᳲ�������󽭺�����Ȩ�ָ��滸����ٹ˾���Ϲ�ŷ���ĺ�������˶��������ʸ�ξ�ٹ����̨��ұ���������������̫����������������ԯ�������������Ľ����������˾ͽ˾������˾���붽�ӳ�����ľ����������������ṫ���ذμй��׸����������ַ���۳Ϳ�նθɰ��ﶫ�����ź��ӹ麣����΢����˧�ÿ�������������������������Ĳ��٦�����Ϲ�ī�������갮��١�����Ը��ټ�����";
			/**
			 * ����Ů������
			 */
			private static String girl="���Ӣ���������Ⱦ���������֥��Ƽ�����ҷ���ʴ��������÷���������滷ѩ�ٰ���ϼ����ݺ�����𷲼Ѽ�������������Ҷ�������������ɺɯ������ٻ�������ӱ¶������������Ǻɵ���ü������ޱݼ���Է�ܰ�������԰��ӽ�������ع���ѱ�ˬ������ϣ����Ʈ�����������������������ܿ�ƺ������˿ɼ���Ӱ��֦˼�� ";
			/**
			 * ������������
			 */
		    private static String boy="ΰ�����㿡��ǿ��ƽ�����Ļ�������������־��������ɽ�ʲ���������Ԫȫ��ʤѧ��ŷ���������ɱ�˳���ӽ��β��ɿ��ǹ���ﰲ����ï�����м�ͱ벩���Ⱦ�����׳��˼Ⱥ���İ�����ܹ����ƺ���������ԣ���ܽ���������ǫ�����֮�ֺ��ʲ����������������ά�������������󳿳�ʿ�Խ��������׵���ʱ̩ʢ��衾��ڲ�����ŷ纽��";
            /**
             * ����ְ��
             */
		    private static String tpt[]= {"������","����","��ʦ","����"};
		    /**
		     * ���ɳ������
		     * @param start
		     * @param end
		     * @return
		     */
		    public static int getNum(int start,int end)
		    {
		    	return (int)(Math.random()*(end-start+1)+start);
		    }
		    /**
		     * �������ְ��
		     */
		    public static String getTpt()
		    {
		    	int n=getNum(0, tpt.length-1);
		        return tpt[n];
		    }
		    /**
		     * ������������������Ա�
		     */
		    public static String getChineseName() {
		        int index=getNum(0, firstName.length()-1);//���ȡ�����ַ����е�����λ��
		        String first=firstName.substring(index, index+1);//��ȡ��λ�õ�����
		        int sex=getNum(0,1);//���ȡ�Ա�����1Ϊ������0ΪŮ��
		        String sexx="��";
		        String str=boy;//��������Ϊ�����ַ���
		        int length=boy.length();//��ȡ�����ַ����ĳ���
		        if(sex==0){//��������ȡΪ0�������ָ�ΪŮ��
		            str=girl;
		            sexx="Ů";//�Ա��޸ĳ�Ů��
		            length=girl.length();
		        }
		        index=getNum(0,length-1);//�����ȡ���ֵ�λ��
		        String second=str.substring(index, index+1);//��ȡ��λ���е�����
		        int hasThird=getNum(0,1);//�����ȡ�����Ƿ��е�������
		        String third="";//Ĭ��û�е�������
		        if(hasThird==1){//��������ȡΪ1�����е�������
		            index=getNum(0,length-1);
		            third=str.substring(index, index+1);
		        }
		        return first+second+third+" "+sexx;//��������  �Ա�
		    }
		    /**
		     * ������ع���
		     * @param args
		     */
		    public static StringBuilder getTno()
		    {
		    	StringBuilder gh=new StringBuilder(String.valueOf(getNum(1, 10000)));
		    	if(gh.length()==1)
		    	{
		    		gh=gh.insert(0, "0000");
		    	}
		    	else if(gh.length()==2)
		    	{
		    		gh=gh.insert(0, "000");
		    	}
		    	else if(gh.length()==3)
		    	{
		    		gh=gh.insert(0, "00");
		    	}
		    	else if(gh.length()==4)
		    	{
		    		gh=gh.insert(0,"0");
		    		
		    	}
		    	return gh;
		    }
		    public static void main(String[]args)
		    {
		    	System.out.println("AAA");
		    	addTeacher();
		    }
		    public static void addTeacher()
		    {
		    	DataBaseConnection02 dbcs=new DataBaseConnection02();
		    	String sql="insert into teacher(tno,tname,tsex,tage,tpt,password) values(?,?,?,?,?,?)";
		        try(Connection conn=dbcs.getConnection();
		        		PreparedStatement pstmt=conn.prepareStatement(sql);)
		        {
		        	
		        	for(int i=1;i<=10000;i++)
		        	{
		        		int age=getNum(27, 45);
		        		String gh=getTno().toString();
	                     String s[]=getChineseName().split(" ");
		        		    pstmt.setString(1, gh);
		        		    pstmt.setString(2,s[0]);
		        		    pstmt.setString(3, s[1]);
		        		    pstmt.setInt(4, age);
		        		    pstmt.setString(5, getTpt());
		        		    pstmt.setString(6, "123456");
		        		    pstmt.addBatch();
		        		 
		        		}
		        		pstmt.executeBatch();
		        		JOptionPane.showMessageDialog(null, "");
		        	
		        }
		        catch (Exception e) {
					// TODO: handle exception
				}
		    }
		    
}
