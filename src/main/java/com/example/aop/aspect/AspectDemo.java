package com.example.aop.aspect;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
@Order(1)
public class AspectDemo {

    //申明一个切点 里面是 execution表达式
    @Pointcut("execution(public * com.example.aop.controller.*.*(..))")
    private void controllerAspect() {
    }

    //请求method前打印内容
    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();

        //打印请求内容
        log.info("===============请求内容===============");
        log.info("请求地址:" + request.getRequestURL().toString());
        log.info("请求方式:" + request.getMethod());
        log.info("请求类方法:" + joinPoint.getSignature());
        log.info("请求类方法参数:" + Arrays.toString(joinPoint.getArgs()));
        log.info("===============请求内容===============");
    }


    //在方法执行完结后打印返回内容
    @AfterReturning(returning = "object", pointcut = "controllerAspect()")
    public void methodAfterReturning(Object object) {
        log.info("--------------返回内容----------------");
        log.info("Response内容:" + new Gson().toJson(object));
        log.info("--------------返回内容----------------");
    }

}
