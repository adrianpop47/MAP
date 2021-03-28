package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceManager;
import utils.observer.Observable;
import utils.observer.Observer;
import vacante.domain.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MainController implements Observer<Reservation> {
    ObservableList<Location> modelGradelLocations = FXCollections.observableArrayList();
    ObservableList<Hotel> modelGradelHotel = FXCollections.observableArrayList();
    ObservableList<OfferDto> modelGradelOffer = FXCollections.observableArrayList();
    ServiceManager serviceManager;
    Long clientId;

    @FXML
    ComboBox locationComboBox;

    @FXML
    TableColumn<Hotel, Double> tableColumnHotelId;
    @FXML
    TableColumn<Hotel, Double> tableColumnLocationId;
    @FXML
    TableColumn<Hotel, String> tableColumnName;
    @FXML
    TableColumn<Hotel, Integer> tableColumnRooms;
    @FXML
    TableColumn<Hotel, Double> tableColumnPrice;
    @FXML
    TableColumn<Hotel, Type> tableColumnType;

    @FXML
    TableView<Hotel> tableViewHotel;


    @FXML
    TableColumn<OfferDto, String> tableColumnOfferName;
    @FXML
    TableColumn<OfferDto, String> tableColumnLocation;
    @FXML
    TableColumn<OfferDto, Date> tableColumnStartDate;
    @FXML
    TableColumn<OfferDto, Date> tableColumnEndDate;

    @FXML
    TableView<OfferDto> tableViewOffers;

    @FXML
    public void initialize(){

        locationComboBox.setItems(modelGradelLocations);
        tableColumnHotelId.setCellValueFactory(new PropertyValueFactory<>("hotelId"));
        tableColumnLocationId.setCellValueFactory(new PropertyValueFactory<>("locationId"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        tableColumnRooms.setCellValueFactory(new PropertyValueFactory<>("noRooms"));
        tableColumnPrice.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        tableColumnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableViewHotel.setItems(modelGradelHotel);


        tableColumnOfferName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        tableColumnLocation.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        tableColumnStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tableColumnEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        tableViewOffers.setItems(modelGradelOffer);

        locationComboBox.getSelectionModel().selectedItemProperty().addListener(e -> handleFilter());
        tableViewHotel.getSelectionModel().selectedItemProperty().addListener(e -> {
            try {
                hotelFilter();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    private List<OfferDto> getOfferDtoList(){
        return serviceManager.offerDtoList(clientId);
    }

    private void hotelFilter() throws IOException {
        Stage offerStage = new Stage();
        FXMLLoader offerLoader = new FXMLLoader();
        offerLoader.setLocation(getClass().getResource("/views/offerView.fxml"));
        AnchorPane layout = offerLoader.load();
        offerStage.setScene(new Scene(layout));

        OfferController offerController = offerLoader.getController();
        offerController.setService(serviceManager,tableViewHotel.getSelectionModel().getSelectedItem().getId(), this.clientId);

        String name = serviceManager.getClientService().findOne(clientId).getName();
        offerStage.setWidth(615);
        offerStage.setResizable(false);
        offerStage.setTitle(name);
        offerStage.show();
    }

    private void handleFilter() {
        Integer index = locationComboBox.getSelectionModel().getSelectedIndex();
        Double locationId = modelGradelLocations.get(index).getId();
        var items = StreamSupport.stream(serviceManager.getHotelService().getAll().spliterator(),false).filter(x->x.getLocationId().equals(locationId)).collect(Collectors.toList());
        modelGradelHotel.setAll(items);
    }

    public void setService(ServiceManager serviceManager, long clientId) {
        this.serviceManager = serviceManager;
        this.clientId = clientId;
        serviceManager.getReservationService().addObserver(this);
        modelGradelLocations.setAll(StreamSupport.stream(serviceManager.getLocationService().getAll().spliterator(), false).collect(Collectors.toList()));
        modelGradelHotel.setAll(StreamSupport.stream(serviceManager.getHotelService().getAll().spliterator(),false).collect(Collectors.toList()));
        modelGradelOffer.setAll(getOfferDtoList());
    }

    @Override
    public void update(Reservation reservation) {
        Hobby currentHobby = serviceManager.getClientService().findOne(clientId).getHobby();
        Hobby otherHobby = serviceManager.getClientService().findOne(reservation.getClientId()).getHobby();
        if(reservation.getClientId() != clientId & currentHobby.equals(otherHobby)){
            String name = serviceManager.getClientService().findOne(clientId).getName();
            String hotelName = serviceManager.getHotelService().findOne(reservation.getHotelId()).getHotelName();
            Alert newReservatinoAlert = new Alert(Alert.AlertType.INFORMATION);
            newReservatinoAlert.setTitle("New reservation");
            newReservatinoAlert.setHeaderText(null);
            newReservatinoAlert.setContentText("Hello, " + name + "! Another client that likes " + currentHobby.toString() + " made a reservation at the " + hotelName);
            newReservatinoAlert.showAndWait();
        }
    }
}
