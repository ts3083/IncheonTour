package IncheonTour.IncheonTour.dto;

import IncheonTour.IncheonTour.domain.Path;
import lombok.Data;

@Data
public class PathDto {

    private Long id;

    public PathDto(Path path) {
        this.id = path.getId();
    }
}
