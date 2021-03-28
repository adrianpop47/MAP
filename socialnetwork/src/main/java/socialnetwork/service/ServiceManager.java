package socialnetwork.service;

import socialnetwork.domain.*;
import socialnetwork.validator.MesajValidator;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceManager {
    private UtilizatorService utilizatorService;
    private PrietenService prietenService;
    private MesajService mesajService;
    private MesajValidator mesajValidator;
    private CereriService cereriService;

    public ServiceManager(UtilizatorService utilizatorService, PrietenService prietenService, MesajService mesajService, CereriService cereriService) {
        this.utilizatorService = utilizatorService;
        this.prietenService = prietenService;
        this.mesajService = mesajService;
        this.mesajValidator = new MesajValidator(utilizatorService);
        this.cereriService = cereriService;
    }

    public UtilizatorService getUtilizatorService() {
        return utilizatorService;
    }

    public PrietenService getPrietenService() {
        return prietenService;
    }

    public MesajService getMesajService(){ return mesajService;}

    public Prietenie addPrietenie(Prietenie messageTask) {
        if(utilizatorService.findId(messageTask.getU1()) && utilizatorService.findId(messageTask.getU2()))
        {
            prietenService.addPrietenie(messageTask);
            return messageTask;
        }
        return null;
    }

    public int numarComunitati(){
        return prietenService.numarComunitati(utilizatorService.getNextId());
    }

    public int[] comunitateMaxima(){
        return prietenService.comunitateMaxima(utilizatorService.getNextId());
    }

    public Utilizator deleteUtilizator(Long id) throws Exception {
        Utilizator task = utilizatorService.deleteUtilizator(id);
        Iterable<Prietenie> prietenii = prietenService.getAll();
        List<Long> deSters = new ArrayList<>();
        for(Prietenie p : prietenii){
            if(p.getU1() == id || p.getU2() == id){
                deSters.add(p.getID());
            }
        }
        for(Long i : deSters){
            Prietenie p = prietenService.deletePrietenie(i);
        }
        return task;
    }

    public List<PrietenieDto> prietenieDtoUtilizator(Long id){
        List<PrietenieDto> rez = StreamSupport
                .stream(prietenService.getAll().spliterator(), false)
                .filter(p -> p.getU1() == id || p.getU2() == id)
                .map(prietenie -> new PrietenieDto(prietenie.getID(), prietenUtilizatorAux(prietenie, id).getFirstName(), prietenUtilizatorAux(prietenie, id).getLastName(), prietenie.getDate()))
                .collect(Collectors.toList());
        return rez;
    }

    public Map<Utilizator, LocalDateTime> prieteniUtilizator(Long id){
        Map<Utilizator, LocalDateTime> rez = StreamSupport.
                stream(prietenService.getAll().spliterator(), false).
                filter(p -> p.getU1() == id || p.getU2() == id).
                collect(Collectors.toMap(u->prietenUtilizatorAux(u, id) , d->d.getDate()));
        return rez;
    }

    private Utilizator prietenUtilizatorAux (Prietenie p, Long id){
        if(p.getU1() != id)
            return utilizatorService.findOne(p.getU1());
        else
            return utilizatorService.findOne(p.getU2());
    }

    public Map<Utilizator, LocalDateTime> prieteniUtilizatorLuna(long id, int luna) {
        if(luna < 1 || luna > 12)
            throw new IllegalArgumentException("Luna invalida");
        return prieteniUtilizator(id)
                .entrySet()
                .stream()
                .filter(p -> p.getValue().getMonthValue() == luna)
                .collect(Collectors.toMap(x -> x.getKey(), x->x.getValue()));
    }

    public Message sendMessage(Long from, List<Long> to, String message, LocalDateTime dateTime) {
        Message m = new Message(from, to, message, dateTime);
        mesajValidator.validate(m);
        mesajService.addMesaj(m);
        return m;
    }

    public CereriService getServiceCereri() {
        return this.cereriService;
    }

    public CererePrietenie sendFriendRequest(long fromId, long toId) {
        if(utilizatorService.findOne(fromId) == null || utilizatorService.findOne(toId) == null)
            throw new IllegalArgumentException("Nu exista acest utilizator!");
        CererePrietenie cererePrietenie = new CererePrietenie(fromId, toId, LocalDateTime.now(), "pending");
        return cereriService.sendFriendRequest(cererePrietenie);
    }

    public void respondFriendRequest(long requestId, String status) throws IOException {
        CererePrietenie cererePrietenie = cereriService.findOne(requestId);
        if(status.equals("approved")){
            Prietenie prietenie = new Prietenie(LocalDateTime.now(), cererePrietenie.getU1(), cererePrietenie.getU2());
            prietenService.addPrietenie(prietenie);
        }
        cereriService.respondFriendRequest(cererePrietenie, status);
    }

    public Prietenie deletePrietenie(Long id) throws Exception {
        Prietenie task = getPrietenService().deletePrietenie(id);
        Long cerereId = findCerere(task.getU1(), task.getU2()).getID();
        getServiceCereri().deleteCerere(cerereId);
        return task;
    }

    private CererePrietenie findCerere(Long id1, Long id2)
    {
        for(CererePrietenie c : cereriService.getAll()){
            if(c.getU1() == id1 && c.getU2() == id2)
            {
                return c;
            }
            if(c.getU1() == id2 && c.getU2()== id1)
            {
                return c;
            }
        }
        return null;
    }
}
