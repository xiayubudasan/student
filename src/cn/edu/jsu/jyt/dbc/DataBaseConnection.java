package cn.edu.jsu.jyt.dbc;

public class DataBaseConnection {
	// 定义SQLServer的数据库驱动程序
	public static final String DBDRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver" ;
   // 定义MySQL的数据库驱动程序
	public static final String DBDRIVER2 = "com.mysql.jdbc.Driver" ;
	public static void main(String[] args) {
		try {
			Class.forName(DBDRIVER2) ;			// 加载驱动程序
             System.out.println("驱动加载成功！");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

