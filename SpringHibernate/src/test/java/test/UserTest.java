package test;
import com.project.bean.UserBean;
import com.project.service.IUserService;
import com.project.tool.BasicDao;
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class UserTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
    IUserService service = (IUserService) context.getBean("userService");

    @Test
    public void addUser(){//添加
        UserBean user = new UserBean();
        user.setUserName("小明");
        user.setLogName("xm");
        user.setLogPass("000");
        user.setStatus("在职");

        service.addUser(user);
    }

    @Test
    public void delUser(){//删除
        UserBean user = new UserBean();
        user.setId("ff808081710649ae01710649b1290000");

        service.delUser(user);
    }

    @Test
    public void delUserByID(){//按id删除

        service.delUserByID("ff808081710668c801710668ca530000");
    }

    @Test
    public void updateUser(){//修改
        UserBean user = new UserBean();
        user.setId("ff80808171066f030171066f04e90000");
        user.setUserName("小明2");
        user.setLogName("xm2");
        user.setLogPass("0002");
        user.setStatus("在职2");

        service.updateUser(user);
    }


    @Test
    public void findAll(){//查询所有
        List<UserBean> list = service.findAll();

        for (UserBean user : list) {
            System.out.println(user.getUserName());
        }

    }


    @Test
    public void findBy1(){//动态查询1
        UserBean user = service.findBy1("小", "xm2");

        System.out.println(user.getUserName()+";"+user.getLogPass());
    }

    @Test
    public void findBy2(){//动态查询2
        List<Map> list = service.findBy2("小", "xm2");

        for (Map map : list) {
            System.out.println(map.get("0")+";"+map.get("1"));
        }
    }

    @Test
    public void findBy3(){//动态查询3
        List<Map> list = service.findBy3("小", "xm2");

        for (Map map : list) {
            System.out.println(map.get("UserName")+";"+map.get("LogPass"));
        }
    }

    @Test
    public void findBy4(){//动态查询4
        List<Object[]> list = service.findBy4("小", "xm2");

        for (Object[] obj : list) {
            System.out.println(obj[0].toString()+";"+obj[1].toString());
        }
    }


    @Test
    public void testW(){//演示一级缓存----脏读情况

        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        BasicDao dao = (BasicDao) context.getBean("basicDao");

        dao.test();

    }


}
