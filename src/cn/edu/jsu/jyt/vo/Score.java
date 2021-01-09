package cn.edu.jsu.jyt.vo;

import java.io.Serializable;

public class Score implements Serializable {
private String sno;
private String cno;
private double cj;

public Score() {
	super();
}

public Score(String sno, String cno) {
	super();
	this.sno = sno;
	this.cno = cno;
	this.cj = 0;
}

public Score(String sno, String cno, double cj) {
	super();
	this.sno = sno;
	this.cno = cno;
	this.cj = cj;
}

public String getSno() {
	return sno;
}

public void setSno(String sno) {
	this.sno = sno;
}

public String getCno() {
	return cno;
}

public void setCno(String cno) {
	this.cno = cno;
}

public double getCj() {
	return cj;
}

public void setCj(double cj) {
	this.cj = cj;
}

@Override
public String toString() {
	return "Score [sno=" + sno + ", cno=" + cno + ", cj=" + cj + ", toString()=" + super.toString() + "]";
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


}
