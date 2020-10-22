package com.thoughtworks.capacity.gtb.mvc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;

    @Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address.")
    private String email;
    @NotBlank(message = "username required")
    @Pattern(regexp = "^[0-9a-zA-Z_]+$", message = "invalid username")
    @Size(min = 3, max = 10, message = "username is too short or too long")
    private String username;
    @NotBlank(message = "password required")
    @Size(min = 5, max = 12, message = "password is too short or too long")
    private String password;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
