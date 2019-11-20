package model;

import java.util.*;

public class Association {
	private int associationId;
	private int placId;
	private String associationName;
	private byte[] logo;
	private String chiefSno;
	private String intro;
	private String status;
	private String remarks;

	public int getAssociationId() {
		return associationId;
	}

	public void setAssociationId(int associationId) {
		this.associationId = associationId;
	}

	public int getPlacId() {
		return placId;
	}

	public void setPlacId(int placId) {
		this.placId = placId;
	}

	public String getAssociationName() {
		return associationName;
	}

	public void setAssociationName(String associationName) {
		this.associationName = associationName;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getChiefSno() {
		return chiefSno;
	}

	public void setChiefSno(String chiefSno) {
		this.chiefSno = chiefSno;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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