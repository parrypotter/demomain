package com.panrui.panrui.service;

import com.panrui.panrui.bean.User;

public interface UserLoginService {
    User doLoginIn(String username , String password);
}
