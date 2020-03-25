package com.project.tool;
import com.project.bean.UserBean;
import com.project.dto.UserDto;
import org.hibernate.engine.spi.Managed;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

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


    //HQL查询集合
    public List<UserBean> findByHQL(){
        String hql = "from UserBean where status=?9";//参数写法

        return this.getmanaged().createQuery(hql)
                .setParameter(9, "在职")
                .getResultList();
    }

    //HQL查询对象
    public UserBean findOne(){
        String hql = "from UserBean where LogName=:nam";//参数写法

        return (UserBean) this.getmanaged().createQuery(hql)
                .setParameter("nam","xm2")
                .getSingleResult();
    }

    //HQL查询map
    public List<Map> findMap(){
        String hql = "select new Map(UserName,LogPass) from UserBean where LogName=:lam";

        return this.getmanaged().createQuery(hql)
                .setParameter("lam", "xm2")
                .getResultList();
    }

    //HQL查询list放入Dto
    public List<UserDto> findListDto(){
        String hql = "select new  com.project.dto.UserDto(LogName,LogPass) from UserBean";

        return this.getmanaged().createQuery(hql).getResultList();
    }






    //SQL查询List
    public List<UserBean> findListBySQL(){
        String sql = "select * from t_user where status like:job";

        EntityManager entityManager = this.getmanaged();

        NativeQuery query = (NativeQuery) entityManager.createNativeQuery(sql);

        return  query
                .addEntity(UserBean.class)
                .setParameter("job", "%在职%")
                .getResultList();
    }

    //SQL查询对象
    public UserBean findOneBySQL(){
        String sql = "select * from t_user where LogName =?5";

        //把javax中的query强转为NativeQuery，才能有addEntityt()实体封装
        NativeQuery query = (NativeQuery) this.getmanaged().createNativeQuery(sql);

        return (UserBean) query
                .addEntity(UserBean.class)
                .setParameter(5, "xm2")
                .getSingleResult();
    }

    //SQL查询map
    public List<Map> findMapBySQL(){
        String sql = "select LogName,LogPass from t_user where LogName=?";

        return this.getmanaged().createNativeQuery(sql)
                .setParameter(1, "xm2")
                .unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .list();
    }







}
