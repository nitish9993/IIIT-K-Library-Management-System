   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddMember;

import DButils.ConnectDB;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author nitish kumar singh
 */
public class AddMemberController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField mobile;
    @FXML
    private JFXTextField email;

    private ConnectDB connectDB;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connectDB = new ConnectDB();
    }

    @FXML
    private void addbtn(ActionEvent event) throws SQLException {
        String mname = name.getText();
        String mid = id.getText();
        String mmobile = mobile.getText();
        String memail = email.getText();

        if(mname.isEmpty() || mid.isEmpty() || mmobile.isEmpty() || memail.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Fields must not be empty");
            alert.showAndWait();
            return;
        }

        String sql = "INSERT INTO tbl_addmember (name,id,mobile,email) VALUES(?,?,?,?)";
        Connection conn = ConnectDB.getConnections();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1,mname);
        pst.setString(2,mid);
        pst.setString(3,mmobile);
        pst.setString(4,memail);

        pst.execute();


        Stage stage = (Stage)anchorpane.getScene().getWindow();
        stage.close();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Member added successfully");
        alert.showAndWait();


    }


    @FXML
    private void cancelbtn(ActionEvent event) throws IOException {

        Stage stage = (Stage)anchorpane.getScene().getWindow();
        stage.close();
    }

}
