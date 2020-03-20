package test;
import com.project.bibernate.tool.HSession;
import com.project.hibernate.bean.StudentBean;
import com.project.hibernate.bean.TeacherBean;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 演示SQL语句
 */
public class SQL {
    Session session = null;
    Transaction tr = null;

    @Before
    public void before(){
        session = HSession.createSession();
        tr = session.getTransaction();
        tr.begin();
        //System.out.println("事务开启了...");
    }
    @After
    public void after(){
        tr.commit();
        session.close();
        //System.out.println("事务关闭了...");
    }


    @Test
    public void findTeacher(){//将查询结果放入实体中
        String sql = "select * from t_teacher";
        //查询结果要与对应实体属性一一对应，不能是*（不知道哪个数据放到哪个属性里）
        //实体与数据表列名早已经映射好，不需要取别名

        NativeQuery query = session.createNativeQuery(sql);
        query.addEntity(TeacherBean.class);//把查询结果放到，被hibernate管理的实体中

        List<TeacherBean> list = query.list();
        for (TeacherBean t:list) {
            System.out.println(t.getName());
        }

    }


    @Test
    public void findTeacher2(){//把程序结果放入数组中
        String sql = "select t_name,t_class from t_teacher";

        List<Object[]> list = session.createNativeQuery(sql).list();

        for (Object[] obj:list) {
            System.out.println(obj[0]+";"+obj[1]);
        }

    }

}
