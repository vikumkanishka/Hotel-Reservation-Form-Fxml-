package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.RoomInfoDTO;
import service.RoomServiceImpl;
import service.RoomInfoService;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomInfoFormController implements Initializable {

    public TextField txtRoomID;
    public ComboBox cmbType;
    public TextField txtPricePerNight;
    public ComboBox cmbFloor;
    public ComboBox cmbMaxGuests;
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

    RoomInfoService roomInfoService  = new RoomServiceImpl();

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadDetails();
    }

    private void loadDetails(){
        tblRoomInfo.setItems(roomInfoService.getAllRooms());
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
        String roomId = txtRoomID.getText();
        String roomType = String.valueOf(cmbType.getValue());
        String description = txtDescription.getText();
        double pricePerNight = Double.parseDouble(txtPricePerNight.getText());
        int maxGuests = Integer.parseInt(String.valueOf(cmbMaxGuests.getValue()));
        int floor = Integer.parseInt(String.valueOf(cmbFloor.getValue()));
        boolean availability = radioAvailable.isSelected();

        roomInfoService.updateRoom(roomId,roomType,description,pricePerNight,maxGuests,floor,availability);
        loadDetails();

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        roomInfoService.deleteRoom(txtRoomID.getText());
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

        roomInfoService.addRoomDetails(roomId,roomType,description,pricePerNight,maxGuests,floor,availability);
    }
}
