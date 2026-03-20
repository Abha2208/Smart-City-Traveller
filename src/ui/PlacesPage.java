package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class PlacesPage {

    public void show(Stage stage, String city, String category) {

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #0f172a;");

        // 🔥 BACK BUTTON
        Button backBtn = new Button("⬅ Back");

        backBtn.setStyle(
                "-fx-background-color: #334155;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 20;"
        );

        backBtn.setOnAction(e -> {
            new CategoryPage().show(stage, city);
        });

        // TITLE
        Label title = new Label(category + " in " + city);
        title.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");

        VBox list = new VBox(12);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/smart_city", "root", "Sololife22@"
            );

            String query = "SELECT * FROM places WHERE LOWER(city)=LOWER(?) AND LOWER(category)=LOWER(?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, city);
            ps.setString(2, category);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;

                String name = rs.getString("place_name");
                float rating = rs.getFloat("rating");
                int price = rs.getInt("price");
                String description = rs.getString("description");

                VBox card = new VBox(6);
                card.setPadding(new Insets(12));
                card.setStyle("-fx-background-color: #1e293b; -fx-background-radius: 12;");

                Label nameLabel = new Label(name);
                nameLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

                Label ratingLabel = new Label("⭐ " + rating);
                Label priceLabel = new Label("₹ " + price);

                ratingLabel.setStyle("-fx-text-fill: #94a3b8;");
                priceLabel.setStyle("-fx-text-fill: #94a3b8;");

                Label descLabel = new Label(description);
                descLabel.setWrapText(true);
                descLabel.setStyle("-fx-text-fill: #cbd5f5;");

                card.getChildren().addAll(nameLabel, ratingLabel, priceLabel, descLabel);

                list.getChildren().add(card);
            }

            if (!found) {
                Label noData = new Label("No places found");
                noData.setStyle("-fx-text-fill: white;");
                list.getChildren().add(noData);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        ScrollPane scroll = new ScrollPane(list);
        scroll.setFitToWidth(true);

        root.getChildren().addAll(backBtn, title, scroll);

        Scene scene = new Scene(root, 400, 700);
        stage.setScene(scene);
        stage.setTitle("Places");
        stage.show();
    }
}