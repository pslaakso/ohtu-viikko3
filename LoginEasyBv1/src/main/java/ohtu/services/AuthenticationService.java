package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

	@Autowired
    private UserDao userDao;

	public AuthenticationService() {
		
	}

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
		if (username == null || username.length() < 3
				|| !username.matches("\\w+") // sanoja, ei numeroita
				|| userDao.findByName(username) != null
				) {
			return true;
		}
		if (password == null || password.length() < 8
				|| !containsNonAlpha(password)
//				|| !password.matches("\\w+[0-9]+\\w") // contains digits
				) {
			return true;
		}
		return false;
    }

	// returns true if string contains atleat one non-alphanumeric character, ie. string must have
	// atleast one number or special character
	private static boolean containsNonAlpha(String s) {
		if (s.matches(".*\\W+.*")) {
			return true;
		} else if (s.matches(".*[0-9].*")) {
			return true;
		}
		return false;
	}

}
