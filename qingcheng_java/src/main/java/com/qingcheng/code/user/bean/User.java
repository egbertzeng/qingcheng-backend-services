/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.user.bean;

import java.util.ArrayList;

public class User {
    //表字段
    private Integer id;
    private String email;
    private String name;
    private String password;
    private String photo;
    private String slogan;
    private Boolean activeStatus;
    private String activeCode;
    private Boolean gender;
    private Integer role;
    private Integer followerNumber;

    //额外字段
    private ArrayList<Course> favoriteCourse=new ArrayList<>();
    private ArrayList<Course> publishCourse=new ArrayList<>();
    //用户是否被登陆的用户所关注
    private boolean userIsInFavorite;
    //发布课程的数量
    private Integer  publishCourseNumber;
    //关注课程的数量
    private Integer  followCourseNumber;

    public Integer getPublishCourseNumber() {
        return publishCourseNumber;
    }

    public void setPublishCourseNumber(Integer publishCourseNumber) {
        this.publishCourseNumber = publishCourseNumber;
    }

    public Integer getFollowCourseNumber() {
        return followCourseNumber;
    }

    public void setFollowCourseNumber(Integer followCourseNumber) {
        this.followCourseNumber = followCourseNumber;
    }

    public Integer getId() {
        return id;
    }

    public ArrayList<Course> getPublishCourse() {
        return publishCourse;
    }

    public void setPublishCourse(ArrayList<Course> publishCourse) {
        this.publishCourse = publishCourse;
    }

    public boolean isUserIsInFavorite() {
        return userIsInFavorite;
    }

    public void setUserIsInFavorite(boolean userIsInFavorite) {
        this.userIsInFavorite = userIsInFavorite;
    }

    public ArrayList<Course> getFavoriteCourse() {
        return favoriteCourse;
    }

    public void setFavoriteCourse(ArrayList<Course> favoriteCourse) {
        this.favoriteCourse = favoriteCourse;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getFollowerNumber() {
        return followerNumber;
    }

    public void setFollowerNumber(Integer followerNumber) {
        this.followerNumber = followerNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", slogan='" + slogan + '\'' +
                ", activeStatus=" + activeStatus +
                ", activeCode='" + activeCode + '\'' +
                ", gender=" + gender +
                ", role=" + role +
                ", followerNumber=" + followerNumber +
                ", favoriteCourse=" + favoriteCourse +
                ", publishCourse=" + publishCourse +
                ", userIsInFavorite=" + userIsInFavorite +
                '}';
    }
}
