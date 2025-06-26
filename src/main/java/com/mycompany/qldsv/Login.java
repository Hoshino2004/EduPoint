package com.mycompany.qldsv;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.scene.Cursor;
import javafx.util.Duration;

public class Login extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Main pane with padding and background
        VBox mainPane = new VBox(20);
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setPadding(new Insets(20));
        mainPane.setStyle("-fx-background-color: linear-gradient(to bottom, #f4f6f9, #e0e7ff);");

        // Title
        Label titleLabel = new Label("Hệ Thống Quản Lý Điểm Sinh Viên EduPoint");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.web("#1e3a8a"));

        // Form pane
        GridPane formPane = new GridPane();
        formPane.setAlignment(Pos.CENTER);
        formPane.setHgap(10);
        formPane.setVgap(15);
        formPane.setPadding(new Insets(20));
        formPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #d1d5db;");

        // Add shadow effect to form
        DropShadow dropShadow = new DropShadow(10, Color.web("#00000033"));
        formPane.setEffect(dropShadow);

        // Username field
        Label usernameLabel = new Label("Tên đăng nhập:");
        usernameLabel.setFont(Font.font("Arial", FontWeight.MEDIUM, 14));
        TextField usernameField = new TextField();
        usernameField.setPromptText("Nhập tên đăng nhập");
        usernameField.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #d1d5db; -fx-font-size: 14px;");
        usernameField.setPrefWidth(250);

        // Password field
        Label passwordLabel = new Label("Mật khẩu:");
        passwordLabel.setFont(Font.font("Arial", FontWeight.MEDIUM, 14));
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Nhập mật khẩu");
        passwordField.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #d1d5db; -fx-font-size: 14px;");

        // Remember me checkbox
        CheckBox rememberCheckBox = new CheckBox("Ghi nhớ tài khoản");
        rememberCheckBox.setCursor(Cursor.HAND);
        rememberCheckBox.setFont(Font.font("Arial", 12));

        // Error label
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.web("#dc2626"));
        errorLabel.setFont(Font.font("Arial", 12));
        errorLabel.setVisible(false);

        // Buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button loginButton = new Button("Đăng nhập");
        loginButton.setCursor(Cursor.HAND);
        loginButton.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        loginButton.setPrefWidth(100);

        Button exitButton = new Button("Thoát");
        exitButton.setCursor(Cursor.HAND);
        exitButton.setStyle("-fx-background-color: #dc2626; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;");
        exitButton.setPrefWidth(100);

        // Hover effects for buttons
        loginButton.setOnMouseEntered(e -> loginButton.setStyle("-fx-background-color: #1d4ed8; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));
        loginButton.setOnMouseExited(e -> loginButton.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));
        exitButton.setOnMouseEntered(e -> exitButton.setStyle("-fx-background-color: #b91c1c; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));
        exitButton.setOnMouseExited(e -> exitButton.setStyle("-fx-background-color: #dc2626; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 5;"));

        // Add components to form
        formPane.add(usernameLabel, 0, 0);
        formPane.add(usernameField, 1, 0);
        formPane.add(passwordLabel, 0, 1);
        formPane.add(passwordField, 1, 1);
        formPane.add(rememberCheckBox, 1, 2);
        formPane.add(errorLabel, 0, 3, 2, 1);
        formPane.add(buttonBox, 0, 4, 2, 1);
        buttonBox.getChildren().addAll(loginButton, exitButton);

        // Add all to main pane
        mainPane.getChildren().addAll(titleLabel, formPane);

        // Login action (dummy data for demonstration)
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (username.equals("admin") && password.equals("password123")) {
                errorLabel.setText("Đăng nhập thành công!");
                errorLabel.setTextFill(Color.web("#15803d"));
                errorLabel.setVisible(true);
                // Fade transition for success
                FadeTransition fade = new FadeTransition(Duration.millis(1000), errorLabel);
                fade.setFromValue(1.0);
                fade.setToValue(0.0);
                fade.setOnFinished(event -> {
                    // Start DashboardApplication with username
                    Dashboard dashboardApp = new Dashboard(username);
                    try {
                        dashboardApp.start(new Stage());
                        primaryStage.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                fade.play();
            } else {
                errorLabel.setText("Tên đăng nhập hoặc mật khẩu sai!");
                errorLabel.setTextFill(Color.web("#dc2626"));
                errorLabel.setVisible(true);
            }
        });

        // Exit action
        exitButton.setOnAction(e -> primaryStage.close());

        // Scene and stage setup
        Scene scene = new Scene(mainPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Đăng Nhập - Hệ Thống Quản Lý Điểm");
        primaryStage.setResizable(false);
        primaryStage.show();

        // Fade-in animation for the form
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), mainPane);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
