package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.CustomerInfoDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerInfoFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colCustomerFirstName;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerLastName;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colRegDate;

    @FXML
    private DatePicker dtRegDate;

    @FXML
    private TableView<CustomerInfoDTO> tblCustomerInfo;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCustomerFirstName;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerLastName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhone;

    ObservableList<CustomerInfoDTO> observableList= FXCollections.observableArrayList();

    CustomerInfoService  customerInfoService = new CustomerController();

    @FXML
    void btnAddOnAction(ActionEvent event) {

        observableList.clear();

        String customerId = txtCustomerId.getText();
        String customerFirstName = txtCustomerFirstName.getText();
        String customerLastName = txtCustomerLastName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        String city =  txtCity.getText();
        String date = String.valueOf(dtRegDate.getValue());

        customerInfoService.addCustomer(customerId,customerFirstName,customerLastName,email,phone,address,city,date);

        loadtable();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        customerInfoService.deleteCustomer(txtCustomerId.getText());
        loadtable();
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadtable();
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colCustomerFirstName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        colCustomerLastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("registered_date"));
    }

    public void loadtable(){
        tblCustomerInfo.setItems(customerInfoService.getAllCustomers());
    }
}

