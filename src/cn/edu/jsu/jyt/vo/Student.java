package cn.edu.jsu.jyt.vo;

import java.io.Serializable;

public class Student implements Serializable{
private String sno;
private String sname;
private String ssex;
private int sage;
private String birthday;
private String password;
private String sdept;
private String major;

public Student() {
	super();
}

public Student(String sno, String sname, String ssex, int sage, String birthday, String sdept, String major) {
	super();
	this.sno = sno;
	this.sname = sname;
	this.ssex = ssex;
	this.sage = sage;
	this.birthday = birthday;
	this.sdept = sdept;
	this.major = major;
	this.password="123456";
}

public Student(String sno, String sname, String ssex, int sage, String birthday, String password, String sdept,
		String major) {
	super();
	this.sno = sno;
	this.sname = sname;
	this.ssex = ssex;
	this.sage = sage;
	this.birthday = birthday;
	this.password = password;
	this.sdept = sdept;
	this.major = major;
}


public String getMajor() {
	return major;
}

public void setMajor(String major) {
	this.major = major;
}

public String getSno() {
	return sno;
}
public void setSno(String sno) {
	this.sno = sno;
}
public String getSname() {
	return sname;
}
public void setSname(String sname) {
	this.sname = sname;
}
public String getSsex() {
	return ssex;
}
public void setSsex(String ssex) {
	this.ssex = ssex;
}
public int getSage() {
	return sage;
}
public void setSage(int sage) {
	this.sage = sage;
}
public String getBirthday() {
	return birthday;
}
public void setBirthday(String birthday) {
	this.birthday = birthday;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getSdept() {
	return sdept;
}
public void setSdept(String sdept) {
	this.sdept = sdept;
}

@Override
public boolean equals(Object arg0) {
	// TODO Auto-generated method stub
	return super.equals(arg0);
}

@Override
public int hashCode() {
	// TODO Auto-generated method stub
	return super.hashCode();
}

@Override
public String toString() {
	// TODO Auto-generated method stub
	return super.toString();
}


}
