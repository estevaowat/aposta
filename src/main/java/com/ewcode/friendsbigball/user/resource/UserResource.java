package com.ewcode.friendsbigball.user.resource;

import com.ewcode.friendsbigball.user.resource.dto.CreateUserDto;
import com.ewcode.friendsbigball.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> create(CreateUserDto user) {
        userService.create(user);
        return ResponseEntity.ok("User created");
    }

}
