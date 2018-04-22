/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forgotpassword;

import DButils.ConnectDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author Rupak Kalita
 */
 //main controller
public class ForgotController implements Initializable {

    @FXML
    private TextField email;

    @FXML
    private TextField dob;

    @FXML
    private Label retpass;
    @FXML
    private ListView<String> listview;

    private ConnectDB connectDB;

//    Boolean isReadyForSubmission = false;
    public String email1;
    public String dob1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connectDB = new ConnectDB();
    }
  //login  
    public boolean isLogin() throws SQLException{
    PreparedStatement pt=null;
    ResultSet rs=null;
    String query="select * from tbl_signmember where email=? and dob=?";
    try{
       Connection conn = ConnectDB.getConnections();
        pt = conn.prepareStatement(query);
        pt.setString(1, email1);
        pt.setString(2, dob1);
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
   public void getpass(ActionEvent event) throws SQLException {
            try{
                email1 = email.getText();
                dob1 = dob.getText();
         if(isLogin()){


         String id = email1;


        String sql = "SELECT * FROM tbl_signmember WHERE email = '" + id + "'";

        Connection conn = ConnectDB.getConnections();
        PreparedStatement pst = conn.prepareStatement(sql);

        ResultSet rst = pst.executeQuery();



        while (rst.next()) {
            String pass = rst.getString("password");

            retpass.setText("Your password is : " + pass);
            }
         }
        else{
         retpass.setText("oops....!! wrong input...try again");
         }
     }
         catch(SQLException e){
         retpass.setText("00ps....!! wrong input ");
     }
   }

}
