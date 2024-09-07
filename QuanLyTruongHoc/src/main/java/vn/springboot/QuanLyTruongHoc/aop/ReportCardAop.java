package vn.springboot.QuanLyTruongHoc.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.Student;
import vn.springboot.QuanLyTruongHoc.entity.Subject;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;
import vn.springboot.QuanLyTruongHoc.service.inter.IStudentService;
import vn.springboot.QuanLyTruongHoc.service.inter.ISubjectService;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;
import vn.springboot.QuanLyTruongHoc.utils.CheckAccessClassroom;
import vn.springboot.QuanLyTruongHoc.utils.Log;
import vn.springboot.QuanLyTruongHoc.utils.SecurityUtils;

import org.springframework.security.access.AccessDeniedException;
import java.util.*;

@Aspect
@Component
public class ReportCardAop {
    private final ITeacherService iTeacherService;
    private final IStudentService iStudentService;
    private final SecurityUtils securityUtils;
    private final ISubjectService iSubjectService;
    private final CheckAccessClassroom checkAccessClassroom;

    public ReportCardAop( ITeacherService iTeacherService, IStudentService iStudentService, SecurityUtils securityUtils, ISubjectService iSubjectService, CheckAccessClassroom checkAccessClassroom) {
        this.iTeacherService = iTeacherService;
        this.iStudentService = iStudentService;
        this.securityUtils = securityUtils;
        this.iSubjectService = iSubjectService;
        this.checkAccessClassroom = checkAccessClassroom;
    }

    @Before(value = "execution(* vn.springboot.QuanLyTruongHoc.rest.ReportCardController.getAllReportCard(..)) && args(..,studentId,semesterYear)",argNames = "studentId,semesterYear")
    public void checkAccess(String studentId, String semesterYear) throws AccessDeniedException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();
        Teacher teacher = iTeacherService.findTeacherByAccountEmail(email);
        Student student = iStudentService.findStudentByAccountEmail(email);

        Student studentFind = iStudentService.findStudentById(studentId);
        Classroom  classroom = studentFind.getClassroom();

        if(teacher!=null){
            List<Subject> subjectList = iSubjectService.getSubjectByRole(classroom,teacher);
            if(!securityUtils.hasPrincipal() && (checkAccessClassroom.hasNotAccessClassroom(classroom.getId(),teacher)||subjectList.isEmpty())){
                handleAccessDenied(studentId, semesterYear, email);
            }
        }else{
            if(student !=studentFind){
                handleAccessDenied(studentId, semesterYear, email);
            }
        }
    }

    private void handleAccessDenied(String studentId, String semesterYear, String userName) throws AccessDeniedException{
        String mess = "Lỗi quyền truy cập khi xem phiếu điểm với mã học sinh: " + studentId + ", học kỳ: " + semesterYear + ", và người dùng: " + userName;
        Log.error(mess);
        throw new AccessDeniedException(mess);
    }
}
