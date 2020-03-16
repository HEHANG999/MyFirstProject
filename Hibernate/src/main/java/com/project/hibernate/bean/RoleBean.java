package com.project.hibernate.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 角色实体类
 * 展示用hibernate生成UUID
 */
@Entity
@Table(name = "t_role")
public class RoleBean {

    @Id
    //设置表中列的属性
    @Column(length = 32)//--这里一定要是32
    //自定义UUID
    @GenericGenerator(name = "ruuid",strategy = "uuid")
    //给id一个生成策略，自定义生成策略
    @GeneratedValue(generator = "ruuid")
    private String id;

    @Column(name = "r_name",length = 48,nullable = false,unique = true)
    private String name;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
