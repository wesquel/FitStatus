package br.com.weslley_addson.fitstatus.services.consumer;

import br.com.weslley_addson.fitstatus.data.app.AppRequestAuth;
import br.com.weslley_addson.fitstatus.data.user.UserResponseFitApi;
import br.com.weslley_addson.fitstatus.exception.BusinessException;
import br.com.weslley_addson.fitstatus.services.DadosPessoaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserEventConsumer {

    @Value("${app.id}")
    private static Long appId;

    private final DadosPessoaisService dadosPessoaisService;

    public UserEventConsumer(DadosPessoaisService dadosPessoaisService) {
        this.dadosPessoaisService = dadosPessoaisService;
    }

    @KafkaListener(topics = "user.created", groupId = "my-group")
    public void receiveMessage(UserResponseFitApi userRequestAuth){
        Set<AppRequestAuth> appPermissions = userRequestAuth.appsPermission();

        appPermissions.stream()
                .filter(appRequestAuth -> appRequestAuth.appId().equals(appId))
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
