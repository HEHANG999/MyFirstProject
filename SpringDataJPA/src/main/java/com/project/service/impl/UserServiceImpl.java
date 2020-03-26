package com.project.service.impl;
import com.project.bean.UserBean;
import com.project.dao.UserDao;
import com.project.dto.UserDto;
import com.project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service(value = "userService")
//@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao dao;

    @Override
    public List<UserBean> getUserList(String status) {
        return dao.getUserList(status);
    }

    @Override
    public List<UserBean> getUserListParam(String status) {
        return dao.getUserListParam(status);
    }

    @Override
    public List<UserDto> findListDto() {
        return dao.findListDto();
    }

    @Override
    public List<Map> getListMap(String logname) {
        return dao.getListMap(logname);
    }

    @Override
    public UserBean save(UserBean user) {
        return dao.save(user);
    }

    @Override
    public void del(String id) {
        dao.deleteById(id);
    }

    @Override
    public UserBean findOptional(String id) {
        Optional<UserBean> user = dao.findById(id);

        return user.get();
    }

    @Override
    public void addList(List<UserBean> list) {
        dao.saveAll(list);
    }

    @Override
    public List<UserBean> getPageList(String status, PageRequest pageable) {
        return dao.getPageList(status, pageable);
    }

    @Override
    public List<UserBean> findSQLList(String status) {
        return dao.findSQLList(status);
    }

    @Override
    public List<UserBean> findSQLObject(String status) {
         List<Object[]> list = dao.findSQLObject(status);

         List<UserBean> list2= null;

         //如果查询结果不为空
         if (!list.isEmpty() && null!=list){
             list2 = new ArrayList<>();

             //把结果放入到list<UserBean>
             for (Object[] objs:list){
                 UserBean user = new UserBean();
                 user.setUserName(objs[0].toString());
                 user.setLogPass(objs[1].toString());

                 list2.add(user);
             }
         }

         return list2;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(String name) {
        return dao.delete(name);
    }

    @Override
    public int update(String status, String name) {
        return dao.update(status, name);
    }

   /* @Override
    public List<UserBean> findByUserNameOrLogName(String userName,String logName) {
        return dao.findByUserNameOrLogName(userName,logName);
    }*/

}
