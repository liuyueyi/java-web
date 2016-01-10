package com.mogu.hui.study.Constants;

/**
* Created by yihui on 16/1/9.
*/
public enum RoleLevel {
    DEFAULT(0), CUSTOM(1), SYSTEM(2), ROOT(3), RoleLevel;

    private interface RoleNames {
        String[] names = new String[] {
            "default", "custom", "system", "root"
        };
    }

    int roleValue;
    String roleName;

    RoleLevel() {
        this.roleValue = 0;
        this.roleName = RoleNames.names[0];
    }

    RoleLevel(int value) {
        this.roleValue = value;
        this.roleName = RoleNames.names[value];
    }

    public int getRoleValue() {
        return roleValue;
    }

    public final String getRoleName() {
        return roleName;
    }
}
