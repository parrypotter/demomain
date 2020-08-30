package com.panrui.panrui.service.info;

import java.util.Map;

public interface UserInfoChangeService {


    Map<Integer,String> updateUserNickName(String nickname, String username);
    Map<Integer,String> updateUserPassword(String password, String username);
}
