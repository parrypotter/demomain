package com.panrui.panrui.service;


import com.panrui.panrui.mapper.UserSetState;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userSetStateService")
public class UserSetStateServiceImpl implements UserSetStateService {

    @Resource(name = "userSetState")
    private UserSetState userSetState;

    private boolean userEnable;

    @Override
    public Boolean setUserState(String username) {

        if(userSetState.setUserState(username)==1){
            return true;
        }
        else if(userSetState.setUserState(username)>1){
            /*数据库有问题，存在多个相同名称的用户*/
            return false;
        }else {
            /*数据库有问题，无法进行更新*/
            return false;
        }

    }

    @Override
    public Boolean checkUserEnable(String username) {

        if("0".equals(userSetState.checkUserEnable(username))){
            userEnable = true;
        }else if("1".equals(userSetState.checkUserEnable(username))){
            userEnable = false;
        }
        return userEnable;
    }

    @Override
    public String getEmailByUserName(String username) {
        return userSetState.getEmailByUserName(username);
    }
}
