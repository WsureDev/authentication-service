package top.wsure.auth.service.impl;

import top.wsure.auth.cache.MemoryStore;
import top.wsure.auth.entity.Role;
import top.wsure.auth.entity.User;
import top.wsure.auth.service.RoleService;

/*
    FileName:   RoleServiceImpl
    Author:     wsure
    Date:       2022/9/6
    Description:
*/
public class RoleServiceImpl implements RoleService {
    @Override
    public boolean createRole(Role role) {
        Role exist = getRole(role);
        if(exist != null){
            throw new RuntimeException("role already exists !");
        }
        // save role
        MemoryStore.roleNameMapRole.put(role.getRoleName(),role);
        return true;
    }

    @Override
    public boolean deleteRole(Role role) {
        Role exist = getRole(role);
        if(exist == null){
            throw new RuntimeException("role not exists!");
        }
        //1. delete role from all user
        MemoryStore.userNameMapUser
                .values()
                .forEach( user -> user.getRoles().remove(exist.getRoleName()));
        //2. delete role from roleMap
        MemoryStore.roleNameMapRole.remove(exist.getRoleName());
        return true;
    }

    @Override
    public Role getRole(Role role) {
        if(role == null) throw new RuntimeException("input role is null!");
        return MemoryStore.roleNameMapRole.get(role.getRoleName());
    }

    @Override
    public boolean associate(User user, Role role) {
        if(user == null) throw new RuntimeException("input user is null!");
        if(role == null) throw new RuntimeException("input role is null!");
        User existUser = MemoryStore.userNameMapUser.get(user.getUserName());
        if(existUser == null) throw new RuntimeException("user not exists !");
        Role existRole = MemoryStore.roleNameMapRole.get(role.getRoleName());
        if(existRole == null) throw  new RuntimeException("role already exists !");
        // add role to user
        existUser.getRoles().add(existRole.getRoleName());
        return true;
    }

}
