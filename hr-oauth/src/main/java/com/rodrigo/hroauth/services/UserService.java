package com.rodrigo.hroauth.services;

import com.rodrigo.hroauth.entities.User;
import com.rodrigo.hroauth.exceptions.EmailNotFountException;
import com.rodrigo.hroauth.feignClient.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    public User findByEmail(String email) {
        User user = userFeignClient.findByEmail(email).getBody();
        if(user == null) {
            throw new EmailNotFountException("Email não encontrado");
        }
        return user;
    }
}
