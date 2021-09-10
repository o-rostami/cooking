package com.infosys.coocking.utils;


import com.infosys.coocking.model.entity.MyUserDetails;
import com.infosys.coocking.model.entity.UserEntity;
import com.infosys.coocking.service.user.JwtUserDetailsService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * using security utils to get user details from session
 * which will be completed after security module has added to project
 *
 * @author Omid Rostami
 */
@Component
@NoArgsConstructor
public class SecurityUtils {

    private static JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    public SecurityUtils(JwtUserDetailsService jwtUserDetailsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
    }


    public static String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = null;
        if (authentication != null) {
            userName = authentication.getName();
        }
        return userName;
    }






    public static UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication) && Objects.nonNull(authentication.getName())) {
            MyUserDetails user = (MyUserDetails) jwtUserDetailsService.loadUserByUsername(authentication.getName());
            return user.getUser();
        }
        return null;
    }
}
