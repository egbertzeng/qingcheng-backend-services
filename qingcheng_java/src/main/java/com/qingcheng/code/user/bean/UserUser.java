package com.qingcheng.code.user.bean;

/**
 * Created by liguohua on 2017/5/19.
 */
public class UserUser {
    private Integer id;
    private String fromEmail;
    private String toEmail;
    private Integer relationType;
    private Boolean relationStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public Integer getRelationType() {
        return relationType;
    }

    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }

    public Boolean getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(Boolean relationStatus) {
        this.relationStatus = relationStatus;
    }

    @Override
    public String toString() {
        return "UserUser{" +
                "id=" + id +
                ", fromEmail='" + fromEmail + '\'' +
                ", toEmail='" + toEmail + '\'' +
                ", relationType=" + relationType +
                ", relationStatus=" + relationStatus +
                '}';
    }
}
