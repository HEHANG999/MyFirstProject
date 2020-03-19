package test;
import com.project.bibernate.tool.HSession;
import com.project.hibernate.bean.ManyStudentBean;
import com.project.hibernate.bean.ManyTeacherBean;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class ManyTeacher_ManyStudent_Test {
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
    public void addManyTeacher(){//添加老师何学生
        ManyTeacherBean teacher1 = new ManyTeacherBean();
        teacher1.setName("何老师");

        ManyTeacherBean teacher2 = new ManyTeacherBean();
        teacher2.setName("李老师");

        ManyTeacherBean teacher3 = new ManyTeacherBean();
        teacher3.setName("胡老师");

        ManyStudentBean student1 = new ManyStudentBean();
        student1.setName("小明");

        ManyStudentBean student2 = new ManyStudentBean();
        student2.setName("小浩");

        ManyStudentBean student3 = new ManyStudentBean();
        student3.setName("小莉");

        Set<ManyStudentBean> set = new HashSet<ManyStudentBean>();
        set.add(student1);
        set.add(student2);
        set.add(student3);
        teacher1.setStudentSet(set);//这里可以添加中间表！---注意：这里是保存老师的Set，所以中间表的维护权要交给老师实体类！

        session.save(teacher1);
        session.save(teacher2);
        session.save(teacher3);
        session.save(student1);
        session.save(student2);
        session.save(student3);
    }

    @Test
    public void addTeacherAndStudent(){//添加中间表，这里好像不能添加进去！
        ManyTeacherBean teacher = session.get(ManyTeacherBean.class, "4028821870f0d06d0170f0d06ef50000");

        ManyStudentBean student1 = session.get(ManyStudentBean.class, "4028821870f0d06d0170f0d06eff0003");

        ManyStudentBean student2 = session.get(ManyStudentBean.class, "4028821870f0d06d0170f0d06eff0004");

        ManyStudentBean student3 = session.get(ManyStudentBean.class, "4028821870f0d06d0170f0d06eff0005");

        Set<ManyStudentBean> set = new HashSet<ManyStudentBean>();
        set.add(student1);
        set.add(student2);
        set.add(student3);

        teacher.setStudentSet(set);

        session.save(teacher);
    }


    @Test
    public void find(){//根据老师查询所教学生
        ManyTeacherBean teacher = session.get(ManyTeacherBean.class, "4028821870f0eedb0170f0eedd300000");
        for (ManyStudentBean stu:teacher.getStudentSet()) {
            System.out.println(stu.getName());
        }
    }

}
