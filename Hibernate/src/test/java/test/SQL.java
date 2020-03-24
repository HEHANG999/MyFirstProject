package test;
import com.project.tool.HSession;
import com.project.tool.ManyTeacherDto;
import com.project.bean.StudentBean;
import com.project.bean.TeacherBean;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

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
    public void findTeacher(){//将总查询结果放入实体中
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
    public void findTeacher2(){//1、把查询结果放入数组中
        String sql = "select t_name,t_class from t_teacher";

        List<Object[]> list = session.createNativeQuery(sql).list();

        for (Object[] obj:list) {
            System.out.println(obj[0]+";"+obj[1]);
        }

    }


    @Test
    public void findTeacher3(){//2、把查询结果放入Map中----sql不是面向对象的，不能在语句里new Map
        String sql = "select t_name,t_class from t_teacher where t_class=?";

        List<Map> list = session.createNativeQuery(sql)//将结果集封装
                .setParameter(1, "J174")
                .unwrap(NativeQueryImpl.class)//拆包
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)//放入map中
                .list();

        for (Map map : list) {
            System.out.println(map.get("t_name")+";"+map.get("t_class"));//sql语句中的map保存的键，是列名
        }
    }


    @Test
    public void findTeacher4(){//3、把查询结果放入Dto中-----sql语句的Dto因为反射，需要取别名
        String sql = "select t_id as id,t_name as name from t_teacher where t_class=?";

        List<ManyTeacherDto> list = session.createNativeQuery(sql)//将结果集封装
                .setParameter(1, "J174")
                .unwrap(NativeQueryImpl.class)//拆包
                .setResultTransformer(Transformers.aliasToBean(ManyTeacherDto.class))//放入map中
                .list();

        for (ManyTeacherDto Dto : list) {
            System.out.println(Dto.getName());
        }
    }


    @Test
    public void findTeacherAndStudent(){//sql联表查询
        String sql = "select s.s_name,t.t_name,t.t_class from t_student s left join t_teacher t on s.fk_teacher=t.t_id where t.t_name=?";

        List<Map> list = session.createNativeQuery(sql)
                .setParameter(1, "陈老师")
                .unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .list();

        for (Map map : list) {
            System.out.println(map.get("s_name")+";"+map.get("t_name")+";"+map.get("t_class"));
        }

    }


    @Test
    public void findObjs(){//联表查询多个结果
        String sql = "select t.*,s.* from t_student s left join t_teacher t on s.fk_teacher=t.t_id where t.t_name=?";

        List<Object[]> list =
                session.createNativeQuery(sql)
                        .addEntity("t", TeacherBean.class)
                        .addEntity("s", StudentBean.class)
                        .setParameter(1, "陈老师")
                        .list();

        for (Object[] o : list) {
            TeacherBean teacher = (TeacherBean)o[0];
            StudentBean student = (StudentBean)o[1];

            System.out.println(teacher.getName()+";"+student.getName());
        }

    }



    @Test
    public void findPage(){//sql分页查询（1）
        String sql = "select * from t_student";

        List<StudentBean> list = session.createNativeQuery(sql)
                .addEntity(StudentBean.class)
                .setFirstResult(0)
                .setMaxResults(2)
                .list();

        for (StudentBean stu : list) {
            System.out.println(stu.getName());
        }
    }

    @Test
    public void findPage2(){//sql分页查询（2）
        String sql = "select * from t_student limit 0,2";

        List<StudentBean> list = session.createNativeQuery(sql)
                .addEntity(StudentBean.class)
                .list();

        for (StudentBean stu : list) {
            System.out.println(stu.getName());
        }
    }


    @Test
    public void findObj(){//sql查询一个对象
        String sql = "select * from t_teacher where t_name =?";

        TeacherBean teacher = (TeacherBean) session.createNativeQuery(sql)
                .addEntity(TeacherBean.class)
                .setParameter(1, "陈老师")
                .uniqueResult();//表示返回唯一结果---错误出现NonUniqueResultException异常

        System.out.println(teacher.getName());
    }



    @Test
    public void findObj2(){//sql查询多个对象
        String sql = "select * from t_student";

        List<StudentBean> list = session.createNativeQuery(sql)
                .addEntity(StudentBean.class)
                .list();

        for (StudentBean stu : list) {
            System.out.println(stu.getName());
        }

    }






    @Test
    public void del(){//删除
        String sql = "delete from t_student where s_name=?";

        int i = session.createNativeQuery(sql)
                .setParameter(1, "小子3")
                .executeUpdate();//表示执行修改、删除后返回受影响的行数

        System.out.println(i);
    }

    @Test
    public void update(){//修改
        String sql = "update t_student set s_name = ? where s_name=?";

        int i = session.createNativeQuery(sql)
                .setParameter(1, "小子2")
                .setParameter(2, "小子22")
                .executeUpdate();

        System.out.println(i);
    }



}
