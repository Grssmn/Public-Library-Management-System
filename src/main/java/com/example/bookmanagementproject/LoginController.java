package com.example.bookmanagementproject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class LoginController {
    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    private Connection connect;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public static String usernameUsed;
    public int loginPanel(String username, String password){
        if(Objects.equals(username, "admin")){
            String sql = "select * from Admin where username = ? and password = ?";
            connect = Database.connectDB();

            try {
                preparedStatement = connect.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                final Alert[] alert = new Alert[1];
                if (username.isEmpty() || password.isEmpty()){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            alert[0] = new Alert(Alert.AlertType.ERROR);
                            alert[0].setTitle("Admin message");
                            alert[0].setHeaderText(null);
                            alert[0].setContentText("Please fill in all the blanks.");
                            alert[0].showAndWait();
                        }
                    });
                    return 0;
                }
                else {
                    if (resultSet.next()){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                alert[0] = new Alert(Alert.AlertType.INFORMATION);
                                alert[0].setTitle("Admin message");
                                alert[0].setHeaderText(null);
                                alert[0].setContentText("Successfully logged in.");
                                alert[0].showAndWait();
                                loginButton.getScene().getWindow().hide();
                                Parent root = null;
                                try {
                                    root = FXMLLoader.load(getClass().getResource("DashboardAdmin.fxml"));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                                Stage stage = new Stage();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            }
                        });

                        return 1;
                    }else{
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                alert[0] = new Alert(Alert.AlertType.ERROR);
                                alert[0].setTitle("Admin message");
                                alert[0].setHeaderText(null);
                                alert[0].setContentText("Credentials are not found. Please try again.");
                                alert[0].showAndWait();
                            }
                        });
                        return 0;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            String sql = "select * from User where username = ? and password = ?";
            connect = Database.connectDB();

            try {
                preparedStatement = connect.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                final Alert[] alert = new Alert[1];
                if (username.isEmpty() || password.isEmpty()){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            alert[0] = new Alert(Alert.AlertType.ERROR);
                            alert[0].setTitle("Admin message");
                            alert[0].setHeaderText(null);
                            alert[0].setContentText("Please fill in all the blanks.");
                            alert[0].showAndWait();
                        }
                    });
                    return 0;
                }
                else {
                    if (resultSet.next()){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                alert[0] = new Alert(Alert.AlertType.INFORMATION);
                                alert[0].setTitle("Admin message");
                                alert[0].setHeaderText(null);
                                alert[0].setContentText("Successfully logged in.");
                                alert[0].showAndWait();
                                try {
                                    usernameUsed=resultSet.getString(1);
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                loginButton.getScene().getWindow().hide();
                                Parent root = null;
                                try {
                                    root = FXMLLoader.load(getClass().getResource("DashboardUser.fxml"));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                                Stage stage = new Stage();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            }
                        });
                        return 1;
                    }else{
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                alert[0] = new Alert(Alert.AlertType.ERROR);
                                alert[0].setTitle("Admin message");
                                alert[0].setHeaderText(null);
                                alert[0].setContentText("Credentials are not found. Please try again.");
                                alert[0].showAndWait();
                            }
                        });
                        return 0;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void login(){
        loginPanel(username.getText(), password.getText());
    }
}