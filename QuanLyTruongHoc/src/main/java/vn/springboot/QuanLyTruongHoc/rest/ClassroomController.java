package vn.springboot.QuanLyTruongHoc.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import vn.springboot.QuanLyTruongHoc.annotation.CheckTeacherRole;
import vn.springboot.QuanLyTruongHoc.dto.ClassroomDto;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.RegisterNotebook;
import vn.springboot.QuanLyTruongHoc.entity.Subject;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;
import vn.springboot.QuanLyTruongHoc.service.inter.IClassroomService;
import vn.springboot.QuanLyTruongHoc.service.inter.IRegisterNotebookService;
import vn.springboot.QuanLyTruongHoc.service.inter.ISubjectService;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;
import vn.springboot.QuanLyTruongHoc.utils.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/api/classroom")
public class ClassroomController {
    private final IClassroomService iClassroomService;
    private final ITeacherService iTeacherService;
    private final ISubjectService iSubjectService;
    private final IRegisterNotebookService iRegisterNotebookService;
    private final SecurityUtils securityUtils;
    private final CheckAccessClassroom checkAccessClassroom;

    @Autowired
    public ClassroomController(SecurityUtils securityUtils, IClassroomService iClassroomService, ITeacherService iTeacherService, ISubjectService iSubjectService, IRegisterNotebookService iRegisterNotebookService, CheckAccessClassroom checkAccessClassroom) {
        this.iClassroomService = iClassroomService;
        this.iTeacherService = iTeacherService;
        this.iSubjectService = iSubjectService;
        this.iRegisterNotebookService = iRegisterNotebookService;
        this.securityUtils = securityUtils;
        this.checkAccessClassroom = checkAccessClassroom;
    }

    @CheckTeacherRole
    @GetMapping("/showAllClassroom")
    public String showAllClassroom(Model model) {
        String teacherEMail = iTeacherService.getEmailTeacher();
        List<Classroom> classrooms;

        if (securityUtils.hasPrincipal()) {
            classrooms = iClassroomService.showAllClassroom();
        } else {
            Teacher teacher = iTeacherService.findTeacherByAccountEmail(teacherEMail);
            classrooms = checkAccessClassroom.getClassroomsByTeacherAccess(teacher);
        }

        classrooms.sort(Comparator.comparing(Classroom::getId));
        model.addAttribute("classrooms", classrooms);

        Log.info("Xem danh sách lớp học, người sử dụng: " + teacherEMail);
        return "/classroom/allClassroom";
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @GetMapping("/create")
    public String createClassroom(Model model)  {
        Log.info("Bắt đầu thêm lớp, người sử dụng: "+iTeacherService.getEmailTeacher());
        ClassroomDto classroomDto = new ClassroomDto();
        model.addAttribute("classroomDto", classroomDto);

        List<Teacher> teachers = iClassroomService.getAllHomeRoomTeacher();
        model.addAttribute("teachers", teachers);

        List<Subject> subjects = iSubjectService.showAllSubject();
        model.addAttribute("subjects", subjects);

        return "/classroom/createClassroom";
    }

      @PreAuthorize("hasRole('PRINCIPAL')")
    @PostMapping("/create")
    public String getClassroom(Model model, @Valid @ModelAttribute ClassroomDto classroomDto, BindingResult result)  {
        List<Teacher> teachers = iClassroomService.getAllHomeRoomTeacher();
        model.addAttribute("teachers", teachers);

        List<Subject> subjects = iSubjectService.showAllSubject();
        model.addAttribute("subjects", subjects);

        if (iClassroomService.findClassroomByName(classroomDto.getClassName()) != null) {
            result.addError(new FieldError("ClassroomDto", "className", "Tên lớp đã tồn tại"));
        }

        if (result.hasErrors()) {
            return "classroom/createClassroom";
        }

        Classroom saveClassroom = iClassroomService.getClassroom(classroomDto, new Classroom());

        RegisterNotebook registerNotebook = new RegisterNotebook();
        saveClassroom.setRegisterNotebook(registerNotebook);
        registerNotebook.setClassroom(saveClassroom);
        iRegisterNotebookService.save(registerNotebook);
        iClassroomService.saveClassroom(saveClassroom);

        Log.info("Đã thêm lớp thành công, tên lớp: " + classroomDto.getClassName()+" , người sử dụng: "+iTeacherService.getEmailTeacher());
        return "redirect:/api/classroom/showAllClassroom";
    }

      @PreAuthorize("hasRole('PRINCIPAL')")
    @GetMapping("/edit")
    public String editClassroom(Model model, @RequestParam("classId") int classId){
        Classroom classroom = iClassroomService.findClassroomById(classId);
        model.addAttribute("classroom", classroom);

        List<Teacher> teachers = iClassroomService.getAllHomeRoomTeacher();
        model.addAttribute("teachers", teachers);

        List<Subject> subjects = iSubjectService.showAllSubject();
        model.addAttribute("subjects", subjects);

        ClassroomDto classroomDto = iClassroomService.findClassroomDto(classroom);
        model.addAttribute("classroomDto", classroomDto);

        Log.info("Bắt đầu chỉnh sửa thông tin lớp học, tên lớp: " + classroomDto.getClassName()+" , người sử dụng: "+iTeacherService.getEmailTeacher());
        return "/classroom/editClassroom";
    }

      @PreAuthorize("hasRole('PRINCIPAL')")
    @PostMapping("/edit")
    public String saveEditClassroom(@Valid @ModelAttribute ClassroomDto classroomDto, BindingResult result, @RequestParam("classId") int classId, Model model) {
        List<Teacher> teachers = iClassroomService.getAllHomeRoomTeacher();
        model.addAttribute("teachers", teachers);

        List<Subject> subjects = iSubjectService.showAllSubject();
        model.addAttribute("subjects", subjects);

        Classroom classroom = iClassroomService.findClassroomById(classId);
        model.addAttribute("classroom", classroom);

        if (classroomDto.getSubjectsId().isEmpty()) {
            result.addError(new FieldError("classroomDto", "subjectsId", "Phải chọn môn học !"));
        }

        if (iClassroomService.findClassroomByName(classroomDto.getClassName()) != null && !classroomDto.getClassName().equals(classroom.getClassName())) {
            result.addError(new FieldError("classroomDto", "className", "Tên lớp đã tồn tại !"));
        }

        if (result.hasErrors()) {
            return "classroom/createClassroom";
        }

        Classroom saveClassroom = iClassroomService.getClassroom(classroomDto, classroom);
        iClassroomService.saveClassroom(saveClassroom);

        Log.info("Đã chỉnh sửa thông tin lớp xong, tên lớp: "+classroomDto.getClassName()+" ,  người sử dụng: "+iTeacherService.getEmailTeacher());
        return "redirect:/api/classroom/showAllClassroom";
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @GetMapping("/delete")
    public String deleteClassroom(@RequestParam("classId") int classId)  {
        Classroom classroom = iClassroomService.findClassroomById(classId);
        try {
            iClassroomService.deleteClassroom(classroom);
        }catch (Exception e){
            Log.info("Không thể xóa được lớp học do có liên quan đến dữ liệu khác trong hệ thống, lớp muốn xóa: "+classroom.getClassName()+" , người sử dụng: "+iTeacherService.getEmailTeacher());
            return "/error/TemplateInputException";
        }
        Log.info("Đã xóa thành công lớp: "+classroom.getClassName()+" , người sử dụng: "+iTeacherService.getEmailTeacher());
        return "redirect:/api/classroom/showAllClassroom";
    }

}
