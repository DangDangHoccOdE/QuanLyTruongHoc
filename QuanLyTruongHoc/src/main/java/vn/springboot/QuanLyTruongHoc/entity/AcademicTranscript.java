package vn.springboot.QuanLyTruongHoc.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "academictranscript")
public class AcademicTranscript {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "academictranscipt_id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "academicTranscript")
    private List<AcademicTranscriptDetail> academicTranscriptDetails;

    public AcademicTranscript() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<AcademicTranscriptDetail> getAcademicTranscriptDetails() {
        return academicTranscriptDetails;
    }

    public void setAcademicTranscriptDetails(List<AcademicTranscriptDetail> academicTranscriptDetails) {
        this.academicTranscriptDetails = academicTranscriptDetails;
    }
}
