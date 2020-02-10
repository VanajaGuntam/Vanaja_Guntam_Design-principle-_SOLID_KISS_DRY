package com.ex.service.interfaces;

import java.util.Collection;
import java.util.List;

import com.ex.dto.UserDTO;
import com.ex.service.exceptions.ServiceException;

public interface UserService {
    public boolean addUser(UserDTO userDTO) throws ServiceException;
    public Collection<List<Object>> fetchUserRolesList();
}