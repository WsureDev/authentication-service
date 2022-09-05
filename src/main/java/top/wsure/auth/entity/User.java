package top.wsure.auth.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*
    FileName:   User
    Author:     wsure
    Date:       2022/9/5
    Description:
*/
public class User {
    private String userName;

    private String password;

    private Set<String> roles = new HashSet<>();

    public User(String userName, String password) {
        Objects.requireNonNull(userName, "userName can't be null");
        Objects.requireNonNull(password, "passWord can't be null");
        this.userName = userName;
        this.password = password;
    }
    public User(String userName, String password, Set<String> roles) {
        this(userName, password);
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
