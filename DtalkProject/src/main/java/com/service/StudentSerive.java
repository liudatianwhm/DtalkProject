package com.service;

import com.github.pagehelper.PageInfo;
import com.models.Student;

import java.util.List;
import java.util.Map;

/**
 * @author 86183
 * @title: StudentSerive
 * @projectName eapp-corp-project
 * @description: TODO 学生服务
 * @date 2019/5/1015:13
 */
public interface StudentSerive {

    public Student getDetails(int id);

    public void batchInsert(List<String> userList);

    public PageInfo<Student> getPageInfo(Map<String,Object> map);
}
