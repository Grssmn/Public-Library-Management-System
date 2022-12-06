package com.example.bookmanagementproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUser {
    @FXML
    private Button cancel;

    @FXML
    private Button delete;

    private Connection connect;
    private PreparedStatement preparedStatement;
    public void deleteUser(){
        String sql = "delete from User where username = ?";
        connect = Database.connectDB();
        try {
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1,AllUsers.user.getUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(){
        deleteUser();
        delete.getScene().getWindow().hide();
    }

    public void cancel(){
        cancel.getScene().getWindow().hide();
    }

}
