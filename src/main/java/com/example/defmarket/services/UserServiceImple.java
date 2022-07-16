package com.example.defmarket.services;

import com.example.defmarket.entity.User;
import com.example.defmarket.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional(readOnly = true) //데이터 변경은 기본적으로 트랜잭션안에 있어야 한다.
@RequiredArgsConstructor // 자동 주입
public class UserServiceImple implements UserService, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImple.class);


    //@Autowird를 추가한다면,
    //EntityManager의 @PersistenceContext와 똑같이 자동 주입을 위함이겠지?
    // 스프링 Bean에 등록되어있는 MemberRepository를 주입해줄 것이다!!
    private final UserRepository userRepository;
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

   @Transactional
   @Override
    //회원가입
    public void signupUserDB(User user){

        // validateDuplicateMember(member); //중복 회원 검증
        userRepository.save(user);
       inMemoryUserDetailsManager.createUser(loadUserByUsername(user.getUser_name()));
        LOGGER.info("유저 : " + user.getUser_name() + " 회원가입 성공.");

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        final User user = userRepository.findByUsername(username);
//        return Optional.ofNullable(user)
//                .map(User::new)
//                .orElseThrow(() -> new UsernameNotFoundException(username));

       return null;
    }
}