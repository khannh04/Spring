package vn.hoidanit.springsieutoc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.springsieutoc.domain.User;
import vn.hoidanit.springsieutoc.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public String handlHello() {
        return "Hello from service :>";
    }

    public User handlSaveUser(User user) {
        User kabeo = this.userRepository.save(user);
        System.out.println(kabeo);
        return kabeo;
    }

}
