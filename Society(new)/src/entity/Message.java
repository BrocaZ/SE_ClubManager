package entity;

import java.util.Date;

public class Message {
    private int mesid;
    private String sendsno;
    private String recsno;
    private String content;
    private java.util.Date senddate;
    private String status;
    private String remarks;

    public int getMesid() {
        return mesid;
    }

    public void setMesid(int mesid) {
        this.mesid = mesid;
    }

    public String getSendsno() {
        return sendsno;
    }

    public void setSendsno(String sendsno) {
        this.sendsno = sendsno;
    }

    public String getRecsno() {
        return recsno;
    }

    public void setRecsno(String recsno) {
        this.recsno = recsno;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSenddate() {
        return senddate;
    }

    public void setSenddate(Date senddate) {
        this.senddate = senddate;
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
