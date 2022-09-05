package top.wsure.auth.service;

import top.wsure.auth.entity.Role;
import top.wsure.auth.entity.User;

import java.util.Set;

public interface AuthenticationService {


    String authenticate(User user);

    void invalidate(String token);

    boolean checkRole(String token, Role role);

    Set<Role> allRoles(String token);

}
