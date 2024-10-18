package com.advance.user.service;

import com.advance.common.enums.Errors;
import com.advance.common.exception.BaseDuplicateException;
import com.advance.user.domain.User;
import com.advance.user.service.port.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    @Transactional(readOnly = true)
    public Optional<User> findByNameAndAge(String name, String age) {
        return repository.findByNameAndAge(name, age);
    }

    @Transactional(readOnly = true)
    public User getByNameAndAge(String name, String age) {
        return repository.findByNameAndAge(name, age)
            .orElseThrow(() -> new BaseDuplicateException(Errors.DUPLICATED_USER));
    }

    @Transactional(readOnly = true)
    public void findByNameAndAgeThrowIfExist(String name, String age) {
        if(repository.findByNameAndAge(name, age).isPresent()) {
            throw new BaseDuplicateException(Errors.DUPLICATED_USER);
        }
    }

    @Transactional
    public void save(User user) {
        repository.save(user);
    }

}
