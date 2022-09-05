package top.wsure.auth.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import top.wsure.auth.cache.MemoryStore;
import top.wsure.auth.entity.User;
import top.wsure.auth.service.UserService;
import top.wsure.auth.service.impl.UserServiceImpl;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
    FileName:   UserServiceTest
    Author:     wsure
    Date:       2022/9/6
    Description:
*/
public class UserServiceTest {
    UserService userService = new UserServiceImpl();
    User user1 = new User("test1", "pwd1");
    User user2 = new User("test1", "pwd2");

    @Test
    public void testCreateUser() {
        Assertions.assertThrows(RuntimeException.class,() -> {
            userService.createUser(user1);
            userService.createUser(user2);
        });

    }

    @Test
    public void testDeleteUser() {
        userService.createUser(user1);
        userService.deleteUser(user2);
        Assertions.assertEquals(MemoryStore.userNameMapUser.toString(),"{}");
    }

    @Test
    public void testGetUser() {
        userService.createUser(user1);
        Assertions.assertEquals(userService.getUser(user1),user1);
    }
}
