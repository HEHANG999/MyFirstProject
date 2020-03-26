package test;

import com.project.bean.UserBean;
import com.project.dao.UserDao;
import com.project.dto.UserDto;
import com.project.service.IUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.ArrayList;
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

    @Test
    public void addList(){
        List<UserBean> list = new ArrayList<UserBean>();

        for (int i = 0; i < 10; i++) {

            UserBean user = new UserBean();
            user.setUserName("涛涛"+i);
            user.setLogName("tt"+i);
            user.setLogPass("77"+i);
            user.setStatus("在职");

            list.add(user);
        }

        dao.addList(list);
    }


    @Test
    public void findPage(){//分页查询
        PageRequest pageable = PageRequest.of(0, 5);

        List<UserBean> list = dao.getPageList("在职", pageable);

        for (UserBean userBean : list) {
            System.out.println(userBean.getUserName());
        }
    }








    @Test
    public void findListSQL(){//SQL查询
        List<UserBean> list = dao.findSQLList("在职");

        for (UserBean userBean : list) {
            System.out.println(userBean.getUserName());
        }
    }



    @Test
    public void findListObject(){//查询且转换
        List<UserBean> list = dao.findSQLObject("在职");

        for (UserBean userBean : list) {
            System.out.println(userBean.getUserName()+";"+userBean.getLogPass()+";"+userBean.getLogName());
        }
    }



    @Test
    public void delete(){//删除
        int i = dao.delete("涛涛0");

        System.out.println(i);
    }

    @Test
    public void update(){//修改
        int i = dao.update("在职00", "涛涛1");

        System.out.println(i);
    }






    //关键字!

   /* @Test
    public void findByUserName(){
        List<UserBean> list = dao.findByUserNameOrLogName("小明","xm");

        for (UserBean userBean : list) {
            System.out.println(userBean.getUserName());
        }
    }*/


}
