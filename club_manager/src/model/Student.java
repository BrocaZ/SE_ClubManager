package model;

public class Student {
	  private String sno;
	   private byte[] head_image;
	   private String name;
	   private String sex;
	   private String tel;
	   private String branch;
	   private String major;
	   private String stuclass;
	   private String password;
	   private String status;
	   private String remarks;
	   public static Student curStu=null;
	public Student getCurStu() {
		return curStu;
	}
	public void setCurStu(Student curStu) {
		this.curStu = curStu;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public byte[] getHead_image() {
		return head_image;
	}
	public void setHead_image(byte[] head_image) {
		this.head_image = head_image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getStuclass() {
		return stuclass;
	}
	public void setStuclass(String stuclass) {
		this.stuclass = stuclass;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
