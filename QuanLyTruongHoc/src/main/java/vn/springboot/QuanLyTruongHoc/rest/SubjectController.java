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
import vn.springboot.QuanLyTruongHoc.dto.SubjectDto;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.Subject;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;
import vn.springboot.QuanLyTruongHoc.service.inter.IClassroomService;
import vn.springboot.QuanLyTruongHoc.service.inter.ISubjectService;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;
import vn.springboot.QuanLyTruongHoc.utils.Log;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/api/subject")
public class SubjectController {
    private final ISubjectService iSubjectService;
    private final ITeacherService iTeacherService;
    private final IClassroomService iClassroomService;

    @Autowired
    public SubjectController(ISubjectService iSubjectService, ITeacherService iTeacherService, IClassroomService iClassroomService) {
        this.iSubjectService = iSubjectService;
        this.iTeacherService = iTeacherService;
        this.iClassroomService = iClassroomService;
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @GetMapping("/showAllSubjectByPrincipal")
    public String showAllSubjectByPrincipal(Model model) {
        List<Subject> subjects = iSubjectService.showAllSubject();
        model.addAttribute("subjects", subjects);

        Log.info("Xem danh sách môn học cả trường"+", người sử dụng: "+iTeacherService.getEmailTeacher());
        return "/subject/allSubjectByPrincipal";
    }

    @CheckTeacherRole
    @GetMapping("/showAllSubject")
    public String showAllSubject(Model model, @RequestParam("classId") int classId){
        Classroom classroom = iClassroomService.findClassroomById(classId);

        model.addAttribute("classroom", classroom);
        model.addAttribute("classId", classId);
        List<Subject> subjects = classroom.getSubjects();
        subjects.sort(Comparator.comparing(Subject::getSubId));
        model.addAttribute("subjects", subjects);

        Log.info("Xem danh sách môn học,tên lớp: "+classroom.getClassName()+", người sử dụng: "+iTeacherService.getEmailTeacher());
        return "/subject/allSubject";
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @GetMapping("/create")
    public String createSubject(Model model)  {
        SubjectDto subjectDto = new SubjectDto();
        List<Teacher> teachers = iTeacherService.findTeacherByTeachRole();
        model.addAttribute("teachers", teachers);
        model.addAttribute("subjectDto", subjectDto);

        Log.info("Thêm môn học"+", người sử dụng: "+iTeacherService.getEmailTeacher());
        return "/subject/createSubject";
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @PostMapping("/create")
    public String saveSubject(Model model, @Valid @ModelAttribute SubjectDto subjectDto, BindingResult result)  {
        List<Teacher> teachers = iTeacherService.findTeacherByTeachRole();
        model.addAttribute("teachers", teachers);

        if (iSubjectService.findSubjectByName(subjectDto.getName()) != null) {
            result.addError(new FieldError("subjectDto", "name", "Tên môn học đã tồn tại"));
        }

        if (result.hasErrors()) {
            return "subject/createSubject";
        }

        iSubjectService.saveSubject(subjectDto, new Subject());
        Log.info("Đã thêm môn học thành công, môn: "+subjectDto.getName()+", người sử dụng: "+iTeacherService.getEmailTeacher());
        return "redirect:/api/subject/showAllSubjectByPrincipal";
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @GetMapping("/edit")
    public String editSubject(Model model, @RequestParam("subId") int subId) {
        Subject subject = iSubjectService.findSubjectById(subId);

        model.addAttribute("subject", subject);

        SubjectDto subjectDto = iSubjectService.getSubjectDto(subject);

        List<Teacher> teachers = iTeacherService.findTeacherByTeachRole();
        model.addAttribute("teachers", teachers);
        model.addAttribute("subjectDto", subjectDto);

        Log.info("Chỉnh sửa môn học,mã môn: "+subject.getSubId()+", người sử dụng: "+iTeacherService.getEmailTeacher());
        return "/subject/editSubject";
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @PostMapping("/edit")
    public String saveEditSubject(Model model, @Valid @ModelAttribute SubjectDto subjectDto, BindingResult result, @RequestParam("subId") int subId) {
        Subject subject = iSubjectService.findSubjectById(subId);

        List<Teacher> teachers = iTeacherService.findTeacherByTeachRole();
        model.addAttribute("teachers", teachers);
        model.addAttribute("subject", subject);

        if (iSubjectService.findSubjectByName(subjectDto.getName()) != null && !subjectDto.getName().equals(subject.getSubName())) {
            result.addError(new FieldError("subjectDto", "name", "Tên môn học đã tồn tại"));
        }

        if (result.hasErrors()) {
            return "/subject/editSubject";
        }

        iSubjectService.saveSubject(subjectDto, subject);
        Log.info("Chỉnh sửa môn học thành công,mã môn: "+subject.getSubId()+", người sử dụng: "+iTeacherService.getEmailTeacher());
        return "redirect:/api/subject/showAllSubjectByPrincipal";
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @GetMapping("/delete")
    public String deleteTeacher(@RequestParam("subId") int subId) {
        Subject subject = iSubjectService.findSubjectById(subId);

        iSubjectService.delete(subject);
        Log.info("Đã xóa môn học thành công, tên môn học: "+subject.getSubName()+" ,người sử dụng: "+iTeacherService.getEmailTeacher());
        return "redirect:/api/subject/showAllSubjectByPrincipal";
    }

}
