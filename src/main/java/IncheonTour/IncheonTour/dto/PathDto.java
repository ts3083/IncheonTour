package IncheonTour.IncheonTour.dto;

import IncheonTour.IncheonTour.domain.MyPath;
import lombok.Data;

@Data
public class PathDto {

    private Long id;

    private String name;

    public PathDto(MyPath myPath) {
        this.id = myPath.getId();
        this.name = myPath.getName();
    }
}
