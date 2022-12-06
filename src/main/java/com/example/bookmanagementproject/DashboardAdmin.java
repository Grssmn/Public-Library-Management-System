package com.example.bookmanagementproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardAdmin implements Initializable {

    @FXML
    private Button books;

    @FXML
    private Button checkoutBook;

    @FXML
    private Button issuedBook;

    @FXML
    private Button logout;

    @FXML
    private Button returnBook;

    @FXML
    private Button users;

    @FXML
    private Button wishlist;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void usersPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("allUsers.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void booksPressed(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("allBooks.fxml"));

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
            Parent root = FXMLLoader.load(getClass().getResource("checkoutBooks.fxml"));

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
            Parent root = FXMLLoader.load(getClass().getResource("returnBooks.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("issuedBooks.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("wishlistAdmin.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
