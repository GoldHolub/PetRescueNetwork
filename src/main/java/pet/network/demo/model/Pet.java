package pet.network.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Table(name = "pets")
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String type;
    private String age;
    @Column(nullable = false, columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    private SexType sex;
    @Column(nullable = false, name = "health_condition", columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    private PetConditionType healthCondition;
    @Column(nullable = false, columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    private PetSterilizationType sterilization;
    @Column(nullable = false, columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    private PetSizeType size;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false, columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    private PetHabitatType habitat;
    @Column(nullable = false, columnDefinition = "price_for_donate")
    private Long priceForDonate;
    @Column(nullable = false, columnDefinition = "accumulated_price")
    private Long accumulatedPrice;
    private String comments;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "image_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PetImage petImage;

    public enum SexType {
        BOY,
        GIRL,
        UNKNOWN
    }

    public enum PetConditionType {
        CRITICAL,
        NEEDS_HELP
    }

    public enum PetHabitatType {
        STRAY,
        IN_GOOD_HANDS,
        IN_SHELTER
    }

    public enum PetSterilizationType {
        YES,
        NO,
        UNKNOWN
    }

    public enum PetSizeType {
        LITTLE,
        MEDIUM,
        BIG
    }
}
