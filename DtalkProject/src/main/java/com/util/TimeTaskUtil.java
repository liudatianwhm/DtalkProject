//package com.util;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * @Auther: 69013
// * @Date: 2019/5/8 08:40
// * @Description: xiaofeixia
// */
//@Configuration
//@EnableScheduling
//public class TimeTaskUtil {
//
//    /**
//     *
//     * 功能描述:  每隔几分钟执行一次方法调度
//     *
//     * @param:
//     * @return:
//     * @auther:
//     * @date:
//     */
//    @Scheduled(cron = "0 0/2 * * * ?")//每隔两分钟执行一次
//    public void timeTask1(){
//        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        System.out.println("任务执行每两分钟一次"+time);
//    }
//
//    /**
//     *
//     * 功能描述:  用作每天凌晨推迟5分钟执行
//     *
//     * @param:
//     * @return:
//     * @auther:
//     * @date:
//     */
//    @Scheduled(cron = "0 5 0 * * ?")//每天凌晨5分钟执行定时任务
//    public void timerTask(){
//        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        System.out.println("任务执行每两分钟一次"+time);
//    }
//}
