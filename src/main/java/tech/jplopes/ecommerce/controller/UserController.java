package tech.jplopes.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jplopes.ecommerce.controller.dto.CreateUserDto;
import tech.jplopes.ecommerce.entities.UserEntity;
import tech.jplopes.ecommerce.services.UserService;

import java.net.URI;
import java.util.UUID;

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

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserEntity> findById(@PathVariable UUID userId){
        var user = userService.findById(userId);

        return user.isPresent() ?
                ResponseEntity.ok(user.get()) :
                ResponseEntity.notFound().build();
    }

}
