package vn.springboot.QuanLyTruongHoc.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "classroom")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_id")
    private int id;

    @Column(name = "classroom_name")
    private String className;

    // homeroom teacher NOT list<teacher> teach
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE,CascadeType.DETACH,
                    CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE,CascadeType.DETACH,
                    CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "classroom_subject",
            joinColumns = @JoinColumn(name = "classroom_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> subjects;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "classroom")
    private List<Student> students  ;

    @OneToMany(mappedBy = "classroom",
            cascade = CascadeType.ALL
    )
    private List<RegisterNotebookDetail> registerNotebookDetails;

    @OneToOne(mappedBy ="classroom",cascade = CascadeType.ALL)
    private RegisterNotebook registerNotebook;

    @Column(name = "classroom_block")
    private String classBlock;

    @Column(name = "school_year")
    private int schoolYear;

    public Classroom() {
    }

    public Classroom(int id, String className, Teacher teacher, List<Subject> subjects, List<Student> students, List<RegisterNotebookDetail> registerNotebookDetails, RegisterNotebook registerNotebook, String classBlock, int schoolYear) {
        this.id = id;
        this.className = className;
        this.teacher = teacher;
        this.subjects = subjects;
        this.students = students;
        this.registerNotebookDetails = registerNotebookDetails;
        this.registerNotebook = registerNotebook;
        this.classBlock = classBlock;
        this.schoolYear = schoolYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getClassBlock() {
        return classBlock;
    }

    public void setClassBlock(String classBlock) {
        this.classBlock = classBlock;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<RegisterNotebookDetail> getRegisterNotebookDetails() {
        return registerNotebookDetails;
    }

    public void setRegisterNotebookDetails(List<RegisterNotebookDetail> registerNotebookDetails) {
        this.registerNotebookDetails = registerNotebookDetails;
    }

    public RegisterNotebook getRegisterNotebook() {
        return registerNotebook;
    }

    public void setRegisterNotebook(RegisterNotebook registerNotebook) {
        this.registerNotebook = registerNotebook;
    }
}
