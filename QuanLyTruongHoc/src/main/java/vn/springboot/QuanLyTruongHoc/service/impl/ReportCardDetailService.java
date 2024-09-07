package vn.springboot.QuanLyTruongHoc.service.impl;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.springboot.QuanLyTruongHoc.dao.ReportCardDetailDao;
import vn.springboot.QuanLyTruongHoc.dto.ReportCardDetailDto;
import vn.springboot.QuanLyTruongHoc.entity.*;
import vn.springboot.QuanLyTruongHoc.service.inter.IReportCardDetailService;
import vn.springboot.QuanLyTruongHoc.service.inter.IReportCardService;
import vn.springboot.QuanLyTruongHoc.service.inter.IScoreSheetService;
import vn.springboot.QuanLyTruongHoc.utils.ScoreMediumCalculator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportCardDetailService implements IReportCardDetailService {
    private final ReportCardDetailDao reportCardDetailDao;
    private final ScoreMediumCalculator scoreMediumCalculator;
    private final IReportCardService iReportCardService;
    private final IScoreSheetService iScoreSheetService;

    @Autowired
    public ReportCardDetailService(IScoreSheetService iScoreSheetService, IReportCardService iReportCardService, ScoreMediumCalculator scoreMediumCalculator, ReportCardDetailDao reportCardDetailDao) {
        this.reportCardDetailDao = reportCardDetailDao;
        this.scoreMediumCalculator = scoreMediumCalculator;
        this.iReportCardService = iReportCardService;
        this.iScoreSheetService = iScoreSheetService;
    }

    @Override
    public List<ReportCardDetail> findReportCardDetailBySubjectIdAndStudentIdAndSemesterYear(int subjectId, String studentId, String semesterYear) {
        return reportCardDetailDao.findReportCardDetailBySubject_SubIdAndStudent_StudentIdAndSemesterYear(subjectId, studentId, semesterYear);
    }

    @Transactional
    @Override
    public void saveScoreMediumOfReportCard(Subject subject, String studentId, String semesterYear) {
        List<ReportCardDetail> reportCardDetailList = findReportCardDetailBySubjectIdAndStudentIdAndSemesterYear(subject.getSubId(), studentId, semesterYear);

        float scoreMedium = scoreMediumCalculator.scoreMediumCalculator(reportCardDetailList);
        ReportCard reportCard = iReportCardService.findReportCardBySemesterYearAndStudentIdAndSubjectId(semesterYear, studentId, subject.getSubId());

        if (reportCard != null) {
            reportCard.setScore(scoreMedium);
            iReportCardService.save(reportCard);
        }
    }

    @Transactional
    @Override
    public void processScoreSaving(Subject subject, List<Student> students, List<Float> listScores, ReportCardDetailDto reportCardDetailDto) {
        for (int i = 0; i < students.size(); i++) {
            Float score = listScores.get(i);

            if (score != null) {
                Student student = students.get(i);
                if (student != null) {
                    ReportCard reportCard = getOrCreateReportCard(reportCardDetailDto, student, subject);
                    saveReportCardDetail(reportCard, subject, score, reportCardDetailDto.getTestName());
                }
            }
        }
    }

    @Transactional
    public void saveReportCardDetail(ReportCard reportCard, Subject subject, Float score, String testName) {
        ReportCardDetail reportCardDetail = new ReportCardDetail();
        reportCardDetail.setStudent(reportCard.getStudent());
        reportCardDetail.setSubject(subject);
        reportCardDetail.setTestDay(new Date());
        reportCardDetail.setTestName(testName);
        reportCardDetail.setScore(score);
        reportCardDetail.setReportCard(reportCard);
        reportCardDetail.setSemesterYear(reportCard.getSemesterYear());
        reportCardDetailDao.save(reportCardDetail);
    }

    @Transactional
    @Override
    public ReportCard getOrCreateReportCard(ReportCardDetailDto reportCardDetailDto, Student student, Subject subject) {
        ReportCard reportCard = iReportCardService.findReportCardBySemesterYearAndStudentIdAndSubjectId(reportCardDetailDto.getSemesterYear(), student.getStudentId(), subject.getSubId());

        if (reportCard == null) {
            reportCard = new ReportCard();
            reportCard.setSemesterYear(reportCardDetailDto.getSemesterYear());
            reportCard.setStudent(student);
            reportCard.setSubject(subject);
            reportCard.setClassroom(student.getClassroom());
            ScoreSheet scoreSheet = iScoreSheetService.findScoreSheetBySemesterYearAndClassId(reportCardDetailDto.getSemesterYear(), student.getClassroom().getId());
            if (scoreSheet == null) {
                scoreSheet = new ScoreSheet();
                scoreSheet.setSemesterYear(reportCardDetailDto.getSemesterYear());
                scoreSheet.setClassroom(student.getClassroom());
                reportCard.setScoreSheet(scoreSheet);
            } else {
                reportCard.setScoreSheet(scoreSheet);
            }
        }

        return reportCard;
    }

    @Override
    @Transactional
    public void saveListReportCardDetailScore(List<Student> students,Subject subject,ReportCardDetailDto reportCardDetailDto) {
        List<Student> studentCopy = new ArrayList<>(students);
        for (Student student : studentCopy) {
            saveScoreMediumOfReportCard(subject, student.getStudentId(), reportCardDetailDto.getSemesterYear());
        }
    }

    @Override
    public ReportCardDetail findReportCardDetailBySubNameAndStudentIdAndSemesterYearAndTestName(String subjectName, String studentId, String semesterYear, String testName) {
        return reportCardDetailDao.findReportCardDetailBySubject_SubNameAndStudent_StudentIdAndSemesterYearAndTestName(subjectName,studentId,semesterYear,testName);
    }
}
