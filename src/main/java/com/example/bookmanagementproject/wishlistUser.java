package com.example.bookmanagementproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class wishlistUser implements Initializable {

    @FXML
    private Button add;

    @FXML
    private TableView<Books> booksTable;

    @FXML
    private TableColumn<Books, String> col_author;

    @FXML
    private TableColumn<Books, String> col_bookName;

    @FXML
    private TableColumn<Books, String> col_category;

    @FXML
    private Button delete;

    @FXML
    private Button refresh;

    private Connection connect;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public ObservableList<Books> dataList(){

        ObservableList<Books> listBooks = FXCollections.observableArrayList();

        String sql = "select * from wishlists where user = ?";
        connect = Database.connectDB();
        try {
            Books books;
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, LoginController.usernameUsed);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                books = new Books(resultSet.getString("bookName"), resultSet.getString("author"), resultSet.getString("category"));
                listBooks.add(books);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listBooks;
    }

    private ObservableList<Books> listBook;

    public void showBooks(){
        listBook = dataList();
        col_bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_category.setCellValueFactory(new PropertyValueFactory<>("category"));

        booksTable.setItems(listBook);
    }
    public static Books book;

    @FXML
    void refreshPressed(ActionEvent event) {
        showBooks();
    }

    @FXML
    void selectBook(ActionEvent event) {
        book = booksTable.getSelectionModel().getSelectedItem();

        Alert alert;

        if(event.getSource() == add){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("addBookWishlist.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if(event.getSource() == delete){
            if (book == null){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Admin message");
                alert.setHeaderText(null);
                alert.setContentText("Please choose a book to delete.");
                alert.showAndWait();
            }else{
                try{
                    Parent root = FXMLLoader.load(getClass().getResource("deleteBookWishlist.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showBooks();
    }

}
