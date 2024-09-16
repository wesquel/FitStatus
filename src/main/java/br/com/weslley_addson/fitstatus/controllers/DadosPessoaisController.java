package br.com.weslley_addson.fitstatus.controllers;

import br.com.weslley_addson.fitstatus.data.dados_pessoais.DadosPessoaisRequest;
import br.com.weslley_addson.fitstatus.models.DadosPessoais;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.weslley_addson.fitstatus.services.DadosPessoaisService;

@RestController
@RequestMapping("/dadosPessoais")
public class DadosPessoaisController {

    private final DadosPessoaisService dadosPessoaisService;

    public DadosPessoaisController(DadosPessoaisService dadosPessoaisService) {
        this.dadosPessoaisService = dadosPessoaisService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDadosPessoais(@RequestBody DadosPessoaisRequest dadosPessoaisRequest){
        return dadosPessoaisService.createDadosPessoais(dadosPessoaisRequest.userId());
    }

}
