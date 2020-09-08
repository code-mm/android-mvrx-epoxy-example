package com.ms.app.utils.permission;

public class PermissionClient {



    public static IPermission permission=new PermissionImpl();

    public static IPermission getPermission() {
        return permission;
    }
}
