package com.panrui.panrui.handler.handlerGroup;

import com.panrui.panrui.bean.Role;
import com.panrui.panrui.handler.AbstractHandler;
import com.panrui.panrui.handler.HandlerType;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
@HandlerType("0")
public class FailHandler extends AbstractHandler {
    public Map<Boolean,String> mapResult;
    @Override
    public Map<Boolean,String> abstractHandler(Role role) {
        mapResult = new HashMap<>();
        mapResult.put(false,"用户权限修改失败，当前用户已是管理员！");
        return mapResult;
    }
}
