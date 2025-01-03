package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.exception.Database.UserNotFoundException;
import org.example.model.Session;
import org.example.model.User;
import org.example.repositories.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SessionService sessionService;

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    public boolean DoesUserExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

/*    public boolean isValidCredentials(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        return user.isPresent()&&password.equals(user.get().getPassword());
    }*/


    public User getUserByUsernameAndPassword(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()&&password.equals(user.get().getPassword())){
            return user.get();
        }
        throw new UserNotFoundException(username);
    }



    public void addUser(String username, String password) {
        User user = new User(username, password);
        userRepository.save(user);
    }



}
