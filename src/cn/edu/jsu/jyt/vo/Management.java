package cn.edu.jsu.jyt.vo;

import java.io.Serializable;

public class Management implements Serializable {
private String mno;
private String password;

public Management() {
	super();
}

public Management(String mno) {
	super();
	this.mno = mno;
	this.password="123456";
}

public Management(String mno, String password) {
	super();
	this.mno = mno;
	this.password = password;
}

public String getMno() {
	return mno;
}

public void setMno(String mno) {
	this.mno = mno;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

@Override
public boolean equals(Object obj) {
	// TODO Auto-generated method stub
	return super.equals(obj);
}

@Override
public int hashCode() {
	// TODO Auto-generated method stub
	return super.hashCode();
}

@Override
public String toString() {
	// TODO Auto-generated method stub
	return this.mno+"\t"+this.password;
}


}
