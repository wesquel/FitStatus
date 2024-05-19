package br.com.weslley_addson.fitstatus.data.user;

import br.com.weslley_addson.fitstatus.data.dados_pessoais.DadosPessoaisResponse;
import br.com.weslley_addson.fitstatus.models.DadosPessoais;

import java.util.UUID;

public record UserResponse(UUID id, String username, String email, DadosPessoaisResponse dadosPessoaisResponse) {
}
