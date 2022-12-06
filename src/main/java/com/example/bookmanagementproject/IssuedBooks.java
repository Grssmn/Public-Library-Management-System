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
import java.util.Date;
import java.util.ResourceBundle;

public class IssuedBooks implements Initializable {
    @FXML
    private TableColumn<issuedAll, String> col_ISBN;

    @FXML
    private TableColumn<issuedAll, Date> col_checkoutDate;

    @FXML
    private TableColumn<issuedAll, Date> col_returnDate;

    @FXML
    private TableColumn<issuedAll, String> col_username;

    @FXML
    private TableView<issuedAll> issuedTable;


    private Connection connect;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    public ObservableList<issuedAll> dataList(){

        ObservableList<issuedAll> listIssued = FXCollections.observableArrayList();

        String sql = "select * from Assigned";
        connect = Database.connectDB();
        try {
            issuedAll issuedBook;
            preparedStatement = connect.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                issuedBook = new issuedAll(resultSet.getString("usernameFK"), resultSet.getString("ISBNFK"), resultSet.getDate("checkoutDate"), resultSet.getDate("returnDate"));
                listIssued.add(issuedBook);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listIssued;
    }

    private ObservableList<issuedAll> issueList;

    public void showBooks(){
        issueList = dataList();
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_ISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        col_checkoutDate.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
        col_returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        issuedTable.setItems(issueList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showBooks();
    }
}
