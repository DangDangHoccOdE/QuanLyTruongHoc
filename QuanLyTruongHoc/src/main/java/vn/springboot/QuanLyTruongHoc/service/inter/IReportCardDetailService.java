package vn.springboot.QuanLyTruongHoc.service.inter;

import vn.springboot.QuanLyTruongHoc.dto.ReportCardDetailDto;
import vn.springboot.QuanLyTruongHoc.entity.ReportCard;
import vn.springboot.QuanLyTruongHoc.entity.ReportCardDetail;
import vn.springboot.QuanLyTruongHoc.entity.Student;
import vn.springboot.QuanLyTruongHoc.entity.Subject;

import java.util.*;

public interface IReportCardDetailService {
     List<ReportCardDetail> findReportCardDetailBySubjectIdAndStudentIdAndSemesterYear(int subjectId,String studentId,String semesterYear);

     void processScoreSaving(Subject subject, List<Student> students, List<Float> listScores, ReportCardDetailDto reportCardDetailDto) ;

     void saveScoreMediumOfReportCard(Subject subject, String studentId, String semesterYear);

     ReportCard getOrCreateReportCard(ReportCardDetailDto reportCardDetailDto,Student student,Subject subject);

     void saveListReportCardDetailScore(List<Student> students,Subject subject,ReportCardDetailDto reportCardDetailDto);

     ReportCardDetail findReportCardDetailBySubNameAndStudentIdAndSemesterYearAndTestName(String subName,String studentId,String semesterYear,String testName);
    }
