package com.example.defmarket.config;

import com.example.defmarket.entity.User;
import com.example.defmarket.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class DefSecurityConfig implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefSecurityConfig.class);
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    public DefSecurityConfig(UserRepository userRepository, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.httpSession = httpSession;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<User> userAcc = Optional.ofNullable(userRepository.findByUserEmail(userEmail));
        User user = userAcc.orElseThrow(()-> new UsernameNotFoundException("요청하신 "+ userEmail + "이 없습니다."));

        httpSession.setAttribute("user", user);
//        httpSession.setAttribute("user", SessionU.fromEntity(user));
        //*주의* Security의 USer임 Entity(X)
        //User(ID, password, 권한) 확인
        return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPassword(), authorities());
    }

    private Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}

