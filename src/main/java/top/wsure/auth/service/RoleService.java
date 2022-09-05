package top.wsure.auth.service;

import top.wsure.auth.entity.Role;
import top.wsure.auth.entity.User;

public interface RoleService {

    boolean createRole(Role role);

    boolean deleteRole(Role role);

    Role getRole(Role role);

    boolean associate(User user,Role role);

}
