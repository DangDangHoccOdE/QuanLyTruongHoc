package vn.springboot.QuanLyTruongHoc.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "student_name")
    private String studentName;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student")
    private List<ReportCard> reportCards;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private Parent parent;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "student")
    private AcademicTranscript academicTranscript;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roles_id")
    private Roles roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "student_phone")
    private String studentPhone;

    public Student() {
    }

    public Student(String studentName, Classroom classroom, List<ReportCard> reportCards, Parent parent, AcademicTranscript academicTranscript, Roles roles, Account account, String gender, Date dateOfBirth, String address, String studentPhone) {
        this.studentName = studentName;
        this.classroom = classroom;
        this.reportCards = reportCards;
        this.parent = parent;
        this.academicTranscript = academicTranscript;
        this.roles = roles;
        this.account = account;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.studentPhone = studentPhone;
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

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public AcademicTranscript getAcademicTranscript() {
        return academicTranscript;
    }

    public void setAcademicTranscript(AcademicTranscript academicTranscript) {
        this.academicTranscript = academicTranscript;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public List<ReportCard> getReportCards() {
        return reportCards;
    }

    public void setReportCards(List<ReportCard> reportCards) {
        this.reportCards = reportCards;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}
