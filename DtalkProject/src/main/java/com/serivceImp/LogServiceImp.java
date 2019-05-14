package com.serivceImp;

import com.dao.LogMapper;
import com.models.Log;
import com.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 86183
 * @title: LogServiceImp
 * @projectName eapp-corp-project
 * @description: TODO  日志服务实现类
 * @date 2019/5/1313:32
 */
@Service
public class LogServiceImp implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public int recordInfo(Log log) {
        return logMapper.insert(log);
    }
}
