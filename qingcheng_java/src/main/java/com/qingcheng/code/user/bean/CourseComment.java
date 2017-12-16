package com.qingcheng.code.user.bean;

/**
 * Created by liguohua on 2017/6/9.
 */
public class CourseComment {
    private Integer commentId;
    private String email;
    private String courseID;
    private String commentContent;
    private String commentTime;
    private boolean  commentCanDisplay;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public boolean isCommentCanDisplay() {
        return commentCanDisplay;
    }

    public void setCommentCanDisplay(boolean commentCanDisplay) {
        this.commentCanDisplay = commentCanDisplay;
    }

    @Override
    public String toString() {
        return "CourseComment{" +
                "commentId=" + commentId +
                ", email='" + email + '\'' +
                ", courseID='" + courseID + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", commentTime='" + commentTime + '\'' +
                ", commentCanDisplay=" + commentCanDisplay +
                '}';
    }
}
