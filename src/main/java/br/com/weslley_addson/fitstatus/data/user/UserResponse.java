package br.com.weslley_addson.fitstatus.data.user;

import java.util.UUID;

import br.com.weslley_addson.fitstatus.data.dados_pessoais.DadosPessoaisResponse;

public record UserResponse(UUID id, String username, String email, DadosPessoaisResponse dadosPessoaisResponse) {
}
