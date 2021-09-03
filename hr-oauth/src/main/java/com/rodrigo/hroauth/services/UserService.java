package com.rodrigo.hroauth.services;

import com.rodrigo.hroauth.entities.User;
import com.rodrigo.hroauth.exceptions.EmailNotFountException;
import com.rodrigo.hroauth.feignClient.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    public User findByEmail(String email) {
        User user = userFeignClient.findByEmail(email).getBody();
            return Optional.ofNullable(user).orElseThrow(() -> new EmailNotFountException("Email não encontrado"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userFeignClient.findByEmail(username).getBody();
            return Optional.ofNullable(user).orElseThrow(() -> new EmailNotFountException("Email não encontrado"));
    }
}
