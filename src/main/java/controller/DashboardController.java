package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class DashboardController {

    Stage stage = new Stage();
    
    public void btnCustomerInfoOnAction(ActionEvent actionEvent) {
        Stage oldStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/customer_Info.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        oldStage.close();
        stage.show();
    }

    public void btnRoomInfoOnAction(ActionEvent actionEvent) {
        Stage oldStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/room_Info.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        oldStage.close();
        stage.show();
    }
}
