/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qldsv.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ADMIN
 */
public class Subject {

    private final SimpleStringProperty code;
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty credits;
    private final SimpleStringProperty semester;

    public Subject(String code, String name, int credits, String semester) {
        this.code = new SimpleStringProperty(code);
        this.name = new SimpleStringProperty(name);
        this.credits = new SimpleIntegerProperty(credits);
        this.semester = new SimpleStringProperty(semester);
    }

    public String getCode() {
        return code.get();
    }

    public String getName() {
        return name.get();
    }

    public int getCredits() {
        return credits.get();
    }

    public String getSemester() {
        return semester.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setCredits(int credits) {
        this.credits.set(credits);
    }

    public void setSemester(String semester) {
        this.semester.set(semester);
    }
}
