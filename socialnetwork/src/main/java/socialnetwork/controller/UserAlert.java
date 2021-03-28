package socialnetwork.controller;

import javafx.scene.control.Alert;

import java.util.concurrent.TimeUnit;

public class UserAlert {
    static void showSentFriendRequest(String firstName, String lastName){
        Alert sentFirendRequestAlert = new Alert(Alert.AlertType.CONFIRMATION);
        sentFirendRequestAlert.setTitle("Friend reuqest sent!");
        sentFirendRequestAlert.setHeaderText(null);
        sentFirendRequestAlert.setContentText("You sent a friend request to " + firstName + " " + lastName + "!");
        sentFirendRequestAlert.showAndWait();
    }

    static void showSelectUser(){
        Alert selectUserAlert = new Alert(Alert.AlertType.ERROR);
        selectUserAlert.setHeaderText(null);
        selectUserAlert.setContentText("Please select an user!");
        selectUserAlert.showAndWait();
    }

    static void showSelectFriend(){
        Alert selectFriendAlert = new Alert(Alert.AlertType.ERROR);
        selectFriendAlert.setHeaderText(null);
        selectFriendAlert.setContentText("Please select a friend!");
        selectFriendAlert.showAndWait();
    }

    static void showSelectRequest(){
        Alert selectFriendAlert = new Alert(Alert.AlertType.ERROR);
        selectFriendAlert.setHeaderText(null);
        selectFriendAlert.setContentText("Please select a friend request!");
        selectFriendAlert.showAndWait();
    }

    static void showDeletedFriend(String firstName, String lastName){
        Alert deletedFriendAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deletedFriendAlert.setHeaderText(null);
        deletedFriendAlert.setContentText("You are no longer friend with " + firstName + " " + lastName + "!");
        deletedFriendAlert.showAndWait();
    }

    public static void acceptedFriendRequest() {
        Alert deletedFriendAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deletedFriendAlert.setHeaderText(null);
        deletedFriendAlert.setContentText("You accepted the friend request!");
        deletedFriendAlert.showAndWait();
    }

    public static void rejectedFriendRequest() {
        Alert deletedFriendAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deletedFriendAlert.setHeaderText(null);
        deletedFriendAlert.setContentText("You rejected the friend request!");
        deletedFriendAlert.showAndWait();
    }

    public static void canceledFriendRequest() {
        Alert deletedFriendAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deletedFriendAlert.setHeaderText(null);
        deletedFriendAlert.setContentText("You canceled the friend request!");
        deletedFriendAlert.showAndWait();
    }

    public static void showSelectFriendsMessage() {
        Alert deletedFriendAlert = new Alert(Alert.AlertType.ERROR);
        deletedFriendAlert.setHeaderText(null);
        deletedFriendAlert.setContentText("Please select a friend!");
        deletedFriendAlert.showAndWait();
    }

    public static void emptyMessageMessage() {
        Alert deletedFriendAlert = new Alert(Alert.AlertType.ERROR);
        deletedFriendAlert.setHeaderText(null);
        deletedFriendAlert.setContentText("Message text can't be empty!");
        deletedFriendAlert.showAndWait();
    }
}
