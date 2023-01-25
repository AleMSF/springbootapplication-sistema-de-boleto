package exercicio.boleto.springbootexercicio.services;

import exercicio.boleto.springbootexercicio.domain.Boleto;
import exercicio.boleto.springbootexercicio.domain.BoletoVld;
import exercicio.boleto.springbootexercicio.repository.BoletoRepository;
import exercicio.boleto.springbootexercicio.requests.BoletoPostRequestBody;
import exercicio.boleto.springbootexercicio.requests.BoletoPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoletoService {
    private final BoletoRepository boletoRepository;

    public Page<Boleto> listAll(Pageable pageable) {
        return boletoRepository.findAll(pageable);
    }
    public List<Boleto> listAllNonPageable() {
        return boletoRepository.findAll();
    }

    public Boleto save(BoletoPostRequestBody boletoPostRequestBody) {
        return boletoRepository.save(Boleto.builder()
                .value(boletoPostRequestBody.getValue())
                .id(boletoPostRequestBody.getId())
                .type(BoletoVld.A_PAGAR)
                .dataValidade(LocalDate.parse(boletoPostRequestBody.getDataValidade()))
                .build());
    }

    public void pagamento(int id) {
        Boleto savedBoleto = findByIdOrThrowBadRequestException(id);

        long dif = ChronoUnit.DAYS.between(savedBoleto.getDataValidade(), LocalDate.now());
        if(dif >= 0) {
            savedBoleto.setType(BoletoVld.VENCIDO);
            boletoRepository.save(savedBoleto);
        }else {
            savedBoleto.setType(BoletoVld.PAGO);
            boletoRepository.save(savedBoleto);
            }
        }

    public Boleto findByIdOrThrowBadRequestException(Integer id) {
        return boletoRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException((HttpStatus.BAD_REQUEST) , "boleto not found"));
    }

    public void delete(int id) {
        boletoRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(BoletoPutRequestBody boletoPutRequestBody) {
        Boleto savedBoleto = findByIdOrThrowBadRequestException(boletoPutRequestBody.getId());
        Boleto boleto = Boleto.builder()
                .id(savedBoleto.getId())
                .value(savedBoleto.getValue())
                .type(savedBoleto.getType())
                .build();

        boletoRepository.save(boleto);
        }
}
