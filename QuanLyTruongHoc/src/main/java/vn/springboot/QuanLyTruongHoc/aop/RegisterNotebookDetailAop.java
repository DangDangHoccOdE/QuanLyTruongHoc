package vn.springboot.QuanLyTruongHoc.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.RegisterNotebookDetail;
import vn.springboot.QuanLyTruongHoc.entity.Subject;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;
import vn.springboot.QuanLyTruongHoc.service.inter.IClassroomService;
import vn.springboot.QuanLyTruongHoc.service.inter.IRegisterNotebookDetailService;
import vn.springboot.QuanLyTruongHoc.service.inter.ISubjectService;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;
import vn.springboot.QuanLyTruongHoc.utils.CheckAccessClassroom;
import vn.springboot.QuanLyTruongHoc.utils.Log;
import vn.springboot.QuanLyTruongHoc.utils.SecurityUtils;

import org.springframework.security.access.AccessDeniedException;
import java.util.List;

@Aspect
@Component
public class RegisterNotebookDetailAop {
    private final SecurityUtils securityUtils;
    private final ITeacherService iTeacherService;
    private final CheckAccessClassroom checkAccessClassroom;
    private final IClassroomService iClassroomService;
    private final IRegisterNotebookDetailService iRegisterNotebookDetailService;
    private final ISubjectService iSubjectService;

    public RegisterNotebookDetailAop(SecurityUtils securityUtils, ITeacherService iTeacherService, CheckAccessClassroom checkAccessClassroom, IClassroomService iClassroomService, IRegisterNotebookDetailService iRegisterNotebookDetailService, ISubjectService iSubjectService) {
        this.securityUtils = securityUtils;
        this.iTeacherService = iTeacherService;
        this.checkAccessClassroom = checkAccessClassroom;
        this.iClassroomService = iClassroomService;
        this.iRegisterNotebookDetailService = iRegisterNotebookDetailService;
        this.iSubjectService = iSubjectService;
    }

    @Before(value = "(execution(* vn.springboot.QuanLyTruongHoc.rest.RegisterNotebookDetailController.create(..))" +
            "|| execution(* vn.springboot.QuanLyTruongHoc.rest.RegisterNotebookDetailController.saveLecture(..)))" +
            " && args(..,classId)")
    public void checkAccessCreate(int classId) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Teacher teacher = iTeacherService.findTeacherByAccountEmail(authentication.getName());

        Classroom classroom = iClassroomService.findClassroomById(classId);

        if(teacher!=null){
            List<Subject> subjects = iSubjectService.uniqueSubjects(classroom, teacher);
                if(!securityUtils.hasPrincipal() && (checkAccessClassroom.hasNotAccessClassroom(classId,teacher)|| subjects.isEmpty())){
                    String username = authentication.getName();
                    String mess = "Lỗi quyền truy cập khi thao tác với sổ đầu bài, lớp: " +classroom.getClassName() + ", người dùng: " + username;
                    Log.error(mess);
                    throw new AccessDeniedException(mess);
             }
        }

    }

    @Before(value = "(execution(* vn.springboot.QuanLyTruongHoc.rest.RegisterNotebookDetailController.editRegisterNotebookDetail(..))" +
            "|| execution(* vn.springboot.QuanLyTruongHoc.rest.RegisterNotebookDetailController.saveEditRegisterNotebookDetail(..)))" +
            " && args(..,registerNotebookDetailId)")
    public void checkAccessEdit(int registerNotebookDetailId) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Teacher teacher = iTeacherService.findTeacherByAccountEmail(authentication.getName());

        RegisterNotebookDetail registerNotebookDetail = iRegisterNotebookDetailService.findRegisterNotebookDetailById(registerNotebookDetailId);
        Classroom classroom = registerNotebookDetail.getClassroom();
        if (teacher!= null && !securityUtils.hasPrincipal() && registerNotebookDetail.getTeacher()!=teacher) {
            String username = authentication.getName();
            String mess = "Lỗi quyền truy cập khi chỉnh sửa sổ đầu bài, mã sổ:  " + registerNotebookDetailId+", lớp: "+classroom.getClassName()+ ", người dùng: " + username;
            Log.error(mess);
            throw new AccessDeniedException(mess);
        }

    }
}
