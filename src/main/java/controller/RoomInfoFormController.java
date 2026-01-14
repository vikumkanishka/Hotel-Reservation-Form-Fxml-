package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.RoomInfoDTO;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RoomInfoFormController implements Initializable {

    public TextField txtRoomID;
    public ComboBox cmbType;
    public TextField txtPricePerNight;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnAdd;
    public ComboBox cmbFloor;
    public ComboBox cmbMaxGuests;
    public Label lblAvailability;
    public RadioButton radioAvailable;
    public RadioButton radioUnavailable;
    public TextArea txtDescription;
    @FXML
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colFloor;

    @FXML
    private TableColumn<?, ?> colMaxGuests;

    @FXML
    private TableColumn<?, ?> colPricePerNight;

    @FXML
    private TableColumn<?, ?> colRoomId;

    @FXML
    private TableColumn<?, ?> colRoomType;

    @FXML
    private TableView<RoomInfoDTO> tblRoomInfo;

    ObservableList <RoomInfoDTO> observableList = FXCollections.observableArrayList();

    RoomController roomController = new RoomController();

    @FXML
    void btnReloadOnAction(ActionEvent event) {


        tblRoomInfo.setItems(observableList);
        loadDetails();

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadDetails();

        ObservableList <String> types= FXCollections.observableArrayList("Single","Double","Family","Suite");
        cmbType.setItems(types);

        ObservableList maxGuests= FXCollections.observableArrayList("1","2","3","4","5");
        cmbMaxGuests.setItems(maxGuests);

        ObservableList floors= FXCollections.observableArrayList("1","2","3","4","5","6");
        cmbFloor.setItems(floors);

        ToggleGroup toggleGroup = new ToggleGroup();
        radioAvailable.setToggleGroup(toggleGroup);
        radioUnavailable.setToggleGroup(toggleGroup);


        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPricePerNight.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        colMaxGuests.setCellValueFactory(new PropertyValueFactory<>("maxGuests"));
        colFloor.setCellValueFactory(new PropertyValueFactory<>("floor"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));


    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_reservation_system","root","1234");

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE rooms SET type = ?, price_per_night = ?, max_guests = ?, availability = ?, description = ?, floor = ? WHERE room_id = ?");

            preparedStatement.setObject(1,cmbType.getValue());
            preparedStatement.setObject(2,Double.parseDouble(txtPricePerNight.getText()));
            preparedStatement.setObject(3,cmbMaxGuests.getValue());
            preparedStatement.setObject(4,radioAvailable.isSelected());
            preparedStatement.setObject(5,txtDescription.getText());
            preparedStatement.setObject(6,cmbFloor.getValue());
            preparedStatement.setObject(7,txtRoomID.getText());

            preparedStatement.executeUpdate();
            loadDetails();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_reservation_system","root","1234");

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM rooms WHERE room_id = ? ");

            preparedStatement.setObject(1,txtRoomID.getText());
            preparedStatement.executeUpdate();
            loadDetails();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        observableList.clear();

        String roomId = txtRoomID.getText();
        String roomType = String.valueOf(cmbType.getValue());
        String description = txtDescription.getText();
        double pricePerNight = Double.parseDouble(txtPricePerNight.getText());
        int maxGuests = Integer.parseInt(String.valueOf(cmbMaxGuests.getValue()));
        int floor = Integer.parseInt(String.valueOf(cmbFloor.getValue()));
        boolean availability = radioAvailable.isSelected();


        roomController.AddRoomDetails(roomId,roomType,description,pricePerNight,maxGuests,floor,availability);

        
    }

    private void loadDetails(){
        observableList = roomController.getAllRooms();
        tblRoomInfo.setItems(roomController.getAllRooms());
    }
}
