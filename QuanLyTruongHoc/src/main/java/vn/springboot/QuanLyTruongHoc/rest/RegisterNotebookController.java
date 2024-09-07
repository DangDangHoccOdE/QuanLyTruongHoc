package vn.springboot.QuanLyTruongHoc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.springboot.QuanLyTruongHoc.annotation.CheckTeacherRole;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.RegisterNotebookDetail;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;
import vn.springboot.QuanLyTruongHoc.service.inter.IClassroomService;
import vn.springboot.QuanLyTruongHoc.service.inter.IRegisterNotebookDetailService;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;
import vn.springboot.QuanLyTruongHoc.utils.Log;

import java.util.List;

@Controller
@RequestMapping("/api/registerNotebook")
public class RegisterNotebookController {
    private final IRegisterNotebookDetailService iRegisterNotebookDetailService;
    private final IClassroomService iClassroomService;
    private final ITeacherService iTeacherService;

    @Autowired
    public RegisterNotebookController(IRegisterNotebookDetailService iRegisterNotebookDetailService, IClassroomService iClassroomService, ITeacherService iTeacherService) {
        this.iRegisterNotebookDetailService = iRegisterNotebookDetailService;
        this.iClassroomService = iClassroomService;
        this.iTeacherService = iTeacherService;
    }

    @CheckTeacherRole
    @GetMapping("/showRegisterNotebook")
    public String getRegisterNotebook(Model model, @RequestParam("classId") int classId){
        String email = iTeacherService.getEmailTeacher();
        Teacher teacher = iTeacherService.findTeacherByAccountEmail(email);

        Classroom classroom = iClassroomService.findClassroomById(classId);
        List<RegisterNotebookDetail> registerNotebookDetails = iRegisterNotebookDetailService.findRegisterNotebookDetailByTeacher(teacher,classroom);
        model.addAttribute("registerNotebookDetails",registerNotebookDetails);

        model.addAttribute("classroom",classroom);

        String className = classroom.getClassName();
        String teacherEmail = iTeacherService.getEmailTeacher();
        Log.info("Xem sổ đầu bài, tên lớp: "+className+" , người sử dụng: "+teacherEmail);
        return "/registerNotebook/getRegisterNotebook";
    }
}
