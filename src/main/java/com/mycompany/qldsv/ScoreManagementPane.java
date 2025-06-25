package com.mycompany.qldsv;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import com.mycompany.qldsv.models.Score;

public class ScoreManagementPane {

    private final ObservableList<Score> scoreList = FXCollections.observableArrayList(
        new Score("SV001", "Nguyễn Văn A", "MH001", "Lập trình Java", 8.0, 7.5, 7.75),
        new Score("SV002", "Trần Thị B", "MH001", "Lập trình Java", 7.0, 6.5, 6.75),
        new Score("SV003", "Lê Văn C", "MH002", "Cơ sở dữ liệu", 9.0, 8.0, 8.5)
    );

    public Pane createPane() {
        // Center content
        HBox contentPane = new HBox(20);
        contentPane.setPadding(new Insets(20));

        // Left: TableView and Filters
        VBox tableSection = new VBox(15);
        tableSection.setPrefWidth(600);

        // Filter bar
        HBox filterBox = new HBox(10);
        ComboBox<String> classCombo = new ComboBox<>(FXCollections.observableArrayList("CNTT01", "CNTT02", "CNTT03"));
        classCombo.setPromptText("Chọn lớp");
        ComboBox<String> semesterCombo = new ComboBox<>(FXCollections.observableArrayList("HK1-2023", "HK2-2023", "HK1-2024"));
        semesterCombo.setPromptText("Chọn học kỳ");
        TextField searchField = new TextField();
        searchField.setPromptText("Tìm kiếm theo MSSV hoặc Tên SV");
        searchField.setPrefWidth(200);
        Button searchButton = new Button("Tìm kiếm");
        searchButton.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        searchButton.setOnMouseEntered(e -> searchButton.setStyle("-fx-background-color: #1d4ed8; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));
        searchButton.setOnMouseExited(e -> searchButton.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));
        for (Control control : new Control[]{classCombo, semesterCombo, searchField}) {
            control.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #d1d5db; -fx-font-size: 14px;");
        }
        filterBox.getChildren().addAll(classCombo, semesterCombo, searchField, searchButton);

        // TableView
        TableView<Score> scoreTable = new TableView<>();
        scoreTable.setStyle("-fx-background-color: #ffffff; -fx-border-color: #d1d5db; -fx-border-radius: 5; -fx-background-radius: 5;");
        scoreTable.setPrefHeight(350);

        TableColumn<Score, String> mssvCol = new TableColumn<>("MSSV");
        mssvCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMssv()));
        mssvCol.setPrefWidth(100);

        TableColumn<Score, String> nameCol = new TableColumn<>("Tên SV");
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentName()));
        nameCol.setPrefWidth(150);

        TableColumn<Score, String> subjectCodeCol = new TableColumn<>("Mã môn");
        subjectCodeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSubjectCode()));
        subjectCodeCol.setPrefWidth(100);

        TableColumn<Score, String> subjectNameCol = new TableColumn<>("Tên môn");
        subjectNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSubjectName()));
        subjectNameCol.setPrefWidth(150);

        TableColumn<Score, Double> midTermCol = new TableColumn<>("Điểm giữa kỳ");
        midTermCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getMidTermScore()).asObject());
        midTermCol.setPrefWidth(100);

        TableColumn<Score, Double> finalCol = new TableColumn<>("Điểm cuối kỳ");
        finalCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getFinalScore()).asObject());
        finalCol.setPrefWidth(100);

        TableColumn<Score, Double> totalCol = new TableColumn<>("Tổng kết");
        totalCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTotalScore()).asObject());
        totalCol.setPrefWidth(100);

        scoreTable.getColumns().addAll(mssvCol, nameCol, subjectCodeCol, subjectNameCol, midTermCol, finalCol, totalCol);
        scoreTable.setItems(scoreList);

        // Buttons
        HBox buttonBox = new HBox(10);
        Button addButton = new Button("Lưu");
        Button editButton = new Button("Cập nhật");
        Button deleteButton = new Button("Xóa điểm");
        Button refreshButton = new Button("Làm mới");
        for (Button btn : new Button[]{addButton, editButton, deleteButton, refreshButton}) {
            btn.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
            btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #1d4ed8; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));
            btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));
        }
        buttonBox.getChildren().addAll(addButton, editButton, deleteButton, refreshButton);

        tableSection.getChildren().addAll(filterBox, scoreTable, buttonBox);

        // Right: Form
        VBox formSection = new VBox(15);
        formSection.setPrefWidth(300);
        formSection.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #d1d5db; -fx-padding: 20;");
        formSection.setEffect(new DropShadow(10, Color.web("#00000033")));

        Label formTitle = new Label("Nhập Điểm Sinh Viên");
        formTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        formTitle.setTextFill(Color.web("#1e3a8a"));

        TextField mssvField = new TextField();
        mssvField.setPromptText("MSSV");
        TextField studentNameField = new TextField();
        studentNameField.setPromptText("Tên sinh viên");
        TextField subjectCodeField = new TextField();
        subjectCodeField.setPromptText("Mã môn");
        TextField subjectNameField = new TextField();
        subjectNameField.setPromptText("Tên môn");
        Spinner<Double> midTermSpinner = new Spinner<>(0.0, 10.0, 0.0, 0.1);
        midTermSpinner.setPromptText("Điểm giữa kỳ");
        Spinner<Double> finalSpinner = new Spinner<>(0.0, 10.0, 0.0, 0.1);
        finalSpinner.setPromptText("Điểm cuối kỳ");

        for (TextField field : new TextField[]{mssvField, studentNameField, subjectCodeField, subjectNameField}) {
            field.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #d1d5db; -fx-font-size: 14px;");
        }
        for (Spinner<?> spinner : new Spinner<?>[]{midTermSpinner, finalSpinner}) {
            spinner.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-font-size: 14px;");
            spinner.getEditor().setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #d1d5db; -fx-font-size: 14px;");
        }

        Label statusLabel = new Label();
        statusLabel.setFont(Font.font("Arial", 12));
        statusLabel.setTextFill(Color.web("#dc2626"));
        statusLabel.setVisible(false);

        Button saveButton = new Button("Lưu");
        saveButton.setStyle("-fx-background-color: #15803d; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        saveButton.setOnMouseEntered(e -> saveButton.setStyle("-fx-background-color: #166534; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));
        saveButton.setOnMouseExited(e -> saveButton.setStyle("-fx-background-color: #15803d; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));

        formSection.getChildren().addAll(formTitle, mssvField, studentNameField, subjectCodeField, subjectNameField, midTermSpinner, finalSpinner, saveButton, statusLabel);

        contentPane.getChildren().addAll(tableSection, formSection);

        // Actions
        searchButton.setOnAction(e -> {
            String query = searchField.getText().toLowerCase();
            String selectedClass = classCombo.getValue();
            String selectedSemester = semesterCombo.getValue();
            ObservableList<Score> filteredList = FXCollections.observableArrayList();
            for (Score score : scoreList) {
                boolean matches = true;
                if (!query.isEmpty() && !(score.getMssv().toLowerCase().contains(query) || score.getStudentName().toLowerCase().contains(query))) {
                    matches = false;
                }
                // Simplified filter for class and semester (assuming MSSV starts with class code, subjectCode ends with semester)
                if (selectedClass != null && !score.getMssv().startsWith(selectedClass.substring(0, 3))) {
                    matches = false;
                }
                if (selectedSemester != null && !score.getSubjectCode().endsWith(selectedSemester.substring(3))) {
                    matches = false;
                }
                if (matches) {
                    filteredList.add(score);
                }
            }
            scoreTable.setItems(filteredList);
        });

        addButton.setOnAction(e -> {
            clearForm(mssvField, studentNameField, subjectCodeField, subjectNameField, midTermSpinner, finalSpinner, statusLabel);
        });

        editButton.setOnAction(e -> {
            Score selected = scoreTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                mssvField.setText(selected.getMssv());
                studentNameField.setText(selected.getStudentName());
                subjectCodeField.setText(selected.getSubjectCode());
                subjectNameField.setText(selected.getSubjectName());
                midTermSpinner.getValueFactory().setValue(selected.getMidTermScore());
                finalSpinner.getValueFactory().setValue(selected.getFinalScore());
            } else {
                showStatus(statusLabel, "Vui lòng chọn dòng để cập nhật!", Color.web("#dc2626"));
            }
        });

        deleteButton.setOnAction(e -> {
            Score selected = scoreTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                scoreList.remove(selected);
                showStatus(statusLabel, "Xóa điểm thành công!", Color.web("#15803d"));
            } else {
                showStatus(statusLabel, "Vui lòng chọn dòng để xóa!", Color.web("#dc2626"));
            }
        });

        refreshButton.setOnAction(e -> {
            scoreTable.setItems(scoreList);
            classCombo.setValue(null);
            semesterCombo.setValue(null);
            searchField.clear();
            clearForm(mssvField, studentNameField, subjectCodeField, subjectNameField, midTermSpinner, finalSpinner, statusLabel);
        });

        saveButton.setOnAction(e -> {
            if (mssvField.getText().isEmpty() || subjectCodeField.getText().isEmpty()) {
                showStatus(statusLabel, "Vui lòng nhập MSSV và Mã môn!", Color.web("#dc2626"));
            } else {
                Score existing = scoreList.stream()
                    .filter(s -> s.getMssv().equals(mssvField.getText()) && s.getSubjectCode().equals(subjectCodeField.getText()))
                    .findFirst().orElse(null);
                double midTermScore = midTermSpinner.getValue();
                double finalScore = finalSpinner.getValue();
                double totalScore = (midTermScore + finalScore) / 2;
                if (existing != null) {
                    existing.setStudentName(studentNameField.getText());
                    existing.setSubjectName(subjectNameField.getText());
                    existing.setMidTermScore(midTermScore);
                    existing.setFinalScore(finalScore);
                    existing.setTotalScore(totalScore);
                    scoreTable.refresh();
                    showStatus(statusLabel, "Cập nhật điểm thành công!", Color.web("#15803d"));
                } else {
                    scoreList.add(new Score(
                        mssvField.getText(),
                        studentNameField.getText(),
                        subjectCodeField.getText(),
                        subjectNameField.getText(),
                        midTermScore,
                        finalScore,
                        totalScore
                    ));
                    showStatus(statusLabel, "Thêm điểm thành công!", Color.web("#15803d"));
                }
                clearForm(mssvField, studentNameField, subjectCodeField, subjectNameField, midTermSpinner, finalSpinner, statusLabel);
            }
        });

        // Fade-in animation
        FadeTransition fade = new FadeTransition(Duration.millis(1000), contentPane);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.play();

        return contentPane;
    }

    private void clearForm(TextField mssvField, TextField studentNameField, TextField subjectCodeField,
                          TextField subjectNameField, Spinner<Double> midTermSpinner, Spinner<Double> finalSpinner, Label statusLabel) {
        mssvField.clear();
        studentNameField.clear();
        subjectCodeField.clear();
        subjectNameField.clear();
        midTermSpinner.getValueFactory().setValue(0.0);
        finalSpinner.getValueFactory().setValue(0.0);
        statusLabel.setVisible(false);
    }

    private void showStatus(Label statusLabel, String message, Color color) {
        statusLabel.setText(message);
        statusLabel.setTextFill(color);
        statusLabel.setVisible(true);
        FadeTransition fade = new FadeTransition(Duration.millis(2000), statusLabel);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setDelay(Duration.millis(1000));
        fade.play();
    }
}