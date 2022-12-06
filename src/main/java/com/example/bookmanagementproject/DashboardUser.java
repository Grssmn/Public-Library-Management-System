package com.example.bookmanagementproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardUser implements Initializable {
    @FXML
    private Button books;

    @FXML
    private Button checkoutBook;

    @FXML
    private Label labelTopUser;

    @FXML
    private Button logout;

    @FXML
    private Button ownedBooks;

    @FXML
    private Button returnBook;

    @FXML
    private Button search;
    @FXML
    private Button wishlist;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelTopUser.setText("Welcome " + LoginController.usernameUsed + "!");
    }

    public void booksPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("AllBooksUser.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkoutPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("checkoutBookForUser.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void returnPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("returnBookForUser.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void issuedPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("issuedBookForUser.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("searchBookForUser.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logoutPressed(){
        try{
            logout.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @FXML
    void wishlistPressed() {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("wishlistUser.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
