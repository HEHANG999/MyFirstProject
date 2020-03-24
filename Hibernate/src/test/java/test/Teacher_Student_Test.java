package test;
import com.project.tool.HSession;
import com.project.bean.StudentBean;
import com.project.bean.TeacherBean;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Teacher_Student_Test {
    Session session = null;
    Transaction tr = null;

    @Before
    public void before(){
        session = HSession.createSession();
        tr = session.getTransaction();
        tr.begin();
    }

    @Test
    public void addT(){//添加主表
        TeacherBean teacher = new TeacherBean();
        teacher.setName("陈老师");
        teacher.setClas("J174");

        session.save(teacher);
    }


    @After
    public void after(){
        tr.commit();
        session.close();
    }


    @Test
    public void manyToOne(){//添加子表
        /*TeacherBean teacher = new TeacherBean();
        teacher.setId("");*/
        TeacherBean teacher = session.get(TeacherBean.class, "4028821870ec62350170ec6238510000");

        //保存三个学生
        StudentBean student1 = new StudentBean();
        student1.setName("小子1");
        student1.setTeacher(teacher);
        student1.setClas(teacher.getClas());

        StudentBean student2 = new StudentBean();
        student2.setName("小子2");
        student2.setTeacher(teacher);
        student2.setClas(teacher.getClas());

        StudentBean student3 = new StudentBean();
        student3.setName("小子3");
        student3.setTeacher(teacher);
        student3.setClas(teacher.getClas());

        session.save(student1);
        session.save(student2);
        session.save(student3);

    }


    //@Test
    public void casaAdd(){//级联添加，同时添加主表和子表
        //瞬时状态
        TeacherBean teacher = new TeacherBean();
        teacher.setName("张老师");
        teacher.setClas("J174");

        //保存三个学生
        StudentBean student1 = new StudentBean();
        student1.setName("小子1");
        student1.setTeacher(teacher);
        student1.setClas(teacher.getClas());

        StudentBean student2 = new StudentBean();
        student2.setName("小子2");
        student2.setTeacher(teacher);
        student2.setClas(teacher.getClas());

        StudentBean student3 = new StudentBean();
        student3.setName("小子3");
        student3.setTeacher(teacher);
        student3.setClas(teacher.getClas());

        //级联保存
        session.save(student1);//这里会先添加主表，再添加子表
        session.save(student2);
        session.save(student3);
    }


    //@Test
    public void del(){
        //测试级联添加后的负影响
        session.delete(session.get(StudentBean.class, "4028821870ec4e9b0170ec4e9df90003"));
    }

    @Test
    public void OneToManyFind(){//查看教师信息，同时查看教师所教的学生姓名
        TeacherBean teacher = session.get(TeacherBean.class, "4028821870ec62350170ec6238510000");
        for (StudentBean stu:teacher.getSetStus()){
            System.out.println(stu.getName());
        }
    }







}
