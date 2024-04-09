package fa.training.JWD.Practice.T01.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SelectOptionDto {
    private String value;

    private String label;
    private Integer totalCert;
}
