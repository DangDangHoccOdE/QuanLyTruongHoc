package vn.springboot.QuanLyTruongHoc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.springboot.QuanLyTruongHoc.entity.Student;
import java.util.*;

@Repository
public interface StudentDao extends JpaRepository<Student, Integer> {
     Student findStudentByStudentId(String id);

     Student findStudentByAccount_Email(String email);

     @Query("Select s from Student s where s.studentName Like %:name% and s.classroom.id=:classId")
     List<Student> findStudentByStudentNameAndClassroom_Id(@Param("name") String name,@Param("classId")int classId);
     List<Student> findStudentByGenderAndClassroom_Id(String gender,int classId);
}
