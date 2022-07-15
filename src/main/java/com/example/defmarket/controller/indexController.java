package com.example.defmarket.controller;

import com.example.defmarket.entity.User;
import com.example.defmarket.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class indexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(indexController.class);

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserService userService;

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
        User user = new User();
        if(request.getParameter("user_password").equalsIgnoreCase(request.getParameter("user_passwordConfirm"))) {
            user.setUser_name(request.getParameter("user_name"));
            user.setUser_email(request.getParameter("user_email"));
            user.setUser_password(
                    bCryptPasswordEncoder.encode(request.getParameter("user_password"))
            );
            LOGGER.info("데이터 전달 테스트");
            userService.signup(user);
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }
}
