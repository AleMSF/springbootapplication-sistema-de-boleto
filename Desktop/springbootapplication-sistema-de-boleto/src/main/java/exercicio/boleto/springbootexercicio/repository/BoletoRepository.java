package exercicio.boleto.springbootexercicio.repository;

import exercicio.boleto.springbootexercicio.domain.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoletoRepository extends JpaRepository<Boleto, Integer> {

}
