package vn.springboot.QuanLyTruongHoc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.springboot.QuanLyTruongHoc.entity.AcademicTranscriptDetail;
import vn.springboot.QuanLyTruongHoc.entity.Student;

import java.util.*;

@Repository
public interface AcademicTranscriptDetailDao extends JpaRepository<AcademicTranscriptDetail,Integer> {
     List<AcademicTranscriptDetail> findAcademicTranscriptDetailByStudent_StudentId(String id);

     AcademicTranscriptDetail findAcademicTranscriptDetailById(Integer id);

     AcademicTranscriptDetail findAcademicTranscriptDetailByStudentAndSemesterYear(Student student, String semesterYear);
}
