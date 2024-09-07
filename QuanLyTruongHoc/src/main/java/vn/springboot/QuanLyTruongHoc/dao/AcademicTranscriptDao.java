package vn.springboot.QuanLyTruongHoc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.springboot.QuanLyTruongHoc.entity.AcademicTranscript;

@Repository
public interface AcademicTranscriptDao extends JpaRepository<AcademicTranscript, Integer> {
    AcademicTranscript findAcademicTranscriptByStudent_StudentId(String id);

}
