package com.reuben.proxystudy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKProxy implements InvocationHandler {
    Object target;

    public JDKProxy(Object _target){
        super();
        target = _target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("售前服务");
        method.invoke(target, args);
        System.out.println("售后增值");
        return null;
    }
}
