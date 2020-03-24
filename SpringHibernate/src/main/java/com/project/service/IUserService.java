package com.project.service;

import com.project.bean.UserBean;

import java.util.List;
import java.util.Map;

public interface IUserService {

    ///添加
    public void addUser(UserBean user);

    //删除
    public void delUser(UserBean user);

    //按id删除
    public void delUserByID(String id);

    //修改
    public void updateUser(UserBean user);

    //查询所有
    public List<UserBean> findAll();

    //动态查询1
    public UserBean findBy1(String name,String logName);

    //动态查询2
    public List<Map> findBy2(String name, String logName);

    //动态查询3
    public List<Map> findBy3(String name,String logName);

    //动态查询4
    public List<Object[]> findBy4(String name,String logName);

}
