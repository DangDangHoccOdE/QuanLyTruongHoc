package vn.springboot.QuanLyTruongHoc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.springboot.QuanLyTruongHoc.entity.Parent;
import vn.springboot.QuanLyTruongHoc.entity.Student;
import vn.springboot.QuanLyTruongHoc.service.inter.IParentService;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;
import vn.springboot.QuanLyTruongHoc.utils.Log;

@Controller
@RequestMapping("/api/parent")
public class ParentController {
    private final IParentService iParentService;
    private final ITeacherService iTeacherService;

    @Autowired
    public ParentController(IParentService iParentService, ITeacherService iTeacherService) {
        this.iParentService = iParentService;
        this.iTeacherService = iTeacherService;
    }

    @GetMapping("/showParent")
    public String showParent(Model model,@RequestParam("studentId") String studentId) {
        Parent parent = iParentService.findParentByStudentId(studentId);
        model.addAttribute("parent", parent);

        Student student = parent.getStudent();
        model.addAttribute("student", student);
        Log.info("Xem thông tin phụ huynh, mã học sinh: "+studentId+" , ngưởi sử dụng: "+iTeacherService.getEmailTeacher());
        return "/parent/showParent";
    }


}
