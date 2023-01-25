package exercicio.boleto.springbootexercicio.controler;

import exercicio.boleto.springbootexercicio.domain.Boleto;
import exercicio.boleto.springbootexercicio.requests.BoletoPostRequestBody;
import exercicio.boleto.springbootexercicio.requests.BoletoPutRequestBody;
import exercicio.boleto.springbootexercicio.services.BoletoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("boletos")
@Log4j2
@RequiredArgsConstructor
public class BoletoController {
    private final BoletoService boletoService;


    @GetMapping
    public ResponseEntity<List<Boleto>> listAll() {
        return ResponseEntity.ok(boletoService.listAllNonPageable());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Boleto> findById(@PathVariable int id) {
        return ResponseEntity.ok(boletoService.findByIdOrThrowBadRequestException(id));
    }

    @PutMapping(path = "pagamento/{id}")
    public ResponseEntity<Void> pagamento(@PathVariable int id) {
        boletoService.pagamento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Boleto> save(@RequestBody BoletoPostRequestBody boleto) {
        return new ResponseEntity<>(boletoService.save(boleto), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        boletoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody BoletoPutRequestBody boletoPutRequestBody) {
        boletoService.replace(boletoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
