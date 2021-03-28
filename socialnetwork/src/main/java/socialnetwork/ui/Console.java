package socialnetwork.ui;

import socialnetwork.domain.CererePrietenie;
import socialnetwork.domain.Message;
import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Utilizator;
import socialnetwork.service.*;

import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Console {
    private ServiceManager serviceManager;
    private UtilizatorService serviceUtilizator;
    private PrietenService servicePrieteni;
    private MesajService serviceMesaje;
    private CereriService serviceCereri;

    public Console(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
        this.serviceUtilizator = serviceManager.getUtilizatorService();
        this.servicePrieteni = serviceManager.getPrietenService();
        this.serviceMesaje = serviceManager.getMesajService();
        this.serviceCereri = serviceManager.getServiceCereri();
    }


    private void adaugaUtilizator()
    {
        String firstName;
        String lastName;

        Scanner reader = new Scanner(System.in);

        System.out.println("\nEnter the first name:");
        firstName = reader.nextLine();

        System.out.println("\nEnter the last name:");
        lastName = reader.nextLine();

        Utilizator user = new Utilizator(firstName, lastName);

        serviceUtilizator.addUtilizator(user);
    }

    private void afiseazaUtilizatori()
    {
        serviceUtilizator.getAll().forEach(System.out::println);
    }

    private void stergeUtilizator() throws Exception {
        long id;
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the id:");
        id = reader.nextLong();

        serviceManager.deleteUtilizator(id);
    }

    private void adaugaPrietenie() throws Exception {
        LocalDateTime dateTime;
        long firstUserId;
        long secondUserId;

        Scanner reader = new Scanner(System.in);
        Scanner reader1 = new Scanner(System.in);

        System.out.println("Enter the date:");
        String dataString = reader1.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        dateTime = LocalDateTime.parse(dataString, formatter);

        System.out.println("Enter the id of the first user:");
        firstUserId = reader.nextLong();

        System.out.println("Enter the id of the second user:");
        secondUserId = reader.nextLong();

        Prietenie prietenie = new Prietenie(dateTime, firstUserId, secondUserId);
        serviceManager.addPrietenie(prietenie);
    }

    private void afiseazaPrietenii(){
        servicePrieteni.getAll().forEach(System.out::println);
    }

    private void stergePrietenie() throws Exception {
        long id;
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the id:");
        id = reader.nextLong();

        servicePrieteni.deletePrietenie(id);
    }

    private void numarComunitati()
    {
        System.out.println(serviceManager.numarComunitati());
        System.out.print('\n');
    }

    private void comunitateMaxima(){
        int[] comunitate = serviceManager.comunitateMaxima();
        for(int utilizator : comunitate){
            System.out.print(utilizator + " ");
        }
        System.out.println('\n');
    }

    private void prieteniUtilizator(){
        long id;
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the id:");
        id = reader.nextLong();

        serviceManager.prieteniUtilizator(id).
                entrySet().
                forEach(entry->
                    System.out.println(
                            entry.getKey().getFirstName() + " "
                            + entry.getKey().getLastName() + " "
                            + entry.getValue()));
    }

    private void prieteniUtilizatorLuna(){
        long id;
        int luna;

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the id:");
        id = reader.nextLong();

        Scanner reader1 = new Scanner(System.in);
        System.out.println("Enter the month:");
        luna = reader1.nextInt();

        serviceManager.prieteniUtilizatorLuna(id, luna)
                .entrySet()
                .forEach(entry ->
                    System.out.println(
                            entry.getKey().getFirstName() + " " + entry.getKey().getLastName() + " " + entry.getValue())
                );
    }

    public void conversatieUtilizatori(){
        long id1;
        long id2;

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the id of the first user");
        id1 = reader.nextLong();
        System.out.println("Enter the id of the second user");
        id2 = reader.nextLong();
        serviceMesaje.conversatieUtilizatori(id1, id2)
                .forEach(x -> {
                    System.out.println("Id: " + x.getID());
                    if(x.getFrom() == id1){
                        System.out.println(serviceUtilizator.findOne(id1).getFirstName() + " "
                                + serviceUtilizator.findOne(id1).getLastName());
                        System.out.println(serviceUtilizator.findOne(id2).getFirstName() + " "
                                + serviceUtilizator.findOne(id2).getLastName());
                    }
                    if(x.getFrom() == id2){
                        System.out.println(serviceUtilizator.findOne(id2).getFirstName() + " "
                                + serviceUtilizator.findOne(id2).getLastName());
                        System.out.println(serviceUtilizator.findOne(id1).getFirstName() + " "
                                + serviceUtilizator.findOne(id1).getLastName());
                    }
                    System.out.println(x.getMessage());
                    System.out.println(x.getDate());
                    System.out.print('\n');
                });
    }

    public void sendMessage(){
        long from;
        long toLength;
        long to;
        List<Long> toList = new ArrayList<>();
        String message;
        LocalDateTime dateTime = LocalDateTime.now();

        Scanner reader = new Scanner(System.in);
        System.out.println("From: ");
        from = reader.nextLong();

        System.out.println("Number of users to send to: ");
        toLength = reader.nextLong();

        for(int i = 0; i < toLength; i++){
            System.out.println("To: ");
            to = reader.nextLong();
            toList.add(to);
        }

        Scanner reader1 = new Scanner(System.in);
        System.out.println("Message: ");
        message = reader1.nextLine();

        serviceManager.sendMessage(from, toList, message, dateTime);
    }

    public void replyMessage() throws IOException {
        long userId;
        long messageId;
        String message;
        LocalDateTime dateTime = LocalDateTime.now();

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the id of the user: ");
        userId = reader.nextLong();

        List<Message> messagesWithoutReply = serviceMesaje.messagesWithoutReply(userId);
        if(messagesWithoutReply.size() == 0){
            System.out.println("No message to reply to");
            return;
        }
        System.out.println("Messages without reply:");
        messagesWithoutReply.forEach(System.out::println);

        System.out.println("Choose a message to reply to:");
        messageId = reader.nextLong();

        Scanner reader1 = new Scanner(System.in);
        System.out.println("Enter the message: ");
        message = reader1.nextLine();

        serviceMesaje.replyMessage(userId, messageId, message, dateTime);
    }

    public void showFriendRequests(){
        serviceCereri.getAll().forEach(System.out::println);
    }

    public void sendFriendRequest(){
        long fromId;
        long toId;

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the id of the first user: ");
        fromId = reader.nextLong();

        System.out.println("Enter the id of the second user: ");
        toId = reader.nextLong();

        serviceManager.sendFriendRequest(fromId, toId);
    }

    public void respondFriendRequest() throws IOException {
        long userId;
        long requestId;
        String status;

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the id of the user: ");
        userId = reader.nextLong();

        List<CererePrietenie> requests = StreamSupport
                .stream(serviceCereri.getAll().spliterator(), false)
                .filter(x -> x.getU2() == userId)
                .filter(x -> x.getStatus().equals("pending"))
                .collect(Collectors.toList());

        if(requests.size() == 0){
            System.out.println("No friend requests");
            return;
        }

        System.out.println("Friend reuqests:");
        requests.forEach(System.out::println);

        System.out.println("Enter the id of the friend request: ");
        requestId = reader.nextLong();

        Scanner reader1 = new Scanner(System.in);
        System.out.println("Enter the response (approve/reject)");
        status = reader1.nextLine();

        switch (status){
            case "approve":
                serviceManager.respondFriendRequest(requestId, "approved");
                break;
            case "reject":
                serviceManager.respondFriendRequest(requestId, "rejected");
                break;
            default:
                System.out.println("Invalid response!");
                break;
        }
    }

    public void start() throws Exception {
        boolean running = true;
        while(running)
        {
            System.out.println("1.Add user");
            System.out.println("2.Show users");
            System.out.println("3.Delete user");
            System.out.println("4.Add friendship");
            System.out.println("5.Show friendships");
            System.out.println("6.Delete friendship");
            System.out.println("7.Numar comunitati");
            System.out.println("8.Comunitate maxima");
            System.out.println("9.Prietenii utilizatorului");
            System.out.println("10.Prieteniile utilizatorului create intr-o luna");
            System.out.println("11.Afiseaza conversatia");
            System.out.println("12.Send message");
            System.out.println("13.Reply message");
            System.out.println("14.Show friend requests");
            System.out.println("15.Send friend request");
            System.out.println("16.Respond to a friend request");
            System.out.println("0.Exit");
            Scanner reader = new Scanner(System.in);
            int cmd = reader.nextInt();
            switch (cmd){
                case 1:
                    this.adaugaUtilizator();
                    break;
                case 2:
                    this.afiseazaUtilizatori();
                    break;
                case 3:
                    this.stergeUtilizator();
                    break;
                case 4:
                    this.adaugaPrietenie();
                    break;
                case 5:
                    this.afiseazaPrietenii();
                    break;
                case 6:
                    this.stergePrietenie();
                    break;
                case 7:
                    this.numarComunitati();
                    break;
                case 8:
                    this.comunitateMaxima();
                    break;
                case 9:
                    this.prieteniUtilizator();
                    break;
                case 10:
                    this.prieteniUtilizatorLuna();
                    break;
                case 11:
                    this.conversatieUtilizatori();
                    break;
                case 12:
                    this.sendMessage();
                    break;
                case 13:
                    this.replyMessage();
                    break;
                case 14:
                    this.showFriendRequests();
                    break;
                case 15:
                    this.sendFriendRequest();
                    break;
                case 16:
                    this.respondFriendRequest();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Comanda invalida!");
            }
        }
    }
}
