package vn.springboot.QuanLyTruongHoc.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "registernotebook")
public class RegisterNotebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registernotebook_id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @OneToMany(mappedBy = "registerNotebook", cascade = CascadeType.ALL)
    private List<RegisterNotebookDetail> registerNotebookDetails;

    public RegisterNotebook() {
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public List<RegisterNotebookDetail> getRegisterNotebookDetails() {
        return registerNotebookDetails;
    }

    public void setRegisterNotebookDetails(List<RegisterNotebookDetail> registerNotebookDetails) {
        this.registerNotebookDetails = registerNotebookDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

