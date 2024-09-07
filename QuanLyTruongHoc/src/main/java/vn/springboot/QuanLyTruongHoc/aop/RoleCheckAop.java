package vn.springboot.QuanLyTruongHoc.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.springboot.QuanLyTruongHoc.utils.Log;
import vn.springboot.QuanLyTruongHoc.utils.SecurityUtils;

import org.springframework.security.access.AccessDeniedException;

@Aspect
@Component
public class RoleCheckAop {
    private final SecurityUtils securityUtils;

    @Autowired
    public RoleCheckAop(SecurityUtils securityUtils) {
        this.securityUtils = securityUtils;
    }

    @Before("@annotation(vn.springboot.QuanLyTruongHoc.annotation.CheckTeacherRole)")
    public void checkHasAccess() throws AccessDeniedException {
        if(securityUtils.hasRoleStudent()) {
            String mess = "Không có quyền truy cập!";
            Log.error(mess);
            throw new AccessDeniedException(mess);
        }
    }
}
