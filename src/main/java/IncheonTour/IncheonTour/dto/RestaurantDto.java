package IncheonTour.IncheonTour.dto;

import lombok.Data;

@Data
public class RestaurantDto {

    private String query;
    private Integer display;
    private Integer start;
    private String sort;

    public static RestaurantDto  createRestaurantDto(String keyword) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setQuery(keyword);
        restaurantDto.setDisplay(5);
        restaurantDto.setStart(1);
        restaurantDto.setSort("random");
        return restaurantDto;
    }

}
