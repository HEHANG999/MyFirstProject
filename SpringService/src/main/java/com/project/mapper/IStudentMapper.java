package com.project.mapper;

import com.project.bean.StudentBean;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IStudentMapper {

    @Select("select * from t_student")
    @ResultMap("studentMap")
    public List<StudentBean> findAll();
}
