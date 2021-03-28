package socialnetwork;

import socialnetwork.domain.CererePrietenie;
import socialnetwork.domain.Message;
import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Utilizator;
import socialnetwork.repository.Repository;
import socialnetwork.repository.file.CereriFile;
import socialnetwork.repository.file.MesajFile;
import socialnetwork.repository.file.PrietenFile;
import socialnetwork.repository.file.UtilizatorFile;
import socialnetwork.service.*;
import socialnetwork.ui.Console;
import socialnetwork.validator.PrietenieValidator;
import socialnetwork.validator.UtilizatorValidator;
import socialnetwork.validator.Validator;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
//        String fileName="data/users.csv";
//        Repository<Long,Utilizator> userFileRepository = new UtilizatorFile(fileName);
//        Validator<Utilizator> validatorUtilizator = new UtilizatorValidator();
//        UtilizatorService serviceUtilizator = new UtilizatorService(userFileRepository, validatorUtilizator);
//
//        String fileName2="data/friends.csv";
//        Repository<Long,Prietenie> friendsFileRepository = new PrietenFile(fileName2);
//        Validator<Prietenie> validatorPrietenie = new PrietenieValidator();
//        PrietenService servicePrietenie = new PrietenService(friendsFileRepository, validatorPrietenie);
//
//        String fileName3="data/messages.csv";
//        Repository<Long, Message> messagesFileRepository = new MesajFile(fileName3);
//        MesajService serviceMesaje = new MesajService(messagesFileRepository);
//
//        String fileName4="data/requests.csv";
//        Repository<Long, CererePrietenie> requestsFileRepository = new CereriFile(fileName4);
//        CereriService serviceCereri = new CereriService(requestsFileRepository);
//
//        ServiceManager serviceManager = new ServiceManager(serviceUtilizator, servicePrietenie, serviceMesaje, serviceCereri);
//        Console ui = new Console(serviceManager);
//        ui.start();
          MainApp.main(args);
    }
}


