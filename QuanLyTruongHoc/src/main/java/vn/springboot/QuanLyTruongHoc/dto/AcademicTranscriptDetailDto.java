package vn.springboot.QuanLyTruongHoc.dto;

import jakarta.validation.constraints.*;
public class AcademicTranscriptDetailDto {
    private String studentId;

    private String studentName;

    private String className;

    private String teacherName;

    @DecimalMin(value = "0",message = "Số điểm không hợp lệ")
    @DecimalMax(value = "10",message = "Số điểm không hợp lệ")
    @NotNull(message = "Thông tin bắt buộc!")
    private Float score;
    private String semesterYear;

    @NotBlank(message = "Phải có nhận xét của giáo viên")
    private String reviewOfTeachersAndParents;

    @NotBlank(message = "Thông tin bắt buộc!")
    private String time;

    public AcademicTranscriptDetailDto() {
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getSemesterYear() {
        return semesterYear;
    }

    public void setSemesterYear(String semesterYear) {
        this.semesterYear = semesterYear;
    }

    public String getReviewOfTeachersAndParents() {
        return reviewOfTeachersAndParents;
    }

    public void setReviewOfTeachersAndParents(String reviewOfTeachersAndParents) {
        this.reviewOfTeachersAndParents = reviewOfTeachersAndParents;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
