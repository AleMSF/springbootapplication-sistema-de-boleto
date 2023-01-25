package exercicio.boleto.springbootexercicio.requests;

import lombok.Data;

@Data
public class BoletoPutRequestBody {
    private Integer id;
    private Integer value;
    private String type;
}
