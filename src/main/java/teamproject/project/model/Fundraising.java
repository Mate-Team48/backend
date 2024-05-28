package teamproject.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "fundraisings")
@Getter
@Setter
public class Fundraising {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private User user;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "sphere", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category sphere;
    @Column(name = "fundraising_info", nullable = false)
    private String fundraisingInfo;
    @Column(name = "payment_info", nullable = false)
    private String paymentInfo;
    @Column(name = "picture_url")
    private String pictureUrl;
    @Column(name = "is_finished", nullable = false)
    private boolean isFinished = false;

    public enum Category {
        DRONES,
        CARS,
        MEDICINE,
        OTHER
    }
}
