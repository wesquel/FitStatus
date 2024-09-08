package br.com.weslley_addson.fitstatus.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.weslley_addson.fitstatus.data.dados_pessoais.DadosPessoaisResponse;
import br.com.weslley_addson.fitstatus.models.DadosPessoais;
import br.com.weslley_addson.fitstatus.repository.DadosPessoaisRepository;

@Service
public class DadosPessoaisService {

    private final DadosPessoaisRepository dadosPessoaisRepository;

    public DadosPessoaisService(DadosPessoaisRepository dadosPessoaisRepository) {
        this.dadosPessoaisRepository = dadosPessoaisRepository;
    }

    public ResponseEntity<?> listAllDadosPessoais(Pageable pageable) {

        if(pageable == null){
            return ResponseEntity.badRequest().body("Erro de paginação!");
        }

        Page<DadosPessoais> dadosPessoaisPage = dadosPessoaisRepository.findAll(pageable);
        Page<DadosPessoaisResponse> dadosPessoaisResponses = dadosPessoaisPage.map(
                DadosPessoaisResponse::fromDadosPessoais
        );

        return ResponseEntity.ok(dadosPessoaisResponses);
    }

    public ResponseEntity<?> getDadosPessoaisByID(Long id){

        var dadosPessoaisFind = dadosPessoaisRepository.findById(id);

        if(dadosPessoaisFind.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var dadosPessoais = dadosPessoaisFind.get();

        return ResponseEntity.ok(DadosPessoaisResponse.fromDadosPessoais(dadosPessoais));

    }

}
