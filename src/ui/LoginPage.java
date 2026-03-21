package ui;

import javafx.application.Application;//main class (start,launch)
import javafx.geometry.Insets;// spacing or padding
import javafx.geometry.Pos;// set alignment
import javafx.scene.Scene;// ui screen
import javafx.scene.control.*;// imports ui buttons
import javafx.scene.layout.*;// import layout containers
import javafx.stage.Stage;// represent main window

import java.sql.*;// used for database connectivity
import java.io.*;// used for input/output operations

public class LoginPage extends Application {

    @Override
    public void start(Stage stage) {

        //STEP 1: AUTO LOGIN CHECK
        File file = new File("remember.txt");

        if(file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String savedEmail = br.readLine();
                br.close();

                if(savedEmail != null && !savedEmail.isEmpty()) {
                    System.out.println("Auto Login: " + savedEmail);
                    new CityPage().show(stage);
                    return; //stop login page
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // UI
        VBox root = new VBox(20);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(30));

        root.setStyle("-fx-background-color: linear-gradient(to bottom, #3a3a98, #6a11cb);");

        Label icon = new Label("📍");
        icon.setStyle("-fx-font-size: 40px; -fx-background-color: rgba(255,255,255,0.1); -fx-padding: 20; -fx-background-radius: 20;");

        Label title = new Label("Smart City");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 30px; -fx-font-weight: bold;");

        Label subtitle = new Label("TRAVELER COMPANION");
        subtitle.setStyle("-fx-text-fill: rgba(255,255,255,0.7); -fx-font-size: 14px;");

        VBox card = new VBox(15);
        card.setPadding(new Insets(25));
        card.setMaxWidth(320);

        card.setStyle(
                "-fx-background-color: rgba(255,255,255,0.1);" +
                        "-fx-background-radius: 25;" +
                        "-fx-border-radius: 25;" +
                        "-fx-border-color: rgba(255,255,255,0.2);"
        );

        Label userLabel = new Label("EMAIL");
        userLabel.setStyle("-fx-text-fill: rgba(255,255,255,0.7);");

        TextField email = new TextField();
        email.setPromptText("Enter email");
        email.setStyle("-fx-background-radius: 30; -fx-padding: 12; -fx-background-color: #eeeeee;");

        Label passLabel = new Label("PASSWORD");
        passLabel.setStyle("-fx-text-fill: rgba(255,255,255,0.7);");

        PasswordField password = new PasswordField();
        password.setPromptText("Enter password");
        password.setStyle("-fx-background-radius: 30; -fx-padding: 12; -fx-background-color: #eeeeee;");

        Label status = new Label();
        status.setStyle("-fx-text-fill: white;");

        Label forgot = new Label("Forgot Password?");
        forgot.setStyle("-fx-text-fill: white; -fx-underline: true;");

        Button loginBtn = new Button("LOG IN");
        loginBtn.setPrefWidth(260);
        loginBtn.setStyle(
                "-fx-background-color: linear-gradient(to right, #4facfe, #a18cd1);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 30;" +
                        "-fx-padding: 12;"
        );

        Button signupBtn = new Button("Create Account");
        signupBtn.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: white;" +
                        "-fx-underline: true;"
        );

        // LOGIN
        loginBtn.setOnAction(e -> {

            String userEmail = email.getText().trim();
            String userPass = password.getText().trim();

            if(userEmail.isEmpty() || userPass.isEmpty()) {
                status.setText("Enter all fields");
                return;
            }

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/smart_city", "root", "Sololife22@"
                );

                String query = "SELECT * FROM users WHERE email=? AND password=?";
                PreparedStatement ps = con.prepareStatement(query);

                ps.setString(1, userEmail);
                ps.setString(2, userPass);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    status.setText("Login Success");

                    //SAVE FOR AUTO LOGIN
                    BufferedWriter bw = new BufferedWriter(new FileWriter("remember.txt"));
                    bw.write(userEmail);
                    bw.close();

                    new CityPage().show(stage);
                } else {
                    status.setText("Invalid Email or Password");
                }

                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                status.setText("Database Error");
            }
        });

        //  SIGNUP
        signupBtn.setOnAction(e -> {

            String userEmail = email.getText().trim();
            String userPass = password.getText().trim();

            if(userEmail.isEmpty() || userPass.isEmpty()) {
                status.setText("Enter all fields");
                return;
            }

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/smart_city", "root", "Sololife22@"
                );

                String checkQuery = "SELECT * FROM users WHERE email=?";
                PreparedStatement checkPs = con.prepareStatement(checkQuery);
                checkPs.setString(1, userEmail);

                ResultSet rs = checkPs.executeQuery();

                if(rs.next()) {
                    status.setText("User already exists");
                } else {
                    String insertQuery = "INSERT INTO users(email, password) VALUES(?, ?)";
                    PreparedStatement ps = con.prepareStatement(insertQuery);

                    ps.setString(1, userEmail);
                    ps.setString(2, userPass);

                    ps.executeUpdate();
                    status.setText("Signup Successful ✔");
                }

                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                status.setText("Database Error");
            }
        });

        //  FORGOT PASSWORD
        forgot.setOnMouseClicked(e -> {

            TextInputDialog emailDialog = new TextInputDialog();
            emailDialog.setTitle("Forgot Password");
            emailDialog.setHeaderText("Enter your email");

            emailDialog.showAndWait().ifPresent(userEmail -> {

                TextInputDialog passDialog = new TextInputDialog();
                passDialog.setTitle("Reset Password");
                passDialog.setHeaderText("Enter new password");

                passDialog.showAndWait().ifPresent(newPass -> {

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        Connection con = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/smart_city", "root", "Sololife22@"
                        );

                        String query = "UPDATE users SET password=? WHERE email=?";
                        PreparedStatement ps = con.prepareStatement(query);

                        ps.setString(1, newPass);
                        ps.setString(2, userEmail);

                        int rows = ps.executeUpdate();

                        if(rows > 0) {
                            status.setText("Password Updated ✔");
                        } else {
                            status.setText("Email not found");
                        }

                        con.close();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        status.setText("Error updating password");
                    }
                });
            });
        });

        // ADD UI
        card.getChildren().addAll(
                userLabel, email,
                passLabel, password,
                forgot,
                loginBtn,
                signupBtn,
                status
        );

        root.getChildren().addAll(icon, title, subtitle, card);

        Scene scene = new Scene(root, 400, 650);
        stage.setTitle("Smart City");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}