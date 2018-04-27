
package MemberDetails;

import DButils.ConnectDB;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;

//membercontroller
public class MemberDetailsController implements Initializable {

    @FXML
    private TableView<Member> tableview;
    @FXML
    private TableColumn<Member, String> bookidcol;
    @FXML
    private TableColumn<Member, String> namecol;
    @FXML
    private TableColumn<Member, String> timecol;
    @FXML
    private TableColumn<Member, String> countcol;

    private ConnectDB connectDB;

    ObservableList<Member> list= FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initcol();
        connectDB = new ConnectDB();
        LoadData();

        tableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    public void initcol(){
        bookidcol.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("memberInput"));
        timecol.setCellValueFactory(new PropertyValueFactory<>("issueTime"));
        countcol.setCellValueFactory(new PropertyValueFactory<>("renew_count"));
    }

    public void LoadData(){
        String sql = "SELECT * FROM tbl_issue";
        try {
            Connection conn = ConnectDB.getConnections();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                String bookID = rs.getString("bookID");
                String memberInput = rs.getString("memberInput");
                String issueTime = rs.getString("issueTime");
                String renew_count = rs.getString("renew_count");

                list.add(new Member(bookID,memberInput,issueTime,renew_count));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemberDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableview.getItems().setAll(list);
    }
//member
    public static class Member{

        private final SimpleStringProperty bookID;
        private final SimpleStringProperty memberInput;
        private final SimpleStringProperty issueTime;
        private final SimpleStringProperty renew_count;

        public Member(String bookID,String memberInput, String issueTime,String renew_count){
            this.bookID = new SimpleStringProperty(bookID);
            this.memberInput = new SimpleStringProperty(memberInput);
            this.issueTime = new SimpleStringProperty(issueTime);
            this.renew_count = new SimpleStringProperty(renew_count);
        }

        public String getBookID() {
            return bookID.get();
        }

        public String getMemberInput() {
            return memberInput.get();
        }

        public String getIssueTime() {
            return issueTime.get();
        }

        public String getRenew_count() {
            return renew_count.get();
        }


    }
}
