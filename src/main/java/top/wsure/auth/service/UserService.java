package top.wsure.auth.service;

import top.wsure.auth.entity.User;

public interface UserService {

    boolean createUser(User user);

    boolean deleteUser(User user);

    User getUser(User user);


}
