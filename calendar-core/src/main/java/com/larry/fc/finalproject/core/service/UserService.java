package com.larry.fc.finalproject.core.service;

import com.larry.fc.finalproject.core.domain.entity.User;
import com.larry.fc.finalproject.core.domain.entity.repository.UserRepository;
import com.larry.fc.finalproject.core.dto.UserCreateReq;
import com.larry.fc.finalproject.core.util.Encryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Larry
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final Encryptor bcryptEncryptor;
    private final UserRepository userRepository;

    @Transactional
    public User create(UserCreateReq req) {
        userRepository.findByEmail(req.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("cannot find user");
                });
        return userRepository.save(User.builder()
                .name(req.getName())
                .password(bcryptEncryptor.encrypt(req.getPassword()))
                .email(req.getEmail())
                .birthday(req.getBirthday())
                .build());
    }

    @Transactional
    public Optional<User> findPwMatchUser(String email, String password) {
        return userRepository.findByEmail(email)
                .map(u -> u.isMatched(bcryptEncryptor, password) ? u : null);
    }

    public User getOrThrowById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("no user."));
    }
}
