package com.project.hibernate.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * 教师实体类
 */
@Entity
@Table(name = "t_teacher")
public class TeacherBean {

    @Id
    @Column(name = "t_id",length = 32)
    @GenericGenerator(name = "uu",strategy = "uuid")
    @GeneratedValue(generator = "uu")
    private String id;
    @Column(name = "t_name",length = 48)
    private String name;
    @Column(name = "t_class",length = 48)
    private String clas;

    //mappedBy 把映射权利交给多的一方，表示把setStus这个属性的维护权交给---StudentBean中的"teacher"属性
    @OneToMany(mappedBy = "teacher")
    private Set<StudentBean> setStus;



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

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public Set<StudentBean> getSetStus() {
        return setStus;
    }

    public void setSetStus(Set<StudentBean> setStus) {
        this.setStus = setStus;
    }
}
