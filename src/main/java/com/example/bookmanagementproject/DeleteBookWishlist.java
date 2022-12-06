package com.example.bookmanagementproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteBookWishlist {
    @FXML
    private Button cancel;

    @FXML
    private Button delete;

    private Connection connect;
    private PreparedStatement preparedStatement;

    public void deleteBook(){
        String sql = "delete from wishlists where bookname = ? and user = ?";
        connect = Database.connectDB();
        try {
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1,wishlistUser.book.getBookName());
            preparedStatement.setString(2,LoginController.usernameUsed);
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
