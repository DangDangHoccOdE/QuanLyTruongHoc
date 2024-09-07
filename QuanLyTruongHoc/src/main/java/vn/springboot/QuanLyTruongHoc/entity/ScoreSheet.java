package vn.springboot.QuanLyTruongHoc.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "scoresheet")
public class ScoreSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scoresheet_id")
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH,
            CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.REFRESH,
            CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "scoreSheet")
    private List<ReportCard> reportCards;


    @Column(name = "semesterYear")
    private String semesterYear;

    public ScoreSheet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public String getSemesterYear() {
        return semesterYear;
    }

    public void setSemesterYear(String semesterYear) {
        this.semesterYear = semesterYear;
    }

    public List<ReportCard> getReportCards() {
        return reportCards;
    }

    public void setReportCards(List<ReportCard> reportCards) {
        this.reportCards = reportCards;
    }

    public Float getScoreBySubjectAndStudent(Subject subject, Student student) {
        for(ReportCard reportCard : reportCards){
            if(reportCard.getSubject().equals(subject) && reportCard.getStudent().equals(student)){
                return reportCard.getScore();
            }
        }
        return null;
    }


}
