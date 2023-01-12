package IncheonTour.IncheonTour.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Eagiggu {

    @Id @GeneratedValue
    @Column(name = "eagiggu_id")
    private Long id;

    private String state;

    // level, 경험치 (0~100)
    private Integer level;

    private Integer exp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "path_id")
    private MyPath myPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location current_location;
}
