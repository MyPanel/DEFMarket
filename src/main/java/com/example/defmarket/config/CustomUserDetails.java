package com.example.defmarket.config;

import com.example.defmarket.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;

    /*
    User < UserType = 1 고객
    User < UserType = 2 판매자
    User < UserType = 3 관리자
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        String userRole;
        if(user.getUserType().equals(1)) userRole = "CUSTOMER";
                else if(user.getUserType().equals(2)) userRole = "SELLER";
                else userRole = "ADMIN";
        collection.add(()-> userRole);
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
