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
    public User saveUser(@RequestBody User user) {

        if (user == null)
            ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Usuário não foi informado");

        return this.userService.save(user);
    }

    @GetMapping("{id}")
    public User getById(@PathVariable Long id) {
        return this.userService.getById(id);
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
