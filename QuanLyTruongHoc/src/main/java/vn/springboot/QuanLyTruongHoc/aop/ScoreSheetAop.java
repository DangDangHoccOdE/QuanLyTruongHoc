package vn.springboot.QuanLyTruongHoc.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.ScoreSheet;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;
import vn.springboot.QuanLyTruongHoc.service.inter.IClassroomService;
import vn.springboot.QuanLyTruongHoc.service.inter.IScoreSheetService;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;
import vn.springboot.QuanLyTruongHoc.utils.CheckAccessClassroom;
import vn.springboot.QuanLyTruongHoc.utils.Log;
import vn.springboot.QuanLyTruongHoc.utils.SecurityUtils;

import org.springframework.security.access.AccessDeniedException;

@Aspect
@Component
public class ScoreSheetAop {
    private final SecurityUtils securityUtils;
    private final ITeacherService iTeacherService;
    private final IClassroomService iClassroomService;
    private final CheckAccessClassroom checkAccessClassroom;
    private final IScoreSheetService iScoreSheetService;

    public ScoreSheetAop(SecurityUtils securityUtils, ITeacherService iTeacherService, IClassroomService iClassroomService, CheckAccessClassroom checkAccessClassroom, IScoreSheetService iScoreSheetService) {
        this.securityUtils = securityUtils;
        this.iTeacherService = iTeacherService;
        this.iClassroomService = iClassroomService;
        this.checkAccessClassroom = checkAccessClassroom;
        this.iScoreSheetService = iScoreSheetService;
    }

    @Before(value = "execution(* vn.springboot.QuanLyTruongHoc.rest.ScoreSheetController.showScoreSheetOfClassroom(..)) && args(..,classId,semesterYear)", argNames = "classId,semesterYear")
    public void checkAccess(int classId,String semesterYear) throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Teacher teacher = iTeacherService.findTeacherByAccountEmail(authentication.getName());
        Classroom classroom = iClassroomService.findClassroomById(classId);
        ScoreSheet scoreSheet = iScoreSheetService.findScoreSheetBySemesterYearAndClassId(semesterYear,classId);

        if ((classroom.getTeacher() != teacher && !securityUtils.hasPrincipal() && checkAccessClassroom.hasNotAccessClassroom(classId,teacher))) {
            String mess = "Lỗi quyền truy cập khi xem bảng điểm với lớp: "+classId+" , học kỳ: "+semesterYear+", với người dùng: "+authentication.getName();
                Log.error(mess);
                throw new AccessDeniedException(mess);
            }

        if(scoreSheet==null){
            String mess = "Bảng điểm trống không thể xem,lớp : "+classId+" , học kỳ: "+semesterYear+", với người dùng: "+authentication.getName();
            Log.error(mess);
            throw new NullPointerException(mess);
        }
    }


}
