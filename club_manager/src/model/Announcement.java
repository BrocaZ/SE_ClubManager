package model;

public class Announcement {
	private int annoucementId;
	private int associationId;
	private int annoContent;
	private int time;
	private int activityId;
	private int place;
	private int annoType;
	private int status;
	private int remarks;
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
	public int getAnnoContent() {
		return annoContent;
	}
	public void setAnnoContent(int annoContent) {
		this.annoContent = annoContent;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
	}
	public int getAnnoType() {
		return annoType;
	}
	public void setAnnoType(int annoType) {
		this.annoType = annoType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRemarks() {
		return remarks;
	}
	public void setRemarks(int remarks) {
		this.remarks = remarks;
	}
}
