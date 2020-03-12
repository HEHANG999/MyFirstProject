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
    public void add(StudentBean student) {
        //int a = 9/0;
        mapper.add(student);
    }

    @Override
    public void del(int id) {
        mapper.del(id);
    }

    @Override
    public void update(int id, String name) {
        mapper.update(id, name);
    }

    @Override
    public List<StudentBean> findAll() {
        return mapper.findAll();
    }
}
