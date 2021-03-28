package repository.file;

import repository.memory.InMemoryRepository;
import vacante.domain.Entity;

import java.io.*;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractFileRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID, E> {
    String fileName;
    public AbstractFileRepository(String fileName){
        super();
        this.fileName = fileName;
        loadData();
    }

    private void loadData(){
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = br.readLine()) != null){
                List<String> attr = Arrays.asList(line.split(";"));
                E e = extractEntity(attr);
                super.add(e);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    public abstract E extractEntity(List<String> attributes) throws ParseException;

    protected abstract String createEntityAsString(E entity);

    @Override
    public E add(E entity){
        E e = super.add(entity);
        if(e == null){
            writeToFile(entity);
        }
        return e;
    }

    protected void writeToFile(E entity){
        try (BufferedWriter bW = new BufferedWriter(new FileWriter(fileName,true))) {
            bW.write(createEntityAsString(entity));
            bW.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
