package com.project.service;

import com.project.bean.StudentBean;

import java.util.List;

public interface IStudentService {

    public void add(StudentBean student);

    public void del(int id);

    public void update(int id,String name);

    public List<StudentBean> findAll();
}
