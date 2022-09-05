package top.wsure.auth.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import top.wsure.auth.cache.MemoryStore;
import top.wsure.auth.entity.Role;
import top.wsure.auth.entity.User;
import top.wsure.auth.service.AuthenticationService;
import top.wsure.auth.service.RoleService;
import top.wsure.auth.service.UserService;
import top.wsure.auth.service.impl.AuthenticationServiceImpl;
import top.wsure.auth.service.impl.RoleServiceImpl;
import top.wsure.auth.service.impl.UserServiceImpl;

/*
    FileName:   AuthenticationServiceTest
    Author:     wsure
    Date:       2022/9/6
    Description:
*/
public class AuthenticationServiceTest {
    UserService userService = new UserServiceImpl();
    User user1 = new User("user1","pwd1");
    User user2 = new User("user1","pwd1");

    RoleService roleService = new RoleServiceImpl();

    Role role1 = new Role("role1","role");

    AuthenticationService authenticationService = new AuthenticationServiceImpl();
    String token;

    {
        userService.createUser(user1);
        roleService.createRole(role1);
        roleService.associate(user1,role1);

        token = authenticationService.authenticate(user2);
    }

    @Test
    public void testAuthenticate(){
        assert MemoryStore.tokenMapUserName.isContains(token);
    }

    @Test
    public void testInvalidate(){
        assert MemoryStore.tokenMapUserName.isContains(token);
        authenticationService.invalidate(token);
        assert ! MemoryStore.tokenMapUserName.isContains(token);
    }

    @Test
    public void testCheckRole(){
        authenticationService.checkRole(token,role1);
    }

    @Test
    public void testAllRoles(){
        Role r1 = new Role("r1","r1");
        Role r2 = new Role("r2","r2");
        Role r3 = new Role("r3","r3");
        roleService.createRole(r1);
        roleService.associate(user1,r1);
        roleService.createRole(r2);
        roleService.associate(user1,r2);
        roleService.createRole(r3);
        roleService.associate(user1,r3);
        assert user1.getRoles().size() == 4;
    }
}
