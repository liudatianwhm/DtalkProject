package com.serivceImp;

import com.dao.StudentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.models.Student;
import com.service.StudentSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * @author 86183
 * @title: StudentImp
 * @projectName eapp-corp-project
 * @description: TODO 学生服务实现类
 * @date 2019/5/1015:14
 */
@Service
public class StudentImp implements StudentSerive {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student getDetails(int id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void batchInsert(List<String> userList) {
        studentMapper.batchInsert(userList);
    }

    @Override
    public PageInfo<Student> getPageInfo(Map<String,Object> map) {
        int pageNum = Integer.valueOf(map.get("pageNum").toString());
        int pageSize = Integer.valueOf(map.get("pageSize").toString());
        PageInfo<Student> studentPageInfo = PageHelper.startPage(1, 2).doSelectPageInfo(() -> studentMapper.selectAll());
//        List<Student> studentList = studentMapper.selectAll();
//        PageInfo<Student> studentPageInfo = new PageInfo<>(studentList);
        System.out.println(studentPageInfo.getTotal());
        return studentPageInfo;
    }
}
