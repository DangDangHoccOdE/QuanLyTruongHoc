package vn.springboot.QuanLyTruongHoc.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    public SecurityUtils(){}

    public boolean hasPrincipal(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_PRINCIPAL"));
    }

    public boolean hasRoleStudent(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch (auth->auth.getAuthority().equals("ROLE_STUDENT"));
    }
}
