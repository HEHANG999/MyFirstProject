package com.project.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_role")
public class RoleBean {
    @Id
    @Column(name = "r_id", length = 32)
    @GenericGenerator(name = "rr", strategy = "uuid")
    @GeneratedValue(generator = "rr")
    private String id;
    private String name;
    private String info;
    private String status;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private UserBean user;


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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

}
