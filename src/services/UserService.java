package services;

import beans.User;
import beans.UserSessionBean;
import daos.UserDAO;
import utils.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
public class UserService {
    @Inject
    private UserDAO userDAO;

    public boolean loginUser(final String userName, final String password) {
        String savedPassword = this.userDAO.getPasswordForUser(userName);
        return StringUtils.hashPassword(password).equals(savedPassword);
    }

    public boolean registerUser(final String eMail, final String password, final String firstName, final String lastName) {
        if(!(this.userDAO.getUser(eMail) == null)) {
            return false;
        }

        return this.userDAO.registerUser(eMail, password, firstName, lastName);
    }

    public UserSessionBean getUserSessionData(final String userID) {
        User user = userDAO.getUser(userID);
        UserSessionBean userSessionBean = new UserSessionBean();
        userSessionBean.setFirstName(user.getFirstName());
        userSessionBean.setLastName(user.getLastName());
        userSessionBean.setEMail(user.geteMail());
        return userSessionBean;
    }
}
