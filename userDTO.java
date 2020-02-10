package com.ex.dto;

public class UserDTO implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String userID;
    private String userName;
    private Integer userRoleID;

    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserRoleID() {
        return userRoleID;
    }
    public void setUserRoleID(Integer userRoleID) {
        this.userRoleID = userRoleID;
    }
}