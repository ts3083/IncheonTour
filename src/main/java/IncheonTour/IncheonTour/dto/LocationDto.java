package IncheonTour.IncheonTour.dto;

import IncheonTour.IncheonTour.domain.Location;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class LocationDto {

    private String name;
    private String imageName;
    private String description;

    public LocationDto(Location location) {
        this.name = location.getName();
        this.imageName = location.getImage_name();
        this.description = location.getDescription();
    }
}
