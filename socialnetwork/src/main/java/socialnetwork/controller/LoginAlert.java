package socialnetwork.controller;

import javafx.scene.control.Alert;

import java.util.concurrent.TimeUnit;

public class LoginAlert {
    static void showLoginFailedMessage(){
        Alert loginFailedAlert = new Alert(Alert.AlertType.ERROR);
        loginFailedAlert.setTitle("Login Failed");
        loginFailedAlert.setHeaderText(null);
        loginFailedAlert.setContentText("Invalid username or password");
        loginFailedAlert.showAndWait();
    }

    static void showWelcomeMessage(String firstName, String lastName) throws InterruptedException {
        Alert welcomeAlert = new Alert(Alert.AlertType.INFORMATION);
        welcomeAlert.setTitle("Welcome");
        welcomeAlert.setHeaderText(null);
        welcomeAlert.setContentText("Hello, " + firstName + " " + lastName + "!");
        welcomeAlert.show();
        TimeUnit.SECONDS.sleep(2);;
        welcomeAlert.close();
    }

}
