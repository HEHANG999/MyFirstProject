package test;

import com.project.bean.UserBean;
import com.project.dto.UserDto;
import com.project.tool.BasicDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class UserTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
    BasicDao dao = (BasicDao) context.getBean("basicDao");



    @Test
    public void findList(){//HQL查询集合

        List<UserBean> list = dao.findByHQL();

        for (UserBean userBean : list) {
            System.out.println(userBean.getUserName());
        }
    }

    @Test
    public void findOne(){//HQL查询对象
        UserBean user = dao.findOne();
        System.out.println(user.getUserName());
    }

    @Test
    public void findMap(){//HQL查询map
        List<Map> list = dao.findMap();

        for (Map map : list) {
            System.out.println(map.get("0")+";"+map.get("1"));
        }
    }

    @Test
    public void findListDto(){//HQL查询list放入Dto
        List<UserDto> list = dao.findListDto();

        for (UserDto userDto : list) {
            System.out.println(userDto.getName()+";"+userDto.getPass());
        }
    }








    @Test
    public void findListBySQL(){//SQL查询list

        List<UserBean> list = dao.findListBySQL();

        for (UserBean userBean : list) {
            System.out.println(userBean.getUserName());
        }
    }

    @Test
    public void findOneBySQL(){//SQL查询对象
        UserBean user = dao.findOneBySQL();
        System.out.println(user.getUserName());
    }

    @Test
    public void findMapBySQL(){//HQL查询map
        List<Map> list = dao.findMapBySQL();

        for (Map map : list) {
            System.out.println(map.get("LogName")+";"+map.get("LogPass"));
        }
    }



}
