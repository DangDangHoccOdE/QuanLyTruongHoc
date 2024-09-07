package vn.springboot.QuanLyTruongHoc.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import vn.springboot.QuanLyTruongHoc.dao.ReportCardDao;
import vn.springboot.QuanLyTruongHoc.entity.*;
import vn.springboot.QuanLyTruongHoc.service.inter.IReportCardService;
import vn.springboot.QuanLyTruongHoc.service.inter.ISubjectService;
import vn.springboot.QuanLyTruongHoc.utils.SecurityUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportCardService implements IReportCardService {
    private final ReportCardDao reportCardDao;
    private final SecurityUtils securityUtils;
    private final ISubjectService iSubjectService;

    ReportCardService(ReportCardDao reportCardDao, SecurityUtils securityUtils, ISubjectService iSubjectService) {
        this.reportCardDao = reportCardDao;
        this.securityUtils = securityUtils;
        this.iSubjectService = iSubjectService;
    }

    @Override
    public List<ReportCard> findReportCardBySemesterYearAndStudentId(String semesterYear,String studentId) {
        return reportCardDao.findReportCardBySemesterYearAndStudent_StudentId(semesterYear,studentId);
    }

    @Override
    public List<ReportCard> findReportCardByStudentId(String id) {
        return reportCardDao.findReportCardByStudent_StudentId(id);
    }

    @Override
    public ReportCard findReportCardBySemesterYearAndStudentIdAndSubjectId(String semesterYear, String studentId, int subjectId) {
        return reportCardDao.findReportCardBySemesterYearAndStudent_StudentIdAndSubjectSubId(semesterYear,studentId,subjectId);
    }

    @Override
    @Transactional
    public void save(ReportCard reportCard) {
         reportCardDao.save(reportCard);
    }

    @Override
    public List<String> findSemesterYearOfStudent(List<ReportCard> reportCards) {
        List<String> semesterYears = new ArrayList<>();
        for (ReportCard reportCard : reportCards) {
            if (!semesterYears.contains(reportCard.getSemesterYear())) {
                semesterYears.add(reportCard.getSemesterYear());
            }
        }
        return semesterYears;
    }

    @Override
    public List<ReportCard> findReportCardByRole(Teacher teacher, Student student, String semesterYear) {
        Classroom classroom =student.getClassroom();
        List<ReportCard> reportCards;
        if (securityUtils.hasPrincipal() || classroom.getTeacher() == teacher) {
            reportCards = findReportCardBySemesterYearAndStudentId(semesterYear, student.getStudentId());
        } else {
            List<Subject> subjects = iSubjectService.uniqueSubjects(student.getClassroom(), teacher);

            reportCards = new ArrayList<>();
            for (Subject subject : subjects) {
                ReportCard reportCard = findReportCardBySemesterYearAndStudentIdAndSubjectId(semesterYear, student.getStudentId(), subject.getSubId());
                if (reportCard != null) {
                    reportCards.add(reportCard);
                }
            }
        }
        return reportCards;
    }
}
