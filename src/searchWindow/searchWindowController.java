    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

    package searchWindow;


    import javafx.beans.property.SimpleStringProperty;
    import DButils.ConnectDB;
    import java.io.IOException;
    import java.net.URL;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Optional;
    import java.util.ResourceBundle;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.Alert;
    import javafx.scene.control.ButtonType;
    import javafx.scene.control.Label;
    import javafx.scene.control.ListView;
    import javafx.scene.control.TextField;
    import javafx.scene.image.Image;
    import javafx.stage.Stage;
    import javafx.stage.StageStyle;
    import javafx.scene.control.TableColumn;
    import javafx.scene.control.cell.PropertyValueFactory;
    import java.sql.*;
    import java.util.logging.Level;
    import java.util.logging.Logger;
    import javafx.scene.control.TableView;
    import DButils.ConnectDB;
    import java.io.IOException;
    import java.net.URL;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.Optional;
    import java.util.ResourceBundle;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;




    /**
     *
     * @author abhijeetsinghkhangarot
     */
    public class searchWindowController implements Initializable {

        @FXML
        private TableView<Book> atableview;
        @FXML
        private TextField searchId;
        @FXML
        private TextField memberId;
        @FXML
        private TableColumn<Book, String> titlecol;
        @FXML
        private TableColumn<Book, String> idcol;
        @FXML
        private TableColumn<Book, String> authorcol;
        @FXML
        private TableColumn<Book, String> pubcol;

        @FXML
        private TableView<Member> tableviewmember;
        @FXML
        private TableColumn<Member, String> namecol;
        @FXML
        private TableColumn<Member, String> idmcol;
        @FXML
        private TableColumn<Member, String> mobcol;
        @FXML
        private TableColumn<Member, String> emailcol;

        private ConnectDB connectDB;
        public String sql_books, sql_members;

        ObservableList<Book> list= FXCollections.observableArrayList();
        ObservableList<Member> listm= FXCollections.observableArrayList();


        @Override
        public void initialize(URL url, ResourceBundle rb) {
        }

        public void startBook() {
            System.out.println("fuckoff");
            initcolBook();
            connectDB = new ConnectDB();
            LoadDataBook();
        }

        public void startMem() {
            initcolMem();
            connectDB = new ConnectDB();
            LoadDataMem();
        }

        @FXML
        public void search(ActionEvent event) throws SQLException{

            String id = searchId.getText();
            sql_books = "SELECT * FROM tbl_addbook WHERE title LIKE '%"+ id + "%' OR author LIKE '%"+ id +"%' OR publisher LIKE '%"+ id +"%'";
            Connection conn = ConnectDB.getConnections();
            PreparedStatement pst1 = conn.prepareStatement(sql_books);
            ResultSet rst = pst1.executeQuery();
            startBook();  
        }

        @FXML
        public void member(ActionEvent event) throws SQLException{

            String id = memberId.getText();
            sql_members = "SELECT * FROM tbl_addmember WHERE name LIKE '%"+ id + "%' OR email LIKE '%"+ id +"%' OR mobile LIKE '%"+ id +"%'";
            Connection conn = ConnectDB.getConnections();
            PreparedStatement pst = conn.prepareStatement(sql_members);
            ResultSet rst1 = pst.executeQuery();
            startMem();
        }

        public void initcolBook(){
            titlecol.setCellValueFactory(new PropertyValueFactory<>("title"));
            idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
            authorcol.setCellValueFactory(new PropertyValueFactory<>("author"));
            pubcol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        }

        public void initcolMem(){
            namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
            idmcol.setCellValueFactory(new PropertyValueFactory<>("id"));
            mobcol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
            emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        }

        public void LoadDataBook(){
            try{
                list.removeAll(list);
                String sql = sql_books;
                Connection conn = ConnectDB.getConnections();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                while(rs.next()){
                    String title = rs.getString("title");
                    String id = rs.getString("id");
                    String author = rs.getString("author");
                    String publisher = rs.getString("publisher");

                    list.add(new Book(title,id,author,publisher));
                }

            } catch (SQLException ex) {
                Logger.getLogger(searchWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }

            atableview.getItems().setAll(list);

        }

        public void LoadDataMem(){
            try{
                listm.removeAll(listm);
                String sql = sql_members;
                Connection conn = ConnectDB.getConnections();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                while(rs.next()){
                    String name = rs.getString("name");
                    String id = rs.getString("id");
                    String mobile = rs.getString("mobile");
                    String email = rs.getString("email");

                    listm.add(new Member(name,id,mobile,email));
                }

            } catch (SQLException ex) {
                Logger.getLogger(searchWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }

            tableviewmember.getItems().setAll(listm);
        }

        public static class Book{

            private final SimpleStringProperty title;
            private final SimpleStringProperty id;
            private final SimpleStringProperty author;
            private final SimpleStringProperty publisher;

            public Book(String title,String id, String author,String pub){
                this.title = new SimpleStringProperty(title);
                this.id = new SimpleStringProperty(id);
                this.author = new SimpleStringProperty(author);
                this.publisher = new SimpleStringProperty(pub);
            }

                public String getTitle() {
                    return title.get();
                }


                public String getId() {
                    return id.get();
                }


                public String getAuthor() {
                    return author.get();
                }


                public String getPublisher() {
                    return publisher.get();
                }
            }

        public static class Member{

            private final SimpleStringProperty name;
            private final SimpleStringProperty id;
            private final SimpleStringProperty mobile;
            private final SimpleStringProperty email;

            public Member(String name,String id, String mobile,String email){
                this.name = new SimpleStringProperty(name);
                this.id = new SimpleStringProperty(id);
                this.mobile = new SimpleStringProperty(mobile);
                this.email = new SimpleStringProperty(email);
            }

                public String getName() {
                    return name.get();
                }


                public String getId() {
                    return id.get();
                }


                public String getMobile() {
                    return mobile.get();
                }


                public String getEmail() {
                    return email.get();
                }
            }
    }
