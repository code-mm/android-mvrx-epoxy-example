package com.ms.app.utils.permission;

public class PermissionClient {

    private static IPermission permission = new PermissionImpl();

    public static IPermission getPermission() {
        return permission;
    }
}
