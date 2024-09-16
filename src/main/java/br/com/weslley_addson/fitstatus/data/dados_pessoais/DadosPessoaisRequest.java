package br.com.weslley_addson.fitstatus.data.dados_pessoais;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.weslley_addson.fitstatus.models.DadosPessoais;

public record DadosPessoaisRequest(Long userId)
{
    public DadosPessoais toEntity() throws JsonProcessingException {
        return new DadosPessoais(
                this.userId
        );
    }
}
