package com.higgsup.oauth.security;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import com.higgsup.oauth.persistence.model.UserSystem;
import com.higgsup.oauth.persistence.repo.UserSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private UserSystemRepository userSystemRepository;

    @Override
    public String execute(Connection<?> connection) {
//        System.out.println("signup === ");
//        final UserSystem user = new UserSystem();
//        user.setName(connection.getDisplayName());
//        user.setPassword(randomAlphabetic(8));
//        userSystemRepository.save(user);
//        return String.valueOf(user.getId());
        return "";
    }

}
