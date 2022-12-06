package com.example.bookmanagementproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditBook {
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

    public void editUser(){
        String ISBN = AllBooks.book.getISBN();
        String sql = "update Book set bookName = ?, author = ?, ISBN = ?, category = ?, inventory = ? where ISBN = ?";
        connect = Database.connectDB();
        try {
            preparedStatement = connect.prepareStatement(sql);
            if (bookNameArea.getText().isEmpty())
                preparedStatement.setString(1, AllBooks.book.getBookName());
            else
                preparedStatement.setString(1,bookNameArea.getText());

            if (authorArea.getText().isEmpty())
                preparedStatement.setString(2, AllBooks.book.getAuthor());
            else
                preparedStatement.setString(2,authorArea.getText());
            if (ISBNArea.getText().isEmpty())
                preparedStatement.setString(3, AllBooks.book.getISBN());
            else
                preparedStatement.setString(3,ISBNArea.getText());
            if (categoryArea.getText().isEmpty())
                preparedStatement.setString(4, AllBooks.book.getCategory());
            else
                preparedStatement.setString(4,categoryArea.getText());
            if (inventoryArea.getText().isEmpty())
                preparedStatement.setInt(5, AllBooks.book.getInventory());
            else
                preparedStatement.setString(5,inventoryArea.getText());
            preparedStatement.setString(6, ISBN);
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
    public void updateBook() {
        editUser();
        saveArea.getScene().getWindow().hide();
    }

}
