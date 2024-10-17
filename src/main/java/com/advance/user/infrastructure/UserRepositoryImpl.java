package com.advance.user.infrastructure;

import com.advance.user.domain.User;
import com.advance.user.service.port.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    @Override
    public void save(User user) {
        jpaRepository.save(user);
    }

    @Override
    public Optional<User> findByNameAndAge(String name, String age) {
        return jpaRepository.findByNameAndAge(name, age);
    }

}
