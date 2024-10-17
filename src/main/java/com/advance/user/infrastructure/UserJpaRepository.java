package com.advance.user.infrastructure;

import com.advance.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByNameAndAge(String name, String age);

}
