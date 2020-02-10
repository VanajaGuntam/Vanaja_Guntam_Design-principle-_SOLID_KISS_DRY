package com.ex.service.impl;

import java.util.Collection;
import java.util.List;

import com.ex.dao.DAOFactory;
import com.ex.dao.exceptions.DAOException;
import com.ex.dao.interfaces.UserDAO;
import com.ex.dto.UserDTO;
import com.ex.service.exceptions.ServiceException;
import com.ex.service.interfaces.UserService;

public class UserServiceImpl implements UserService {
    private DAOFactory daoFactory=DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
    // Create a DAO
    UserDAO userDAO = daoFactory.getUserDAO();

    public boolean addUser(UserDTO userDTO) throws ServiceException {
        boolean boolResult=false;

        try {
            boolResult=userDAO.addUser(userDTO);
        }
        catch (DAOException daoEx) {
            throw new ServiceException(daoEx);
        }

        return boolResult;
    }

    public Collection<List<Object>> fetchUserRolesList() {
        return userDAO.fetchUserRolesList();
    }
}