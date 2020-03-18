package com.project.bibernate.tool;

import com.project.hibernate.bean.RoleBean;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

public class HEntity {

    Session session = null;

    @Before
    public void before(){
        session = HSession.createSession();
    }

    //@Test
    public void save(){
        //瞬时状态
        RoleBean role = new RoleBean();
        role.setName("教师");
        //操作数据库
        Transaction tr = session.getTransaction();
        tr.begin();
        //---------------------------持久状态
        session.save(role);//保存数据库中（DB）
        //操作持久状态
        role.setName("学生");//修改的是缓存（一级缓存）中的数据

        tr.commit();//提交时，缓存会和数据库同步，若不一致就会update数据库----【前提有事务Transaction支持】
        //---------------------------持久状态
        session.close();
        //游离状态---与数据库断开了连接，session缓存也没有，这时改变数据不会同步数据库了
    }

    //@Test
    public void test(){
        //Transaction tr = session.getTransaction();
        //tr.begin();

        //持久状态---session没有关闭，数据库没有关闭
        RoleBean r = session.get(RoleBean.class, "4028821870e66d440170e66d46d70000");
        //改变持久状态------这里【没有事务支持，不能修改】
        r.setName("教师");

        //tr.commit();
        session.close();
    }

    //====================================================================================================

    //急加载
    @Test
    public void get(){
        RoleBean r = session.get(RoleBean.class, "4028821870e66d440170e66d46d70000");//直接查找数据库，并将返回数据放入缓存！
        System.out.println(r.getName());//从缓存中查找---可以查找
        session.close();//与数据库断开连接
        System.out.println(r.getName());//从缓存中查找---可以查找
    }


    //懒加载
    @Test
    public void load(){
        RoleBean r = session.load(RoleBean.class, "4028821870e66d440170e66d46d70000");//放入缓存，不会查找数据库！
        System.out.println(r.getName());//从数据库中查找，再把信息放入缓存（内存）------存在一级缓存

        session.close();//与数据库断开连接（no-session）

        System.out.println(r.getName());//从缓存中查找！！---可以查找


        /*RoleBean r2 = session.load(RoleBean.class, "4028821870e66d440170e66d46d70000");//放入缓存
        session.close();//与数据库断开连接（no-session）
        System.out.println(r.getName());//从缓存中查找！！-----不存在一级缓存，所以查找不到
        */
    }

}
