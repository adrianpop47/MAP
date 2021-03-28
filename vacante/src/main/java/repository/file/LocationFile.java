package repository.file;

import vacante.domain.Location;

import java.text.ParseException;
import java.util.List;

public class LocationFile extends AbstractFileRepository<Double, Location> {
    public LocationFile(String fileName) { super(fileName); }

    @Override
    public Location extractEntity(List<String> attributes) throws ParseException {
        Location location = new Location(Double.parseDouble(attributes.get(0)), attributes.get(1));
        return location;
    }

    @Override
    protected String createEntityAsString(Location entity) {
        return entity.getId()+";"+entity.getLocationName();
    }
}
