package fa.training.JWD.Practice.T01.models.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CertList {
    private String id;
    private String certName;
    private BigDecimal cost;
    private String categoryName;

    public CertList(String id, String certName, BigDecimal cost, String categoryName) {
        this.id = id;
        this.certName = certName;
        this.cost = cost;
        this.categoryName = categoryName;
    }
}
