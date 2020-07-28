package com.panrui.panrui.service;

import com.panrui.panrui.bean.User;
import com.panrui.panrui.mapper.UserLoginRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService{

    @Resource(name="userLoginRepository")
    private UserLoginRepository userLoginRepository;

    @Override
    public User doLoginIn(String username, String password) {
        return userLoginRepository.doLoginIn(username,password);
    }
}
