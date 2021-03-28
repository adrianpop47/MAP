package controller;

import domain.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceManager;

import java.io.IOException;


public class MainController {
    ServiceManager serviceManager;

    @FXML
    TextField textFieldUser;
    @FXML
    Button buttonLogin;


    public void setService(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    public void handleLoginButton(ActionEvent actionEvent) throws IOException {
            Client client = serviceManager.getClientService().login(textFieldUser.getText());

            if(client == null){
                Alert loginFailedAlert = new Alert(Alert.AlertType.ERROR);
                loginFailedAlert.setTitle("Login Failed");
                loginFailedAlert.setHeaderText(null);
                loginFailedAlert.setContentText("Invalid username");
                loginFailedAlert.showAndWait();
            }
            else{
                Stage userStage = new Stage();
                FXMLLoader userLoader = new FXMLLoader();
                userLoader.setLocation(getClass().getResource("/views/userView.fxml"));
                AnchorPane layout = userLoader.load();
                userStage.setScene(new Scene(layout));

                UserController userController = userLoader.getController();
                userController.setService(serviceManager, client);

                userStage.setWidth(615);
                userStage.setResizable(false);
                userStage.setTitle(client.getName());
                userStage.show();
            }
        }
}
