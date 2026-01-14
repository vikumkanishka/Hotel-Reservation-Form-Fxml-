package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.RoomInfoDTO;

import java.sql.*;

public class RoomController {


    public ObservableList<RoomInfoDTO> getAllRooms() {

        ObservableList<RoomInfoDTO> observableList = FXCollections.observableArrayList();

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

            return observableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void AddRoomDetails(String roomId, String roomType, String description, double pricePerNight, int maxGuests, int floor, boolean availability) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_reservation_system","root","200004602360");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO rooms VALUES(?,?,?,?,?,?,?)");

            preparedStatement.setObject(1,roomId);
            preparedStatement.setObject(2,roomType);
            preparedStatement.setObject(6,description);
            preparedStatement.setObject(3,pricePerNight);
            preparedStatement.setObject(4,maxGuests);
            preparedStatement.setObject(7,floor);
            preparedStatement.setObject(5,availability);

            preparedStatement.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    }
