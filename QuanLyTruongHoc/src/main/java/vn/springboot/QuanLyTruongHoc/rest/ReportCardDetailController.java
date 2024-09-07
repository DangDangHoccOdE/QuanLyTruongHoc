package vn.springboot.QuanLyTruongHoc.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.springboot.QuanLyTruongHoc.annotation.CheckTeacherRole;
import vn.springboot.QuanLyTruongHoc.dto.ReportCardDetailDto;
import vn.springboot.QuanLyTruongHoc.entity.*;
import vn.springboot.QuanLyTruongHoc.service.inter.*;
import vn.springboot.QuanLyTruongHoc.utils.Log;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/reportCardDetail")
public class ReportCardDetailController {
    private final IReportCardDetailService iReportCardDetailService;
    private final ITeacherService iTeacherService;
    private final IClassroomService iClassroomService;
    private final ISubjectService iSubjectService;
    private final IStudentService iStudentService;
    private final IAcademicTranscriptDetailService iAcademicTranscriptDetailService;

    @Autowired
    public ReportCardDetailController(IClassroomService iClassroomService, IReportCardDetailService iReportCardDetailService, ITeacherService iTeacherService, ISubjectService iSubjectService, IStudentService iStudentService, IAcademicTranscriptDetailService iAcademicTranscriptDetailService) {
        this.iReportCardDetailService = iReportCardDetailService;
        this.iTeacherService = iTeacherService;
        this.iSubjectService = iSubjectService;
        this.iClassroomService = iClassroomService;
        this.iStudentService = iStudentService;
        this.iAcademicTranscriptDetailService = iAcademicTranscriptDetailService;
    }

    @GetMapping("/showReportCardDetail")
    public String showReportCardDetail(Model model, @RequestParam("subjectId") int subjectId, @RequestParam("studentId") String studentId, @RequestParam("semesterYear") String semesterYear)  {
        String email = iTeacherService.getEmailTeacher();
        Subject subject = iSubjectService.findSubjectById(subjectId);
        Student student = iStudentService.findStudentById(studentId);
        List<ReportCardDetail> reportCardDetails = iReportCardDetailService.findReportCardDetailBySubjectIdAndStudentIdAndSemesterYear(subjectId, studentId, semesterYear);
        model.addAttribute("reportCardDetails", reportCardDetails);

        ReportCardDetail reportCardDetail = reportCardDetails.get(0);
        model.addAttribute("reportCardDetail", reportCardDetail);
        model.addAttribute("studentId", studentId);
        model.addAttribute("semesterYear", semesterYear);

        Log.info("Xem chi tiết điểm, tên học sinh: " + student.getStudentName() + " ,môn học: " + subject.getSubName()
                + " ,năm học: " + semesterYear + " ,người sử dụng: " + email);
        return "reportCardDetail/showReportCardDetail";
    }

    @CheckTeacherRole
    @GetMapping("/create")
    public String createReportCard(Model model, @RequestParam("classId") int classId){
        Classroom classroom = iClassroomService.findClassroomById(classId);

        String teacherEmail = iTeacherService.getEmailTeacher();
        Teacher teacher = iTeacherService.findTeacherByAccountEmail(teacherEmail);

        model.addAttribute("classroom", classroom);

        List<Student> students = classroom.getStudents();
        model.addAttribute("students", students);
        ReportCardDetailDto reportCardDetailDto = new ReportCardDetailDto();
        model.addAttribute("reportCardDetailDto", reportCardDetailDto);

        List<Subject> subjects = iSubjectService.getSubjectByRoleToMark(classroom, teacher);
        model.addAttribute("subjects", subjects);

        Log.info("Thêm điểm cho học sinh, lớp: " + classroom.getClassName() + " ,người sử dụng: " + teacherEmail);
        return "/reportCardDetail/createReportCardDetail";
    }

    @CheckTeacherRole
    @PostMapping("/create")
    public String saveReportCard(Model model, @Valid @ModelAttribute ReportCardDetailDto reportCardDetailDto, BindingResult result, @RequestParam("classId") int classId, RedirectAttributes redirectAttributes)  {
        Classroom classroom = iClassroomService.findClassroomById(classId);

        String teacherEmail = iTeacherService.getEmailTeacher();
        Teacher teacher = iTeacherService.findTeacherByAccountEmail(teacherEmail);

        model.addAttribute("classroom", classroom);
        List<Student> students = classroom.getStudents();
        model.addAttribute("students", students);

        List<Subject> subjects = iSubjectService.getSubjectByRoleToMark(classroom, teacher);
        model.addAttribute("subjects", subjects);
        List<Float> listScores = reportCardDetailDto.getScore();

        if(reportCardDetailDto.getSubjectName()==null){
            result.addError(new FieldError("reportCardDetailDto", "testName", "Phải chọn 1 môn học!"));
        }else if (students.isEmpty()) {
            result.addError(new FieldError("reportCardDetailDto", "testName", "Lớp trống, không thể thêm điểm!"));
        } else {
            Student checkStudent = students.get(0);
            String subName = reportCardDetailDto.getSubjectName();
            String semesterYear = reportCardDetailDto.getSemesterYear();
            String testName1 = "Điểm giữa kỳ";
            String testName2 = "Điểm cuối kỳ";

            if ((reportCardDetailDto.getTestName().equals(testName1) && iReportCardDetailService.findReportCardDetailBySubNameAndStudentIdAndSemesterYearAndTestName(subName, checkStudent.getStudentId(), semesterYear, testName1) != null)
                    || (reportCardDetailDto.getTestName().equals(testName2) && iReportCardDetailService.findReportCardDetailBySubNameAndStudentIdAndSemesterYearAndTestName(subName, checkStudent.getStudentId(), semesterYear, testName2) != null)) {
                result.addError(new FieldError("reportCardDetailDto", "testName", "Bài kiểm tra đã tồn tại, không thể thêm!"));
            }else{
                for(Student student : students){
                    if(iAcademicTranscriptDetailService.findAcademicTranscriptDetailByStudentAndSemesterYear(student,reportCardDetailDto.getSemesterYear())!=null){
                        result.addError(new FieldError("reportCardDetailDto", "testName", "Đã có sổ học bạ, không thể thêm điểm!"));
                        break;
                    }
                }
            }
        }

        if (result.hasErrors()) {
            return "reportCardDetail/createReportCardDetail";
        }

        Subject subject = iSubjectService.findSubjectByName(reportCardDetailDto.getSubjectName());
        iReportCardDetailService.processScoreSaving(subject, students, listScores, reportCardDetailDto);

        List<Student> studentCopy = new ArrayList<>(students);
        iReportCardDetailService.saveListReportCardDetailScore(studentCopy, subject, reportCardDetailDto);
        redirectAttributes.addAttribute("classId", classId);

        for (int i = 0; i < listScores.size(); i++) {
            Float score = listScores.get(i);
            Student student = studentCopy.get(i);
            if (score != null && student != null) {
                Log.info("Thêm điểm thành công, học sinh: " + student.getStudentName() + " ,lớp: " + classroom.getClassName() + " ,môn học: " + subject.getSubName()
                        + " ,loại bài kiểm tra: " + reportCardDetailDto.getTestName() + " ,điểm: " + score + " ,ngưởi sử dụng: " + teacherEmail);
            }
        }

        return "redirect:/api/student/showAllStudent";
    }
}
