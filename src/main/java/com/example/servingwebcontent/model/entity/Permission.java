package com.example.servingwebcontent.model.entity;

/**
 * @author Oksana Borisenko
 */
public enum Permission {
    DEV_READ("dev:read"),
    DEV_WRITE("dev:write");

    private final String permission;

    Permission(String permission){
        this.permission=permission;
    }

    public String getPermission(){
        return permission;
    }
}
