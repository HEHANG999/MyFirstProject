package com.project.tool;
import com.project.bean.UserBean;
import org.hibernate.Session;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository(value = "basicDao")
public class BasicDao<T> {//加泛型，用于封装对象

    Class<T> cl = null;

    public BasicDao() {
        cl = ReflectUtils.getClassGenricType(this.getClass());
    }

    @Autowired
    private LocalSessionFactoryBean factoryBean;

    //hibernate提供的session---需要手动管理事务，关闭连接
    public Session getOpenSession(){
        return factoryBean.getObject().openSession();
    }

    //是被spring管理的session---不需要手动管理，但需要spring的事务支持，关闭session由spring来做
    public Session getCurrentSession(){
        return factoryBean.getObject().getCurrentSession();
    }


    //添加
    public void saveObj(T t){
        this.getCurrentSession().save(t);
    }

    //删除
    public void deleteObj(T t){
        this.getCurrentSession().delete(t);
    }

    //按id删除
    public void deleteObjByID(String id){
        Object obj = this.getCurrentSession().get(cl, id);
        this.getCurrentSession().delete(obj);
    }

    //修改
    public void updateObj(T t){
        this.getCurrentSession().update(t);
    }

    //查询所有
    public List<T> findAll(){
        String hql = "from "+cl.getSimpleName();
        return this.getCurrentSession().createQuery(hql).list();
    }

    //动态查询1----hql查询一个对象
    public UserBean findByOther(String name,String logName){
        String hql = "from UserBean where Username like ? and logName=?";

        return (UserBean) this.getCurrentSession()
                .createQuery(hql)
                .setParameter(0, "%"+name+"%")
                .setParameter(1, logName)
                .uniqueResult();
    }

    //动态查询2----hql查询数据放入map
    public List<Map> findByOtherMap(String name,String logName){
        String hql = "select new Map(UserName,LogPass) from UserBean where UserName like? and LogName=?";

        return  this.getCurrentSession()
                .createQuery(hql)
                .setParameter(0, "%"+name+"%")
                .setParameter(1, logName)
                .list();
    }

    //动态查询3----sql查询数据放入map
    public List<Map> findByOtherMap2(String name,String logName){
        String sql = "select UserName,LogPass from t_user where UserName like? and LogName=?";

        return  this.getCurrentSession()
                .createNativeQuery(sql)
                .setParameter(1, "%"+name+"%")
                .setParameter(2, logName)
                .unwrap(NativeQueryImpl.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .list();
    }

    //动态查询4----hql查询数据放入数组
    public List<Object[]> findByOtherMap3(String name,String logName){
        String hql = "select UserName,LogPass from UserBean where UserName like? and LogName=?";

        return  this.getCurrentSession()
                .createQuery(hql)
                .setParameter(0, "%"+name+"%")
                .setParameter(1, logName)
                .list();
    }


    //演示一级缓存----脏读情况
    public void test(){
        Session session = this.getOpenSession();

        UserBean user1 = session.get(UserBean.class, "ff80808171066f030171066f04e90000");
        System.out.println(user1.getUserName());

        //休眠10秒
        try {
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        UserBean user2 = session.get(UserBean.class, "ff80808171066f030171066f04e90000");
        System.out.println(user2.getUserName());

        UserBean user3 = session.get(UserBean.class, "ff80808171066f030171066f04e90000");
        System.out.println(user3.getUserName());
    }


}
