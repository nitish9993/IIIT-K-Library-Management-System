/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Addbook;

import DButils.ConnectDB;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.sql.*;
import javafx.stage.Stage;


public class AddBookController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField author;
    @FXML
    private JFXTextField publisher;

    private ConnectDB connectDB;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connectDB = new ConnectDB();
    }    

    @FXML
    private void addBook(ActionEvent event) throws SQLException {
        String mtitle = title.getText();
        String mid = id.getText();
        String mauthor = author.getText();
        String mpublisher = publisher.getText();
        
        if(mtitle.isEmpty() || mid.isEmpty() || mauthor.isEmpty() || mpublisher.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Fields must not be empty");
            alert.showAndWait();
            return;
        } 
        
        String sql = "INSERT INTO tbl_addbook (title,id,author,publisher) VALUES(?,?,?,?)";
        Connection conn = ConnectDB.getConnections();
        PreparedStatement pst = conn.prepareStatement(sql);
        
        pst.setString(1,mtitle);
        pst.setString(2,mid);
        pst.setString(3,mauthor);
        pst.setString(4,mpublisher);
        
        pst.execute();
        
        Stage stage = (Stage)anchorpane.getScene().getWindow();
        stage.close();
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Book added successfully");
            alert.showAndWait();
        
       
        
    }

    @FXML
    private void cancelbtn(ActionEvent event) {
        Stage stage = (Stage)anchorpane.getScene().getWindow();
        stage.close();
    }
    
}
