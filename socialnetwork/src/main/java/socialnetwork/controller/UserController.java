package socialnetwork.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import socialnetwork.domain.Prietenie;
import socialnetwork.domain.PrietenieDto;
import socialnetwork.domain.Utilizator;
import socialnetwork.domain.UtilizatorDto;
import socialnetwork.service.ServiceManager;
import socialnetwork.utils.observer.Observer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UserController implements Observer {
    ObservableList<PrietenieDto> modelGradeFriends = FXCollections.observableArrayList();
    ObservableList<UtilizatorDto> modelGradelUsers = FXCollections.observableArrayList();
    ServiceManager serviceManager;
    Long currentUser;


    @FXML
    TextField textFieldFirstName;
    @FXML
    TextField textFieldLastName;

    @FXML
    Button addFriendButton;
    @FXML
    Button removeFriendButton;
    @FXML
    Button showRequestsButton;

    @FXML
    TableColumn<PrietenieDto,String> tableColumnFirstName;
    @FXML
    TableColumn<PrietenieDto,String> tableColumnLastName;
    @FXML
    TableColumn<PrietenieDto,LocalDateTime> tableColumnDate;
    @FXML
    TableView<PrietenieDto> tableViewFriends;

    @FXML
    TableColumn<UtilizatorDto, Long> tableColumnIdUser;
    @FXML
    TableColumn<UtilizatorDto, String> tableColumnFirstNameUser;
    @FXML
    TableColumn<UtilizatorDto, String> tableColumnLastNameUser;
    @FXML
    TableView<UtilizatorDto> tableViewUsers;

    @FXML
    public void initialize(){
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("data"));
        tableViewFriends.setItems(modelGradeFriends);

        tableColumnIdUser.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnFirstNameUser.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableColumnLastNameUser.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableViewUsers.setItems(modelGradelUsers);

        textFieldFirstName.textProperty().addListener(e -> handleFilter());
        textFieldLastName.textProperty().addListener(e -> handleFilter());
    }

    private void handleFilter(){
        Predicate<UtilizatorDto> firstName = user -> user.getFirstName().startsWith(textFieldFirstName.getText());
        Predicate<UtilizatorDto> lastName = user -> user.getLastName().startsWith(textFieldLastName.getText());
        modelGradelUsers.setAll(getUtilizatoriList().stream()
                                .filter(firstName.and(lastName))
                                .collect(Collectors.toList()));
    }

    private List<PrietenieDto> getPrieteniDtoList(){
        return serviceManager.prietenieDtoUtilizator(currentUser);
    }

    private List<UtilizatorDto> getUtilizatoriList(){
        return StreamSupport
            .stream(serviceManager.getUtilizatorService().getAll().spliterator(), false)
                .filter(x -> x.getID()!= currentUser)
                .map(user -> new UtilizatorDto(user.getID(), user.getFirstName(), user.getLastName()))
                .collect(Collectors.toList());
    }

    public void setService(ServiceManager serviceManager, Long userId) {
        this.serviceManager = serviceManager;
        serviceManager.getPrietenService().addObserver(this);
        this.currentUser = userId;
        modelGradeFriends.setAll(getPrieteniDtoList());
        modelGradelUsers.setAll(getUtilizatoriList());
    }

    public void handleAddFriendButton(ActionEvent actionEvent) {
        UtilizatorDto selectedUser = tableViewUsers.getSelectionModel().getSelectedItem();

        if(selectedUser == null)
            UserAlert.showSelectUser();
        else
        {
            Long toId = selectedUser.getId();
            serviceManager.sendFriendRequest(currentUser, toId);
            UserAlert.showSentFriendRequest(selectedUser.getFirstName(), selectedUser.getLastName());
        }
    }

    public void handleRemoveFriendButton(ActionEvent actionEvent) throws Exception {
        PrietenieDto selectedFriend = tableViewFriends.getSelectionModel().getSelectedItem();
        if(selectedFriend == null)
        {
            UserAlert.showSelectFriend();
        }else{
            Long id = selectedFriend.getId();
            serviceManager.deletePrietenie(id);
            UserAlert.showDeletedFriend(selectedFriend.getFirstName(), selectedFriend.getLastName());
            modelGradeFriends.setAll(getPrieteniDtoList());
        }
    }

    @Override
    public void update() {
        modelGradelUsers.setAll(getUtilizatoriList());
        modelGradeFriends.setAll(getPrieteniDtoList());
    }

    public void handleShowFriendRequests(ActionEvent actionEvent) throws IOException {
        String firstName = serviceManager.getUtilizatorService().findOne(currentUser).getFirstName();
        String lastName = serviceManager.getUtilizatorService().findOne(currentUser).getLastName();

        Stage requestsStage = new Stage();
        FXMLLoader requestsLoader = new FXMLLoader();
        requestsLoader.setLocation(getClass().getResource("/views/requestsView.fxml"));
        AnchorPane layout = requestsLoader.load();
        requestsStage.setScene(new Scene(layout));

        RequestsController requestsController = requestsLoader.getController();
        requestsController.setService(serviceManager, currentUser);

        requestsStage.setWidth(650);
        requestsStage.setResizable(false);
        requestsStage.setTitle(firstName + " " + lastName);
        requestsStage.show();
    }

    public void handleMessagesButton(ActionEvent actionEvent) throws IOException {
        String firstName = serviceManager.getUtilizatorService().findOne(currentUser).getFirstName();
        String lastName = serviceManager.getUtilizatorService().findOne(currentUser).getLastName();

        Stage messagesStage = new Stage();
        FXMLLoader messagesLoader = new FXMLLoader();
        messagesLoader.setLocation(getClass().getResource("/views/messagesView.fxml"));
        AnchorPane layout = messagesLoader.load();
        messagesStage.setScene(new Scene(layout));

        MessagesController messagesController = messagesLoader.getController();
        messagesController.setService(serviceManager, currentUser);

        messagesStage.setResizable(false);
        messagesStage.setTitle(firstName + " " + lastName);
        messagesStage.show();
    }
}
