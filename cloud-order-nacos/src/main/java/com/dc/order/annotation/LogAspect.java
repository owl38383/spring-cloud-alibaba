package com.dc.order.annotation;

import lombok.Builder;
import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @PACKAGE_NAME: com.dc.order.config
 * @NAME: Logs
 * @USER: 25738
 * @DATE: 2021/4/28
 **/
@Aspect
@Component
public class LogAspect {

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 此处的切点是注解的方式，也可以用包名的方式达到相同的效果
     * '@Pointcut("execution(* com.wwj.springboot.service.impl.*.*(..))")'
     */
//    @Pointcut("@annotation(com.dc.order.annotation.ActionLogs)")
    @Pointcut("execution(* com.dc.order.controller.*.*(..))")
    public void operationLog() {}


    /**
     * 环绕增强，相当于MethodInterceptor
     */
    @Around("operationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        assert sra != null;
        HttpServletRequest request = sra.getRequest();

        Object res = null;
        Date time = new Date();
        try {
            res = joinPoint.proceed();
            return res;
        } finally {
            try {
                addOperationLog(joinPoint,res,time);
            } catch (Exception e) {
                System.out.println("LogAspect 操作失败：" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void addOperationLog(JoinPoint joinPoint, Object res, Date time){
        // 获取到注解参数
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        ActionLogs annotation = signature.getMethod().getAnnotation(ActionLogs.class);

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        //方法执行完成后增加日志
        OperationLog operationLog = new OperationLog.OperationLogBuilder()
                .ip(getIpAddress(request))
                .uri(request.getRequestURI())
                .startTime(time)
                .responseTime(System.currentTimeMillis() - time.getTime())
                .build();
        mongoTemplate.save(operationLog);
//        System.out.println("记录日志:" + operationLog);
    }


//    @Before("operationLog()")
//    public void doBeforeAdvice(JoinPoint joinPoint) {
//        System.out.println("进入方法前执行....." + joinPoint);
//
//    }

    /**
     * 处理完请求，返回内容
     *
     * @param ret
     */
//    @AfterReturning(returning = "ret", pointcut = "operationLog()")
//    public void doAfterReturning(Object ret) {
//        System.out.println("方法的返回值 : " + ret);
//    }

    /**
     * 后置异常通知
     */
//    @AfterThrowing("operationLog()")
//    public void throwing(JoinPoint jp) {
//        System.out.println("方法异常时执行.....");
//    }


    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     */
//    @After("operationLog()")
//    public void after(JoinPoint jp) {
//        System.out.println("方法最后执行.....");
//    }

    static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
