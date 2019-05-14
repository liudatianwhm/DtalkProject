//package com.config;
//
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//
///**
// * @author 86183
// * @title: MyBatisConfig
// * @projectName eapp-corp-project
// * @description: TODO mybatis 映射注册
// * @date 2019/5/1015:48
// */
//@Configuration
//public class MyBatisConfig {
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean(name="sqlSessionFactory")
//    public SqlSessionFactoryBean sqlSessionFactory(ApplicationContext applicationContext) throws IOException {
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        Resource[] resources = new PathMatchingResourcePatternResolver()
//                .getResources("classpath*:mappers/*Mapper.xml");
//        sessionFactory.setMapperLocations(resources);
//        return sessionFactory;
//    }
//}
//
