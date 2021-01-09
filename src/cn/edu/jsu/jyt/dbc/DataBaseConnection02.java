package cn.edu.jsu.jyt.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class DataBaseConnection02 {
  private static final String DBRIVER="com.mysql.cj.jdbc.Driver"; //����MySQL���ݿ���������
  private static final String DBURL="jdbc:mysql://localhost:3306/information_management?serverTimezone=GMT%2B8"; //����MySQL���ݿ����ӵ�ַ
  private static final String DBUSER="root"; //MySQL���ݿ������û���
  private static final String PASSWORD="123456"; //MySQL���ݿ���������
  private Connection conn=null; //�������Ӷ���
  public DataBaseConnection02() {//���췽���������ݿ�
    try {
      Class.forName(DBRIVER);
      this.conn=DriverManager.getConnection(DBURL,DBUSER,PASSWORD);
    } catch (Exception e) {e.printStackTrace();}
  }
  public Connection getConnection() {//�������ݿ����Ӷ���
    return this.conn;
  }
@Test
  public void close() {//�ر���������
    if(this.conn!=null) {
      try {this.conn.close();} catch (SQLException e) {e.printStackTrace();}
}}

  }