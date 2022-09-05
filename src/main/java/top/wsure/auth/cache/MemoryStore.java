package top.wsure.auth.cache;

import top.wsure.auth.entity.Role;
import top.wsure.auth.entity.User;

import java.util.HashMap;

/*
    FileName:   Memory
    Author:     wsure
    Date:       2022/9/5
    Description:
*/
public class MemoryStore {
    public static HashMap<String, User> userNameMapUser = new HashMap<>();

    public static HashMap<String, Role> roleNameMapRole = new HashMap<>();

    public static CacheManager<String> tokenMapUserName = new CacheManager<>();

}
