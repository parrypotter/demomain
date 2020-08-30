package com.panrui.panrui.bean;

import lombok.Data;

@Data
public class Role {
    private int uid;
    private String username;
    private int userRole;
    private Integer userEnable;
    private String type;
}
