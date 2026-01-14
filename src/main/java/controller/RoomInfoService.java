package controller;

import javafx.collections.ObservableList;
import model.dto.RoomInfoDTO;

public interface RoomInfoService {
    ObservableList<RoomInfoDTO> getAllRooms();

    void addRoomDetails(String roomId, String roomType, String description, double pricePerNight, int maxGuests, int floor, boolean availability);

    void deleteRoom(String roomId);

    void updateRoom(String roomId, String roomType, String description, double pricePerNight, int maxGuests, int floor, boolean availability) ;



}
