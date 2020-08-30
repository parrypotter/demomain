package com.panrui.panrui.handler.handlerGroup;

import com.panrui.panrui.bean.Role;
import com.panrui.panrui.handler.AbstractHandler;
import com.panrui.panrui.handler.HandlerType;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
@HandlerType("1")
public class SuccessHandler extends AbstractHandler {

    public Map<Boolean,String> mapResult;

    @Override
    public Map<Boolean,String> abstractHandler(Role role) {
        mapResult = new HashMap<>();
        mapResult.put(false,"用户权限修改成功！");
        return mapResult;
    }

}
