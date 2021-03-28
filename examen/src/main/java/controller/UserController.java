package controller;

import domain.Client;
import domain.Flight;
import domain.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceManager;
import utils.observer.Observable;
import utils.observer.Observer;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UserController implements Observer {
    ObservableList<Flight> modelGradelFlights = FXCollections.observableArrayList();
    ObservableList<String> modelGradelFromLocations = FXCollections.observableArrayList();
    ObservableList<String> modelGradelToLocations = FXCollections.observableArrayList();
    ServiceManager serviceManager;
    Client client;

    @FXML
    TableColumn<Flight, Long> tableColumnFlightId;
    @FXML
    TableColumn<Flight, String> tableColumnFrom;
    @FXML
    TableColumn<Flight, String> tableColumnTo;
    @FXML
    TableColumn<Flight, LocalDateTime> tableColumnDepartureTime;
    @FXML
    TableColumn<Flight, LocalDateTime> tableColumnLandingTime;
    @FXML
    TableColumn<Flight, Integer> tableColumnSeats;

    @FXML
    TableView<Flight> tableViewFlight;

    @FXML
    ComboBox comboBoxFrom;
    @FXML
    ComboBox comboBoxTo;
    @FXML
    DatePicker datePickerDeparture;

    @FXML
    public void initialize(){
        comboBoxFrom.setItems(modelGradelFromLocations);
        comboBoxTo.setItems(modelGradelToLocations);
        tableColumnFlightId.setCellValueFactory(new PropertyValueFactory<>("flightId"));
        tableColumnFrom.setCellValueFactory(new PropertyValueFactory<>("from"));
        tableColumnTo.setCellValueFactory(new PropertyValueFactory<>("to"));
        tableColumnDepartureTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        tableColumnLandingTime.setCellValueFactory(new PropertyValueFactory<>("landingTime"));
        tableColumnSeats.setCellValueFactory(new PropertyValueFactory<>("seats"));
        tableViewFlight.setItems(modelGradelFlights);

        comboBoxFrom.getSelectionModel().selectedItemProperty().addListener(e -> handleFilter());
        comboBoxTo.getSelectionModel().selectedItemProperty().addListener(e -> handleFilter());
        datePickerDeparture.valueProperty().addListener(e -> handleFilter());
    }

    private void handleFilter() {
            Integer indexFrom;
            Integer indexTo;
            String from = null;
            String to = null;
            LocalDateTime departure = null;
        if(!comboBoxFrom.getSelectionModel().isEmpty())
            {
                indexFrom = comboBoxFrom.getSelectionModel().getSelectedIndex();
                from = modelGradelFromLocations.get(indexFrom);
            }
            if(!comboBoxTo.getSelectionModel().isEmpty()){
                indexTo = comboBoxTo.getSelectionModel().getSelectedIndex();
                to = modelGradelToLocations.get(indexTo);
                if(datePickerDeparture.getValue() != null)
                {
                    departure = datePickerDeparture.getValue().atStartOfDay();
                    modelGradelFlights.setAll(serviceManager.getFlightService().flightFilter(from, to, departure));
                }
            }


    }

    public void setService(ServiceManager serviceManager, Client client) {
        this.serviceManager = serviceManager;
        this.client = client;
        modelGradelFlights.setAll(StreamSupport
                .stream(serviceManager.getFlightService().getAll().spliterator(), false)
                .collect(Collectors.toList()));
        modelGradelFromLocations.setAll(serviceManager.getFlightService().getFromLocations());
        modelGradelToLocations.setAll(serviceManager.getFlightService().getToLocations());
        serviceManager.addObserver(this);
    }

    public void handleBuyButton(ActionEvent actionEvent) {
        Flight selectedFlight = tableViewFlight.getSelectionModel().getSelectedItem();
        if(selectedFlight != null)
        {
            Ticket ticket = new Ticket(serviceManager.getTicketService().getId(), client.getUsername(), selectedFlight.getFlightId(), LocalDateTime.now());
            serviceManager.addTicket(ticket);
        }
    }

    @Override
    public void update() {
        modelGradelFlights.setAll(StreamSupport
                .stream(serviceManager.getFlightService().getAll().spliterator(), false)
                .collect(Collectors.toList()));
    }
}
