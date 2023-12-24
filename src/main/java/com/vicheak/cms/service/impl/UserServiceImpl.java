package com.vicheak.cms.service.impl;

import com.vicheak.cms.model.User;
import com.vicheak.cms.repository.UserRepository;
import com.vicheak.cms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.select();
    }

}
