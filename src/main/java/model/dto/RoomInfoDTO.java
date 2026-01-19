package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomInfoDTO {
    private String roomId;
    private String roomType;
    private String description;
    private double pricePerNight;
    private int maxGuests;
    private int floor;
    private boolean availability;
}
