package com.example.bookmanagementproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class SearchBookForUser {
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

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Books> searchTable;

    private Connection connect;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    public ObservableList<Books> dataList(){

        ObservableList<Books> listBooks = FXCollections.observableArrayList();

        String sql = "select * from Book where bookName like '%" + searchField.getText() + "%'";
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

        searchTable.setItems(listBook);
    }

    public void search(){
        showBooksUser();
    }

}
