package com.project.dao;
import com.project.tool.BasicDao;
import com.project.bean.UserBean;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao extends BasicDao<UserBean> {
}
