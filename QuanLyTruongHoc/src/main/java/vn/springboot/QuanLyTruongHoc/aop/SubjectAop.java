package vn.springboot.QuanLyTruongHoc.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;
import vn.springboot.QuanLyTruongHoc.service.inter.IClassroomService;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;
import vn.springboot.QuanLyTruongHoc.utils.Log;
import vn.springboot.QuanLyTruongHoc.utils.SecurityUtils;

import org.springframework.security.access.AccessDeniedException;
@Aspect
@Component
public class SubjectAop {
    private final ITeacherService iTeacherService;
    private final IClassroomService iClassroomService;
    private final SecurityUtils securityUtils;

    @Autowired
    public SubjectAop(ITeacherService iTeacherService, IClassroomService iClassroomService, SecurityUtils securityUtils) {
        this.iTeacherService = iTeacherService;
        this.iClassroomService = iClassroomService;
        this.securityUtils = securityUtils;
    }

    @Before("execution(* vn.springboot.QuanLyTruongHoc.rest.SubjectController.showAllSubject(..)) && args(..,classId)")
    public void checkAccess(int classId) throws AccessDeniedException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Teacher teacher = iTeacherService.findTeacherByAccountEmail(authentication.getName());
        Classroom classroom = iClassroomService.findClassroomById(classId);

            if (classroom.getTeacher() != teacher && !securityUtils.hasPrincipal()) {
                String mess = "Lỗi quyền truy cập khi xem danh sách môn học với tên lớp: "+classroom.getClassName()+", và người dùng: "+authentication.getName();
                Log.error(mess);
                throw new AccessDeniedException(mess);
            }
    }
}
