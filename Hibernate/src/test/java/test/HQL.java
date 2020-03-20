package test;
import com.project.bibernate.tool.HSession;
import com.project.bibernate.tool.ManyTeacherDto;
import com.project.hibernate.bean.ManyTeacherBean;
import com.project.hibernate.bean.StudentBean;
import com.project.hibernate.bean.TeacherBean;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * 演示HQL语句
 */
public class HQL {
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
    public void findStudent(){//HQL语句里没有“*”，因为HQL是面向（实体）对象的
        String hql = "select t from ManyTeacherBean t ";

        //获得query对象
        Query query = session.createQuery(hql);

        //查询所有
        List<Object> list = query.list();//其实可以用对象泛型来接受，如下面单元测试的简化
        for (Object o:list) {
            System.out.println(((ManyTeacherBean)o).getName());
        }

    }

    @Test
    public void findTeacher2(){//这个是上面的简化！
        String hql = "from ManyTeacherBean";

        List<ManyTeacherBean> list = session.createQuery(hql).list();//只有一个对象，泛型可以为对象

        for (ManyTeacherBean t:list) {
            System.out.println(t.getName());
        }
    }

    @Test
    public void findById(){//按id查询老师
        String hql = "from ManyTeacherBean where id = ?";

        List<ManyTeacherBean> list =
        session.createQuery(hql)
                .setParameter(0, "4028821870f0eedb0170f0eedd300000")//替换占位符
                //.setParameter(0, "")
                .list();

        for (ManyTeacherBean t:list) {
            System.out.println(t.getName());
        }
    }

    @Test
    public void findByName(){//返会多个查询结果，1、用Object[]数组装
        String hql = " select id,name from ManyTeacherBean where name = ?";

        List<Object[]> list =
        session.createQuery(hql).setParameter(0, "何老师").list();

        for (Object[] obj:list) {
            System.out.println(obj[0].toString()+";"+obj[1].toString());
        }

    }

    @Test
    public void findByName2(){//2、用Dto类来装数据
        String hql = " select new com.project.bibernate.tool.ManyTeacherDto(id,name) " +
                     " from ManyTeacherBean where name = ?";

        List<ManyTeacherDto> list = session.createQuery(hql).setParameter(0, "何老师").list();

        for (ManyTeacherDto dto:list) {
            System.out.println(dto.getId()+";"+dto.getName());
        }

    }


    @Test
    public void findByName3(){//3、用Map来装数据
        String hql = " select new map(id,name) " +
                " from ManyTeacherBean where name like '%何%'";

        List<Map> list = session.createQuery(hql).list();

        for (Map map:list){
            System.out.println(map.get("0")+";"+map.get("1"));
        }

    }

    @Test
    public void findLike(){//HQL模糊查询

        /*String hql = " select new map(id,name) " +
                " from ManyTeacherBean where name like '%何%'";//方法一

        List<Map> list = session.createQuery(hql).list();*/

        String hql = " select new map(id,name) " +
                " from ManyTeacherBean where name like ?";//方法二

        List<Map> list = session.createQuery(hql).setParameter(0, "%何%").list();

        for (Map map:list){
            System.out.println(map.get("0")+";"+map.get("1"));
        }
    }







    @Test
    public void findJoin(){//HQL联表查询
        String hql = "select new Map(s.name,t.name,t.clas) from StudentBean s left join s.teacher t where t.name=?";

        List<Map> list = session.createQuery(hql).setParameter(0, "陈老师").list();

        for (Map map:list) {
            System.out.println(map.get("0"));
        }

    }


    @Test
    public void findPage(){//演示HQL分页查询
        int currPage = 1;
        int number = 2;

        String hql = "from StudentBean";

        List<StudentBean> list = session.createQuery(hql)
                .setFirstResult((currPage-1)*number)//起始位置
                .setMaxResults(number)//每页显示行数
                .list();

        for (StudentBean stu:list) {
            System.out.println(stu.getName());
        }

    }



    @Test
    public void findObj(){//查询对象
        String hql = "from TeacherBean where name=?";

        TeacherBean t = (TeacherBean) session.createQuery(hql)
                .setParameter(0, "陈老师").uniqueResult();//表示唯一对象

        System.out.println(t.getName());

    }


    @Test
    public void del(){//删除
        String hql = "delete from StudentBean where name=?";

        int i = session.createQuery(hql)
                .setParameter(0, "小子3").executeUpdate();//表示执行修改、删除后返回受影响的行数

        System.out.println(i);

    }

    @Test
    public void update(){//修改
        String hql = "update StudentBean set name = ? where name = ?";

        int i = session.createQuery(hql)
                .setParameter(0, "小子22")
                .setParameter(1, "小子22").executeUpdate();

        System.out.println(i);

    }


}
