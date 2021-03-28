package repository.file;

import vacante.domain.Hotel;
import vacante.domain.Type;

import java.text.ParseException;
import java.util.List;

public class HotelFile extends AbstractFileRepository<Double, Hotel>{
    public HotelFile(String fileName) {
        super(fileName);
    }

    @Override
    public Hotel extractEntity(List<String> attributes) throws ParseException {
        Hotel hotel = new Hotel(Double.parseDouble(attributes.get(0)), Double.parseDouble(attributes.get(1)),attributes.get(2),
                Integer.parseInt(attributes.get(3)), Double.parseDouble(attributes.get(4)), Type.valueOf(attributes.get(5)));
        return hotel;
    }

    @Override
    protected String createEntityAsString(Hotel entity) {
        return entity.getId()+";"+entity.getLocationId()+";"+entity.getHotelName()+";"+entity.getNoRooms()+";"+entity.getPricePerNight() + ";" + entity.getType();
    }
}
