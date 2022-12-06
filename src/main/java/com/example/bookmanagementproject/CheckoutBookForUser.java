package com.example.bookmanagementproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CheckoutBookForUser implements Initializable {

    @FXML
    private TextField ISBNfield;

    @FXML
    private Button cancelField;

    @FXML
    private Button checkoutField;

    @FXML
    private Label labelTopUser;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelTopUser.setText("Checkout Book for " + LoginController.usernameUsed);
    }

    private Connection connect;
    private PreparedStatement preparedStatement;

    private ResultSet resultSet;
    public void assignBook(){
        String sql = "insert into Assigned values(?,?,?,?)";
        String sql1 = "update Book set inventory = ? where ISBN = ?";
        String sql2 = "select inventory from Book where ISBN = ?";
        connect = Database.connectDB();
        Alert alert;
        try {
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1,LoginController.usernameUsed);
            preparedStatement.setString(2,ISBNfield.getText());
            preparedStatement.setDate(3, Date.valueOf(java.time.LocalDate.now()));
            preparedStatement.setDate(4,Date.valueOf(java.time.LocalDate.now().plusDays(14)));
            preparedStatement.executeUpdate();
            preparedStatement = connect.prepareStatement(sql2);
            preparedStatement.setString(1, ISBNfield.getText());
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                preparedStatement = connect.prepareStatement(sql1);
                int inventory = resultSet.getInt(1);
                System.out.println(inventory);
                preparedStatement.setInt(1, inventory-1);
                preparedStatement.setString(2, ISBNfield.getText());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Admin message");
            alert.setHeaderText(null);
            alert.setContentText("There is no user or book to checkout.");
            alert.showAndWait();
        }
    }


    public void assignSave(){
        assignBook();
        checkoutField.getScene().getWindow().hide();
    }

    public void cancel(){
        cancelField.getScene().getWindow().hide();
    }
}
