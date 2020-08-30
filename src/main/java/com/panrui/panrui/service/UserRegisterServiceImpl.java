package com.panrui.panrui.service;

import com.panrui.panrui.mapper.UserRegisterRepository;
import com.panrui.panrui.utils.SnowFlakeUid;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("userRegisterService")
public class UserRegisterServiceImpl implements UserRegisterService {

    @Resource(name = "userRegisterRepository")
    private UserRegisterRepository userRegisterRepository;


    @Override
    public Integer doRegisterIn(String username, String password, String nickname, String email) {
        return userRegisterRepository.doRegisterIn(new SnowFlakeUid().nextId(),username, password, nickname, email);
    }

    @Override
    public String doRegisterUserNameCheck(String username) {
        return userRegisterRepository.findByUserName(username);
    }


}
