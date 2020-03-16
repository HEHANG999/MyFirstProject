package test;
import com.project.bibernate.tool.HSession;
import com.project.hibernate.bean.RoleBean;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class RoleTest {

    @Test
    public void add(){
        Session session = HSession.createSession();
        Transaction tr = session.getTransaction();

        for (int i=0;i<5;i++) {
            tr.begin();

            RoleBean role = new RoleBean();
            role.setName("张三"+i);

            session.save(role);
            tr.commit();
        }

        session.close();

    }



}
