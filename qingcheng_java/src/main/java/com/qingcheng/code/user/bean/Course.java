/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.user.bean;

import com.qingcheng.code.common.constant.AppConstants;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liguohua on 6/27/16.
 */
public class Course implements Serializable {
    //表字段
    private String courseID;
    private String courseName;
    private String courseSubName;
    private String courseUrl;
    private String courseDescription;
    private String coursePoster;
    private Boolean courseOnLine;
    private Integer coursePlayTimes;
    private Boolean courseIsRecommended;
    private Integer courseRecommendStart;
    private Boolean courseIsRecommendedForLoop;
    private String categoryId;
    private String teacherEmail;
    private String materialDirName;

    //额外字段
    private String coursePosterUrl;
    private Boolean courseDescriptionShow;
    private Boolean courseIsInFavorite;
    private Integer courseFollowerNumber;
    private List<MaterialFile> materialFiles = new LinkedList<>();
    private List<CourseComment> comments = new LinkedList<>();




    public Course() {
    }

    public Course(String courseID, String courseName, String courseSubName, String courseUrl, String courseDescription, int coursePlayTimes, Boolean courseIsRecommended, int courseRecommendStart) {
        this(courseID, courseName, courseSubName, courseUrl, courseDescription);
        this.coursePlayTimes = coursePlayTimes;
        this.courseIsRecommended = courseIsRecommended;
        this.courseRecommendStart = courseRecommendStart;
    }

    public Course(String courseID, String courseName, String courseSubName, String courseUrl, String courseDescription) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseSubName = courseSubName;

        this.courseDescription = courseDescription;
        //1.处理一下URL
        if (!courseUrl.endsWith(AppConstants.URL_SPLITER)) {
            courseUrl = courseUrl + AppConstants.URL_SPLITER;
        }
        if (!courseUrl.startsWith(AppConstants.URL_SPLITER)) {
            courseUrl = AppConstants.URL_SPLITER + courseUrl;
        }

        this.courseUrl = courseUrl;

    }
    public String getCoursePosterUrl() {
        //2.拼接posterURL
        return this.coursePosterUrl = this.courseUrl + this.coursePoster;
    }


    public void setCoursePosterUrl(String coursePosterUrl) {
        this.coursePosterUrl = coursePosterUrl;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseSubName() {
        return courseSubName;
    }

    public void setCourseSubName(String courseSubName) {
        this.courseSubName = courseSubName;
    }

    public String getCourseUrl() {
        return courseUrl;
    }

    public void setCourseUrl(String courseUrl) {
        this.courseUrl = courseUrl;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCoursePoster() {
        return coursePoster;
    }

    public void setCoursePoster(String coursePoster) {
        this.coursePoster = coursePoster;
    }

    public Boolean getCourseOnLine() {
        return courseOnLine;
    }

    public void setCourseOnLine(Boolean courseOnLine) {
        this.courseOnLine = courseOnLine;
    }

    public Integer getCoursePlayTimes() {
        return coursePlayTimes;
    }

    public void setCoursePlayTimes(Integer coursePlayTimes) {
        this.coursePlayTimes = coursePlayTimes;
    }

    public Boolean getCourseIsRecommended() {
        return courseIsRecommended;
    }

    public void setCourseIsRecommended(Boolean courseIsRecommended) {
        this.courseIsRecommended = courseIsRecommended;
    }

    public Integer getCourseRecommendStart() {
        return courseRecommendStart;
    }

    public void setCourseRecommendStart(Integer courseRecommendStart) {
        this.courseRecommendStart = courseRecommendStart;
    }

    public Boolean getCourseIsRecommendedForLoop() {
        return courseIsRecommendedForLoop;
    }

    public void setCourseIsRecommendedForLoop(Boolean courseIsRecommendedForLoop) {
        this.courseIsRecommendedForLoop = courseIsRecommendedForLoop;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public Boolean getCourseDescriptionShow() {
        return courseDescriptionShow;
    }

    public void setCourseDescriptionShow(Boolean courseDescriptionShow) {
        this.courseDescriptionShow = courseDescriptionShow;
    }

    public Boolean getCourseIsInFavorite() {
        return courseIsInFavorite;
    }

    public void setCourseIsInFavorite(Boolean courseIsInFavorite) {
        this.courseIsInFavorite = courseIsInFavorite;
    }

    public Integer getCourseFollowerNumber() {
        return courseFollowerNumber;
    }

    public void setCourseFollowerNumber(Integer courseFollowerNumber) {
        this.courseFollowerNumber = courseFollowerNumber;
    }

    public String getMaterialDirName() {
        return materialDirName;
    }

    public void setMaterialDirName(String materialDirName) {
        this.materialDirName = materialDirName;
    }

    public List<MaterialFile> getMaterialFiles() {
        return materialFiles;
    }

    public void setMaterialFiles(List<MaterialFile> materialFiles) {
        this.materialFiles = materialFiles;
    }

    public List<CourseComment> getComments() {
        return comments;
    }

    public void setComments(List<CourseComment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID='" + courseID + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseSubName='" + courseSubName + '\'' +
                ", courseUrl='" + courseUrl + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", coursePoster='" + coursePoster + '\'' +
                ", courseOnLine=" + courseOnLine +
                ", coursePlayTimes=" + coursePlayTimes +
                ", courseIsRecommended=" + courseIsRecommended +
                ", courseRecommendStart=" + courseRecommendStart +
                ", courseIsRecommendedForLoop=" + courseIsRecommendedForLoop +
                ", categoryId='" + categoryId + '\'' +
                ", teacherEmail='" + teacherEmail + '\'' +
                ", materialDirName='" + materialDirName + '\'' +
                ", coursePosterUrl='" + coursePosterUrl + '\'' +
                ", courseDescriptionShow=" + courseDescriptionShow +
                ", courseIsInFavorite=" + courseIsInFavorite +
                ", courseFollowerNumber=" + courseFollowerNumber +
                ", materialFiles=" + materialFiles +
                ", comments=" + comments +
                '}';
    }
}
