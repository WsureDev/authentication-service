package top.wsure.auth.service.impl;

import org.jetbrains.annotations.NotNull;
import top.wsure.auth.cache.MemoryStore;
import top.wsure.auth.entity.Role;
import top.wsure.auth.entity.User;
import top.wsure.auth.service.AuthenticationService;
import top.wsure.auth.utils.SecurityUtil;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/*
    FileName:   AuthenticationServiceImpl
    Author:     wsure
    Date:       2022/9/6
    Description:
*/
public class AuthenticationServiceImpl implements AuthenticationService {
    public static long TWO_HOUR = 2*60*60*1000;

    @Override
    @NotNull
    public String authenticate(User user) {
        if(user == null) throw new RuntimeException("input user is null !");
        User exist = MemoryStore.userNameMapUser.get(user.getUserName());
        if(exist != null && exist.getPassword().equals(SecurityUtil.encryptUserPassword(user))){
            // create token
            String token = SecurityUtil.createToken(user);
            MemoryStore.tokenMapUserName.putCache(token,exist.getUserName(),TWO_HOUR);
            return token;
        }
        throw new RuntimeException("invalid passwd or username !");
    }

    @Override
    public void invalidate(String token) {
        MemoryStore.tokenMapUserName.clearByKey(token);
    }

    @Override
    public boolean checkRole(String token, Role role) {
        Set<String> roleNames = Optional.ofNullable(MemoryStore.tokenMapUserName.getCacheDataByKey(token))
                .map(userName ->MemoryStore.userNameMapUser.get(userName))
                .map(User::getRoles)
                .orElseThrow(() -> new RuntimeException("invalid token !"));
        String roleName = Optional.ofNullable(MemoryStore.roleNameMapRole.get(role.getRoleName()))
                .map(Role::getRoleName)
                .orElseThrow(() -> new RuntimeException("invalid role !"));
        return roleNames.contains(roleName);
    }

    @Override
    public Set<Role> allRoles(String token) {
        Set<String> roleNames = Optional.ofNullable(MemoryStore.tokenMapUserName.getCacheDataByKey(token))
                .map(userName ->MemoryStore.userNameMapUser.get(userName))
                .map(User::getRoles)
                .orElseThrow(() -> new RuntimeException("invalid token !"));
        return roleNames.stream()
                .map(v -> MemoryStore.roleNameMapRole.get(v))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
