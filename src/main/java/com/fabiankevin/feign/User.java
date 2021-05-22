package com.fabiankevin.feign;


import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class User {

    @SerializedName(value = "id")
    private Long id;
    @SerializedName(value = "name")
    private String name;
    @SerializedName(value = "birthDate")
    private String birthDate;
    private Integer age;
//    private LocalDate birthDate;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", age=" + age +
                '}';
    }
}