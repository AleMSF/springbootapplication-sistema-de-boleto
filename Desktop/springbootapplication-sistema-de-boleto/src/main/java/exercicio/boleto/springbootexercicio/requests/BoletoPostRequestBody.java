package exercicio.boleto.springbootexercicio.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BoletoPostRequestBody {
    @NotEmpty
    private Integer id;
    private Double value;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private String dataValidade;

}
