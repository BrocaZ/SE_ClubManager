package entity;

import java.util.Date;

public class Announcement {
	private int annoucementId;
	private int associationId;
	private String associationName;
	private int activityId;
	private String title;
	private String annoContent;
	private Date createtime;
	private String place;
	private String annoType;
	private String status;
	private String remarks;
	private String annobrief;
	public int getAnnoucementId() {
		return annoucementId;
	}
	public void setAnnoucementId(int annoucementId) {
		this.annoucementId = annoucementId;
	}
	public int getAssociationId() {
		return associationId;
	}
	public void setAssociationId(int associationId) {
		this.associationId = associationId;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String gettitle() {
		return title;
	}
	public void settitle(String title) {
		this.title = title;
	}
	public String getAnnoContent() {
		return annoContent;
	}
	public void setAnnoContent(String annoContent) {
		this.annoContent = annoContent;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getAnnoType() {
		return annoType;
	}
	public void setAnnoType(String annoType) {
		this.annoType = annoType;
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
	public String getAnnobrief() {
		return annobrief;
	}
	public void setAnnobrief(String annobrief) {
		this.annobrief = annobrief;
	}
	public String getAssociationName() {
		return associationName;
	}
	public void setAssociationName(String associationName) {
		this.associationName = associationName;
	}
	
}
