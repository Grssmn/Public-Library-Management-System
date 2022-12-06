package com.example.bookmanagementproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteBook {
    @FXML
    private Button cancel;

    @FXML
    private Button delete;

    private Connection connect;
    private PreparedStatement preparedStatement;

    public void deleteBook(){
        String sql = "delete from Book where ISBN = ?";
        connect = Database.connectDB();
        try {
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1,AllBooks.book.getISBN());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(){
        deleteBook();
        delete.getScene().getWindow().hide();
    }

    public void cancel(){
        cancel.getScene().getWindow().hide();
    }
}
