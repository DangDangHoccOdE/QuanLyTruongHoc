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
import vn.springboot.QuanLyTruongHoc.service.inter.IClassroomService;
import vn.springboot.QuanLyTruongHoc.service.inter.IStudentService;
import vn.springboot.QuanLyTruongHoc.service.inter.ISubjectService;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;
import vn.springboot.QuanLyTruongHoc.utils.CheckAccessClassroom;
import vn.springboot.QuanLyTruongHoc.utils.Log;
import vn.springboot.QuanLyTruongHoc.utils.SecurityUtils;

import org.springframework.security.access.AccessDeniedException;
import java.util.List;

@Aspect
@Component
public class ReportCardDetailAop {
    private final CheckAccessClassroom checkAccessClassroom;
    private final ITeacherService iTeacherService;
    private final SecurityUtils securityUtils;
    private final ISubjectService iSubjectService;
    private final IClassroomService iClassroomService;
    private final IStudentService iStudentService;

    public ReportCardDetailAop(CheckAccessClassroom checkAccessClassroom, ITeacherService iTeacherService, SecurityUtils securityUtils, ISubjectService iSubjectService, IClassroomService iClassroomService, IStudentService iStudentService) {
        this.checkAccessClassroom = checkAccessClassroom;
        this.iTeacherService = iTeacherService;
        this.securityUtils = securityUtils;
        this.iSubjectService = iSubjectService;
        this.iClassroomService = iClassroomService;
        this.iStudentService = iStudentService;
    }

    @Before("(execution( * vn.springboot.QuanLyTruongHoc.rest.ReportCardDetailController.createReportCard(..))" +
            "|| execution( * vn.springboot.QuanLyTruongHoc.rest.ReportCardDetailController.saveReportCard(..))) && args(..,classId)")
    public void checkAccess(int classId) throws AccessDeniedException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Teacher teacher = iTeacherService.findTeacherByAccountEmail(authentication.getName());
        Classroom classroom = iClassroomService.findClassroomById(classId);

        if(teacher!=null) {
            List<Subject> subjects = iSubjectService.uniqueSubjects(classroom, teacher);

            if ((!securityUtils.hasPrincipal() && (checkAccessClassroom.hasNotAccessClassroom(classId, teacher) || subjects.isEmpty()))) {
                String mess = "Lỗi quyền truy cập khi tạo phiếu điểm với lớp: " + classroom.getClassName() + ", và người dùng: " + authentication.getName();
                Log.error(mess);
                throw new AccessDeniedException(mess);
            }
        }
    }


    @Before(value = "execution( * vn.springboot.QuanLyTruongHoc.rest.ReportCardDetailController.showReportCardDetail(..)) && args(..,subjectId,studentId,semesterYear)", argNames = "subjectId,studentId,semesterYear")
    public void checkAccess(int subjectId,String studentId,String semesterYear) throws AccessDeniedException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Subject subject = iSubjectService.findSubjectById(subjectId);
        Student student = iStudentService.findStudentById(studentId);
        Classroom classroom = student.getClassroom();

        Teacher teacher = iTeacherService.findTeacherByAccountEmail(authentication.getName());
        Student studentFind = iStudentService.findStudentByAccountEmail(authentication.getName());

        if (teacher != null) {
            if (!securityUtils.hasPrincipal() && (subject.getTeacher() != teacher && classroom.getTeacher() != teacher)) {
                handleAccessDenied(student, subject, semesterYear, authentication.getName());
            }
        } else {
            if (student != studentFind) {
                handleAccessDenied(student, subject, semesterYear, authentication.getName());
            }
        }
    }

    private void handleAccessDenied(Student student, Subject subject, String semesterYearDecrypt, String userName) throws AccessDeniedException {
        String mess = "Lỗi quyền truy cập khi xem phiếu điểm với học sinh: " + student.getStudentName() + " ,môn: " + subject.getSubName() + " ,học kỳ: " + semesterYearDecrypt + ", và người dùng: " + userName;
        Log.error(mess);
        throw new AccessDeniedException(mess);
    }
}
