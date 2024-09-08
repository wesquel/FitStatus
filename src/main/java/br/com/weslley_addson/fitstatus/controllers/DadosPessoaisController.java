package br.com.weslley_addson.fitstatus.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.weslley_addson.fitstatus.services.DadosPessoaisService;

@RestController
@RequestMapping("/dados_pessoais")
public class DadosPessoaisController {

    private final DadosPessoaisService dadosPessoaisService;

    public DadosPessoaisController(DadosPessoaisService dadosPessoaisService) {
        this.dadosPessoaisService = dadosPessoaisService;
    }

}
