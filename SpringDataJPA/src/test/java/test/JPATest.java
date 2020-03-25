package test;

import com.project.tool.BasicDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JPATest {

    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        BasicDao dao = (BasicDao) context.getBean("basicDao");

        System.out.println(dao.getmanaged());
    }


}
