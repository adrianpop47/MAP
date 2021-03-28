package socialnetwork;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import socialnetwork.controller.LoginController;
import socialnetwork.domain.*;
import socialnetwork.repository.Repository;
import socialnetwork.repository.file.*;
import socialnetwork.service.*;
import socialnetwork.validator.PrietenieValidator;
import socialnetwork.validator.UtilizatorValidator;
import socialnetwork.validator.Validator;

import java.io.IOException;

public class MainApp extends Application {
    Repository<Long, Utilizator> userFileRepository;
    Validator<Utilizator> validatorUtilizator;
    UtilizatorService serviceUtilizator;

    Repository<Long, Prietenie> friendsFileRepository;
    Validator<Prietenie> validatorPrietenie;
    PrietenService servicePrietenie;

    Repository<Long, Message> messagesFileRepository;
    MesajService serviceMesaje;

    Repository<Long, CererePrietenie> requestsFileRepository;
    CereriService serviceCereri;

        ServiceManager serviceManager;

    Repository<Long, UserLogin> userLoginFileRepository;
    LoginService serviceLogin;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String fileName="data/users.csv";
        userFileRepository = new UtilizatorFile(fileName);
        validatorUtilizator = new UtilizatorValidator();
        serviceUtilizator = new UtilizatorService(userFileRepository, validatorUtilizator);

        String fileName2="data/friends.csv";
        friendsFileRepository = new PrietenFile(fileName2);
        validatorPrietenie = new PrietenieValidator();
        servicePrietenie = new PrietenService(friendsFileRepository, validatorPrietenie);

        String fileName3="data/messages.csv";
        messagesFileRepository = new MesajFile(fileName3);
        serviceMesaje = new MesajService(messagesFileRepository);

        String fileName4="data/requests.csv";
        requestsFileRepository = new CereriFile(fileName4);
        serviceCereri = new CereriService(requestsFileRepository);

        serviceManager = new ServiceManager(serviceUtilizator, servicePrietenie, serviceMesaje, serviceCereri);


        String fileName5="data/userLogin.csv";
        userLoginFileRepository = new LoginFile(fileName5);
        serviceLogin = new LoginService(userLoginFileRepository);


        initView(primaryStage);
        primaryStage.setWidth(320);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    private void initView(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/loginView.fxml"));
        AnchorPane layout = loader.load();
        primaryStage.setScene(new Scene(layout));

        LoginController loginController = loader.getController();
        loginController.setService(serviceLogin, serviceManager);

    }
}
