package controller;

import domain.Nota;
import domain.NotaDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceManager;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NotaController {

    ObservableList<NotaDto> modelGrade = FXCollections.observableArrayList();
    List<String> modelTema;
    private ServiceManager service;
    Integer textFieldCount = 0;


    @FXML
    TableColumn<NotaDto, String> tableColumnName;
    @FXML
    TableColumn<NotaDto, String> tableColumnTema;
    @FXML
    TableColumn<NotaDto, Double> tableColumnNota;
    @FXML
    TableColumn<NotaDto, String> tableColumnProfesor;
    @FXML
    TableView<NotaDto> tableViewNote;
    @FXML
    TextField textFieldDisplayNumber;
    @FXML
    TextField textFieldTema;
    @FXML
    TextField textFieldNota;
    @FXML
    TextField textFieldNume;



    @FXML
    public void initialize() {
        // TODO
        tableColumnName.setCellValueFactory(new PropertyValueFactory<NotaDto, String>("studentName"));
        tableColumnTema.setCellValueFactory(new PropertyValueFactory<NotaDto, String>("temaId"));
        tableColumnNota.setCellValueFactory(new PropertyValueFactory<NotaDto, Double>("nota"));
        tableColumnProfesor.setCellValueFactory(new PropertyValueFactory<NotaDto, String>("profesor"));
        tableViewNote.setItems(modelGrade);
        textFieldNota.textProperty().addListener(e -> handleFilter());
        textFieldNume.textProperty().addListener(e -> handleFilter());
        textFieldTema.textProperty().addListener(e -> handleFilter());
    }

    private List<NotaDto> getNotaDTOList() {
        // TODO
        return service.findAllGrades()
                .stream()
                .map(nota -> new NotaDto(nota.getStudent().getName(), nota.getTema().getId(), nota.getValue(), nota.getProfesor()))
                .collect(Collectors.toList());
    }

    private void handleFilter() {
        // TODO sem 8
        Predicate<NotaDto> nameStartsWith = nota -> nota.getStudentName().startsWith(textFieldNume.getText());
        Predicate<NotaDto> tema = notaDto -> notaDto.getTemaId().startsWith(textFieldTema.getText());
        Predicate<NotaDto> nota = notaDto -> {
            try{
                return notaDto.getNota() > Double.parseDouble(textFieldNota.getText());
            }catch(NumberFormatException e){
                return true;
            }
        };
        modelGrade.setAll(getNotaDTOList().stream()
                .filter(nameStartsWith.and(tema).and(nota))
                .collect(Collectors.toList()))  ;
    }


    public void setService(ServiceManager service) {
        // TODO
        this.service = service;
        modelGrade.setAll(getNotaDTOList());
    }

    public void handlClickButtonCounter(ActionEvent actionEvent) {
        textFieldCount++;
        textFieldDisplayNumber.setText(textFieldCount.toString());
    }
}
