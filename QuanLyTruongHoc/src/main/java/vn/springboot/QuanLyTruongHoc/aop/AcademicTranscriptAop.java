package vn.springboot.QuanLyTruongHoc.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.Student;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;
import vn.springboot.QuanLyTruongHoc.service.inter.IStudentService;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;
import vn.springboot.QuanLyTruongHoc.utils.Log;
import vn.springboot.QuanLyTruongHoc.utils.SecurityUtils;


@Aspect
@Component
public class AcademicTranscriptAop {
    private final ITeacherService iTeacherService;
    private final IStudentService iStudentService;
    private final SecurityUtils securityUtils;

    public AcademicTranscriptAop(ITeacherService iTeacherService, IStudentService iStudentService, SecurityUtils securityUtils) {
        this.iTeacherService = iTeacherService;
        this.iStudentService = iStudentService;
        this.securityUtils = securityUtils;
    }

    @Before("execution(* vn.springboot.QuanLyTruongHoc.rest.AcademicTranscriptController.showAcademicTranscript(..))&& args(..,studentId)")
    public void checkAccess(String studentId) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Teacher teacher=iTeacherService.findTeacherByAccountEmail(authentication.getName());

        Student student = iStudentService.findStudentById(studentId);
        Classroom classroom = student.getClassroom();
        if(classroom.getTeacher() != teacher && !securityUtils.hasPrincipal()){
            String mess = "Lỗi quyền truy cập khi xem sổ học bạ với mã học sinh "+student.getStudentId() + ", người dùng đăng nhập: "+authentication.getName();
            Log.error(mess);
            throw new AccessDeniedException(mess);
        }
    }
}
