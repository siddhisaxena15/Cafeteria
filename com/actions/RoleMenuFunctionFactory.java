package com.actions;

import com.controller.AdminMenuFunction;
import com.controller.ChefMenuFunction;
import com.controller.EmployeeMenuFunction;
import com.controller.RoleMenuFunction;

import java.util.HashMap;
import java.util.Map;

public class RoleMenuFunctionFactory {

    private static final Map<String, RoleMenuFunction> commandMap = new HashMap<>();

    static {
        commandMap.put("admin", new AdminMenuFunction());
        commandMap.put("chef", new ChefMenuFunction());
        commandMap.put("employee", new EmployeeMenuFunction());
    }

    public static RoleMenuFunction getRoleMenuFunction(String role) {
        RoleMenuFunction command = commandMap.get(role.toLowerCase());
        if (command == null) {
            throw new IllegalArgumentException("Unknown role: " + role);
        }
        return command;
    }
}
