package com.roominventory.roominventorysys.config;

import com.roominventory.roominventorysys.model.User;
import com.roominventory.roominventorysys.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    @Order(1)
    CommandLineRunner initializeUsers(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                System.out.println("User Repository count was 0");
                List<User> userList = new ArrayList<>();
                userList.add(new User("admin1", "$2a$12$7mEVcqvFpFkOPBKpZmNaX.7Z.txBIZDB/.mDe4AmZziRrfpWxh3Cq", "ADMIN"));
                userList.add(new User("tywaine", "$2a$12$2f6nhDEfqViNIWeMWtxXEOf3cuE/axkRZSajd5IlVt5BhJs6zhy.m", "ADMIN"));
                userList.add(new User("maurice", "$2a$12$zhyc3Q1t1K8LYUlbzkODyuH6sfaExsWoK6xKMRLuuyXHkqQZ866.W", "ADMIN"));
                userList.add(new User("vanessa", "$2a$12$UtUqoI8OMIfu9GT3CBXhU.0ZH5XUPns.vRn5gs1pWcJ9PsSVnnEUO", "ADMIN"));
                userList.add(new User("ron-hugh", "$2a$12$MBgRIzBXJx6eR1P3fatwquGH4Vw9SKCju/EDo.71UbSLUN2ohAh1a", "ADMIN"));
                userList.add(new User("chev", "$2a$12$56SKSL5zAn4NEGNeRhMFcOUKzQcvoRsUXVypHjL2fOGnGrLHiAtBO", "ADMIN"));
                userList.add(new User("leah-jay", "$2a$12$6uDiLm149lq1ih0CkYtoSeCseDcI9QZpQ5PkBli4CRuDUVgep2eha", "ADMIN"));
                userList.add(new User("staff1", "$2a$12$/YTKoIJ2ky2.DslSNg73tON793DWt9bTjNSYfFXZCKoIfvzraBzvO", "STAFF"));

                userRepository.saveAll(userList);
                System.out.println("Default User data initialized successfully.");
            }
            else {
                System.out.println("User table already populated. Skipping initialization.");
            }
        };
    }
}
