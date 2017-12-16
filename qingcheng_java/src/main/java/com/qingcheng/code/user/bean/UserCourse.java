package com.qingcheng.code.user.bean;

/**
 * Created by liguohua on 2017/5/14.
 */
public class UserCourse {
    private Integer id ;
    private String email;
    private String courseID;
    private Boolean favorite;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "UserCourse{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", courseID='" + courseID + '\'' +
                ", favorite=" + favorite +
                '}';
    }
}
