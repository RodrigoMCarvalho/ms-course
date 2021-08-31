package com.rodrigo.hruser.resources;

import com.rodrigo.hruser.entities.User;
import com.rodrigo.hruser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/search")
    public ResponseEntity<User> findByEmail(@RequestParam String email) {
        return Optional.ofNullable(userRepository.findByEmail(email))
                                        .map(user -> ResponseEntity.ok().body(user))
                                        .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
