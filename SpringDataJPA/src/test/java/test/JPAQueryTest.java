package test;

import com.project.bean.UserBean;
import com.project.dao.UserDao;
import com.project.dto.UserDto;
import com.project.service.IUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class JPAQueryTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
    IUserService dao = (IUserService) context.getBean("userService");


    @Test
    public void findList(){
        List<UserBean> list = dao.getUserList("%在职%");

        for (UserBean userBean : list) {
            System.out.println(userBean.getUserName());
        }
    }


    @Test
    public void findList2(){
        List<UserBean> list = dao.getUserListParam("%在职%");

        for (UserBean userBean : list) {
            System.out.println(userBean.getUserName());
        }
    }

    @Test
    public void findListDto(){
        List<UserDto> list = dao.findListDto();

        for (UserDto user : list) {
            System.out.println(user.getName()+";"+user.getPass());
        }
    }

    @Test
    public void findListMap(){
        List<Map> list = dao.getListMap("xm2");

        System.out.println(list);
        for (Map map : list) {
            System.out.println(map.get("0")+";"+map.get("1"));
        }
    }





    @Test
    public void save(){//添加
        UserBean user = new UserBean();
        user.setUserName("88");
        user.setLogName("99");
        user.setLogPass("77");
        user.setStatus("5");

        dao.save(user);
    }

    @Test
    public void del(){//删除

        dao.del("4028ab7c7110ecbb017110ecbeba0000");
    }


    @Test
    public void findOptional(){
        UserBean user = dao.findOptional("ff80808171066f030171066f04e90000");

        System.out.println(user.getUserName());
    }


}
