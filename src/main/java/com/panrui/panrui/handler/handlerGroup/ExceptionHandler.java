package com.panrui.panrui.handler.handlerGroup;

import com.panrui.panrui.bean.Role;
import com.panrui.panrui.handler.AbstractHandler;
import com.panrui.panrui.handler.HandlerType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@HandlerType("2")
public class ExceptionHandler extends AbstractHandler {

    public Map<Boolean,String> mapResult;
    @Override
    public Map<Boolean,String> abstractHandler(Role role) {
        mapResult = new HashMap<>();
        mapResult.put(false,"存在多个相同数据，请联系管理员查看数据库！");
        return mapResult;
    }
}
