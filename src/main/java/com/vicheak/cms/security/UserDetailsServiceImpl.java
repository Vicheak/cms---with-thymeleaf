package com.vicheak.cms.security;

import com.vicheak.cms.model.User;
import com.vicheak.cms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.selectByUsername(username)
                .orElseThrow(
                        () -> {
                            log.info("Username has not been found!");
                            return new UsernameNotFoundException("User is not found in the system!");
                        }
                );

        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUser(user);

        log.info("User's username and object : {} / {}",  user.getUsername(), user);
        log.info("User's username : {}", customUserDetails.getUsername());
        log.info("User roles : {}", customUserDetails.getAuthorities());

        return customUserDetails;
    }

}
