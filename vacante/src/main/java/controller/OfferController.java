package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceManager;
import vacante.domain.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.SECONDS;

public class OfferController {
    ObservableList<SpecialOffer> modelGradelOffer = FXCollections.observableArrayList();
    ServiceManager serviceManager;
    Double hotelId;
    Long clientId;

    @FXML
    TableColumn<SpecialOffer, Double> tableColumnId;
    @FXML
    TableColumn<SpecialOffer, Double> tableColumnHotelId;
    @FXML
    TableColumn<SpecialOffer, Date> tableColumnStartDate;
    @FXML
    TableColumn<SpecialOffer, Date> tableColumnEndDate;
    @FXML
    TableColumn<SpecialOffer, Integer> tableColumnPercents;

    @FXML
    DatePicker datePickerStart;
    @FXML
    DatePicker datePickerEnd;
    @FXML
    Button buttonReservation;


    @FXML
    TableView<SpecialOffer> tableViewOffer;
    public void setService(ServiceManager serviceManager, Double hotelId, Long clientId) {
        this.serviceManager = serviceManager;
        this.hotelId = hotelId;
        this.clientId = clientId;
        modelGradelOffer.setAll(StreamSupport
                .stream(serviceManager.getOfferService().getAll().spliterator(),false)
                .filter(x -> x.getHotelId().equals(hotelId))
                .collect(Collectors.toList()));

        datePickerStart.valueProperty().addListener(e -> handleFilter());
        datePickerEnd.valueProperty().addListener(e -> handleFilter());
    }

    private void handleFilter() {
        if(datePickerStart.getValue() != null && datePickerEnd.getValue() != null)  {
            LocalDate localDateStart = datePickerStart.getValue();
            Instant instantStart = Instant.from(localDateStart.atStartOfDay(ZoneId.systemDefault()));
            Date startDate = Date.from(instantStart);

            LocalDate localDateEnd = datePickerEnd.getValue();
            Instant instantEnd = Instant.from(localDateEnd.atStartOfDay(ZoneId.systemDefault()));
            Date endDate = Date.from(instantEnd);

            Predicate<SpecialOffer> startDatePredicate = o -> o.getStartDate().after(startDate);
            Predicate<SpecialOffer> endDatePredicate = o -> o.getStartDate().before(endDate);
            modelGradelOffer.setAll(StreamSupport
                    .stream(serviceManager.getOfferService().getAll().spliterator(), false)
                    .filter(x -> x.getHotelId().equals(hotelId))
                    .filter(startDatePredicate.and(endDatePredicate))
                    .collect(Collectors.toList()));
        }
    }

    @FXML
    public void initialize() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("specialOfferId"));
        tableColumnHotelId.setCellValueFactory(new PropertyValueFactory<>("hotelId"));
        tableColumnStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tableColumnEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        tableColumnPercents.setCellValueFactory(new PropertyValueFactory<>("percents"));
        tableViewOffer.setItems(modelGradelOffer);
    }

    public void handleReservationButton(ActionEvent actionEvent) {
        if(datePickerStart.getValue() != null&& datePickerEnd.getValue() != null) {
            LocalDate localDateStart = datePickerStart.getValue();
            Instant instantStart = Instant.from(localDateStart.atStartOfDay(ZoneId.systemDefault()));
            Date startDate = Date.from(instantStart);

            LocalDate localDateEnd = datePickerEnd.getValue();
            Instant instantEnd = Instant.from(localDateEnd.atStartOfDay(ZoneId.systemDefault()));
            Date endDate = Date.from(instantEnd);

            long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            int noNights = Math.toIntExact(diff);

            Reservation reservation = new Reservation(serviceManager.getReservationService().getId(), hotelId, clientId, datePickerStart.getValue().atStartOfDay(), noNights);
            serviceManager.getReservationService().addReservation(reservation);
        }
    }
}
