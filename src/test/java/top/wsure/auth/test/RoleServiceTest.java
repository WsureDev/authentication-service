package top.wsure.auth.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import top.wsure.auth.cache.MemoryStore;
import top.wsure.auth.entity.Role;
import top.wsure.auth.entity.User;
import top.wsure.auth.service.RoleService;
import top.wsure.auth.service.UserService;
import top.wsure.auth.service.impl.RoleServiceImpl;
import top.wsure.auth.service.impl.UserServiceImpl;

/*
    FileName:   RoleServiceTest
    Author:     wsure
    Date:       2022/9/6
    Description:
*/
public class RoleServiceTest {
    RoleService roleService = new RoleServiceImpl();

    Role role1 = new Role("role1","for test");

    Role role2 = new Role("role1","for test");

    @Test
    public void testCreateRole(){
        Assertions.assertThrows(RuntimeException.class,() -> {
            roleService.createRole(role1);
            roleService.createRole(role2);
        });
    }

    @Test
    public void testDeleteRole(){
        roleService.createRole(role1);
        roleService.deleteRole(role2);
        Assertions.assertEquals(MemoryStore.roleNameMapRole.toString(),"{}");
    }

    @Test
    public void testGetRole(){
        roleService.createRole(role1);
        Assertions.assertEquals(MemoryStore.roleNameMapRole.get(role1.getRoleName()),role1);
    }

    @Test
    public void testAssociateRole(){
        UserService userService = new UserServiceImpl();
        User user = new User("user1","pwd1");
        userService.createUser(user);

        roleService.createRole(role1);
        roleService.associate(user,role1);

        assert MemoryStore.userNameMapUser.get(user.getUserName()).getRoles().contains(role1.getRoleName());
    }

}
