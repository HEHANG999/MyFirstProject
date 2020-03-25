package com.project.service.impl;
import com.project.bean.UserBean;
import com.project.dao.UserDao;
import com.project.dto.UserDto;
import com.project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("userService")
@Transactional(rollbackFor = Exception.class)
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

}
