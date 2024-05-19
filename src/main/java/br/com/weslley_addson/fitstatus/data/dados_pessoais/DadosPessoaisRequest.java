package br.com.weslley_addson.fitstatus.data.dados_pessoais;

import br.com.weslley_addson.fitstatus.models.DadosPessoais;
import br.com.weslley_addson.fitstatus.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
