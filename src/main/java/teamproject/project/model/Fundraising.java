package teamproject.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fundraisings")
@Getter
@Setter
public class Fundraising {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "sphere", columnDefinition = "varchar", nullable = false)
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
