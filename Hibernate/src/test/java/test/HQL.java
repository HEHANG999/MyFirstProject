package test;
import com.project.bibernate.tool.HSession;
import com.project.bibernate.tool.ManyTeacherDto;
import com.project.hibernate.bean.ManyTeacherBean;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * 演示用HQL查询
 */
public class HQL {
    Session session = null;
    Transaction tr = null;

    @Before
    public void before(){
        session = HSession.createSession();
        tr = session.getTransaction();
        tr.begin();
    }
    @After
    public void after(){
        tr.commit();
        session.close();
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

}
