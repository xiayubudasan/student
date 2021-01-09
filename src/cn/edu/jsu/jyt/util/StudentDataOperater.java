package cn.edu.jsu.jyt.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import cn.edu.jsu.jyt.dbc.DataBaseConnection;
import cn.edu.jsu.jyt.dbc.DataBaseConnection02;

public class StudentDataOperater {
	    //��������
		private static String firstName="��Ǯ��������֣��������������������������ʩ�ſײ��ϻ���κ�ս���л������ˮ��������˸��ɷ�����³Τ������ﻨ������Ԭ��ۺ��ʷ�Ʒ����Ѧ�׺����������ޱϺ�����������ʱ��Ƥ���뿵����Ԫ������ƽ�ƺ�������Ҧ��տ����ë����ױ���갼Ʒ��ɴ�̸��é���ܼ�������ף������������ϯ����ǿ��·¦Σ��ͯ�չ�÷ʢ�ֵ�����������Ĳ��﷮���������֧�¾̹�¬Ī�������Ѹɽ�Ӧ�������ڵ��������������ʯ�޼�ť�������ϻ���½��������춻���κ�ӷ����ഢ���������ɾ��θ����ڽ��͹�����ɽ�ȳ������ȫۭ�����������������ﱩ�����������������ղ����Ҷ��˾��۬�輻��ӡ�ް׻���̨�Ӷ����̼���׿�����ɳ����������ܲ�˫��ݷ����̷�����̼������Ƚ��۪Ӻȴ�ɣ���ţ��ͨ�����༽ۣ����ũ�±�ׯ�̲����ֳ�Ľ����ϰ�°���������������θ����߾Ӻⲽ�����������Ŀܹ�»�ڶ�Ź�����εԽ��¡ʦ�������˹��������������Ǽ��Ŀ�����ɳؿ������ᳲ�������󽭺�����Ȩ�ָ��滸����ٹ˾���Ϲ�ŷ���ĺ�������˶��������ʸ�ξ�ٹ����̨��ұ���������������̫����������������ԯ�������������Ľ����������˾ͽ˾������˾���붽�ӳ�����ľ����������������ṫ���ذμй��׸����������ַ���۳Ϳ�նθɰ��ﶫ�����ź��ӹ麣����΢����˧�ÿ�������������������������Ĳ��٦�����Ϲ�ī�������갮��١�����Ը��ټ�����";
		//����Ů������
		private static String girl="���Ӣ���������Ⱦ���������֥��Ƽ�����ҷ���ʴ��������÷���������滷ѩ�ٰ���ϼ����ݺ�����𷲼Ѽ�������������Ҷ�������������ɺɯ������ٻ�������ӱ¶������������Ǻɵ���ü������ޱݼ���Է�ܰ�������԰��ӽ�������ع���ѱ�ˬ������ϣ����Ʈ�����������������������ܿ�ƺ������˿ɼ���Ӱ��֦˼�� ";
		//������������
	    private static String boy="ΰ�����㿡��ǿ��ƽ�����Ļ�������������־��������ɽ�ʲ���������Ԫȫ��ʤѧ��ŷ���������ɱ�˳���ӽ��β��ɿ��ǹ���ﰲ����ï�����м�ͱ벩���Ⱦ�����׳��˼Ⱥ���İ�����ܹ����ƺ���������ԣ���ܽ���������ǫ�����֮�ֺ��ʲ����������������ά�������������󳿳�ʿ�Խ��������׵���ʱ̩ʢ��衾��ڲ�����ŷ纽��";
	   //����ϵ��רҵ
	    private static String dept[][]= {
	    		{ "��֯ϵ","��֯����","��װ���"},
	    		{"���պ���ϵ" ,"���պ��칤��","����������빤��" ,"�����������","��������������"," �������������������Ϲ���"},
	    		{"�˹�����","�˹�����˼���","���������˰�ȫ","��������","�˻������ȼ�Ϲ���"},
	    		{"������ѧ�빤����","������ѧ�빤��","��������","������ѧ","������̬���� "},
	    		{"��ѧϵ","��ѧ","�߼�ѧ","�ڽ�ѧ "},
	    		{"����ϵ","����ѧ","����ͳ��ѧ"},
	    		{"����ϵ","����ѧ","˰��ѧ"},
	    		{"����ϵ","����ѧ","���ڹ���"},
	    		{"��ľ��","��ľ����","������������ԴӦ�ù���","����ˮ��ѧ�빤��","�������������ܻ� "},
	    		{"�����","�������ѧ�뼼��","�������","���繤��","��Ϣ��ȫ","����������","����ý�弼��"},
	    		{"��е��","��е����","��е������켰���Զ���","���ϳ��ͼ����ƹ���","��е���ӹ���","��ҵ���","����װ������ƹ���","��������",
	"�������񹤳�"}
	    };
	    public static int getNum(int start,int end) {//������ط���ָ����Χ�������
	    	//Math.random()�������0.0��1.0֮�����
	        return (int)(Math.random()*(end-start+1)+start);
	    }
	    /**
	     * �����������
	     */
	    public static int getAge()
	    {
	    	return getNum(15, 25);
	    }
	    /**
	     * �����������
	     */
	    public static StringBuilder getBirthday(int num)
	    {
	    	Integer n=2021-num;
	    	Integer y=getNum(1, 12);
	    	Integer r=getNum(1, 28);
	    	StringBuilder sr=new StringBuilder(n.toString()+"��");
	    	sr.append(y.toString()+"��");
	    	sr.append(r.toString()+"��");
	    	return sr;
	    }
	    /**
	     * �������ϵ��רҵ
	     */
	    public static String getDept()
	    {
	    	int n=getNum(0, dept.length-1);
	    	int m=getNum(1, dept[n].length -1);
	    	String sdept=dept[n][0];
	    	String major=dept[n][m];
	    	return sdept+" "+major;
	    	
	    }
	    /**
	     * �������ѧ��
	     * @return
	     */
	    public static StringBuilder getStuno() {//��ʹ��String����Ϊ��Ҫ����ƴ���ַ���
	    	StringBuilder xh=new StringBuilder("2019401");//ѧ��ǰ7λ��ͬ
	    	StringBuilder xh1=new StringBuilder(String.valueOf(getNum(1,10000)));//���ȡ����λ
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
	    //��������������� 
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
	    public static void main(String[] args) {
	    	      addStudent();

		}
	        	public static void addStudent() {//���ӳɼ�
	        	DataBaseConnection02 dbcs=new DataBaseConnection02();
	        	String sql="insert into student(sno,sname,ssex,sage,birthday,password,sdept,major) values(?,?,?,?,?,?,?,?)";//ʹ��ռλ������������
	        	try(Connection conn=dbcs.getConnection();//��ȡ���ݿ��
	            		PreparedStatement pstmt=conn.prepareStatement(sql);){//ʵ����PreparedStatement
	                	ArrayList<String> alist=new ArrayList<String>();//���弯��
	            		for(int i=1;i<=10000;) {
	            			int age=getAge();
	            			String xh=getStuno().toString();//�����ȡѧ��
	            			if(!alist.contains(xh)) {//�ж�ѧ���Ƿ�Ψһ
	            				alist.add(xh);//��ѧ�ż��뼯��
	            				String s[]=getChineseName().split(" ");//�����ȡ�����Ա�
	            				String ss[]=getDept().split(" ");//�����ȡϵ��רҵ
	            				pstmt.setString(1, xh);//�����1��ռλ��������
	            	    		pstmt.setString(2, s[0]);//�����2��ռλ��������
	            	    		pstmt.setString(3, s[1]);//�����3��ռλ��������
	            	    		pstmt.setInt(4, age);//�����4��ռλ��������
	            	    		pstmt.setString(5, getBirthday(age).toString());//�����4��ռλ��������
	            	    		pstmt.setString(6, "123456");
	            	    		pstmt.setString(7, ss[0]);
	            	    		pstmt.setString(8, ss[1]);
	            	    		pstmt.addBatch();//����������ȴ�ִ��
	            				i++;//ѧ��Ψһ��ѭ����������ִ��
	            			}
	            		}
	            		pstmt.executeBatch();//����ִ�в������
	            		JOptionPane.showMessageDialog(null, "sucess");
	            	}catch(SQLException e) {
	            		e.printStackTrace();
	            	}
        
	     	}

}
