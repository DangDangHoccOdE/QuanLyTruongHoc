package vn.springboot.QuanLyTruongHoc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.springboot.QuanLyTruongHoc.entity.ReportCardDetail;

import java.util.*;
@Repository
public interface ReportCardDetailDao extends JpaRepository<ReportCardDetail, Integer> {
     List<ReportCardDetail> findReportCardDetailBySubject_SubIdAndStudent_StudentIdAndSemesterYear(int subjectId,String studentId,String semesterYear);

     ReportCardDetail findReportCardDetailBySubject_SubNameAndStudent_StudentIdAndSemesterYearAndTestName(String subName,String studentId,String semesterYear,String testName);
}
