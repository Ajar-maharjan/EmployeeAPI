package com.e.classworkvoli.model;

public class EmployeeCUD {
    private int id;
    private String name;
    private float salary;
    private String profile_image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public EmployeeCUD(int id, String name, float salary, String profile_image) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.profile_image = profile_image;
    }
}