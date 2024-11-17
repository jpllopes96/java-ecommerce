package tech.jplopes.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jplopes.ecommerce.controller.dto.CreateUserDto;
import tech.jplopes.ecommerce.services.UserService;

import java.net.URI;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto dto){

        var user = userService.createUser(dto);

        return ResponseEntity.created(URI.create("/users/"+ user.getUserId())).build();
    }

}
