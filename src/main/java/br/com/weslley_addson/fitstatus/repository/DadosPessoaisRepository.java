package br.com.weslley_addson.fitstatus.repository;

import br.com.weslley_addson.fitstatus.models.DadosPessoais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DadosPessoaisRepository extends JpaRepository<DadosPessoais, Long> {

    Optional<DadosPessoais> findByUserId(Long authUserId);

}
