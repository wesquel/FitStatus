package br.com.weslley_addson.fitstatus.data.dados_pessoais;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.weslley_addson.fitstatus.models.DadosPessoais;

public record DadosPessoaisRequest(String nomeCompleto, double peso, int altura, double cintura,
                                   double quadril, double bracos, double coxas, double dobraCutanea,
                                   double pressaoArterial)
{
    public DadosPessoais toEntity() throws JsonProcessingException {
        return new DadosPessoais(
                this.nomeCompleto,
                this.peso,
                this.altura,
                this.cintura,
                this.quadril,
                this.bracos,
                this.coxas,
                this.dobraCutanea,
                this.pressaoArterial
        );
    }
}
