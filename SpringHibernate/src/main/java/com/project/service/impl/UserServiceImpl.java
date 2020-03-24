package com.project.service.impl;
import com.project.dao.UserDao;
import com.project.tool.BasicDao;
import com.project.bean.UserBean;
import com.project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service(value = "userService")
@Transactional(rollbackFor = {Exception.class})//事务---异常时回滚
public class UserServiceImpl implements IUserService {

    @Autowired
    //BasicDao dao;
    UserDao userDao;

    @Override
    public void addUser(UserBean user) {
        //int a = 9/0;
        //dao.getCurrentSession().save(user);//下面有封装的简化
        userDao.saveObj(user);
    }

    @Override
    public void delUser(UserBean user) {
        userDao.deleteObj(user);
    }

    @Override
    public void delUserByID(String id) {
        userDao.deleteObjByID(id);
    }


    @Override
    public void updateUser(UserBean user) {
        userDao.updateObj(user);

    }

    @Override
    @Transactional(readOnly = true)
    public List<UserBean> findAll() {
        return userDao.findAll();
    }

    @Override
    public UserBean findBy1(String name, String logName) {
        return userDao.findByOther(name, logName);
    }

    @Override
    public List<Map> findBy2(String name, String logName) {
        return userDao.findByOtherMap(name, logName);
    }

    @Override
    public List<Map> findBy3(String name, String logName) {
        return userDao.findByOtherMap2(name, logName);
    }

    @Override
    public List<Object[]> findBy4(String name, String logName) {
        return userDao.findByOtherMap3(name, logName);
    }

}
