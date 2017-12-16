package com.qingcheng.code.user.bean;

/**
 * Created by liguohua on 2017/5/25.
 */
public class UserParam {
    Integer role;
    String relationType;

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    @Override
    public String toString() {
        return "UserParam{" +
                "role=" + role +
                ", relationType='" + relationType + '\'' +
                '}';
    }
}
