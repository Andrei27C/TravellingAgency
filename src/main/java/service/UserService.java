package service;

import model.UserEntity;
import repository.UserRepository;
import service.validation.RegisterValidation;

import java.util.List;

public class UserService {
    List<UserEntity> userEntityList = UserRepository.getAllUsers();

    private void addUser(String username, String email, String password)
    {
        int userId = userEntityList.size();
        UserEntity userEntity = new UserEntity(userId, username, password, "user", email);
        userEntityList.add(userEntity);
        UserRepository.addUser(userEntity);
    }

    public String userOrAdmin(String username, String password)
    {
        for (UserEntity x :
                userEntityList) {
            if (username.equals(x.getUsername()) && password.equals(x.getPassword()))
                return x.getRole();
            else if(username.equals(x.getUsername()))
            {
                return "incorrect_password";
            }
        }
        return "";
    }

    private boolean userFound(String username)
    {
        for (UserEntity x :
                userEntityList) {
            if (username.equals(x.getUsername()))
                return true;
        }
        return false;
    }

    private boolean emailFound(String email)
    {
        for (UserEntity x :
                userEntityList) {
            if (email.equals(x.getEmail()))
                return true;
        }
        return false;
    }

    public String registerUser(String username, String email, String password)
    {
        UserEntity user = new UserEntity(username, password, "user", email);
        if(userFound(username))
        {
            return "username_found";
        }
        else if(emailFound(email))
        {
            return "email_found";
        }
        else if(!RegisterValidation.validPassword(password))
        {
            return "invalid_password";
        }
        else if(!RegisterValidation.validEmail(email))
        {
            return "invalid_email";
        }
        else if(username.equals(""))
        {
            return "username_empty";
        }
        addUser(username, email, password);
        return "user_added";
    }
}
