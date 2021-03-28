package socialnetwork.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import socialnetwork.domain.*;
import socialnetwork.service.ServiceManager;
import socialnetwork.utils.observer.Observer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class
MessagesController implements Observer {
    ObservableList<PrietenieDto> modelGradeFriends = FXCollections.observableArrayList();
    ObservableList<MessageDto> modelGradeMessages = FXCollections.observableArrayList();
    PrietenieDto lastSelected;

    ServiceManager serviceManager;
    Long currentUser;

    @FXML
    TextField messageTextField;

    @FXML
    TableColumn<PrietenieDto,String> tableColumnFirstName;
    @FXML
    TableColumn<PrietenieDto,String> tableColumnLastName;
    @FXML
    TableView<PrietenieDto> tableViewFriends;

    @FXML
    TableColumn<MessageDto,String> tableColumnFrom;
    @FXML
    TableColumn<MessageDto,String> tableColumnMessage;
    @FXML
    TableColumn<MessageDto,LocalDateTime> tableColumnDate;
    @FXML
    TableView<MessageDto> tableViewMessages;

    @FXML
    public void initialize(){
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableViewFriends.setItems(modelGradeFriends);
        tableViewFriends.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableColumnFrom.setCellValueFactory(new PropertyValueFactory<>("from"));
        tableColumnMessage.setCellValueFactory(new PropertyValueFactory<>("message"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableViewMessages.setItems(modelGradeMessages);
        tableViewFriends.getSelectionModel().selectedItemProperty().addListener(e -> handleMessages());

    }


    public void setService(ServiceManager serviceManager, Long userId) {
        this.serviceManager = serviceManager;
        serviceManager.getPrietenService().addObserver(this);
        serviceManager.getMesajService().addObserver(this);

        this.currentUser = userId;
        modelGradeFriends.setAll(getPrieteniDtoList());
    }

    private List<PrietenieDto> getPrieteniDtoList(){
        return serviceManager.prietenieDtoUtilizator(currentUser);
    }

    private List<MessageDto> getConversation(Long friendId){ ;
        List<Message> messages = serviceManager.getMesajService().conversatieUtilizatori(currentUser, friendId);
        return messages
                .stream()
                .map(message -> new MessageDto(conversationUserName(message.getFrom()), message.getMessage(), message.getDate()))
                .collect(Collectors.toList());

    }

    private String conversationUserName(Long id){
        if(id == currentUser)
            return "You";
        else
            return serviceManager.getUtilizatorService().findOne(id).getLastName();
    }

    private Long getFriendId(Long friendshipId){
        Prietenie friendShip = serviceManager.getPrietenService().findOne(friendshipId);
        Long friendId;
        if(friendShip.getU1() == currentUser)
            friendId = friendShip.getU2();
        else
            friendId = friendShip.getU1();
        return friendId;
    }


    private void handleMessages(){
        if(tableViewFriends.getSelectionModel().getSelectedItems().size() == 1){
            lastSelected = tableViewFriends.getSelectionModel().getSelectedItem();
        }
        Long friendshipId = lastSelected.getId();
        Long friendId = getFriendId(friendshipId);
        List<MessageDto> conversation = getConversation(friendId);
        modelGradeMessages.setAll(conversation);
    }

    @Override
    public void update() {
        modelGradeFriends.setAll(getPrieteniDtoList());
        handleMessages();
    }

    public void handleMessageButton(ActionEvent actionEvent) {
        ObservableList<PrietenieDto> selectedFriends = tableViewFriends.getSelectionModel().getSelectedItems();
        if(selectedFriends.size() == 0){
            UserAlert.showSelectFriendsMessage();
        }
        else if(messageTextField.getText().isEmpty())
        {
            UserAlert.emptyMessageMessage();
        }
        else{
            List<Long> toList = new ArrayList<>();
            selectedFriends.forEach(friend -> toList.add(getFriendId(friend.getId())));
            Message message = new Message(currentUser, toList, messageTextField.getText(), LocalDateTime.now());
            serviceManager.getMesajService().addMesaj(message);
        }
    }
}
