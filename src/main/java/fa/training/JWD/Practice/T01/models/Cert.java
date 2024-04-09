package fa.training.JWD.Practice.T01.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "cert")
@Getter
@Setter
public class Cert {
    @Id
    @Column(name = "id", columnDefinition = "varchar(12)", unique = true)
    private String id;

    @Column(name = "cert_name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String certName;

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
