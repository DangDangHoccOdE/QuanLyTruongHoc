package vn.springboot.QuanLyTruongHoc.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import vn.springboot.QuanLyTruongHoc.entity.Student;

public class ReportCardDto {
    private String subjectName;

    @DecimalMin(value = "0",message = "Số điểm không hợp lệ")
    @DecimalMax(value = "9.9",message = "Số điểm không hợp lệ")
    private float score;

    private String testName;

    private String semesterYear;

    private Student student;

    public ReportCardDto() {
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
