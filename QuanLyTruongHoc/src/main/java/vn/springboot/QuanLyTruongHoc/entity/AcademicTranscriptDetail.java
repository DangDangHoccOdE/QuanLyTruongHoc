package vn.springboot.QuanLyTruongHoc.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@Table(name = "academictranscriptdetail")
public class AcademicTranscriptDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "academictransciptdetail_id")
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH,
            CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH,
            CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH,
            CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "academictranscipt_id")
    private AcademicTranscript academicTranscript;

    @Column(name = "score")
    private float score;

    @Column(name = "semester_year")
    private String semesterYear;

    @Column(name = "review_of_teachers_and_parents")
    private String reviewOfTeachersAndParents;

    @Column(name = "time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;

    @Column(name = "academic_ability")
    private String academicAbility;

    public AcademicTranscriptDetail() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public AcademicTranscript getAcademicTranscript() {
        return academicTranscript;
    }

    public void setAcademicTranscript(AcademicTranscript academicTranscript) {
        this.academicTranscript = academicTranscript;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAcademicAbility() {
        return academicAbility;
    }

    public void setAcademicAbility(String academicAbility) {
        this.academicAbility = academicAbility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
