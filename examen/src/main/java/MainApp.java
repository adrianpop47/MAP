import controller.MainController;
import domain.Client;
import domain.Flight;
import domain.Ticket;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import repository.Repository;
import repository.file.ClientFile;
import repository.file.FlightFile;
import repository.file.TicketFile;
import service.ClientService;
import service.FlightService;
import service.ServiceManager;
import service.TicketService;
import validator.ClientValidator;
import validator.FlightValidator;
import validator.TicketValidator;
import validator.Validator;

import java.io.IOException;

public class MainApp extends Application {
    Repository<String, Client> clientFileRepository;
    Validator<Client> validatorClient;
    ClientService serviceClient;

    Repository<Long, Flight> flightFileRepository;
    Validator<Flight> validatorFlight;
    FlightService serviceFlight;

    Repository<Long, Ticket> ticketFileRepository;
    Validator<Ticket> validatorTicket;
    TicketService serviceTicket;

    ServiceManager serviceManager;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException{
        String fileName="data/clients.csv";
        clientFileRepository = new ClientFile(fileName);
        validatorClient = new ClientValidator();
        serviceClient = new ClientService(clientFileRepository, validatorClient);

        String fileName1="data/flights.csv";
        flightFileRepository = new FlightFile(fileName1);
        validatorFlight = new FlightValidator();
        serviceFlight = new FlightService(flightFileRepository, validatorFlight);

        String fileName2="data/tickets.csv";
        ticketFileRepository = new TicketFile(fileName2);
        validatorTicket = new TicketValidator();
        serviceTicket = new TicketService(ticketFileRepository, validatorTicket);

        serviceManager = new ServiceManager(serviceClient, serviceFlight, serviceTicket);

        Stage mainStage = new Stage();
        initView(mainStage);
        mainStage.setWidth(320);
        mainStage.setResizable(false);
        mainStage.show();
    }

    private void initView(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/mainView.fxml"));
        AnchorPane layout = loader.load();
        primaryStage.setScene(new Scene(layout));

        MainController mainController = loader.getController();
        mainController.setService(serviceManager);
    }
}
