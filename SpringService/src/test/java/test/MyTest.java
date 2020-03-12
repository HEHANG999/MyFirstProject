package test;
import com.project.service.IStudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    public static void main(String[] args) {
        ApplicationContext context = new  ClassPathXmlApplicationContext("applicationContext.xml");

        IStudentService service = (IStudentService) context.getBean("studentServiceImpl");

        System.out.println(service.findAll());
    }
}
