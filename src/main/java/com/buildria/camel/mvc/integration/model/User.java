package com.buildria.camel.mvc.integration.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public class User {

    @NotNull
    @Size(min = 3, max = 3)
    private String id;

    @NotNull
    @Size(min = 1)
    private String name;

    @NotNull
    @Range(min = 10, max = 99)
    private int age;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User() {
    }

    public User(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", age=" + age + '}';
    }
    
}
