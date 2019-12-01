package entity;

import java.util.*;

public class Activity {
	private int activityId;
	private int palceId;
	private int associationId;
	private String activityContent;
	private String leaderSno;
	private Date startTime;
	private Date endTime;
	private int attendNumber;
	private float buget;
	private String status;
	private String remarks;
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public int getPalceId() {
		return palceId;
	}
	public void setPalceId(int palceId) {
		this.palceId = palceId;
	}
	public int getAssociationId() {
		return associationId;
	}
	public void setAssociationId(int associationId) {
		this.associationId = associationId;
	}
	public String getActivityContent() {
		return activityContent;
	}
	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}
	public String getLeaderSno() {
		return leaderSno;
	}
	public void setLeaderSno(String leaderSno) {
		this.leaderSno = leaderSno;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getAttendNumber() {
		return attendNumber;
	}
	public void setAttendNumber(int attendNumber) {
		this.attendNumber = attendNumber;
	}
	public float getBuget() {
		return buget;
	}
	public void setBuget(float buget) {
		this.buget = buget;
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