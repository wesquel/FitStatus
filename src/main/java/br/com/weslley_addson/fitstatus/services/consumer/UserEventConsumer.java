package br.com.weslley_addson.fitstatus.services.consumer;

import br.com.weslley_addson.fitstatus.data.app.AppRequestAuth;
import br.com.weslley_addson.fitstatus.data.user.UserResponseFitApi;
import br.com.weslley_addson.fitstatus.exception.BusinessException;
import br.com.weslley_addson.fitstatus.services.DadosPessoaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserEventConsumer {

    private final DadosPessoaisService dadosPessoaisService;

    public UserEventConsumer(DadosPessoaisService dadosPessoaisService) {
        this.dadosPessoaisService = dadosPessoaisService;
    }

    @KafkaListener(topics = "user.created", groupId = "my-group")
    public void receiveMessage(UserResponseFitApi userRequestAuth){
        Set<AppRequestAuth> appPermissions = userRequestAuth.appsPermission();

        appPermissions.stream()
                .filter(appRequestAuth -> appRequestAuth.appId() == 1)
                .forEach(appRequestAuth -> handleUserCreation(userRequestAuth.authUserId()));
    }

    private void handleUserCreation(Long authUserId) {
        try {
            dadosPessoaisService.createDadosPessoais(authUserId);
        } catch (BusinessException businessException) {
            // TODO: Log the error or resubmit the message to the queue
        }
    }
}
