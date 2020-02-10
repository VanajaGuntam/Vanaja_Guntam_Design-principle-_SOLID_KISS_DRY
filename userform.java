package com.ex.struts.formbeans;

import org.apache.struts.action.ActionForm;

import com.ex.dto.UserDTO;

public class UserForm extends ActionForm {
    private static final long serialVersionUID = 1L;

    private String actionMethod;

    private String userID;
    private String userName;
    private Integer userRoleID;

    public String getActionMethod() {
        return actionMethod;
    }
    public void setActionMethod(String actionMethod) {
        this.actionMethod = actionMethod;
    }

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

    // Method to get Transfer Object for User data
    public UserDTO getUserDTO() {
        return createUserDTO();
    }

    // method to create a new Transfer Object and 
    // copy data from entity bean into the value 
    // object
    private UserDTO createUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserID(this.userID);
        userDTO.setUserName(this.userName);
        userDTO.setUserRoleID(this.userRoleID);

        return userDTO;
    }
}
