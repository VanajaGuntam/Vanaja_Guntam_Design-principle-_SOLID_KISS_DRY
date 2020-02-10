package com.ex.struts.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;

import com.ex.service.exceptions.ServiceException;
import com.ex.service.impl.UserServiceImpl;
import com.ex.service.interfaces.UserService;
import com.ex.struts.formbeans.UserForm;

public class UserAction extends DispatchAction {
    public ActionForward beginAddUser(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
    {
        try
        {
            UserForm userForm = (UserForm)form;
            userForm.setUserID(null);
            userForm.setUserName(null);
            userForm.setUserRoleID(null);

            HttpSession httpSession = request.getSession();
            UserService userService = new UserServiceImpl();
            Collection<LabelValueBean> colxnUserRoles = createLabelValueList(userService.fetchUserRolesList());
            httpSession.setAttribute("userRolesList",colxnUserRoles);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return mapping.findForward("fwdAddUser");
    }

    public ActionForward insertUser(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
    {
        try
        {
            UserService userService = new UserServiceImpl();
            UserForm userForm = (UserForm)form;
            if(userService.addUser(userForm.getUserDTO())) {
                System.out.println("New User created");
            }
            else {
                System.out.println("New User could not be created.");
            }
        }
        catch(ServiceException se)
        {
            System.out.println(se.getMessage());
        }

        return mapping.findForward("fwdAddUser");
    }

    protected Collection<LabelValueBean> createLabelValueList(Collection<List<Object>> inData)
    {
        List<LabelValueBean> outData = new ArrayList<LabelValueBean>();
        if(inData!=null && inData.size()>0)
        {
            Iterator<List<Object>> it = inData.iterator();
            while (it.hasNext())
            {
                List<Object> tmp = it.next();
                outData.add(new LabelValueBean(tmp.get(1).toString(), tmp .get(0).toString()));
            }
        }

        return outData;
    }
}