package com.project.dao;

import com.project.bean.UserBean;
import com.project.dto.UserDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
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


    //分页
    @Query("from UserBean where status=?1")
    public List<UserBean> getPageList(String status, PageRequest pageable);






    //SQL查询---nativeQuery = true表示原生态
    @Query(value = "select * from t_user where status=?1",nativeQuery = true)
    public List<UserBean> findSQLList(String status);

    //查询且转换
    @Query(value = "select LogName,LogPass from t_user where status=:ss",nativeQuery = true)
    public List<Object[]> findSQLObject(@Param("ss") String status);



    //删除
    @Query("delete from UserBean where UserName = ?1")
    @Modifying//删除、修改要加它
    public int delete(String name);

    //修改
    @Query("update from UserBean set status = ?1 where UserName =?2")
    @Modifying
    public int update(String status,String name);



    //关键字！

    //from UserBean where UserName=?
    //public List<UserBean> findByUserNameOrLogName(String userName,String logName);





}
