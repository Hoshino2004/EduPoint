package com.mycompany.qldsv.models;

import javafx.beans.property.SimpleStringProperty;
public class Student {

    private final SimpleStringProperty id;
    private final SimpleStringProperty mssv;
    private final SimpleStringProperty name;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty dob;
    private final SimpleStringProperty className;
    private final SimpleStringProperty email;

    public Student(String id, String mssv, String name, String gender, String dob, String className, String email) {
        this.id = new SimpleStringProperty(id);
        this.mssv = new SimpleStringProperty(mssv);
        this.name = new SimpleStringProperty(name);
        this.gender = new SimpleStringProperty(gender);
        this.dob = new SimpleStringProperty(dob);
        this.className = new SimpleStringProperty(className);
        this.email = new SimpleStringProperty(email);
    }

    public String getId() {
        return id.get();
    }

    public String getMssv() {
        return mssv.get();
    }

    public String getName() {
        return name.get();
    }

    public String getGender() {
        return gender.get();
    }

    public String getDob() {
        return dob.get();
    }

    public String getClassName() {
        return className.get();
    }

    public String getEmail() {
        return email.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public void setDob(String dob) {
        this.dob.set(dob);
    }

    public void setClassName(String className) {
        this.className.set(className);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
