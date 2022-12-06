package com.example.bookmanagementproject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.*;

public class CheckoutBooks {
    @FXML
    private TextField ISBNfield;

    @FXML
    private Button cancelField;

    @FXML
    private Button checkoutField;

    @FXML
    private TextField usernameField;

    private Connection connect;
    private PreparedStatement preparedStatement;

    private ResultSet resultSet;
    int result = 0;
    public int assignBook(String username, String ISBN){
        String sql = "insert into Assigned values(?,?,?,?)";
        String sql1 = "update Book set inventory = ? where ISBN = ?";
        String sql2 = "select inventory from Book where ISBN = ?";
        connect = Database.connectDB();
        final Alert[] alert = new Alert[1];
        try {
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,ISBN);
            preparedStatement.setDate(3, Date.valueOf(java.time.LocalDate.now()));
            preparedStatement.setDate(4,Date.valueOf(java.time.LocalDate.now().plusDays(14)));
            result = preparedStatement.executeUpdate();
            preparedStatement = connect.prepareStatement(sql2);
            preparedStatement.setString(1, ISBN);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                preparedStatement = connect.prepareStatement(sql1);
                int inventory = resultSet.getInt(1);
                preparedStatement.setInt(1, inventory-1);
                preparedStatement.setString(2, ISBN);
                preparedStatement.executeUpdate();
            }
            return 1;
        } catch (SQLException e) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    alert[0] = new Alert(Alert.AlertType.ERROR);
                    alert[0].setTitle("Admin message");
                    alert[0].setHeaderText(null);
                    alert[0].setContentText("There is no user or book to checkout.");
                    alert[0].showAndWait();
                    new Alert(Alert.AlertType.ERROR).showAndWait();
                    Platform.exit();
                }
            });
            return 0;
        }catch(Exception e){
            return 0;
        }
    }


    public void assignSave(){
        assignBook(usernameField.getText(), ISBNfield.getText());
        checkoutField.getScene().getWindow().hide();
    }

    public void cancel(){
        cancelField.getScene().getWindow().hide();
    }

}
