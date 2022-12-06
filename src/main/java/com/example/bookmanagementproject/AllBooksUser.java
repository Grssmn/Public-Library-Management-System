package com.example.bookmanagementproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AllBooksUser implements Initializable {

    @FXML
    private TableView<Books> booksTableUser;

    @FXML
    private TableColumn<Books, String> col_ISBN;

    @FXML
    private TableColumn<Books, String> col_author;

    @FXML
    private TableColumn<Books, String> col_bookName;

    @FXML
    private TableColumn<Books, String> col_category;

    @FXML
    private TableColumn<Books, Integer> col_inventory;

    private Connection connect;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    public ObservableList<Books> dataList(){

        ObservableList<Books> listBooks = FXCollections.observableArrayList();

        String sql = "select * from Book";
        connect = Database.connectDB();
        try {
            Books books;
            preparedStatement = connect.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                books = new Books(resultSet.getString("bookName"), resultSet.getString("author"), resultSet.getString("ISBN"), resultSet.getString("category"), resultSet.getInt("inventory"));
                listBooks.add(books);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listBooks;
    }

    private ObservableList<Books> listBook;

    public void showBooksUser(){
        listBook = dataList();
        col_bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_ISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        col_inventory.setCellValueFactory(new PropertyValueFactory<>("inventory"));

        booksTableUser.setItems(listBook);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showBooksUser();
    }
}
