package by.kazakevich.uniteddirect.repository;

import by.kazakevich.uniteddirect.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
