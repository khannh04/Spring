package vn.hoidanit.springsieutoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.springsieutoc.domain.User;
import java.util.List;
import java.util.Optional;

//crud: create, read, update, delete
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User khanh);

    List<User> findByEmail(String email);

    User findById(long id);

    void deleteById(long id);

}
