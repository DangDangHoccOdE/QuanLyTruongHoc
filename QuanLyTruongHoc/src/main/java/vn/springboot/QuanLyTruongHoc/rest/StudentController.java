package vn.springboot.QuanLyTruongHoc.rest;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.springboot.QuanLyTruongHoc.annotation.CheckTeacherRole;
import vn.springboot.QuanLyTruongHoc.dto.AccountDto;
import vn.springboot.QuanLyTruongHoc.dto.ParentDto;
import vn.springboot.QuanLyTruongHoc.dto.StudentDto;
import vn.springboot.QuanLyTruongHoc.entity.*;
import vn.springboot.QuanLyTruongHoc.service.inter.*;
import vn.springboot.QuanLyTruongHoc.utils.Log;
import vn.springboot.QuanLyTruongHoc.utils.SecurityUtils;

import java.util.List;

@Controller
@RequestMapping("/api/student")
public class StudentController {
    private final IStudentService iStudentService;
    private final IClassroomService iClassroomService;
    private final IParentService iParentService;
    private final ITeacherService iTeacherService;
    private final IAccountService iAccountService;
    private final SecurityUtils securityUtils;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StudentController(IStudentService iStudentService, IClassroomService iClassroomService, IParentService iParentService, ITeacherService iTeacherService, IAccountService iAccountService, SecurityUtils securityUtils, PasswordEncoder passwordEncoder) {
        this.iStudentService = iStudentService;
        this.iClassroomService = iClassroomService;
        this.iParentService = iParentService;
        this.iTeacherService = iTeacherService;
        this.iAccountService = iAccountService;
        this.securityUtils = securityUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @CheckTeacherRole
    @GetMapping("/showAllStudent")
    public String getAllStudent(Model model, @RequestParam("classId") int classId) {
        Classroom classroom = iClassroomService.findClassroomById(classId);

        model.addAttribute("classroom", classroom);
        List<Student> students = classroom.getStudents();
        model.addAttribute("students", students);

        Log.info("Xem danh sách học sinh, lớp: "+classroom.getClassName()+", người sử dụng: "+iTeacherService.getEmailTeacher());
        return "/student/allStudent";
    }

    @CheckTeacherRole
    @GetMapping("/getStudentByName")
    public String getStudentByName(Model model,@RequestParam("studentName") String studentName,@RequestParam("classId") int classId){
        List<Student> students = iStudentService.findStudentByStudentNameAndClassroomId(studentName,classId);
        model.addAttribute("students",students);

        Classroom classroom = iClassroomService.findClassroomById(classId);
        model.addAttribute("classroom",classroom);

        Log.info("Tìm kiếm học sinh theo tên: "+studentName+" ,lớp: "+classroom.getClassName());

        return "/student/allStudent";
    }

    @CheckTeacherRole
    @GetMapping("/getStudentByGender")
    public String getStudentByGender(Model model,@RequestParam("selectGender") String selectGender,@RequestParam("classId") int classId){
        List<Student> students = iStudentService.findStudentByGenderAndClassroomId(selectGender,classId);
        model.addAttribute("students",students);

        Classroom classroom = iClassroomService.findClassroomById(classId);
        model.addAttribute("classroom",classroom);
        model.addAttribute("selectGender",selectGender);

        Log.info("Tìm kiếm học sinh theo giới tính: "+selectGender+" ,lớp: "+classroom.getClassName());

        return "/student/allStudent";
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @GetMapping("/create")
    public String createStudent(Model model, @RequestParam("classId") int classId) {
        Classroom classroom = iClassroomService.findClassroomById(classId);
        model.addAttribute("classroom", classroom);

        StudentDto studentDto = new StudentDto();
        model.addAttribute("studentDto", studentDto);
        ParentDto parentDto = new ParentDto();
        model.addAttribute("parentDto", parentDto);

        Log.info("Thêm học sinh, lớp: "+classroom.getClassName()+", người sử dụng: "+iTeacherService.getEmailTeacher());
        return "/student/createStudent";
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @PostMapping("/create")
    public String saveStudent(Model model, @RequestParam("classId") int classId, @Valid @ModelAttribute StudentDto studentDto, BindingResult bindingResult, @Valid @ModelAttribute ParentDto parentDto, BindingResult result, RedirectAttributes redirectAttributes) {
        Classroom classroom = iClassroomService.findClassroomById(classId);
        model.addAttribute("classroom", classroom);

        if (iStudentService.findStudentById(studentDto.getStudentId()) != null || iTeacherService.findTeacherById(studentDto.getStudentId())!=null) {
            bindingResult.addError(new FieldError("studentDto", "studentId", "Mã học sinh đã tồn tại !"));
        }

        if(iAccountService.findAccountByEmail(studentDto.getStudentEmail())!=null){
            bindingResult.addError(new FieldError("studentDto", "studentEmail", "Email đã tồn tại !"));
        }

        if (result.hasErrors() || bindingResult.hasErrors()) {
            return "student/createStudent";
        }

        AcademicTranscript academicTranscript = new AcademicTranscript();
        Student student = iStudentService.getStudent(new Student(),studentDto, parentDto, classroom);
        student.setAcademicTranscript(academicTranscript);
        academicTranscript.setStudent(student);
        iStudentService.saveStudent(student);

        redirectAttributes.addAttribute("classId", classId);
        Log.info("Thêm học sinh thành công, lớp: "+classroom.getClassName()+" ,mã học sinh: "+student.getStudentId()+" ,tên: "+student.getStudentName()+", người sử dụng: "+iTeacherService.getEmailTeacher());
        return "redirect:/api/student/showAllStudent";
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @GetMapping("/edit")
    public String changeInfoStudent(Model model, @RequestParam("studentId") String studentId)  {
        Student student = iStudentService.findStudentById(studentId);

        model.addAttribute("student", student);
        model.addAttribute("parentDto", iParentService.getParentDto(student));
        model.addAttribute("studentDto", iStudentService.getStudentDto(student));
        Log.info("Chỉnh sửa học sinh, mã học sinh: "+student.getStudentId()+", người sử dụng: "+iTeacherService.getEmailTeacher());
        return "/student/editStudent";
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @PostMapping("/edit")
    public String saveChangeStudent(Model model, @RequestParam("studentId") String studentId, @Valid @ModelAttribute StudentDto studentDto, BindingResult bindingResult, @Valid @ModelAttribute ParentDto parentDto, BindingResult result, RedirectAttributes redirectAttributes) {
        Student student = iStudentService.findStudentById(studentId);
        Classroom classroom = student.getClassroom();

        model.addAttribute("student", student);
        model.addAttribute("parentDto", iParentService.getParentDto(student));

        if(iAccountService.findAccountByEmail(studentDto.getStudentEmail())!=null && !student.getAccount().getEmail().equals(studentDto.getStudentEmail())){
            bindingResult.addError(new FieldError("studentDto", "studentEmail", "Email đã tồn tại !"));
        }

        if (result.hasErrors() || bindingResult.hasErrors()) {
            return "student/editStudent";
        }
        Student studentNew = iStudentService.getStudent(student,studentDto, parentDto, classroom);
        iStudentService.saveStudent(studentNew);

        redirectAttributes.addAttribute("classId", classroom.getId());
        Log.info(" Đã chỉnh sửa học sinh thành công, mã học sinh: "+student.getStudentId()+", người sử dụng: "+iTeacherService.getEmailTeacher());
        return "redirect:/api/student/showAllStudent";
    }

    @GetMapping("/infoAccount")
    public String changeInfoAccount(Model model, @RequestParam("studentId") String id) {
        Student student = iStudentService.findStudentById(id);
        model.addAttribute("student", student);
        AccountDto accountDto = new AccountDto();
        accountDto.setId(student.getStudentId());
        accountDto.setEmail(student.getAccount().getEmail());

        model.addAttribute("accountDto", accountDto);

        Log.info("Chỉnh sửa thông tin tài khoản học sinh, mã học sinh: "+student.getStudentId()+" ,tên: "+student.getStudentName()+", người dụng: "+iTeacherService.getEmailTeacher());
        return "/account/changeInfoAccount";
    }

    @PostMapping("/infoAccount")
    public String saveInfoAccount(Model model, @RequestParam("studentId") String id, @Valid @ModelAttribute AccountDto accountDto, BindingResult result)  {
        Student student = iStudentService.findStudentById(id);
            Account account = student.getAccount();

            model.addAttribute("student", student);

            if (!accountDto.getEmail().equals(account.getEmail()) && !securityUtils.hasPrincipal()) {
                result.addError(new FieldError("accountDto", "email", "Bạn không thể thay đổi tài khoản!"));
            } else if (iAccountService.findAccountByEmail(accountDto.getEmail()) != null && !accountDto.getEmail().equals(student.getAccount().getEmail())) {
                result.addError(new FieldError("accountDto", "email", "Email đã tồn tại"));
            }

            if(!accountDto.getPassword().equals(accountDto.getPasswordNew())){
              result.addError(new FieldError("accountDto", "passwordNew", "Mật khẩu mới và Nhập lại mật khẩu mới không khớp!"));
             }

             if(!passwordEncoder.matches(accountDto.getPreviousPassword(),account.getPassword())&& !accountDto.getPreviousPassword().isEmpty()){
                   result.addError(new FieldError("accountDto", "previousPassword", "Mật khẩu cũ mới không khớp!"));
              }

            if (result.hasErrors()) {
                return "/account/changeInfoAccount";
            }

            account.setEmail(accountDto.getEmail());
            account.setPassword(passwordEncoder.encode(accountDto.getPassword()));

            iAccountService.saveAccount(account);

        Log.info("Chỉnh sửa thông tin tài khoản giáo viên thành công, mã học sinh: "+student.getStudentId()+" ,tên: "+student.getStudentName()+", người dụng: "+iTeacherService.getEmailTeacher());
        return "redirect:/";
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("studentId") String studentId, RedirectAttributes redirectAttributes) {
        Student student = iStudentService.findStudentById(studentId);
        Classroom classroom = student.getClassroom();

        try {
            student.setRoles(null);
            iStudentService.deleteStudent(student);
        }catch (Exception e){
            Log.info("Không thể xóa học sinh do có liên quan dến dữ liệu hệ thống, mã học sinh: "+studentId+" , tên học sinh: "+student.getStudentName()+", ngưởi sử dụng: "+iTeacherService.getEmailTeacher() );
            return "/error/TemplateInputException";
        }
        redirectAttributes.addAttribute("classId", classroom.getId());

        Log.info("Xóa học sinh thành công, mã học sinh: "+student.getStudentId()+" , tên học sinh: "+student.getStudentName()+", người sử dụng: "+iTeacherService.getEmailTeacher());
        return "redirect:/api/student/showAllStudent";
    }

}
