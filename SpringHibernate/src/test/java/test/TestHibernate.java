package test;
import com.project.tool.BasicDao;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHibernate {

    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        BasicDao dao = (BasicDao) context.getBean("basicDao");

        System.out.println(dao.getOpenSession());
    }
}
