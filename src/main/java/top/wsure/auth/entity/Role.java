package top.wsure.auth.entity;

import java.util.Objects;

/*
    FileName:   Role
    Author:     wsure
    Date:       2022/9/5
    Description:role
*/
public class Role {

    private String roleName;

    private String description;


    public Role(String roleName, String description) {
        Objects.requireNonNull(roleName, "roleName can't be null");
        Objects.requireNonNull(description,"description can't be null");
        this.roleName = roleName;
        this.description = description;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
