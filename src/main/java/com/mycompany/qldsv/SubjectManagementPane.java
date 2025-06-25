package com.mycompany.qldsv;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import com.mycompany.qldsv.models.Subject;

public class SubjectManagementPane {

    private final ObservableList<Subject> subjectList = FXCollections.observableArrayList(
        new Subject("MH001", "Lập trình Java", 3, "HK1-2023"),
        new Subject("MH002", "Cơ sở dữ liệu", 3, "HK1-2023"),
        new Subject("MH003", "Cấu trúc dữ liệu", 4, "HK2-2023")
    );

    public Pane createPane() {
        // Center content
        HBox contentPane = new HBox(20);
        contentPane.setPadding(new Insets(20));

        // Left: TableView and Search
        VBox tableSection = new VBox(15);
        tableSection.setPrefWidth(600);

        // Search bar
        HBox searchBox = new HBox(10);
        TextField searchField = new TextField();
        searchField.setPromptText("Tìm kiếm theo Mã môn hoặc Tên môn");
        searchField.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #d1d5db; -fx-font-size: 14px;");
        searchField.setPrefWidth(300);
        Button searchButton = new Button("Tìm kiếm");
        searchButton.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        searchButton.setOnMouseEntered(e -> searchButton.setStyle("-fx-background-color: #1d4ed8; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));
        searchButton.setOnMouseExited(e -> searchButton.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));
        searchBox.getChildren().addAll(searchField, searchButton);

        // TableView
        TableView<Subject> subjectTable = new TableView<>();
        subjectTable.setStyle("-fx-background-color: #ffffff; -fx-border-color: #d1d5db; -fx-border-radius: 5; -fx-background-radius: 5;");
        subjectTable.setPrefHeight(400);

        TableColumn<Subject, String> codeCol = new TableColumn<>("Mã môn");
        codeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCode()));
        codeCol.setPrefWidth(100);

        TableColumn<Subject, String> nameCol = new TableColumn<>("Tên môn");
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        nameCol.setPrefWidth(200);

        TableColumn<Subject, Integer> creditsCol = new TableColumn<>("Số tín chỉ");
        creditsCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCredits()).asObject());
        creditsCol.setPrefWidth(100);

        TableColumn<Subject, String> semesterCol = new TableColumn<>("Học kỳ");
        semesterCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSemester()));
        semesterCol.setPrefWidth(150);

        subjectTable.getColumns().addAll(codeCol, nameCol, creditsCol, semesterCol);
        subjectTable.setItems(subjectList);

        // Buttons
        HBox buttonBox = new HBox(10);
        Button addButton = new Button("Thêm");
        Button editButton = new Button("Sửa");
        Button deleteButton = new Button("Xóa");
        Button refreshButton = new Button("Làm mới");
        for (Button btn : new Button[]{addButton, editButton, deleteButton, refreshButton}) {
            btn.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
            btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #1d4ed8; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));
            btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));
        }
        buttonBox.getChildren().addAll(addButton, editButton, deleteButton, refreshButton);

        tableSection.getChildren().addAll(searchBox, subjectTable, buttonBox);

        // Right: Form
        VBox formSection = new VBox(15);
        formSection.setPrefWidth(300);
        formSection.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #d1d5db; -fx-padding: 20;");
        formSection.setEffect(new DropShadow(10, Color.web("#00000033")));

        Label formTitle = new Label("Thông Tin Môn Học");
        formTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        formTitle.setTextFill(Color.web("#1e3a8a"));

        TextField codeField = new TextField();
        codeField.setPromptText("Mã môn");
        TextField nameField = new TextField();
        nameField.setPromptText("Tên môn");
        TextField creditsField = new TextField();
        creditsField.setPromptText("Số tín chỉ");
        ComboBox<String> semesterCombo = new ComboBox<>(FXCollections.observableArrayList("HK1-2023", "HK2-2023", "HK1-2024"));
        semesterCombo.setPromptText("Học kỳ");

        for (TextField field : new TextField[]{codeField, nameField, creditsField}) {
            field.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #d1d5db; -fx-font-size: 14px;");
        }
        semesterCombo.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-font-size: 14px;");

        Label statusLabel = new Label();
        statusLabel.setFont(Font.font("Arial", 12));
        statusLabel.setTextFill(Color.web("#dc2626"));
        statusLabel.setVisible(false);

        Button saveButton = new Button("Lưu");
        saveButton.setStyle("-fx-background-color: #15803d; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        saveButton.setOnMouseEntered(e -> saveButton.setStyle("-fx-background-color: #166534; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));
        saveButton.setOnMouseExited(e -> saveButton.setStyle("-fx-background-color: #15803d; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));

        formSection.getChildren().addAll(formTitle, codeField, nameField, creditsField, semesterCombo, saveButton, statusLabel);

        contentPane.getChildren().addAll(tableSection, formSection);

        // Actions
        searchButton.setOnAction(e -> {
            String query = searchField.getText().toLowerCase();
            ObservableList<Subject> filteredList = FXCollections.observableArrayList();
            for (Subject subject : subjectList) {
                if (subject.getCode().toLowerCase().contains(query) || subject.getName().toLowerCase().contains(query)) {
                    filteredList.add(subject);
                }
            }
            subjectTable.setItems(filteredList);
        });

        addButton.setOnAction(e -> {
            clearForm(codeField, nameField, creditsField, semesterCombo, statusLabel);
        });

        editButton.setOnAction(e -> {
            Subject selected = subjectTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                codeField.setText(selected.getCode());
                nameField.setText(selected.getName());
                creditsField.setText(String.valueOf(selected.getCredits()));
                semesterCombo.setValue(selected.getSemester());
            } else {
                showStatus(statusLabel, "Vui lòng chọn môn học để sửa!", Color.web("#dc2626"));
            }
        });

        deleteButton.setOnAction(e -> {
            Subject selected = subjectTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                subjectList.remove(selected);
                showStatus(statusLabel, "Xóa môn học thành công!", Color.web("#15803d"));
            } else {
                showStatus(statusLabel, "Vui lòng chọn môn học để xóa!", Color.web("#dc2626"));
            }
        });

        refreshButton.setOnAction(e -> {
            subjectTable.setItems(subjectList);
            searchField.clear();
            clearForm(codeField, nameField, creditsField, semesterCombo, statusLabel);
        });

        saveButton.setOnAction(e -> {
            if (codeField.getText().isEmpty() || nameField.getText().isEmpty() || creditsField.getText().isEmpty()) {
                showStatus(statusLabel, "Vui lòng nhập đầy đủ thông tin!", Color.web("#dc2626"));
            } else {
                try {
                    int credits = Integer.parseInt(creditsField.getText());
                    Subject existing = subjectList.stream()
                        .filter(s -> s.getCode().equals(codeField.getText()))
                        .findFirst().orElse(null);
                    if (existing != null) {
                        existing.setName(nameField.getText());
                        existing.setCredits(credits);
                        existing.setSemester(semesterCombo.getValue() != null ? semesterCombo.getValue() : "");
                        subjectTable.refresh();
                        showStatus(statusLabel, "Cập nhật môn học thành công!", Color.web("#15803d"));
                    } else {
                        subjectList.add(new Subject(
                            codeField.getText(),
                            nameField.getText(),
                            credits,
                            semesterCombo.getValue() != null ? semesterCombo.getValue() : ""
                        ));
                        showStatus(statusLabel, "Thêm môn học thành công!", Color.web("#15803d"));
                    }
                    clearForm(codeField, nameField, creditsField, semesterCombo, statusLabel);
                } catch (NumberFormatException ex) {
                    showStatus(statusLabel, "Số tín chỉ phải là số nguyên!", Color.web("#dc2626"));
                }
            }
        });

        // Fade-in animation
        FadeTransition fade = new FadeTransition(Duration.millis(1000), contentPane);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.play();

        return contentPane;
    }

    private void clearForm(TextField codeField, TextField nameField, TextField creditsField, ComboBox<String> semesterCombo, Label statusLabel) {
        codeField.clear();
        nameField.clear();
        creditsField.clear();
        semesterCombo.setValue(null);
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
