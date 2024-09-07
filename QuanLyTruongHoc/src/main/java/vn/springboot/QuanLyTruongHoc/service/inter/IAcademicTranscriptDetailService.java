package vn.springboot.QuanLyTruongHoc.service.inter;

import vn.springboot.QuanLyTruongHoc.dto.AcademicTranscriptDetailDto;
import vn.springboot.QuanLyTruongHoc.entity.AcademicTranscriptDetail;
import vn.springboot.QuanLyTruongHoc.entity.Student;

import java.util.*;

public interface IAcademicTranscriptDetailService {
     List<AcademicTranscriptDetail> getAcademicTranscriptDetailByStudentId(String id);

     void save(AcademicTranscriptDetail academicTranscriptDetail);

     AcademicTranscriptDetail findAcademicTranscriptDetailById(int academicTranscriptDetailId);

     void delete(AcademicTranscriptDetail academicTranscriptDetail);

    AcademicTranscriptDetailDto getAcademicTranscriptDetailDto(AcademicTranscriptDetail academicTranscriptDetail);

    AcademicTranscriptDetail getAcademicTranscriptDetail(AcademicTranscriptDetail academicTranscriptDetail, AcademicTranscriptDetailDto academicTranscriptDetailDto, Student student);

    AcademicTranscriptDetail findAcademicTranscriptDetailByStudentAndSemesterYear(Student student,String semesterYear);
}
