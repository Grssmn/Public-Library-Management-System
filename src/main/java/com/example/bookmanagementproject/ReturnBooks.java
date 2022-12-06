package com.example.bookmanagementproject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.*;

public class ReturnBooks {
    @FXML
    private TextField ISBNfield;

    @FXML
    private Button cancelField;

    @FXML
    private Button returnField;

    @FXML
    private TextField usernameField;

    private Connection connect;
    private PreparedStatement preparedStatement;

    private Statement statement;

    private ResultSet resultSet;

    public int returnBook(String username, String ISBN){
        String sql = "delete from Assigned where usernameFK = ? and ISBNFK = ?";
        String rows = "select count(*) from Assigned";
        String sql1 = "update Book set inventory = ? where ISBN = ?";
        String sql2 = "select inventory from Book where ISBN = ?";
        final Alert[] alert = new Alert[1];
        int numRowsBefore = 0;
        int numRowsAfter = 0;
        connect = Database.connectDB();
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(rows);
            if (resultSet.next())
                numRowsBefore = resultSet.getInt(1);
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,ISBN);
            preparedStatement.executeUpdate();
            resultSet = statement.executeQuery(rows);
            if(resultSet.next())
                numRowsAfter = resultSet.getInt(1);
            resultSet.close();
            statement.close();
            if(numRowsBefore == numRowsAfter+1){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        alert[0] = new Alert(Alert.AlertType.INFORMATION);
                        alert[0].setTitle("Admin message");
                        alert[0].setHeaderText(null);
                        alert[0].setContentText("Book returned successfully.");
                        alert[0].showAndWait();
                    }
                });
                preparedStatement = connect.prepareStatement(sql2);
                preparedStatement.setString(1, ISBN);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    preparedStatement = connect.prepareStatement(sql1);
                    int inventory = resultSet.getInt(1);
                    preparedStatement.setInt(1, inventory+1);
                    preparedStatement.setString(2, ISBN);
                    preparedStatement.executeUpdate();
                }
                return 1;
            }else{
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        alert[0] = new Alert(Alert.AlertType.ERROR);
                        alert[0].setTitle("Admin message");
                        alert[0].setHeaderText(null);
                        alert[0].setContentText("Either user does not have this checked out book or there is a typo in your fields.");
                        alert[0].showAndWait();
                    }
                });
                return 0;
            }
        } catch (SQLException e) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    alert[0] = new Alert(Alert.AlertType.ERROR);
                    alert[0].setTitle("Admin message");
                    alert[0].setHeaderText(null);
                    alert[0].setContentText("Unknown error happened.");
                    alert[0].showAndWait();
                }
            });
            return 0;
        }
    }


    public void returnSave(){
        returnBook(usernameField.getText(), ISBNfield.getText());
        returnField.getScene().getWindow().hide();
    }

    public void cancel(){
        cancelField.getScene().getWindow().hide();
    }
}
