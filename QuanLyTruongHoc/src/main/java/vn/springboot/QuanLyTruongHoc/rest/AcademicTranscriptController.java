package vn.springboot.QuanLyTruongHoc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.springboot.QuanLyTruongHoc.annotation.CheckTeacherRole;
import vn.springboot.QuanLyTruongHoc.entity.AcademicTranscriptDetail;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.Student;
import vn.springboot.QuanLyTruongHoc.service.inter.IAcademicTranscriptDetailService;
import vn.springboot.QuanLyTruongHoc.service.inter.IClassroomService;
import vn.springboot.QuanLyTruongHoc.service.inter.IStudentService;
import vn.springboot.QuanLyTruongHoc.utils.Log;

import java.util.*;

@Controller
@RequestMapping("/api/academicTranscript")
public class AcademicTranscriptController {
    private final IAcademicTranscriptDetailService iAcademicTranscriptDetailService;
    private final IStudentService iStudentService;
    private final IClassroomService iClassroomService;

    @Autowired
    public AcademicTranscriptController(IClassroomService iClassroomService, IAcademicTranscriptDetailService iAcademicTranscriptDetailService, IStudentService iStudentService){
        this.iAcademicTranscriptDetailService = iAcademicTranscriptDetailService;
        this.iStudentService = iStudentService;
        this.iClassroomService = iClassroomService;
    }

    @CheckTeacherRole
    @GetMapping("/showAcademicTranscript")
    public String showAcademicTranscript(Model model,@RequestParam("studentId") String studentId){
        List<AcademicTranscriptDetail> academicTranscriptDetails = iAcademicTranscriptDetailService.getAcademicTranscriptDetailByStudentId(studentId);
        model.addAttribute("academicTranscriptDetails",academicTranscriptDetails);

        Student student = iStudentService.findStudentById(studentId);
        model.addAttribute("student",student);

        Classroom classroom = iClassroomService.findClassroomById(student.getClassroom().getId());
        model.addAttribute("classroom",classroom);

        Log.info("Xem danh sách sổ học bạ, mã học sinh: "+studentId);
        return "/academicTranscript/showAcademicTranscript";
    }

}
