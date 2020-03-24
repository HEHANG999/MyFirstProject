package com.project.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_user")
public class UserBean {
    @Id
    @Column(name = "u_id",length = 32)
    @GenericGenerator(name = "uu",strategy = "uuid")
    @GeneratedValue(generator = "uu")
    private String id;
    private String UserName;
    private String LogName;
    private String LogPass;
    private String status;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getLogName() {
        return LogName;
    }

    public void setLogName(String logName) {
        LogName = logName;
    }

    public String getLogPass() {
        return LogPass;
    }

    public void setLogPass(String logPass) {
        LogPass = logPass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
