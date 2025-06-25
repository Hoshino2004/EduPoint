package com.mycompany.qldsv;

import javafx.beans.property.SimpleStringProperty;
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
import com.mycompany.qldsv.models.Student;

public class StudentManagementPane {

    private final ObservableList<Student> studentList = FXCollections.observableArrayList(
        new Student("1", "SV001", "Nguyễn Văn A", "Nam", "01/01/2000", "CNTT01", "a@gmail.com"),
        new Student("2", "SV002", "Trần Thị B", "Nữ", "02/02/2001", "CNTT02", "b@gmail.com"),
        new Student("3", "SV003", "Lê Văn C", "Nam", "03/03/2002", "CNTT03", "c@gmail.com")
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
        searchField.setPromptText("Tìm kiếm theo MSSV hoặc Họ tên");
        searchField.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #d1d5db; -fx-font-size: 14px;");
        searchField.setPrefWidth(300);
        Button searchButton = new Button("Tìm kiếm");
        searchButton.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        searchButton.setOnMouseEntered(e -> searchButton.setStyle("-fx-background-color: #1d4ed8; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));
        searchButton.setOnMouseExited(e -> searchButton.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));
        searchBox.getChildren().addAll(searchField, searchButton);

        // TableView
        TableView<Student> studentTable = new TableView<>();
        studentTable.setStyle("-fx-background-color: #ffffff; -fx-border-color: #d1d5db; -fx-border-radius: 5; -fx-background-radius: 5;");
        studentTable.setPrefHeight(400);

        TableColumn<Student, String> idCol = new TableColumn<>("STT");
        idCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        idCol.setPrefWidth(50);

        TableColumn<Student, String> mssvCol = new TableColumn<>("MSSV");
        mssvCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMssv()));
        mssvCol.setPrefWidth(100);

        TableColumn<Student, String> nameCol = new TableColumn<>("Họ tên");
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        nameCol.setPrefWidth(150);

        TableColumn<Student, String> genderCol = new TableColumn<>("Giới tính");
        genderCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGender()));
        genderCol.setPrefWidth(80);

        TableColumn<Student, String> dobCol = new TableColumn<>("Ngày sinh");
        dobCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDob()));
        dobCol.setPrefWidth(100);

        TableColumn<Student, String> classCol = new TableColumn<>("Lớp");
        classCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClassName()));
        classCol.setPrefWidth(100);

        TableColumn<Student, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        emailCol.setPrefWidth(150);

        studentTable.getColumns().addAll(idCol, mssvCol, nameCol, genderCol, dobCol, classCol, emailCol);
        studentTable.setItems(studentList);

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

        tableSection.getChildren().addAll(searchBox, studentTable, buttonBox);

        // Right: Form
        VBox formSection = new VBox(15);
        formSection.setPrefWidth(300);
        formSection.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #d1d5db; -fx-padding: 20;");
        formSection.setEffect(new DropShadow(10, Color.web("#00000033")));

        Label formTitle = new Label("Thông Tin Sinh Viên");
        formTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        formTitle.setTextFill(Color.web("#1e3a8a"));

        TextField mssvField = new TextField();
        mssvField.setPromptText("MSSV");
        TextField nameField = new TextField();
        nameField.setPromptText("Họ tên");
        ComboBox<String> genderCombo = new ComboBox<>(FXCollections.observableArrayList("Nam", "Nữ"));
        genderCombo.setPromptText("Giới tính");
        TextField dobField = new TextField();
        dobField.setPromptText("Ngày sinh (dd/mm/yyyy)");
        TextField classField = new TextField();
        classField.setPromptText("Lớp");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        for (TextField field : new TextField[]{mssvField, nameField, dobField, classField, emailField}) {
            field.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #d1d5db; -fx-font-size: 14px;");
        }
        genderCombo.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-font-size: 14px;");

        Label statusLabel = new Label();
        statusLabel.setFont(Font.font("Arial", 12));
        statusLabel.setTextFill(Color.web("#dc2626"));
        statusLabel.setVisible(false);

        Button saveButton = new Button("Lưu");
        saveButton.setStyle("-fx-background-color: #15803d; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        saveButton.setOnMouseEntered(e -> saveButton.setStyle("-fx-background-color: #166534; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));
        saveButton.setOnMouseExited(e -> saveButton.setStyle("-fx-background-color: #15803d; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));

        formSection.getChildren().addAll(formTitle, mssvField, nameField, genderCombo, dobField, classField, emailField, saveButton, statusLabel);

        contentPane.getChildren().addAll(tableSection, formSection);

        // Actions
        searchButton.setOnAction(e -> {
            String query = searchField.getText().toLowerCase();
            ObservableList<Student> filteredList = FXCollections.observableArrayList();
            for (Student student : studentList) {
                if (student.getMssv().toLowerCase().contains(query) || student.getName().toLowerCase().contains(query)) {
                    filteredList.add(student);
                }
            }
            studentTable.setItems(filteredList);
        });

        addButton.setOnAction(e -> {
            clearForm(mssvField, nameField, genderCombo, dobField, classField, emailField, statusLabel);
        });

        editButton.setOnAction(e -> {
            Student selected = studentTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                mssvField.setText(selected.getMssv());
                nameField.setText(selected.getName());
                genderCombo.setValue(selected.getGender());
                dobField.setText(selected.getDob());
                classField.setText(selected.getClassName());
                emailField.setText(selected.getEmail());
            } else {
                showStatus(statusLabel, "Vui lòng chọn sinh viên để sửa!", Color.web("#dc2626"));
            }
        });

        deleteButton.setOnAction(e -> {
            Student selected = studentTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                studentList.remove(selected);
                showStatus(statusLabel, "Xóa sinh viên thành công!", Color.web("#15803d"));
            } else {
                showStatus(statusLabel, "Vui lòng chọn sinh viên để xóa!", Color.web("#dc2626"));
            }
        });

        refreshButton.setOnAction(e -> {
            studentTable.setItems(studentList);
            searchField.clear();
            clearForm(mssvField, nameField, genderCombo, dobField, classField, emailField, statusLabel);
        });

        saveButton.setOnAction(e -> {
            if (mssvField.getText().isEmpty() || nameField.getText().isEmpty()) {
                showStatus(statusLabel, "Vui lòng nhập MSSV và Họ tên!", Color.web("#dc2626"));
            } else {
                Student existing = studentList.stream()
                    .filter(s -> s.getMssv().equals(mssvField.getText()))
                    .findFirst().orElse(null);
                if (existing != null) {
                    existing.setName(nameField.getText());
                    existing.setGender(genderCombo.getValue() != null ? genderCombo.getValue() : "");
                    existing.setDob(dobField.getText());
                    existing.setClassName(classField.getText());
                    existing.setEmail(emailField.getText());
                    studentTable.refresh();
                    showStatus(statusLabel, "Cập nhật sinh viên thành công!", Color.web("#15803d"));
                } else {
                    studentList.add(new Student(
                        String.valueOf(studentList.size() + 1),
                        mssvField.getText(),
                        nameField.getText(),
                        genderCombo.getValue() != null ? genderCombo.getValue() : "",
                        dobField.getText(),
                        classField.getText(),
                        emailField.getText()
                    ));
                    showStatus(statusLabel, "Thêm sinh viên thành công!", Color.web("#15803d"));
                }
                clearForm(mssvField, nameField, genderCombo, dobField, classField, emailField, statusLabel);
            }
        });

        // Fade-in animation
        FadeTransition fade = new FadeTransition(Duration.millis(1000), contentPane);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.play();

        return contentPane;
    }

    private void clearForm(TextField mssvField, TextField nameField, ComboBox<String> genderCombo,
                          TextField dobField, TextField classField, TextField emailField, Label statusLabel) {
        mssvField.clear();
        nameField.clear();
        genderCombo.setValue(null);
        dobField.clear();
        classField.clear();
        emailField.clear();
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