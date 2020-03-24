package com.project.bean;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * 用hibernate演示注解中间表
 */
@Entity
@Table(name = "m_student")
public class ManyStudentBean {
    @Id
    @Column(name = "s_id",length = 32)
    @GenericGenerator(name = "ss",strategy = "uuid")
    @GeneratedValue(generator = "ss")
    private String id;
    @Column(name = "s_name",length = 48)
    private String name;

    @ManyToMany(mappedBy = "studentSet")
    private Set<ManyTeacherBean> teacherSet;

    //注解中间表
    //joinColumns = {@JoinColumn(name = "fk_s")}----表示把当前类注解到中间表中，名为fk_s
    //inverseJoinColumns = {@JoinColumn(name = "fk_t")}---表示把TeacherBean类注解到中间表，名为fk_t


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

    public Set<ManyTeacherBean> getTeacherSet() {
        return teacherSet;
    }

    public void setTeacherSet(Set<ManyTeacherBean> teacherSet) {
        this.teacherSet = teacherSet;
    }
}
