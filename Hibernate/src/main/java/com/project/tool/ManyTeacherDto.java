package com.project.tool;

/**
 * 用于保存数据
 */
public class ManyTeacherDto {

    private String id;
    private String name;

    public ManyTeacherDto() {
    }

    public ManyTeacherDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

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
