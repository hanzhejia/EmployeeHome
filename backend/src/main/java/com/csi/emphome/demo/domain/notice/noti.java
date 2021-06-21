package com.csi.emphome.demo.domain.notice;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="notice_inf")
public class noti {
    @Id
    private int id;
    private String tiitle;
    private String content;
    private Date caredate;
    private int userid;

    public noti() {
    }

    public noti(int id, String tiitle, Date caredate,String content,int userid) {
        this.id = id;
        this.caredate=caredate;
        this.userid=userid;
        this.tiitle=tiitle;
        this.content=content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTiitle() {
        return tiitle;
    }

    public void setTiitle(String tiitle) {
        this.tiitle = tiitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getCareTime() {
        return caredate;
    }

    public void setCaredate(Date caredate) {
        this.caredate = caredate;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "id=" + id +
                ", tittle='" + tiitle + '\'' +
                ", content=" + content +'\'' +
                ", caredate=" + caredate +'\'' +
                ", userid=" + userid +'\'' +
                '}';
    }

}

