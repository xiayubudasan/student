package cn.edu.jsu.jyt.vo;

import java.io.Serializable;

public class CClass implements Serializable {
	private String cno;
	private String cname;
	private double ccredit;
	private String tno;
	
	public CClass() {
		super();
	}
	
	public CClass(String cno, String cname, double ccredit) {
		super();
		this.cno = cno;
		this.cname = cname;
		this.ccredit = ccredit;
		this.tno = "";
	}
	
	public CClass(String cno, String cname, double ccredit, String tno) {
		super();
		this.cno = cno;
		this.cname = cname;
		this.ccredit = ccredit;
		this.tno = tno;
	}
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public double getCcredit() {
		return ccredit;
	}
	public void setCcredit(double ccredit) {
		this.ccredit = ccredit;
	}
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.tno = tno;
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
	/**
	 * ÷ÿ–¥toString
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	

}
