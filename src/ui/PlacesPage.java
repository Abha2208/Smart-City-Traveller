package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.util.*;

public class PlacesPage {

    // MODEL CLASS
    class Place {
        String name;
        double rating;
        double price;
        String description;

        public Place(String name, double rating, double price, String description) {
            this.name = name;
            this.rating = rating;
            this.price = price;
            this.description = description;
        }
    }

    public void show(Stage stage, String city, String category) {

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #0f172a;");

        // BACK BUTTON
        Button backBtn = new Button("⬅ Back");
        backBtn.setStyle(
                "-fx-background-color: #334155;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 20;"
        );

        backBtn.setOnAction(e -> new CategoryPage().show(stage, city));

        // TITLE
        Label title = new Label(category + " in " + city);
        title.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");

        // SORT DROPDOWN
        ComboBox<String> sortBox = new ComboBox<>();
        sortBox.getItems().addAll(
                "Recommended (KNN)",
                "Price: Low to High",
                "Rating: High to Low"
        );
        sortBox.setValue("Recommended (KNN)");
        sortBox.setPrefWidth(250);

        VBox list = new VBox(12);

        List<Place> places = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/smart_city", "root", "Sololife22@"
            );

            // FETCH DATA
            String query = "SELECT * FROM places WHERE LOWER(city)=LOWER(?) AND LOWER(category)=LOWER(?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, city);
            ps.setString(2, category);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                places.add(new Place(
                        rs.getString("place_name"),
                        rs.getDouble("rating"),
                        rs.getDouble("price"),
                        rs.getString("description")
                ));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // INITIAL LOAD
        updateList(list, places, "Recommended (KNN)");

        // DROPDOWN ACTION
        sortBox.setOnAction(e -> {
            String selected = sortBox.getValue();
            updateList(list, places, selected);
        });

        ScrollPane scroll = new ScrollPane(list);
        scroll.setFitToWidth(true);

        root.getChildren().addAll(backBtn, title, sortBox, scroll);

        Scene scene = new Scene(root, 400, 700);
        stage.setScene(scene);
        stage.setTitle("Places");
        stage.show();
    }

    // ================== UPDATE LIST ==================
    private void updateList(VBox list, List<Place> places, String type) {

        list.getChildren().clear();

        List<Place> temp = new ArrayList<>(places);

        switch (type) {

            case "Price: Low to High":
                temp.sort(Comparator.comparingDouble(p -> p.price));
                break;

            case "Rating: High to Low":
                temp.sort((a, b) -> Double.compare(b.rating, a.rating));
                break;

            case "Recommended (KNN)":
            default:
                Place ideal = new Place("Ideal", 5.0, 1000.0, "");
                temp.sort((a, b) -> Double.compare(
                        getDistance(a, ideal),
                        getDistance(b, ideal)
                ));
                break;
        }

        for (Place p : temp) {
            list.getChildren().add(createCard(p));
        }

        if (temp.isEmpty()) {
            Label noData = new Label("No places found");
            noData.setStyle("-fx-text-fill: white;");
            list.getChildren().add(noData);
        }
    }

    // ================== DISTANCE FUNCTION ==================
    private double getDistance(Place p, Place target) {

        double r1 = target.rating / 5.0;
        double p1 = target.price / 10000.0;

        double r2 = p.rating / 5.0;
        double p2 = p.price / 10000.0;

        return Math.sqrt(
                Math.pow(r1 - r2, 2) +
                        Math.pow(p1 - p2, 2)
        );
    }

    // ================== CARD ==================
    private VBox createCard(Place p) {

        VBox card = new VBox(6);
        card.setPadding(new Insets(12));
        card.setStyle("-fx-background-color: #1e293b; -fx-background-radius: 12;");

        Label name = new Label(p.name);
        name.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        Label rating = new Label("⭐ " + p.rating);
        Label price = new Label("₹ " + p.price);

        Label desc = new Label(p.description);
        desc.setWrapText(true);

        rating.setStyle("-fx-text-fill: #94a3b8;");
        price.setStyle("-fx-text-fill: #94a3b8;");
        desc.setStyle("-fx-text-fill: #cbd5f5;");

        card.getChildren().addAll(name, rating, price, desc);

        return card;
    }
}