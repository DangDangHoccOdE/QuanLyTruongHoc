package vn.springboot.QuanLyTruongHoc.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String userName = authentication!=null?authentication.getName():"Người sử dụng không tồn tại!";
        String mess = "Người dùng: "+userName+" đã đăng xuất thành công";
        Log.info(mess);

        response.sendRedirect("/");    }
}
