package IncheonTour.IncheonTour.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseResult<T> {

    private String pathName;
    private T data;
}
