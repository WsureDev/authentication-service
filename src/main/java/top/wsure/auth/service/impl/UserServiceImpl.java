package top.wsure.auth.service.impl;

import top.wsure.auth.cache.MemoryStore;
import top.wsure.auth.entity.User;
import top.wsure.auth.service.UserService;
import top.wsure.auth.utils.SecurityUtil;

import java.util.Map;

/*
    FileName:   UserServiceImpl
    Author:     wsure
    Date:       2022/9/6
    Description:
*/
public class UserServiceImpl implements UserService {
    @Override
    public boolean createUser(User user) {
        User exist = getUser(user);
        if(exist != null){
            throw new RuntimeException("user already exists !");
        }
        // 1. encrypt passwd
        String passwd = SecurityUtil.encryptUserPassword(user);
        // 2. save passwd
        user.setPassword(passwd);
        // 3. save user
        MemoryStore.userNameMapUser.put(user.getUserName(),user);
        return true;
    }

    @Override
    public boolean deleteUser(User user) {
        User exist = getUser(user);
        if(exist == null){
            throw new RuntimeException("user not exists !");
        }
        //1. delete token if exists
        MemoryStore.tokenMapUserName.getCacheAll()
                .entrySet()
                .stream()
                .filter(e -> e.getValue().getData().equals(user.getUserName()))
                .map(Map.Entry::getKey)
                .forEach(token -> MemoryStore.tokenMapUserName.clearByKey(token));
        //delete user
        MemoryStore.userNameMapUser.remove(user.getUserName());
        return true;
    }

    @Override
    public User getUser(User user) {
        if(user == null) throw new RuntimeException("input user is null!");
        return MemoryStore.userNameMapUser.get(user.getUserName());
    }


}
