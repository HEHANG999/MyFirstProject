package com.project.service;
import com.project.bean.UserBean;
import com.project.dto.UserDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

public interface IUserService {

    public List<UserBean> getUserList(String status);

    public List<UserBean> getUserListParam(String status);


    public List<UserDto> findListDto();

    public List<Map> getListMap(String logname);

    public UserBean save(UserBean user);

    public void del(String id);

    public UserBean findOptional(String id);

    public void addList(List<UserBean> list);

    public List<UserBean> getPageList(String status, PageRequest pageable);

    public List<UserBean> findSQLList(String status);

    public List<UserBean> findSQLObject(@Param("ss") String status);

    public int delete(String name);

    public int update(String status,String name);

    //public List<UserBean> findByUserNameOrLogName(String userName,String logName);

}
