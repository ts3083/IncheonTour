package IncheonTour.IncheonTour.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Sigungu {

    @Id
    @GeneratedValue
    @Column(name = "sigungu_id")
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String code;

    private String nx;

    private String ny;

    public static Sigungu createSigungu(String name, String code, String nx, String ny) {
        Sigungu sigungu = new Sigungu();
        sigungu.setName(name);
        sigungu.setCode(code);
        sigungu.setNx(nx);
        sigungu.setNy(ny);
        return sigungu;
    }
}
