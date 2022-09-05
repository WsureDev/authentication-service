package top.wsure.auth;

import top.wsure.auth.api.AuthenticationHttpService;
import top.wsure.auth.service.AuthenticationService;
import top.wsure.auth.service.RoleService;
import top.wsure.auth.service.UserService;
import top.wsure.auth.service.impl.AuthenticationServiceImpl;
import top.wsure.auth.service.impl.RoleServiceImpl;
import top.wsure.auth.service.impl.UserServiceImpl;

import java.util.Scanner;

/*
    FileName:   AuthenticationServiceStater
    Author:     wsure
    Date:       2022/9/5
    Description:
*/
public class AuthenticationServiceStater {

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        RoleService roleService = new RoleServiceImpl();
        AuthenticationService authenticationService = new AuthenticationServiceImpl();
        AuthenticationHttpService authenticationHttpService = new AuthenticationHttpService(8080,
                userService,
                roleService,
                authenticationService);
    }
}
