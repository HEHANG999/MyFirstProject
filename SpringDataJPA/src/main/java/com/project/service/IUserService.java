package com.project.service;
import com.project.bean.UserBean;
import com.project.dto.UserDto;

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

}
