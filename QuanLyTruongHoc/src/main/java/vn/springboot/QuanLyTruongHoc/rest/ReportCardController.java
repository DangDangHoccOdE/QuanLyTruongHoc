package vn.springboot.QuanLyTruongHoc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.ReportCard;
import vn.springboot.QuanLyTruongHoc.entity.Student;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;
import vn.springboot.QuanLyTruongHoc.service.inter.IReportCardService;
import vn.springboot.QuanLyTruongHoc.service.inter.IStudentService;
import vn.springboot.QuanLyTruongHoc.service.inter.ITeacherService;
import vn.springboot.QuanLyTruongHoc.utils.Log;
import vn.springboot.QuanLyTruongHoc.utils.ScoreMediumCalculator;

import java.util.List;

@Controller
@RequestMapping("/api/reportCard")
public class ReportCardController {
    private final IStudentService iStudentService;
    private final IReportCardService iReportCardService;
    private final ITeacherService iTeacherService;
    private final ScoreMediumCalculator scoreMediumCalculator;

    @Autowired
    public ReportCardController(IReportCardService iReportCardService, IStudentService iStudentService, ITeacherService iTeacherService, ScoreMediumCalculator scoreMediumCalculator) {
        this.iStudentService = iStudentService;
        this.iReportCardService = iReportCardService;
        this.iTeacherService = iTeacherService;
        this.scoreMediumCalculator = scoreMediumCalculator;
    }

    @GetMapping("/showReportCard")
    public String getAllReportCard(Model model, @RequestParam("studentId") String studentId, @RequestParam("semesterYear") String semesterYear)  {
        Student student = iStudentService.findStudentById(studentId);
        model.addAttribute("student", student);

        String email = iTeacherService.getEmailTeacher();
        Teacher teacher = iTeacherService.findTeacherByAccountEmail(email);
        Student studentFind = iStudentService.findStudentByAccountEmail(email);

        List<ReportCard> reportCards;
        if(teacher!=null){
            reportCards =iReportCardService.findReportCardByRole(teacher,student,semesterYear);
        }else {
            Classroom classroomOfStudentFind = studentFind.getClassroom();
            reportCards = iReportCardService.findReportCardByRole(classroomOfStudentFind.getTeacher(), student, semesterYear);
        }
        model.addAttribute("reportCards", reportCards);

        Float scoreMediumOfStudent = scoreMediumCalculator.scoreMediumOfStudent(reportCards);
        model.addAttribute("scoreMediumOfStudent",scoreMediumOfStudent);

        Log.info("Xem bảng điểm, tên học sinh: "+student.getStudentName()+" ,năm học: "+semesterYear+" , người sử dụng: "+email);
        return "/reportCard/showReportCard";
    }

}
