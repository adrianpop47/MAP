package socialnetwork.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import socialnetwork.domain.CererePrietenie;
import socialnetwork.domain.CereriDto;
import socialnetwork.domain.PrietenieDto;
import socialnetwork.domain.UtilizatorDto;
import socialnetwork.service.LoginService;
import socialnetwork.service.ServiceManager;
import socialnetwork.utils.observer.Observer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RequestsController implements Observer {
    ObservableList<CereriDto> modelGradeRequests = FXCollections.observableArrayList();
    ObservableList<CereriDto> modelGradeRequestsSent = FXCollections.observableArrayList();

    ServiceManager serviceManager;
    Long currentUser;

    @FXML
    TableColumn<CereriDto,String> tableColumnFirstName;
    @FXML
    TableColumn<CereriDto,String> tableColumnLastName;
    @FXML
    TableColumn<CereriDto, LocalDateTime> tableColumnDate;
    @FXML
    TableColumn<CereriDto, String> tableColumnStatus;
    @FXML
    TableView<CereriDto> tableViewRequests;

    @FXML
    TableColumn<CereriDto,String> tableColumnFirstNameSent;
    @FXML
    TableColumn<CereriDto,String> tableColumnLastNameSent;
    @FXML
    TableColumn<CereriDto, LocalDateTime> tableColumnDateSent;
    @FXML
    TableColumn<CereriDto, String> tableColumnStatusSent;
    @FXML
    TableView<CereriDto> tableViewRequestsSent;

    @FXML
    Button acceptButton;
    @FXML
    Button rejectButton;


    @FXML
    public void initialize() {
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("data"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableViewRequests.setItems(modelGradeRequests);

        tableColumnFirstNameSent.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableColumnLastNameSent.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableColumnDateSent.setCellValueFactory(new PropertyValueFactory<>("data"));
        tableColumnStatusSent.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableViewRequestsSent.setItems(modelGradeRequestsSent);
    }

    public void setService(ServiceManager serviceManager, Long currentUser) {
        this.serviceManager = serviceManager;
        serviceManager.getServiceCereri().addObserver(this);
        this.currentUser = currentUser;
        modelGradeRequests.setAll(getCereriDtoList());
        modelGradeRequestsSent.setAll(getCereriDtoListSent());
    }

    private List<CereriDto> getCereriDtoList(){
        return StreamSupport
                .stream(serviceManager.getServiceCereri().getAll().spliterator(), false)
                .filter(x-> x.getU2() == currentUser)
                .map(request -> new CereriDto(request.getID(), serviceManager.getUtilizatorService().findOne(request.getU1()).getFirstName(), serviceManager.getUtilizatorService().findOne(request.getU1()).getLastName(), request.getDate(), request.getStatus()))
                .collect(Collectors.toList());
    }

    private List<CereriDto> getCereriDtoListSent(){
        return StreamSupport
                .stream(serviceManager.getServiceCereri().getAll().spliterator(), false)
                .filter(x-> x.getU1() == currentUser)
                .map(request -> new CereriDto(request.getID(), serviceManager.getUtilizatorService().findOne(request.getU2()).getFirstName(), serviceManager.getUtilizatorService().findOne(request.getU2()).getLastName(), request.getDate(), request.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public void update() {
        modelGradeRequests.setAll(getCereriDtoList());
        modelGradeRequestsSent.setAll(getCereriDtoListSent());
    }

    public void handleAcceptButton(ActionEvent actionEvent) throws IOException {
        CereriDto cererePrietenieDto = tableViewRequests.getSelectionModel().getSelectedItem();
        if(cererePrietenieDto == null){
            UserAlert.showSelectRequest();
        }else{
            serviceManager.respondFriendRequest(cererePrietenieDto.getId(), "approved");
            UserAlert.acceptedFriendRequest();
        }
    }

    public void handleRejectButton(ActionEvent actionEvent) throws IOException {
        CereriDto cererePrietenieDto = tableViewRequests.getSelectionModel().getSelectedItem();
        if(cererePrietenieDto == null){
            UserAlert.showSelectRequest();
        }else{
            serviceManager.respondFriendRequest(cererePrietenieDto.getId(), "rejected");
            UserAlert.rejectedFriendRequest();
        }
    }

    public void handleCancelButton(ActionEvent actionEvent) throws IOException {
        CereriDto cererePrietenieDto = tableViewRequestsSent.getSelectionModel().getSelectedItem();
        if(cererePrietenieDto == null) {
            UserAlert.showSelectRequest();
        }else{
            serviceManager.getServiceCereri().deleteCerere(cererePrietenieDto.getId());
            UserAlert.canceledFriendRequest();
        }
    }
}
