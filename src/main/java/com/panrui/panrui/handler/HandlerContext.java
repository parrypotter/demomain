package com.panrui.panrui.handler;

import com.panrui.panrui.utils.BeanTool;
import java.util.Map;

@SuppressWarnings("unchecked")
public class HandlerContext {

    private Map<String ,Class> handlerMap;

    public HandlerContext(Map<String, Class> handlerMap) {
        this.handlerMap = handlerMap;
    }

    public AbstractHandler getInstance(String type){

        Class classz = handlerMap.get(type);
        if(classz==null){
            throw new IllegalArgumentException("当前没有此Type值："+type);
        }
        return (AbstractHandler) BeanTool.getBean(classz);

    }
}
