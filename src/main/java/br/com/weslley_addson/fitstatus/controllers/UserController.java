package br.com.weslley_addson.fitstatus.controllers;

import br.com.weslley_addson.fitstatus.data.user.UserRequest;
import br.com.weslley_addson.fitstatus.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> listAllUser(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size
    ) throws JsonProcessingException {
        Pageable pageable = PageRequest.of(page, size);

        return userService.listAllUsers(pageable);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getUserById(@PathVariable("uuid") String uuid){
        return userService.getUserByID(uuid);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest){
        try{
            return userService.registerUser(userRequest);
        }catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Not created, user object fail");
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteUser(@PathVariable("uuid") String uuid){
        return userService.deleteUser(uuid);
    }
}
