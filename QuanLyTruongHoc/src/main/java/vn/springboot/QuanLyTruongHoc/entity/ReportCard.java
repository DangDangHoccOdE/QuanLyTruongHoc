package vn.springboot.QuanLyTruongHoc.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "reportcard")
public class ReportCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reportcard_id")
    private int reportCardId;

    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH,
                    CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH,
            CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH,
            CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportCard")
    private List<ReportCardDetail> reportCardDetails;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH,
            CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "scoresheet_id")
    private ScoreSheet scoreSheet;

    @Column(name = "score_medium")
    private float score;

    @Column(name = "semester_year")
    private String semesterYear;

    public ReportCard() {
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

    public List<ReportCardDetail> getReportCardDetails() {
        return reportCardDetails;
    }

    public void setReportCardDetails(List<ReportCardDetail> reportCardDetails) {
        this.reportCardDetails = reportCardDetails;
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

    public int getReportCardId() {
        return reportCardId;
    }

    public void setReportCardId(int reportCardId) {
        this.reportCardId = reportCardId;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }


    public ScoreSheet getScoreSheet() {
        return scoreSheet;
    }

    public void setScoreSheet(ScoreSheet scoreSheet) {
        this.scoreSheet = scoreSheet;
    }

}
