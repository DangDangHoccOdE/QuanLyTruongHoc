package vn.springboot.QuanLyTruongHoc.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;
import vn.springboot.QuanLyTruongHoc.utils.Log;
import vn.springboot.QuanLyTruongHoc.utils.SecurityUtils;

import org.springframework.security.access.AccessDeniedException;
@Aspect
@Component
public class TeacherAop {
    private final ITeacherService iTeacherService;
    private final SecurityUtils securityUtils;

    @Autowired
    public TeacherAop(ITeacherService iTeacherService, SecurityUtils securityUtils) {
        this.iTeacherService = iTeacherService;
        this.securityUtils = securityUtils;
    }

    @Before("(execution(* vn.springboot.QuanLyTruongHoc.rest.TeacherController.editTeacher(..))" +
            "|| execution(* vn.springboot.QuanLyTruongHoc.rest.TeacherController.saveEditTeacher(..))"+
            "|| execution(* vn.springboot.QuanLyTruongHoc.rest.TeacherController.changeInfoAccount(..))" +
            "|| execution(* vn.springboot.QuanLyTruongHoc.rest.TeacherController.saveInfoAccount(..)))&& args(..,teacherId)")
    public void checkAccess(String teacherId) throws AccessDeniedException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Teacher teacher = iTeacherService.findTeacherByAccountEmail(authentication.getName());

        if(teacher!=null && !securityUtils.hasPrincipal() && !teacherId.equals(teacher.getTeacherId())){
            String mess = "Bạn không có quyền chỉnh sửa thông tin giáo viên, người sử dụng: "+authentication.getName();
            Log.info(mess);
            throw new AccessDeniedException(mess);
        }
    }
}
