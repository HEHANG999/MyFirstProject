package com.project.tool;
import org.hibernate.engine.spi.Managed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Transactional
@Repository("basicDao")
public class BasicDao {

    @Autowired
    private LocalContainerEntityManagerFactoryBean managerFactoryBean;

    public EntityManager getmanaged(){
        //获得工厂
        EntityManagerFactory managerFactory = managerFactoryBean.getNativeEntityManagerFactory();

        return managerFactory.createEntityManager();
    }

}
