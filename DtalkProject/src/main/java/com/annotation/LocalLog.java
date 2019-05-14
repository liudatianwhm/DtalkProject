package com.annotation;

import java.lang.annotation.*;

/**
 * @author 86183
 * @title: LocalLog
 * @projectName eapp-corp-project
 * @description: TODO 自定义操作日志标签，模块名和方法名
 * @date 2019/5/138:46
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LocalLog {

    String module() default "";

    String method() default "";

}
