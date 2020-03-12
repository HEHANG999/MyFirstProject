package com.project.mapper;

import com.project.bean.StudentBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IStudentMapper {

    @Insert("insert into t_student values (null,#{name},#{phone})")
    public void add(StudentBean student);

    @Delete("delete from t_student where s_id = #{id}")
    public void del(int id);

    @Update("update t_student set s_name = #{param2} where s_id = #{param1}")
    public void update(int id,String name);

    @Select("select * from t_student")
    @ResultMap("studentMap")
    public List<StudentBean> findAll();
}
