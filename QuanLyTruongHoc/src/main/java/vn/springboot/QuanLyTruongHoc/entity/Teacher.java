package vn.springboot.QuanLyTruongHoc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @Column(name = "teacher_id")
    private String teacherId;
    @Column(name = "teacher_name")
    private String teacherName;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.DETACH,
            CascadeType.PERSIST,CascadeType.REFRESH} )
    @JoinTable(name = "roles_teacher" , joinColumns = @JoinColumn(name = "teacher_id"),
                inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private List<Roles> roles;

    @Column(name = "teacher_phone")
    private String teacherPhone;

    // HomeroomTeacher not List<teacher> teach
    @OneToMany(
            cascade = {CascadeType.MERGE,CascadeType.DETACH,
                    CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "teacher")
    private List<Classroom> classRooms;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.REFRESH,
            CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "teacher")
    private List<Subject> subjects;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    public Teacher() {
    }

    public Teacher(String teacherName, List<Roles> roles, String teacherPhone, List<Classroom> classRooms, List<Subject> subjects, Date dateOfBirth, String gender, String address, Account account) {
        this.teacherName = teacherName;
        this.roles = roles;
        this.teacherPhone = teacherPhone;
        this.classRooms = classRooms;
        this.subjects = subjects;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.account = account;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public List<Classroom> getClassRooms() {
        return classRooms;
    }

    public void setClassRooms(List<Classroom> classRooms) {
        this.classRooms = classRooms;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
