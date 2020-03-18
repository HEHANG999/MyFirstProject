package com.project.hibernate.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 学生实体类
 */
@Entity
@Table(name = "t_student")
public class StudentBean {

    @Id
    @Column(name = "s_id",length = 32)
    @GenericGenerator(name = "ss",strategy = "uuid")
    @GeneratedValue(generator = "ss")
    private String id;
    @Column(name = "s_name",length = 48)
    private String name;
    @Column(name = "s_class",length = 48)
    private String clas;

    @ManyToOne//(cascade = CascadeType.ALL)//级联
    //@ManyToOne(fetch = FetchType.LAZY)//修改为懒加载
    @JoinColumn(name = "fk_teacher")//外键
    private TeacherBean teacher;


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

    public TeacherBean getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherBean teacher) {
        this.teacher = teacher;
    }
}
