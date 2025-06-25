/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qldsv.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ADMIN
 */
public class Score {

    private final SimpleStringProperty mssv;
    private final SimpleStringProperty studentName;
    private final SimpleStringProperty subjectCode;
    private final SimpleStringProperty subjectName;
    private final SimpleDoubleProperty midTermScore;
    private final SimpleDoubleProperty finalScore;
    private final SimpleDoubleProperty totalScore;

    public Score(String mssv, String studentName, String subjectCode, String subjectName,
            double midTermScore, double finalScore, double totalScore) {
        this.mssv = new SimpleStringProperty(mssv);
        this.studentName = new SimpleStringProperty(studentName);
        this.subjectCode = new SimpleStringProperty(subjectCode);
        this.subjectName = new SimpleStringProperty(subjectName);
        this.midTermScore = new SimpleDoubleProperty(midTermScore);
        this.finalScore = new SimpleDoubleProperty(finalScore);
        this.totalScore = new SimpleDoubleProperty(totalScore);
    }

    public String getMssv() {
        return mssv.get();
    }

    public String getStudentName() {
        return studentName.get();
    }

    public String getSubjectCode() {
        return subjectCode.get();
    }

    public String getSubjectName() {
        return subjectName.get();
    }

    public double getMidTermScore() {
        return midTermScore.get();
    }

    public double getFinalScore() {
        return finalScore.get();
    }

    public double getTotalScore() {
        return totalScore.get();
    }

    public void setStudentName(String studentName) {
        this.studentName.set(studentName);
    }

    public void setSubjectName(String subjectName) {
        this.subjectName.set(subjectName);
    }

    public void setMidTermScore(double midTermScore) {
        this.midTermScore.set(midTermScore);
    }

    public void setFinalScore(double finalScore) {
        this.finalScore.set(finalScore);
    }

    public void setTotalScore(double totalScore) {
        this.totalScore.set(totalScore);
    }
}
