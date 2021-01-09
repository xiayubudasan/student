package cn.edu.jsu.jyt.vo;

import java.io.Serializable;

public class Teacher implements Serializable {
	private String tno;
	private String tname;
	private String tsex;
	private int tage;
	private String tpt;
	private String password;
	
	public Teacher() {
		super();
	}

	public Teacher(String tno, String tname, String tsex, int tage, String tpt) {
		super();
		this.tno = tno;
		this.tname = tname;
		this.tsex = tsex;
		this.tage = tage;
		this.tpt = tpt;
		this.password="123456";
	}

	public Teacher(String tno, String tname, String tsex, int tage, String tpt, String password) {
		super();
		this.tno = tno;
		this.tname = tname;
		this.tsex = tsex;
		this.tage = tage;
		this.tpt = tpt;
		this.password = password;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTsex() {
		return tsex;
	}

	public void setTsex(String tsex) {
		this.tsex = tsex;
	}

	public int getTage() {
		return tage;
	}

	public void setTage(int tage) {
		this.tage = tage;
	}

	public String getTpt() {
		return tpt;
	}

	public void setTpt(String tpt) {
		this.tpt = tpt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
