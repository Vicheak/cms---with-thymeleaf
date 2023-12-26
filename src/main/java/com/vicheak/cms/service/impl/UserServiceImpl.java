package com.vicheak.cms.service.impl;

import com.vicheak.cms.model.User;
import com.vicheak.cms.repository.UserRepository;
import com.vicheak.cms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.select();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        user.setUuid(UUID.randomUUID().toString());
        user.setIsDeleted(false);
        user.setCreatedAt(LocalDate.now());

        //save new user to the database
        userRepository.insert(user);

        //use generated key from database
        user.getRoles().forEach(role -> {
            if (Objects.nonNull(role.getId())) {
                userRepository.insertUserRoles(user.getId(), role.getId());
            }
        });
    }

}
