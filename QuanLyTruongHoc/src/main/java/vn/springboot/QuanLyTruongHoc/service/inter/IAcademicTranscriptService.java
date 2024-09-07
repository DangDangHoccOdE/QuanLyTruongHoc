package vn.springboot.QuanLyTruongHoc.service.inter;

import vn.springboot.QuanLyTruongHoc.entity.AcademicTranscript;

public interface IAcademicTranscriptService {
     void save(AcademicTranscript academicTranscript);

     AcademicTranscript findAcademicTranscriptByStudentId(String id);
}
