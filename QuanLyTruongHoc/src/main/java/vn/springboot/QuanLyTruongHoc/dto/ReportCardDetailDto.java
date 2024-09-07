package vn.springboot.QuanLyTruongHoc.dto;

import java.util.*;

public class ReportCardDetailDto {
    private List<String> studentId;
    private String subjectName;
    private List<Float> score;
    private String testName;
    private String semesterYear;

    public void setScore(List<Float> score) {
        this.score = score;
    }

    public List<Float> getScore() {
        return score;
    }

    public ReportCardDetailDto() {
        this.score = new ArrayList<>();
    }

    public List<String> getStudentId() {
        return studentId;
    }

    public void setStudentId(List<String> studentId) {
        this.studentId = studentId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }


    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getSemesterYear() {
        return semesterYear;
    }

    public void setSemesterYear(String semesterYear) {
        this.semesterYear = semesterYear;
    }


}
