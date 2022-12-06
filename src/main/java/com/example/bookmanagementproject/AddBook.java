package com.example.bookmanagementproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.*;

public class AddBook {
    @FXML
    private TextField ISBNArea;

    @FXML
    private TextField authorArea;

    @FXML
    private TextField bookNameArea;

    @FXML
    private Button cancelArea;

    @FXML
    private TextField categoryArea;

    @FXML
    private TextField inventoryArea;

    @FXML
    private Button saveArea;

    private Connection connect;
    private PreparedStatement preparedStatement;

    public void addBook(){
        String sql = "insert into Book values(?,?,?,?,?)";
        connect = Database.connectDB();
        try {
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1,bookNameArea.getText());
            preparedStatement.setString(2,authorArea.getText());
            preparedStatement.setString(3,ISBNArea.getText());
            preparedStatement.setString(4,categoryArea.getText());
            preparedStatement.setString(5,inventoryArea.getText());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void saveBook(){
        addBook();
        saveArea.getScene().getWindow().hide();
    }

    public void cancel(){
        cancelArea.getScene().getWindow().hide();
    }
}
