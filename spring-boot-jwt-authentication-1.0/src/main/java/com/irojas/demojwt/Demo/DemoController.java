package com.irojas.demojwt.Demo;

import com.irojas.demojwt.User.User;
import com.irojas.demojwt.User.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController {
    private final UserRepository userRepository;

    @PostMapping(value = "demo")
    public ResponseEntity<?> welcome(@RequestBody String username) {
        try {
            Optional<User> user = userRepository.findByUsername(username);

            if (user.isPresent()) {
                return ResponseEntity.ok(user.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

}
