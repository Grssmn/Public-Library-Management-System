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

public class ReturnBookForUser{
    @FXML
    private TextField ISBNfield;

    @FXML
    private Button cancelField;

    @FXML
    private Label labelTopReturn;

    @FXML
    private Button returnField;

    private Connection connect;
    private PreparedStatement preparedStatement;

    private Statement statement;

    private ResultSet resultSet;

    public void returnBook(){
        String sql = "delete from Assigned where usernameFK = ? and ISBNFK = ?";
        String rows = "select count(*) from Assigned";
        String sql1 = "update Book set inventory = ? where ISBN = ?";
        String sql2 = "select inventory from Book where ISBN = ?";
        Alert alert;
        int numRowsBefore = 0;
        int numRowsAfter = 0;
        connect = Database.connectDB();
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(rows);
            if (resultSet.next())
                numRowsBefore = resultSet.getInt(1);
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1,LoginController.usernameUsed);
            preparedStatement.setString(2,ISBNfield.getText());
            preparedStatement.executeUpdate();
            resultSet = statement.executeQuery(rows);
            if(resultSet.next())
                numRowsAfter = resultSet.getInt(1);
            resultSet.close();
            statement.close();
            if(numRowsBefore == numRowsAfter+1){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Admin message");
                alert.setHeaderText(null);
                alert.setContentText("Book returned successfully.");
                alert.showAndWait();
                preparedStatement = connect.prepareStatement(sql2);
                preparedStatement.setString(1, ISBNfield.getText());
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    preparedStatement = connect.prepareStatement(sql1);
                    int inventory = resultSet.getInt(1);
                    System.out.println(inventory);
                    preparedStatement.setInt(1, inventory+1);
                    preparedStatement.setString(2, ISBNfield.getText());
                    preparedStatement.executeUpdate();
                }
            }else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Admin message");
                alert.setHeaderText(null);
                alert.setContentText("Either user does not have this checked out book or there is a typo in your fields.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Admin message");
            alert.setHeaderText(null);
            alert.setContentText("Unknown error happened.");
            alert.showAndWait();
        }
    }


    public void returnSave(){
        returnBook();
        returnField.getScene().getWindow().hide();
    }

    public void cancel(){
        cancelField.getScene().getWindow().hide();
    }

}
