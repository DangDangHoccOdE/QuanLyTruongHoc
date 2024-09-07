package vn.springboot.QuanLyTruongHoc.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.springboot.QuanLyTruongHoc.annotation.CheckTeacherRole;
import vn.springboot.QuanLyTruongHoc.dto.RegisterNotebookDetailDto;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.RegisterNotebookDetail;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;
import vn.springboot.QuanLyTruongHoc.service.inter.IClassroomService;
import vn.springboot.QuanLyTruongHoc.service.inter.IRegisterNotebookDetailService;
import vn.springboot.QuanLyTruongHoc.service.inter.ISubjectService;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;
import vn.springboot.QuanLyTruongHoc.utils.Log;


@Controller
@RequestMapping("/api/registerNotebookDetail")
public class RegisterNotebookDetailController {
    private final IRegisterNotebookDetailService iRegisterNotebookDetailService;
    private final IClassroomService iClassroomService;
    private final ITeacherService iTeacherService;
    private final ISubjectService iSubjectService;

    @Autowired
    public RegisterNotebookDetailController(IRegisterNotebookDetailService iRegisterNotebookDetailService, IClassroomService iClassroomService, ITeacherService iTeacherService, ISubjectService iSubjectService) {
        this.iRegisterNotebookDetailService = iRegisterNotebookDetailService;
        this.iClassroomService = iClassroomService;
        this.iTeacherService = iTeacherService;
        this.iSubjectService = iSubjectService;
    }

    @CheckTeacherRole
    @GetMapping("/create")
    public String create(Model model, @RequestParam("classId") int classId) {
        RegisterNotebookDetailDto registerNotebookDetailDto = new RegisterNotebookDetailDto();
        model.addAttribute("registerNotebookDetailDto", registerNotebookDetailDto);

        Classroom classroom = iClassroomService.findClassroomById(classId);
        model.addAttribute("classroom", classroom);

        String teacherEmail = iTeacherService.getEmailTeacher();
        Teacher teacher = iTeacherService.findTeacherByAccountEmail(teacherEmail);
        model.addAttribute("teacher", teacher);

        String className = classroom.getClassName();
        Log.info("Bắt đầu thêm sổ đầu bài, tên lớp: " + className + " ,người sử dụng: " + teacherEmail);
        return "/registerNotebookDetail/createRegisterNotebookDetail";
    }

    @CheckTeacherRole
    @PostMapping("/create")
    public String saveLecture(@RequestParam("classId") int classId, Model model, @Valid @ModelAttribute RegisterNotebookDetailDto registerNotebookDetailDto, BindingResult result, RedirectAttributes redirectAttributes) {
        Classroom classroom = iClassroomService.findClassroomById(classId);
        model.addAttribute("classroom", classroom);

        String teacherEmail = iTeacherService.getEmailTeacher();
        Teacher teacher = iTeacherService.findTeacherByAccountEmail(teacherEmail);
        model.addAttribute("teacher", teacher);

        if(iSubjectService.getSubjectByRoleToMark(classroom,teacher).isEmpty()){
            result.addError(new FieldError("registerNotebookDetailDto","teacherId","Bạn không thuộc giáo viên dạy môn học của lớp"));
        }
        if (result.hasErrors()) {
            return "registerNotebookDetail/createRegisterNotebookDetail";
        }

        iRegisterNotebookDetailService.saveRegisterNotebookDetail(registerNotebookDetailDto, new RegisterNotebookDetail(), classId, teacher);
        redirectAttributes.addAttribute("classId", classId);

        String className = classroom.getClassName();
        Log.info("Đã thêm sổ đầu bài, tên lớp: " + className + " , người sử dụng: " + teacherEmail);
        return "redirect:/api/registerNotebook/showRegisterNotebook";
    }

    @CheckTeacherRole
    @GetMapping("/edit")
    public String editRegisterNotebookDetail(Model model, @RequestParam("registerNotebookDetailId") int registerNotebookDetailId)  {
        String teacherEmail = iTeacherService.getEmailTeacher();
        Teacher teacher = iTeacherService.findTeacherByAccountEmail(teacherEmail);
        model.addAttribute("teacher", teacher);

        RegisterNotebookDetail registerNotebookDetail = iRegisterNotebookDetailService.findRegisterNotebookDetailById(registerNotebookDetailId);
        model.addAttribute("registerNotebookDetail", registerNotebookDetail);

        Classroom classroom = registerNotebookDetail.getClassroom();
        model.addAttribute("classroom",classroom);

        RegisterNotebookDetailDto registerNotebookDetailDto = iRegisterNotebookDetailService.getRegisterNotebookDetailDto(registerNotebookDetail);
        model.addAttribute("registerNotebookDetailDto", registerNotebookDetailDto);

        String className = registerNotebookDetail.getClassroom().getClassName();
        Log.info("Bắt đầu sửa sổ đầu bài, tên lớp: " + className + " , người sử dụng: " + teacherEmail);
        return "/registerNotebookDetail/editRegisterNotebookDetail";
    }

    @CheckTeacherRole
    @PostMapping("/edit")
    public String saveEditRegisterNotebookDetail(Model model, @Valid @ModelAttribute RegisterNotebookDetailDto registerNotebookDetailDto, BindingResult result, @RequestParam("registerNotebookDetailId") int registerNotebookDetailId, RedirectAttributes redirectAttributes) {
        String teacherEmail = iTeacherService.getEmailTeacher();
        Teacher teacher = iTeacherService.findTeacherByAccountEmail(teacherEmail);
        model.addAttribute("teacher", teacher);

        RegisterNotebookDetail registerNotebookDetail = iRegisterNotebookDetailService.findRegisterNotebookDetailById(registerNotebookDetailId);
        model.addAttribute("registerNotebookDetail", registerNotebookDetail);

        Classroom classroom = registerNotebookDetail.getClassroom();
        model.addAttribute("classroom",classroom);
        if(iSubjectService.getSubjectByRoleToMark(classroom,teacher).isEmpty()){
            result.addError(new FieldError("registerNotebookDetailDto","teacherId","Bạn không thuộc giáo viên dạy môn học của lớp"));
        }

        if (result.hasErrors()) {
            return "registerNotebookDetail/editRegisterNotebookDetail";
        }

        iRegisterNotebookDetailService.saveRegisterNotebookDetail(registerNotebookDetailDto, registerNotebookDetail, registerNotebookDetail.getClassroom().getId(), teacher);
        redirectAttributes.addAttribute("classId", (registerNotebookDetail.getClassroom().getId()));

        String className = registerNotebookDetail.getClassroom().getClassName();
        Log.info("Đã sửa sổ đầu bài, tên lớp: " + className + " , người sử dụng: " + teacherEmail);
        return "redirect:/api/registerNotebook/showRegisterNotebook";
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @GetMapping("/delete")
    public String delete(@RequestParam("registerNotebookDetailId") int registerNotebookDetailId, RedirectAttributes redirectAttributes)  {
        RegisterNotebookDetail registerNotebookDetail = iRegisterNotebookDetailService.findRegisterNotebookDetailById(registerNotebookDetailId);

        iRegisterNotebookDetailService.delete(registerNotebookDetail);
        Classroom classroom = registerNotebookDetail.getClassroom();
        redirectAttributes.addAttribute("classId", classroom.getId());

        String teacherEmail = iTeacherService.getEmailTeacher();

        String className = classroom.getClassName();
        Log.info("Đã xóa sổ đầu bài thành công, mã sổ học bạ: " + registerNotebookDetailId + " ,tên lớp: " + className
                + " ,người sử dụng: " + teacherEmail);
        return "redirect:/api/registerNotebook/showRegisterNotebook";
    }
}
