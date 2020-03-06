package com.project.service.impl;

import com.project.bean.StudentBean;
import com.project.mapper.IStudentMapper;
import com.project.service.IStudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {

    @Resource
    private IStudentMapper mapper;

    @Override
    public List<StudentBean> findAll() {
        return mapper.findAll();
    }
}
