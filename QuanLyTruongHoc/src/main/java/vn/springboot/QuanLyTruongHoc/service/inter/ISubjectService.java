package vn.springboot.QuanLyTruongHoc.service.inter;

import vn.springboot.QuanLyTruongHoc.dto.SubjectDto;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.Subject;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;

import java.util.List;
public interface ISubjectService {
    List<Subject> showAllSubject();

     Subject findSubjectByName(String name);

     Subject findSubjectById(int id);

     void delete(Subject subject);

    void saveSubject(SubjectDto subjectDto, Subject subject);

    SubjectDto getSubjectDto(Subject subject);

    List<Subject> uniqueSubjects(Classroom classroom, Teacher teacher);

    List<Subject> getSubjectByRole(Classroom classroom, Teacher teacher);

    List<Subject> getSubjectByRoleToMark(Classroom classroom, Teacher teacher);

}
