package top.wsure.auth.cache;

import top.wsure.auth.entity.Role;
import top.wsure.auth.entity.User;

import java.util.concurrent.ConcurrentHashMap;

/*
    FileName:   Memory
    Author:     wsure
    Date:       2022/9/5
    Description:
*/
public class MemoryStore {
    public static ConcurrentHashMap<String, User> userNameMapUser = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, Role> roleNameMapRole = new ConcurrentHashMap<>();

    public static CacheManager<String> tokenMapUserName = new CacheManager<>();

}
