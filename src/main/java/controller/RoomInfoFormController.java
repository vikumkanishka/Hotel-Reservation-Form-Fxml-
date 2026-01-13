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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @FXML
    void btnReloadOnAction(ActionEvent event) {

        loadDetails();

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDetails();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
    }

    private void loadDetails(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_reservation_system","root","200004602360");

            ResultSet resultSet = connection.prepareStatement("SELECT * FROM rooms").executeQuery();

            observableList.clear();

            while (resultSet.next()){
                String roomId = resultSet.getString("room_Id");
                String roomType = resultSet.getString("type");
                String description = resultSet.getString("description");
                double pricePerNight = resultSet.getDouble("price_Per_Night");
                int maxGuests = resultSet.getInt("max_Guests");
                int floor = resultSet.getInt("floor");
                boolean availability = resultSet.getBoolean("availability");

                RoomInfoDTO roomInfoDTO = new RoomInfoDTO(roomId,roomType,description,pricePerNight,maxGuests,floor,availability);

                observableList.add(roomInfoDTO);
            }

            colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
            colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colPricePerNight.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
            colMaxGuests.setCellValueFactory(new PropertyValueFactory<>("maxGuests"));
            colFloor.setCellValueFactory(new PropertyValueFactory<>("floor"));
            colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));

            tblRoomInfo.setItems(observableList);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
