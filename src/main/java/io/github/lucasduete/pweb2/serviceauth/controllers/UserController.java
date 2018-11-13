package io.github.lucasduete.pweb2.serviceauth.controllers;

import io.github.lucasduete.pweb2.serviceauth.models.User;
import io.github.lucasduete.pweb2.serviceauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                    .body(null);

        return ResponseEntity.ok(this.userService.save(user));
    }

    @GetMapping
    public ResponseEntity getAll() {

        List<User> users = this.userService.listAll();

        if (users == null || users.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(null);


        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id) {

        if (id <= 0)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);

        User user = this.userService.getById(id);

        if (user == null)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);

        return ResponseEntity.ok(user);
    }

    @PutMapping("{id}")
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable Long id) {

        if (id <= 0)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);

        User userById = this.userService.getById(id);

        if (userById == null)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);

        userById.setMatricula(user.getMatricula());
        userById.setPassword(user.getPassword());
        user.setRole(user.getRole());

        return ResponseEntity.ok(this.userService.save(userById));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {

        if (id <= 0) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        userService.deleteById(id);
        return ResponseEntity.ok(null);
    }

}
