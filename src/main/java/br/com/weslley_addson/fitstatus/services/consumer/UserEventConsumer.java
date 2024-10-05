package br.com.weslley_addson.fitstatus.services.consumer;

import br.com.weslley_addson.fitstatus.data.user.UserResponseFitApi;
import br.com.weslley_addson.fitstatus.exception.BusinessException;
import br.com.weslley_addson.fitstatus.services.DadosPessoaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventConsumer {

    private final DadosPessoaisService dadosPessoaisService;

    public UserEventConsumer(DadosPessoaisService dadosPessoaisService) {
        this.dadosPessoaisService = dadosPessoaisService;
    }

    @KafkaListener(topics = "user.created", groupId = "my-group")
    public void receiveMessage(UserResponseFitApi userRequestAuth){
        try {
            dadosPessoaisService.createDadosPessoais(userRequestAuth.authUserId());
        } catch (BusinessException businessException) {
            // TODO Log error or Resubmit message to queue
        }
    }
}
