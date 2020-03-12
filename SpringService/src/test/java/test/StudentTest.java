package test;
import com.project.bean.StudentBean;
import com.project.service.IStudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class StudentTest {

    @Resource
    private IStudentService service;

    @Test
    public void test(){
        //service.add(new StudentBean("丽丽", "555"));
        //service.del(5);
        //service.update(5, "99");
        System.out.println(service.findAll());
    }


}
