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
import vn.springboot.QuanLyTruongHoc.annotation.CheckTeacherRole;
import vn.springboot.QuanLyTruongHoc.dto.AccountDto;
import vn.springboot.QuanLyTruongHoc.dto.TeacherDto;
import vn.springboot.QuanLyTruongHoc.entity.Account;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;
import vn.springboot.QuanLyTruongHoc.service.inter.IAccountService;
import vn.springboot.QuanLyTruongHoc.service.inter.IRolesService;
import vn.springboot.QuanLyTruongHoc.service.inter.IStudentService;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;
import vn.springboot.QuanLyTruongHoc.service.impl.TeacherService;
import vn.springboot.QuanLyTruongHoc.utils.Log;
import vn.springboot.QuanLyTruongHoc.utils.SecurityUtils;

import java.util.List;

@Controller
@RequestMapping("/api/teacher")
public class TeacherController {
    private final ITeacherService iTeacherService;
    private final IAccountService iAccountService;
    private final PasswordEncoder passwordEncoder;
    private final SecurityUtils securityUtils;
    private final IStudentService iStudentService;
    private final IRolesService iRolesService;

    @Autowired
    public TeacherController(TeacherService teacherService, IAccountService iAccountService, PasswordEncoder passwordEncoder, SecurityUtils securityUtils, IStudentService iStudentService, IRolesService iRolesService) {
        this.iAccountService = iAccountService;
        this.iTeacherService = teacherService;
        this.passwordEncoder = passwordEncoder;
        this.securityUtils = securityUtils;
        this.iStudentService = iStudentService;
        this.iRolesService = iRolesService;
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @GetMapping("/showAllTeacher")
    public String showAllTeacher(Model model) {
            List<Teacher> teachers = iTeacherService.showAllTeacher();
            model.addAttribute("teachers", teachers);

        Log.info("Xem danh sách giáo viên, người sử dụng: "+iTeacherService.getEmailTeacher());
        return "/teacher/allTeacher";
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @GetMapping("/getTeacherByName")
    public String getTeacherByName(Model model,@RequestParam("teacherName")String teacherName){
        List<Teacher> teachers = iTeacherService.findTeacherByTeacherName(teacherName);
        model.addAttribute("teachers",teachers);

        Log.info("Đang tìm kiếm tên giáo viên: "+teacherName+" ,người sử dụng: "+iTeacherService.getEmailTeacher());
        return "/teacher/allTeacher";
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @GetMapping("/create")
    public String createTeacher(Model model)  {
            TeacherDto teacherDto = new TeacherDto();
            model.addAttribute("teacherDto", teacherDto);

        Log.info("Thêm giáo viên, người sử dụng: "+iTeacherService.getEmailTeacher());
        return "/teacher/createTeacher";
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @PostMapping("/create")
    public String getTeacher(@Valid @ModelAttribute TeacherDto teacherDto, BindingResult result){
        List<String> listRoles = teacherDto.getRolesName();
        for(String s : listRoles){
            if(s.equals("ROLE_PRINCIPAL")){
                result.addError(new FieldError("teacherDto", "rolesName", "Không thể thêm vai trò Hiệu truởng!"));
                break;
            }
        }

        if (iTeacherService.findTeacherById(teacherDto.getId()) != null || iStudentService.findStudentById(teacherDto.getId())!=null) {
            result.addError(new FieldError("teacherDto", "id", "Mã giáo viên đã tồn tại"));
        }

        if (iAccountService.findAccountByEmail(teacherDto.getEmailTeacher()) != null) {
            result.addError(new FieldError("teacherDto", "emailTeacher", "Email đã tồn tại"));
        }

        if (result.hasErrors()) {
            return "teacher/createTeacher";
        }

        Teacher newTeacher = iTeacherService.getTeacher(teacherDto, new Teacher());
        iTeacherService.saveTeacher(newTeacher);

        Log.info("Thêm giáo viên thành công, mã giáo viên: "+teacherDto.getId()+" ,tên: "+teacherDto.getName()+", người sử dụng: "+iTeacherService.getEmailTeacher());
        return "redirect:/api/teacher/showAllTeacher";
    }

    @CheckTeacherRole
    @GetMapping("/edit")
    public String editTeacher(Model model, @RequestParam("teacherId") String id)  {
        Teacher teacher = iTeacherService.findTeacherById(id);
        model.addAttribute("teacher", teacher);
        TeacherDto teacherDto = iTeacherService.getTeacherDto(teacher);
        model.addAttribute("teacherDto", teacherDto);

        Log.info("Chỉnh sửa thông tin giáo viên, mã giáo viên: "+teacherDto.getId()+" ,tên: "+teacherDto.getName()+", người sử dụng: "+iTeacherService.getEmailTeacher());
        return "teacher/editTeacher";
    }

    @CheckTeacherRole
    @PostMapping("/edit")
    public String saveEditTeacher(Model model, @Valid @ModelAttribute TeacherDto teacherDto, BindingResult result, @RequestParam("teacherId") String id)  {
        Teacher teacher = iTeacherService.findTeacherById(id);
        Account account = teacher.getAccount();

        model.addAttribute("teacher", teacher);

        List<String> listRoles = teacherDto.getRolesName();
        for(String s : listRoles){
            if(s.equals("ROLE_PRINCIPAL") && iRolesService.findRolesByName("ROLE_PRINCIPAL")!=null && !iTeacherService.checkPrincipalByTeacher(teacher)){
                result.addError(new FieldError("teacherDto", "rolesName", "Không thể thêm vai trò Hiệu truởng!"));
                break;
            }
        }

        if (iAccountService.findAccountByEmail(teacherDto.getEmailTeacher()) != null && !teacherDto.getEmailTeacher().equals(account.getEmail())) {
            result.addError(new FieldError("teacherDto", "emailTeacher", "Email đã tồn tại"));
        }else if(!teacherDto.getEmailTeacher().equals(account.getEmail()) && !securityUtils.hasPrincipal()){
            result.addError(new FieldError("teacherDto", "emailTeacher", "Bạn không thể thay đổi tài khoản!"));
        }

        if (result.hasErrors()) {
            return "/teacher/editTeacher";
        }

        Teacher newTeacher = iTeacherService.getTeacher(teacherDto, teacher);
        iTeacherService.saveTeacher(newTeacher);

        Log.info("Chỉnh sửa thông tin giáo viên thành công, mã giáo viên: "+teacherDto.getId()+" ,tên: "+teacherDto.getName()+", người sử dụng: "+iTeacherService.getEmailTeacher());
        return "redirect:/api/teacher/showAllTeacher";
    }

    @CheckTeacherRole
    @GetMapping("/infoAccount")
    public String changeInfoAccount(Model model, @RequestParam("teacherId") String id) {
        Teacher teacher = iTeacherService.findTeacherById(id);
        model.addAttribute("teacher", teacher);
        AccountDto accountDto = new AccountDto();
        accountDto.setId(teacher.getTeacherId());
        accountDto.setEmail(teacher.getAccount().getEmail());

        model.addAttribute("accountDto", accountDto);

        Log.info("Chỉnh sửa thông tin tài khoản giáo viên, mã giáo viên: "+teacher.getTeacherId()+" ,tên: "+teacher.getTeacherName()+", người dùng: "+iTeacherService.getEmailTeacher());
        return "/account/changeInfoAccount";
    }

    @CheckTeacherRole
    @PostMapping("/infoAccount")
    public String saveInfoAccount(Model model, @RequestParam("teacherId") String id, @Valid @ModelAttribute AccountDto accountDto, BindingResult result)  {
        Teacher teacher = iTeacherService.findTeacherById(id);
        Account account = teacher.getAccount();

        model.addAttribute("teacher", teacher);

        if(!accountDto.getEmail().equals(account.getEmail()) && !securityUtils.hasPrincipal()){
            result.addError(new FieldError("accountDto", "email", "Bạn không thể thay đổi tài khoản!"));
        } else  if (iAccountService.findAccountByEmail(accountDto.getEmail()) != null && !accountDto.getEmail().equals(teacher.getAccount().getEmail())) {
            result.addError(new FieldError("accountDto", "email", "Email đã tồn tại"));
        }

        if(!accountDto.getPassword().equals(accountDto.getPasswordNew())){
            result.addError(new FieldError("accountDto", "passwordNew", "Mật khẩu mới và Nhập lại mật khẩu mới không khớp!"));
        }

        if(!passwordEncoder.matches(accountDto.getPreviousPassword(),account.getPassword()) && !accountDto.getPreviousPassword().isEmpty()){
            result.addError(new FieldError("accountDto", "previousPassword", "Mật khẩu cũ mới không khớp!"));
        }

        if (result.hasErrors()) {
            return "/account/changeInfoAccount";
        }

        account.setEmail(accountDto.getEmail());
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));

        iAccountService.saveAccount(account);
        Log.info("Chỉnh sửa thông tin tài khoản giáo viên thành công, mã giáo viên: "+teacher.getTeacherId()+" ,tên: "+teacher.getTeacherName()+", người sử dụng: "+iTeacherService.getEmailTeacher());
        return "redirect:/";
    }

    @PreAuthorize("hasRole('PRINCIPAL')")
    @GetMapping("/delete")
    public String deleteTeacher(@RequestParam("teacherId") String id) {
        Teacher teacher = iTeacherService.findTeacherById(id);
        List<Classroom> classrooms = teacher.getClassRooms();
        String email = iTeacherService.getEmailTeacher();
        for (Classroom classroom : classrooms) {
            classroom.setTeacher(null);
        }

        try {
            iTeacherService.deleteTeacher(teacher);
        }catch (Exception e){
            Log.info("Không thể xóa được giáo viên do có liên quan đến dữ liệu khác trong hệ thống, giáo viên muốn xóa: "+teacher.getTeacherName()+" , người sử dụng: "+email);
            return "/error/TemplateInputException";
        }
        Log.info("Đã xóa giáo viên thành công, mã giáo viên: "+teacher.getTeacherId()+" ,tên: "+teacher.getTeacherName()+", người sử dụng: "+iTeacherService.getEmailTeacher());
        return "redirect:/api/teacher/showAllTeacher";
    }
}
