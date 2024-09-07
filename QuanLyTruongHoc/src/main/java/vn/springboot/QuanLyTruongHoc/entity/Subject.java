package vn.springboot.QuanLyTruongHoc.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private int subId;

    @Column(name = "subject_name")
    private String subName;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "classroom_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "classroom_id"))
    private List<Classroom> classrooms;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH,
            CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "description")
    private String subDes;

    @Column(name = "number_of_periods")
    private int numberOfPeriods;

    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "subject")
    private List<ReportCard> reportCards;

    public Subject() {
    }

    public Subject(String subName, List<Classroom> classrooms, Teacher teacher, String subDes, int numberOfPeriods, List<ReportCard> reportCards) {
        this.subName = subName;
        this.classrooms = classrooms;
        this.teacher = teacher;
        this.subDes = subDes;
        this.numberOfPeriods = numberOfPeriods;
        this.reportCards = reportCards;
    }

    public int getSubId() {
        return subId;
    }

    public List<ReportCard> getReportCards() {
        return reportCards;
    }

    public void setReportCards(List<ReportCard> reportCards) {
        this.reportCards = reportCards;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }


    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getSubDes() {
        return subDes;
    }

    public void setSubDes(String subDes) {
        this.subDes = subDes;
    }

    public int getNumberOfPeriods() {
        return numberOfPeriods;
    }

    public void setNumberOfPeriods(int credit) {
        this.numberOfPeriods = credit;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }
}
