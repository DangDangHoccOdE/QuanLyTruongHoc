package vn.springboot.QuanLyTruongHoc.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "reportcarddetail")
public class ReportCardDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reportcarddetail_id")
    private int reportCardDetailId;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH,
            CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH,
            CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH,
            CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "reportcard_id")
    private ReportCard reportCard;

    @Column(name = "test_name")
    private String testName;

    @Column(name = "score")
    private float score;

    @Column(name = "test_day")
    private Date testDay;

    @Column(name = "semesterYear")
    private String semesterYear;

    public ReportCardDetail() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public ReportCard getReportCard() {
        return reportCard;
    }

    public void setReportCard(ReportCard reportCard) {
        this.reportCard = reportCard;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Date getTestDay() {
        return testDay;
    }

    public void setTestDay(Date testDay) {
        this.testDay = testDay;
    }

    public String getSemesterYear() {
        return semesterYear;
    }

    public void setSemesterYear(String semesterYear) {
        this.semesterYear = semesterYear;
    }

    public int getReportCardDetailId() {
        return reportCardDetailId;
    }

    public void setReportCardDetailId(int reportCardDetailId) {
        this.reportCardDetailId = reportCardDetailId;
    }

}
