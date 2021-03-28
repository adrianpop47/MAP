package socialnetwork;

import socialnetwork.config.ApplicationContext;
import socialnetwork.domain.Entity;
import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Tuple;
import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.UtilizatorValidator;
import socialnetwork.repository.Repository;
import socialnetwork.repository.file.UtilizatorFile;

public class Main {
    public static void main(String[] args) {
        //String fileName=ApplicationContext.getPROPERTIES().getProperty("data.socialnetwork.users");
        String fileName="data/users.csv";
        Repository<Long,Utilizator> userFileRepository = new UtilizatorFile(fileName
                , new UtilizatorValidator());

        userFileRepository.findAll().forEach(System.out::println);

        Utilizator u1 = new Utilizator("asd", "asd");
        Utilizator u2 = new Utilizator("asd", "asd");


        Prietenie p = new Prietenie(u1, u2);
        System.out.println(p);
    }
}


