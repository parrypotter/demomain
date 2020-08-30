package com.panrui.panrui.service;

import com.panrui.panrui.mapper.UserResetEmail;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("userResetEmailService")
public class UserResetEmailServiceImpl implements UserResetEmailService {

    @Resource(name="userResetEmail")
    private UserResetEmail userResetEmail;

    @Override
    public Boolean checkUserEmailIsSimilar(String username, String perEmail) {
        /*true代表当前输入邮箱与数据库中相同*/
        return perEmail.equals(userResetEmail.checkUserEmailIsSimilar(username));
    }

    @Override
    public int updateUserEmail(String username, String email) {
        return userResetEmail.updateUserEmail(username,email);
    }
}
