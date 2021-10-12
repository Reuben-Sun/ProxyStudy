package com.reuben.proxystudy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SpringBootApplication
public class ProxyStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyStudyApplication.class, args);

        SalesService service = new SalesService();
        //静态代理
        System.out.println("-----静态代理-----");
        StaticProxyDemo demo1 = new StaticProxyDemo(service);
        demo1.sell();
        //JDK动态代理
        System.out.println("---JDK动态代理----");
        InvocationHandler handler = new JDKProxy(service);
        SellProvider demo2 = (SellProvider) Proxy.newProxyInstance(SalesService.class.getClassLoader(), SalesService.class.getInterfaces(), handler);
        demo2.sell();
        //CGLIB动态代理
        System.out.println("--CGLIB动态代理---");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SalesService.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("售前服务");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("售后增值");
                return result;
            }
        });
        SalesService service1 = (SalesService) enhancer.create();
        service1.sell();
    }

}
