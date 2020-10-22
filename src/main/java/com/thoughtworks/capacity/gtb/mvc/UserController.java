package com.thoughtworks.capacity.gtb.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@RestController
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid User user) {
        userService.createUser(user);
    }

    @GetMapping("/login")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUserByIdAndPassword(@RequestParam(name = "username", required = true)
                                                 @NotBlank(message = "username required")
                                                 @Pattern(regexp = "^[0-9a-zA-Z_]+$", message = "invalid username")
                                                 @Size(min = 3, max = 10, message = "username is too short or too long")
                                                         String username,
                                             @RequestParam(name = "password", required = true)
                                             @Size(min = 5, max = 12, message = "password is too short or too long")
                                             @NotBlank(message = "password required")
                                                     String password) {
        return userService.getUserByUsernameAndPassword(username, password);
    }
}
