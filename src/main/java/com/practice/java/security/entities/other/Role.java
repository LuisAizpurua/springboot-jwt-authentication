package com.practice.java.security.entities.other;

import java.util.Arrays;
import java.util.List;

import static com.practice.java.security.entities.other.Permission.*;
public enum Role {
    ADMIN(Arrays.asList(READ_ALL_PRODUCT, READ_ONE_PRODUCT, CREATE_ONE_PRODUCT, UPDATE_ONE_PRODUCT, DELETE_ONE_PRODUCT)),
    CUSTOMER(Arrays.asList(READ_ALL_PRODUCT, READ_ONE_PRODUCT, UPDATE_ONE_PRODUCT, CREATE_ONE_PRODUCT)),
    USER(Arrays.asList(READ_ALL_PRODUCT, READ_ONE_PRODUCT))
    ;

    private List<Permission> listRole;

    Role(List<Permission> list) {
        this.listRole = list;
    }

    public List<Permission> getListRole() {
        return listRole;
    }
}
