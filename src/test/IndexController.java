/*
 * 9/15/2017
 * Cse
 * Testing of codes ni kuya pawii XD
 */
package test;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Csearfarf
 */
public class IndexController implements Initializable {

    // generated by "Make Controller"
    @FXML
    private TableView<studentDetails> tableStudentInformation;
    @FXML
    private TableColumn<studentDetails, String> columnStudentNumber;
    @FXML
    private TableColumn<studentDetails, String> columnFirstname;
    @FXML
    private TableColumn<studentDetails, String> columnLastname;
    @FXML
    private TableColumn<studentDetails, String> columnContactNumber;
    @FXML
    private TableColumn<studentDetails, String> columnAddress;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton btnLoad;
    
    private ObservableList<studentDetails>data;
    private dbConnection dc;
    @FXML
    private JFXTextField student_num;
    @FXML
    private JFXTextField firstname;
    @FXML
    private JFXTextField lastname;
    @FXML
    private JFXTextField contact_number;
    @FXML
    private JFXTextField address;
    @FXML
    private Pane pane;
    @FXML
    private Pane pane2;
    @FXML
    private JFXButton btnLoad1;
    @FXML
    private JFXButton addBtn;
    
    

  @FXML
    private void handleExit(MouseEvent event) {
          // the program will close;
      System.exit(0);
    }
    public void load(){// select * accounts
        pane.visibleProperty().set(false);
        
           try{
        Connection conn= dc.Connect();
        data=FXCollections.observableArrayList();
        // Executee query and store result in a result set;
        ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM student_information");
        while(rs.next()){
            //getting each data from db by column number
            data.add(new studentDetails(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));             
        }
        
        
        }catch(SQLException ex){
        System.err.println("ERROR query"+ex);
        }
        columnStudentNumber.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        columnFirstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        columnLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        columnContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tableStudentInformation.setItems(null);
        tableStudentInformation.setItems(data);
        
    }
    public void clearInformation() { // clear inputs
        student_num.clear();
        firstname.clear();
        lastname.clear();
        contact_number.clear();
        address.clear();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // preparation
         dc= new dbConnection();
         // load data from mysql database to tableview
         load();
    }    

    @FXML
    private void saveInformation(ActionEvent event) {
        dbConnection connect = new dbConnection();
        try{
        connect.connection("insert into student_information (studentNumber,firstName,lastName,contactNumber,address) values('"+student_num.getText()+"','"+firstname.getText()+"','"+lastname.getText()+"','"+contact_number.getText()+"','"+address .getText()+"')");
        JOptionPane.showMessageDialog(null,"Created successfully !");
        clearInformation();
        //load();
        }
        catch(Exception ex){}
        load();
                                 
    
    }
    
    

    @FXML
    private void loadInformation(ActionEvent event) {
        // load data from mysql database to tableview
            load();
        
    }

    @FXML
    private void addButton(ActionEvent event) {
    }
    
}
