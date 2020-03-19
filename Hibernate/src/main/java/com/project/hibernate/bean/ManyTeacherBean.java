package com.project.hibernate.bean;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * 用hibernate演示注解中间表
 */
@Entity
@Table(name = "m_teacher")
public class ManyTeacherBean {
    @Id
    @Column(name = "t_id",length = 32)
    @GenericGenerator(name = "uu",strategy = "uuid")
    @GeneratedValue(generator = "uu")
    private String id;
    @Column(name = "t_name",length = 48)
    private String name;

    @ManyToMany//多对多
    @JoinTable(name = "m_s_t",joinColumns = {@JoinColumn(name = "fk_s")},inverseJoinColumns = {@JoinColumn(name = "fk_t")})
    private Set<ManyStudentBean> studentSet;

    //因为另一个类中已经注解了中间表，所以这个类只引用就行了


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

    public Set<ManyStudentBean> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<ManyStudentBean> studentSet) {
        this.studentSet = studentSet;
    }
}
