package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;

public class CityPage {

    public void show(Stage stage) {

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));

        root.setStyle("-fx-background-color: linear-gradient(to bottom, #dbeafe, #bfdbfe);");

        // LOGOUT BUTTON
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
            if (file.exists()) file.delete();
            new LoginPage().start(stage);
        });

        // ICON
        Label icon = new Label("📍");
        icon.setStyle("-fx-font-size: 24px; -fx-background-color: #f0f0f0; -fx-padding: 20; -fx-background-radius: 50;");

        // TITLE
        Label title = new Label("Where to next?");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        // SUBTITLE
        Label subtitle = new Label("Select your state & city");
        subtitle.setStyle("-fx-text-fill: #718096;");

        // STATE DROPDOWN
        ComboBox<String> stateDropdown = new ComboBox<>();
        stateDropdown.setPromptText("Select state");
        stateDropdown.setPrefWidth(300);

        // CITY DROPDOWN
        ComboBox<String> cityDropdown = new ComboBox<>();
        cityDropdown.setPromptText("Select city");
        cityDropdown.setPrefWidth(300);

        // MAP
        Map<String, List<String>> stateCityMap = new HashMap<>();

        // -------- ADD DATA --------
        stateCityMap.put("Andhra Pradesh", Arrays.asList("Visakhapatnam (Vizag)", "Vijayawada", "Guntur", "Nellore", "Tirupati"));
        stateCityMap.put("Arunachal Pradesh", Arrays.asList("Tawang", "Bhismaknagar", "Pasighat", "Ziro", "Bomdila"));
        stateCityMap.put("Assam", Arrays.asList("Guwahati", "Tezpur", "Dibrugarh", "Silchar", "North Lakhimpur"));
        stateCityMap.put("Bihar", Arrays.asList("Gaya", "Biharsharif", "Darbhanga", "Bhagalpur"));
        stateCityMap.put("Chhattisgarh", Arrays.asList("Bilaspur", "Korba", "Durg-Bhilai", "Raigarh", "Rajnandgaon"));
        stateCityMap.put("Goa", Arrays.asList("Vasco-da-Gama", "Ponda", "Margao", "Mapusa", "Goa Velha"));
        stateCityMap.put("Gujarat", Arrays.asList("Ahmedabad", "Surat", "Rajkot", "Junagadh", "Vadodara"));
        stateCityMap.put("Haryana", Arrays.asList("Faridabad", "Gurgaon", "Sonipat", "Panipat", "Ambala"));
        stateCityMap.put("Himachal Pradesh", Arrays.asList("Dharamshala", "Mandi", "Solan", "Bilaspur", "Chamba"));

        stateCityMap.put("Jharkhand", Arrays.asList("Bokaro Steel City", "Jamshedpur", "Deoghar", "Hazaribagh", "Dhanbad"));
        stateCityMap.put("Karnataka", Arrays.asList("Mysore", "Davangere", "Mangalore", "Hubli-Dharwad", "Belgaum"));
        stateCityMap.put("Kerala", Arrays.asList("Kochi", "Kozhikode", "Thrissur", "Malappuram"));
        stateCityMap.put("Madhya Pradesh", Arrays.asList("Indore", "Gwalior", "Jabalpur", "Ujjain", "Sagar"));
        stateCityMap.put("Maharashtra", Arrays.asList("Pune", "Nagpur", "Nashik", "Aurangabad", "Solapur"));
        stateCityMap.put("Manipur", Arrays.asList("Bishnupur", "Ukhrul", "Tamenglong", "Chandel", "Senapati"));
        stateCityMap.put("Meghalaya", Arrays.asList("Cherrapunji", "Tura", "Jowai", "Baghmara", "Nongpoh"));
        stateCityMap.put("Mizoram", Arrays.asList("Lunglei", "Serchhip", "Champhai", "Tuipang", "Mamit"));

        stateCityMap.put("Nagaland", Arrays.asList("Tuensang", "Zunheboto", "Mokokchung", "Kiphire Sadar", "Phek"));
        stateCityMap.put("Odisha", Arrays.asList("Rourkela", "Cuttack", "Brahmapur", "Puri", "Sambalpur"));
        stateCityMap.put("Punjab", Arrays.asList("Amritsar", "Jalandhar", "Ludhiana", "Patiala", "Kapurthala"));
        stateCityMap.put("Rajasthan", Arrays.asList("Bikaner", "Jaisalmer", "Jodhpur", "Udaipur", "Ajmer"));
        stateCityMap.put("Sikkim", Arrays.asList("Namchi", "Gyalshing", "Mangan", "Rabdentse"));
        stateCityMap.put("Tamil Nadu", Arrays.asList("Tiruchirappalli", "Madurai", "Erode", "Vellore", "Coimbatore"));
        stateCityMap.put("Telangana", Arrays.asList("Warangal", "Nizamabad", "Karimnagar", "Adilabad", "Khammam"));
        stateCityMap.put("Tripura", Arrays.asList("Amarpur", "Kumarghat", "Udaipur", "Gakulnagar", "Kunjaban"));
        stateCityMap.put("Uttar Pradesh", Arrays.asList("Noida", "Varanasi", "Allahabad", "Agra", "Kanpur"));
        stateCityMap.put("Uttarakhand", Arrays.asList("Haridwar", "Roorkee", "Rishikesh", "Kashipur", "Haldwani"));
        stateCityMap.put("West Bengal", Arrays.asList("Darjeeling", "Siliguri", "Asansol", "Howrah", "Durgapur"));

        // UNION TERRITORIES
        stateCityMap.put("Delhi", Arrays.asList("New Delhi"));
        stateCityMap.put("Jammu and Kashmir", Arrays.asList("Srinagar", "Jammu"));
        stateCityMap.put("Ladakh", Arrays.asList("Leh"));
        stateCityMap.put("Chandigarh", Arrays.asList("Chandigarh"));
        stateCityMap.put("Puducherry", Arrays.asList("Puducherry"));
        stateCityMap.put("Lakshadweep", Arrays.asList("Kavaratti"));
        stateCityMap.put("Andaman and Nicobar Islands", Arrays.asList("Port Blair"));

        // SET STATES
        stateDropdown.getItems().addAll(stateCityMap.keySet());

        // STATE → CITY LOGIC
        stateDropdown.setOnAction(e -> {
            String state = stateDropdown.getValue();
            cityDropdown.getItems().clear();

            if (state != null) {
                cityDropdown.getItems().addAll(stateCityMap.get(state));
            }
        });

        // EXPLORE BUTTON
        Button exploreBtn = new Button("Explore");
        exploreBtn.setPrefWidth(300);
        exploreBtn.setStyle("-fx-background-color: linear-gradient(to right, #667eea, #764ba2); -fx-text-fill: white;");

        exploreBtn.setOnAction(e -> {

            String state = stateDropdown.getValue();
            String city = cityDropdown.getValue();

            if (state == null || city == null) {
                System.out.println("Select state and city");
            } else {
                new CategoryPage().show(stage, city);
            }
        });

        // ADD ALL
        root.getChildren().addAll(
                logoutBtn,
                icon,
                title,
                subtitle,
                stateDropdown,
                cityDropdown,
                exploreBtn
        );

        Scene scene = new Scene(root, 400, 650);
        stage.setTitle("City Page");
        stage.setScene(scene);
        stage.show();
    }
}