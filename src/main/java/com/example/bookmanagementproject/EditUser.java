package com.example.bookmanagementproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.*;

public class EditUser {
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

    public void editUser(){
        String username = AllUsers.user.getUsername();
            String sql = "update User set firstName = ?, lastName = ?, username = ?, password = ? where username = ?";
            connect = Database.connectDB();
            try {
                preparedStatement = connect.prepareStatement(sql);
                if (firstArea.getText().isEmpty())
                    preparedStatement.setString(1, AllUsers.user.getFirstName());
                else
                    preparedStatement.setString(1,firstArea.getText());

                if (lastArea.getText().isEmpty())
                    preparedStatement.setString(2, AllUsers.user.getLastName());
                else
                    preparedStatement.setString(2,lastArea.getText());
                if (usernameArea.getText().isEmpty())
                    preparedStatement.setString(3, AllUsers.user.getUsername());
                else
                    preparedStatement.setString(3,usernameArea.getText());
                if (passArea.getText().isEmpty())
                    preparedStatement.setString(4, AllUsers.user.getPassword());
                else
                    preparedStatement.setString(4,passArea.getText());
                preparedStatement.setString(5, username);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    @FXML
    void cancel(ActionEvent event) {
        cancelArea.getScene().getWindow().hide();
    }

    @FXML
    public void updateUser() {
        editUser();
        saveArea.getScene().getWindow().hide();
    }
}
