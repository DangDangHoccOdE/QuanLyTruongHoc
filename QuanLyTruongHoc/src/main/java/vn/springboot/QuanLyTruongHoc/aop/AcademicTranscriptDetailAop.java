package vn.springboot.QuanLyTruongHoc.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import vn.springboot.QuanLyTruongHoc.entity.AcademicTranscriptDetail;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.Student;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;
import vn.springboot.QuanLyTruongHoc.service.inter.IAcademicTranscriptDetailService;
import vn.springboot.QuanLyTruongHoc.service.inter.IStudentService;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;
import vn.springboot.QuanLyTruongHoc.utils.Log;
import vn.springboot.QuanLyTruongHoc.utils.SecurityUtils;

import org.springframework.security.access.AccessDeniedException;
@Aspect
@Component
public class AcademicTranscriptDetailAop {
    private final ITeacherService iTeacherService;
    private final IStudentService iStudentService;
    private final SecurityUtils securityUtils;
    private final IAcademicTranscriptDetailService iAcademicTranscriptDetailService;

    public AcademicTranscriptDetailAop(ITeacherService iTeacherService, IStudentService iStudentService, SecurityUtils securityUtils, IAcademicTranscriptDetailService iAcademicTranscriptDetailService) {
        this.iTeacherService = iTeacherService;
        this.iStudentService = iStudentService;
        this.securityUtils = securityUtils;
        this.iAcademicTranscriptDetailService = iAcademicTranscriptDetailService;
    }

    @Before("(execution(* vn.springboot.QuanLyTruongHoc.rest.AcademicTranscriptDetailController.createAcademicTranscriptDetail(..))" +
            "|| execution(* vn.springboot.QuanLyTruongHoc.rest.AcademicTranscriptDetailController.saveAcademicTranscriptDetail(..)))&& args(..,idStudent)")
    public void checkAccess(String idStudent) throws AccessDeniedException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Teacher teacher=iTeacherService.findTeacherByAccountEmail(authentication.getName());

        Student student = iStudentService.findStudentById(idStudent);
        Classroom classroom = student.getClassroom();
        if(classroom.getTeacher() != teacher && !securityUtils.hasPrincipal()){
            String mess = "Lỗi quyền truy cập khi tạo sổ học bạ với mã học sinh "+student.getStudentId() + ", người dùng đăng nhập: "+authentication.getName();
            Log.error(mess);
            throw new AccessDeniedException(mess);
        }
    }

    @Before("(execution(* vn.springboot.QuanLyTruongHoc.rest.AcademicTranscriptDetailController.editAcademicTranscriptDetail(..))" +
            "|| execution(* vn.springboot.QuanLyTruongHoc.rest.AcademicTranscriptDetailController.saveEditAcademicTranscriptDetail(..)))&& args(..,academicTranscriptDetailId)")
    public void checkAccess(int academicTranscriptDetailId) throws AccessDeniedException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Teacher teacher=iTeacherService.findTeacherByAccountEmail(authentication.getName());

        AcademicTranscriptDetail academicTranscriptDetail = iAcademicTranscriptDetailService.findAcademicTranscriptDetailById(academicTranscriptDetailId);
        Student student = academicTranscriptDetail.getStudent();
        Classroom classroom = student.getClassroom();
        if(classroom.getTeacher() != teacher && !securityUtils.hasPrincipal()){
            String mess = "Lỗi quyền truy cập khi sửa sổ học bạ với mã học sinh "+student.getStudentId() + ", người dùng đăng nhập: "+authentication.getName();
            Log.error(mess);
            throw new AccessDeniedException(mess);
        }
    }
}
