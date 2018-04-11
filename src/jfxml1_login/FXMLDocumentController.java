/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxml1_login;

import DButils.ConnectDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Rupak Kalita
 */
public class FXMLDocumentController implements Initializable {
    //public loginModel lm = new loginModel();
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Label label;
    @FXML
    private TextField txtusername;
    @FXML
    private PasswordField txtpassword;
    @FXML
    private Hyperlink signin;

    private ConnectDB connectDB;
    
    public String emaill;
    public String paswordd;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connectDB = new ConnectDB();
    }    
    
    public boolean isLogin() throws SQLException{
    PreparedStatement pt=null;
    ResultSet rs=null;
    String query="select * from tbl_signmember where email=? and password=?";
    try{
       Connection conn = ConnectDB.getConnections();
        pt = conn.prepareStatement(query);
        pt.setString(1, emaill);
        pt.setString(2, paswordd);
        rs=pt.executeQuery();
        if(rs.next()){
          
           return true;
       }else{
       
           return false;
       }
    }catch(Exception e){
        return false;
    }finally{
        rs.close();
        pt.close();
    }
    
    }  

    @FXML
    private void signIn(ActionEvent event) throws IOException {
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/Singup/signup.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("SIGN IN");
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @FXML
    private void forgot(ActionEvent event) throws IOException {
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/forgotpassword/forgot.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Forgot password");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void login(ActionEvent event) throws IOException, SQLException {
         try{
                emaill = txtusername.getText();
                paswordd = txtpassword.getText();
                if(isLogin()){
                    Parent root = FXMLLoader.load(getClass().getResource("/library_help/Library.fxml"));
                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.setTitle("Welcome");
                    stage.setScene(new Scene(root));
                    stage.show();   
       
           
            }
        else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("oops !!....wrong input");
            alert.showAndWait();
            return;
         } 
     }
         catch(SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("oops....!!");
            alert.showAndWait();
            return;
     }
        Stage stage = (Stage)anchorpane.getScene().getWindow();
        stage.close();
   }

    }
    
      

