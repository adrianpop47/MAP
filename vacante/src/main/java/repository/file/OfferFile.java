package repository.file;

import vacante.domain.Location;
import vacante.domain.SpecialOffer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OfferFile extends AbstractFileRepository<Double, SpecialOffer>{
    public OfferFile(String fileName) {
        super(fileName);
    }

    @Override
    public SpecialOffer extractEntity(List<String> attributes) throws ParseException {
        Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(attributes.get(2));
        Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(attributes.get(3));
        SpecialOffer specialOffer = new SpecialOffer(Double.parseDouble(attributes.get(0)), Double.parseDouble(attributes.get(1)),
                startDate, endDate, Integer.parseInt(attributes.get(4)));
        return specialOffer;
    }

    @Override
    protected String createEntityAsString(SpecialOffer entity) {
        return entity.getId().intValue() + ";" + entity.getHotelId() + ";" + entity.getStartDate() + ";" + entity.getEndDate() + ";" + entity.getPercents();
    }
}
