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

public class AllUsers implements Initializable {

    @FXML
    private Button add;

    @FXML
    private TableColumn<Users, String> col_firstName;

    @FXML
    private TableColumn<Users, String> col_lastName;

    @FXML
    private TableColumn<Users, String> col_password;

    @FXML
    private TableColumn<Users, String> col_username;

    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private TableView<Users> usersTable;

    private Connection connect;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    public ObservableList<Users> dataList(){

        ObservableList<Users> listUsers = FXCollections.observableArrayList();

        String sql = "select * from User";
        connect = Database.connectDB();
        try {
            Users users;
            preparedStatement = connect.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                users = new Users(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("username"), resultSet.getString("password"));
                listUsers.add(users);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUsers;
    }

    private ObservableList<Users> listUser;

    public void showUsers(){
        listUser = dataList();
        col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<>("password"));

        usersTable.setItems(listUser);
    }
    public static Users user;
    public void selectUser(ActionEvent event){
        user = usersTable.getSelectionModel().getSelectedItem();
        Alert alert;

        if(event.getSource() == add){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("addUser.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if(event.getSource() == edit){
            if (user == null){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Admin message");
                alert.setHeaderText(null);
                alert.setContentText("Please choose a user to edit.");
                alert.showAndWait();
            }else{
                try{
                    Parent root = FXMLLoader.load(getClass().getResource("editUser.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }else if(event.getSource() == delete){
            if (user == null){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Admin message");
                alert.setHeaderText(null);
                alert.setContentText("Please choose a user to delete.");
                alert.showAndWait();
            }else{
                try{
                    Parent root = FXMLLoader.load(getClass().getResource("deleteUser.fxml"));
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

    public void refreshPressed(){
        showUsers();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showUsers();
    }
}
