package vn.hoidanit.springsieutoc.service;

import java.util.List;
import java.util.Optional;
import vn.hoidanit.springsieutoc.domain.Role;

import org.springframework.stereotype.Service;

import vn.hoidanit.springsieutoc.domain.User;
import vn.hoidanit.springsieutoc.repository.RoleRepository;
import vn.hoidanit.springsieutoc.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public String handlHello() {
        return "Hello from service :>";
    }

    public User handleSaveUser(User user) {
        User kabeo = this.userRepository.save(user);
        System.out.println(kabeo);
        return kabeo;
    }

    public void deleteAUser(long id) {
        this.userRepository.deleteById(id);
    }

    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }

}
