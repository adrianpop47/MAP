package socialnetwork.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import socialnetwork.domain.CererePrietenie;
import socialnetwork.domain.Message;
import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Utilizator;
import socialnetwork.repository.Repository;
import socialnetwork.repository.file.CereriFile;
import socialnetwork.repository.file.MesajFile;
import socialnetwork.repository.file.PrietenFile;
import socialnetwork.repository.file.UtilizatorFile;
import socialnetwork.service.*;
import socialnetwork.validator.PrietenieValidator;
import socialnetwork.validator.UtilizatorValidator;
import socialnetwork.validator.Validator;

import java.awt.*;
import java.io.IOException;

public class LoginController {
    LoginService service;
    ServiceManager serviceManager;

    @FXML
    TextField textFieldUser;
    @FXML
    TextField textFieldPassword;
    @FXML
    Button loginButton;

    public void setService(LoginService loginService, ServiceManager serviceManager){
        this.service = loginService;
        this.serviceManager = serviceManager;
    }

    public void handleLoginButton(ActionEvent actionEvent) throws IOException, InterruptedException {
        Long currentUser = service.login(textFieldUser.getText(), textFieldPassword.getText());
        if(currentUser == null){
            LoginAlert.showLoginFailedMessage();
        }
        else{
            String firstName = serviceManager.getUtilizatorService().findOne(currentUser).getFirstName();
            String lastName = serviceManager.getUtilizatorService().findOne(currentUser).getLastName();
            Stage currentStage = (Stage) loginButton.getScene().getWindow();

            Stage userStage = new Stage();
            FXMLLoader userLoader = new FXMLLoader();
            userLoader.setLocation(getClass().getResource("/views/userView.fxml"));
            AnchorPane layout = userLoader.load();
            userStage.setScene(new Scene(layout));

            UserController userController = userLoader.getController();
            userController.setService(serviceManager, currentUser);

            LoginAlert.showWelcomeMessage(firstName, lastName);
            userStage.setWidth(615);
            userStage.setResizable(false);
            userStage.setTitle(firstName + " " + lastName);
            userStage.show();
        }
    }
}
