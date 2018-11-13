package io.github.lucasduete.pweb2.serviceauth.controllers;

import io.github.lucasduete.pweb2.serviceauth.models.User;
import io.github.lucasduete.pweb2.serviceauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity saveUser(@RequestBody User user) {

        if (user == null)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Usuário não foi informado");

        return ResponseEntity.ok(this.userService.save(user));
    }

    @GetMapping
    public ResponseEntity getAll() {

        return ResponseEntity.ok(
                this.userService.listAll()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id) {

        User user = this.userService.getById(id);

        if (user == null)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Este usuário não exite");

        return ResponseEntity.ok(user);
    }

    @PutMapping("{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        User userById = this.userService.getById(id);

        userById.setMatricula(user.getMatricula());
        userById.setPassword(user.getPassword());
        user.setRole(user.getRole());

        return this.userService.save(userById);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

}
