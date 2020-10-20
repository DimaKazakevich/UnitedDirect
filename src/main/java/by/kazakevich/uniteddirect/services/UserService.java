package by.kazakevich.uniteddirect.services;

import by.kazakevich.uniteddirect.domain.User;

import java.util.List;

public interface UserService {
    User register(User user);

    List<User> getAll();

    User findByEmail(String email);

    User findById(Integer id);
}
