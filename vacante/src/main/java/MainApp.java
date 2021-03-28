import controller.MainController;
import controller.OfferController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import repository.Repository;
import repository.file.*;
import service.*;
import vacante.domain.*;
import validator.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MainApp extends Application {
    Repository<Double, Location> locationsFileRepository;
    Validator<Location> validatorLocation;
    LocationService serviceLocation;

    Repository<Double, Hotel> hotelFileRepository;
    Validator<Hotel> validatorHotel;
    HotelService serviceHotel;

    Repository<Double, SpecialOffer> offerFileRepository;
    Validator<SpecialOffer> validatorOffer;
    OfferService serviceOffer;

    Repository<Long, Client> clientFileRepository;
    Validator<Client> validatorClient;
    ClientService serviceClient;

    Repository<Double, Reservation> reservationFileRepository;
    Validator<Reservation> validatorReservation;
    ReservationService serviceReservation;

    ServiceManager serviceManager;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String fileName="data/locations.csv";
        locationsFileRepository = new LocationFile(fileName);
        validatorLocation = new LocationValidator();
        serviceLocation = new LocationService(locationsFileRepository, validatorLocation);

        String fileName1="data/hotels.csv";
        hotelFileRepository = new HotelFile(fileName1);
        validatorHotel = new HotelValidator();
        serviceHotel = new HotelService(hotelFileRepository, validatorHotel);

        String fileName2="data/offers.csv";
        offerFileRepository = new OfferFile(fileName2);
        validatorOffer = new OfferValidator();
        serviceOffer = new OfferService(offerFileRepository, validatorOffer);

        String fileName3="data/clients.csv";
        clientFileRepository = new ClientFile(fileName3);
        validatorClient = new ClientValidator();
        serviceClient = new ClientService(clientFileRepository, validatorClient);

        String fileName4="data/reservations.csv";
        reservationFileRepository = new ReservationFile(fileName4);
        validatorReservation = new ReservationValidator();
        serviceReservation = new ReservationService(reservationFileRepository, validatorReservation);

        serviceManager = new ServiceManager(serviceLocation, serviceHotel, serviceOffer, serviceClient, serviceReservation);

        Parameters parameters = getParameters();
        List clients = parameters.getRaw();
        clients.forEach(c -> {
            Stage mainStage = new Stage();
            try {
                initView(mainStage,Long.parseLong(c.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String name = serviceManager.getClientService().findOne(Long.parseLong(c.toString())).getName();
            mainStage.setWidth(800);
            mainStage.setTitle(name);
            mainStage.show();
        });

    }

    private void initView(Stage primaryStage, long clientId) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/mainView.fxml"));
        AnchorPane layout = loader.load();
        primaryStage.setScene(new Scene(layout));

        MainController mainController = loader.getController();
        mainController.setService(serviceManager, clientId);
    }
}
