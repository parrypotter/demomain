package com.panrui.panrui.service;

import com.panrui.panrui.bean.User;

public interface UserRegisterService {
    Integer doRegisterIn(String username , String password , String nickname , String email);
    String doRegisterUserNameCheck(String username);

}
