package com.project.hibernate.bean;

import javax.persistence.*;


@Entity//被hibernate管理的标记
@Table(name = "t_user")//映射表,不写name默认类名为表名，不写table就不会将实体映射到数据库
public class UserBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//生成策略---为mysql的自动增长
    private int id;

    private String name;//列名
    private String pass;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
