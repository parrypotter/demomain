package com.panrui.panrui.handler;

import com.panrui.panrui.bean.Role;
import java.util.Map;

public abstract class AbstractHandler {
    abstract public Map<Boolean,String> abstractHandler(Role role);
}
