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
import vn.springboot.QuanLyTruongHoc.dto.AcademicTranscriptDetailDto;
import vn.springboot.QuanLyTruongHoc.entity.AcademicTranscriptDetail;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.Student;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;
import vn.springboot.QuanLyTruongHoc.service.inter.IAcademicTranscriptDetailService;
import vn.springboot.QuanLyTruongHoc.service.inter.IStudentService;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;
import vn.springboot.QuanLyTruongHoc.utils.Log;


@Controller
@RequestMapping("/api/academicTranscriptDetail")
public class AcademicTranscriptDetailController {
    private final IAcademicTranscriptDetailService iAcademicTranscriptDetailService;
    private final IStudentService iStudentService;
    private final ITeacherService iTeacherService;

    @Autowired
    public AcademicTranscriptDetailController(IAcademicTranscriptDetailService iAcademicTranscriptDetailService, IStudentService iStudentService, ITeacherService iTeacherService) {
        this.iAcademicTranscriptDetailService = iAcademicTranscriptDetailService;
        this.iStudentService = iStudentService;
        this.iTeacherService = iTeacherService;
    }

    @CheckTeacherRole
    @GetMapping("/create")
    public String createAcademicTranscriptDetail(Model model, @RequestParam("idStudent") String studentId) {
        Student student = iStudentService.findStudentById(studentId);
        model.addAttribute("student", student);

        Classroom classroom = student.getClassroom();
        Teacher teacher = classroom.getTeacher();
        model.addAttribute("teacher",teacher);

        AcademicTranscriptDetailDto academicTranscriptDetailDto = new AcademicTranscriptDetailDto();
        model.addAttribute("academicTranscriptDetailDto", academicTranscriptDetailDto);

        Log.info("Bắt đầu thêm sổ học bạ, mã học sinh: " + studentId+", người sử dụng: "+iTeacherService.getEmailTeacher());

        return "/academicTranscriptDetail/createAcademicTranscriptDetail";
    }

    @CheckTeacherRole
    @PostMapping("/create")
    public String saveAcademicTranscriptDetail(@Valid @ModelAttribute AcademicTranscriptDetailDto academicTranscriptDetailDto, BindingResult result, Model model, @RequestParam("idStudent") String studentId, RedirectAttributes redirectAttributes) {
        Student student = iStudentService.findStudentById(studentId);
        model.addAttribute("student", student);

        Classroom classroom = student.getClassroom();
        Teacher teacher = classroom.getTeacher();
        model.addAttribute("teacher",teacher);

        String email = iTeacherService.getEmailTeacher();
        Teacher teacherUser = iTeacherService.findTeacherByAccountEmail(email);

        if(teacher!=teacherUser){
            result.addError(new FieldError("academicTranscriptDetailDto","teacherName","Chỉ giáo viên chủ nhiệm mới được thêm sổ học bạ!"));
        }else if(iAcademicTranscriptDetailService.findAcademicTranscriptDetailByStudentAndSemesterYear(student,academicTranscriptDetailDto.getSemesterYear())!=null){
            result.addError(new FieldError("academicTranscriptDetailDto","semesterYear","Đã tồn tại sổ học bạ!"));
        }
        if (result.hasErrors()) {
            return "/academicTranscriptDetail/createAcademicTranscriptDetail";
        }

        AcademicTranscriptDetail academicTranscriptDetail = iAcademicTranscriptDetailService.getAcademicTranscriptDetail(new AcademicTranscriptDetail(), academicTranscriptDetailDto, student);
        iAcademicTranscriptDetailService.save(academicTranscriptDetail);
        redirectAttributes.addAttribute("studentId", studentId);

        Log.info("Đã thêm sổ học bạ thành công, mã học sinh: " + studentId+" , người sử dụng: "+iTeacherService.getEmailTeacher());
        return "redirect:/api/academicTranscript/showAcademicTranscript";
    }

    @CheckTeacherRole
    @GetMapping("/edit")
    public String editAcademicTranscriptDetail(Model model, @RequestParam("academicTranscriptDetailId") int academicTranscriptDetailId)  {
        AcademicTranscriptDetail academicTranscriptDetail = iAcademicTranscriptDetailService.findAcademicTranscriptDetailById(academicTranscriptDetailId);

        Student student = academicTranscriptDetail.getStudent();
        Teacher teacher = student.getClassroom().getTeacher();
        model.addAttribute("teacher",teacher);

        AcademicTranscriptDetailDto academicTranscriptDetailDto = iAcademicTranscriptDetailService.getAcademicTranscriptDetailDto(academicTranscriptDetail);
        model.addAttribute("academicTranscriptDetailDto", academicTranscriptDetailDto);

        Log.info("Bắt đầu chỉnh sửa sổ học bạ, mã học sinh: " + student.getStudentId()+" , người sử dụng: "+iTeacherService.getEmailTeacher());
        return "/academicTranscriptDetail/editAcademicTranscriptDetail";
    }

    @CheckTeacherRole
    @PostMapping("/edit")
    public String saveEditAcademicTranscriptDetail(@Valid @ModelAttribute AcademicTranscriptDetailDto academicTranscriptDetailDto, BindingResult result, Model model, @RequestParam("academicTranscriptDetailId") int academicTranscriptDetailId, RedirectAttributes redirectAttributes) {
        AcademicTranscriptDetail academicTranscriptDetail = iAcademicTranscriptDetailService.findAcademicTranscriptDetailById(academicTranscriptDetailId);
        model.addAttribute("academicTranscriptDetail", academicTranscriptDetail);

        Student student = academicTranscriptDetail.getStudent();
        Teacher teacher = student.getClassroom().getTeacher();
        model.addAttribute("teacher",teacher);
        String email = iTeacherService.getEmailTeacher();
        Teacher teacherUser = iTeacherService.findTeacherByAccountEmail(email);

        if(teacher!=teacherUser){
            result.addError(new FieldError("academicTranscriptDetailDto","teacherName","Chỉ giáo viên chủ nhiệm mới được sửa sổ học bạ!"));
        }

        if (result.hasErrors()) {
            return "/academicTranscriptDetail/editAcademicTranscriptDetail";
        }

        iAcademicTranscriptDetailService.getAcademicTranscriptDetail(academicTranscriptDetail, academicTranscriptDetailDto, student);
        iAcademicTranscriptDetailService.save(academicTranscriptDetail);
        redirectAttributes.addAttribute("studentId", student.getStudentId());

        Log.info("Chỉnh sửa thành công, mã học sinh: " + student.getStudentId()+" , người sử dụng: "+iTeacherService.getEmailTeacher());
        return "redirect:/api/academicTranscript/showAcademicTranscript";
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @GetMapping("/delete")
    public String delete(@RequestParam("academicTranscriptDetailId") int academicTranscriptDetailId, RedirectAttributes redirectAttributes) throws Exception {
        AcademicTranscriptDetail academicTranscriptDetail = iAcademicTranscriptDetailService.findAcademicTranscriptDetailById(academicTranscriptDetailId);
        iAcademicTranscriptDetailService.delete(academicTranscriptDetail);

        Student student = academicTranscriptDetail.getStudent();
        redirectAttributes.addAttribute("studentId", student.getStudentId());

        Log.info("Xóa thành công sổ học bạ, mã học sinh: "+student.getStudentId()+" , người sử dụng: "+iTeacherService.getEmailTeacher());
        return "redirect:/api/academicTranscript/showAcademicTranscript";
    }

}
