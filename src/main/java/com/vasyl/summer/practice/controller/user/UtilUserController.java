package com.vasyl.summer.practice.controller.user;

import com.vasyl.summer.practice.dao.UserDao;
import com.vasyl.summer.practice.database.entity.User;
import com.vasyl.summer.practice.database.repository.UserRepository;
import com.vasyl.summer.practice.service.UserService;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/util")
@RequiredArgsConstructor
public class UtilUserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final UserDao userDao;

    @GetMapping("/user/{user-id}/role")
    public ResponseEntity<?> getUserRole(@PathVariable("user-id") String userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user.getRole());
    }

}
