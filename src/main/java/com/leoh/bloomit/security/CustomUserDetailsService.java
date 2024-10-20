package com.leoh.bloomit.security;

import com.leoh.bloomit.user.entity.User;
import com.leoh.bloomit.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User findUser = userRepository.findByUsername(username);

        if (ObjectUtils.isEmpty(findUser)) {
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(findUser);
    }

}
