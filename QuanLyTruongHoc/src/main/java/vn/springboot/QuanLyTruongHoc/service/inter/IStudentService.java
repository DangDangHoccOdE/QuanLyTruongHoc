package vn.springboot.QuanLyTruongHoc.service.inter;

import vn.springboot.QuanLyTruongHoc.dto.ParentDto;
import vn.springboot.QuanLyTruongHoc.dto.StudentDto;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.Student;

import java.util.List;

public interface IStudentService {
     List<Student> showAllStudent();

     void saveStudent(Student student);

     Student findStudentById(String id);

     void deleteStudent(Student student);

     Student getStudent(Student student,StudentDto studentDto, ParentDto parentDto, Classroom classroom);

     StudentDto getStudentDto(Student student);

     Student findStudentByAccountEmail(String email);

     List<Student> findStudentByStudentNameAndClassroomId(String name,int classId);

     List<Student> findStudentByGenderAndClassroomId(String gender,int classId);
}
