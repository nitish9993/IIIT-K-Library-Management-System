/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singup;

import DButils.ConnectDB;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rupak Kalita
 */
public class signupController implements Initializable {
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField repassword;
    @FXML
    private Hyperlink alreadymember;
    @FXML
    private TextField dob;
    @FXML
    private JFXButton register;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void alreadyMember(ActionEvent event) {
        Stage stage = (Stage)anchorpane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onRegister(ActionEvent event) throws SQLException {
        String memail = email.getText();
        String mpassword = password.getText();
        String mrepassword = repassword.getText();
        String mdob = dob.getText();
        
        if(memail.isEmpty() || mpassword.isEmpty() || mrepassword.isEmpty() || mdob.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Fields must not be empty");
            alert.showAndWait();
            return;
        } 
        
        String sql = "INSERT INTO tbl_signmember (email,password,repassword,dob) VALUES(?,?,?,?)";
        Connection conn = ConnectDB.getConnections();
        PreparedStatement pst = conn.prepareStatement(sql);
        
        pst.setString(1,memail);
        pst.setString(2,mpassword);
        pst.setString(3,mrepassword);
        pst.setString(4,mdob);
        
        pst.execute();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Member added successfully");
            alert.showAndWait();
        
        
        }
}
    

