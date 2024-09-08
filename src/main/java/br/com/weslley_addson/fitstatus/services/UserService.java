package br.com.weslley_addson.fitstatus.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.weslley_addson.fitstatus.data.dados_pessoais.DadosPessoaisResponse;
import br.com.weslley_addson.fitstatus.data.user.UserRequest;
import br.com.weslley_addson.fitstatus.data.user.UserResponse;
import br.com.weslley_addson.fitstatus.models.DadosPessoais;
import br.com.weslley_addson.fitstatus.models.User;
import br.com.weslley_addson.fitstatus.repository.DadosPessoaisRepository;
import br.com.weslley_addson.fitstatus.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final DadosPessoaisRepository dadosPessoaisRepository;

    public UserService(UserRepository userRepository, DadosPessoaisRepository dadosPessoaisRepository) {
        this.userRepository = userRepository;
        this.dadosPessoaisRepository = dadosPessoaisRepository;
    }

    public ResponseEntity<?> listAllUsers(Pageable pageable) {

        if(pageable == null){
            return ResponseEntity.badRequest().body("Erro de paginação!");
        }

        Page<User> userPage = userRepository.findAll(pageable);
        Page<UserResponse> userResponses = userPage.map(user -> new UserResponse(user.getId(), user.getUsername(),
                user.getEmail(), DadosPessoaisResponse.fromDadosPessoais(user.getDadosPessoais())));

        return ResponseEntity.ok(userResponses);
    }

    public ResponseEntity<?> getUserByID(String id){
        var uuid = UUID.fromString(id);
        var user = userRepository.findById(uuid);

        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        User userPersistence = user.get();

        return ResponseEntity.ok(
                new UserResponse(
                        userPersistence.getId(),
                        userPersistence.getUsername(),
                        userPersistence.getEmail(),
                        DadosPessoaisResponse.fromDadosPessoais(userPersistence.getDadosPessoais())
                )
        );

    }

    public ResponseEntity<?> registerUser(UserRequest userRequest) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String userRequestJson = objectMapper.writeValueAsString(userRequest);

        User user = objectMapper.readValue(userRequestJson, User.class);

        if (this.checkIfUserExist(user)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já existe!");
        }

        DadosPessoais dadosPessoais = dadosPessoaisRepository.save(new DadosPessoais());

        user.setDadosPessoais(dadosPessoais);

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public Boolean checkIfUserExist(User user){
        return userRepository.findByUsername(user.getUsername()).isPresent();
    }

    public ResponseEntity<?> deleteUser(String uuidString) {
        try{
            UUID uuid = UUID.fromString(uuidString);
            userRepository.deleteById(uuid);
        }
        catch (IllegalArgumentException ignore){}

        return ResponseEntity.ok().build();
    }
}
