package com.project.dao;

import com.project.bean.UserBean;
import com.project.dto.UserDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;


public interface UserDao extends CrudRepository<UserBean,String> {//这里面写if语句就难了

    @Query("from UserBean where status like ?1")
    public List<UserBean> getUserList(String status);

    @Query("from UserBean where status like :sta")
    public List<UserBean> getUserListParam(@Param("sta") String status);


    @Query("select new com.project.dto.UserDto(LogName,LogPass) from UserBean ")
    public List<UserDto> findListDto();

    @Query("select new Map(UserName,LogPass) from UserBean where LogName=?1")
    public List<Map> getListMap(String logname);



}
