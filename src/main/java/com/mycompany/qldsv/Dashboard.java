package com.mycompany.qldsv;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.animation.ScaleTransition;
import javafx.scene.Cursor;
import javafx.util.Duration;

public class Dashboard extends Application {

    private final String username;

    public Dashboard(String username) {
        this.username = username;
    }

    public Dashboard() {
        this.username = "Quản Trị Viên"; // Default if no username is provided
    }

    @Override
    public void start(Stage primaryStage) {

        VBox contentPane = new VBox(20);

        // Root pane
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f4f6f9;");

        // Left sidebar
        VBox sidebar = new VBox(10);
        sidebar.setPrefWidth(200);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #1e3a8a; -fx-background-radius: 0 10 10 0;");
        sidebar.setAlignment(Pos.TOP_CENTER); // Center align content

        // Logo
        Image logoImage = new Image("/com/mycompany/qldsv/images/logo.png", 100, 100, true, true); // Placeholder: replace with actual logo path
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(100);
        logoView.setFitHeight(50);
        logoView.setPreserveRatio(true);

        // Space for application name (empty label as placeholder)
        Label appNamePlaceholder = new Label("EduPoint");
        appNamePlaceholder.setMinHeight(30); // Reserve space for application name
        appNamePlaceholder.setStyle("-fx-font-size: 16px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

        // Navigation menu
        VBox menuBox = new VBox(5);
        menuBox.setAlignment(Pos.CENTER); // Center align menu items
        String[] menuItems = {"Dashboard", "Quản lý sinh viên", "Quản lý môn học", "Quản lý điểm", "Báo cáo", "Đăng xuất"};
        Button[] menuButtons = new Button[menuItems.length]; // Store buttons to manage selected state
        for (int i = 0; i < menuItems.length; i++) {
            String item = menuItems[i];
            Button menuButton = new Button(item);
            menuButton.setCursor(Cursor.HAND);
            menuButtons[i] = menuButton;

            // Default style
            String defaultStyle = item.equals("Đăng xuất")
                    ? "-fx-background-color: #dc2626; -fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-alignment: CENTER_LEFT; -fx-padding: 10px; -fx-border-radius: 5; -fx-background-radius: 5;"
                    : "-fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-alignment: CENTER_LEFT; -fx-padding: 10px; -fx-border-radius: 5; -fx-background-radius: 5;";
            menuButton.setStyle(defaultStyle);
            menuButton.setPrefWidth(180);

            // Create a single ScaleTransition instance for this button
            ScaleTransition scaleIn = new ScaleTransition(Duration.millis(100), menuButton);
            scaleIn.setToX(1.05);
            scaleIn.setToY(1.0);

            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(100), menuButton);
            scaleOut.setToX(1.0);
            scaleOut.setToY(1.0);

            menuButton.setOnMouseEntered(e -> {
                String hoverStyle = item.equals("Đăng xuất")
                        ? "-fx-background-color: #b91c1c; -fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-alignment: CENTER_LEFT; -fx-padding: 10px; -fx-border-radius: 5; -fx-background-radius: 5;"
                        : "-fx-background-color: #2563eb; -fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-alignment: CENTER_LEFT; -fx-padding: 10px; -fx-border-radius: 5; -fx-background-radius: 5;";
                menuButton.setStyle(hoverStyle);
                scaleIn.playFromStart();
            });

            menuButton.setOnMouseExited(e -> {
                String exitStyle = menuButton.getUserData() != null && menuButton.getUserData().equals("selected")
                        ? "-fx-background-color: #3b82f6; -fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-alignment: CENTER_LEFT; -fx-padding: 10px; -fx-border-radius: 5; -fx-background-radius: 5; -fx-border-color: #ffffff; -fx-border-width: 1;"
                        : item.equals("Đăng xuất")
                        ? "-fx-background-color: #dc2626; -fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-alignment: CENTER_LEFT; -fx-padding: 10px; -fx-border-radius: 5; -fx-background-radius: 5;"
                        : "-fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-alignment: CENTER_LEFT; -fx-padding: 10px; -fx-border-radius: 5; -fx-background-radius: 5;";
                menuButton.setStyle(exitStyle);
                scaleOut.playFromStart();
            });

            if (item.equals("Dashboard")) {
                menuButton.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-alignment: CENTER_LEFT; -fx-padding: 10px; -fx-border-radius: 5; -fx-background-radius: 5; -fx-border-color: #ffffff; -fx-border-width: 1;");
                menuButton.setUserData("selected");
            }

            menuButton.setOnAction(e -> {
                // Reset all buttons to default style
                for (Button btn : menuButtons) {
                    String resetStyle = btn.getText().equals("Đăng xuất")
                            ? "-fx-background-color: #dc2626; -fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-alignment: CENTER_LEFT; -fx-padding: 10px; -fx-border-radius: 5; -fx-background-radius: 5;"
                            : "-fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-alignment: CENTER_LEFT; -fx-padding: 10px; -fx-border-radius: 5; -fx-background-radius: 5;";
                    btn.setStyle(resetStyle);
                    btn.setUserData(null);
                }
                // Set selected style for the clicked button
                if (!item.equals("Đăng xuất")) {
                    menuButton.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-alignment: CENTER_LEFT; -fx-padding: 10px; -fx-border-radius: 5; -fx-background-radius: 5; -fx-border-color: #ffffff; -fx-border-width: 1;");
                    menuButton.setUserData("selected");
                }

                if (item.equals("Dashboard")) {
                    // Fade-in animation
                    FadeTransition fade = new FadeTransition(Duration.millis(1000), contentPane);
                    fade.setFromValue(0.0);
                    fade.setToValue(1.0);
                    fade.play();
                    root.setCenter(contentPane);
                } else if (item.equals("Quản lý sinh viên")) {
                    StudentManagementPane studentPane = new StudentManagementPane();
                    root.setCenter(studentPane.createPane());
                } else if (item.equals("Quản lý môn học")) {
                    SubjectManagementPane subjectPane = new SubjectManagementPane();
                    root.setCenter(subjectPane.createPane());
                } else if (item.equals("Quản lý điểm")) {
                    ScoreManagementPane scorePane = new ScoreManagementPane();
                    root.setCenter(scorePane.createPane());
                } else if (item.equals("Đăng xuất")) {
                    primaryStage.close();
                    Login loginScreen = new Login();
                    Stage loginStage = new Stage();
                    loginScreen.start(loginStage);
                }
            });
            menuBox.getChildren().add(menuButton);
        }

        sidebar.getChildren().addAll(logoView, appNamePlaceholder, new Separator(), menuBox);

        root.setLeft(sidebar);

        // Top header
        HBox header = new HBox(20);
        header.setPadding(new Insets(10, 20, 10, 20));
        header.setStyle("-fx-background-color: #ffffff; -fx-border-color: #d1d5db; -fx-border-width: 0 0 1 0;");
        header.setAlignment(Pos.CENTER_RIGHT);

        Label userLabel = new Label("Xin chào, " + username);
        userLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        userLabel.setTextFill(Color.web("#1e3a8a"));

        header.getChildren().add(userLabel);
        root.setTop(header);

        // Main content panel
        contentPane.setAlignment(Pos.TOP_LEFT);
        contentPane.setPadding(new Insets(20));
        contentPane.setStyle("-fx-padding: 20px;");

        // Dashboard title
        Label dashboardTitle = new Label("Tổng Quan Hệ Thống");
        dashboardTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        dashboardTitle.setTextFill(Color.web("#333333"));

        // Quick stats cards
        GridPane statsGrid = new GridPane();
        statsGrid.setHgap(15);
        statsGrid.setVgap(15);

        // Dummy data for cards
        String[] stats = {"Số sinh viên: 500", "Số môn học: 25", "Số lớp học: 15", "Kỳ hiện tại: HK1-2023"};
        int row = 0, col = 0;
        for (String stat : stats) {
            VBox card = new VBox(10);
            card.setPadding(new Insets(15));
            card.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #d1d5db; -fx-border-width: 1;");
            card.setPrefWidth(200);
            card.setPrefHeight(100);
            Label statLabel = new Label(stat);
            statLabel.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
            statLabel.setTextFill(Color.web("#333333"));

            DropShadow cardShadow = new DropShadow(10, Color.web("#00000033"));
            card.setOnMouseEntered(e -> card.setEffect(cardShadow));
            card.setOnMouseExited(e -> card.setEffect(null));

            card.getChildren().add(statLabel);
            statsGrid.add(card, col, row);
            col++;
            if (col == 2) {
                col = 0;
                row++;
            }
        }

        contentPane.getChildren().addAll(dashboardTitle, statsGrid);
        root.setCenter(contentPane);

        // Scene and stage setup
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dashboard - Hệ Thống Quản Lý Điểm");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
