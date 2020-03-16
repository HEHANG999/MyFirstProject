package test;
import com.project.bibernate.tool.HSession;
import com.project.hibernate.bean.UserBean;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class UserTest {

    //@Test
    public void test1(){
        System.out.println(HSession.createSession());
    }


    @Test
    public void test2(){
        UserBean user = new UserBean();
        //user.setId(1);
        user.setName("丽丽");
        user.setPass("777");
        user.setAge(20);

        //获得session
        Session s = HSession.createSession();
        //获取事务
        Transaction tr = s.getTransaction();
        //开启事务
        tr.begin();
        //执行保存
        s.save(user);
        //提交事务
        tr.commit();
        //关闭session
        s.close();

    }


    //@Test
    public void findUser(){
        Session s = HSession.createSession();
        UserBean user = s.get(UserBean.class, 1);
        System.out.println(user.getName());
        s.close();
    }


    //@Test
    public void update(){
        Session s = HSession.createSession();
        Transaction tr = s.getTransaction();
        tr.begin();

        UserBean user = s.get(UserBean.class, 1);
        user.setName("可可");
        //修改
        s.update(user);

        tr.commit();
        s.close();
    }

    //@Test
    public void delete(){
        Session s = HSession.createSession();
        Transaction tr = s.getTransaction();
        tr.begin();

        UserBean user = new UserBean();
        user.setId(1);

        //删除
        s.delete(user);

        tr.commit();
        s.close();
    }

}
