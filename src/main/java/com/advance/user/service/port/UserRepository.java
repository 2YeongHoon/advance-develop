package com.advance.user.service.port;

import com.advance.user.domain.User;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findByNameAndAge(String name, String age);

}
