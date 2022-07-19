package com.example.defmarket.controller;

import com.example.defmarket.entity.User;
import com.example.defmarket.services.UserServiceImple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class indexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(indexController.class);


    private final UserServiceImple userServiceImple;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public indexController(UserServiceImple userServiceImple) {
        this.userServiceImple = userServiceImple;
    }

    @RequestMapping(value = "/")
    public String main() {
        LOGGER.info("메인 페이지 이동");

        return "main";
    }

    @RequestMapping("login")
    public String loginPage() {
        LOGGER.info("로그인 페이지 이동");
        return "login";
    }

    @PostMapping("/signup")
    public String login(HttpServletRequest request) {
        LOGGER.info(request.getParameter("user_name"));
        LOGGER.info("회원가입 시도");
        if(request.getParameter("user_password").equalsIgnoreCase(request.getParameter("user_passwordConfirm"))) {
            User user = new User(
                    request.getParameter("user_name"),
                    request.getParameter("user_email"),
                    bCryptPasswordEncoder.encode(request.getParameter("user_password"))
            );
            userServiceImple.signupUserDB(user);
            LOGGER.info(request.getSession().getId());
            LOGGER.info("데이터 전달 테스트");
//            userService.signup(user);
            return "main";
        } else {
            return "redirect:/login";
        }
    }
}
