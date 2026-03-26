package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class CategoryPage {

    public void show(Stage stage, String city) {

        VBox root = new VBox(20);
        root.setPadding(new Insets(20, 15, 20, 15));

        root.setStyle("-fx-background-color: linear-gradient(to bottom, #0f172a, #1e293b);");

        // BACK BUTTON
        Button backBtn = new Button("⬅ Back");
        backBtn.setStyle(
                "-fx-background-color: #334155;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 20;"
        );

        backBtn.setOnAction(e -> {
            new CityPage().show(stage);
        });

        // HEADER
        Label title = new Label("Explore");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 28px; -fx-font-weight: bold;");

        Label subtitle = new Label("Where do you want to go today?");
        subtitle.setStyle("-fx-text-fill: #94a3b8;");

        VBox header = new VBox(5, title, subtitle);

        // CARDS
        VBox cards = new VBox(15);

        cards.getChildren().addAll(
                createCard(stage, city, "🏛", "Tourist", "Discover iconic landmarks", "#60a5fa"),
                createCard(stage, city, "📈", "Fun", "Experience the thrill", "#34d399"),
                createCard(stage, city, "🍽", "Food", "Local flavors & dining", "#fb923c"),
                createCard(stage, city, "🛍", "Shopping", "Luxury & local markets", "#c084fc")
        );

        // NAV BAR
        HBox nav = new HBox(40);
        nav.setAlignment(Pos.CENTER);

        Label home = new Label("🏠");
        Label search = new Label("🔍");
        Label heart = new Label("❤");
        Label profile = new Label("👤");

        home.setStyle("-fx-text-fill: white;");
        search.setStyle("-fx-text-fill: #94a3b8;");
        heart.setStyle("-fx-text-fill: #94a3b8;");
        profile.setStyle("-fx-text-fill: #94a3b8;");

        nav.getChildren().addAll(home, search, heart, profile);

        nav.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 15;");

        // ADD ALL
        root.getChildren().addAll(backBtn, header, cards, nav);
        ScrollPane scroll = new ScrollPane(root);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        Scene scene = new Scene(scroll, 400, 700);
       // Scene scene = new Scene(root, 400, 700);
        stage.setScene(scene);
        stage.setTitle("Category Page");
        stage.show();
    }

    private VBox createCard(Stage stage, String city, String icon, String titleText, String desc, String color) {

        VBox card = new VBox(10);
        card.setPadding(new Insets(20));

        card.setStyle(
                "-fx-background-color: linear-gradient(to right, #1e293b, #0f172a);" +
                        "-fx-background-radius: 20;"
        );

        Label iconLabel = new Label(icon);
        iconLabel.setStyle("-fx-font-size: 20px; -fx-background-color: " + color + "; -fx-padding: 10;");

        Label title = new Label(titleText);
        title.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

        Label description = new Label(desc);
        description.setStyle("-fx-text-fill: #94a3b8;");

        card.getChildren().addAll(iconLabel, title, description);

        // CLICK → OPEN PLACES
        card.setOnMouseClicked(e -> {
            new PlacesPage().show(stage, city, titleText);
        });

        return card;
    }
}