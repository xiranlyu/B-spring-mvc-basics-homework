package com.thoughtworks.capacity.gtb.mvc;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final Map<Integer, User> userMap = new HashMap<>();
    int id = userMap.size() + 1;

    public UserService() {
        userMap.put(id, new User("Layla", "Lay1a995", "test@email.com"));
        userMap.put(id, new User("Yasu", "Ya3u707", "test@email.com"));
    }

    public void createUser(User user) {
        List<User> usernameCheck= userMap.values().stream()
                .filter(userIterator -> userIterator.getUsername().equals(user.getUsername())).collect(Collectors.toList());
        user.setId(id);
        if (usernameCheck.size() == 0) {
            userMap.put(id, user);
        } else {
            throw new UsernameExistsException("Username exists!");
        }
    }

    public List<User> getUserByUsernameAndPassword(String username, String password) {
        List<User> usernameCheck= userMap.values().stream()
                .filter(userIterator -> userIterator.getUsername().equals(username)).collect(Collectors.toList());
        List<User> passwordCheck= userMap.values().stream()
                .filter(userIterator -> userIterator.getPassword().equals(password)).collect(Collectors.toList());
        if (usernameCheck.size() == 0) {
            throw new UserNotFoundException("wrong username");
        } else if(passwordCheck.size() == 0) {
            throw new UserNotFoundException("wrong password");
        } else {
            return userMap.values().stream()
                    .filter(userIterator -> userIterator.getUsername().equals(username))
                    .filter(userIterator -> userIterator.getPassword().equals(password))
                    .collect(Collectors.toList());
        }
    }
}
