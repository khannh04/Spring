package vn.hoidanit.springsieutoc.service;

import java.util.List;
import java.util.Optional;
import vn.hoidanit.springsieutoc.domain.Role;

import org.springframework.stereotype.Service;

import vn.hoidanit.springsieutoc.domain.User;
import vn.hoidanit.springsieutoc.domain.dto.RegisterDTO;
import vn.hoidanit.springsieutoc.repository.OrderRepository;
import vn.hoidanit.springsieutoc.repository.ProductRepository;
import vn.hoidanit.springsieutoc.repository.RoleRepository;
import vn.hoidanit.springsieutoc.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
            ProductRepository productRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email) {
        return this.userRepository.findOneByEmail(email);
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

    public User regiterDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();

        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());

        return user;
    }

    public boolean checkEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public long countUsers() {
        return this.userRepository.count();
    }

    public long countProducts() {
        return this.productRepository.count();
    }

    public long countOrders() {
        return this.orderRepository.count();
    }
}
