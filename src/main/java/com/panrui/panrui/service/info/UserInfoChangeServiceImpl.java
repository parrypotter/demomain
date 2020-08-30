package com.panrui.panrui.service.info;

import com.panrui.panrui.mapper.UserInfoChange;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("userInfoChangeService")
public class UserInfoChangeServiceImpl implements UserInfoChangeService {

    @Resource(name="userInfoChange")
    private UserInfoChange userInfoChange;

    private Map<Integer , String> returnMap;

    @Override
    public Map<Integer, String> updateUserNickName(String nickname, String username) {
        returnMap = new HashMap<>();
        returnMap.put(userInfoChange.updateUserNickName(username,nickname),nickname);
        return returnMap;
    }

    @Override
    public Map<Integer, String> updateUserPassword(String password, String username) {
        returnMap = new HashMap<>();
        returnMap.put(userInfoChange.updateUserNickName(username,password),password);
        return returnMap;
    }
}
