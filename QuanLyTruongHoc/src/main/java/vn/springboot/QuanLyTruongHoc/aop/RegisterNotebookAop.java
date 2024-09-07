package vn.springboot.QuanLyTruongHoc.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;
import vn.springboot.QuanLyTruongHoc.service.inter.IClassroomService;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;
import vn.springboot.QuanLyTruongHoc.utils.CheckAccessClassroom;
import vn.springboot.QuanLyTruongHoc.utils.Log;
import vn.springboot.QuanLyTruongHoc.utils.SecurityUtils;

import org.springframework.security.access.AccessDeniedException;

@Aspect
@Component
public class RegisterNotebookAop {
    private final IClassroomService iClassroomService;
    private final CheckAccessClassroom checkAccessClassroom;
    private final ITeacherService iTeacherService;
    private final SecurityUtils securityUtils;

    public RegisterNotebookAop( IClassroomService iClassroomService, CheckAccessClassroom checkAccessClassroom, ITeacherService iTeacherService, SecurityUtils securityUtils) {
        this.iClassroomService = iClassroomService;
        this.checkAccessClassroom = checkAccessClassroom;
        this.iTeacherService = iTeacherService;
        this.securityUtils = securityUtils;
    }

    @Before("execution(* vn.springboot.QuanLyTruongHoc.rest.RegisterNotebookController.getRegisterNotebook(..)) && args(..,classId)")
    public void checkAccess(int classId) throws AccessDeniedException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Teacher teacher = iTeacherService.findTeacherByAccountEmail(authentication.getName());

        Classroom classroom = iClassroomService.findClassroomById(classId);

        if(teacher!=null && !securityUtils.hasPrincipal() && checkAccessClassroom.hasNotAccessClassroom(classId,teacher)
                                            && classroom.getTeacher()!=teacher){
            String mess = "Lỗi quyền truy cập khi xem sổ đầu bài với tên lớp: "+classroom.getClassName()+", người dùng: "+authentication.getName();
            Log.error(mess);
            throw new AccessDeniedException(mess);
        }

    }
}
