package vn.springboot.QuanLyTruongHoc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.springboot.QuanLyTruongHoc.entity.ReportCard;
import java.util.*;
@Repository
public interface ReportCardDao extends JpaRepository<ReportCard,Integer> {
     List<ReportCard> findReportCardBySemesterYearAndStudent_StudentId(String semesterAndYear,String studentId);

     ReportCard findReportCardBySemesterYearAndStudent_StudentIdAndSubjectSubId(String semesterYear,String studentId,int subjectId);

     List<ReportCard> findReportCardByStudent_StudentId(String id);
}
