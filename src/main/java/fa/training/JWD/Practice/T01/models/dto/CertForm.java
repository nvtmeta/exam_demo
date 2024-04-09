package fa.training.JWD.Practice.T01.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CertForm {
    //    CertId contains  characters, numbers,and dashes only. It does not contain special characters
    @Pattern(regexp = "^[a-zA-Z0-9-]+$",
            message = " Id wrong format")
    @NotNull(message = "Id must not be empty")
    private String id;

    @NotNull(message = "certification name must not be empty")
    @NotBlank(message = "certification name must not be empty")
    private String certName;

    @NotNull(message = "cost must not be empty")
    private BigDecimal cost;

    private Integer categoryId;
}
