package cn.edu.jsu.jyt.dbc;

public class DataBaseConnection {
	// ����SQLServer�����ݿ���������
	public static final String DBDRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver" ;
   // ����MySQL�����ݿ���������
	public static final String DBDRIVER2 = "com.mysql.jdbc.Driver" ;
	public static void main(String[] args) {
		try {
			Class.forName(DBDRIVER2) ;			// ������������
             System.out.println("�������سɹ���");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

