package fa.training.JWD.Practice.T01.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)", unique = true)
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "varchar(1000)")
    private String descriptions;

    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
    private List<Cert> certs;
}
