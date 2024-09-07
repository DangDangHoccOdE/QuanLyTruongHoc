package vn.springboot.QuanLyTruongHoc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.springboot.QuanLyTruongHoc.dao.AcademicTranscriptDao;
import vn.springboot.QuanLyTruongHoc.entity.AcademicTranscript;
import vn.springboot.QuanLyTruongHoc.service.inter.IAcademicTranscriptService;

@Service
public class AcademicTranscriptService implements IAcademicTranscriptService {
    private final AcademicTranscriptDao academicTranscriptDao;

    @Autowired
    public AcademicTranscriptService(AcademicTranscriptDao academicTranscriptDao){
        this.academicTranscriptDao= academicTranscriptDao;
    }

    @Override
    public void save(AcademicTranscript academicTranscript) {
        academicTranscriptDao.save(academicTranscript);
    }

    @Override
    public AcademicTranscript findAcademicTranscriptByStudentId(String id) {
        return academicTranscriptDao.findAcademicTranscriptByStudent_StudentId(id);
    }
}
