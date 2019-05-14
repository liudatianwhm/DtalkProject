package com.config;

import com.annotation.LocalLog;
import com.google.gson.Gson;
import com.models.Log;
import com.service.LogService;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author 86183
 * @title: LocalLogAction
 * @projectName eapp-corp-project
 * @description: TODO 定义日志aop
 * @date 2019/5/138:51
 */
@Component
@Aspect
public class LocalLogAction {
//    service切面，配置的切入点
    private final String POINT_CUT = "execution(* com.controller..*(..))";
    //日志
    private Logger log = Logger.getLogger(getClass());

    private Gson gson = new Gson();

    private long startTime = 0L;

    @Autowired
    private LogService logService;

    @Pointcut(POINT_CUT)
    private void contollerAspect(){}

    @Before(value = "contollerAspect()")
    public void methodBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //打印请求内容
        log.info("============请求内容==============");
        log.info("请求地址"+request.getRequestURL().toString());
        log.info("请求方式"+request.getMethod());
        log.info("请求类方法"+joinPoint.getSignature());
        log.info("请求类方法参数"+ Arrays.toString(joinPoint.getArgs()));
        log.info("============请求内容==============");
        this.startTime = System.currentTimeMillis();
    }
    @After("contollerAspect()")
    public void doAfterAdvice(JoinPoint joinPoint) throws Throwable{
        //模拟执行时长
        long time = System.currentTimeMillis() - startTime;
        // 拦截正在执行的实体类
        Object target = joinPoint.getTarget();
        //拦截正在执行的方法名称
        String methodName = joinPoint.getSignature().getName();

        //拦截放参的类型
        Signature paramType = joinPoint.getSignature();
        MethodSignature methodSignature = null;
        if (!(paramType instanceof MethodSignature)){
            throw new IllegalArgumentException("该方法只能用于方法！");
        }
        methodSignature = (MethodSignature) paramType;
        Class<?>[] parameterTypes = methodSignature.getMethod().getParameterTypes();

        Method method = target.getClass().getMethod(methodName, parameterTypes);

        if (null!=method){
            //获取方法，此处是自定义注解
            LocalLog localLog = method.getAnnotation(LocalLog.class);
            if (localLog!=null){
//                //获取注解的操作模块
//                System.out.println(localLog.module());
//                //获取注解的method
//                System.out.println(localLog.method());
                //日志入库测试
                Log logs = new Log();
                logs.setModule(localLog.module());
                logs.setMethod(localLog.method());
                int recordInfo = logService.recordInfo(logs);
//                System.out.println(recordInfo);
                log.info("本地录入的结果是"+recordInfo);
            }
        }
    }

    //在方法执行完毕后返回打印的内容
    @AfterReturning(returning = "o",pointcut = "contollerAspect()")
    public void methodAfterReturning(Object o){
        log.info("===========返回响应内容==========");
        log.info("respense内容："+gson.toJson(o));
        log.info("===========返回响应内容==========");
    }

    public int saveLog(JoinPoint joinPoint,long time){

        return 0;
    }
}
