package com.example.bookmanagementproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.*;

public class AddUser {
    @FXML
    private Button cancelArea;

    @FXML
    private TextField firstArea;

    @FXML
    private TextField lastArea;

    @FXML
    private TextField passArea;

    @FXML
    private Button saveArea;

    @FXML
    private TextField usernameArea;

    private Connection connect;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    public void addUser(){
        String sql = "insert into User values(?,?,?,?)";
        connect = Database.connectDB();
        try {
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1,usernameArea.getText());
            preparedStatement.setString(2,passArea.getText());
            preparedStatement.setString(3,firstArea.getText());
            preparedStatement.setString(4,lastArea.getText());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void saveUser(){
        addUser();
        saveArea.getScene().getWindow().hide();
    }

    public void cancel(){
        cancelArea.getScene().getWindow().hide();
    }
}
