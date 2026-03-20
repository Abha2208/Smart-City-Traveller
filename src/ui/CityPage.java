package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;

public class CityPage {

    public void show(Stage stage) {

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));

        root.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #dbeafe, #bfdbfe);"
        );

        // 🔥 LOGOUT BUTTON
        Button logoutBtn = new Button("Logout");

        logoutBtn.setStyle(
                "-fx-background-color: #ff4d4d;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 20;" +
                        "-fx-padding: 8 20;"
        );

        logoutBtn.setOnAction(e -> {
            File file = new File("remember.txt");

            if(file.exists()) {
                file.delete(); // remove auto login
            }

            new LoginPage().start(stage);
        });

        // ICON
        Label icon = new Label("📍");
        icon.setStyle(
                "-fx-font-size: 24px;" +
                        "-fx-background-color: #f0f0f0;" +
                        "-fx-padding: 20;" +
                        "-fx-background-radius: 50;" +
                        "-fx-border-radius: 50;" +
                        "-fx-border-color: #dddddd;"
        );

        // TITLE
        Label title = new Label("Where to next?");
        title.setStyle(
                "-fx-font-size: 26px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #2d3748;"
        );

        // SUBTITLE
        Label subtitle = new Label("Select your city");
        subtitle.setStyle(
                "-fx-text-fill: #718096;" +
                        "-fx-font-size: 14px;"
        );

        // DROPDOWN
        ComboBox<String> cityDropdown = new ComboBox<>();
        cityDropdown.setPromptText("Select city");
        cityDropdown.setPrefWidth(300);

        cityDropdown.getItems().addAll(
                "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar",
                "Chhattisgarh", "Delhi", "Goa", "Gujarat", "Haryana",
                "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala",
                "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya",
                "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan",
                "Sikkim", "Tamil Nadu", "Telangana", "Tripura",
                "Uttar Pradesh", "Uttarakhand", "West Bengal",
                "Andaman & Nicobar", "Chandigarh", "Jammu & Kashmir",
                "Ladakh", "Lakshadweep", "Puducherry"
        );

        cityDropdown.setStyle(
                "-fx-background-radius: 30;" +
                        "-fx-padding: 8;"
        );

        // EXPLORE BUTTON
        Button exploreBtn = new Button("Explore");
        exploreBtn.setPrefWidth(300);

        exploreBtn.setStyle(
                "-fx-background-color: linear-gradient(to right, #667eea, #764ba2);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 30;" +
                        "-fx-padding: 12;"
        );

        // ACTION
        exploreBtn.setOnAction(e -> {

            String city = cityDropdown.getValue();

            if (city == null) {
                System.out.println("Please select a city");
            } else {
                System.out.println("Selected city: " + city);
                new CategoryPage().show(stage, city);
            }
        });
        Button backBtn = new Button("⬅ Back");

        backBtn.setStyle(
                "-fx-background-color: #334155;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 20;"
        );

        backBtn.setOnAction(e -> {
            new LoginPage().start(stage);
        });
        // ADD ALL
        root.getChildren().addAll(
                backBtn,
                logoutBtn,
                icon,
                title,
                subtitle,
                cityDropdown,
                exploreBtn
        );

        Scene scene = new Scene(root, 400, 650);
        stage.setTitle("City Page");
        stage.setScene(scene);
        stage.show();
    }
}