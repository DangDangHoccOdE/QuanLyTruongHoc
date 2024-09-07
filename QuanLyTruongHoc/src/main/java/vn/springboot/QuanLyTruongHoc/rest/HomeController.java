package vn.springboot.QuanLyTruongHoc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.springboot.QuanLyTruongHoc.entity.Student;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;
import vn.springboot.QuanLyTruongHoc.service.inter.IStudentService;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;
import vn.springboot.QuanLyTruongHoc.utils.Log;
import java.security.Principal;

@Controller
public class HomeController {
    private final ITeacherService iTeacherService;
    private final IStudentService iStudentService;

    @Autowired
    public HomeController(ITeacherService iTeacherService, IStudentService iStudentService) {
        this.iTeacherService = iTeacherService;
        this.iStudentService = iStudentService;
    }

    @GetMapping({"","/"})
    public String getHome(Model model,Principal principal) {
        Teacher teacher = iTeacherService.findTeacherByAccountEmail(principal.getName());
        Student student = iStudentService.findStudentByAccountEmail(principal.getName());

        String id;
        if(teacher!=null){
            id = teacher.getTeacherId();
        }else{
            id = student.getStudentId();
        }

        model.addAttribute("semesterYear","Năm 2024 học Kỳ 2");
        model.addAttribute("id",id);
        Log.info("Bắt đầu vào trang chủ: "+principal.getName());

        return teacher!=null?"/home/teacherHome":"/home/studentHome";
    }
}
