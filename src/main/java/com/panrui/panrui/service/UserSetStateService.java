package com.panrui.panrui.service;


public interface UserSetStateService {

    /*设置用户激活*/
    Boolean setUserState(String username);

    /*激活前检查用户是否遭到封禁*/
    Boolean checkUserEnable(String username);

    /*通过userSession里的username反email再次发验证码*/
    String getEmailByUserName(String username);
}
