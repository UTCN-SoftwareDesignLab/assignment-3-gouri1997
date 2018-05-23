package hospital.repository;

import hospital.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String Username);
    List<User> findByRole(String role);
}
