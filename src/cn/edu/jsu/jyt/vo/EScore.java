package cn.edu.jsu.jyt.vo;

public class EScore {
	private String sno;
	private String sname;
	private String cname;
	private double grade;
	
	public EScore() {
		super();
	}

	public EScore(String sno, String sname, String cname, double grade) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.cname = cname;
		this.grade = grade;
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

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	

}
