package br.com.weslley_addson.fitstatus.data.dados_pessoais;

import br.com.weslley_addson.fitstatus.models.DadosPessoais;

public record DadosPessoaisResponse(Long id, String nomeCompleto, double peso, int altura, double cintura,
                                    double quadril, double bracos, double coxas, double dobraCutanea,
                                    double pressaoArterial)
{
    public static DadosPessoaisResponse fromDadosPessoais(DadosPessoais dadosPessoais) {
        return new DadosPessoaisResponse(
                dadosPessoais.getId(),
                dadosPessoais.getNomeCompleto(),
                dadosPessoais.getPeso(),
                dadosPessoais.getAltura(),
                dadosPessoais.getCintura(),
                dadosPessoais.getQuadril(),
                dadosPessoais.getBracos(),
                dadosPessoais.getCoxas(),
                dadosPessoais.getDobraCutanea(),
                dadosPessoais.getPressaoArterial()
        );
    }
}
