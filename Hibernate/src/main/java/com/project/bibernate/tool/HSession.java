package com.project.bibernate.tool;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

//这里最好不需要记，因为每个hibernate版本不一样
public class HSession {

    static SessionFactory sessionFactory;

    static {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure("bibernate.xml").build();
            try {
                sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            }
            catch (Exception e) {
                StandardServiceRegistryBuilder.destroy( registry );
            }
    }


    public static Session createSession(){
        return sessionFactory.openSession();
    }


}
