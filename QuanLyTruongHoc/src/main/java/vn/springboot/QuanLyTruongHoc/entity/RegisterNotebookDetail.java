package vn.springboot.QuanLyTruongHoc.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "registernotebookdetail")
public class RegisterNotebookDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registernotebookdetail_id")
    private int id;

    @ManyToOne(
            cascade = {CascadeType.DETACH,CascadeType.REFRESH,
                    CascadeType.PERSIST,CascadeType.MERGE}
    )
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @ManyToOne(
            cascade = {CascadeType.DETACH,CascadeType.REFRESH,
                    CascadeType.PERSIST,CascadeType.MERGE}
    )
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(
            cascade = {CascadeType.DETACH,CascadeType.REFRESH,
                    CascadeType.PERSIST,CascadeType.MERGE}
    )
    @JoinColumn(name = "registernotebook_id")
    private RegisterNotebook registerNotebook;

    @Column(name = "semester")
    private String semester;

    @Column(name = "timeteaching")
    private Date timeTeaching;

    @Column(name = "content")
    private String content;

    @Column(name = "lesson")
    private String lesson;

    public RegisterNotebookDetail() {
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Date getTimeTeaching() {
        return timeTeaching;
    }

    public void setTimeTeaching(Date timeTeaching) {
        this.timeTeaching = timeTeaching;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public RegisterNotebook getRegisterNotebook() {
        return registerNotebook;
    }

    public void setRegisterNotebook(RegisterNotebook registerNotebook) {
        this.registerNotebook = registerNotebook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
