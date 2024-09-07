package vn.springboot.QuanLyTruongHoc.entity;

import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roles_id")
    private int id;

    @Column(name = "namerole")
    private String roleName;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "roles_teacher" ,joinColumns = @JoinColumn(name = "roles_id"),
                inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private List<Teacher> teachers;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH,
            CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "roles")
    private List<Student> students;

    public Roles() {

    }

    public Roles(String roleName) {
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
